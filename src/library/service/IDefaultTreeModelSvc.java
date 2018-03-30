/**
 *
 * @author Mark Barone
 */
package library.service;

import javax.swing.tree.DefaultTreeModel;

public interface IDefaultTreeModelSvc extends IService {
    
        public DefaultTreeModel retrieve() throws Exception;
}