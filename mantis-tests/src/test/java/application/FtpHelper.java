package application;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created on 16/10/2017. по уроку 8.5 передача файлов
 * для работы треб подключить зависимость compile 'commons-net:commons-net:x.xx '
 */
public class FtpHelper {
    private final ApplicationManager app;
    private FTPClient ftp;

    // конструктор
    public FtpHelper(ApplicationManager app) {
        this.app = app;
        this.ftp = new FTPClient();
    }


    // ** Использоуется при инициализации в testbase для подмены файла с каптчей на без.
    public void upload (File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.username"), app.getProperty("ftp.password"));  // значения берутся из файла св-в,
        // указанного при инициализации, по умолчанию local.properties
        ftp.deleteFile(backup); // на всякий случай удаляем предыдущий backup
        ftp.rename(target, backup);  // переименовываем текущий файл в backup
        ftp.enterLocalPassiveMode(); // специфика исп в данном случае сервера, так надо :)
        ftp.storeFile(target, new FileInputStream(file)); // загружаем в таргет-файл  файл с локальной машины где  прописана отключка капчи
        ftp.disconnect();

    }

    public void restore( String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.username"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target); // то что было backup-ом делаем target
        ftp.disconnect();

    }
}
