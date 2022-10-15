package org.itstep;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Java. Lesson025. Task01
 * Колода карт (см. README.md)
 * Semenyuk Alexander
 * Date 13.10.2022
 */
public class Task01 {
    public static void main(String[] args) {
        // Знаки масти
//        System.out.println("+\u2660 \u2661 \u2662 +\u2663 \u2664 +\u2665 +\u2666 \u2667");
        // Масти карт
//        Suite [] suites = Suite.values();
//        for (Suite suite: suites ){
//            System.out.println(suite.toString());
//        }
        // Значения карт
//        Mean [] means = Mean.values();
//        for (Mean mean: means ){
//            System.out.println(mean.toString());
//        }


        // Создание колод
        Deck deck1 = new Deck("Колода 54 карты", 54);
        deck1.addDeck();
//        deck1.printDeck();  // Вывод в консоль

        Deck deck2 = new Deck("Колода 36 карт", 36);
        deck2.addDeck();
//        deck2.printDeck();  // Вывод в консоль

        Deck deck3 = new Deck();   // Конструктор по умолчанию
        deck3.addDeck();
        deck3.printDeck();  // Вывод в консоль

        // Перемешивание (через Comparator)
        deck1.shuffle();
//        deck1.printDeck();

        deck2.shuffle();
//        deck2.printDeck();

        deck3.shuffle();
        deck3.printDeck();

        // Сортировка по убыванию (через Comparator)
        CardComparatorByPriority comparatorByPriority = new CardComparatorByPriority();
        Arrays.sort(deck1.cards, comparatorByPriority);
//        deck1.printDeck();

        Arrays.sort(deck2.cards, comparatorByPriority);
//        deck2.printDeck();

        Arrays.sort(deck3.cards, comparatorByPriority);
        deck3.printDeck();

        // Сортировка по возрастанию (через Comparable)
        Arrays.sort(deck1.cards);
//        deck1.printDeck();

        Arrays.sort(deck2.cards);
//        deck2.printDeck();

        Arrays.sort(deck3.cards);
        deck3.printDeck();

        // Вывод карт через перебор в foreach
        System.out.println(deck3.getNameDeck() + "(перебор)");
        for (Object card : deck3) {
            System.out.println(card.toString());
        }
        // Если поменять Сard toString() - можно вывести и без приоритета

    }
}

