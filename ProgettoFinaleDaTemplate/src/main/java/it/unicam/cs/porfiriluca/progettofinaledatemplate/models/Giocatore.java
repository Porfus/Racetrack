package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;


import java.util.ArrayList;

/**
 * L'astrazione {@code Giocatore} fornisce metodi di supporto per la
 * rappresentazione di un Giocatore che partecipa
 * alla gara, ne gestisce lo stato e le azioni all’interno del gioco
 * Viene implementata da ({@link GiocatoreBot})
 **/
public interface Giocatore {

    Posizione getPosizione();
    Velocita getVelocita();
    String getNome();
    boolean isAlive();
    Character getCarattere();
    Posizione getPosizionePrecedente();

    void setPosizione(Posizione posizione);
    void setVelocita(Velocita velocita);
    void setNome(String nome);
    void setAlive(boolean alive);

    /**
     * Trova una delle otto velocità {@code Velocita} disponibili e l'aggiorna, aggiorna la posizione {@code Posizione}
     * del giocatore {@code Giocatore}
     */
    void muovi(Tracciato tracciato, ArrayList<Giocatore> giocatori);

    /**
     * Metodo che ritorna le otto velocita {@code Velocita} disponibili
     * @return l'{@code ArrayList<Velocita>} di mosse possibili
     */
    ArrayList<Velocita> possibleMoves();

    /**
     * Memorizza la posizione {@code Posizione} precedente a quella attuale del {@code Giocatore}
     */
    void setPosizionePrecedente(Posizione posizione);
}