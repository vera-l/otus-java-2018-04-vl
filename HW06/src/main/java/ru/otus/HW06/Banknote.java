package ru.otus.HW06;

enum Banknote {

    N50(50),
    N100(100),
    N200(200),
    N500(500),
    N1000(1000),
    N2000(2000),
    N5000(5000);

    private final int faceValue;

    Banknote(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public String toString() {
        return String.valueOf(faceValue);
    }

}