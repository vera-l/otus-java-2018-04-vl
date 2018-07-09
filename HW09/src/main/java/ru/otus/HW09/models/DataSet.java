package ru.otus.HW09.models;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class DataSet {

    public abstract Long getId();

    public abstract void setId(Long id);

}
