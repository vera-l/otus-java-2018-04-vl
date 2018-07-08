package ru.otus.HW06;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ATMTests {

    @Test
    public void shouldPutAnyNumberOfBanknotes() {
        ATM atm = new ATM(
            new HashMap<>(
                Map.ofEntries(
                    Map.entry(Banknote.N50, 2),
                    Map.entry(Banknote.N100, 3),
                    Map.entry(Banknote.N200, 3),
                    Map.entry(Banknote.N500, 1)
                )
            )
        );

        atm.put(Banknote.N100, 1);
        atm.put(Banknote.N500, 2);

        Assert.assertEquals(atm.getSum(), 2600);
    }

    @Test
    public void shouldTakeBanknotes() throws NoNeededSumException {
        ATM atm = new ATM(
            new HashMap<>(
                Map.ofEntries(
                    Map.entry(Banknote.N50, 3),
                    Map.entry(Banknote.N100, 2),
                    Map.entry(Banknote.N200, 5),
                    Map.entry(Banknote.N500, 10)
                )
            )
        );

        Assert.assertEquals(atm.getSum(), 6350);

        atm.take(1350);
        Assert.assertEquals(atm.getSum(), 5000);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N50), 2);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N100), 1);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N200), 4);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N500), 8);
    }

    @Test(expected = NoNeededSumException.class)
    public void shouldFailForBadNumberOfBanknotes() throws NoNeededSumException {
        ATM atm = new ATM(
            new HashMap<>(
                Map.ofEntries(
                    Map.entry(Banknote.N50, 4),
                    Map.entry(Banknote.N100, 7),
                    Map.entry(Banknote.N200, 2),
                    Map.entry(Banknote.N500, 8)
                )
            )
        );

        Assert.assertEquals(atm.getSum(), 5300);

        atm.take(420);
    }

    @Test(expected = NoNeededSumException.class)
    public void shouldFailForTooBigSumOfBanknotes() throws NoNeededSumException {
        ATM atm = new ATM(
            new HashMap<>(
                Map.ofEntries(
                    Map.entry(Banknote.N50, 4),
                    Map.entry(Banknote.N100, 7),
                    Map.entry(Banknote.N200, 2),
                    Map.entry(Banknote.N500, 8)
                )
            )
        );

        Assert.assertEquals(atm.getSum(), 5300);

        atm.take(10_000);
    }
}