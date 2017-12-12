package tests;

import model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * пример подсоединения к БД через Hibernate ( c ресурс файлом hibernate... )
 *
 */
public class HbConnectionTests {
    private SessionFactory sessionFactory;

    @BeforeClass
protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
        // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
        // so destroy it manually.
        e.printStackTrace();
        StandardServiceRegistryBuilder.destroy( registry );
    }
}

    @Test
    public  void HbConnectionTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();

        for ( GroupData group : result ) {
            System.out.println( "Group (" + group.id() + ") : " + group.name() );
        }
        session.getTransaction().commit();
        session.close();
    }
}
