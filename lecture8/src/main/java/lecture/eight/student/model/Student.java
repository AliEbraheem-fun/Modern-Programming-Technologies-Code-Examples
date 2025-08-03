package lecture.eight.student.model;

import jakarta.persistence.*;

/**
 * Entity representing a student.
 * <p>
 * Each student has a unique ID, a first name, and a surname.
 * Used for both displaying and managing student data in the application.
 * </p>
 */
@Entity
@Table(name = "students") // Явное указание имени таблицы
public class Student {

    /**
     * Unique identifier for the student (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Можно использовать IDENTITY для баз данных типа MySQL
    @Column(name = "id") // Явное указание имени столбца
    private long id;

    /**
     * First name of the student.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Last name (surname) of the student.
     */
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    /**
     * Default constructor.
     */
    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
