/**
 *
 * @author Mark Barone
 */
package library.business;

import library.service.Factory;
import javax.swing.tree.DefaultTreeModel;
import library.service.IDefaultTreeModelSvc;

public class DefaultTreeModelMgr {
    
    public DefaultTreeModel retrieveTree() throws Exception{
       
        Factory factory = new Factory();
        IDefaultTreeModelSvc treeSvc = (IDefaultTreeModelSvc)factory.getService("IDefaultTreeModelSvc");
        return treeSvc.retrieve();
    }
}
