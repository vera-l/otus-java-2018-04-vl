package ru.otus.HW09.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="phones")
public class PhoneDataSet extends DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phones_seq")
    @SequenceGenerator(name="phones_seq", sequenceName="phone_id_seq", allocationSize=10)
    private Long id;

    @Column(name="number")
    private String number;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDataSet userDataSet;

    public PhoneDataSet() { }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserDataSet getUserDataSet() {
        return userDataSet;
    }

    public void setUserDataSet(UserDataSet userDataSet) {
        this.userDataSet = userDataSet;
    }
}