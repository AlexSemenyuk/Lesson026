package org.itstep;

import java.util.Comparator;
import java.util.Random;

public class Card implements Comparable {
    private String suite;
    private String mean;
    private String sign;
    private int priority;

    public Card(String mean, String sign, String suite, int priority) {
        this.suite = suite;
        this.mean = mean;
        this.sign = sign;
        this.priority = priority;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return String.format("%8s:%1s  priority: %-3d", mean, sign, priority);
    }

    @Override
    public int compareTo(Object o) {
        return priority - ((Card) o).getPriority();
    }
}


class CardComparatorByPriority implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Card) o2).getPriority() - ((Card) o1).getPriority();
    }
}

class CardComparatorForShuffle implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Random random = new Random();
        return random.nextInt(3) - 1;
    }
}