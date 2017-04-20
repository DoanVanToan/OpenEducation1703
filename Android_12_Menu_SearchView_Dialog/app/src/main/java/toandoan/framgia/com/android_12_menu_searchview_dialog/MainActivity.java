package toandoan.framgia.com.android_12_menu_searchview_dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.local.ContactDataSource;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    Xây dựng 1 app hiển thị Contact gồm
    Tên, Sdt, Dia chi

    Các tính năng
    + Thêm
    + Xóa
    + Sửa (Homework)
    sử dụng option menu, popupmenu, dialog
     */

    private RecyclerView mRecyclerContact;
    private ContactAdapter mAdapter;
    private List<Contact> mContacts;
    private Button mButtonContextMenu;
    private ContactDataSource mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.contact);
        mContacts = new ArrayList<>();
        mDatabase = new ContactDataSource(this);
        mContacts = mDatabase.getAllContacts();
        mAdapter = new ContactAdapter(mContacts);
        mRecyclerContact = (RecyclerView) findViewById(R.id.recycler_contact);

        mRecyclerContact.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerContact.setAdapter(mAdapter);

        mButtonContextMenu = (Button) findViewById(R.id.button_context_menu);
        //        registerForContextMenu(mButtonContextMenu);

        mButtonContextMenu.setOnClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contact_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Hàm khởi tạo menu
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        MenuItem menuSearch = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "submit", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Hàm control sự kiện của menu
        switch (item.getItemId()) {
            case R.id.menu_add:
                // random a number and add to contact
                Random random = new Random();
                int randomNumber = random.nextInt(100);
                Contact contact =
                        new Contact(1, "Contact " + randomNumber, "01234 56 " + randomNumber,
                                "HN +" + randomNumber);
                long rowID = mDatabase.insertContact(contact);
                if (rowID > 0) {
                    // insert successfull
                    contact.setId((int) rowID);
                    mContacts.add(contact);
                    mAdapter.notifyItemInserted(mContacts.size() - 1);
                } else {
                    // insert failed
                    Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.contact_context_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(MainActivity.this, "menu dissmiss", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
}
