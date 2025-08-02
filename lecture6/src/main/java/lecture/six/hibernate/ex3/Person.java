package lecture.six.hibernate.ex3;

public class Person {
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
    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Person{id=%d, firstname='%s', surname='%s', address='%s'}"
                .formatted(id, firstname, surname, address);
    }
}
