package com.nothing.android_week9_menu_searchview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ContactAdapter.WorkingWithContact {
    private static final String TAG = "MainActivity";
    private List<Contact> mContactList, mSearchList;
    private ContactAdapter mContactAdapter, mSearchAdapter;
    private RecyclerView mRecyclerContact;
    private LoadContractThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        ContactDataSource contactDataSource = new ContactDataSource(this);
        mSearchList = new ArrayList<>();
        mRecyclerContact = (RecyclerView) findViewById(R.id.recycler_contact);
        mRecyclerContact.setLayoutManager(new LinearLayoutManager(this));
        mContactList = contactDataSource.getAllContact();
        mContactAdapter = new ContactAdapter(mContactList);
        mSearchAdapter = new ContactAdapter(mSearchList);
        mRecyclerContact.setAdapter(mContactAdapter);
//        Button buttonContactMenu = (Button) findViewById(R.id.button_context);
//        registerForContextMenu(buttonContactMenu);
//        buttonContactMenu.setOnClickListener(this);
        mThread = new LoadContractThread();
        mThread.start();
        Log.d(TAG, "LoadContactThread: " + Thread.currentThread());
    }

    private void getContacts() {
        try {
            Thread.sleep(5000);
            mContactList.clear();
            mContactList.add(new Contact("a", "b", "c"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contact_context_menu, menu);
    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_remove:
//                mContactList.remove(0);
//                mContactAdapter.notifyItemRemoved(0);
//                Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        MenuItem menuSearch = menu.findItem(R.id.menu_search);
        SearchView searchView;
        searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mSearchList.clear();
                mSearchAdapter.notifyDataSetChanged();
                mRecyclerContact.setAdapter(mSearchAdapter);
                Contact tempContact;
                for (Contact c : mContactList) {
                    if (c.getName().contains(newText) || c.getAddress().contains(newText) || c
                        .getNumber().contains(newText)) {
                        tempContact = c;
                        tempContact.setSearch(newText.toLowerCase());
                        mSearchList.add(tempContact);
                        mSearchAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mRecyclerContact.setAdapter(mContactAdapter);
                mSearchList.clear();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ContactDataSource db = new ContactDataSource(this);
        switch (item.getItemId()) {
            case R.id.menu_add:
                if (mRecyclerContact.getAdapter() == mSearchAdapter) {
                    Toast.makeText(this, R.string.close_search, Toast.LENGTH_SHORT).show();
                } else {
                    Random random = new Random();
                    int randomNumber = random.nextInt(100);
                    Contact randomContact = new Contact("Contact " + randomNumber, "09234923420",
                        "Hanoi + " + randomNumber);
                    long rowId = db.insertContact(randomContact);
                    if (rowId > 0) {
                        randomContact.setId((int) rowId);
                        mContactList.add(randomContact);
                        mContactAdapter.notifyItemInserted(mContactList.size() - 1);
                    } else {
                        Toast.makeText(this, R.string.insert_failed, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuPop(final int position, final View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.contact_context_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final ContactDataSource db = new ContactDataSource(v.getContext());
                switch (item.getItemId()) {
                    case R.id.menu_remove:
                        AlertDialog.Builder removeBuilder = new AlertDialog.Builder(v.getContext());
                        removeBuilder.setMessage(R.string.confirm_message)
                            .setTitle(R.string.warning)
                            .setPositiveButton(R.string.remove,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int realPosition = 0;
                                        if (mRecyclerContact.getAdapter() == mSearchAdapter) {
                                            for (Contact c : mContactList) {
                                                if (c.getId() ==
                                                    mSearchList.get(position).getId()) {
                                                    break;
                                                }
                                                realPosition++;
                                            }
                                            if (db
                                                .deleteContact(mContactList.get(realPosition).getId
                                                    ())) {
                                                mContactList.remove(realPosition);
                                                mContactAdapter.notifyItemRemoved(realPosition);
                                                mSearchList.remove(position);
                                                mSearchAdapter.notifyItemRemoved(position);
                                            } else {
                                                Toast.makeText(MainActivity.this, R.string
                                                        .remove_failed,
                                                    Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            if (db.deleteContact(mContactList.get(position).getId
                                                ())) {
                                                mContactList.remove(position);
                                                mContactAdapter.notifyItemRemoved(position);
                                            } else {
                                                Toast.makeText(MainActivity.this, R.string
                                                        .remove_failed,
                                                    Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                })
                            .setNegativeButton(R.string.cancel, null);
                        removeBuilder.show();
                        break;
                    case R.id.menu_edit:
                        AlertDialog.Builder updateBuilder = new AlertDialog.Builder(v.getContext());
                        final View view = getLayoutInflater().inflate(R.layout.edit_dialog, null);
                        updateBuilder.setView(view)
                            .setPositiveButton(R.string.done,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText editName, editPhone, editAddress;
                                        String strName, strPhone, strAddress;
                                        editName = (EditText) view.findViewById(R.id.edit_name);
                                        editPhone = (EditText) view.findViewById(R.id.edit_phone);
                                        editAddress =
                                            (EditText) view.findViewById(R.id.edit_address);
                                        strName = editName.getEditableText().toString();
                                        strPhone = editPhone.getEditableText().toString();
                                        strAddress = editAddress.getEditableText().toString();
                                        int realPosition = 0;
                                        if (mRecyclerContact.getAdapter() == mSearchAdapter) {
                                            for (Contact c : mContactList) {
                                                if (c.getId() == mSearchList.get(position)
                                                    .getId()) {
                                                    break;
                                                }
                                                realPosition++;
                                            }
                                            Contact tempContact = mContactList.get(realPosition);
                                            tempContact.setName(strName);
                                            tempContact.setNumber(strPhone);
                                            tempContact.setAddress(strAddress);
                                            mContactAdapter.notifyDataSetChanged();
                                            db.updateContact(tempContact);
                                            tempContact = mSearchList.get(position);
                                            tempContact.setName(strName);
                                            tempContact.setNumber(strPhone);
                                            tempContact.setAddress(strAddress);
                                            mSearchAdapter.notifyDataSetChanged();
                                        } else {
                                            Contact tempContact = mContactList.get(position);
                                            tempContact.setName(strName);
                                            tempContact.setNumber(strPhone);
                                            tempContact.setAddress(strAddress);
                                            mContactAdapter.notifyDataSetChanged();
                                            db.updateContact(tempContact);
                                        }
                                    }
                                })
                            .setNegativeButton(R.string.cancel, null);
                        updateBuilder.show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    public class LoadContractThread extends Thread {
        @Override
        public void run() {
            Log.d(TAG, "LoadContactThread: " + Thread.currentThread());
            getContacts();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mContactAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
