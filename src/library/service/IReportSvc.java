/**
 *
 * @author Markj Barone
 */
package library.service;

import library.domain.Report;

public interface IReportSvc extends IService {
    
        public Report retBelow(Report report) throws Exception;
        public Report retAbove(Report report) throws Exception;
        public Report retAll(Report report) throws Exception;
}
