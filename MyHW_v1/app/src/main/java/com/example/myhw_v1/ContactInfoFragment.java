package com.example.myhw_v1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhw_v1.contacts.ContactListContent;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactInfoFragment extends Fragment {

    public void displayContact(ContactListContent.Contact contact){
        FragmentActivity activity = getActivity();

        TextView contactFullName = activity.findViewById(R.id.contactFullName);
        TextView contactPhoneNumber = activity.findViewById(R.id.contactPhoneNumber);
        TextView contactBirthday = activity.findViewById(R.id.contactBirthday);
        ImageView contactInfoImage = activity.findViewById(R.id.contactInfoImage);
        String fullName = (contact.firstName+" "+contact.lastName);
        contactFullName.setText(fullName);
        String phoneNumber = "Phone number: "+contact.phoneNumber;
        contactPhoneNumber.setText(phoneNumber);
        String birthday = "Birthday: "+ contact.birthday;
        contactBirthday.setText(birthday);


        if(contact.picPath!=null && !contact.picPath.isEmpty()) {
            if (contact.picPath.contains("avatar")){
                Drawable contactDrawable;

                switch(contact.picPath){
                    case "avatar_1":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "avatar_2":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "avatar_3":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "avatar_4":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "avatar_5":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "avatar_6":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "avatar_7":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "avatar_8":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "avatar_9":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "avatar_10":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "avatar_11":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "avatar_12":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "avatar_13":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "avatar_14":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "avatar_15":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    case "avatar_16":
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_16);
                        break;
                    default:
                        contactDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);

                }
                contactInfoImage.setImageDrawable(contactDrawable);

            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();

        if(intent!=null){
            ContactListContent.Contact receivedContact = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedContact != null){
                displayContact(receivedContact);
            }

        }
    }

    public ContactInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }
}
