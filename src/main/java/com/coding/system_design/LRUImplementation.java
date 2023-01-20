package com.coding.system_design;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUImplementation {
    //HashMap to store cache data
    HashMap<Integer,String> data = new HashMap<>();
    //LinkedList to store order of the cache access
    LinkedList<Integer> order = new LinkedList<>();
    //size of the cache
    int capacity;

    LRUImplementation(int capacity){
        this.capacity=capacity;
    }

    //put - for adding new cache data
    public void put(int key,String val){

        //check if the cache is full by comparing size of the list with capacity
        if(order.size()>=capacity){
            //if cache is full remove the last element from order list  and also from data, so we will get room
            //for new cache data
           int keyRemoved= order.removeLast(); //remove from linked-list
           data.remove(keyRemoved); //removing from hashmap
        }
        //add new cache data to the top of the linked-list and also to the hashmap
        order.addFirst(key); //adding to the top of linked-list
        data.put(key,val);//adding to the hashmap
    }

    //get -
    String get(int key){
        //get the value from map using key
        String res=data.get(key);

        if(res!=null){
            //if the data is present then we need to update the access order
            //move the current key to the top of the linked-list.To move it to top first remove it and re-add it to top
            order.remove((Object)key);
            order.addFirst(key);
        }
        //if value is not present in the cache then it is cache miss
        else{
            res=null;

        }
        return res;
    }

    //display method
    public void display(){
        for(int i =0;i<order.size();i++){
            int key =order.get(i);
            System.out.println(key +"->"+data.get(key));
        }
    }

    //driver code
    public static void main(String[] args) {
        LRUImplementation LRUObj= new LRUImplementation(3);
        LRUObj.put(1,"A");
        LRUObj.put(2,"B");
        LRUObj.put(3,"C");
        LRUObj.display();
        LRUObj.put(10,"J");
        LRUObj.display();
        LRUObj.get(2);
        System.out.println("After fetching 2");
        LRUObj.display();
        LRUObj.get(10);
        System.out.println("After fetching 10");
        LRUObj.display();
    }
}
