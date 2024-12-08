package Personas;

public class Person {
    private int id;
    private String name;
    private String lastName;
    private int age;

    public Person() {
    }

    public Person(int id, String name, String lastName, int age){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.lastName + "," + this.age;
    }
}
