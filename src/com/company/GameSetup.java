package com.company;
import java.util.ArrayList;
import java.util.Random;

public class GameSetup {
    private int totalPopulation;
    private int covidStartPopulation;
    private int spreadPossibility;
    private int totalVaccinatedPopulation;
    private int PersonQuarantinePossibility;
    private int MaximumPossibleVaccinations;
    private int infectedPeople;
    private int day;
    public ArrayList<Vaccine> Vaccines;

    public GameSetup()
    {
        this.totalPopulation = 0;
        this.covidStartPopulation = 0;
        this.spreadPossibility = 0;
        this.totalVaccinatedPopulation=0;
        this.PersonQuarantinePossibility=0;
        this.MaximumPossibleVaccinations =0;
        this.infectedPeople = 0;
        this.day = 0;
        this.Vaccines = new ArrayList<>();
    }

    public int getTotalPopulation()
    {
        return this.totalPopulation;
    }
    public void setTotalPopulation(int population)
    {
        this.totalPopulation = population;
    }
    public int getCovidStartPopulation()
    {
        return this.covidStartPopulation;
    }
    public void setCovidStartPopulation(int covidStartPopulation1){
        this.covidStartPopulation = covidStartPopulation1;
    }
    public int getSpreadPossibility(){
       return this.spreadPossibility;
    }
    public void setSpreadPossibility(int spreadPossibility1){
        this.spreadPossibility = spreadPossibility1;
    }
    public int getTotalVaccinatedPopulation(){
        return this.totalVaccinatedPopulation;
    }
    public void setTotalVaccinatedPopulation(int vaccinatedPopulation){
        this.totalVaccinatedPopulation = vaccinatedPopulation;
    }
    public int getPersonQuarantinePossibility(){
        return this.PersonQuarantinePossibility;
    }
    public void setPersonQuarantinePossibility(int quarantine){
        this.PersonQuarantinePossibility= quarantine;
    }

    public  int getMaximumPossibleVaccinations() {return this.MaximumPossibleVaccinations;}
    public void setMaximumPossibleVaccinations(int maximumPossibleVaccinations1) {this.MaximumPossibleVaccinations = maximumPossibleVaccinations1; }

    public int getInfectedPeople() {return this.infectedPeople;}
    public void setInfectedPeople(int infectedPeople) {this.infectedPeople = infectedPeople;}
    public int getDay(){return this.day; }
    public void setDay(int day1){this.day = day1; }

