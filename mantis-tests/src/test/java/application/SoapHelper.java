package application;


import biz.futureware.mantis.rpc.soap.client.*;
import com.gargoylesoftware.htmlunit.javascript.host.Set;
import model.Issue;
import model.Project;
import org.apache.axis.i18n.ProjectResourceBundle;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created on 23/11/2017.по  аналогии с видео 9.3
 */
public class SoapHelper {

    public ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public java.util.Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();

        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "toor");

        java.util.Set<Project> projectSet = Arrays.asList(projects).stream().map(p -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());

        return projectSet;
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {

        return new MantisConnectLocator().
                    getMantisConnectPort(new URL(app.getProperty("web.BaseUrl") +"api/soap/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();

        BigInteger projectId = mc.mc_project_get_id_from_name("administrator", "toor", issue.getProject());

        // у проекта находим список категорий
        String[] projectCategs = mc.mc_project_get_categories("administrator", "toor", projectId);

        IssueData issueData = new IssueData();

        issueData.setCategory(projectCategs[0]);
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(projectId, issue.getProject()));

       int id = mc.mc_issue_add(app.getProperty("web.AdminLogin"), app.getProperty("web.AdminPassword"), issueData).intValue();


        Issue created = new Issue().withId(id).
                withSummary(issueData.getSummary()).withDescription(issueData.getDescription());
        return created;
    }

    public boolean isIssueResolvedorClosed(int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issuedata = mc.mc_issue_get(app.getProperty("web.AdminLogin"), app.getProperty("web.AdminPassword"), BigInteger.valueOf(id));

       String issueStatus = issuedata.getStatus().getName();
       boolean r = issueStatus.equals("resolved");
       boolean c = issueStatus.equals("closed");

       return (issueStatus.equals("resolved")|| issueStatus.equals("closed") );
    }
}
