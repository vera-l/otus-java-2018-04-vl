# Hibernate ORM

На основе предыдущего ДЗ (myORM):

1. Оформить решение в виде DBService (interface DBService, class DBServiceImpl, UsersDAO, UsersDataSet, Executor)
2. Не меняя интерфейс DBService сделать DBServiceHibernateImpl на Hibernate.
3. Добавить в UsersDataSet поля:

  * адрес<sup>*</sup> (OneToOne)
  
```java
class AddressDataSet {
    private String street;
}
```

  * телефон<sup>*</sup> (OneToMany)
  
```java
class PhoneDataSet {
    private String number;
}
```

Добавить соответствущие датасеты и DAO.
  
4.Поддержать работу из пункта `3` в myORM<sup>**</sup>
