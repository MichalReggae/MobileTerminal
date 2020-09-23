package com.example.my_hw3_v5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModificationActivity extends AppCompatActivity {

    private static final String TAG = "ModificationActivity";
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference();
    private long itemId;
    private ItemModel itemModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);
        Button modify =(Button)findViewById(R.id.modify_btn);
        final EditText nameItem = (EditText)findViewById(R.id.editName);
        final EditText countItem = (EditText)findViewById(R.id.editCount);


        itemId = getIntent().getLongExtra("ITEMID",0);
        Log.i(TAG, "onCreate: "+String.valueOf(itemId));
        Query query1 = myRef.child("item").orderByChild("itemID").equalTo(itemId);

        Log.i(TAG, "onCreate: "+String.valueOf(query1));

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                       itemModel = snapshot1.getValue(ItemModel.class);
                        Log.d(TAG, "onDataChange: "+itemModel.getName()+" "+itemModel.getItemID()+" "+itemModel.getCount());
                        nameItem.setText(itemModel.getName());
                        countItem.setText(itemModel.getCount()+"");
                    }

                }else{
                    Log.d(TAG, "onDataChange: Error");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        //Log.d(TAG, "onDataChange: "+itemModel.getName()+" "+itemModel.getItemID()+" "+itemModel.getCount());
       /*
        //Set value to fields
        if(itemModel.getName() !=null){
        nameItem.setText(itemModel.getName());
        countItem.setText(itemModel.getCount()+"");}
*/
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameItem.getText().toString();
                String counts = countItem.getText().toString();
                Long count = Long.parseLong(counts);
                ItemModel modifiedItem = new ItemModel(itemModel.getItemID(),name,count);
                myRef.child("item").child("obj"+itemModel.getItemID()).setValue(modifiedItem);
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}