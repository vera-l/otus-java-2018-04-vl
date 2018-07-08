package ru.otus.HW07;

import java.util.ArrayList;
import ru.otus.HW06.ATM;
import ru.otus.HW06.ATMResetListener;
import ru.otus.HW06.EventManager;

class ATMDepartment {
    private final ArrayList<ATM> atms;
    private final EventManager events;

    ATMDepartment() {
        atms = new ArrayList<>();
        events = new EventManager();
    }

    public void add(ATM atm) {
        atms.add(atm);
        events.subscribe("reset", new ATMResetListener(atm));
    }

    public int getSumFromAllATMs() {
        return atms.stream().map(
            atm -> atm.getSum()
        ).reduce(
            0, (x, y) -> x + y
        );
    }

    public void resetAll() {
        events.notify("reset");
    }

    public String toString() {
        return String.valueOf(atms.size()) + " atms";
    }
}
