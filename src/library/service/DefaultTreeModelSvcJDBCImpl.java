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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import library.domain.Node;


public class DefaultTreeModelSvcJDBCImpl implements IDefaultTreeModelSvc{
    
    private final String connString = "jdbc:ucanaccess://C:\\PrimroseProject\\Primrose Assessment Platform\\Assessment_DB.accdb";

    private Connection getConnection() throws Exception{
        return DriverManager.getConnection(connString);
    }
    
    @Override
    public DefaultTreeModel retrieve() throws Exception{
        
        int id;
        int dID;
        int cID;
        String domain;
        String category;
        String objective;
        
        DefaultMutableTreeNode dom = new DefaultMutableTreeNode(null);
        DefaultMutableTreeNode cat = new DefaultMutableTreeNode(null);
        DefaultMutableTreeNode obj;
        Node temp;
        
        Node node = new Node(1, " ", "Learning Domains");
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(node);
        
        try(Connection conn = getConnection()){
            String sql = "SELECT [Domains].Domain, [Category].Cat_Name, " +
                    "[Objectives].Obj_Name, [Objectives].ID, [Category].Cat_ID, [Domains].Dom_ID FROM [Objectives] " +
                    "INNER JOIN ([Domains] INNER JOIN [Category] " +
                    "ON [Domains].Dom_ID = [Category].Domain) " +
                    "ON [Objectives].Cat_ID = [Category].Cat_ID ORDER by [Objectives].ID";
            
            ResultSet rs;
            Statement s= conn.createStatement();
            rs = s.executeQuery(sql);
            
            temp = (Node)root.getUserObject();
            
            while(rs.next()){
                domain = rs.getString(1);
                category = rs.getString(2);
                objective = rs.getString(3);
                id = rs.getInt(4);
                dID = rs.getInt(5);
                cID = rs.getInt(6);
                
                
                if(! temp.getLabel().equals(category)){ 
                    if(! temp.getParent().equals(domain)){
                        node = new Node(cID,"root",domain);
                        dom = new DefaultMutableTreeNode(node);
                        root.add(dom);
                    }
                    node = new Node(dID,domain,category);
                    cat = new DefaultMutableTreeNode(node);
                    dom.add(cat);
                }
                
                node = new Node(id, category, objective);
                obj = new DefaultMutableTreeNode(node);
                cat.add(obj);
                temp = (Node)cat.getUserObject();
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
            
            // DefaultTreeModel instance is not returned to GUI
            throw e;
        }
        DefaultTreeModel model = new DefaultTreeModel(root);
        return model;
    }
}
