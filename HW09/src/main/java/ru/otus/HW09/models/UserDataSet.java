package ru.otus.HW09.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="users")
public class UserDataSet extends DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name="users_seq", sequenceName="user_id_seq", allocationSize=10)
    private Long id;

    @Column(name="name")
    public String name;

    @Column(name="age")
    public Integer age;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    AddressDataSet address;

    @OneToMany(mappedBy="userDataSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<PhoneDataSet> phones = Collections.emptyList();

    public UserDataSet() { }

    public UserDataSet(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
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
