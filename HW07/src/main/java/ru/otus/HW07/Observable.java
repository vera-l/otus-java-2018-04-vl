package ru.otus.HW07;

import ru.otus.HW06.Observer;

public interface Observable {

    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}
