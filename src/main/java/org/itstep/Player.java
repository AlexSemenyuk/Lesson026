package org.itstep;

public class Player { // Мені здається, що ділер та ігрок мають дуже схожу логіку. Можливо треба було створити один клас (при необхідності можно було реалізувати успадуквання)
    private static final int START_BANK = 1_000;

    private String name;
    private int bank;

    public Player() {
        this("Alex", START_BANK);
    }

    public Player(String name, int bank) {
        this.name = name;
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-7d ", name, bank);
    }
}
