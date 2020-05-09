package com.example.myhw_v1.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myhw_v1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();

    private static final int COUNT = 0;

     static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void removeItem(int position){
         String itemId = ITEMS.get(position).id;

         ITEMS.remove(position);
         ITEM_MAP.remove(itemId);

    }

    private static Contact createDummyItem(int position) {
        return new Contact(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Contact implements Parcelable {
        public final String id;
        public final String firstName;
        public final String lastName;
        public final String phoneNumber;
        public final String birthday;
        public final String picPath;
        //public final String picPathTrash;

        public Contact(String id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = "";
            this.birthday="";
            this.picPath = "";
           // this.picPathTrash = "";
        }
        public Contact(String id, String firstName, String lastName,  String phoneNumber, String birthday, String picPath) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.birthday=birthday;
            this.picPath = picPath;
           // this.picPathTrash = picPathTrash;
        }

        protected Contact(Parcel in) {
            id = in.readString();
            firstName = in.readString();
            lastName = in.readString();
            phoneNumber = in.readString();
            birthday = in.readString();
            picPath = in.readString();
        }

        public static final Creator<Contact> CREATOR = new Creator<Contact>() {
            @Override
            public Contact createFromParcel(Parcel in) {
                return new Contact(in);
            }

            @Override
            public Contact[] newArray(int size) {
                return new Contact[size];
            }
        };

        @Override
        public String toString() {
            return firstName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(firstName);
            dest.writeString(lastName);
            dest.writeString(phoneNumber);
            dest.writeString(birthday);
            dest.writeString(picPath);
        }
    }
}
