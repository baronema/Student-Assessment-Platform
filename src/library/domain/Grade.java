/**
 *
 * @author Mark Barone
 */
package library.domain;


public class Grade {
    
    private final int objNum;
    private final String stuNum;
    private final String grade;
    private final String date;
    private final String note;
    private final String semester;
    
    public Grade (int num, String stu, String grade, 
                          String date, String note, String semester){
        this.objNum = num;
        this.stuNum = stu;
        this.grade = grade;
        this.date = date;
        this.note = note;
        this.semester = semester;
    }
    public int getObj(){return objNum;};
    public String getStu(){return stuNum;};
    public String getGrade(){return grade;};
    public String getDate(){return date;};
    public String getNote(){return note;};
    public String getSem(){return semester;};
    
    public boolean validate(){
        if (stuNum.equals("") || stuNum == null){
            return false;
            
        }
        else if (grade.equals("") || grade == null){
            return false;
            
        }
        else if (date.equals("") || date == null){
            return false;
            
        }
        else if (semester.equals("") || semester == null){
            return false;
            
        }
        else if (date.equals("") || date == null){
            return false;
            
        }
        else if (objNum == 0){
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString(){return "Error: projection for student #: " +
                                stuNum + " was not entered";}
}
