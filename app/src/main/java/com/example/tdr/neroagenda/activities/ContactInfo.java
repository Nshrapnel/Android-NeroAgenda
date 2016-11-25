package com.example.tdr.neroagenda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tdr.neroagenda.R;


public class ContactInfo extends AppCompatActivity {
    private EditText etName, etNumber, etEmail;
    private long id;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.contact_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String name = extra.getString("name"),
                    number = extra.getString("number"),
                    email = extra.getString("email");
            id = extra.getLong("id");
            etName.setText(name);
            etNumber.setText(number);
            etEmail.setText(email);
        }
    }

    public void onClick(View view) {
        Intent data = new Intent();
        switch (view.getId()) {
            case R.id.createButton:
                data.putExtra("name", etName.getText().toString());
                data.putExtra("number", etNumber.getText().toString());
                data.putExtra("email", etEmail.getText().toString());
                data.putExtra("id", id);
                setResult(RESULT_OK, data);
                break;
            case R.id.cancelButton:
                Toast.makeText(this, "Creation canceled", Toast.LENGTH_SHORT).show();
                break;
        }
        finish();
    }
}
