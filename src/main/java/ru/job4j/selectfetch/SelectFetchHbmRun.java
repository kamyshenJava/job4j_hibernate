package ru.job4j.selectfetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashSet;
import java.util.Set;

public class SelectFetchHbmRun {
    public static void main(String[] args) {

        VacancyDb rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Applicant candidate = Applicant.of("John");

            Vacancy first = Vacancy.of("The first vacancy");
            Vacancy second = Vacancy.of("The second vacancy");
            Vacancy third = Vacancy.of("The third vacancy");
            Set<Vacancy> vacancySet = new HashSet<>();
            vacancySet.add(first);
            vacancySet.add(second);
            vacancySet.add(third);

            VacancyDb mainDb = VacancyDb.of("Main");

            mainDb.setApplicant(candidate);
            mainDb.setVacancies(vacancySet);

            session.save(mainDb);

            rsl = session.createQuery(
                    "Select distinct db from VacancyDb db join fetch db.vacancies join fetch db.applicant",
                    VacancyDb.class).uniqueResult();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}
