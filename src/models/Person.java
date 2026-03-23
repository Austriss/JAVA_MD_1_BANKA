package models;

public class Person {
    private String name;
    private String surname;
    private String personal_code;

    public void set_name(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }
    public void set_surname(String surname) {
        if (!surname.isEmpty()) {
            this.surname = surname;
        }
    }
    public void set_personal_code(String code) {
        if (code.matches("^\\d{11}$")) {
            personal_code = code;
        }
    }
    public String get_name() {
        return name;
    }
    public String get_surname() {
        return surname;
    }
    public String get_personal_code() {
        return personal_code;
    }
    public Person() {
        set_name("Janis");
        set_surname("Abols");
    }

    public Person(String name, String surname, String personal_code) {
        set_name(name);
        set_surname(surname);
        set_personal_code(personal_code);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", personal_code='" + personal_code + '\'' +
                '}';
    }
}
