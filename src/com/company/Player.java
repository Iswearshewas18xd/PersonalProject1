package com.company;

import java.util.Random;

public class Player {
    private int playerID;
    private int xPos;
    private int yPos;

    private static int DaysOfSickness = 14;

    private int InfectionStartDay;
    private boolean hasCovid;
    private boolean isVaccinated;
    private boolean isQuarantined;
    private Vaccine myVaccine;
    private int QuarantineChance;
    private int infectionPossibility;

    public Player(int x, int y, int ID, boolean covid, boolean vaccinated, int Quarantine, int infectionpossibility1){
        this.xPos = x;
        this.yPos = y;
        this.playerID = ID;
        this.InfectionStartDay = -1;
        this.hasCovid = covid;
        this.isVaccinated = vaccinated;
        this.isQuarantined = false;
        this.QuarantineChance = Quarantine;
        this.infectionPossibility = infectionpossibility1;
    }

    public int getxPos() {
        return xPos;
    }
    public int getyPos(){
        return yPos;
    }
    public void setxPos(int xPos1){
        this.xPos = xPos1;
    }
    public void setyPos(int yPos1){
        this.yPos = yPos1;
    }
    public int getDaysOfSickness() {return DaysOfSickness; }
    public int getPlayerID(){
        return playerID;
    }
    public void setPlayerID(int playerID1) {
        this.playerID = playerID1;
    }
    public int getInfectionStartDay(){
        return InfectionStartDay;
    }
    public void setInfectionStartDay(int infectionStartDay1){
        this.InfectionStartDay = infectionStartDay1;
    }
    public boolean getHasCovid() {
        return hasCovid;
    }
    public void setHasCovid(boolean hasCovid1) {
        this.hasCovid = hasCovid1;
    }
    public boolean getIsVaccinated() {
        return isVaccinated;
    }
    public void setVaccinated(boolean vaccinated) {
        this.isVaccinated = vaccinated;
    }
    public boolean getIsQuarantined() {
        return isQuarantined;
    }
    public void setQuarantined(boolean quarantined) {
        this.isQuarantined = quarantined;
    }
    public int getQuarantineChance() {
        return QuarantineChance;
    }
    public void setQuarantineChance(int myquarantine) {
        this.QuarantineChance = myquarantine;
    }
    public void CovidAttack(int today) {
        if (InfectionStartDay==-1) { // den prepei na exei ksananosisei
            Random r1 = new Random();
            int resistance = 0;
            if (isVaccinated) {
                resistance += myVaccine.getResistance();
            }
            if (r1.nextInt(100) < infectionPossibility - resistance) {
                hasCovid = true;
                InfectionStartDay = today;
                Random r2 = new Random();
                if (r2.nextInt(100) < QuarantineChance)
                    isQuarantined = true;
            }
        }
    }
    public Vaccine getVaccine(){
        return this.myVaccine;
    }
    public void setMyVaccine(Vaccine theVaccine){
        this.myVaccine = theVaccine;
    }
}
