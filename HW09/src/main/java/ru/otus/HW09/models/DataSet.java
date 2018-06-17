package ru.otus.HW09.models;

import ru.otus.HW09.annotations.Column;
import ru.otus.HW09.annotations.Id;

public abstract class DataSet {
    @Id
    @Column
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}