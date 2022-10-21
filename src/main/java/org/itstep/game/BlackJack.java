package org.itstep.game;

import org.itstep.*;

import java.util.Arrays;
import java.util.Scanner;

public class BlackJack extends Games {
    private static final int DILLER_NORMA = 17;
    private static final double K_VICTORY = 1.5;
    private static final int START_MEMBERS_CARDS = 2;
    private static final double BLACK_JACK = 21;
    private static final Scanner scanner = new Scanner(System.in);
    private Deck deck;
    private int totalPriorityPlayer;
    private int totalPriorityDiller;
    private Deck.Card[] playerCards = new Deck.Card[0];
    private Deck.Card[] dillerCards = new Deck.Card[0];

    public BlackJack() {
        this("", 0, null, null, null);
    }

    public BlackJack(String name, int bid, Player player, Diller diller, Deck deck) {
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
        System.out.println("\n\n\n");
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
        System.out.println("\n\n\n");
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
        deck = new Deck("Колода на 52 карты для игры Black Jack", Deck.DECK_52);
        Suite[] suites = Suite.values();
        Mean[] means = Mean.values();
        for (int i = 0; i < deck.getAmount(); i++) {
            label:
            for (Suite suite : suites) {
                for (Mean mean : means) {
                    int prior = 0;
                    if (mean.nameMean().equals("J") || mean.nameMean().equals("Q") || mean.nameMean().equals("K")) {
                        prior = 10;
                    } else if (mean.nameMean().equals("A")) {
                        prior = 11;
                    } else {
                        prior = mean.priorityMean();
                    }
                    Deck.Card card = new Deck.Card(suite, mean, prior);
                    if (deck.checkCard(card) && !card.getMean().nameMean().equals("Jr")) {
                        deck.addCard(card);
                        break label;
                    }

                }
            }
        }
//            deck.printDeck();
        deck.sortRandom();          // Сортировка по убыванию / возрастанию (Random) (Для лучшего перемешивания)
        deck.shuffle();             // Перемешивание карт
//        deck.printDeck();
        bidUp();
        startCards();
    }


    // 2.3 Начальные карты игрока и диллера
    @Override
    public void startCards() {
        totalPriorityPlayer = 0;            // Обнуление суммарных приоритетов (веса карт) игрока и диллера
        totalPriorityDiller = 0;
        playerCards = new Deck.Card[0];     // Обнуление карт у игрока и диллера
        dillerCards = new Deck.Card[0];
        for (int i = 0; i < START_MEMBERS_CARDS * 2; i++) {
            if (i < START_MEMBERS_CARDS) {
                playerCards = Arrays.copyOf(playerCards, playerCards.length + 1);
                playerCards[playerCards.length - 1] = deck.getCards()[0];
                totalPriorityPlayer += deck.getCards()[0].getPriority();
//                System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
                deck.subOneCard();
            } else {
                dillerCards = Arrays.copyOf(dillerCards, dillerCards.length + 1);
                dillerCards[dillerCards.length - 1] = deck.getCards()[0];
                totalPriorityDiller += deck.getCards()[0].getPriority();
//                System.out.println("totalPriorityDiller = " + totalPriorityDiller);
                deck.subOneCard();
            }
        }
//        deck.printDeck();
        printTableHidden();
        checkStartCards();
    }

