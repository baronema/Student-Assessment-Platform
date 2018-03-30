/**
 *
 * @author Mark Barone
 */
package library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.domain.Progression;

public class ProgressionSvcJDBCImpl implements IProgressionSvc {
    
    private final String connString = "jdbc:ucanaccess://C:\\PrimroseProject\\Primrose Assessment Platform\\Assessment_DB.accdb";

    private Connection getConnection() throws Exception{
        return DriverManager.getConnection(connString);
    }
    
    @Override
    public Progression retrieve(int id) throws Exception{
        Progression progList;
        try(Connection conn = getConnection()){
            
            String sql = "SELECT Prog_2, Prog_4, Prog_6, Prog_8, Prog_10, Prog_12 "
                         + "FROM [Objectives] WHERE ID = ? ORDER by ID ";
            
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, id);
                ResultSet rs;
                rs = pstmt.executeQuery();
                ArrayList<String> tempList = new ArrayList<>();
                while(rs.next()){
                    
                    for(int i = 1; i <= 6; i++){
                        tempList.add(rs.getString(i));
                    }
                } 
                progList = new Progression(tempList);
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
            
            // Progression instance is not returned to GUI
            throw e;
        }
        return progList;
    }
}
