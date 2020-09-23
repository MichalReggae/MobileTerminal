package com.example.my_hw3_v5;

public class ItemModel {


    private long itemID;
    private String name;
    private long count;

    public ItemModel() {
    }
    public ItemModel(long itemID, String name, long count){
        this.itemID =itemID;
        this.name = name;
        this.count = count;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public long getCount(){
        return count;
    }
    public void setCount(long count){
        this.count = count;
    }
    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }


}
