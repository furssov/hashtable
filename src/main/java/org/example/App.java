package org.example;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HashTable hashTable = new HashTable();
        boolean programWorks = true;
        while (programWorks)
        {
            System.out.println("1.Add");
            System.out.println("2.Delete");
            System.out.println("3.Search");
            System.out.println("4.Print");

            Scanner enter = new Scanner(System.in);
            int point = enter.nextInt();
            switch (point) {
                case 1: {
                    System.out.println("Key : ");
                    int key = enter.nextInt();
                    System.out.println("Value : ");
                    int value = enter.nextInt();
                    hashTable.add(key, value);
                    break;
                }
                case 2:
                {
                    System.out.println("Key : ");
                    int key = enter.nextInt();
                    System.out.println("Has been key deleted? : "+hashTable.delete(key));
                    break;
                }
                case 3:
                {
                    System.out.println("Key : ");
                    int key = enter.nextInt();
                    try {
                        int searched = hashTable.search(key);
                        System.out.println(searched + " has been found");
                    }
                    catch (NoSuchElementException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    finally {
                        break;
                    }
                }
                case 4 :
                {
                    hashTable.display();
                    break;
                }
                default: {
                    programWorks = false;
                    break;
                }

            }
        }
    }
}
