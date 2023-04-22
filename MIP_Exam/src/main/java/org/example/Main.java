package org.example;

import org.example.database.dao.CatchDao;
import org.example.database.dao.CatchEntity;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner= new Scanner(System.in);
    private static boolean isRunning = true;

    private static int dimensiuneCatch=0;

    public static void main(String[] args) {

        System.out.println("Dati dimensiunea catch-ului:");
        dimensiuneCatch=scanner.nextInt();
        scanner.nextLine();
        while (isRunning) {
            String s;
            s = scanner.nextLine();
            showMenu();
            getChoice();
        }
    }

    public static void showMenu() {
        System.out.println("1.ADAUGARE CATCH");
        System.out.println("2.CAUTARE");
        System.out.println("0.EXIT");
    }
    public static void getChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            choice = 10;
        }
        switch (choice) {
            case 1:
                addCatch();
                break;
            case 2:
                searchCatch();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                //clearScreen();
                System.out.println("INVALID CHOICE");
                scanner.nextLine();
        }
    }

    private static void searchCatch() {
        String s;
        s= scanner.nextLine();
        System.out.println("INTRODU NUMELE CATCH-ULUI:");
        CatchDao echipaDao = new CatchDao();
        String nume = scanner.nextLine();
        nume = nume.toUpperCase();
        CatchEntity echipa = echipaDao.getByNume(nume);
        if(echipa == null){
            System.out.println("CATCH NOT FOUND");
            return;
        }
        int x=echipa.getCautara();
        System.out.println("cautari:" + x);
        echipaDao.updateCautara(echipa.getId(),echipa.getCautara()+1);
        printBar();
        showEchipa(echipa);
    }
    static void showEchipa(CatchEntity movie){
        System.out.println("NUME: " + movie.getNume());
        System.out.println("ORA: " + movie.getOra());
        System.out.println("MINUTE: " + movie.getMinute());
        printBar();
    }
    static void printBar(){
        System.out.println("------------------------------");
    }

    private static void addCatch() {
        String s;
        s = scanner.nextLine();
        CatchDao echipaDao = new CatchDao();
        List<CatchEntity> echipaEntities = echipaDao.getAll();

        scanner.nextLine();
        //clearScreen();
        CatchEntity echipa = new CatchEntity();
        System.out.println("INTRODUCERE CATCH");

        System.out.print("NUME CATCH:");
        String nume = scanner.nextLine();
        nume = nume.toUpperCase();

        System.out.print("ORA:");
        int ora = scanner.nextInt();
        scanner.nextLine();

        while(ora>=24)
        {
            System.out.print("NU AI INTRODUS BINE ORA,MAI INCEARCA:");
            ora = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("MINUTE:");
        int minute = scanner.nextInt();
        scanner.nextLine();

        while(minute>=60)
        {
            System.out.print("NU AI INTRODUS BINE MINUTELE,MAI INCEARCA:");
            minute = scanner.nextInt();
            scanner.nextLine();
        }

        if (echipaEntities.size() == dimensiuneCatch) {
            System.out.println("S-a sters un catch");
            echipaDao.deleteCatch();
        }

        echipa.setNume(nume);
        echipa.setMinute(minute);
        echipa.setOra(ora);
        echipa.setCautara(1);

        CatchDao movieDao = new CatchDao();
        movieDao.create(echipa);
        System.out.println("CATCH ADDED SUCCESFULLY");
    }
}