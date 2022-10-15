package org.itstep;

import java.util.Arrays;
import java.util.Iterator;


public class Deck implements Iterable {
    private String nameDeck;
    private int amount;
    Card[] cards = new Card[0];

    public Deck() {
        this("Колода 36 карт", 36);
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

    // Создание колоды
    public void addDeck() {
        Suite[] suites = Suite.values();
        Mean[] means = Mean.values();
        for (int i = 0; i < amount; i++) {
            label:
            for (Suite suite : suites) {
                for (Mean mean : means) {
                    Card card = new Card(mean.meanCard(), suite.signSuite(), suite.meanSuite(), suite.priority() * 100 + mean.num());
                    if (amount == 54 && i < 52 &&
                            checkCard(card) &&
                            !card.getMean().equals("JokerBW") &&
                            !card.getMean().equals("JokerR")) {
                        addCard(card);
                        break label;
                    }
                    if (amount == 36 && checkCard(card) &&
                            !card.getMean().equals("2") &&
                            !card.getMean().equals("3") &&
                            !card.getMean().equals("4") &&
                            !card.getMean().equals("5") &&
                            !card.getMean().equals("JokerBW") &&
                            !card.getMean().equals("JokerR")) {
                        addCard(card);
                        break label;
                    }

                }
            }
        }
        if (amount == 54) {
            addCard(new Card(Mean.JOKER_BW.meanCard(), "", "BW", Mean.JOKER_BW.num()));
            addCard(new Card(Mean.JOKER_RED.meanCard(), "", "Red", Mean.JOKER_RED.num()));
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


    @Override
    public Iterator iterator() {
        return new DeckIterator(cards);
    }


    // Вывод колоды в консоль
    public void printDeck() {
        System.out.println(nameDeck);
        int n = 1;
        Card[] ss = cards;
        for (Card card : cards) {
            System.out.println(n + ":" + card.toString());
            n++;
        }
    }

    public void shuffle() {
        CardComparatorForShuffle comparatorForShuffle = new CardComparatorForShuffle();
        Arrays.sort(cards, comparatorForShuffle);
    }

}


class DeckIterator implements Iterator {
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



