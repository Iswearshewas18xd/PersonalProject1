package com.company;

public class Vaccine {
    private String name;
    private int resistance;
    private int population;
    private int newPeoplePerDay;

    public Vaccine(String name1, int resistance1, int population1, int newPeoplePerDay1) {
        this.name = name1;
        this.resistance = resistance1;
        this.population = population1;
        this.newPeoplePerDay = newPeoplePerDay1;
    }
    public String getName(){
        return name;
    }
    public void setName(String theName){
        this.name = theName;
    }
    public int getResistance(){
        return resistance;
    }
    public void setResistance(int theResistance){
        this.resistance = theResistance;
    }
    public int getPopulation(){
        return population;
    }
    public void setPopulation(int thePopulation){
        this.population = thePopulation;
    }
    public int getNewPeoplePerDay(){
        return  newPeoplePerDay;
    }
    public void setNewPeoplePerDay(int theNewPeoplePerDay){
        this.newPeoplePerDay = theNewPeoplePerDay;
    }
}
