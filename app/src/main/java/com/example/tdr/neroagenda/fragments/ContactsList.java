package com.example.tdr.neroagenda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdr.neroagenda.R;
import com.example.tdr.neroagenda.adapters.ContactAdapter;
import com.example.tdr.neroagenda.decorators.ShadowVerticalSpaceItemDecorator;
import com.example.tdr.neroagenda.decorators.VerticalSpaceItemDecorator;
import com.example.tdr.neroagenda.interfaces.OnListFragmentInteractionListener;
import com.example.tdr.neroagenda.models.Contact;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContactsList extends Fragment {

    private static final String CONTACTS = "contacts";
    // TODO: Customize parameters
    private ArrayList<Contact> contacts;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactsList() {
    }

    public static ContactsList newInstance(ArrayList<Contact> contacts) {
        ContactsList fragment = new ContactsList();
        Bundle args = new Bundle();
        args.putParcelableArrayList(CONTACTS, contacts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contacts = getArguments().getParcelableArrayList(CONTACTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.content_main, container, false);
        Context context = view.getContext();
        VerticalSpaceItemDecorator itemDecorator = new VerticalSpaceItemDecorator(20);
        ShadowVerticalSpaceItemDecorator shadowItemDecorator = new ShadowVerticalSpaceItemDecorator(getContext(), R.drawable.drop_shadow);
        view.setLayoutManager(new LinearLayoutManager(context));
        view.addItemDecoration(itemDecorator);
        view.addItemDecoration(shadowItemDecorator);
        view.setAdapter(new ContactAdapter(getContext(), R.layout.phonebook_row, contacts, mListener));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
