package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

import java.util.*;
/**
 *
 * <p>La classe implementa le responsabilità dell'astrazione ({@code GameEngineInterface}).
 * <ul>
 *   <li>Aggiorna il turno per ogni giocatore in vita, alla fine di ogni turno aggiorna lo stato di ogni giocatore e lo stato della partita.</li>
 *   <li>Implementa metodi per il controllo dello status dei elementi di gioco.</li>
 *   <li>Implementa metodi per gestire l'aggiunta di giocatori.</li>
 * </ul>
 * <p>Questa classe implementa l'interfaccia {@link GameEngineInterface}.
 *
 * @author lucaporfiri
 *
 * @see Tracciato
 * @see Giocatore
 */

public class GameEngine implements GameEngineInterface {
    private Tracciato tracciato;
    private ArrayList <Giocatore> giocatori;
    private int turnoCorrente;
    private boolean garaInCorso;
    private Giocatore winner;

    public GameEngine() {
        this.giocatori = new ArrayList<>();
        this.turnoCorrente = 0;
        this.garaInCorso = false;
    }
    //getter & setter
    public void setTracciato(Tracciato tracciato) {
        if (tracciato==null)
            throw new NullPointerException("Null tracciato");
        this.tracciato = tracciato;
    }

    public void setGiocatori(ArrayList<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public void setTurnoCorrente(int turnoCorrente) {
        this.turnoCorrente = turnoCorrente;
    }

    public void setWinner(Giocatore winner) {
        this.winner = winner;
    }
    public void setGaraInCorso(boolean garaInCorso) {
        this.garaInCorso = garaInCorso;
    }

    public Tracciato getTracciato() {
        return tracciato;
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public int getTurnoCorrente() {
        return turnoCorrente;
    }

    public Giocatore getWinner() {
        return winner;
    }

    public boolean isGaraInCorso() {
        return garaInCorso;
    }

    //METODI
    public void aggiungiGiocatore(Giocatore g){
        giocatori.add(g);
    }

    public void aggiornaGara(){
        for (Giocatore giocatore : giocatori) {
            if(giocatore.isAlive()){
                giocatore.muovi(this.tracciato, giocatori);
                this.controlloStatusGiocatore(giocatore);
            }
        }
        //fine turno
        turnoCorrente++;
        this.controlloStatusPartita();
        this.tracciato.resetTrack(this.giocatori);
        this.tracciato.updateTrackWithPlayers(this.giocatori);
    }

    private void controlloStatusGiocatore(Giocatore player){
        if(this.controllaCollisione(player))
            player.setAlive(false);
        else
            this.controllaVittoria(player);
        if(this.winner != null)
            for(Giocatore giocatore : giocatori){
                giocatore.setAlive(false);
            }
    }

    private boolean controllaCollisione(Giocatore giocatore){
        if (tracciato.isOutOfMap(giocatore.getPosizione()) ||
                tracciato.isOutOfRace(giocatore.getPosizione())) {
            return true;
        }
        for (Giocatore otherPlayer : this.giocatori) {
            // Se la posizione è uguale a quella di un altro giocatore che è "morto" allora è collisione
            if (!otherPlayer.equals(giocatore) && !otherPlayer.isAlive()) {
                if (otherPlayer.getPosizione().equals(giocatore.getPosizione())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void controllaVittoria(Giocatore giocatore){
        Posizione posizionePrecedente = giocatore.getPosizionePrecedente();
        Posizione posizioneAttuale = giocatore.getPosizione();
        ArrayList<Posizione> finishLine = tracciato.getFinishLine();
        for (Posizione posizioneTraguardo : finishLine) {
            if (this.hasCrossedTheFinishLine(posizionePrecedente, posizioneAttuale, posizioneTraguardo)) {
                giocatore.setAlive(false);
                this.winner = giocatore;
                return;
            }
        }
    }

    private void controlloStatusPartita(){
        if(winner != null || !isAnyoneAlive())
            this.setGaraInCorso(false);
    }

    private boolean isAnyoneAlive(){
        for(Giocatore giocatore : giocatori){
            if(giocatore.isAlive()){
                return true;
            }
        }
        return false;
    }

    public void iniziaGara(){
        this.garaInCorso = true;
        for(Giocatore giocatore : giocatori)
            giocatore.setAlive(true);
        this.tracciato.updateTrackWithPlayers(this.giocatori);
    }

    private boolean hasCrossedTheFinishLine(Posizione posizionePrecedente,
                                           Posizione posizioneAttuale, Posizione finishLine) {
        int prevX= posizionePrecedente.getX();
        int prevY= posizionePrecedente.getY();
        int attX= posizioneAttuale.getX();
        int attY= posizioneAttuale.getY();
        int finishX= finishLine.getX();
        int finishY= finishLine.getY();
        return(finishX>=Math.min(prevX,attX) && finishX<=Math.max(prevX,attX) &&
                finishY>=Math.min(prevY,attY) && finishY<=Math.max(prevY,attY));
    }

    public void playerBotFarm(int numPlayer) {
        int index=1;
        while(index<=numPlayer){
            char simboloGiocatore = (char) ('0' + index);
            this.aggiungiGiocatore(new GiocatoreBot(this.tracciato, "Giocatore"+index,simboloGiocatore));
            index++;
        }
    }
}
