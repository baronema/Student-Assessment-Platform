/**
 *
 * @author Mark Barone
 */
package library.domain;

import javax.swing.table.DefaultTableModel;


public class Report {
    private final String name;
    private final String id;
    private final String semester;
    private final DefaultTableModel model;
    
    public Report(String name, String id, String semester){
        this.name = name;
        this.id = id;
        this.semester = semester;
        this.model = new DefaultTableModel();
    }
    public void setModelRow(Object[] row){
        this.model.addRow(row);    
    }
    public void SetModelColumn(String col){
        this.model.addColumn(col);
    }
    public String getName(){return name;};
    public String getID(){return id;};
    public String getSemester(){return semester;};
    public DefaultTableModel getModel(){return model;};
    
    public boolean validate(){
        if (name.equals("") || name == null){
            return false;
            
        }
        else if (id.equals("") || id == null){
            return false;
            
        }
        else if (semester.equals("") || semester == null){
            return false;
            
        }
        else if ((model == null) || model.getRowCount() == 0){
            return false;
        }
        
        return true;
    }
}
