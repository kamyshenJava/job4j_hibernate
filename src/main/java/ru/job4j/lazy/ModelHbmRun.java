package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ModelHbmRun {
    public static void main(String[] args) {
        List<Type> rsl = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Type bmw = Type.of("BMW");

            Model one = Model.of("M3 Sedan", bmw);
            Model two = Model.of("M4 Coupe", bmw);
            Model three = Model.of("M5 Sedan", bmw);

            session.save(bmw);
            session.save(one);
            session.save(two);
            session.save(three);
            rsl = session.createQuery("Select distinct t from Type t join fetch t.models").list();

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        for (Type type : rsl) {
            for (Model model : type.getModels()) {
                System.out.println(model);
            }
        }
    }
}

