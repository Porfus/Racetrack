package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

import java.util.ArrayList;
/**
 * L'astrazione {@code GameEngineInterface} fornisce metodi di supporto per
 * la gestione della logica di simulazione della gara, coordina
 * le interazioni tra le componenti di gioco.
 * Viene implementata da ({@link GameEngine})
 **/
public interface GameEngineInterface {

     /**
      * Inizializza il tracciato
      * @param tracciato
      */
     void setTracciato(Tracciato tracciato);

     /**
      * Inizializza i giocatori
      * @param {giocatori}
      */
     void setGiocatori(ArrayList<Giocatore> giocatori);

     /**
      * Inizailizza il vincitore
      * @param winner
      */
     void setWinner(Giocatore winner);

     /**
      * Inizializza lo stato della gara
      * @param garaInCorso
      */
     void setGaraInCorso(boolean garaInCorso);
     Tracciato getTracciato();
     /**
      *
      * @return l'insieme {@code ArrayList<Giocatore>} contenente tutti i giocatori
      */
     ArrayList<Giocatore> getGiocatori();
     int getTurnoCorrente();

     /**
      * @return il giocatore {@code Giocatore} che ha vinto la partita. null se non è presente
      */
     Giocatore getWinner();

     /**
      * Metodo che verifica se la gara è ancora in corso
      * @return valore {@code boolean}
      */
     boolean isGaraInCorso();

     /**
      * Aggiunge un giocatore {@code Giocatore} alla gara
      */
     void aggiungiGiocatore(Giocatore g);

     /**
      * Inizializza le componenti di gioco per l'avvio della gara
      */
     void iniziaGara();

     /**
      *Aggiorna il turno per ogni giocatore ancora presente,
      * il suo stato e lo stato della partita
      */
     void aggiornaGara();

     /**
      * aggiunge numPlayer di ({@code GiocatoreBot} alla gara
      */
     void playerBotFarm(int numPlayer);

}
