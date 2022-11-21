package org.example;

import java.util.NoSuchElementException;

public class HashTable implements HashTableAble {

    private static final int INITIAL_CAPACITY = 3;

    private Entry[] array = new Entry[INITIAL_CAPACITY];

    private int size = 0;

    public int getSize() {
        return size;
    }

    private static final double LOAD_FACTOR = 0.75;
    private int getElementPosition(int key, int arrayLength)
    {
        return Math.abs(HashFunctions.devideHash(key, arrayLength));
    }
    @Override
    public void add(int key, int value) {
        if (size >= array.length * LOAD_FACTOR) increaseArray();
         boolean added =  add(key, value, array);
         if (added) size++;
    }

    private boolean add(int key, int value, Entry[] dst)
    {
        int position = getElementPosition(key, dst.length);
        Entry existed = dst[position];
        if (existed == null)
        {
            Entry entry = new Entry(key, value, null);
            dst[position] = entry;
            return true;
        }
        else
        {
            while (true)
            {
                if (existed.key == key)
                {
                    existed.value = value;
                    return false;
                }
                if (existed.next == null)
                {
                    existed.next = new Entry(key, value, null);
                    return  true;
                }
                existed = existed.next;
            }
        }
    }

    @Override
    public boolean delete(int key) {
        int position = getElementPosition(key, array.length);
        Entry existed = array[position];
        if (existed != null && existed.key == key)
        {
            array[position] = existed.next;
            size--;
            return true;
        }
        else {
            while (existed != null)
            {
                Entry nextE = existed.next;
                if (nextE == null) return false;
                if (nextE.key == key)
                {
                    existed.next = nextE.next;
                    size--;
                    return true;
                }
                existed = existed.next;
            }
        }
        return false;
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array)
        {
            Entry existed = entry;
            while (existed != null)
            {
                add(existed.key, existed.value, newArray);
                existed = existed.next;
            }
        }
        array = newArray;
    }
    @Override
    public int search(int key) {
        int position = getElementPosition(key, array.length);
        Entry existed = array[position];
        while (existed != null)
        {
             if (existed.key == key)
             {
                 return existed.value;
             }
             existed = existed.next;
        }
        throw new NoSuchElementException("there is no such element");
    }

    public void display()
    {
        System.out.println("Hash table : ");
        for (Entry entry : array)
        {
            if (entry != null)
            System.out.println(entry);
        }
        System.out.println();
    }


    private static class Entry
    {
        private int key;
        private int value;
        private Entry next;

        public Entry(int key, int value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;

        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
