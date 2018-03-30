/*
 * Author: Mark Barone
 */
package library.service;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import library.domain.Grade;

public class GradeSvcJDBCImpl implements IGradeSvc {
    
    private final String connString = "jdbc:ucanaccess://C:\\PrimroseProject\\Primrose Assessment Platform\\Assessment_DB.accdb";

    private Connection getConnection() throws Exception{
        return DriverManager.getConnection(connString);
    }
    
    @Override 
    public Grade store(Grade grade) throws Exception{
        
        try(Connection conn = getConnection()){
            
            String sql = "SELECT * FROM [Grades] WHERE Obj_Num = ? AND Stu_ID = ? AND Semester = ?";
            
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, grade.getObj());
                pstmt.setString(2, grade.getStu());
                pstmt.setString(3, grade.getSem());
                ResultSet rs;
                rs = pstmt.executeQuery();
                
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Progression is duplicate");
                    throw new Exception();
                }
            }
            String sql2 = "INSERT INTO Grades (Obj_Num, Stu_ID, Grade_Date, Grade, Notes, Semester) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            try(PreparedStatement pstmt = conn.prepareStatement(sql2)){
                pstmt.setInt(1, grade.getObj());
                pstmt.setString(2, grade.getStu());
                pstmt.setString(3, grade.getDate());
                pstmt.setString(4, grade.getGrade());
                pstmt.setString(5, grade.getNote());
                pstmt.setString(6, grade.getSem());
                pstmt.executeUpdate();
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
            
            // Grade instance is not returned to GUI
            throw e;
        }
        return grade;
        
    }
    @Override
    public ArrayList<Grade> retrieve(Grade grade) throws Exception{
        ArrayList<Grade> grades = new ArrayList<>();
        Grade g;
        
        try(Connection conn = getConnection()){
            
            String sql = "SELECT [Grades].Grade FROM [Grades] WHERE Obj_Num = ? AND Stu_ID = ?";
            
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, grade.getObj());
                pstmt.setString(2, grade.getStu());
                ResultSet rs;
                rs = pstmt.executeQuery();
                
                while(rs.next()){
                    String gr = rs.getString(1);
                    g = new Grade(0, null, gr, null, null, null);
                    grades.add(g);
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
            
                // ArrayList instance is not returned to GUI
                throw e;
            }
            
        return grades;
        }
    }
}

