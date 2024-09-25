package it.unicam.cs.porfiriluca.progettofinaledatemplate.controller;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.view.ConsoleView;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.ConsoleInputHandler;
/**
 * L'astrazione {@code GameControllerInterface} fornisce metodi di supporto per
 * la gestione dell'interazione tra il modello di gioco
 * ({@link GameEngine}), le viste ({@link ConsoleView})
 * e l'handler dell'input dalla console ({@link ConsoleInputHandler}).
 * Viene implementata da ({@link GameController})
 **/
public interface GameControllerInterface {
     /**
      * @return  la vista da console {@code ConsoleView} associata al controller
      */
     ConsoleView getConsoleView();
     /**
      * @return il motore di gioco {@code GameEngine} associato al controller
      */
     GameEngine getEngine();
     /**
      * @return il gestore degli input da console {@code ConsoleInputHandler}
      * {@see ConsoleInputHandler}
      */
     ConsoleInputHandler getInputHandler();

     /**
      * Inizializza il motore di gioco per l'inizio della gara, e lo aggiorna fino a che non termina la gara.
      * Ad ogni aggiornamento stampa lo stato della partita e il tracciato.
      * Quando la partita termina, stampa l'ultimo aggiornamento con l'esito della partita
      */
     void startRace();

     /**
      * Stampa la vista {@code ConsoleView}
      */
     void stampaVista();

    }
