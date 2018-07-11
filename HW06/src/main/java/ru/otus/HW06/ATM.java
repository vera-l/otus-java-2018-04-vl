package ru.otus.HW06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ATM implements Observer {
    private final List<Slot> slots;
    private Map<Banknote, Integer> initState;
    private final List<Banknote> FACES_VALUES = Arrays.asList(Banknote.values());
    private final static String MONEY_SIGN = "\uD83D\uDCB5";
    private final static String PRINT_DELIMITER = "\n------------------\n";

    public ATM(HashMap<Banknote, Integer> initState) {
        slots = new ArrayList<>();
        this.initState = initState;
        FACES_VALUES.forEach(
            item -> slots.add(new Slot(item))
        );
        Collections.sort(
            slots,
            (slot1, slot2) -> slot1.getBanknote().getFaceValue() > slot2.getBanknote().getFaceValue() ? -1 : 1
        );
        reset();
    }

    public void reset() {
        for(Slot slot : slots) {
            Integer countFromInitState = this.initState.get(slot.getBanknote());
            slot.set(
                countFromInitState == null ? 0 : countFromInitState
            );
        }
    }

    @Override
    public void handle() {
        reset();
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
        try {
            getSlotForBanknote(banknote).put(count);
        } catch (SlotNotFoundException e) {
            System.out.println("Banknote is bad");
        }
    }

    private Slot getSlotForBanknote(Banknote banknote) throws SlotNotFoundException {
        Slot banknoteSlot = slots.stream().filter(
            slot -> slot.getBanknote() == banknote
        ).findAny().orElse(null);

        if(banknoteSlot == null) {
            throw new SlotNotFoundException("Неправильная банкнота");
        }

        return banknoteSlot;
    }

    public int getSlotBanknotesCount(Banknote banknote) {
        try {
            return getSlotForBanknote(banknote).getCount();
        } catch (SlotNotFoundException e) {
            System.out.println("Slot is not found");
            return 0;
        }

    }

    public int take(int sum) throws NoNeededSumException {
        if (sum > getSum()) {
            throw new NoNeededSumException(sum);
        }

        HashMap countToSlots = getCountToSlotsMapForSum(sum);

        for(Slot slot : slots) {
            Object i = countToSlots.get(slot);
            if (i == null) {
                continue;
            }
            slot.take((int) i);
        }

        return sum;
    }

    private boolean isPossibleToTakeSum(int sum) throws NoNeededSumException {
        if (sum > getSum()) {
            return false;
        }

        try {
            getCountToSlotsMapForSum(sum);
            return true;
        } catch (NoNeededSumException e) {
            return false;
        }
    }

    private HashMap<Slot, Integer> getCountToSlotsMapForSum(int sum) throws NoNeededSumException {
        HashMap<Slot, Integer> countToSlots = new HashMap<>();
        int initSum = sum;

        for(Slot slot : slots) {
            int faceValue = slot.getBanknote().getFaceValue();
            int count = sum / faceValue;

            if(count == 0) {
                continue;
            }

            if(count > slot.getCount()) {
                count = slot.getCount();
            }

            countToSlots.put(slot, count);
            sum -= faceValue * count;
        }

        if (sum != 0) {
            throw new NoNeededSumException(initSum);
        }

        return countToSlots;
    }

    public boolean isStateEquals(HashMap<Banknote, Integer> state) {
        return slots.stream().allMatch(
            slot -> slot.getCount() == (
                state.get(slot.getBanknote()) == null ? 0 : state.get(slot.getBanknote())
            )
        );
    }

    public String toString() {
        return PRINT_DELIMITER + String.join(
            "\n", slots.stream().map(
                item -> MONEY_SIGN + " " + item.toString()
            ).collect(Collectors.toList())
        )  + PRINT_DELIMITER + "BALANCE: " + getSum() + PRINT_DELIMITER;
    }
}
