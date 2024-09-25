package it.unicam.cs.porfiriluca.progettofinaledatemplate;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;

/**
 * L'interfaccia {@code ConsoleInputHandlerInterface} fornisce metodi di supporto per la gestione
 * dell’input da console, come il numero di giocatori o la selezione del
 * tracciato, fornendo un supporto al controller.
 * * Viene implementata da ({@link ConsoleInputHandler})
 **/
public interface ConsoleInputHandlerInterface {
    /**
     * Chiede se continuare la gara
     * @param engine
     */
    void chiediSeContinuare(GameEngine engine, boolean confirm);

    /**
     * Chiede di inserire il numero di GiocatoriBot con cui iniziare
     * @param engine
     */
    void InputPlayersNumber(GameEngine engine);

    /**
     * Chiede quale tracciato predefinita scegliere (massimo 1 tracciato)
     * @param engine
     */
    void InputSelectTrack(GameEngine engine);

}
