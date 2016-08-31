package com.db.example.activityes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.db.example.R;
import com.db.example.db.DatabaseManager;
import com.db.example.modal.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager manager = DatabaseManager.newInstance(this);

        Contact contact = new Contact();
        contact.setContactAddress("Katm");
        contact.setContactId("9841225352");
        contact.setContactName("Krishna");
        contact.setGender("Male");
        manager.addContact(contact);
        Log.e("------", "-----------");
        Contact contact1 = new Contact();
        contact1.setContactAddress("Nepal");
        contact1.setContactId("984122");
        contact1.setContactName("Test");
        contact1.setGender("Male");
        manager.addContact(contact1);
        manager.fetchContact();
        Log.e("Updatee", "-----------");
        manager.upateContact("9841225352", "Krishna Stha");
        Log.e("After update", "-----------");
        manager.fetchContact();
        Log.e("after delete", "-----------");
        manager.deleteContact("9841225352");
        Log.e("------", "-----------");
        manager.fetchContact();

    }
}
