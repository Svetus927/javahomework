package tests;

import application.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static tests.TestBase.app;

/**
 * Created on 11/10/2017.
 * пробный тест чтоб проверить что логин под админом без браузера (через HTTP клиент)  работает

 */
public class LoginTests extends  TestBase {

    @Test
    //* * тест для проверки логин в мантис с креденшлс взятых с проперти-файла
    public void loginAsAdministrator() throws IOException {
        HttpSession session = app.newSession();// т.к. сессии очень легковесны без браузера они создаются напрямую в тесте,
        // это удобно когда нужно делать проверки зайдя под другим пользователем (тестером или девом или админом)

        boolean login = session.login(app.getProperty("web.AdminLogin"), app.getProperty("web.AdminPassword"));
        System.out.println("login "+ login);
        Assert.assertTrue(session.isLoggedInAs("administrator"), "строка поиска не найдена");

    }


    //* * тест для проверки логин в мантис с созданным юзером
    public void loginAsUser(String username, String password) throws IOException {
        HttpSession session = app.newSession();

        boolean login = session.login(username, password);
        System.out.println("login is "+ login);
        Assert.assertTrue(session.isLoggedInAs(username), "Проблема с логином - имя юзера на странице  не найдено!");

    }

}
