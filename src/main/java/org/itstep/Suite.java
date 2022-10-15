package org.itstep;


enum Suite {

    SPADES("Spades", "♠", 1),
    HEARTS("Hearts", "♥", 2),
    DIAMONDS("Diamonds", "♦", 3),
    TWO_CLUBS("Clubs", "♣", 4);


    private final String signSuite;
    private final String meanSuite;
    private final int priority;

    Suite(String meanSuite, String signSuite, int priority) {
        this.signSuite = signSuite;
        this.meanSuite = meanSuite;
        this.priority = priority;
    }

    String signSuite() {
        return signSuite;
    }

    String meanSuite() {
        return meanSuite;
    }

    int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("%8s: %-2s %1d", meanSuite, signSuite, priority);
    }
}

//    THREE_SPADES ( "Spades", "♠", "3", 2,1 ),
//    FOUR_SPADES ( "Spades", "♠", "4", 3,1 ),
//    FIVE_SPADES ( "Spades", "♠", "5", 4,1 ),
//    SIX_SPADES ( "Spades", "♠", "6", 5,1 ),
//    SEVEN_SPADES ( "Spades", "♠", "7", 6,1 ),
//    EIGTH_SPADES ( "Spades", "♠", "8", 7,1 ),
//    NINE_SPADES ( "Spades", "♠", "9", 8,1 ),
//    TEN_SPADES ( "Spades", "♠", "10", 9,1 ),
//    JACK_SPADES ( "Spades", "♠", "Jack", 10,1 ),
//    QUEEN_SPADES ( "Spades", "♠", "Queen", 11,1 ),
//    KING_SPADES ( "Spades", "♠", "King", 12,1 ),
//    ACE_SPADES ( "Spades", "♠", "Ace", 13,1 ),
//
//    TWO_HEARTS ( "Hearts", "♥", "2",1, 2 ),
//    THREE_HEARTS ( "Hearts", "♥", "3", 2,2 ),
//    FOUR_HEARTS ( "Hearts", "♥", "4", 3,2 ),
//    FIVE_HEARTS ( "Hearts", "♥", "5", 4,2 ),
//    SIX_HEARTS ( "Hearts", "♥", "6", 5,2 ),
//    SEVEN_HEARTS ( "Hearts", "♥", "7", 6,2 ),
//    EIGTH_HEARTS ( "Hearts", "♥", "8", 7,2 ),
//    NINE_HEARTS ( "Hearts", "♥", "9", 8,2 ),
//    TEN_HEARTS ( "Hearts", "♥", "10", 9,2 ),
//    JACK_HEARTS ( "Hearts", "♥", "Jack", 10,2 ),
//    QUEEN_HEARTS ( "Hearts", "♥", "Queen", 11,2 ),
//    KING_HEARTS ( "Hearts", "♥", "King", 12,2 ),
//    ACE_HEARTS ( "Hearts", "♥", "Ace", 13,2 ),
//
//    TWO_DIAMONDS ( "Diamonds", "♦", "2",1, 3 ),
//    THREE_DIAMONDS ( "Diamonds", "♦", "3", 2,3 ),
//    FOUR_DIAMONDS ( "Diamonds", "♦", "4", 3,3 ),
//    FIVE_DIAMONDS ( "Diamonds", "♦", "5", 4,3 ),
//    SIX_DIAMONDS ( "Diamonds", "♦", "6", 5,3 ),
//    SEVEN_DIAMONDS ( "Diamonds", "♦", "7", 6,3 ),
//    EIGTH_DIAMONDS ( "Diamonds", "♦", "8", 7,3 ),
//    NINE_DIAMONDS ( "Diamonds", "♦", "9", 8,3 ),
//    TEN_DIAMONDS ( "Diamonds", "♦", "10", 9,3 ),
//    JACK_DIAMONDS ( "Diamonds", "♦", "Jack", 10,3 ),
//    QUEEN_DIAMONDS ( "Diamonds", "♦", "Queen", 11,3 ),
//    KING_DIAMONDS ( "Diamonds", "♦", "King", 12,3 ),
//    ACE_DIAMONDS ( "Diamonds", "♦", "Ace", 13,3 ),
//
//    TWO_CLUBS ( "Clubs", "♣", "2",1, 4 ),
//    THREE_CLUBS ( "Clubs", "♣", "3", 2,4 ),
//    FOUR_CLUBS ( "Clubs", "♣", "4", 3,4 ),
//    FIVE_CLUBS ( "Clubs", "♣", "5", 4,4 ),
//    SIX_CLUBS ( "Clubs", "♣", "6", 5,4 ),
//    SEVEN_CLUBS ( "Clubs", "♣", "7", 6,4 ),
//    EIGTH_CLUBS ( "Clubs", "♣", "8", 7,4 ),
//    NINE_CLUBS ( "Clubs", "♣", "9", 8,4 ),
//    TEN_CLUBS ( "Clubs", "♣", "10", 9,4 ),
//    JACK_CLUBS ( "Clubs", "♣", "Jack", 10,4 ),
//    QUEEN_CLUBS ( "Clubs", "♣", "Queen", 11,4 ),
//    KING_CLUBS ( "Clubs", "♣", "King", 12,4 ),
//    ACE_CLUBS ( "Clubs", "♣", "Ace", 13,4 ),
//
//    JOKER_BLACK_AND_WHITE ( "Black and White", "BW", "Joker", 1,5 ),
//    JOKER_RED ( "Red",  "R", "Joker", 2,5 );