package lecture.six.hibernate.ex2;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Entity
@Table(name = "people")
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String surname;
    private String address;

    public Person() {}
    public Person(String firstname, String surname, String address) {
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
    }

    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }

    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Person{id=%d, firstname='%s', surname='%s', address='%s'}"
                .formatted(id, firstname, surname, address);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        // Загрузка hibernate.properties из подпакета
        Properties properties = new Properties();
        properties.load(Main.class.getResourceAsStream("/ex2/hibernate.properties"));

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        SessionFactory sessionFactory = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Person.class)
                .buildMetadata()
                .buildSessionFactory();

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
