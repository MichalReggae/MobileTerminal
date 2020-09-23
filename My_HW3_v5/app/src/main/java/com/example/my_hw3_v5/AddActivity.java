package com.example.my_hw3_v5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    private static String TAG = "AddActivity";
    DatabaseReference myRef;
    private long lastId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myRef = FirebaseDatabase.getInstance().getReference().child("item");
        //Button addButton = (Button) findViewById(R.id.buttonAdd);

        lastId = getIntent().getLongExtra("LastID",0);

        Log.d(TAG, "onCreate: "+lastId);

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

    public void addItem(View v){
        EditText addName= (EditText)findViewById(R.id.addName);
        EditText addCount= (EditText)findViewById(R.id.addCount);
        String name = addName.getText().toString();
        String count = addCount.getText().toString();
        DataSnapshot snapshot = null;
        String example = myRef.getKey();



        if(name.isEmpty() || count.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Empty data", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
            toast.show();
        }else{
            if(isNumeric(count)){
                //add item to database
                long testCount = Long.parseLong(count);
                ItemModel test = new ItemModel((lastId+1),name,testCount);
                myRef.child("obj"+(lastId+1)).setValue(test);
                //clear
                addName.setText("");
                addCount.setText("");
                startActivity(new Intent(this,MainActivity.class));

            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Wrong number", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,0,0);
                toast.show();
                addCount.setText("");

            }

        }

    }




}