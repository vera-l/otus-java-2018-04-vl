package ru.otus.HW06;

import org.junit.Assert;
import org.junit.Test;

public class SlotTests {

    @Test
    public void shouldPutAnyNumberOfBanknotes() {
        Slot slot = new Slot(Banknote.N50);
        slot.put(2);
        Assert.assertEquals(slot.getCount(), 2);
        slot.put(21);
        Assert.assertEquals(slot.getCount(), 21 + 2);
        Assert.assertEquals(slot.getSum(), 50 * 23);
    }

    @Test
    public void shouldTakeRightNumberOfBanknotes() throws NoNeededSumException {
        Slot slot = new Slot(Banknote.N2000);
        slot.put(15);
        Assert.assertEquals(slot.getCount(), 15);
        slot.take(7);
        Assert.assertEquals(slot.getCount(), 15 - 7);
        Assert.assertEquals(slot.getSum(), (15 - 7) * 2000);
    }

    @Test(expected = NoNeededSumException.class)
    public void shouldFailForTakeBigNumberOfBanknotes() throws NoNeededSumException {
        Slot slot = new Slot(Banknote.N500);
        slot.put(3);
        Assert.assertEquals(slot.getCount(), 3);
        slot.take(21);
    }
}
