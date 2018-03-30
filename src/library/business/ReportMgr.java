package library.business;

/**
 *
 * @author Punisher
 */


import library.domain.Report;
import library.service.Factory;
import library.service.IReportSvc;


public class ReportMgr{
    public Report retBelow(Report report) throws Exception{
       
        Factory factory = new Factory();
        IReportSvc retTable = (IReportSvc)factory.getService("IReportSvc");
        return retTable.retBelow(report);
    }
    
    public Report retAbove(Report report) throws Exception{
       
        Factory factory = new Factory();
        IReportSvc retTable = (IReportSvc)factory.getService("IReportSvc");
        return retTable.retAbove(report);
    }
    
    public Report retAll(Report report) throws Exception{
       
        Factory factory = new Factory();
        IReportSvc retTable = (IReportSvc)factory.getService("IReportSvc");
        return retTable.retAll(report);
    }
}
