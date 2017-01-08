package com.example.tdr.neroagenda.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdr.neroagenda.holders.ContactHolder;
import com.example.tdr.neroagenda.interfaces.OnListFragmentInteractionListener;
import com.example.tdr.neroagenda.models.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

    private final OnListFragmentInteractionListener mListener;
    private Context context;
    private ArrayList<Contact> contacts;
    private int itemResource;

    public ContactAdapter(Context context, int itemResource, ArrayList<Contact> contacts,
                          OnListFragmentInteractionListener listener) {
        this.context = context;
        this.contacts = contacts;
        this.itemResource = itemResource;
        mListener = listener;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new ContactHolder(this.context, view, contacts, this);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = this.contacts.get(position);
        holder.bindContact(contact);
    }

    @Override
    public long getItemId(int arg0) {
        return contacts.get(arg0).getId();
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

}