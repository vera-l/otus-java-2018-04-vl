package ru.otus.HW07;

import ru.otus.HW06.ATM;
import ru.otus.HW06.Banknote;

import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String... args) {
        ATMDepartment atmDepartment = new ATMDepartment();
        ATM atm1 = new ATM(
            new HashMap<>(
                Map.ofEntries(
                    Map.entry(Banknote.N50, 3),
                    Map.entry(Banknote.N100, 2),
                    Map.entry(Banknote.N200, 1),
                    Map.entry(Banknote.N2000, 5)
                )
            )
        );
        atmDepartment.add(atm1);
        System.out.println(atm1);

        ATM atm2 = new ATM(
            new HashMap<>(
                Map.ofEntries(
                    Map.entry(Banknote.N50, 10),
                    Map.entry(Banknote.N200, 8),
                    Map.entry(Banknote.N500, 7),
                    Map.entry(Banknote.N5000, 1)
                )
            )
        );
        System.out.println(atm2);
        atmDepartment.add(atm2);

        System.out.println(atmDepartment);

    }

}
