package ru.otus.HW06;

import java.util.HashMap;

class Main {
    public static void main(String... args) {
        ATM atm = new ATM(new HashMap<>());

        System.out.println("Добавляем банкноты:");
        atm.put(Banknote.N50, 3);
        atm.put(Banknote.N100, 1);
        atm.put(Banknote.N200, 2);
        atm.put(Banknote.N500, 1);
        atm.put(Banknote.N1000, 3);
        atm.put(Banknote.N2000, 1);
        atm.put(Banknote.N5000, 2);
        System.out.println(atm);

        int NEEDED_SUM = 4750;
        System.out.println("Затем забираем из банкомата " + String.valueOf(NEEDED_SUM) + ": ");
        try {
            atm.take(NEEDED_SUM);
        } catch (NoNeededSumException e) {
            System.out.print(e.getMessage());
        }

        System.out.println(atm);

        NEEDED_SUM = 2320;
        System.out.println("Затем забираем из банкомата еще " + String.valueOf(NEEDED_SUM) + ": ");
        try {
            atm.take(NEEDED_SUM);
        } catch (NoNeededSumException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(atm);

        NEEDED_SUM = 100_000;
        System.out.println("Затем забираем из банкомата еще " + String.valueOf(NEEDED_SUM) + ": ");
        try {
            atm.take(NEEDED_SUM);
        } catch (NoNeededSumException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(atm);
    }

}