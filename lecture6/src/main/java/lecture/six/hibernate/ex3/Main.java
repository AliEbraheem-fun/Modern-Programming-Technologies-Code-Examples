package lecture.six.hibernate.ex3;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .configure("/ex3/hibernate.cfg.xml")  // explicitly load config
                .addResource("/ex3/Person.hbm.xml");  // explicitly add mapping

        SessionFactory sessionFactory = config.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Person("John", "Doe", "123 Main St"));
            session.persist(new Person("Alice", "Smith", "456 Park Ave"));
            session.persist(new Person("Bob", "Brown", "789 Elm St"));
            session.getTransaction().commit();
            System.out.println("Данные добавлены:");
            printPeopleByCriteria(session);
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person alice = session.createQuery("from Person where firstname = :name", Person.class)
                    .setParameter("name", "Alice")
                    .uniqueResult();
            if (alice != null) {
                alice.setAddress("999 Updated Ave");
            }
            session.getTransaction().commit();
            System.out.println("Адрес Alice обновлён (HQL):");
            printPeopleByCriteria(session);
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Person> delete = cb.createCriteriaDelete(Person.class);
            Root<Person> root = delete.from(Person.class);
            delete.where(cb.equal(root.get("firstname"), "Bob"));
            int deletedCount = session.createMutationQuery(delete).executeUpdate();
            session.getTransaction().commit();

            System.out.printf("Удалено записей (Criteria API): %d\n", deletedCount);
            printPeopleByCriteria(session);
        }

        sessionFactory.close();
    }

    private static void printPeopleByCriteria(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root);

        List<Person> people = session.createQuery(query).getResultList();
        people.forEach(System.out::println);
        System.out.println();
    }
}

