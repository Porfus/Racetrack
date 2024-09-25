package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

import java.io.*;
import java.util.*;
/**
 *
 * <p>La classe ({@code Tracciato} implementa {@link TracciatoInterface}
 * <ul>
 *   <li>Lo fa caricando il tracciato da costruttore, attraverso il metodo caricaTracciatoDaFile(), che utilizza InputStream.</li>
 *   <li>Rappresenta il tracciato in un ArrayList di ArrayList.</li>
 *   <li>Implementa metodi di supporto per l'aggiornamento e il reset del tracciato.</li>
 *   <li>Inoltre fornisce informazioni sulla struttura del tracciato stesso tramite metodi che accettano parametri Poszione.</li>
 * </ul>
 * <p>
 *
 * @author lucaporfiri
 * @see Posizione
 */
public class Tracciato implements TracciatoInterface {
    private final ArrayList<ArrayList<Character>> trackMatrix = new ArrayList<ArrayList<Character>>();
    ArrayList<Posizione> finishLine;

    public Tracciato(String filePath){
        if(filePath == null)
            throw new NullPointerException("Etichetta della radice null");
        if (filePath.isEmpty())
            throw new IllegalArgumentException(
                    "Tentativo di caricare un tracciato non esistente");
        caricaTracciatoDaFile(filePath);
        setFinishLine();
        matrixCheck();
    }

    private void matrixCheck() throws IllegalArgumentException {
        if (getTrackMatrix()==null || getTrackMatrix().isEmpty())
            throw new IllegalArgumentException("La matrice è mancante.");
        if(getFinishLine().isEmpty())
            throw new IllegalArgumentException("La linea di arrivo è mancante.");
        if(getPartenza() == null)
            throw new IllegalArgumentException("La posizione di partenza è mancante.");
        for (ArrayList<Character> row : getTrackMatrix()) {
            for (Character c : row) {
                if (c == null || (c != '#' && c != '@' && c != '|' && c!='*')) {
                    throw new IllegalArgumentException("Carattere non valido nel tracciato: " + c);
                }
            }
        }
    }

    private void caricaTracciatoDaFile(String fileName){
        try{
            InputStream inStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inStream == null) {
                System.out.println("File not found: " + fileName);
                return;
            }
            Scanner input = new Scanner(inStream);
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                ArrayList<Character> col = new ArrayList<>();
                for(char ch : line.toCharArray())
                {
                    col.add(ch);
                }
                trackMatrix.add(col);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<Character>> getTrackMatrix() {
            return trackMatrix;
    }

    public Posizione getPartenza() {
        if(this.trackMatrix.isEmpty()){
            System.out.println("Track matrix is empty.");
            return null;
        }
        int x=0;
        for(ArrayList col: this.trackMatrix){
            int y=0;
            for(Object carattere: col){
                if(carattere.equals('@')){
                    return new Posizione(x, y);
                }
                y++;
            }
            x++;
        }
        System.out.println("Start position '@' not found in track matrix.");
        return null;
    }

    private void setFinishLine(){
        if(trackMatrix.isEmpty())
            return;
        ArrayList<Posizione> traguardo = new ArrayList<>();
        int x=0;
        for(ArrayList col: trackMatrix){
            int y=0;
            for(Object carattere: col){
                if(carattere.equals('|')){
                    traguardo.add(new Posizione(x, y));
                }
                y++;
            }
            x++;
        }
         this.finishLine=traguardo;
    }

    public ArrayList<Posizione> getFinishLine() {
        return finishLine;
    }

    public int getRowLength() {
        return trackMatrix.size();
    }
    public int getColLength() {
        int rowLength=getRowLength();
        if  (rowLength > 0) {
            return trackMatrix.get(0).size();
        }else
            return 0;
    }

    public boolean isOutOfRace(Posizione playerPosition) {
        if (playerPosition == null)
            throw new NullPointerException("Posizione null");
        int newX=playerPosition.getX();
        int newY=playerPosition.getY();
        return trackMatrix.get(newX)
                 .get(newY).equals('#');
    }

    public boolean isOutOfMap(Posizione playerPosition) {
        if (playerPosition == null) {
            throw new NullPointerException("Posizione null");
        }
        int newX=playerPosition.getX();
        int newY=playerPosition.getY();
        int rowLength=getRowLength();
        int colLength=getColLength();
        return newX<0 || newY<0 || newX >= rowLength || newY >= colLength;
    }

    public void resetTrack(ArrayList<Giocatore> giocatori) {
        for (ArrayList<Character> row : trackMatrix) {
            for (int col = 0; col < row.size(); col++) {
                for(Giocatore giocatore: giocatori){
                    if (row.get(col).equals(giocatore.getCarattere())) {
                        row.set(col, '*');
                    }
                }
            }
        }
    }

    public void updateTrackWithPlayers(ArrayList<Giocatore> giocatori) {
        //aggiorna il tracciato con i giocatori
        for(Giocatore giocatore : giocatori){
            //se è fuori dalla mappa non lo stampa
            if(!this.isOutOfMap(giocatore.getPosizione()))
                this.trackMatrix.get(giocatore.getPosizione().getX()).
                        set(giocatore.getPosizione().getY(), giocatore.getCarattere());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracciato tracciato = (Tracciato) o;
        return Objects.equals(this.getTrackMatrix(), tracciato.getTrackMatrix());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrackMatrix());
    }
}
