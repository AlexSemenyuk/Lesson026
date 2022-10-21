package org.itstep.game;

import org.itstep.Deck;
import org.itstep.Diller;
import org.itstep.Player;

import java.util.Scanner;

public abstract class Games {
    protected static final int BID_MIN = 20;
    protected static final int DILLER_BANK_MIN = 200;
    private static final Scanner scanner = new Scanner(System.in);

    private String name;        // Например "Стол №1"
    private int bid;            // Ставка
    private Player player;
    private Diller diller;

    Games() {
        this("", 0, null, null);
    }

    Games(String name, int bid, Player player, Diller diller) {
        this.name = name;
        this.bid = bid;
        this.player = player;
        this.diller = diller;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Diller getDiller() {
        return diller;
    }

    public void setDiller(Diller diller) {
        this.diller = diller;
    }

    @Override
    public String toString() {
        return String.format("%-10s: %d ", name, bid);
    }

    public void preparation(String line) {
        name = line;                // Название игры
        player = new Player();      // Создание игрока
        diller = new Diller();      // Создание диллера / казино
        bid = BID_MIN;              // Ставка по умолчанию
    }


    // Печать игрового стола со скрытыми картами
    public abstract void printTableHidden();

    // Печать игрового стола со открытыми картами
    public abstract void printTableOpen();

    // Игра
    public abstract void game(String line);

    // 2.1 Создание колоды карт
    public abstract void createDeck();

    // 2.2 Возможность увеличить ставку
    public void bidUp() {
        label2:
        while (true) {
            System.out.print("Желаете увеличить ставку (1 - да, 2 - нет): >>> ");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    System.out.print("Введите значение новой ставки (но не более - " + player.getBank() + ") >>> ");
                    int m = scanner.nextInt();
                    if (m > player.getBank()) {
                        System.out.println("Вы неправильно ввели данные");
                        break;
                    } else {
                        bid = m;
                    }
                case 2:
                    break label2;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }
    }

    // 2.3 Начальные карты игрока и диллера
    public abstract void startCards();

    // 2.4 Проверка начальных карт игрока и диллера
    public abstract void checkStartCards();

    // 3.1 Добавление карт игроку
    public abstract void addCardPlayer();

    // 3.1.1 Добавление игроку карты
    public abstract Deck.Card[] addPlayerCard(Deck.Card newCard);

    // 3.2 Добавление диллеру карты
    public abstract void addCardDiller();

    // 3.2.1 Добавление карты диллера
    public abstract Deck.Card[] addDillerCard(Deck.Card newCard);

    // Предложение о новой игре
    public abstract void newGameOffer();
}
