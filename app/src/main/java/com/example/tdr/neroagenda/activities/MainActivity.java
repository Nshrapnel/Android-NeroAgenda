package com.example.tdr.neroagenda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tdr.neroagenda.R;
import com.example.tdr.neroagenda.adapters.ContactAdapter;
import com.example.tdr.neroagenda.dao.ContentProviderDAO;
import com.example.tdr.neroagenda.fragments.ContactsList;
import com.example.tdr.neroagenda.interfaces.OnListFragmentInteractionListener;
import com.example.tdr.neroagenda.models.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private ArrayList<Contact> contacts;
    private ContactAdapter adapter;
    private ContentProviderDAO contentProviderDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentProviderDAO = new ContentProviderDAO(this);
        contacts = contentProviderDAO.retrieveContacts();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fragment = ContactsList.newInstance(contacts);
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.content_main, fragment);
        FT.commit();
//        adapter = new ContactAdapter(this, R.layout.phonebook_row, contacts, this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        int verticalSpacing = 20;
//        VerticalSpaceItemDecorator itemDecorator =
//                new VerticalSpaceItemDecorator(verticalSpacing);
//        ShadowVerticalSpaceItemDecorator shadowItemDecorator =
//                new ShadowVerticalSpaceItemDecorator(this, R.drawable.drop_shadow);
//        RecyclerView phonebook = (RecyclerView) findViewById(R.id.listPhone);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ContactInfo.class);
//                startActivityForResult(intent, 0);
//            }
//        });

//        phonebook.setHasFixedSize(true);
//        phonebook.setLayoutManager(layoutManager);
//        phonebook.addItemDecoration(shadowItemDecorator);
//        phonebook.addItemDecoration(itemDecorator);
//        phonebook.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Contact contact = new Contact(data.getExtras().getString("name"),
                        data.getExtras().getString("number"),
                        data.getExtras().getString("email"));
                contact.setId(contentProviderDAO.addContact(contact));
                contacts.add(contact);
            }
        } else {
            if (resultCode == RESULT_OK) {
                Contact contact = new Contact(data.getExtras().getString("name"),
                        data.getExtras().getString("number"),
                        data.getExtras().getString("email"),
                        data.getExtras().getLong("id"));
                for (Contact c : contacts) {
                    if (c.getId() == contact.getId()) {
                        int i = contacts.indexOf(c);
                        contacts.remove(i);
                        contacts.add(i, contact);
                    }
                }
                contentProviderDAO.updateContact(contact);
                Toast.makeText(this, "Changes have been saved",
                        Toast.LENGTH_SHORT).show();
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.create_bulk_contact:
                for (int i = 0; i < 10; i++) {
                    Contact contact = new Contact("Contact " + (i + 1),
                            "555-000-00" + i,
                            "av" + (i + 1) + "@gmail.com");
                    contact.setId(contentProviderDAO.addContact(contact));
                    contacts.add(contact);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.export:
                if (isExternalStorageWritable()) {
                    String export_file_content = "";
                    for (File file : getFilesDir().listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.endsWith(".json");
                        }
                    })) {
                        StringBuilder text = new StringBuilder();
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = br.readLine()) != null) {
                                text.append(line);
                            }
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        export_file_content += text + "\n";
                    }
                    try {
                        String filename = "NeroAgenda - Contacts.json";
                        File file = new File(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOCUMENTS), filename);
                        FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(export_file_content.getBytes());
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(this, contacts.size() + " Contacts has been Exported",
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @Override
    public void onListFragmentInteraction(Contact contact) {

    }
}
