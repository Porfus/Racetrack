package it.unicam.cs.porfiriluca.progettofinaledatemplate.view;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Giocatore;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Tracciato;
/**
 * L'interfaccia {@code ConsoleViewInterface} fornisce metodi di supporto per la stampa su console
 * del tracciato e giocatori di una gara.
 * Viene implementata da ({@link ConsoleView})
 **/
public interface ConsoleViewInterface {
     /**
      * Stampa la matrice che rappresenta il tracciato
      * @param tracciato
      */
     void stampa (Tracciato tracciato);

     /**
      * Stama lo stato dei giocatori
      * @param engine
      */
     void stampaStatoGiocatori(GameEngine engine);

     /**
      * Se presente stampa una stringa che indica il vincitore della gara
      * @param giocatore
      */
     void showWinner(Giocatore giocatore);

     /**
      * Stampa una stringa che indica che tutti i giocatori hanno perso
      */
     void showLoser();

     /**
      * Chiede quale traccia predefinita selezionare
      */
     void OutputSelectTrack();
    }
