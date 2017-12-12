package tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import model.Issue;
import model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * partially created on 22/11/2017. по видео 9.3 +
 * Задание №19: Реализовать интеграцию тестов с баг-трекером MantisBT
 Представьте себе, что баг-репорты для тестируемой системы хранятся в баг-трекере MantisBT. Некоторые автотесты невозможно
 выполнить из-за наличия багов в тестируемой системе. До тех пор, пока баг не исправлен, тест выполнять нет смысла, он
 должен быть отключен. Но как только статус баг-репорта меняется -- тест должен автоматически активироваться. Необходимо
 реализовать такую интеграцию тестов с баг-трекером.

 Для этого нужно сделать следующее:

 1) В классе TestBase, от которого наследуются все тесты, необходимо реализовать функцию boolean isIssueOpen(int issueId) ,
 которая должна через Remote API получать из баг-трекера информацию о баг-репорте с заданным идентификатором, и возвращать значение false или true в зависимости от того, помечен он как исправленный или нет.

 2) Туда же в TestBase необходимо добавить такую функцию:

 public void skipIfNotFixed(int issueId) {
 if (isIssueOpen(issueId)) {
 throw new SkipException("Ignored because of issue " + issueId)
 }

 и вызывать её в начале нужного теста, чтобы он пропускался, если баг ещё не исправлен.
 */
public class SoapTests  extends TestBase {// в TestBase инициализируется appmanager{

   @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

       java.util.Set<Project> projects = app.soapHelper().getProjects();

       for ( Project project: projects) {
           System.out.println(project.getName());
       }
   }

   @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {

       java.util.Set<Project> projects = app.soapHelper().getProjects();


       Issue issue = new Issue().withSummary("summary1").withDescription("Description1").withProject(projects.iterator().next().getName());
       System.out.println("сделана подготовка объекта");
       Issue createdIssue = app.soapHelper().addIssue(issue);
       Assert.assertEquals(issue.getDescription(),createdIssue.getDescription() );
   }

   @Test
   // тест который выполняется в зависимости то того исправлен ли какой то определенный баг
    public void testCheckIfFixed () throws RemoteException, ServiceException, MalformedURLException {

       skipIfNotFixed(43);

       System.out.println("runing test, the bug 43 should be fixed !!");


   }

}
