package ru.otus.HW09;

import ru.otus.HW09.models.UserDataSet;
import java.util.List;

public interface DBService {

    UserDataSet read(long userId);

    List<UserDataSet> readAll();

    void save(UserDataSet user);

}