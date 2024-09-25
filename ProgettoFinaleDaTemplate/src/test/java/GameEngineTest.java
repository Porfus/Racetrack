import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {
    private GameEngine gameEngine;
    private Tracciato tracciato;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine();
        tracciato = new Tracciato("Tracciato3.txt");
        gameEngine.setTracciato(tracciato);
    }

    @Test
    void getWinnerShouldBeNull(){
        gameEngine.playerBotFarm(3);
        gameEngine.aggiornaGara();
        assertNull(gameEngine.getWinner());
    }

    @Test
    void testIniziaGara() {
        gameEngine.playerBotFarm(2);
        gameEngine.iniziaGara();
        assertTrue(gameEngine.isGaraInCorso());
        for (Giocatore giocatore : gameEngine.getGiocatori()) {
            assertTrue(giocatore.isAlive());
        }
    }

    @Test
    void testAggiornaGara() {
        gameEngine.playerBotFarm(2);
        gameEngine.iniziaGara();
        gameEngine.aggiornaGara();
        assertEquals(1, gameEngine.getTurnoCorrente());
        assertTrue(gameEngine.isGaraInCorso());
        for (Giocatore giocatore : gameEngine.getGiocatori()) {
            assertNotNull(giocatore.getPosizione());
        }
    }

    @Test
    void testControllaVittoria() {
        Giocatore giocatore = new GiocatoreBot(tracciato, "Giocatore1", '1');
        gameEngine.aggiungiGiocatore(giocatore);
        gameEngine.iniziaGara();
        giocatore.setPosizionePrecedente(new Posizione(0, 0));
        giocatore.setPosizione(tracciato.getFinishLine().getFirst());
        // Test vittoria
        gameEngine.aggiornaGara();
        assertFalse(gameEngine.isGaraInCorso());
        assertEquals(giocatore, gameEngine.getWinner());
    }

}
