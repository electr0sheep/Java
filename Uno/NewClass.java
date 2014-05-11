package uno;
public class NewClass <T extends Comparable<T>>{
    private Link root;
    private Link end;
    
    NewClass(T stuff){
        root = new Link(stuff);
        end = root;
    }
    
    public void addLink(T stuff){
        end.next = new Link(stuff);
        end = end.next;
    }
    
    public void sortList(){
        if (root.data.compareTo(end.data) == 1){
            Comparable<T> temp = end.data;
            end.data = root.data;
            root.data = temp;
        }
    }
    
    private class Link <T extends Comparable<T>>{
        private T data;
        private Link next;
        
        Link(T stuff){
            data = stuff;
            next = null;
        }
        
        public int compareTo(Link<T> c){
            return data.compareTo(c.data);
        }
    }
}
