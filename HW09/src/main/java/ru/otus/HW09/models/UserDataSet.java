package ru.otus.HW09.models;

import ru.otus.HW09.annotations.Table;
import ru.otus.HW09.annotations.Column;

@Table("users")
public class UserDataSet extends DataSet {

    @Column
    public String name;

    @Column("age")
    public Integer age;

    public UserDataSet() { }

    public UserDataSet(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return String.format(
            "USER #%s: %s, %s years old",
            getId() != null ? getId() : "-",
            getName() != null ? getName() : "-",
            getAge() != null ? getAge() : "-"
        );
    }
}
