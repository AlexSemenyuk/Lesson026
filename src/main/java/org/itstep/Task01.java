package org.itstep;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Java. Lesson025. Task01
 * Колода карт (см. README.md)
 * Semenyuk Alexander
 * Date 13.10.2022
 */
public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("В какую игру сыграем (1-Black Jack, 2-<Очко>): >>> ");
        int game = scanner.nextInt();
        label1:
        while (true) {
            switch (game) {
                case 1:
                    GameTable blackJack = new GameTable();
                    blackJack.preparationStart("Black Jack");
                    blackJack.gameBlackJack();
                    break label1;
                case 2:
                    GameTable ochko = new GameTable();
                    ochko.preparationStart("<Очко>");

                    break label1;
                default:
                    System.out.println("Ds неправильно ввели цифру");
                    break;
            }

        }
    }
}






        // Создание колод
//        Deck BlackJack = new Deck("Колода на 52 карты для игры Black Jack", Deck.DECK_52);
//        BlackJack.addDeckBlackJack();
//        BlackJack.printDeck();
//        System.out.println("После сортировки по возрастанию");
//        BlackJack.sortUp();
//        BlackJack.printDeck();
//        System.out.println("После сортировки по убыванию");
//        BlackJack.sortDown();
//        BlackJack.printDeck();
//        System.out.println("После сортировки Random");
//        BlackJack.sortRandom();
//        BlackJack.printDeck();
//        System.out.println("После перемешивания");
//        BlackJack.shuffle();
//        BlackJack.printDeck();

        // Создание диллера и игрока
//        Diller diller = new Diller();
//        System.out.println(diller.toString());
//
//        Player player = new Player();
//        System.out.println(player.toString());
//        Deck Ochko = new Deck("Колода на 36 карты для игры очко", Deck.DECK_36);
//        Ochko.addDeckBlackJack();
//        Ochko.printDeck();






        // Создание колод
//        Deck deck1 = new Deck("Колода 54 карты", Deck.DECK_54);
//        deck1.addDeckClassic();
//        deck1.printDeck();  // Вывод в консоль

//        Deck deck2 = new Deck("Колода 36 карт", Deck.DECK_36);
//        deck2.addDeckClassic();
//        deck2.printDeck();  // Вывод в консоль

//        Deck deck3 = new Deck();   // Конструктор по умолчанию
//        deck3.addDeckClassic();
//        deck3.printDeck();  // Вывод в консоль

        // Перемешивание (через Comparator)
//        deck1.shuffle();
//        deck1.printDeck();
//
//        deck2.shuffle();
//        deck2.printDeck();
//
//        deck3.shuffle();
//        deck3.printDeck();

        // Сортировка по убыванию (через Comparator)
//        Deck.Card.CardComparatorByPriorityDown comparatorByPriorityDown = new Deck.Card.CardComparatorByPriorityDown();
//        Arrays.sort(deck1.cards, comparatorByPriorityDown);
//        deck1.printDeck();
//
//        Arrays.sort(deck2.cards, comparatorByPriorityDown);
//        deck2.printDeck();
//
//        Arrays.sort(deck3.cards, comparatorByPriorityDown);
//        deck3.printDeck();

        // Сортировка по возрастанию (через Comparable)
//        Arrays.sort(deck1.cards);
//        deck1.printDeck();
//
//        Arrays.sort(deck2.cards);
//        deck2.printDeck();
//
//        Arrays.sort(deck3.cards);
//        deck3.printDeck();

        // Вывод карт через перебор в foreach
//        System.out.println(deck3.getNameDeck() + "(перебор)");
//        for (Object card : deck3) {
//            System.out.println(card.toString());
//        }
//        // Если поменять Сard toString() - можно вывести и без приоритета



