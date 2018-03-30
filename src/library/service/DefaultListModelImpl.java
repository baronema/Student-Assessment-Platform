/**
 *
 * @author Mark Barone
 */
package library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import library.domain.Student;

public class DefaultListModelImpl implements IDefaultListModelSvc{
    
    private final String connString = "jdbc:ucanaccess://C:\\PrimroseProject\\Primrose Assessment Platform\\Assessment_DB.accdb";

    private Connection getConnection() throws Exception{
        return DriverManager.getConnection(connString);
    }
    
    @Override
    public DefaultListModel retrieve() throws Exception{
        DefaultListModel<Student> listModel = new DefaultListModel<>();
        
        try(Connection conn = getConnection()){
            String sql = "SELECT * FROM Students ORDER BY Lastname";
            ResultSet rs;
            Statement s= conn.createStatement();
            rs = s.executeQuery(sql);
            while(rs.next()){
                String fName = rs.getString(1);
                String lName = rs.getString(2);
                String id = rs.getString(3);
                Student stu = new Student(fName, lName, id);
                listModel.addElement(stu);
            }
        }
        catch (Exception e){
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " +
                        ((SQLException)e).getSQLState());

                System.err.println("Error Code: " +
                        ((SQLException)e).getErrorCode());

                System.err.println("Message: " + e.getMessage());

                Throwable t = e.getCause();
                    while(t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                }         
            }
            
            // ListModel instance is not returned to GUI
            throw e;
        }
        return listModel;
    }
}
