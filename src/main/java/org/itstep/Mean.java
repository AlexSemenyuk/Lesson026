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
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A"),
    JOKER_BW(15, "Jr"),
    JOKER_R(16, "Jr");

    private final int priorityMean;
    private final String nameMean;

    Mean(int priorityMean, String nameMean) {
        this.priorityMean = priorityMean;
        this.nameMean = nameMean;
    }

    int priorityMean() {
        return priorityMean;
    }

    String nameMean() {
        return nameMean;
    }

    @Override
    public String toString() {
        return String.format("%2d: %-7s", priorityMean, nameMean);
    }
}