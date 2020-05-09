package com.example.myhw_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhw_v1.contacts.ContactListContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AddContactActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }



    public static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try{
            int i = Integer.parseInt(strNum);
        } catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static boolean checkDate(String date){
        SimpleDateFormat sdfmt = new SimpleDateFormat("dd/mm/yyyy");
        sdfmt.setLenient(false);

        try {
            Date javaDate = sdfmt.parse(date);
        }catch(ParseException e){
            return false;
        }
        return true;

    }
    public void addContact(View view) {
        EditText firstNameEditTxt = findViewById(R.id.firstName);
        EditText lastNameEditTxt = findViewById(R.id.lastName);
        EditText birthdayEditTxt = findViewById(R.id.birthday);
        EditText phoneNumberEditTxt = findViewById(R.id.phoneNumber);

        String firstName = firstNameEditTxt.getText().toString();
        String lasName = lastNameEditTxt.getText().toString();
        String birthday= birthdayEditTxt.getText().toString();
        String phoneNumber = phoneNumberEditTxt.getText().toString();

        String avatar;
        int number;
        Random rnd = new Random();

        number = (rnd.nextInt(15)+1);
        avatar = "avatar_"+Integer.toString(number);


        //((ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();


       // startActivity(new Intent(getApplicationContext(),MainActivity.class));
        if(firstName.isEmpty() || lasName.isEmpty() || birthday.isEmpty() || phoneNumber.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Empty data", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
            toast.show();

        }else {
            if(isNumeric(phoneNumber)){
                if(phoneNumber.length()>9){
                    Toast toast = Toast.makeText(getApplicationContext(),"Number too long", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
                    toast.show();
                    phoneNumberEditTxt.setText("");
                }else if(phoneNumber.length()<9){
                    Toast toast = Toast.makeText(getApplicationContext(),"Number too short", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
                    toast.show();
                    phoneNumberEditTxt.setText("");
                }else{
                    if(checkDate(birthday)){
                        ContactListContent.addItem(new ContactListContent.Contact("Contact."+ContactListContent.ITEMS.size()+1,firstName,lasName,phoneNumber,birthday,avatar));
                       // ((ContactFragment) getSupportFragmentManager().findFragmentById(R.id.contactFragment)).notifyDataChange();
                        firstNameEditTxt.setText("");
                        lastNameEditTxt.setText("");
                        birthdayEditTxt.setText("");
                        phoneNumberEditTxt.setText("");

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Wrong date format", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
                        toast.show();
                        birthdayEditTxt.setText("");
                    }

                }


            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Wrong number", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
                toast.show();
                phoneNumberEditTxt.setText("");
            }



        }


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);


    }

    @Override
    public void onListFragmentClickInteraction(ContactListContent.Contact contact, int position) {

    }

    @Override
    public void onListFragmentLongClickInteraction(ContactListContent.Contact contact, int position) {

    }


    @Override
    public void onDeleteFragmentClickInteraction(int position) {

    }
}
