package org.itstep;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class GameTable {
    private static final int BID_MIN = 20;
    private static final double K_VICTORY = 1.5;
    private String name;        // Например "Стол №1"
    private int bid;            // Ставка
    private Player player;
    private Diller diller;
    private Deck deck;
    Deck.Card[] playerCards = new Deck.Card[0];
    Deck.Card[] dillerCards = new Deck.Card[0];

    GameTable() {
        this("", 0, null, null, null);
    }

    GameTable(String name, int bid, Player player, Diller diller, Deck deck) {
        this.name = name;
        this.bid = bid;
        this.player = player;
        this.diller = diller;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
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

    @Override
    public String toString() {
        return String.format("%-10s: %d ", name, bid);
    }

    public void printTable() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(name + ", Ставка: " + bid + "$");
        int totalPriorityPlayer = 0;
        int totalPriorityDiller = 0;
        System.out.println("------------------------------------------------------------------------------------");

        System.out.print(diller.toString());
        for (int i = 0; i < dillerCards.length; i++) {
            if (i == 0) {
                System.out.print(" | " + dillerCards[i].toString());
                totalPriorityDiller += playerCards[i].getPriority();
            } else {
                System.out.print(" | Не видно " );
                totalPriorityDiller += playerCards[i].getPriority();
            }
        }
        System.out.println(" |  Итого: " + totalPriorityDiller);

        System.out.print(player.toString());
        for (int i = 0; i < playerCards.length; i++) {
            if (i == 0) {
                System.out.print(" | " + playerCards[i].toString());
                totalPriorityPlayer += playerCards[i].getPriority();
            } else {
                System.out.print(" | " + playerCards[i].toString());
                totalPriorityPlayer += playerCards[i].getPriority();
            }
        }
        System.out.println(" |  Итого: " + totalPriorityPlayer);
    }

    public void preparationStart(String line) {
        Scanner scanner1 = new Scanner(System.in);
        name = line;
        bid = BID_MIN;      // Ставка по умолчанию
        // Создание игрока
        player = new Player();
//        System.out.println(player.toString());
        // Создание диллера / казино
        diller = new Diller();
//        System.out.println(diller.toString());
        printTable();
    }

    public String preparation() {
        // 1. Создание колоды карт
        if (name.equals("Black Jack")) {
            deck = new Deck("Колода на 52 карты для игры Black Jack", Deck.DECK_52);
            deck.addDeckBlackJack();
//            deck.printDeck();
        } else {
            deck = new Deck("Колода на 36 карт для игры Очко", Deck.DECK_36);
            deck.addDeckBlackJack();
//            deck.printDeck();
        }
        // 2. Возможность увеличить ставку
        bidUp();
        // 3. Предварительная сдача карт игроку и диллеру
        deck.sortRandom();
        deck.shuffle();
        int totalPriorityPlayer = 0;
        int totalPriorityDiller = 0;
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                playerCards = Arrays.copyOf(playerCards, playerCards.length + 1);
                playerCards[playerCards.length - 1] = deck.cards[i];
                totalPriorityPlayer += deck.cards[i].getPriority();
                deck.subOneCard();
            } else {
                dillerCards = Arrays.copyOf(dillerCards, dillerCards.length + 1);
                dillerCards[dillerCards.length - 1] = deck.cards[i];
                totalPriorityDiller += deck.cards[i].getPriority();
                deck.subOneCard();
            }
        }
        printTable();
        return totalPriorityDiller + " " + totalPriorityPlayer;
    }


    // Цикл игры
    public void gameBlackJack() {
        Scanner scanner3 = new Scanner(System.in);
        // 1. Подготовка (создание колоды карт, возможность увеличить ставку, предварительная сдача карт игроку и диллеру)
        String [] line = preparation().split(" ");
        int totalPriorityDiller = Integer.parseInt(line[0]);
        int totalPriorityPlayer = Integer.parseInt(line[1]);

        // 2. Проверка первоначально розданных карт
        if (totalPriorityPlayer == 21 && dillerCards[0].getPriority() != 10){
            System.out.println("У вас Black Jack, а у нас НЕТ");
            diller.setBank(diller.getBank() - (int) (bid * K_VICTORY) );
            player.setBank(player.getBank() + (int) (bid * K_VICTORY) );
        } else if (totalPriorityPlayer == 21) {
            lebel4:
            while (true) {
                System.out.println("У вас Black Jack, а у нас - возможно (1 - Ваш приз + " + bid + "$, 2 - проверим нашу удачу)");
                int n = scanner3.nextInt();
                switch (n) {
                    case 1:
                        diller.setBank(diller.getBank() - bid);
                        player.setBank(player.getBank() + bid);
                        break lebel4;
                    case 2:
                        if (totalPriorityDiller == 21){
                            System.out.println("Вам не повезло - каждый при своих");
                            break lebel4;
                        } else {
                            diller.setBank(diller.getBank() - (int) (bid * K_VICTORY) );
                            player.setBank(player.getBank() + (int) (bid * K_VICTORY) );
                            break lebel4;
                        }
                    default:
                        System.out.println("Вы неправильно ввели данные");
                        break;
                }
            }
        }
        // 3. Добавление карт игроку
        lebel5:
        while (true) {
            System.out.print("Вы желаете получить дополнительную карту (1 - да, 2 - нет): >>> ");
            int m = scanner3.nextInt();
            switch (m) {
                case 1:
                    addPlayerCard(deck.cards[0]);
                    totalPriorityPlayer += deck.cards[0].getPriority();
                    if (totalPriorityPlayer > 21){
                        System.out.println("Перебор");
                        diller.setBank(diller.getBank() + bid );
                        player.setBank(player.getBank() - bid );
                        break lebel5;
                    }
                    deck.subOneCard();
                    printTable();
                    break;
                case 2:
                        break lebel5;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }

        // 3. Добавление карт диллеру
        lebel6:
        while (true && totalPriorityPlayer <= 21) {
            if  (totalPriorityDiller < 17) {
                addDillerCard(deck.cards[0]);
                totalPriorityDiller += deck.cards[0].getPriority();
                if (totalPriorityDiller > 21){
                    System.out.println("Перебор у диллера");
                    diller.setBank(diller.getBank() - bid );
                    player.setBank(player.getBank() + bid );
                    break lebel6;
                }
                deck.subOneCard();
                printTable();
                break;
            } else {
                break lebel6;
            }
        }
        endBlackJack(totalPriorityDiller, totalPriorityPlayer);
    }



    // Возможность увеличить ставку
    public int bidUp() {
        Scanner scanner2 = new Scanner(System.in);
        label2:
        while (true) {
            System.out.print("Желаете увеличить ставку (1 - да, 2 - нет): >>> ");
            int n = scanner2.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Введите значение новой ставки (но не более - " + player.getBank() + ")");
                    bid = scanner2.nextInt();
                    if (bid > player.getBank()) {
                        System.out.println("Вы неправильно ввели данные");
                        break;
                    } else {
                        return bid;
                    }
                case 2:
                    break label2;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }
        return 0;
    }

    public void endBlackJack(int totalPriorityDiller, int totalPriorityPlayer) {
        if (totalPriorityDiller == totalPriorityPlayer){
            System.out.println("Ровно - каждый при своих");
        } else  if (totalPriorityDiller > totalPriorityPlayer){
            System.out.println("Ставка у диллера");
            diller.setBank(diller.getBank() + bid );
            player.setBank(player.getBank() - bid );
        } else {
            System.out.println("Ставка у игрока");
            diller.setBank(diller.getBank() - bid );
            player.setBank(player.getBank() + bid );
        }
        printTable();
        if (player.getBank() > BID_MIN){
            System.out.println("Еще разочек");

        }
    }


    // Добавление карты игрока
    public Deck.Card[] addPlayerCard(Deck.Card newCard) {
        playerCards = Arrays.copyOf(playerCards, playerCards.length + 1);
        playerCards[playerCards.length - 1] = newCard;
        return playerCards;
    }

    // Добавление карты диллера
    public Deck.Card[] addDillerCard(Deck.Card newCard) {
        dillerCards = Arrays.copyOf(dillerCards, dillerCards.length + 1);
        dillerCards[dillerCards.length - 1] = newCard;
        return dillerCards;
    }




}
