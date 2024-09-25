package it.unicam.cs.porfiriluca.progettofinaledatemplate.view;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Giocatore;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Tracciato;

import java.util.ArrayList;

/**
 *
 * <p>La classe ({@code ConsoleView} implementa {@link ConsoleViewInterface}
 * <ul>
 *   <li>Stampa il tracciato come una matrice di caratteri.</li>
 *   <li>Implementa metodi per la stampa di messaggi in console.</li>
 * </ul>
 * <p>
 *
 * @author lucaporfiri
 *
 */
public class ConsoleView implements ConsoleViewInterface {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[95m";

    public ConsoleView() {
    }

    public static void pause(long timeInMilliSeconds) {
        long timestamp = System.currentTimeMillis();
        do {
        } while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);

    }

    public void stampaIntroduzione(){
        System.out.println(MAGENTA + " ______     ______     ______     ______     ______   ______     ______     ______     __  __    \n" +
                "/\\  == \\   /\\  __ \\   /\\  ___\\   /\\  ___\\   /\\__  _\\ /\\  == \\   /\\  __ \\   /\\  ___\\   /\\ \\/ /    \n" +
                "\\ \\  __<   \\ \\  __ \\  \\ \\ \\____  \\ \\  __\\   \\/_/\\ \\/ \\ \\  __<   \\ \\  __ \\  \\ \\ \\____  \\ \\  _\"-.  \n" +
                " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\    \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\ \n" +
                "  \\/_/ /_/   \\/_/\\/_/   \\/_____/   \\/_____/     \\/_/   \\/_/ /_/   \\/_/\\/_/   \\/_____/   \\/_/\\/_/ \n" +
                "                                                                                                 \n"+RESET);
        System.out.print("Avvio");
        pause(1000);
        System.out.print(".");
        pause(1000);
        System.out.print(".");
        pause(1000);
        System.out.print(".\n\n");
        pause(1000);
        System.out.println(MAGENTA+"------------------\n"+RESET);
        pause(1000);
        System.out.println("\nIntroduzione:\n\n");
        System.out.println("\tE'possibile scegliere tra 5 tipi di tracciato preimpostati\n");
        System.out.print("\tIl tracciato è rappresentato da una matrice di caratteri che ha come indice delle posizioni (0,0) in alto a sinistra, fino\n" +
                " \ta (n,m) in basso a destra, con n e m rispettivamente lunghezza e larghezza del tracciato\n\n");
        System.out.println("\tLa "+BLUE+"'@'"+RESET+" rappresenta il punto di partenza, la sequenza di caratteri "+MAGENTA+"'|'"+RESET+" rappresentano il traguardo\n");
        System.out.println("\tOgni giocatore è rappresentato dal carattere Ascii corrispondente al suo numero (es. giocatore1 -> '1')\n");
    }

    public void stampa (Tracciato tracciato) {
        if (tracciato == null) {
            throw new NullPointerException("Null tracciato");
        }
        ArrayList<ArrayList<Character>> trackMatrix = tracciato.getTrackMatrix();
        for(ArrayList row : trackMatrix){
            for(Object o : row){
                if(o.equals('#'))
                    System.out.print(RED+o+RESET);
                else if(o.equals('*'))
                    System.out.print(YELLOW+o+RESET);
                else if(o.equals('|'))
                    System.out.print(MAGENTA+o+RESET);
                else
                    System.out.print(BLUE+o+RESET);
            }
            System.out.println();
        }
    }

    public void stampaStatoGiocatori(GameEngine engine) {
        if (engine == null) {
            throw new NullPointerException("Null engine");
        }
        System.out.println(YELLOW+"Turno:\t"+RESET+engine.getTurnoCorrente());
        System.out.println(YELLOW+"Nome giocatore:"+"\t"+"Posizione:"+"\t"
                +"Velocita':"+"\t"+"E'vivo:"+"\t"+"Mosse disponibili:"+RESET);
        for(Giocatore giocatore : engine.getGiocatori()){
            System.out.printf(RESET + "%-15s %-15s %-15s %-7s %s%n",
                    giocatore.getNome(),
                    giocatore.getPosizione().toString(),
                    giocatore.getVelocita().toString(),
                    giocatore.isAlive(),
                    giocatore.possibleMoves().toString()
            );
        }
    }

    public void showWinner(Giocatore giocatore){
        if (giocatore == null) {
            throw new NullPointerException("Null giocatore");
        }
        System.out.println("And the winner is..."+giocatore.getNome()+"...congratulations");
    }

    public void showLoser(){
        System.out.println("Everyone...\n"+"...died");
    }

    public void OutputSelectTrack(){
        System.out.println("Seleziona il tracciato: ");
        pause(1500);
        System.out.println("1. Tracciato 1");
        this.stampa(new Tracciato("Tracciato1.txt"));
        System.out.println("2. Tracciato 2");
        this.stampa(new Tracciato("Tracciato2.txt"));
        System.out.println("3. Tracciato 3");
        this.stampa(new Tracciato("Tracciato3.txt"));
        System.out.println("4. Tracciato 4");
        this.stampa(new Tracciato("Tracciato4.txt"));
        System.out.println("5. Tracciato 5");
        this.stampa(new Tracciato("Tracciato5.txt"));
    }

}
