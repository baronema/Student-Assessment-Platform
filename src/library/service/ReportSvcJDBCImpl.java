/**
 *
 * @author Mark Barone
 */
package library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import library.domain.Report;

public class ReportSvcJDBCImpl implements IReportSvc{
    
    private final String connString = "jdbc:ucanaccess://C:\\PrimroseProject\\Primrose Assessment Platform\\Assessment_DB.accdb";

    private Connection getConnection() throws Exception{
        return DriverManager.getConnection(connString);
    }
    
    @Override
    public Report retBelow(Report report) throws Exception{
        
        
        String sql = "SELECT Domains.Dom_ID, Category.Cat_ID, Objectives.Obj_Name, " +
                "Grades.Grade, Grades.Grade_Date, Grades.Notes " +
                "FROM ((Grades INNER JOIN Objectives ON Grades.Obj_Num = Objectives.ID) " + 
                "INNER JOIN Category ON Objectives.Cat_ID = Category.Cat_ID) " + 
                "INNER JOIN Domains ON Category.Domain = Domains.Dom_ID " +
                "WHERE [Grades].Stu_ID = ? AND [Grades].Semester = ? AND " +
                "CAST([Grades].Grade AS INT) < CAST([Objectives].Below_Std AS INT);";
        
        Report nReport;
        nReport = buildModel(sql, report);
        return nReport;
    }
    @Override
    public Report retAbove(Report report) throws Exception{
        
        String sql = "SELECT Domains.Dom_ID, Category.Cat_ID, Objectives.Obj_Name, " +
                "Grades.Grade, Grades.Grade_Date, Grades.Notes " +
                "FROM ((Grades INNER JOIN Objectives ON Grades.Obj_Num = Objectives.ID) " + 
                "INNER JOIN Category ON Objectives.Cat_ID = Category.Cat_ID) " + 
                "INNER JOIN Domains ON Category.Domain = Domains.Dom_ID " +
                "WHERE [Grades].Stu_ID = ? AND [Grades].Semester = ? AND " +
                "CAST([Grades].Grade AS INT) > CAST([Objectives].Above_Std AS INT);";
        
        Report nReport;
        nReport = buildModel(sql, report);
        return nReport;    
    }
    @Override
    public Report retAll(Report report) throws Exception{
        
        String sql = "SELECT Domains.Dom_ID, Category.Cat_ID, Objectives.Obj_Name, " +
                "Grades.Grade, Grades.Grade_Date, Grades.Notes " +
                "FROM ((Grades INNER JOIN Objectives ON Grades.Obj_Num = Objectives.ID) " + 
                "INNER JOIN Category ON Objectives.Cat_ID = Category.Cat_ID) " + 
                "INNER JOIN Domains ON Category.Domain = Domains.Dom_ID " +
                "WHERE [Grades].Stu_ID = ? AND [Grades].Semester = ?;";
        
        Report nReport;
        nReport = buildModel(sql, report);
        return nReport;    
    }
    
    public Report buildModel(String sql, Report report) throws Exception{
     
        ResultSetMetaData metaData;
        
        try(Connection conn = getConnection()){  
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, report.getID());
                pstmt.setString(2, report.getSemester());
                ResultSet rs;
                rs = pstmt.executeQuery();
                metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                Object[] row = new Object[columnCount];
                
                report.SetModelColumn("Dom:");
                report.SetModelColumn("Cat:");
                report.SetModelColumn("Objective:");
                report.SetModelColumn("Grade:");
                report.SetModelColumn("Date:");
                report.SetModelColumn("Notes:");
                
                while(rs.next()){
                    for (int i = 0; i < columnCount; i++){
                        row[i] = rs.getObject(i+1);
                    }
                    report.setModelRow(row);
                }
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
            
            // report instance is not returned to GUI
            throw e;
        }
        return report;
    }
}
