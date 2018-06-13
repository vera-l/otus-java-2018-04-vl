package ru.otus.HW06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class ATM {
    private ArrayList<Slot> slots;
    private List<Banknote> FACES_VALUES = Arrays.asList(Banknote.values());
    private final static String MONEY_SIGN = "\uD83D\uDCB5";
    private final static String ERROR_SIGN = "\uD83D\uDEAB";
    private final static String PRINT_DELIMITER = "\n------------------\n";

    ATM() {
        slots = new ArrayList<>();
        FACES_VALUES.forEach(
            item -> slots.add(new Slot(item))
        );
        Collections.sort(
            slots,
            (slot1, slot2) -> slot1.getBanknote().getFaceValue() > slot2.getBanknote().getFaceValue() ? -1 : 1
        );
    }

    public int getSum() {
        return slots.stream().map(
            slot -> slot.getSum()
        ).reduce(
            0, (sum1, sum2) -> sum1 + sum2
        );
    }

    public void put(Banknote banknote) {
        put(banknote, 1);
    }

    public void put(Banknote banknote, int count) {
        getSlotForBanknote(banknote).put(count);
    }

    private Slot getSlotForBanknote(Banknote banknote) {
        Slot banknoteSlot = slots.stream().filter(
            slot -> slot.getBanknote() == banknote
        ).findAny().orElse(null);

        if(banknoteSlot == null) {
            throw new Error("Неправильная банкнота");
        }

        return banknoteSlot;
    }

    public int getSlotBanknotesCount(Banknote banknote) {
        return getSlotForBanknote(banknote).getCount();
    }

    public int take(int sum) {
        try {
            if (!isPossibleToTakeSum(sum)) {
                System.out.println(ERROR_SIGN + " Нельзя выдать данную сумму ("
                    + String.valueOf(sum) + ") " + ERROR_SIGN);
                return -1;
            }

            getRemainsAfterIterateOverSlots(sum, true);
        } catch (NoNeededSumException e) {
            return -1;
        }

        return sum;
    }

    private boolean isPossibleToTakeSum(int sum) throws NoNeededSumException {
        if (sum > getSum()) {
            return false;
        }

        return getRemainsAfterIterateOverSlots(sum, false) == 0;
    }

    private int getRemainsAfterIterateOverSlots(int sum, boolean isExecute) throws NoNeededSumException {
        for(Slot slot : slots) {
            int faceValue = slot.getBanknote().getFaceValue();
            int count = sum / faceValue;

            if(count == 0) {
                continue;
            }

            if(count > slot.getCount()) {
                count = slot.getCount();
            }

            if(isExecute) {
                slot.take(count);
            }

            sum -= faceValue * count;
        }

        return sum;
    }

    public String toString() {
        return PRINT_DELIMITER + String.join(
            "\n", slots.stream().map(
                item -> MONEY_SIGN + " " + item.toString()
            ).collect(Collectors.toList())
        )  + PRINT_DELIMITER + "BALANCE: " + getSum() + PRINT_DELIMITER;
    }
}
