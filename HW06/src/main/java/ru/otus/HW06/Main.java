package ru.otus.HW06;

class Main {
    public static void main(String... args) {
        ATM atm = new ATM();

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
        atm.take(NEEDED_SUM);
        System.out.println(atm);

        NEEDED_SUM = 2320;
        System.out.println("Затем забираем из банкомата еще " + String.valueOf(NEEDED_SUM) + ": ");
        atm.take(NEEDED_SUM);
        System.out.println(atm);

        NEEDED_SUM = 100_000;
        System.out.println("Затем забираем из банкомата еще " + String.valueOf(NEEDED_SUM) + ": ");
        atm.take(NEEDED_SUM);
        System.out.println(atm);
    }

}