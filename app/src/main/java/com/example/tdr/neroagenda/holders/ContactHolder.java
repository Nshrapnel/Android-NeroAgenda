package com.example.tdr.neroagenda.holders;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tdr.neroagenda.R;
import com.example.tdr.neroagenda.activities.ContactInfo;
import com.example.tdr.neroagenda.adapters.ContactAdapter;
import com.example.tdr.neroagenda.dao.ContentProviderDAO;
import com.example.tdr.neroagenda.models.Contact;

import java.util.ArrayList;

public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private final TextView tvName, tvPhone, tvEmail;
    private Contact contact;
    private ArrayList<Contact> contacts;
    private Context context;
    private ContactAdapter adapter;
    private ContentProviderDAO contentProviderDAO;

    public ContactHolder(Context context, View itemView, ArrayList<Contact> contacts, ContactAdapter adapter) {
        super(itemView);
        this.context = context;
        this.tvName = (TextView) itemView.findViewById(R.id.tvName);
        this.tvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        this.tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
        this.contacts = contacts;
        this.adapter = adapter;
        this.contentProviderDAO = new ContentProviderDAO(this.context);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void bindContact(Contact contact) {
        this.contact = contact;
        this.tvName.setText(contact.getmName());
        this.tvPhone.setText(contact.getmPhone());
        this.tvEmail.setText(contact.getmEmail());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ContactInfo.class);
        intent.putExtra("name", contact.getmName());
        intent.putExtra("number", contact.getmPhone());
        intent.putExtra("email", contact.getmEmail());
        intent.putExtra("id", contact.getId());
        ((Activity) context).startActivityForResult(intent, 1);
        Toast.makeText(context, "Editing " + this.contact.getmName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    @SuppressLint("InflateParams")
    public boolean onLongClick(View v) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
        dialog.setTitle("Delete " + this.contact.getmName() + " from the phonebook?");
        dialog.setView(view);
        dialog.setCancelable(true);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contentProviderDAO.deleteContact(contact);
                contacts.remove(contact);
                Toast.makeText(context, contact.getmName() + " has been deleted.",
                        Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
        return false;
    }
}
