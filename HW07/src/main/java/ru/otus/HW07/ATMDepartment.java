package ru.otus.HW07;

import java.util.ArrayList;
import java.util.List;

import ru.otus.HW06.ATM;
import ru.otus.HW06.Observer;

class ATMDepartment implements Observable {
    private final List<ATM> atms;
    // отдельный список, так как не все atm могут являться наблюдателями
    private final List<Observer> observers;

    ATMDepartment() {
        atms = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void add(ATM atm) {
        atms.add(atm);
        addObserver(atm);
    }

    @Override
    public void addObserver(Observer atm) {
        observers.add(atm);
    }

    @Override
    public void removeObserver(Observer atm) {
        observers.remove(atm);
    }

    public void notifyObservers() {
        atms.stream().forEach(ATM::handle);
    }

    public int getSumFromAllATMs() {
        return atms.stream().map(ATM::getSum)
                   .reduce(0, (x, y) -> x + y);
    }

    public String toString() {
        return String.valueOf(atms.size()) + " atms";
    }
}
