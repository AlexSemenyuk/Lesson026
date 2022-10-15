package org.itstep;

enum Mean {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGTH(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "Jack"),
    QUEEN(12, "Queen"),
    KING(13, "King"),
    ACE(14, "Ace"),
    JOKER_BW(501, "JokerBW"),
    JOKER_RED(502, "JokerR");

    private final int num;
    private final String meanCard;

    Mean(int num, String meanCard) {
        this.num = num;
        this.meanCard = meanCard;
    }

    int num() {
        return num;
    }

    String meanCard() {
        return meanCard;
    }

    @Override
    public String toString() {
        return String.format("%2d: %-7s", num, meanCard);
    }
}