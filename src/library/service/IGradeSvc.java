/**
 *
 * @author Mark Barone
 */
package library.service;

import java.util.ArrayList;
import library.domain.Grade;

public interface IGradeSvc extends IService {
    
    public Grade store(Grade grade) throws Exception;
    
    public ArrayList<Grade> retrieve(Grade grade) throws Exception;
}
    

