package ru.otus.HW07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.HW06.ATM;
import ru.otus.HW06.Banknote;
import ru.otus.HW06.NoNeededSumException;

import java.util.HashMap;
import java.util.Map;

public class ATMDepartmentTests {

    private ATMDepartment atmDepartment;

    @Before
    public void setupMethod() {
        atmDepartment = new ATMDepartment();
    }

    @Test
    public void depShouldGetSumFromAllATMs() {
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

        Assert.assertEquals(
            atmDepartment.getSumFromAllATMs(), atm1.getSum() + atm2.getSum()
        );
    }

    @Test
    public void atmShouldResetOwnStateByEvent() throws NoNeededSumException {
        // Создаем 2 ATM
        HashMap<Banknote, Integer> atm1InitState = new HashMap<>(
            Map.ofEntries(
                Map.entry(Banknote.N50, 3),
                Map.entry(Banknote.N100, 2),
                Map.entry(Banknote.N200, 1),
                Map.entry(Banknote.N2000, 5)
            )
        );
        ATM atm1 = new ATM(atm1InitState);
        atmDepartment.add(atm1);

        HashMap<Banknote, Integer> atm2InitState = new HashMap<>(
            Map.ofEntries(
                Map.entry(Banknote.N50, 10),
                Map.entry(Banknote.N200, 3),
                Map.entry(Banknote.N500, 8),
                Map.entry(Banknote.N5000, 5)
            )
        );
        ATM atm2 = new ATM(atm2InitState);
        atmDepartment.add(atm2);

        // Кладем и забираем некоторые суммы
        atm1.put(Banknote.N50, 3);
        atm1.put(Banknote.N500, 2);
        atm1.put(Banknote.N5000, 1);
        atm1.take(200);

        atm1.put(Banknote.N50, 3);
        atm1.put(Banknote.N500, 2);
        atm1.put(Banknote.N5000, 1);
        atm1.take(200);

        // Восстанавливаем начальное состояние всех atm
        atmDepartment.resetAll();

        Assert.assertTrue(atm1.isStateEquals(atm1InitState));
        Assert.assertTrue(atm2.isStateEquals(atm2InitState));
    }
    
}
