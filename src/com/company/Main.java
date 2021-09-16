package com.company;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.*;

public class Main
{
    public static void main(String[] args) {
        GUI gui = new GUI();
    }

    public static class GUI extends JFrame implements ActionListener {
        public JFrame f;
        public JPanel panel;
        public JPanel panel2;
        public static JLabel c;
        public static JLabel e;
        public static JLabel g;
        public static JLabel k1;
        public static JLabel l1;
        public JLabel a1;
        public static JLabel a2;
        public static JLabel a3;
        public static JLabel a4;
        public static JLabel b1;
        public static JLabel b2;
        public static JLabel b3;
        public static JLabel b4;
        public static JLabel b5;
        public static JLabel b6;
        public static JLabel b7;
        public static JLabel b8;
        public JButton a;
        public JButton d;
        public JButton h;
        public JButton j;
        public JButton k2;
        public JButton k3;
        public JButton k4;
        public JButton l2;
        public JButton k5;
        public JButton k6;
        public JButton k7;
        public JButton k8;
        public JButton k9;
        public JButton k10;
        public JTextField l3;
        public JTextField l4;
        public JTextField l5;
        public JTextField q00;
        public JTextField q01;
        public JTextField q02;
        public JTextField q10;
        public JTextField q11;
        public JTextField q12;
        public JTextField q20;
        public JTextField q21;
        public JTextField q22;
        public JTextField q30;
        public JTextField q31;
        public JTextField q32;
        public JTextField q40;
        public JTextField q41;
        public JTextField q42;
        public JTextField q43;
        public JLabel b9;
        public JTextField b10;
        public JButton b11;
        public JLabel b12;
        public JButton b13;
        public JTextField b14;
        public JButton q50;
        public JButton q51;
        public JButton q52;
        public JButton q53;
        public static JLabel position;
        public static JLabel PlayerID;
        public static JLabel condition;
        public static JLabel status;
        public static JLabel Vaccinated;
        public static JLabel Vacine;
        public static JLabel resistance;
        public static JLabel daysofsickness;
        public static JLabel sickPopulation;
        public static JLabel vacPopulation;

        public int x;
        public JButton[][] mapbuttons;
        public int squareSide;

        public GameSetup gs1 = new GameSetup();

        private Player[][] playerPos1 = new Player[45][45];

