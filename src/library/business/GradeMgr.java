/**
 *
 * @author Mark Barone
 */
package library.business;

import java.util.ArrayList;
import library.domain.Grade;
import library.service.Factory;
import library.service.IGradeSvc;

public class GradeMgr {
    
    public Grade storeGrade (Grade grade) throws Exception{
       
        Factory factory = new Factory();
        IGradeSvc gradeSvc = (IGradeSvc)factory.getService("IGradeSvc");
        return gradeSvc.store(grade);
    }
    public ArrayList<Grade> retrieveGrade (Grade grade) throws Exception{
       
        Factory factory = new Factory();
        IGradeSvc gradeSvc = (IGradeSvc)factory.getService("IGradeSvc");
        return gradeSvc.retrieve(grade);
    }
    
}
