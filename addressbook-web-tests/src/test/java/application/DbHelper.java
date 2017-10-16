package application;

import model.GroupData;
import model.Groups;
import model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by uasso on 26/09/2017.
 * помощник работы с БД
 */
public class DbHelper {
    private   final SessionFactory sessionFactory;

    public DbHelper() { // инициализатор -конструктор
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public  Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list(); // GroupData это не таблица, а название объекта
        //  а Hibernate уже  сам разберется какой таблице соотв этот объект на основании связей которые прописаны в классе GroupData
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }
    public List<UserData> users()  {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData" ).list(); // UserData это не таблица, а название объекта
        //  а Hibernate уже  сам разберется какой таблице соотв этот объект на основании связей которые прописаны в классе UserData
        session.getTransaction().commit();
        session.close();
        return result;
    }
}

