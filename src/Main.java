// -----------------------------------------------------
// Assignment 4
// Written by: Rania Maoukout 40249281
// -----------------------------------------------------

import java.io.*;
// -----------------------------------------------------
// Assignment 4
// Written by: Rania Maoukout 40249281
// -----------------------------------------------------

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Main class creates BookList and ArrayList, creates an array of books from the Books.txt file,
 * runs the different functions for the booklist such as adding the books to the booklist, creates the year error text if the arraylist is not empty
 * @author raniam
 * @version 1.0
 *
 */
public class Main {
    public static void main(String[] args) {
        Book[] Books = new Book[23];
        String[] Attributes = null;
        int nbline = 0;
        BufferedReader br = null;
        Scanner key = new Scanner(System.in);
        int option = 0;
        ArrayList<Book> arrLst = new ArrayList<Book>();
        BookList bkLst = new BookList();


        try {
            br = new BufferedReader(new FileReader("Books.txt"));
            String s = br.readLine();
            while (s != null && nbline != 23) {
                Attributes = s.split(",");
                Books[nbline] = new Book(Attributes[0], Attributes[1], Double.valueOf(Attributes[2]), Long.parseLong(Attributes[3]), Attributes[4], Integer.parseInt(Attributes[5]));
                nbline++;
                s = br.readLine();
            }
            br.close();

            //arrayList of badrecords, goodrecords linked list
            for (int i = Books.length - 1; i >= 0; i--) {
                if (Books[i].getYear() >= 2024) {
                    arrLst.add(Books[i]);
                } else {
                    bkLst.addToStart(Books[i]);
                }
            }
            //arrayList content written correctly to YearErr.txt
            if (!arrLst.isEmpty()) {
                PrintWriter pw = new PrintWriter(new FileOutputStream("YearErr.txt"));
                for (Book YearErr_records : arrLst)
                    pw.println(YearErr_records);
                pw.close();
            }


            boolean check = false;
            while (!check) {
                System.out.println("\n---------------------------------------------------MENU--------------------------------------------------------");

                System.out.println(" 1) Give me a year # and I would extract all records of that year and store them in a file for that year;\n" +
                        " 2) Ask me to delete all consecutive repeated records;\n" +
                        " 3) Give me an author name and I will create a new list with the records of this author and display them;\n" +
                        " 4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n" +
                        " 5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\n" +
                        " 6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\n" +
                        " 7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n" +
                        " 8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");

                System.out.println("Please enter an integer to choose an option from the above menu: ");
                option = key.nextInt();

                switch (option) {
                    case 1: {
                        System.out.println("Enter a year #");
                        int year = key.nextInt();
                        bkLst.storeRecordsByYear(year); //smh not running
                        bkLst.displayContent();
                    }
                    break;
                    case 2: {
                        bkLst.delConsecutiveRepeatedRecords();
                        bkLst.displayContent();
                    }
                    break;

                    case 3: {
                        System.out.println("Enter an author's name:");
                        String aut = key.nextLine();
                        bkLst.extractAuthList(aut).displayContent();
                        bkLst.displayContent();
                    }
                    break;
                    case 4: {
                        System.out.println("Enter an ISBN number and a Book object:");
                        Long ISBN = key.nextLong();
                        String attributes = (key.nextLine());
                        Book b = Bookbuilt(attributes);
                        bkLst.insertBefore(ISBN, b);
                        bkLst.displayContent();
                        //1574670913 "The Dark Road", "Jimin S.", 25.92, 1239009879, "FCN",2019
                    }
                    break;
                    case 5: {
                        System.out.println("Enter two ISBN numbers and a Book object:");
                        Long ISBN1 = key.nextLong();
                        Long ISBN2 = key.nextLong();
                        String attributes = (key.nextLine());
                        Book b = Bookbuilt(attributes);
                        bkLst.insertBetween(ISBN1, ISBN2, b);
                        bkLst.displayContent();
                        // 879101490 1879103272   "The Dark Road", "Jimin S.", 25.92, 1239009879, "FCN",2019
                    }
                    break;
                    case 6: {

                        System.out.println("Enter two ISBN numbers");
                        Long ISBN1 = key.nextLong();
                        Long ISBN2 = key.nextLong();
                        ;
                        bkLst.swap(ISBN1, ISBN2);
                        bkLst.displayContent();
                        //1574670913 1879103272
                    }
                    break;
                    case 7: {
                        bkLst.commit();
                    }
                    break;
                    case 8: {
                        System.out.println("Program ended");
                        check = true;
                    }
                    break;
                    default:
                        System.out.println("Please enter a positive integer between 1-8 inclusive");

                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Problem reading file ");
        }
    }

    /**
     * Bookbuilt() creates a book object with passed string attributes
     * @param attributes
     * @return Book
     */
    static Book Bookbuilt(String attributes) {
        //String Attributes = (attributes.trim());
        String [] Attributes1 = attributes.split(",");
        for (int i = 0; i < 2; i++) {
            Attributes1[i] = Attributes1[i].substring(1);
        }


        double price = Double.valueOf(Attributes1[2].replaceAll(" ", ""));
        Long isbn1 = Long.parseLong(Attributes1[3].replaceAll(" ", ""));
        Attributes1[4] = Attributes1[4].substring(1);
        int year = Integer.parseInt(Attributes1[5].replaceAll(" ", ""));
        Book b = new Book(Attributes1[0], Attributes1[1], price, isbn1, Attributes1[4], year);
        return b;
    }
}
