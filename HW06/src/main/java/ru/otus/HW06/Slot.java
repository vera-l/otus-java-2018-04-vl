package ru.otus.HW06;

public class Slot {
    private Banknote banknote;
    private int count;

    public Slot(Banknote banknote) {
        this.banknote = banknote;
        this.count = 0;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return count;
    }

    public void put(int itemsCount) {
        if (itemsCount < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.count += itemsCount;
    }

    public void set(int itemsCount) {
        if (itemsCount < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.count = itemsCount;
    }

    public void take(int itemsCount) throws NoNeededSumException {
        if (this.count < itemsCount) {
            throw new NoNeededSumException();
        }
        this.count -= itemsCount;
    }

    public int getSum() {
        return banknote.getFaceValue() * count;
    }

    public String toString() {
        return String.format(
            "%-4s x %s",
            String.valueOf(banknote),
            String.valueOf(count)
        );
    }

}
