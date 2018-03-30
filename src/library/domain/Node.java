/**
 *
 * @author Mark Barone
 */
package library.domain;

public class Node {
    final private int ID;
    final private String parent;
    final private String label;
    
    public Node(int id, String parent, String label){
        this.ID = id;
        this.parent = parent;
        this.label = label;
    }
    public Node(Node node){
        this.ID = node.ID;
        this.parent = node.parent;
        this.label = node.label
                ;
    }
    public int getID(){return ID;};
    public String getParent(){return parent;};
    public String getLabel(){return label;};
    
    @Override
    public String toString(){return ID + ". " + label;};
    
}
