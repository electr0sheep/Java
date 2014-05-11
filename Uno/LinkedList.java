// Michael DeGraw
// COSC 2415
// 3/28/2014
// R10483006

/**
 * @author Michael DeGraw
 */

package uno;
/*private class Link <T>{
    private T data;
    private Link previous;
    private Link next;
    Link(T d, Link p){
        data = d;
        previous = p;
        next = null;
    }
    public class LinkedList <T>{
        LinkedList(T data){
            Link(data, null);
        }
    }
}*/

// THE DATA TYPE WILL BE SPECIFIED UPON LIST CREATION
//  THE ROOT WILL BE INITIALIZED UPON LIST CREATION
/** A class usable to store data in the form of a doubly linked list.
    A doubly linked list is composed of nodes, with each node containing:
    Data, the location of the node previous, and the location of the node next
    
    @author Michael DeGraw
*/

/**
 * 
 * @author Michael DeGraw
 * @param <T> The parameter that makes me want to club hundreds of baby seals. But not literally. I'm not a psycho.
 */
public class LinkedList <T extends Comparable<T>>{
    // It is much easier to dynamically sort a list as links are added
    //  as opposed to sorting it only every now and then. Therefore
    //  the flag sorted can be used to simply specify whether or not
    //  the list is currently sorted.
    private boolean sorted;
    private Link root;
    private Link end;
    private int size;
    
    /**
     * This list will not be sorted.
     * @param data The data of the first node.
     */
    LinkedList(T data){
        root = new Link(data, null);
        end = root;
        sorted = false;
        size = 1;
    }
    
    /**
     * 
     * @param data The data of the first node.
     * @param sort Flags the list to be sorted or not.
     */
    LinkedList(T data, boolean sort){
        root = new Link(data, null);
        end = root;
        sorted = sort;
        size = 1;
    }
    
    /**
     * 
     * @param sort Indicates if the list should be sorted or not
     */
    LinkedList(boolean sort){
        sorted = sort;
        size = 0;
    }
    
    /**
     * A method added in the haste to just finish the project.
     * This should not exist.
     * @param data 
     */
    public void startList(T data){
        root = new Link(data, null);
        end = root;
        size = 1;
    }
    
     /**
      * Method that adds a link to the list.
      * @param data Adds a new link with data.
      */
    public void addLink(Comparable<T> data){
        size++;
        if (sorted){
            // first, check to make sure that the new data is not smaller than everything else in the list
            // if it is, simply add it to the front
            if (root.data.compareTo(data) == 1){
                root.previous = new Link(data, null, root);
                root = root.previous;
            // if the new data is not smaller than everything in the list
            } else{
                Link current = root;
                while (current.next != null && current.next.data.compareTo(data) == -1)
                    current = current.next;
                insertLink(data, current);
                /*if (current.next != null){
                    current.next = new Link(data, current, current.next);
                    current.next.next.previous = current.previous.next;
                } else{
                    insertLink(data, current);
                    current.next = new Link(data, end);
                    end = end.next;
                    end.next = null;
                }*/
            }
        // if not sorted
        } else{
            insertLink(data, end);
            /*
            end.next = new Link(data, end);
            end = end.next;
            end.next = null;*/
        }
    }
    
    /**
     * 
     * @param data The data to be added
     * @param location The new Link will be added AFTER this part
     */
    private void insertLink(Comparable<T> data, Link location){
        location.next = new Link(data, location, location.next);
        // if the new Link is inserted in the middle of the list
        if (location.next.next != null)
            location.next.next.previous = location.next;
        else
            end = location.next;
    }
    
    public void setSorted(boolean flag){
        sorted = flag;
            if (sorted)
                sortList();
    }
    
    /**
     * Method that will sort the list.
     */
    private void sortList(){
        Link pivot = root;
        Link swap = root;
        Link current = root.next;
        while (pivot.next != null){
            // WHILE LOOP FINDS THE LOWEST VALUE
            while (current != null){
                if (current.data.compareTo(swap.data) == -1)
                    swap = current;
                current = current.next;
            }
            // IF A LOWER VALUE WAS FOUND
            if (swap != pivot){
                switchLink(pivot, swap);
            }
            
            if (pivot.next != null){
                pivot = pivot.next;
                swap = pivot;
                current = pivot.next;
            }
        }
    }
    
    /**
     * Method that exchanges two links in a linked list
     * @param first The first link
     * @param second The second link
     */
    private void switchLink(Link first, Link second){
        Comparable<T> data = first.data;
        first.data = second.data;
        second.data = data;
    }
    
    public void switchLink(int first, int second){
        switchLink(getLink(first), getLink(second));
    }
    
    /**
     * Not quite sure how to permanently delete a link
     * @param set The list of which the root will be retrieved, and deleted
     * @return The data found in the root that is now deleted
     */
    private Comparable<T> popRoot(){
        Comparable<T> data = root.data;
        root = root.next;
        size --;
        return data;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public void mergeWith(LinkedList second){
        while (!second.isEmpty())
            addLink(second.popRoot());
    }
    
    public Link getLink(int num){
        if (num > 1 && num < size){
            Link current = root;
            for (int x = 1; x < num; x++)
                current = current.next;
            return current;
        }
        return null;
    }
    
    
    /**
     * Method that will print the list.
     */
    public void printList(){
        Link current = root;
        int x = 0;
        while (current != null){
            System.out.println("Link " + ++x + ": " + current.data);
            current = current.next;
        }
        System.out.println("End of list");
    }
    
    // It appears as though it is not necessary to free memory in java
    /*public void deleteList(){
        Link current = root;
        Link next = root.next;
        while (current != null){
            
        }
    }*/
    
    private class Link <T extends Comparable<T>> implements Comparable<Link<T>>{
        private T data;
        private Link previous;
        private Link next;
        
        /**
         * 
         * @param d The data to be entered in the new node
         * @param p The Link previous to this one
         */
        Link(T d, Link p){
            data = d;
            previous = p;
            next = null;
        }
        
        /**
         * 
         * @param d The data to be entered in the new node
         * @param p The Link previous to this one
         * @param n The Link after this one
         */
        Link(T d, Link p, Link n){
            data = d;
            previous = p;
            next = n;
        }
        
        public Comparable<T> getData(){
            return data;
        }
        
        @Override
        public int compareTo(Link<T> c){
            return data.compareTo(c.data);
        }
        
        /*protected T getData(){
            return data;
        }*/
    }
}