        public GUI() {

            squareSide = 15;
            f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Vaccine vaccine1 = new Vaccine("name1", 15,5,2);
            gs1.Vaccines.add(vaccine1);
            Vaccine vaccine2 = new Vaccine("name2", 14,8,2);
            gs1.Vaccines.add(vaccine2);
            Vaccine vaccine3 = new Vaccine("name3", 13,4,1);
            gs1.Vaccines.add(vaccine3);
            Vaccine vaccine4 = new Vaccine("name4", 12,6,3);
            gs1.Vaccines.add(vaccine4);


            panel = new JPanel();
            panel.setLayout(null);

            panel2 = new JPanel();
            panel2.setLayout(null);

            d = new JButton("proceed");           //StepButton
            d.setLocation(500, 400);
            d.setSize(new Dimension(100, 50));
            d.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MapSizeSetter(squareSide);
                    BoxFunction();
                    NextPanel();
                }
            });
            panel2.add(d);

            int numRows = 15;
            int numCols = 15;
            mapbuttons = new JButton[numCols][numRows];

            mapbuttons = addButtonsA(panel, numRows, numCols);

            LabelMaker(k1, "Select Map Size: ", 0 ,0, 100, 50, panel2);

            LabelMaker(l1, "Select Population Size: ", 0, 70, 200 , 50, panel2);

            l3 = new JTextField();
            gs1.setTotalPopulation(100);
            l3.setText(gs1.getTotalPopulation()+"");
            l3.setLocation(130 ,85);
            l3.setSize(new Dimension(70, 25));
            panel2.add(l3);

            LabelMaker(a1,"(The number must be lesser that the physical limit of the map to be accepted)", 20, 100, 500, 40, panel2);

            l2 = new JButton("Validate");
            l2.setLocation(200, 85);
            l2.setSize(new Dimension(100, 25));
            l2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent q) {
                    if(l3.getText() != "")
                    {
                        int populationAmount = (int) Double.parseDouble(l3.getText());
                        if(populationAmount>0&populationAmount<squareSide*squareSide){
                            gs1.setTotalPopulation(populationAmount);
                            System.out.println(gs1.getTotalPopulation());
                            if(populationAmount<100){
                                q00.setText("0");
                                q10.setText("0");
                                q20.setText("0");
                                q30.setText("0");
                                gs1.setTotalVaccinatedPopulation(0);

                                l4.setText(""+(gs1.getTotalPopulation()-1+(gs1.getTotalPopulation()/5)));
                                gs1.setCovidStartPopulation(gs1.getTotalPopulation()-1+(gs1.getTotalPopulation()/5));
                            }
                            panel2.revalidate();
                        }
                        else
                        {
                            l3.setText(gs1.getTotalPopulation()+"");
                            panel2.revalidate();
                        }
                    }
                }
            });
            panel2.add(l2);

            LabelMaker(a2, "Starting WITH Covid Population: ",0, 140, 200, 50, panel2);
            LabelMaker(a3,"(The Covid Population number must be lesser than the total population)", 20, 167, 500, 40, panel2);

            l4= new JTextField();
            gs1.setCovidStartPopulation(18);
            l4.setText(gs1.getCovidStartPopulation()+"");
            l4.setLocation(185, 153);
            l4.setSize(new Dimension(70, 25));
            panel2.add(l4);

            k5 = new JButton("Validate");
            k5.setLocation(255, 153);
            k5.setSize(new Dimension(100, 25));
            k5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent q) {
                    if(l4.getText()!= "")
                    {
                        int StartPopulation = (int) Double.parseDouble(l4.getText());
                        if(StartPopulation>=0&&StartPopulation<gs1.getTotalPopulation())
                        {
                            gs1.setCovidStartPopulation(StartPopulation);
                            System.out.println(gs1.getCovidStartPopulation());
                            panel2.revalidate();
                        }
                        else
                        {
                            l4.setText(gs1.getCovidStartPopulation()+"");
                            panel2.revalidate();
                        }
                    }
                }
            });
            panel2.add(k5);

            LabelMaker(a4, "Infection possibility upon interaction ( %):            %",0, 200, 500, 40, panel2);

            l5= new JTextField();
            l5.setText(gs1.getSpreadPossibility()+"");
            gs1.setSpreadPossibility(40);
            l5.setLocation(233, 208);
            l5.setSize(new Dimension(32, 25));
            panel2.add(l5);

            k6 = new JButton("Validate");
            k6.setLocation(275, 208);
            k6.setSize(new Dimension(100, 25));
            k6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent q) {
                    if(l5.getText()!="")
                    {
                        int SpreadPos = (int) Double.parseDouble(l5.getText());
                        if(SpreadPos>=0&&SpreadPos<101)
                        {
                            gs1.setSpreadPossibility(SpreadPos);
                            System.out.println(gs1.getSpreadPossibility());
                            panel2.revalidate();
                        }
                        else
                        {
                            l5.setText(gs1.getSpreadPossibility()+"");
                            panel2.revalidate();
                        }
                    }
                }
            });
            panel2.add(k6);

            LabelMaker(b9, "Person's quarantine possibility ( %):           %",0, 420, 270, 40, panel2);

            b10 = new JTextField();
            gs1.setPersonQuarantinePossibility(60);
            b10.setText(gs1.getPersonQuarantinePossibility()+"");
            b10.setLocation(202, 427);
            b10.setSize(new Dimension(32, 25));
            panel2.add(b10);

            b11 = new JButton("Validate");
            b11.setLocation(247, 427);
            b11.setSize(new Dimension(100, 25));
            b11.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(b10.getText()!=""){
                        int amount = (int) Double.parseDouble(b10.getText());
                        if(amount>=0&&amount<=100){
                            gs1.setPersonQuarantinePossibility(amount);
                            b10.setText(amount+"");
                            panel2.revalidate();
                        }
                        else{
                            b10.setText(gs1.getPersonQuarantinePossibility()+"");
                            panel2.revalidate();
                        }
                    }
                    else{
                        b10.setText(gs1.getPersonQuarantinePossibility()+"");
                        panel2.revalidate();
                    }
                }
            });
            panel2.add(b11);

            LabelMaker(b12, "Highest Vaccination Persentage: ( %):           %", 0, 385, 270,40, panel2);

            b14 = new JTextField();
            gs1.setMaximumPossibleVaccinations(80);
            b14.setText(gs1.getMaximumPossibleVaccinations()+"");
            b14.setLocation(212, 392);
            b14.setSize(new Dimension(32, 25));
            panel2.add(b14);

            b13 = new JButton("Validate");
            b13.setLocation(255, 392);
            b13.setSize(new Dimension(100, 25));
            b13.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(b14.getText()!=""){
                        int amount = (int) Double.parseDouble(b14.getText());
                        if(amount>=0&&amount<=100){
                            gs1.setMaximumPossibleVaccinations(amount);
                            b14.setText(amount+"");
                            panel2.revalidate();
                        }
                        else{
                            b14.setText(gs1.getMaximumPossibleVaccinations()+"");
                            panel2.revalidate();
                        }
                    }
                    else{
                        b14.setText(gs1.getMaximumPossibleVaccinations()+"");
                        panel2.revalidate();
                    }
                }
            });
            panel2.add(b13);

            int vac1y = 235;
            LabelMaker(b1, "- Vaccine 1:", 20, vac1y-15, 500, 40, panel2);
            LabelMaker(b2," Vaccinated population:           , vaccinations/ Day:       , Infection Resistance ( %):            %  ", 40, vac1y, 500, 40, panel2);

            q40= new JTextField();
            q40.setText(gs1.Vaccines.get(0).getName());
            // set the vaccines name;
            q40.setLocation(90, vac1y-7);
            q40.setSize(new Dimension(70,22));
            panel2.add(q40);

            q50 = new JButton();
            q50.setLocation(160, vac1y-7);
            q50.setSize(new Dimension(10, 22));
            q50.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q40.getText()!= ""){
                        gs1.Vaccines.get(0).setName(q40.getText());
                        q40.setText(gs1.Vaccines.get(0).getName());
                        panel2.revalidate();
                    }
                }
            });
            panel2.add(q50);

            q00 = new JTextField();
            q00.setText(gs1.Vaccines.get(0).getPopulation()+"");
            gs1.setTotalVaccinatedPopulation(gs1.getTotalVaccinatedPopulation()+ gs1.Vaccines.get(0).getPopulation());
            q00.setLocation(175, vac1y+8);
            q00.setSize(new Dimension(30, 25));
            panel2.add(q00);

            q01 = new JTextField();
            q01.setText(gs1.Vaccines.get(0).getNewPeoplePerDay()+"");
            q01.setLocation(315, vac1y+8);
            q01.setSize(new Dimension(20, 25));
            panel2.add(q01);

            q02 = new JTextField();
            q02.setText(gs1.Vaccines.get(0).getResistance()+"");
            q02.setLocation(485, vac1y+8);
            q02.setSize(new Dimension(30, 25));
            panel2.add(q02);

            k7 = new JButton("Validate");
            k7.setLocation(531, vac1y +7);
            k7.setSize(new Dimension(100, 25));
            k7.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q00.getText()!= ""){
                        int VacStart = (int) Double.parseDouble(q00.getText());
                        int sum = gs1.Vaccines.get(1).getPopulation();
                        sum += gs1.Vaccines.get(2).getPopulation();
                        sum += gs1.Vaccines.get(3).getPopulation();
                        if(VacStart>=0&&VacStart+sum<=gs1.getTotalPopulation()-gs1.getCovidStartPopulation()){
                            gs1.setTotalVaccinatedPopulation(VacStart+sum);
                            gs1.Vaccines.get(0).setPopulation(VacStart);
                            System.out.println("Vaccines: "+gs1.getTotalVaccinatedPopulation());
                            panel2.revalidate();
                        }
                        else {
                            q00.setText(gs1.Vaccines.get(0).getPopulation()+"");
                            panel2.revalidate();
                        }
                        int newPerDay = (int) Double.parseDouble(q01.getText());
                        if(newPerDay>=0&&newPerDay<gs1.getTotalPopulation()-gs1.getCovidStartPopulation()) {
                            gs1.Vaccines.get(0).setNewPeoplePerDay(newPerDay);
                            panel2.revalidate();
                        }
                        else{
                            q01.setText(gs1.Vaccines.get(0).getNewPeoplePerDay()+"");
                        }
                        int resistance = (int) Double.parseDouble(q02.getText());
                        if(resistance>=0&&resistance<=100){
                            gs1.Vaccines.get(0).setResistance(resistance);
                            panel2.revalidate();
                        }
                        else{
                            q02.setText(gs1.Vaccines.get(0).getResistance()+"");
                        }
                    }
                }
            });
            panel2.add(k7);


            int vac2y = vac1y+40;
            LabelMaker(b3, "- Vaccine 2:", 20, vac2y-15, 500, 40, panel2);
            LabelMaker(b4," Vaccinated population:           , vaccinations/ Day:       , Infection Resistance ( %):            %  ", 40, vac2y, 500, 40, panel2);

            q41= new JTextField();
            q41.setText(gs1.Vaccines.get(1).getName());
            // set the vaccines name;
            q41.setLocation(90, vac2y-7);
            q41.setSize(new Dimension(70,22));
            panel2.add(q41);

            q51 = new JButton();
            q51.setLocation(160, vac2y-7);
            q51.setSize(new Dimension(10, 22));
            q51.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q41.getText()!= ""){
                        gs1.Vaccines.get(1).setName(q41.getText());
                        q41.setText(gs1.Vaccines.get(1).getName());
                        panel2.revalidate();
                    }
                }
            });
            panel2.add(q51);

            q10 = new JTextField();
            q10.setText(""+gs1.Vaccines.get(1).getPopulation());
            gs1.setTotalVaccinatedPopulation(gs1.getTotalVaccinatedPopulation()+gs1.Vaccines.get(1).getPopulation());
            q10.setLocation(175, vac2y+7);
            q10.setSize(new Dimension(30, 25));
            panel2.add(q10);

            q11 = new JTextField();
            q11.setText(""+gs1.Vaccines.get(1).getNewPeoplePerDay());
            q11.setLocation(315, vac2y+7);
            q11.setSize(new Dimension(20, 25));
            panel2.add(q11);

            q12 = new JTextField();
            q12.setText(""+gs1.Vaccines.get(1).getResistance());
            q12.setLocation(485, vac2y+7);
            q12.setSize(new Dimension(30, 25));
            panel2.add(q12);

            k8 = new JButton("Validate");
            k8.setLocation(531, vac2y+7);
            k8.setSize(new Dimension(100, 25));
            k8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q10.getText()!= ""){
                        int VacStart = (int) Double.parseDouble(q10.getText());
                        int sum = gs1.Vaccines.get(0).getPopulation();
                        sum += gs1.Vaccines.get(2).getPopulation();
                        sum += gs1.Vaccines.get(3).getPopulation();
                        if(VacStart>=0&&VacStart+sum<=gs1.getTotalPopulation()-gs1.getCovidStartPopulation()){
                            gs1.setTotalVaccinatedPopulation(VacStart+sum);
                            gs1.Vaccines.get(1).setPopulation(VacStart);
                            System.out.println("Vaccines: "+gs1.getTotalVaccinatedPopulation());
                            panel2.revalidate();
                        }
                        else {
                            q10.setText(gs1.Vaccines.get(1).getPopulation()+"");
                            panel2.revalidate();
                        }
                        int newPerDay = (int) Double.parseDouble(q11.getText());
                        if(newPerDay>=0&&newPerDay<gs1.getTotalPopulation()-gs1.getCovidStartPopulation()) {
                            gs1.Vaccines.get(1).setNewPeoplePerDay(newPerDay);
                            panel2.revalidate();
                        }
                        else{
                            q11.setText(gs1.Vaccines.get(1).getNewPeoplePerDay()+"");
                        }
                        int resistance = (int) Double.parseDouble(q12.getText());
                        if(resistance>=0&&resistance<=100){
                            gs1.Vaccines.get(1).setResistance(resistance);
                            panel2.revalidate();
                        }
                        else{
                            q12.setText(gs1.Vaccines.get(1).getResistance()+"");
                        }
                    }
                }
            });
            panel2.add(k8);

            int vac3y=vac2y+40;
            LabelMaker(b5, "- Vaccine 3:", 20, vac3y-15, 500, 40, panel2);
            LabelMaker(b6," Vaccinated population:           , vaccinations/ Day:       , Infection Resistance ( %):            %  ", 40, vac3y, 500, 40, panel2);

            q42= new JTextField();
            q42.setText(gs1.Vaccines.get(2).getName());
            // set the vaccines name;
            q42.setLocation(90, vac3y-7);
            q42.setSize(new Dimension(70,22));
            panel2.add(q42);

            q52 = new JButton();
            q52.setLocation(160, vac3y-7);
            q52.setSize(new Dimension(10, 22));
            q52.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q42.getText()!= ""){
                        gs1.Vaccines.get(2).setName(q42.getText());
                        q42.setText(gs1.Vaccines.get(2).getName());
                        panel2.revalidate();
                    }
                }
            });
            panel2.add(q52);

            q20 = new JTextField();
            q20.setText(""+gs1.Vaccines.get(2).getPopulation());
            gs1.setTotalVaccinatedPopulation(gs1.getTotalVaccinatedPopulation()+ gs1.Vaccines.get(2).getPopulation());
            q20.setLocation(175, vac3y+7);
            q20.setSize(new Dimension(30, 25));
            panel2.add(q20);

            q21 = new JTextField();
            q21.setText(""+gs1.Vaccines.get(2).getNewPeoplePerDay());
            q21.setLocation(315, vac3y+7);
            q21.setSize(new Dimension(20, 25));
            panel2.add(q21);

            q22 = new JTextField();
            q22.setText(""+gs1.Vaccines.get(2).getResistance());
            q22.setLocation(485, vac3y+7);
            q22.setSize(new Dimension(30, 25));
            panel2.add(q22);

            k9 = new JButton("Validate");
            k9.setLocation(531, vac3y+7);
            k9.setSize(new Dimension(100, 25));
            k9.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q20.getText()!= ""){
                        int VacStart = (int) Double.parseDouble(q20.getText());
                        int sum = gs1.Vaccines.get(0).getPopulation();
                        sum += gs1.Vaccines.get(1).getPopulation();
                        sum += gs1.Vaccines.get(3).getPopulation();
                        if(VacStart>=0&&VacStart+sum<=gs1.getTotalPopulation()-gs1.getCovidStartPopulation()){
                            gs1.setTotalVaccinatedPopulation(VacStart+sum);
                            gs1.Vaccines.get(2).setPopulation(VacStart);
                            System.out.println("Vaccines: "+gs1.getTotalVaccinatedPopulation());
                            panel2.revalidate();
                        }
                        else {
                            q20.setText(gs1.Vaccines.get(2).getPopulation()+"");
                            panel2.revalidate();
                        }
                        int newPerDay = (int) Double.parseDouble(q21.getText());
                        if(newPerDay>=0&&newPerDay<gs1.getTotalPopulation()-gs1.getCovidStartPopulation()) {
                            gs1.Vaccines.get(2).setNewPeoplePerDay(newPerDay);
                            panel2.revalidate();
                        }
                        else{
                            q21.setText(gs1.Vaccines.get(2).getNewPeoplePerDay()+"");
                        }
                        int resistance = (int) Double.parseDouble(q22.getText());
                        if(resistance>=0&&resistance<=100){
                            gs1.Vaccines.get(2).setResistance(resistance);
                            panel2.revalidate();
                        }
                        else{
                            q22.setText(gs1.Vaccines.get(2).getResistance()+"");
                        }
                    }
                }
            });
            panel2.add(k9);

            int vac4y = vac3y+40;
            LabelMaker(b7, "- Vaccine 4:", 20, vac4y-15, 500, 40, panel2);
            LabelMaker(b8," Vaccinated population:           , vaccinations/ Day:       , Infection Resistance ( %):            %  ", 40, vac4y, 500, 40, panel2);

            q43= new JTextField();
            q43.setText(gs1.Vaccines.get(3).getName());
            // set the vaccines name;
            q43.setLocation(90, vac4y-7);
            q43.setSize(new Dimension(70,22));
            panel2.add(q43);

            q53 = new JButton();
            q53.setLocation(160, vac4y-7);
            q53.setSize(new Dimension(10, 22));
            q53.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q43.getText()!= ""){
                        gs1.Vaccines.get(3).setName(q43.getText());
                        q43.setText(gs1.Vaccines.get(3).getName());
                        panel2.revalidate();
                    }
                }
            });
            panel2.add(q53);

            q30 = new JTextField();
            q30.setText(""+gs1.Vaccines.get(3).getPopulation());
            gs1.setTotalVaccinatedPopulation(gs1.getTotalVaccinatedPopulation()+ gs1.Vaccines.get(3).getPopulation());
            q30.setLocation(175, vac4y+7);
            q30.setSize(new Dimension(30, 25));
            panel2.add(q30);

            q31 = new JTextField();
            q31.setText(""+gs1.Vaccines.get(3).getNewPeoplePerDay());
            q31.setLocation(315, vac4y+7);
            q31.setSize(new Dimension(20, 25));
            panel2.add(q31);

            q32 = new JTextField();
            q32.setText(gs1.Vaccines.get(3).getResistance()+"");
            q32.setLocation(485, vac4y+7);
            q32.setSize(new Dimension(30, 25));
            panel2.add(q32);

            k10 = new JButton("Validate");
            k10.setLocation(531, vac4y+7);
            k10.setSize(new Dimension(100, 25));
            k10.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(q30.getText()!= ""){
                        int VacStart = (int) Double.parseDouble(q30.getText());
                        int sum = gs1.Vaccines.get(0).getPopulation();
                        sum += gs1.Vaccines.get(1).getPopulation();
                        sum += gs1.Vaccines.get(2).getPopulation();
                        if(VacStart>=0&&VacStart+sum<=gs1.getTotalPopulation()-gs1.getCovidStartPopulation()){
                            gs1.setTotalVaccinatedPopulation(VacStart+sum);
                            gs1.Vaccines.get(3).setPopulation(VacStart);
                            System.out.println("Vaccines: "+gs1.getTotalVaccinatedPopulation());
                            panel2.revalidate();
                        }
                        else {
                            q30.setText(gs1.Vaccines.get(3).getPopulation()+"");
                            panel2.revalidate();
                        }
                        int newPerDay = (int) Double.parseDouble(q31.getText());
                        if(newPerDay>=0&&newPerDay<gs1.getTotalPopulation()-gs1.getCovidStartPopulation()) {
                            gs1.Vaccines.get(3).setNewPeoplePerDay(newPerDay);
                            panel2.revalidate();
                        }
                        else{
                            q31.setText(gs1.Vaccines.get(3).getNewPeoplePerDay()+"");
                        }
                        int resistance = (int) Double.parseDouble(q32.getText());
                        if(resistance>=0&&resistance<=100){
                            gs1.Vaccines.get(3).setResistance(resistance);
                            panel2.revalidate();
                        }
                        else{
                            q32.setText(gs1.Vaccines.get(3).getResistance()+"");
                        }
                    }
                }
            });
            panel2.add(k10);


            k2 = new JButton("15x15");
            k2.setLocation(100, 0);
            k2.setSize(new Dimension(70, 50));
            k2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LabelMaker(a2, "Map Size Accepted", 600, 85, 100,75, panel2);
                    squareSide = 15;
                    MapSizeSetter(15);
                    k2.setBackground(Color.blue);
                    k2.setForeground(Color.white);
                    k4.setBackground(null);
                    k4.setForeground(Color.black);
                    k3.setBackground(null);
                    k3.setForeground(Color.black);
                    BoxFunction();
                }
            });
            panel2.add(k2);

            k2.setBackground(Color.blue);
            k2.setForeground(Color.white);

            k3 = new JButton("30x30");
            k3.setLocation(200, 0);
            k3.setSize(new Dimension(70, 50));
            k3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LabelMaker(a2, "Map Size Accepted", 600, 85, 100,75, panel2);
                    squareSide = 30;
                    MapSizeSetter(30);
                    k3.setBackground(Color.blue);
                    k3.setForeground(Color.white);
                    k2.setBackground(null);
                    k2.setForeground(Color.black);
                    k4.setBackground(null);
                    k4.setForeground(Color.black);
                    BoxFunction();
                }
            });
            panel2.add(k3);

            k4 = new JButton("45x45");
            k4.setLocation(300, 0);
            k4.setSize(new Dimension(70, 50));
            k4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LabelMaker(a2, "Map Size Accepted", 600, 85, 100,75, panel2);
                    panel2.revalidate();
                    squareSide = 45;
                    MapSizeSetter(45);
                    k4.setBackground(Color.blue);
                    k4.setForeground(Color.white);
                    k2.setBackground(null);
                    k2.setForeground(Color.black);
                    k3.setBackground(null);
                    k3.setForeground(Color.black);
                    BoxFunction();
                }
            });
            panel2.add(k4);

            g = new JLabel(" ");    //Box Text
            g.setLocation(470, 145);
            g.setSize(new Dimension(100, 50));
            panel.add(g);

            PlayerID = new JLabel("");
            PlayerID.setLocation(470, 170);
            PlayerID.setSize(new Dimension(150, 50));
            panel.add(PlayerID);

            position = new JLabel("");
            position.setLocation(470, 190);
            position.setSize(new Dimension(150, 50));
            panel.add(position);

            condition = new JLabel("");
            condition.setLocation(470, 210);
            condition.setSize(new Dimension(150, 50));
            panel.add(condition);

            status = new JLabel("");
            status.setLocation(470, 230);
            status.setSize(new Dimension(150, 50));
            panel.add(status);

            Vaccinated = new JLabel("");
            Vaccinated.setLocation(470, 250);
            Vaccinated.setSize(new Dimension(150, 50));
            panel.add(Vaccinated);

            Vacine = new JLabel("");
            Vacine.setLocation(470, 270);
            Vacine.setSize(new Dimension(190, 50));
            panel.add(Vacine);

            resistance = new JLabel("");
            resistance.setLocation(470, 290);
            resistance.setSize(new Dimension(150, 50));
            panel.add(resistance);

            daysofsickness = new JLabel("");
            daysofsickness.setLocation(470, 310);
            daysofsickness.setSize(new Dimension(150, 50));
            panel.add(daysofsickness);

            numCols =squareSide;
            numRows = squareSide;

            f.getContentPane().add(panel2);
            f.setSize(650, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

            MapSizeSetter(squareSide);
            BoxFunction();

            sickPopulation = new JLabel("");
            sickPopulation.setText("Infected People: "+gs1.getCovidStartPopulation()+",      "+gs1.getCovidStartPopulation()*100/gs1.getTotalPopulation()+" %");
            sickPopulation.setLocation(460, 100);
            sickPopulation.setSize(new Dimension(180, 50));
            panel.add(sickPopulation);

            vacPopulation = new JLabel("");
            vacPopulation.setText("Vaccinated Percent: "+gs1.getTotalVaccinatedPopulation()*100/gs1.getTotalPopulation()+" %");
            vacPopulation.setLocation(460, 130);
            vacPopulation.setSize(new Dimension(180, 50));
            panel.add(vacPopulation);

            a = new JButton("Next");           //StepButton
            a.setLocation(455, 400);
            a.setSize(new Dimension(70, 50));
            a.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playerPos1=gs1.NextDay(playerPos1, squareSide);
                    BoxFunction();
                    panel.revalidate();
                    panel.repaint();
                    c.setText("Current Day: " + gs1.getDay());
                    sickPopulation.setText("Infected People: "+gs1.getInfectedPeople()+",      "+gs1.getInfectedPeople()*100/gs1.getTotalPopulation()+" %");
                    vacPopulation.setText("Vaccinated Percent: "+gs1.getTotalVaccinatedPopulation()*100/gs1.getTotalPopulation());
                }
            });
            panel.add(a);

            h = new JButton(">>");           //StepButton
            h.setLocation(528, 400);
            h.setSize(new Dimension(50, 50));
            h.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    x++;
                    c.setText("Current Day: " + x);
                }
            });
            panel.add(h);

            j = new JButton("x2");           //StepButton
            j.setLocation(580, 400);
            j.setSize(new Dimension(50, 50));
            j.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    x++;
                    c.setText("Current Day: " + x);
                }
            });
            panel.add(j);

            c = new JLabel("Current Day: 0");    //CurrentStep Text
            c.setLocation(500, 350);
            c.setSize(new Dimension(100, 50));
            panel.add(c);




        }



        public void NextPanel() {
            panel.revalidate();
            f.getContentPane().removeAll();
            f.setContentPane(panel);
            f.getContentPane().revalidate();
            f.getContentPane().repaint();
        }
        public void BoxFunction()
        {
            for (int i = 0; i < squareSide; i++) {
                for (int h = squareSide-1; h >= 0; h--) {
                    int finalI = i;
                    int finalH = h;
                    mapbuttons[i][h].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent q) {
                            //if(playerPos1[finalI][finalH].getxPos()!=0&&playerPos1[finalI][finalH].getyPos()!=0)
                            if(playerPos1[finalI][finalH]!=null) {
                                g.setText("Person Selected");
                                position.setText("-  position:         "+"("+playerPos1[finalI][finalH].getxPos()+","+playerPos1[finalI][finalH].getyPos()+")");
                                PlayerID.setText("-  player ID:          "+playerPos1[finalI][finalH].getPlayerID());
                                if(playerPos1[finalI][finalH].getHasCovid()){
                                    condition.setText("-  condition:     Infected");
                                    daysofsickness.setText("- days infected:  "+ (gs1.getDay()-playerPos1[finalI][finalH].getInfectionStartDay()));
                                }
                                else{
                                    condition.setText("-  condition:       Healthy");
                                    daysofsickness.setText("");

                                }
                                if(playerPos1[finalI][finalH].getIsQuarantined())
                                    status.setText("-  status:       Quarantined");
                                else
                                    status.setText("-  status:            Roaming");
                                if(playerPos1[finalI][finalH].getIsVaccinated()){
                                    Vaccinated.setText("-  vaccinated:     Yes");
                                    Vacine.setText("-  vaccine:          "+playerPos1[finalI][finalH].getVaccine().getName());
                                    resistance.setText("-  resistance:      "+playerPos1[finalI][finalH].getVaccine().getResistance()+"%");
                                }
                                else
                                {
                                    Vaccinated.setText("-  vaccinated:      No");
                                    Vacine.setText("");
                                    resistance.setText("");
                                }
                            }
                            else {
                                g.setText("<empty square>");
                                position.setText("");
                                PlayerID.setText("");
                                condition.setText("");
                                status.setText("");
                                Vaccinated.setText("");
                                Vacine.setText("");
                                resistance.setText("");
                                daysofsickness.setText("");
                            }
                        }
                    });

                    if(playerPos1[finalI][finalH]!=null)
                    {
                        if(!playerPos1[finalI][finalH].getIsQuarantined()) {
                            if (!playerPos1[finalI][finalH].getHasCovid()) {
                                if (playerPos1[finalI][finalH].getIsVaccinated()) {
                                    mapbuttons[finalI][finalH].setBackground(Color.green); // button color changes with player's state
                                } else {
                                    mapbuttons[finalI][finalH].setBackground(Color.blue); // button color changes with player's state
                                }
                            } else {
                                mapbuttons[finalI][finalH].setBackground(Color.red); // button color changes with player's state
                            }
                            //mapbuttons[finalI][finalH].setBackground(Color.red); // button color changes with player's state
                        }
                        else{
                            mapbuttons[finalI][finalH].setBackground(Color.black);
                        }
                    }
                    else
                        mapbuttons[finalI][finalH].setBackground(Color.white); // button color changes with player's state
                }
            }
        }
        public void MapSizeSetter(int Size)
        {
            for(int i =0; i<mapbuttons.length; i++)
                for(int h =0; h<mapbuttons.length;h++)
                    panel.remove(mapbuttons[i][h]);
            squareSide = Size;
            mapbuttons = new JButton[squareSide][squareSide];
            mapbuttons = addButtonsA(panel, squareSide, squareSide);
            for(int i =0; i<mapbuttons.length; i++)
                for(int h =0; h<mapbuttons.length;h++)
                    panel.add(mapbuttons[i][h]); // buttons

            int a=0;

            playerPos1 = gs1.PlayerMaker(squareSide);
        }
        public void LabelMaker(JLabel test, String name,int locX, int locY, int width, int height,JPanel thepanel)
        {
            test = new JLabel((name));
            test.setLocation(locX ,locY);
            test.setSize(new Dimension(width, height));
            thepanel.add(test);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Next Day")) {
                x++;
                c.setText("Current Day: " + x);
            } else if (e.getActionCommand().equals("proceed")) {
                NextPanel();
            }
        }

        private static JButton[][] addButtonsA(JPanel panel, int numRows, int numCols) {
            JButton[][] mapbuttons1 = new JButton[numRows][numCols];
            int size = 450 / numCols;
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    JButton b = new JButton();
                    b.setLocation(c * size, r * size);

                    b.setBackground(Color.white);

                    b.setSize(new Dimension(size, size));

                    mapbuttons1[c][r] = b;

                    panel.add(b);
                }
            }
            return mapbuttons1;
        }
    }
}
