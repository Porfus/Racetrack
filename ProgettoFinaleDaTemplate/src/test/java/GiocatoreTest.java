import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GiocatoreBot;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Tracciato;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GiocatoreTest {
    @Test
    void giocatoreBotShouldThrow(){
        assertThrows(NullPointerException.class, ()->{
            new GiocatoreBot(null, "bot", '1');
        });
    }

    @Test
    void giocatoreBotShouldThrowName(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        assertThrows(NullPointerException.class, ()->{
            new GiocatoreBot(tracciato, null, '1');
        });
    }

    @Test
    void giocatoreBotShouldThrowChar(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        assertThrows(NullPointerException.class, ()->{
            new GiocatoreBot(tracciato, "bot", null);
        });
    }

    @Test
    void giocatoreBotShouldNotThrow(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        assertDoesNotThrow(()->{
            new GiocatoreBot(tracciato, "bot", '1');
        });
    }

    @Test
    void getPosizioneTest() {
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        GiocatoreBot giocatoreBot = new GiocatoreBot(tracciato,"bot", '1');
        assertDoesNotThrow(()->{
            giocatoreBot.getPosizione();
        });
    }

    @Test
    void muoviTest() {
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        GiocatoreBot giocatoreBot = new GiocatoreBot(tracciato,"bot", '1');
        assertThrows(NullPointerException.class,()->{
            giocatoreBot.muovi(tracciato, null);
        });
    }

    @Test
    void getPossibleMovesTest() {
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        GiocatoreBot giocatoreBot = new GiocatoreBot(tracciato,"bot", '1');
        assertDoesNotThrow(()->{
            giocatoreBot.possibleMoves();
        });
    }

    @Test
    void equalTest(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        GiocatoreBot giocatoreBot1 = new GiocatoreBot(tracciato,"bot", '1');
        GiocatoreBot giocatoreBot2 = new GiocatoreBot(tracciato,"bot", '2');
        assertFalse(giocatoreBot1.equals(giocatoreBot2));
    }

}
