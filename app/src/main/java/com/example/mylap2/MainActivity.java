package com.example.mylap2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mylap2.Adaptor.ContactAdapter;
import com.example.mylap2.Model.ContactModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContactAdapter adapter;
    private ArrayList<ContactModel> contactList;
    private FloatingActionButton btnAdd;
    protected int currentId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu,menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contactmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected (@NonNull MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        currentId = info.position;
        if(item.getItemId()==R.id.mnuEdit){
            Intent intent = new Intent (MainActivity.this, SubActivity.class);
            ContactModel c = (ContactModel)listView.getItemAtPosition(currentId);
            intent.putExtra("Id",c.getId());
            intent.putExtra("Name",c.getName());
            intent.putExtra("Phone",c.getNumber());
            intent.putExtra("Uri",c.getImage());
            Log.d("UriDebug", "Uri: " + c.getImage());
            startActivityForResult(intent,3000);
        }
        else if(item.getItemId()==R.id.mnuDelete){
            Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.mnuCall){
            Toast.makeText(this,"Call",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.mnuSms){
            Toast.makeText(this,"Sms",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 150) {
            String id = data.getStringExtra("id");
            String fullname = data.getStringExtra("fullname");
            String phone = data.getStringExtra("phone");
            String uri = data.getStringExtra("uri");
            Log.d("UriDebug", "Uri: " + uri);
            contactList.add(new ContactModel(Integer.parseInt(id), fullname, phone, uri));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        contactList = new ArrayList<ContactModel>();
        contactList.add(new ContactModel(1,"Duong", "123456789","img"));
        contactList.add(new ContactModel(2,"Duong", "123456789","img"));
        contactList.add(new ContactModel(3,"Duong", "123456789","img"));

        adapter = new ContactAdapter(contactList, this);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast .makeText(MainActivity.this, "Add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }
}