package ru.otus.HW06;

public class ATMResetListener implements EventListener {

    private ATM atm;

    public ATMResetListener(ATM atm) {
        this.atm = atm;
    }

    public void onEvent() {
        atm.reset();
    }
}
