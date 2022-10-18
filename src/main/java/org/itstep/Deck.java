package org.itstep;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Deck implements Iterable {

   static class DeckIterator implements Iterator {
        private final Card[] cards;
        private int index;
        public DeckIterator(Card[] cards) {
            this.cards = cards;
            this.index = -1;
        }
        @Override
        public boolean hasNext() {
            return ++index < cards.length;
        }
        @Override
        public Object next() {
            return cards[index];
        }
    }

    public static class Card implements Comparable {

        // Comparator для сортировки по возрастанию (учитывается приоритет Suite и Meam)
        static class CardComparatorByUp implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Card) o1).suite.prioritySuite() + ((Card) o1).mean.priorityMean()
                        - ((Card) o2).suite.prioritySuite() - ((Card) o2).mean.priorityMean();
            }
        }
        // Comparator для сортировки по убыванию (учитывается приоритет Suite и Meam)
        static class CardComparatorByDown implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Card) o2).suite.prioritySuite() + ((Card) o2).mean.priorityMean()
                        - ((Card) o1).suite.prioritySuite() - ((Card) o1).mean.priorityMean();
            }
        }

        // Comparator для перемешивания карт
        static class CardComparatorForShuffle implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                Random random = new Random();
                return random.nextInt(3) - 1;
            }
        }
        private final Suite suite;
        private final Mean mean;
        private int priority;

        public Card(Suite suite, Mean mean, int priority) {
            this.suite = suite;
            this.mean = mean;
            this.priority = priority;
//            priority = suite.prioritySuite() + mean.priorityMean();
        }

        public Suite getSuite() {
            return suite;
        }

        public Mean getMean() {
            return mean;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }


        @Override
        public String toString() {
            return String.format("%2s:%-2s p:%-2d", mean.nameMean(), suite.signSuite(), priority);
        }

        @Override
        public int compareTo(Object o) {
            return priority - ((Card) o).getPriority();
        }
    }

    public static final int DECK_36 = 36;       // Игра в дурака, очко и др.
    public static final int DECK_52 = 52;       // Black Jack
    public static final int DECK_54 = 54;

    private String nameDeck;
    private int amount;
    Card[] cards = new Card[0];

    public Deck() {
        this("Колода 36 карт", DECK_36);
    }

    public Deck(String nameDeck, int amount) {
        this.nameDeck = nameDeck;
        this.amount = amount;
    }

    public String getNameDeck() {
        return nameDeck;
    }

    public void setNameDeck(String nameDeck) {
        this.nameDeck = nameDeck;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] decks) {
        this.cards = cards;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // Создание классической колоды (игры дурак и др.)
    public void addDeckClassic() {
        Suite[] suites = Suite.values();
        Mean[] means = Mean.values();

        for (int i = 0; i < amount; i++) {
            label:
            for (Suite suite : suites) {
                for (Mean mean : means) {
                    Card card = new Card(suite, mean, suite.prioritySuite() + mean.priorityMean());
                    if (amount == DECK_54 && i < DECK_54 - 2 &&
                            checkCard(card) &&
                            !card.getMean().nameMean().equals("Jr") ) {
                        addCard(card);
                        break label;
                    }
                    if (amount == 36 && checkCard(card) &&
                            !card.getMean().nameMean().equals("2") &&
                            !card.getMean().nameMean().equals("3") &&
                            !card.getMean().nameMean().equals("4") &&
                            !card.getMean().nameMean().equals("5") &&
                            !card.getMean().nameMean().equals("Jr") ) {
                        addCard(card);
                        break label;
                    }

                }
            }
        }
        if (amount == DECK_54) {
            addCard(new Card(Suite.FOR_JOCKER_BW, Mean.JOKER_BW, Suite.FOR_JOCKER_BW.prioritySuite() + Mean.JOKER_BW.priorityMean() ) );
            addCard(new Card(Suite.FOR_JOCKER_R, Mean.JOKER_R, Suite.FOR_JOCKER_R.prioritySuite() + Mean.JOKER_R.priorityMean() ) );
        }
    }

    // Создание колоды BlackJack или "Очко"
    public void addDeckBlackJack() {
        Suite[] suites = Suite.values();
        Mean[] means = Mean.values();

        for (int i = 0; i < amount; i++) {
            label:
            for (Suite suite : suites) {
                for (Mean mean : means) {
                    int prior = 0;
                    if (mean.nameMean().equals("J") ||
                            mean.nameMean().equals("Q") ||
                            mean.nameMean().equals("K")){
                        prior = 10;
                    } else if (mean.nameMean().equals("A")) {
                        prior = 11;
                    } else {
                        prior = mean.priorityMean();
;                    }

                    Card card = new Card(suite, mean, prior);
                    if (amount == DECK_52 && checkCard(card) &&
                            !card.getMean().nameMean().equals("Jr") ) {
                        addCard(card);
                        break label;
                    }
                    if (amount == DECK_36 && checkCard(card) &&
                            !card.getMean().nameMean().equals("2") &&
                            !card.getMean().nameMean().equals("3") &&
                            !card.getMean().nameMean().equals("4") &&
                            !card.getMean().nameMean().equals("5") &&
                            !card.getMean().nameMean().equals("Jr") ) {
                        addCard(card);
                        break label;
                    }
                }
            }
        }
    }

    // Добавление карты
    public Card[] addCard(Card newCard) {
        cards = Arrays.copyOf(cards, cards.length + 1);
        cards[cards.length - 1] = newCard;
        return cards;
    }

    // Проверка аналогичной карты на отсутствие в колоде (проверка Ок - true)
    public boolean checkCard(Card newCard) {
        boolean rezult = false;
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getMean().equals(newCard.getMean()) && cards[i].getSuite().equals(newCard.getSuite())) {
                return rezult;
            } else {
                count++;
            }
        }
        if (count == cards.length) {     // Учтен случай с cards.length = 0
            rezult = true;
        }
        return rezult;
    }

    // Убрать первую карту из колоды
    public Card[] subOneCard() {
        cards = Arrays.copyOfRange(cards, 1, cards.length-1);
        return cards;
    }

    @Override
    public Iterator iterator() {
        return new DeckIterator(cards);
    }

    // Вывод колоды в консоль
    public void printDeck() {
        System.out.println(nameDeck);
        int n = 1;
//        Card[] ss = cards;
        for (Card card : cards) {
            System.out.println(n + ": " + card.toString());
            n++;
        }
    }

    // Сортировка по возрастанию (Учитывается приоритет Suite и Mean)
    public void sortUp() {
        Card.CardComparatorByUp comparatorByUp = new Card.CardComparatorByUp();
        Arrays.sort(cards, comparatorByUp);
    }

    // Сортировка по убыванию (Учитывается приоритет Suite и Mean)
    public void sortDown() {
        Card.CardComparatorByDown comparatorByDown = new Card.CardComparatorByDown();
        Arrays.sort(cards, comparatorByDown);
    }

    // Сортировка по убыванию / возрастанию (Random)
    public void sortRandom() {
        Random random1 = new Random();
        int num = random1.nextInt(2);
        if (num == 0) {
            sortUp();
        } else {
            sortDown();
        }
    }

    public void shuffle() {
        Card.CardComparatorForShuffle comparatorForShuffle = new Card.CardComparatorForShuffle();
        Arrays.sort(cards, comparatorForShuffle);
    }
}






