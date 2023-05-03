// -----------------------------------------------------
// Assignment 4
// Written by: Rania Maoukout 40249281
// ------------------------------------

import java.io.*;

/**
 * BookList class creates BookList objects with Book Nodes and includes a nested class Node to create Nodes
 * @author raniam
 * @version 1.0
 *
 */

public class BookList  {
    private class Node{
        private Book b;
        private Node next;

        public Node(Book b, Node next) {
            this.b=b;
            this.next=next;
        }
    }
    private Node head;

    /**
     * BookList() constructor of booklist
     */
    public BookList()
    {
        this.head=null;
    }

    /**
     * addToStart() add a book node at the start of the list
     * @param b
     */
    public void addToStart(Book b)//works
    {

        head=new Node(b,head);
    }

    /**
     * storeRecordsByYear() stores books by passed year in a file called year.txt
     * @param yr
     */
    public void storeRecordsByYear(int yr) throws FileNotFoundException {
        Node temp=head;
        boolean exist=false;

        while(temp!=null)
        {if(temp.b.getYear()==yr)
            {   exist=true;
                break;
            }
            temp=temp.next;
        }

        if (!exist)
        {System.out.println("No records found with that year");}

        else{
            PrintWriter pw1=new PrintWriter(new FileOutputStream(yr + ".txt"));
            while (temp !=null) {
                if (temp.b.getYear()==yr)
                {pw1.println(temp.b);}
                temp=temp.next;
            }
            pw1.close();
        }
    }

    /**
     * insertBefore() inserts a node before the given node with corresponding isbn
     * @param isbn
     * @param b
     * @return boolean
     */
    public boolean insertBefore(long isbn, Book b) //works
    {
        Node temp=head;
        boolean possible= false;

        while(temp!=null)
        {
            if(temp==head && temp.b.getIsbn()==isbn)
            {
                head=new Node(b,temp);
                break;

            }
            else if(temp.next!=null && temp.next.b.getIsbn()==(isbn))
            {
                temp.next=new Node(b,temp.next);
                possible= true;

                break;
            }

            else if(temp.next==null)
            {
                System.out.println("Book with given ISBN number was not found");
                possible= false;
            }
            temp=temp.next;
        }

        return possible;

    }

    /**
     * insertBetween() inserts a node between 2 nodes with corresponding isbns
     * @param isbn1
     * @param isbn2
     * @param b
     * @return boolean
     */

    public boolean insertBetween(long isbn1, long isbn2, Book b)//works
    {
        Node temp=head;
        boolean possible= false;

        while(temp!=null)
        {

            if(temp.next!=null &&  temp.b.getIsbn()==isbn1  && temp.next.b.getIsbn()==(isbn2))
            {
                temp.next=new Node(b,temp.next);
                possible= true;

                break;
            }

            else if(temp.next==null)
            {
                System.out.println("Books with given ISBN numbers were not found");
                break;

            }
            else

                temp=temp.next;
        }

        return possible;
    }

    /**
     * displayContent() displays content of linked list
     */
    public void displayContent()//works
    {
        Node temp = head;
        if (temp == null)
            System.out.println("\nThere is nothing to display; list is empty." );
        else

            while(temp != null)
            {
                System.out.println(temp.b + " ==> ");
                temp = temp.next;
            }
        System.out.println("==> head");			}

    /**
     * delConsecutiveRepeatedRecords() deletes consecutive repeated records
     */
    public boolean delConsecutiveRepeatedRecords()//works
    {
        Node temp=head;
        boolean check=false;

        while(temp!=null)
        {
            while(temp.next!=null && temp.b.equals(temp.next.b))
            {
                temp.next=temp.next.next;
                check=true;
            }

            if(temp.next==null && temp.b.equals(head.b))
            {
                head=head.next;
            }

            temp=temp.next;

        }
        return check;
    }

    /**
     * extractAuthList() returns a booklist of books with only the passed author
     * @param aut
     * @return BookList
     */
    public BookList extractAuthList(String aut) //works
    {   Node temp=head;
        BookList l = new BookList();

        while(temp!=null)
        {
            if(temp.b.getAuthor().equals(aut))
            {

                l.addToStart(temp.b);
            }
            temp=temp.next;
        }

        return l;
    }

    /**
     * swap() swaps 2 nodes with corresponding isbns
     * @param isbn1
     * @param isbn2
     * @return boolean
     */
    public boolean swap(long isbn1, long isbn2) { //check if u have time for head
        Node temp1=null, temp2=null; Node pointer=head;
        Book temp; boolean check=false;

        while (pointer!= null) {
            if (pointer.b.getIsbn()==isbn1)
            {temp1 = pointer;}
            else if (pointer.b.getIsbn()==isbn2)
            {temp2 = pointer;}
            pointer = pointer.next;
            if (temp1 != null && temp2 != null)
            {break;}
        }
        if (temp1==null && temp2==null)
        {System.out.println("both books do not exist");
            return check;
        }
        temp=temp1.b;
        temp1.b=temp2.b;
        temp2.b=temp;
        return true;
    }

    /**
     * commit() stores the contents of the list in a file called Update_Books.txt which gets overwritten every time its called.
     */
    public void commit() throws IOException {
        Node temp=head;
        FileWriter w=new FileWriter("Update_Books.txt");

        try {
            while(temp!=null)
            {
                {
                    w.write(temp.b.toString() + "\n");
                }
                temp=temp.next;
            }
            w.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
