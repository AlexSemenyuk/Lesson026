package org.itstep;

public class Diller {
    private static final int START_BANK = 500_000;

    private String name;
    private int bank;


    Diller (){
        this("Casino Red Dragon", START_BANK);
    }

    Diller (String name, int bank){
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

