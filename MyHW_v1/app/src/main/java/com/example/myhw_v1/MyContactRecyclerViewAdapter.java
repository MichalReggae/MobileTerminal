package com.example.myhw_v1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhw_v1.ContactFragment.OnListFragmentInteractionListener;
import com.example.myhw_v1.contacts.ContactListContent.Contact;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListFragmentInteractionListener mListener;



    public MyContactRecyclerViewAdapter(List<Contact> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Contact contact = mValues.get(position);
        holder.mItem = contact;

        holder.mContentView.setText(contact.firstName);
        final String picPath = contact.picPath;

        ImageButton deleteButton = holder.mView.findViewById(R.id.item_image_trash);


        Context context = holder.mView.getContext();

        if(picPath!=null && !picPath.isEmpty()) {
            if (picPath.contains("avatar")){
                Drawable contactDrawable;

                switch(picPath){
                    case "avatar_1":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "avatar_2":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "avatar_3":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "avatar_4":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "avatar_5":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "avatar_6":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "avatar_7":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "avatar_8":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "avatar_9":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "avatar_10":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "avatar_11":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "avatar_12":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "avatar_13":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "avatar_14":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "avatar_15":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    case "avatar_16":
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                        break;
                    default:
                        contactDrawable = context.getResources().getDrawable(R.drawable.avatar_1);

                }
                holder.mItemImageView.setImageDrawable(contactDrawable);

            }
        }else {
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_1));

        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener){
                    mListener.onListFragmentClickInteraction(holder.mItem,position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(holder.mItem,position);
                return false;
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteFragmentClickInteraction(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mItemImageView = (ImageView) view.findViewById(R.id.item_image);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