    // 2.4 Проверка начальных карт игрока и диллера
    @Override
    public void checkStartCards() {
        if (totalPriorityPlayer > BLACK_JACK) {             // Изменение значения туза (11 -> 1) при переборе
//            changeAcePriorityPlayer();
            System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
            totalPriorityPlayer = 0;
            for (Deck.Card card : playerCards) {
                if (card.getMean().nameMean().equals("A")) {
                    card.setPriority(1);
                }
                totalPriorityPlayer += card.getPriority();
            }
            System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
        } else if (totalPriorityDiller > BLACK_JACK) {      // Изменение значения туза (11 -> 1) при переборе
            System.out.println("totalPriorityDiller = " + totalPriorityDiller);
//            changeAcePriorityDiller();
            totalPriorityDiller = 0;
            for (Deck.Card card : dillerCards) {
                if (card.getMean().nameMean().equals("A")) {
                    card.setPriority(1);
                }
                totalPriorityDiller += card.getPriority();
            }
            System.out.println("totalPriorityDiller = " + totalPriorityDiller);
        }
        if (totalPriorityPlayer == BLACK_JACK) {
            if (dillerCards[0].getPriority() != 10 && dillerCards[0].getPriority() != 11) {
                System.out.println("У вас Black Jack, а у нас НЕТ!!! Вы сорвали банк");
                addBlackJackPlayer();
                newGameOffer();            // Предложение о новой игре
            } else {
                label4:
                while (true) {
                    System.out.print("У диллера тоже возможен Black Jack (1 - Заберете приз (1:1) сейчас, 2 - Проверим Вашу удачу(приз 3:2)) >>> ");
                    int n = scanner.nextInt();
                    switch (n) {
                        case 1:
                            addBidPlayer();
                            newGameOffer();            // Предложение о новой игре
                            break label4;
                        case 2:
                            if (totalPriorityDiller == BLACK_JACK) {
                                System.out.println("Вам не повезло - каждый при своих");
                                printTableOpen();
                                newGameOffer();            // Предложение о новой игре
                                break label4;
                            } else {
                                System.out.println("Вы сорвали банк");
                                addBlackJackPlayer();
                                newGameOffer();            // Предложение о новой игре
                                break label4;
                            }
                        default:
                            System.out.println("Вы неправильно ввели данные");
                            break;
                    }
                }
            }
        } else if (totalPriorityDiller == BLACK_JACK) {
            System.out.println("У диллера Black Jack! Ставка у диллера! ");
            addBidDiller();
            newGameOffer();            // Предложение о новой игре
        } else if (totalPriorityPlayer < BLACK_JACK && totalPriorityDiller < BLACK_JACK) {
            addCardPlayer();            // 3.1 Добавление карт игроку
        } else {
            System.err.println("Ошибка");
        }
    }


    // 3.1 Добавление карт игроку
    @Override
    public void addCardPlayer() {
        label5:
        while (true) {
            System.out.print("Вы желаете получить дополнительную карту (1 - да, 2 - нет): >>> ");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    addPlayerCard(deck.getCards()[0]);
                    totalPriorityPlayer += deck.getCards()[0].getPriority();
                    printTableHidden();
                    // Изменение значения туза (11 -> 1) при переборе
                    if (totalPriorityPlayer > BLACK_JACK) {
//                        changeAcePriorityPlayer();
                        System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
                        totalPriorityPlayer = 0;
                        for (Deck.Card card : playerCards) {
                            if (card.getMean().nameMean().equals("A")) {
                                card.setPriority(1);
                            }
                            totalPriorityPlayer += card.getPriority();
                        }
                        System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
                    }
                    if (totalPriorityPlayer > BLACK_JACK) {
                        System.out.println("У Вас перебор! Банк у диллера!");
                        addBidDiller();
                        newGameOffer();            // Предложение о новой игре
                        break label5;
                    }
                    deck.subOneCard();
                    break;
                case 2:
                    addCardDiller();            // 3.2 Добавление диллеру карты
                    break label5;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }
        addCardDiller();
    }

    // 3.1.1 Добавление игроку карты
    @Override
    public Deck.Card[] addPlayerCard(Deck.Card newCard) {
        playerCards = Arrays.copyOf(playerCards, playerCards.length + 1);
        playerCards[playerCards.length - 1] = newCard;
        return playerCards;
    }


    // 3.2 Добавление диллеру карты
    @Override
    public void addCardDiller() {
        label6:
        while (true) {
            if (totalPriorityDiller >= DILLER_NORMA) {
                rezult();                  // 3.3 Объявление результата
                break label6;
            } else {
                addDillerCard(deck.getCards()[0]);
                totalPriorityDiller += deck.getCards()[0].getPriority();
                printTableOpen();
                // Изменение значения туза (11 -> 1) при переборе
                if (totalPriorityDiller > BLACK_JACK) {
//                    changeAcePriorityDiller();
                    System.out.println("totalPriorityDiller = " + totalPriorityDiller);
                    totalPriorityDiller = 0;
                    for (Deck.Card card : playerCards) {
                        if (card.getMean().nameMean().equals("A")) {
                            card.setPriority(1);
                        }
                        totalPriorityDiller += card.getPriority();
                    }
                    System.out.println("totalPriorityDiller = " + totalPriorityDiller);
                }
//                printTableOpen();       //
                if (totalPriorityDiller > BLACK_JACK) {
                    System.out.println("Перебор у диллера! Банк Ваш!");
                    addBidPlayer();
                    newGameOffer();            // Предложение о новой игре
                    break label6;
                } else {
                    deck.subOneCard();
                    break;
                }
            }

        }
    }


