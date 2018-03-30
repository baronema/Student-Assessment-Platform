/**
 *
 * @author Mark Barone
 */
package library.service;

import library.domain.Progression;

    
public interface IProgressionSvc extends IService {
    
        public Progression retrieve(int id) throws Exception;
}
