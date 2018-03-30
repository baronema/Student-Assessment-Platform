/**
 *
 * @author Mark Barone
 */
package library.business;

import library.domain.Progression;
import library.service.Factory;
import library.service.IProgressionSvc;

public class ProgressionMgr {
    
    public Progression retrieve(int id) throws Exception{
       
        Factory factory = new Factory();
        IProgressionSvc retProg = (IProgressionSvc)factory.getService("IProgressionSvc");
        return retProg.retrieve(id);
    }
}
