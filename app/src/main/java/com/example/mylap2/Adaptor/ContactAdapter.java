package com.example.mylap2.Adaptor;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylap2.Model.ContactModel;
import com.example.mylap2.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private ArrayList<ContactModel> data;
    private ArrayList<ContactModel> databackup;
    private Activity context;
    private LayoutInflater inflater;

    public ContactAdapter(ArrayList<ContactModel> data, Activity context) {
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public ContactAdapter(ArrayList<ContactModel> data,ArrayList<ContactModel> databackup, Activity context, LayoutInflater inflater) {
        this.context = context;
        this.data = data;
        this.inflater = inflater;
        this.databackup = databackup;
    }
    public ContactAdapter() {
    }

    public ArrayList<ContactModel> getData() {
        return data;
    }


    public void setData(ArrayList<ContactModel> data) {
        this.data = data;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.contact_item, null);
            ImageView imageProfile = v.findViewById(R.id.imageView);
            TextView tvName = v.findViewById(R.id.textView3);
            TextView tvPhone = v.findViewById(R.id.textView4)   ;

            tvName.setText(data.get(position).getName());
            tvPhone.setText(data.get(position).getNumber());
            imageProfile.setImageURI(Uri.parse(data.get(position).getImage()));

        }
        return v;
    }
}
