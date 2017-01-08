package com.example.tdr.neroagenda.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tdr.neroagenda.R;
import com.example.tdr.neroagenda.models.Contact;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactInfo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CONTACT = "contact";

    // TODO: Rename and change types of parameters

    private Contact contact;
    private EditText etName, etNumber, etEmail;
    private OnFragmentInteractionListener mListener;

    public ContactInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactInfo newInstance(Contact contact) {
        ContactInfo fragment = new ContactInfo();
        Bundle args = new Bundle();
        args.putParcelable(CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contact = getArguments().getParcelable(CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact_info, container, false);
        etName = (EditText) view.findViewById(R.id.etName);
        etNumber = (EditText) view.findViewById(R.id.etPhone);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        if (contact != null) {
            etName.setText(contact.getmName());
            etNumber.setText(contact.getmPhone());
            etEmail.setText(contact.getmEmail());
        }
        return view;
    }

    public Contact onButtonPressed(View view) {
        switch (view.getId()) {
            case R.id.createButton:
                contact.setmName(etName.getText().toString());
                contact.setmPhone(etNumber.getText().toString());
                contact.setmEmail(etEmail.getText().toString());
                break;
            case R.id.cancelButton:
                Toast.makeText(getActivity(), "Operation canceled", Toast.LENGTH_SHORT).show();
                break;
        }
        return contact;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
