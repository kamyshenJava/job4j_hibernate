package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class CandidateHbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", "1.5 years", 350.5);
            Candidate two = Candidate.of("Mark", "0.5 years", 250.5);
            Candidate three = Candidate.of("Paul", "12 years", 1200D);

            session.save(one);
            session.save(two);
            session.save(three);

            Query query = session.createQuery("from Candidate ");
            for (Object c : query.list()) {
                System.out.println(c);
            }

            Query query2 = session.createQuery("from Candidate c where c.id = 1");
            System.out.println(query2.uniqueResult());

            Query query3 = session.createQuery("from Candidate c where c.name = 'Paul'");
            System.out.println(query3.uniqueResult());

            session.createQuery("update Candidate c set c.salary = :newSalary where c.id = :fId")
                    .setParameter("newSalary", 500D)
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
