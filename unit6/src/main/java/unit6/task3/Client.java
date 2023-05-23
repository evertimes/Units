package unit6.task3;

import java.util.List;
import java.util.Objects;

public class Client {

    public Client(int id, String name, int age, List<PhoneNumber> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumbers = phoneNumbers;
    }

    private int id;
    private String name;
    private int age;
    private List<PhoneNumber> phoneNumbers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return id == client.id && age == client.age && Objects.equals(name, client.name)
               && Objects.equals(phoneNumbers, client.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, phoneNumbers);
    }

    @Override
    public String toString() {
        return "Client{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", phoneNumbers=" + phoneNumbers +
               '}';
    }
}
