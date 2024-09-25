package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 *
 * <p>La classe ({@code GiocatoreBot} implementa i metodi dell'astrazione ({@link Giocatore}).
 * <ul>
 *   <li>Gestisce lo stato del giocatore, attraverso le classi ({@link Posizione} e ({@link Velocita}).</li>
 *   <li>Determina le mosse possibili e valide del Bot.</li>
 *   <li>Aggiorna la posizione sul tracciato con una nuova Posizione, ottenuta dalle posizione valide o possibili.</li>
 * </ul>
 * <p>
 *
 * @author lucaporfiri
 *
 * @see Posizione
 * @see Velocita
 */
public class GiocatoreBot implements Giocatore {
    private Posizione posizione;
    private Velocita velocita;
    private String nome;
    private boolean isAlive;
    private Posizione posizionePrecedente;
    private final Character carattere;

    public GiocatoreBot(Tracciato tracciato, String nome, Character carattere) {
        if(tracciato==null || nome == null || carattere == null)
            throw new NullPointerException();
        this.velocita= new Velocita(0,0);
        if(tracciato.getPartenza()==null)
            throw new IllegalArgumentException();
        this.posizione = tracciato.getPartenza();
        if (this.posizione == null) {
            throw new IllegalArgumentException("Invalid track: starting position not found.");
        }
        this.nome = nome;
        this.isAlive = true;
        this.posizionePrecedente = posizione;
        this.carattere = carattere;
    }

    @Override
    public void setPosizionePrecedente(Posizione posizionePrecedente) {
        this.posizionePrecedente = posizionePrecedente;
    }

    public void setPosizione(Posizione posizione) {
        if(posizione==null)
            throw new IllegalArgumentException();
        this.posizione = posizione;
    }

    public void setVelocita(Velocita velocita) {
        if(velocita==null)
            throw new IllegalArgumentException();
        this.velocita = velocita;
    }

    public void setNome(String nome) {
        if(nome==null)
            throw new IllegalArgumentException();
        this.nome = nome;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public Velocita getVelocita() {
        return velocita;
    }

    public String getNome() {
        return nome;
    }

    public Posizione getPosizionePrecedente() {
        return posizionePrecedente;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Character getCarattere() {
        return carattere;
    }

    /**
     *Trova le mosse valide per un GiocatoreBot tra quelle possibili, e se non ve ne sono
     * ne ritorna una tramite {@code getRandomMove} tra quelle possibili
     * Infine aggiorna la posizione e la velocita
     * @param tracciato
     * @param giocatori
     */
    public void muovi(Tracciato tracciato, ArrayList<Giocatore> giocatori) {
        if(tracciato==null || giocatori==null)
            throw new NullPointerException();
        Velocita nuovaMossa=new Velocita(0,0);
        ArrayList<Velocita> mossePossibili = possibleMoves();
        ArrayList<Velocita> mosseValide = getValidMove(mossePossibili , tracciato, giocatori);
        if(!mosseValide.isEmpty())
            nuovaMossa=getRandomMove(mosseValide);
        else
            nuovaMossa=getRandomMove(mossePossibili);
        this.aggiornaPosizioneVelocitaGiocatore(nuovaMossa);
    }

    private void aggiornaPosizioneVelocitaGiocatore(Velocita nuovaMossa) {
        this.velocita.aggiornaVelocita(nuovaMossa.getX(),
                nuovaMossa.getY());
        this.posizionePrecedente = new Posizione(this.posizione.getX(), this.posizione.getY());
        this.posizione.aggiorna(velocita);
    }

    public ArrayList<Velocita> possibleMoves(){
        if(posizione==null)
            throw new NullPointerException();
        ArrayList<Velocita> moves = new ArrayList<>();
        int currentX = velocita.getX();
        int currentY = velocita.getY();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = currentX + dx;
                int newY = currentY + dy;
                moves.add(new Velocita(newX, newY));
            }
        }
        return moves;
    }

    private Velocita getRandomMove(ArrayList<Velocita> listaVelocita)
    {
        if(listaVelocita==null)
            throw new NullPointerException();
        if(listaVelocita.isEmpty())
            return null;
        Random random = new Random();
        int listSize = listaVelocita.size();
        int randomIndex = random.nextInt(listSize);
        return listaVelocita.get(randomIndex);
    }

    /**
     * @return un {@code ArrayList<Velocita>} di possibili valide
     */
    private ArrayList<Velocita> getValidMove(ArrayList<Velocita> moves, Tracciato tracciato, ArrayList<Giocatore> giocatori) {
        ArrayList<Velocita> mossePossibili = new ArrayList<>();
        for (Velocita mossa : moves) {
            Posizione nuovaPosizione = this.posizione.nextPosition(mossa);
            // Controlla se la nuova posizione è all'interno della mappa e non fuori pista
            if (!tracciato.isOutOfMap(nuovaPosizione) && !tracciato.isOutOfRace(nuovaPosizione)) {
                boolean collisione = false;
                // Controlla se c'è una collisione con altri giocatori
                for (Giocatore otherPlayer : giocatori) {
                    if (!otherPlayer.equals(this) && !otherPlayer.isAlive()) {
                        if (otherPlayer.getPosizione().equals(nuovaPosizione)) {
                            collisione = true;
                            break;
                        }
                    }
                }
                if (!collisione)
                    mossePossibili.add(mossa);
            }
        }
        return mossePossibili;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiocatoreBot giocatore = (GiocatoreBot) o;
        return isAlive == giocatore.isAlive && Objects.equals(posizione, giocatore.posizione) && Objects.equals(velocita, giocatore.velocita) && Objects.equals(nome, giocatore.nome) && Objects.equals(posizionePrecedente, giocatore.posizionePrecedente) && Objects.equals(carattere, giocatore.carattere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posizione, velocita, nome, isAlive, posizionePrecedente, carattere);
    }
}