    public Player[][] NextDay(Player[][] myplayers, int Size){
        Player[][] playerPos1 = new Player[Size][Size];

        //Player Movement
        for(int i = 0; i<Size; i++)
            for(int h=0;h<Size;h++) {
                if (i < Size && h >= 0) {
                    int xMove = 0;
                    int yMove = 0;
                    Random rng1 = new Random();
                    Random rng2 = new Random();

                    Player tempPlayer = myplayers[i][h];
                    boolean sameCoordinates = false;
                    if (tempPlayer != null) {
                        if (tempPlayer.getxPos() < Size && tempPlayer.getyPos() < Size && tempPlayer.getxPos() > 1 && tempPlayer.getyPos() > 1) {
                            xMove = rng1.nextInt(3) - 1;
                            yMove = rng2.nextInt(3) - 1;

                        } else if (tempPlayer.getxPos() == Size) { // right border
                            xMove = rng1.nextInt(2) - 1;
                            yMove = rng2.nextInt(3) - 1;

                        } else if (tempPlayer.getyPos() == Size) { // upper border
                            xMove = rng1.nextInt(3) - 1;
                            yMove = rng2.nextInt(2) - 1;

                        } else if (tempPlayer.getxPos() == 1) {   // left border
                            xMove = rng1.nextInt(2);
                            yMove = rng2.nextInt(3) - 1;

                        } else if (tempPlayer.getyPos() == 1) {   // bottom border
                            xMove = rng1.nextInt(3) - 1;
                            yMove = rng2.nextInt(2);

                        } else {
                            xMove = 0;
                            yMove = 0;
                        }
                        for (int j = 0; j < Size; j++) {
                            for (int k = 0; k < Size; k++) {
                                if (playerPos1[j][k] != null && myplayers[j][k] != null) {
                                    if ((playerPos1[j][k].getxPos() == myplayers[i][h].getxPos() + xMove
                                            && playerPos1[j][k].getxPos() == (myplayers[i][h].getyPos() - yMove))
                                            && myplayers[j][k].getxPos() == myplayers[i][h].getxPos() + xMove
                                            && myplayers[j][k].getxPos() == myplayers[i][h].getyPos() - yMove
                                    ) {
                                        sameCoordinates = true;
                                        xMove = 0;
                                        yMove = 0;
                                    }
                                }
                            }
                        }
                        if (i + xMove < Size && h - yMove < Size && i + xMove >= 0 && h - yMove >= 0){
                            if (playerPos1[i + xMove][h - yMove] != null) {
                                if (tempPlayer.getPlayerID() != playerPos1[i + xMove][h - yMove].getPlayerID()) {
                                    sameCoordinates = true;
                                    xMove = 0;
                                    yMove = 0;
                                }
                            }
                        }
                        if (i + xMove < Size && h - yMove < Size && i + xMove >= 0 && h - yMove >= 0){
                            if(myplayers[i+xMove][h-yMove]!=null){
                                if(tempPlayer.getPlayerID()!= myplayers[i+xMove][h-yMove].getPlayerID()){
                                    sameCoordinates = true;
                                    xMove = 0;
                                    yMove = 0;
                                }
                            }
                        }
                        if(!tempPlayer.getIsQuarantined()){
                            if (!sameCoordinates&&i+xMove<Size&&h-yMove<Size&&i+xMove>=0&&h-yMove>=0) {
                                playerPos1[i + xMove][h - yMove] = tempPlayer;
                                playerPos1[i + xMove][h - yMove].setxPos(playerPos1[i + xMove][h - yMove].getxPos() + xMove);
                                playerPos1[i + xMove][h - yMove].setyPos(playerPos1[i + xMove][h - yMove].getyPos() + yMove);
                                //}
                            } else {
                                playerPos1[i][h] = tempPlayer;
                                playerPos1[i][h].setxPos(playerPos1[i][h].getxPos());
                                playerPos1[i][h].setyPos(playerPos1[i][h].getyPos());
                            }
                        }
                        else{
                            playerPos1[i][h] = tempPlayer;
                            playerPos1[i][h].setxPos(playerPos1[i][h].getxPos());
                            playerPos1[i][h].setyPos(playerPos1[i][h].getyPos());
                        }
                        tempPlayer = null;
                    }
                }
            }

        day++;
        for(int i =0; i<Size; i++) { // Virus Attack
            for (int h = 0; h < Size; h++) {
                if (playerPos1[i][h] != null) {
                    if (playerPos1[i][h].getHasCovid() && !playerPos1[i][h].getIsQuarantined()&&playerPos1[i][h].getInfectionStartDay()!=day) {

                        boolean upperCheck = true, bottomCheck = true, rightCheck = true, leftCheck = true;
                        if (playerPos1[i][h].getxPos() == 1) {    // forbid left check;
                            leftCheck = false;
                        }
                        if (playerPos1[i][h].getxPos() == Size) {   //forbid right check;
                            rightCheck = false;
                        }
                        if (playerPos1[i][h].getyPos() == 1) {   //forbid upper check;
                            upperCheck = false;
                        }
                        if (playerPos1[i][h].getyPos() == Size) {  // forbid bottom check
                            bottomCheck = false;
                        }
                        if (upperCheck) {
                            if (playerPos1[i][h + 1] != null && !playerPos1[i][h + 1].getHasCovid()) {
                                playerPos1[i][h + 1].CovidAttack(day);
                            }
                        }
                        if (bottomCheck) {
                            if (playerPos1[i][h - 1] != null && !playerPos1[i][h - 1].getHasCovid()) {
                                playerPos1[i][h - 1].CovidAttack(day);
                            }
                        }
                        if (leftCheck) {
                            if (playerPos1[i - 1][h] != null && !playerPos1[i - 1][h].getHasCovid()) {
                                playerPos1[i - 1][h].CovidAttack(day);
                            }
                        }
                        if (rightCheck) {
                            if (playerPos1[i + 1][h] != null && !playerPos1[i + 1][h].getHasCovid()) {
                                playerPos1[i + 1][h].CovidAttack(day);
                            }
                        }
                    }
                }
            }
        }
        for(int i =0; i<Size; i++) { // update day in players
            for (int h = 0; h < Size; h++) {
                if(playerPos1[i][h]!=null){
                    if(day >= playerPos1[i][h].getInfectionStartDay()+playerPos1[i][h].getDaysOfSickness()){
                        playerPos1[i][h].setHasCovid(false);
                        playerPos1[i][h].setQuarantined(false);
                    }
                }
            }
        }
        int infectedsum =0;
        for(int i=0; i<Size;i++){
            for(int h =0; h<Size;h++){
                if(playerPos1[i][h]!=null){
                    if(playerPos1[i][h].getHasCovid()){
                        infectedsum++;
                    }
                }
            }
        }
        setInfectedPeople(infectedsum);

        int v1= Vaccines.get(0).getNewPeoplePerDay();
        int v2= Vaccines.get(1).getNewPeoplePerDay();
        int v3= Vaccines.get(2).getNewPeoplePerDay();
        int v4= Vaccines.get(3).getNewPeoplePerDay();

        for(int i=0; i<Size;i++){
            for(int h =0; h<Size;h++){
                if(playerPos1[i][h]!=null){
                    if(!playerPos1[i][h].getHasCovid()&&!playerPos1[i][h].getIsVaccinated()&&(totalVaccinatedPopulation*100/totalPopulation<getMaximumPossibleVaccinations())){
                        if(v1 >0){
                            playerPos1[i][h].setVaccinated(true);
                            playerPos1[i][h].setMyVaccine(Vaccines.get(0));
                            totalVaccinatedPopulation++;
                            v1--;
                        } else if(v2>0) {
                            playerPos1[i][h].setVaccinated(true);
                            playerPos1[i][h].setMyVaccine(Vaccines.get(1));
                            totalVaccinatedPopulation++;
                            v2--;
                        } else if(v3>0) {
                            playerPos1[i][h].setVaccinated(true);
                            playerPos1[i][h].setMyVaccine(Vaccines.get(2));
                            totalVaccinatedPopulation++;
                            v3--;
                        } else if(v4>0) {
                            playerPos1[i][h].setVaccinated(true);
                            playerPos1[i][h].setMyVaccine(Vaccines.get(3));
                            totalVaccinatedPopulation++;
                            v4--;
                        }
                    }
                }
            }
        }
        for(int i=0; i<Size;i++) {
            for(int h=0; h<Size;h++) {

            }
        }
        //->>
        return playerPos1;
    }

