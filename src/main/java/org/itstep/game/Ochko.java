package org.itstep.game;

import org.itstep.*;

public class Ochko extends Games {
    private static final int DILLER_NORMA = 17;
    private static final double K_VICTORY = 1.5;
    private static final double OCHKO = 21;
    private Deck deck;
    private int totalPriorityPlayer;
    private int totalPriorityDiller;
    private Deck.Card[] playerCards = new Deck.Card[0];
    private Deck.Card[] dillerCards = new Deck.Card[0];

    public Ochko() {
        this("", 0, null, null, null);
    }

    public Ochko(String name, int bid, Player player, Diller diller, Deck deck) {
        super(name, bid, player, diller);
        this.deck = deck;
        this.totalPriorityPlayer = totalPriorityPlayer;
        this.totalPriorityDiller = totalPriorityDiller;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getTotalPriorityPlayer() {
        return totalPriorityPlayer;
    }

    public void setTotalPriorityPlayer(int totalPriorityPlayer) {
        this.totalPriorityPlayer = totalPriorityPlayer;
    }

    public int getTotalPriorityDiller() {
        return totalPriorityDiller;
    }

    public void setTotalPriorityDiller(int totalPriorityDiller) {
        this.totalPriorityDiller = totalPriorityDiller;
    }

    public Deck.Card[] getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(Deck.Card[] playerCards) {
        this.playerCards = playerCards;
    }

    public Deck.Card[] getDillerCards() {
        return dillerCards;
    }

    public void setDillerCards(Deck.Card[] dillerCards) {
        this.dillerCards = dillerCards;
    }


    // Печать игрового стола со скрытыми картами
    @Override
    public void printTableHidden() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(getName() + ", Ставка: " + getBid() + "$");
        System.out.println("------------------------------------------------------------------------------------");

        System.out.print(getDiller().toString());
        for (int i = 0; i < dillerCards.length; i++) {
            if (i == 0) {
                System.out.print(" | " + dillerCards[i].toString());
            } else {
                System.out.print(" | Не видно  ");
            }
        }
        System.out.println();
//        System.out.println(" |  Итого: " + totalPriorityDiller);

        System.out.print(getPlayer().toString());
        for (int i = 0; i < playerCards.length; i++) {
            System.out.print(" | " + playerCards[i].toString());
        }
        System.out.println(" |  Итого: " + totalPriorityPlayer);
//        deck.printDeck();
//        System.out.println("deck.cards.length = " + deck.cards.length);
    }

    // Печать игрового стола со открытыми картами
    @Override
    public void printTableOpen() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(getName() + ", Ставка: " + getBid() + "$");
        System.out.println("------------------------------------------------------------------------------------");

        System.out.print(getDiller().toString());
        for (int i = 0; i < dillerCards.length; i++) {
            System.out.print(" | " + dillerCards[i].toString());
        }
        System.out.println(" |  Итого: " + totalPriorityDiller);

        System.out.print(getPlayer().toString());
        for (int i = 0; i < playerCards.length; i++) {
            System.out.print(" | " + playerCards[i].toString());
        }
        System.out.println(" |  Итого: " + totalPriorityPlayer);
//        deck.printDeck();
//        System.out.println("deck.cards.length = " + deck.cards.length);
    }


    // Игра
    @Override
    public void game(String line) {
        // 1. Начальная стадия - 1 раз, в начале игры (название игры, создание игрока и диллера, ставка по умолчанию)
        preparation(line);
        printTableHidden();

        // 2. Начало игры - многократно повторяется (указанные методы выполняются последовательно, в описанном ниже порядке)
        // (создание колоды карт, возможность увеличить ставку, предварительная сдача карт игроку и диллеру)
        createDeck();                 // 2.1 Создание колоды карт
//        bidUp();                    // 2.2 Возможность увеличить ставку
//        startCards();               // 2.3 Начальные карты игрока и диллера
//        checkStartCards();          // 2.4 Проверка начальных карт игрока и диллера

        // 3. Игра (добавление карт игроку и диллеру, подсчет результата)
//        addCardPlayer();           // 3.1 Добавление карт игроку
//        addCardDiller();           // 3.2 Добавление карт диллеру
//        rezult ();                 // 3.3 Объявление результата
        // 4 Предложение о новой игре
//        newGameOffer ();

    }

    // 2.1 Создание колоды карт
    @Override
    public void createDeck() {
        deck = new Deck("Колода на 36 карт для игры Очко", Deck.DECK_36);
        Suite[] suites = Suite.values();
        Mean[] means = Mean.values();
        for (int i = 0; i < deck.getAmount(); i++) {
            label:
            for (Suite suite : suites) {
                for (Mean mean : means) {
                    int prior = 0;
                    if (mean.nameMean().equals("J")) {
                        prior = 2;
                    } else if (mean.nameMean().equals("Q")) {
                        prior = 3;
                    } else if (mean.nameMean().equals("K")) {
                        prior = 3;
                    } else if (mean.nameMean().equals("A")) {
                        prior = 11;
                    } else {
                        prior = mean.priorityMean();
                    }
                    Deck.Card card = new Deck.Card(suite, mean, prior);
                    if (deck.checkCard(card) &&
                            !card.getMean().nameMean().equals("2") &&
                            !card.getMean().nameMean().equals("3") &&
                            !card.getMean().nameMean().equals("4") &&
                            !card.getMean().nameMean().equals("5") &&
                            !card.getMean().nameMean().equals("Jr")) {
                        deck.addCard(card);
                        break label;
                    }

                }
            }
        }
//        deck.printDeck();
        deck.sortRandom();          // Сортировка по убыванию / возрастанию (Random) (Для лучшего перемешивания)
        deck.shuffle();             // Перемешивание карт
//        deck.printDeck();
        bidUp();
        startCards();
    }

    @Override
    public void startCards() {

    }

    @Override
    public void checkStartCards() {

    }

    @Override
    public void addCardPlayer() {

    }

    @Override
    public Deck.Card[] addPlayerCard(Deck.Card newCard) {
        return new Deck.Card[0];
    }

    @Override
    public void addCardDiller() {

    }

    @Override
    public Deck.Card[] addDillerCard(Deck.Card newCard) {
        return new Deck.Card[0];
    }

    @Override
    public void newGameOffer() {

    }


}
