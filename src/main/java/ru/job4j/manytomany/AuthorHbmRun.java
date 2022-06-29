package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AuthorHbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book firstBook = Book.of("The first book");
            Book secondBook = Book.of("The second book");
            Book thirdBook = Book.of("The third book");

            Author firstAuthor = Author.of("The first author");
            firstAuthor.getBooks().add(firstBook);
            firstAuthor.getBooks().add(secondBook);
            firstAuthor.getBooks().add(thirdBook);

            Author secondAuthor = Author.of(("The second author"));
            secondAuthor.getBooks().add(firstBook);
            secondAuthor.getBooks().add(thirdBook);

            Author thirdAuthor = Author.of("The third author");
            thirdAuthor.getBooks().add(secondBook);
            thirdAuthor.getBooks().add(thirdBook);

            session.persist(firstAuthor);
            session.persist(secondAuthor);
            session.persist(thirdAuthor);

            Author author = session.get(Author.class, 3);
            session.remove(author);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
