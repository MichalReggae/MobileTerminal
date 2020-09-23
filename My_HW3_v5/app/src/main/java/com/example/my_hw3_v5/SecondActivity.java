package com.example.my_hw3_v5;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity implements MyAdapter.MyViewHolder.OnItemListener {

    RecyclerView recyclerView;
    MyAdapter adapter;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("item");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FloatingActionButton fabHome = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView =(RecyclerView)findViewById(R.id.recView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ItemModel> options =
                new FirebaseRecyclerOptions.Builder<ItemModel>()
                        .setQuery(mRef, ItemModel.class)
                        .build();
        Log.i("Main", String.valueOf(FirebaseDatabase.getInstance().getReference().child("item")));

        adapter = new MyAdapter(options,this);


        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onItemClick(int position) {
        Log.i("Main","Position 1: "+adapter.getItem(position).getName());
        Intent intent = new Intent(this,ModificationActivity.class);
        intent.putExtra("ITEMID",adapter.getItem(position).getItemID());
        startActivity(intent);
        Toast toast= Toast.makeText(this,"Modify: "+position,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onItemLongClick(int position) {
        Log.i("ID",adapter.lastId+"");
        mRef.child("obj"+adapter.getItem(position).getItemID()).removeValue();
        Toast toast= Toast.makeText(this,"Position nr: "+position+" deleted",Toast.LENGTH_SHORT);
        toast.show();
    }
}