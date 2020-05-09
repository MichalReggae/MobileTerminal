package com.example.myhw_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.myhw_v1.contacts.ContactListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener, DeleteDialog.OnDeleteDialogInteractionListener, DialDialog.OnDialDialogInteractionListener {
    public static final String taskExtra = "taskExtra";
    // static final String NAME = "name";
    private int currentItemPosition= -1;
    private String currentItemName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    FloatingActionButton fab = findViewById(R.id.fab);

    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),AddContactActivity.class));
        }
    });



    }
    private void    displayContactInFragment(ContactListContent.Contact contact){
        ContactInfoFragment contactInfoFragment = ((ContactInfoFragment)getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(contactInfoFragment !=null ){
            contactInfoFragment.displayContact(contact);
        }

    }


    private void startSecondActivity(ContactListContent.Contact contact,int position){
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(taskExtra,contact);
        startActivity(intent);
    }

    @Override
    public void onListFragmentClickInteraction(ContactListContent.Contact contact, int position) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayContactInFragment(contact);

        }else{
            startSecondActivity(contact,position);
        }


    }

    @Override
    public void onListFragmentLongClickInteraction(ContactListContent.Contact contact,int position) {
        currentItemName = contact.firstName;

        showDialDialog();


    }

    @Override
    public void onDeleteFragmentClickInteraction(int position) {
        showDeleteDialog();

        currentItemPosition = position;

    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));

    }
    private void showDialDialog(){
        DialDialog.newInstance(currentItemName).show(getSupportFragmentManager(),getString(R.string.dial_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {


        if(currentItemPosition != -1 && currentItemPosition < ContactListContent.ITEMS.size()){
            ContactListContent.removeItem(currentItemPosition);

            ((ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();

        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {


    }

    @Override
    public void onDialDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialDialogNegativeClick(DialogFragment dialog) {

    }
}

        /*final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });*/







