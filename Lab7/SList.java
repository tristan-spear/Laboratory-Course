//--------------------------------------------------------------------
//
//  Laboratory 7                                         SList.jshl
//
//  Class definitions for the singly linked list implementation of 
//  the List ADT
//
//  The student is to complete all missing or incomplete method 
//     implementations for each class (Slist and SListNode)
//
//--------------------------------------------------------------------

import javax.swing.text.Style;

class SList implements List
{
    // Data members
    protected SListNode head,     // Reference to the beginning of the list
        cursor; // Reference to current cursor position
    
    // Constructors & Helper Method
    SList (  )                  // Default constructor: Creates an empty list
    {
        setup();
    }

    SList ( int size )
        // Creates an empty list. The argument is included for compatibility
        // with the array implementation; size is ignored.
    {
        setup();
    }

    // Class Methods    
    private void setup( )   // Called by constructors only: Creates an empty list
    {
        head = null;
        cursor = head;
    }


    // ----- Insert method definitions for the interface List here ------ //

    public void insert(Object newElement)
    // Inserts newElement after the cursor. If the list is empty, then
    // newElement is inserted as the first (and only) element in the list.
    // In either case, moves the cursor to newElement.
    {
        if(!isEmpty())
        {
            // if inserting at end of list
            if (cursor.getNext() == null)
                cursor = cursor.setNext(new SListNode(newElement, null));
            else
                cursor = cursor.setNext(new SListNode(newElement, cursor.getNext()));
        }
        else // if list is empty
            head = cursor = new SListNode(newElement, null);
    }

    public void remove( )
    // Removes the element marked by the cursor from a list. Moves the
    // cursor to the next element in the list. Assumes that the first list
    // element "follows" the last list element.
    {
        if(!isEmpty())
        {
            if (cursor == head) // remove from front of list
                head = cursor = head.getNext();

            else if(cursor.getNext() != null) // remove from middle of list
            {
                cursor.setElement(cursor.getNext().getElement());
                cursor.setNext(cursor.getNext().getNext());
            }
            else // remove from end of list
            {
                gotoBeginning();

                // iterate to 2nd-to-last element
                while(cursor.getNext().getNext() != null)
                    gotoNext();

                cursor.setNext(null);
                gotoBeginning();
            }

//                gotoPrior();
//                cursor.setNext(cursor.getNext().getNext());
//
//                if (cursor.getNext() != null)
//                    cursor = cursor.getNext();
//                else
//                    cursor = head;

        }
    }

    public void replace (Object newElement)
    // Replaces the element marked by the cursor with newElement and
    // leaves the cursor at newElement.
    {
        cursor.setElement(newElement);
    }

    public void clear( )
    // Removes all elements in a list
    {
        setup();
    }

    public boolean isEmpty( )
    // Returns true if list is empty, else returns false
    {
        return head == null;
    }

    public boolean isFull( )
    // Returns true if list is full, else returns false
    // Always returns false because Java automatically
    //  generates an OutOfMemory error otherwise.
    {
        return false;
    }

    public boolean gotoBeginning( )
    // If list not empty, moves cursor to beginning of list & returns true, else returns false
    {
        if(!isEmpty())
        {
            cursor = head;
            return true;
        }
        else
            return false;
    }

    public boolean gotoEnd( )
    // If list not empty, moves cursor to end of list & returns true,else returns false
    {
        if(!isEmpty())
        {
            while (cursor.getNext() != null)
                cursor = cursor.getNext();
            return true;
        }
        else
            return false;
    }

    public boolean gotoNext( )
    // If cursor not at end of list, moves cursor to next element in list & returns true
    //  else returns false
    {
        if(cursor.getNext() != null)
        {
            cursor = cursor.getNext();
            return true;
        }
        else
            return false;
    }

    public boolean gotoPrior( )
    // If cursor not at beginning of list, moves cursor to preceding element
    //   in list & returns true, else returns false
    {
        if(cursor != head)
        {
            SListNode tempCursor = head;
            while(tempCursor.getNext() != cursor)
                tempCursor = tempCursor.getNext();
            cursor = tempCursor;
            return true;
        }
        else
            return false;
    }

    public Object getCursor( )
    // Returns the element at the cursor
    {
        if(cursor != null)
            return cursor.getElement();

        else
            return null;
    }

    public void showStructure( )
    // Outputs the elements in a list. If the list is empty, outputs
    // "Empty list". This operation is intended for testing and
    // debugging purposes only.
    {
        if(!isEmpty())
        {
            System.out.println("Cursor is on : " + cursor.getElement());
            SListNode tempCursor = head;

            System.out.print("head ");
            do
            {
                System.out.print(tempCursor.getElement() + " ");
                tempCursor = tempCursor.getNext();
            } while(tempCursor != null);
            System.out.println("tail");

        }
        else
            System.out.println("Empty List");
    }


    //--------------------------------------------------------------------
    //
    //                        In-lab operations
    //
    //--------------------------------------------------------------------

    void moveToBeginning ( )                    // Move to beginning
    {
        Object temp = cursor.getElement();
        remove();

        head = cursor = new SListNode(temp, head);
    }

    void insertBefore ( Object newElement )     // Insert before cursor
    {
        if(cursor == head)
            head = cursor = new SListNode(newElement, head);
        else
        {
            SListNode tempCursor = cursor;
            insert(cursor.getElement());
            cursor = tempCursor;
            replace(newElement);
        }
    }
    
} // class SList