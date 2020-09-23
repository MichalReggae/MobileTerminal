package com.example.my_hw3_v5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends FirebaseRecyclerAdapter <ItemModel,MyAdapter.MyViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public long lastId;
    private MyViewHolder.OnItemListener mOnItemListener;
    public MyAdapter(@NonNull FirebaseRecyclerOptions<ItemModel> options, MyViewHolder.OnItemListener onItemListener) {
        super(options);
        this.mOnItemListener = onItemListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ItemModel model) {
        holder.itemId.setText(model.getItemID()+"");
        holder.itemName.setText(model.getName());
        holder.itemCount.setText(model.getCount()+"");
        lastId = model.getItemID();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view,mOnItemListener);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView itemId;
        TextView itemName;
        TextView itemCount;
        OnItemListener onItemListener;
        public MyViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            itemId = (TextView)itemView.findViewById(R.id.list_id);
            itemName = (TextView)itemView.findViewById(R.id.list_name);
            itemCount = (TextView)itemView.findViewById(R.id.list_count);

            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onItemListener.onItemLongClick(getAdapterPosition());
            return true;
        }

        public interface OnItemListener{
            void onItemClick(int position);
            void onItemLongClick(int position);
        }

    }
}
