package com.nothing.android_week11_permission_load_image;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_READ_STORAGE = 1;
    private static final int REQUEST_READ_CONTACT = 2;
    private List<String> mPathList;
    private List<Contact> mContactList;
    private RecyclerView mRecyclerView;
    private ImageAdapter mImageAdapter;
    private ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_load);
        mPathList = new ArrayList<>();
        mContactList = new ArrayList<>();
        mImageAdapter = new ImageAdapter(mPathList);
        mContactAdapter = new ContactAdapter(mContactList);
        findViewById(R.id.button_show_image).setOnClickListener(this);
        findViewById(R.id.button_show_contact).setOnClickListener(this);
    }

    public List<String> getImagePath() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projection[] = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        List<String> path = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                path.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media
                    .DATA)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return path;
    }

    public List<Contact> getContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        List<Contact> contacts = new ArrayList<>();
        String colName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String colNumber = ContactsContract.CommonDataKinds.Phone.NUMBER;
        String projection[] = {colName, colNumber};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                contacts.add(new Contact(name, phone));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return contacts;
    }

    public void showImages() {
        mPathList.clear();
        mPathList.addAll(getImagePath());
        mImageAdapter.notifyDataSetChanged();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mImageAdapter);
    }

    private void showContacts() {
        mContactList.clear();
        mContactList.addAll(getContacts());
        mContactAdapter.notifyDataSetChanged();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mContactAdapter);
    }

    public void requestImagePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, "Manifest" +
            ".permission.READ_EXTERNAL_STORAGE");
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            showImages();
        } else if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest
                .permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Request Image Permission")
                    .setMessage("We want to access to your external storage to get image")
                    .setPositiveButton(R.string.yes_option, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat
                                .requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission
                                        .READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
                        }
                    })
                    .setNegativeButton(R.string.no_option, null);
                builder.create().show();
            } else {
                ActivityCompat
                    .requestPermissions(MainActivity.this, new String[]{Manifest.permission
                        .READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
            }
        }
    }

    public void requestContactPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, "Manifest.permission" +
            ".READ_CONTACTS");
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            showContacts();
        } else if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest
                .permission.READ_CONTACTS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Request Contact Permission")
                    .setMessage("We want to access to your contacts data")
                    .setPositiveButton(R.string.yes_option, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat
                                .requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission
                                        .READ_CONTACTS}, REQUEST_READ_CONTACT);
                        }
                    })
                    .setNegativeButton(R.string.no_option, null);
                builder.create().show();
            } else {
                ActivityCompat
                    .requestPermissions(MainActivity.this, new String[]{Manifest.permission
                        .READ_CONTACTS}, REQUEST_READ_CONTACT);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_image:
                requestImagePermission();
                break;
            case R.id.button_show_contact:
                requestContactPermission();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showImages();
                } else {
                    Toast.makeText(this, R.string.denied, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_READ_CONTACT:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showImages();
                } else {
                    Toast.makeText(this, R.string.denied, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
