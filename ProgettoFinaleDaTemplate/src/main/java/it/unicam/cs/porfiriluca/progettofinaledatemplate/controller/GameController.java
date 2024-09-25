package it.unicam.cs.porfiriluca.progettofinaledatemplate.controller;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.ConsoleInputHandler;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.view.ConsoleView;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;

/**
 *
 * <p>La classe soddisfa le responsabilità dell'astrazione {@code GameControllerInterface}
 * <ul>
 *   <li>implementando il metodo public {@code start()}, avviando la gara e gestendo l'inizializzazione del tracciato e dei giocatori.</li>
 *   <li>Gestisce il ciclo di aggiornamento della gara, aggiornando lo stato del gioco
 *   e la vista a ogni turno.</li>
 *   <li>Determina il vincitore al termine della gara e gestire la visualizzazione
 *   dell'esito della gara.</li>
 *   <li>Gestisce l'interazione dell'utente per proseguire o terminare il gioco
 *   dopo ogni turno.</li>
 * </ul>
 * <p>Questa classe implementa l'interfaccia {@link GameControllerInterface},
 * fornendo i metodi necessari per avviare e gestire la gara.
 *
 * @author lucaporfiri
 *
 * @see ConsoleView
 * @see GameEngine
 * @see ConsoleInputHandler
 */
public class GameController implements GameControllerInterface {
    private final ConsoleView consoleView;
    private final GameEngine engine;
    private final ConsoleInputHandler inputHandler;
    private boolean confirmAfterTurn;

    public GameController(ConsoleView console, GameEngine engine, ConsoleInputHandler inputHandler) {
        if (console == null || engine == null || inputHandler == null)
            throw new NullPointerException();
        this.consoleView = console;
        this.engine = engine;
        this.inputHandler = inputHandler;
        this.confirmAfterTurn=true;
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public ConsoleInputHandler getInputHandler() {
        return inputHandler;
    }

    public void startRace() {
        consoleView.stampaIntroduzione();
        inputHandler.chiediSeContinuare(engine, confirmAfterTurn);
        // Inizializza tracciato e giocatori
        this.inizializzaGara();
        // Inizia la gara e mostra l'output iniziale
        engine.iniziaGara();
        this.mostraStatoIniziale();
        while (engine.isGaraInCorso()) {
            this.aggiornaTurno();
        }
        if(engine.getWinner()!=null)
            consoleView.showWinner(engine.getWinner());
        else
            consoleView.showLoser();
        inputHandler.chiediSeContinuare(engine, confirmAfterTurn);
    }

    private void inizializzaGara(){
        consoleView.OutputSelectTrack();
        inputHandler.InputSelectTrack(engine);
        inputHandler.InputPlayersNumber(engine);
        this.confirmAfterTurn=inputHandler.inputConfirmAfterEveryTurn();
    }

    private void mostraStatoIniziale() {
        consoleView.stampa(engine.getTracciato());
        consoleView.stampaStatoGiocatori(engine);
        System.out.println("Start!");
        inputHandler.chiediSeContinuare(engine, confirmAfterTurn);
    }

    private void aggiornaTurno() {
        engine.aggiornaGara();
        consoleView.stampa(engine.getTracciato());
        consoleView.stampaStatoGiocatori(engine);
        inputHandler.chiediSeContinuare(engine, confirmAfterTurn);
    }

    public void stampaVista(){
        consoleView.stampa(engine.getTracciato());
    }

}
