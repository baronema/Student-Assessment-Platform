/**
 *
 * @author Mark Barone
 */
package library.domain;

import java.util.ArrayList;


public class Progression {
    final private ArrayList<String> progression;
    
    public Progression(ArrayList<String> progList){
        this.progression = new ArrayList<>();
        this.progression.addAll(progList);
    }
    public ArrayList<String> getProgList(){return progression;};
}
