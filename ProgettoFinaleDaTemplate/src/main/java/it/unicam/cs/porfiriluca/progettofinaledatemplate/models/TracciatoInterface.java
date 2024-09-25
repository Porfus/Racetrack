package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

import java.util.ArrayList;

/**
 * L'interfaccia {@code TracciatoInterface} fornisce metodi di supporto per il caricamento
 * e rappresentazione del tracciato sotto forma di matrice di caratteri, e caricamento e rappresentazione
 * di giocatori in una gara.
 * Viene implementata da ({@link Tracciato})
 **/
public interface TracciatoInterface {

     /**
      * @return La matrice del tracciato
      */
     ArrayList<ArrayList<Character>> getTrackMatrix();

     /**
      * @return La posizione di partenza, null se la matrice è vuota
      */
     Posizione getPartenza();

     /**
      *
      * @return L'array di Posizioni del traguardo nella matrice, null se questa è vuoto
      */
     ArrayList<Posizione> getFinishLine();

     /**
      *
      * @return La larghezza della matrice
      */
     int getRowLength();

     /**
      *
      * @return La lunghezza della matrice
      */
     int getColLength();

     /**
      *
      * @param playerPosition
      * @return un valore booleano che indica se la posizione è al di fuori del tracciato di gioco
      */
     boolean isOutOfRace(Posizione playerPosition);

     /**
      * @param playerPosition
      * @return un valore booleano che indica se la posizione è al di fuori della matrice
      */
     boolean isOutOfMap(Posizione playerPosition);

     /**
      * Resetta la matrice del tracciato allo stato iniziale
      * @param giocatori
      */
     void resetTrack(ArrayList<Giocatore> giocatori);

     /**
      * Aggiorna la matrice con tutti i giocatori presenti al suo interno
      * @param giocatori
      */
     void updateTrackWithPlayers(ArrayList<Giocatore> giocatori);

    }
