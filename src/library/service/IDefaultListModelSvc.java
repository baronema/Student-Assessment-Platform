/*
 * @author Mark Barone
 */
package library.service;

import javax.swing.DefaultListModel;

public interface IDefaultListModelSvc extends IService {
    public DefaultListModel retrieve() throws Exception;
    
}
