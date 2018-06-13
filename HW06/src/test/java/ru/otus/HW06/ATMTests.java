package ru.otus.HW06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ATMTests {

    private ATM atm;

    @Before
    public void setup() {
        atm = new ATM();
    }

    @Test
    public void shouldPutAnyNumberOfBanknotes() {
        atm.put(Banknote.N50, 2);
        atm.put(Banknote.N100, 3);
        atm.put(Banknote.N200, 4);
        atm.put(Banknote.N500, 1);

        Assert.assertEquals(atm.getSum(), 1700);
    }

    @Test
    public void shouldTakeAnyNumberOfBanknotes() {
        atm.put(Banknote.N50, 3);
        atm.put(Banknote.N100, 2);
        atm.put(Banknote.N200, 5);
        atm.put(Banknote.N500, 10);

        Assert.assertEquals(atm.getSum(), 6350);

        atm.take(1350);
        Assert.assertEquals(atm.getSum(), 5000);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N50), 2);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N100), 1);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N200), 4);
        Assert.assertEquals(atm.getSlotBanknotesCount(Banknote.N500), 8);
    }

    @Test
    public void shouldFailForBadNumberOfBanknotes() {
        atm.put(Banknote.N50, 4);
        atm.put(Banknote.N100, 7);
        atm.put(Banknote.N200, 2);
        atm.put(Banknote.N500, 8);

        Assert.assertEquals(atm.getSum(), 5300);

        Assert.assertEquals(atm.take(420), -1);
        Assert.assertEquals(atm.getSum(), 5300);

        Assert.assertEquals(atm.take(10_000), -1);
        Assert.assertEquals(atm.getSum(), 5300);
    }
}