package tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by uasso on 06/12/2017.
 */
public class SkipIfNotFixedTests extends TestBase {
    @Test
    // тест который выполняется в зависимости то того исправлен ли какой то определенный баг
    public void testCheckIfFixed () throws IOException {

        skipIfNotFixed(1);

        System.out.println("runing test, the bug 43 should be fixed !!");


    }


}
