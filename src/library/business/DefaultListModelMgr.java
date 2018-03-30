/**
 *
 * @author Mark Barone
 */
package library.business;

import library.service.Factory;
import javax.swing.DefaultListModel;
import library.service.IDefaultListModelSvc;

public class DefaultListModelMgr {
    
    public DefaultListModel retrieveList() throws Exception{
       
        Factory factory = new Factory();
        IDefaultListModelSvc listSvc = (IDefaultListModelSvc)factory.getService("IDefaultListModelSvc");
        return listSvc.retrieve();
    }
}
