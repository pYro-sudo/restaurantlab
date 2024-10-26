package org.restaurant;

public class Tip {
    private double tip;
    private String NameOfGuest;

    public Tip(double tip, String nameOfGuest){
        this.tip = tip;
        this.NameOfGuest = nameOfGuest;
    }

    public double getTip() {
        return tip;
    }

    public String getNameOfGuest() {
        return NameOfGuest;
    }
}
