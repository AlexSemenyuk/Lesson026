package org.itstep;


enum Suite {

    SPADES("Spades", "♠", 100),
    HEARTS("Hearts", "♥", 200),
    DIAMONDS("Diamonds", "♦", 300),
    CLUBS("Clubs", "♣", 400),
    FOR_JOCKER_BW("Black and White", "B", 500),
    FOR_JOCKER_R("Red", "R", 500);

    private final String nameSuite;
    private final String signSuite;
    private final int prioritySuite;

    Suite(String nameSuite, String signSuite, int prioritySuite) {
        this.signSuite = signSuite;
        this.nameSuite = nameSuite;
        this.prioritySuite = prioritySuite;
    }

    String signSuite() {
        return signSuite;
    }

    String nameSuite() {
        return nameSuite;
    }

    int prioritySuite() {
        return prioritySuite;
    }

    @Override
    public String toString() {
        return String.format("%8s: %-2s %1d", nameSuite, signSuite, prioritySuite);
    }
}

