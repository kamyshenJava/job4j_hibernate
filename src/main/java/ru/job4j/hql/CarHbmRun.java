package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CarHbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel one = CarModel.of("M3 Sedan");
            CarModel two = CarModel.of("M4 Coupe");
            CarModel three = CarModel.of("M5 Sedan");
            CarModel four = CarModel.of("M8 Competition Coupe");
            CarModel five = CarModel.of("X6 M Sports Activity Coupe");
            session.save(one);
            session.save(two);
            session.save(three);
            session.save(four);
            session.save(five);

            CarType bmw = CarType.of("BMW");
            bmw.addModel(session.load(CarModel.class, 1));
            bmw.addModel(session.load(CarModel.class, 2));
            bmw.addModel(session.load(CarModel.class, 3));
            bmw.addModel(session.load(CarModel.class, 4));
            bmw.addModel(session.load(CarModel.class, 5));

            session.save(bmw);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