    // 3.2.1 Добавление карты диллера
    @Override
    public Deck.Card[] addDillerCard(Deck.Card newCard) {
        dillerCards = Arrays.copyOf(dillerCards, dillerCards.length + 1);
        dillerCards[dillerCards.length - 1] = newCard;
        return dillerCards;
    }


    // 3.3 Объявление результата
    public void rezult() {
        if (totalPriorityPlayer == BLACK_JACK && totalPriorityDiller != BLACK_JACK) {
            System.out.println("У вас Black Jack, а у нас НЕТ!!! Вы сорвали банк");
            addBlackJackPlayer();
            newGameOffer();            // Предложение о новой игре
        } else if (totalPriorityPlayer != BLACK_JACK && totalPriorityDiller == BLACK_JACK) {
            System.out.println("У диллера Black Jack! Ставка у диллера! ");
            addBidDiller();
            newGameOffer();            // Предложение о новой игре
        } else if (totalPriorityDiller == totalPriorityPlayer) {
            System.out.println("Ровно - каждый при своих");
            printTableOpen();
        } else if (totalPriorityDiller > totalPriorityPlayer) {
            System.out.println("Ставка у диллера");
            addBidDiller();
        } else {
            System.out.println("Ставка у игрока");
            addBidPlayer();
        }
        newGameOffer();            // Предложение о новой игре
    }

    // Предложение о новой игре
    @Override
    public void newGameOffer() {
        int count = 0;
        if (getPlayer().getBank() >= Games.BID_MIN && getDiller().getBank() >= DILLER_BANK_MIN && count == 0) {

            while (count == 0) {
                System.out.print("Скатаем еще партейку (1 - да, 2 - нет, пойду домой с тем, что осталось) >>> ");
                int n = scanner.nextInt();
                switch (n) {
                    case 1:
                        createDeck();
                        break;
                    case 2:
                        System.out.println("Хорошего вечера");
                        count++;
                        break;
                    default:
                        System.out.println("Вы неправильно ввели данные");
                        break;
                }
            }
        } else if (getPlayer().getBank() < BID_MIN) {
            System.out.println("Будем рады сыграть, когда у Вас будут деньги");
        } else if (getDiller().getBank() >= DILLER_BANK_MIN) {
            System.out.println("Сори, деньги у банка на исходе");
        }

    }


    // Black Jack у игрока
    public void addBlackJackPlayer() {
        getDiller().setBank(getDiller().getBank() - (int) (getBid() * K_VICTORY));
        getPlayer().setBank(getPlayer().getBank() + (int) (getBid() * K_VICTORY));
        printTableOpen();
    }


    // Ставка у игрока
    public void addBidPlayer() {
        getDiller().setBank(getDiller().getBank() - getBid());
        getPlayer().setBank(getPlayer().getBank() + getBid());
        printTableOpen();
    }


    // Ставка у диллера
    public void addBidDiller() {
        getDiller().setBank(getDiller().getBank() + getBid());
        getPlayer().setBank(getPlayer().getBank() - getBid());
        printTableOpen();
    }


    // Изменение значения туза (11 -> 1) при переборе
//    public void changeAcePriorityPlayer() {
//        System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
//        for (Deck.Card card : playerCards) {
//            if (card.getMean().nameMean().equals("A")) {
//                card.setPriority(1);
//            }
//        }
//        totalPriorityPlayer = 0;
//        for (Deck.Card card : playerCards) {
//            totalPriorityPlayer += card.getPriority();
//        }
//        System.out.println("totalPriorityPlayer = " + totalPriorityPlayer);
//    }

    // Изменение значения туза (11 -> 1) при переборе
//    public void changeAcePriorityDiller() {
//        System.out.println("totalPriorityDiller = " + totalPriorityDiller);
//        for (Deck.Card card : dillerCards) {
//            if (card.getMean().nameMean().equals("A")) {
//                card.setPriority(1);
//            }
//        }
//        totalPriorityPlayer = 0;
//        for (Deck.Card card : playerCards) {
//            totalPriorityDiller += card.getPriority();
//        }
//        System.out.println("totalPriorityDiller = " + totalPriorityDiller);
//    }
}
