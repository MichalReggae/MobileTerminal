package com.example.my_hw3_v5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    MyAdapter adapter;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference();
    private long lastId;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonList = (Button)findViewById(R.id.btn_list);
        Button buttonAdd = (Button)findViewById(R.id.btn_add);
        Button buttonAdditional = (Button)findViewById(R.id.btn_additional);

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                intent.putExtra("LastID",lastId);

                startActivity(intent);
            }
        });
        buttonAdditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),"modyfikacja i usuwanie na liscie,odpowienio po przez onClick i onLongClick",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Log.i(TAG, "onCreate: First");
        Query query1 = myRef.child("item").orderByChild("itemID").limitToLast(1);

        Log.i(TAG, "onCreate: "+String.valueOf(query1));

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        ItemModel itemmodel = snapshot1.getValue(ItemModel.class);
                        lastId = itemmodel.getItemID();
                        Log.d(TAG, "onDataChange: "+itemmodel.getName()+" "+itemmodel.getItemID()+" "+itemmodel.getCount());
                    }

                }else{
                    Log.d(TAG, "onDataChange: Error");
                }

             }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

}
}