    public Player[][] PlayerMaker(int Size)
    {
        Player[][] playerPos = new Player[46][46];
        int a=0;
        int b=0,v1,v2,v3,v4;
        boolean withCovid;
        boolean Vaccinated;
        v1 = Vaccines.get(0).getPopulation();
        v2 = Vaccines.get(1).getPopulation();
        v3 = Vaccines.get(2).getPopulation();
        v4 = Vaccines.get(3).getPopulation();

        for(int i=0; i<Size; i++)   // players
        {
            for(int h=0; h<Size; h++)
            {
                playerPos[i][h] = null;
            }
        }
        while(a<totalPopulation)
        {
            Random intx = new Random();
            int x = intx.nextInt(Size);
            Random inty = new Random();
            int y = inty.nextInt(Size+1);
            while(x>Size || x<0)
            {
                x = intx.nextInt(Size);
            }
            while(y>Size || y<1)
            {
                y = intx.nextInt(Size+1);
            }
            Player playerVaccine = new Player(0,0,0,true, true, PersonQuarantinePossibility, spreadPossibility);
            if(a<totalPopulation&&playerPos[x][Size - y]==null){
                if(b<covidStartPopulation) {
                    withCovid = true;
                    Vaccinated = false;
                }
                else {
                    withCovid = false;
                    if(b- covidStartPopulation<totalVaccinatedPopulation) {
                        Vaccinated = true;
                        if(v1>0){
                            playerVaccine.setMyVaccine(Vaccines.get(0));
                            v1--;
                        }
                        else if(v2>0){
                            playerVaccine.setMyVaccine(Vaccines.get(1));
                            v2--;
                        }
                        else if(v3>0){
                            playerVaccine.setMyVaccine(Vaccines.get(2));
                            v3--;
                        }
                        else if(v4>0){
                            playerVaccine.setMyVaccine(Vaccines.get(3));
                            v4--;
                        }
                    }
                    else {
                        Vaccinated = false;
                    }
                }
                Player testplayer = new Player(x+1, y, a, withCovid, Vaccinated, PersonQuarantinePossibility, spreadPossibility);
                if(Vaccinated)
                    testplayer.setMyVaccine(playerVaccine.getVaccine());
                playerPos[x][Size - y] = testplayer;
                a++;
                b++;
            }
        }

        //playerPos[1-1][Size-1] = new Player(1,1, 0,false, false);// to x einai x - 1 apo tin metrisi pinaka
        //playerPos[1-1][Size-4] = new Player(1, 4,0,true, false);
        //playerPos[4-1][Size-9] = new Player(4,9);
        return playerPos;
    }
}
