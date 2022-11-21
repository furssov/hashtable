package org.example;

public class HashFunctions {



    public static int devideHash(int key, int size)
    {
        return key % size;
    }

    public static int multipleHash(int key, int size)
    {
        return (int) (size * ((key * 0.618033) % 1));
    }

    public static int additHash(String key, int size)
    {
        long s = 0;
        for (int i = 0; i < key.length(); i++)
        {
            s += key.charAt(i) * Math.pow(2,i);
        }

        return (int) s % size;
    }

}
