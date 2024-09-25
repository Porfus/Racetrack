import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Posizione;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Tracciato;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test JUnit per la classe BinarySearchTree
 *
 * @author lucaporfiri
 *
 */
public class TracciatoTest {
    @Test
    void loadShouldNotThrow() {
        assertDoesNotThrow(()-> {
            new Tracciato("Tracciato3.txt");
        });
    }
    @Test
    void loadShouldThrow() {
        assertThrows(NullPointerException.class, ()-> {
            new Tracciato(null);
        });
    }
    @Test
    void loadShouldThrowArgument() {
        assertThrows(IllegalArgumentException.class, ()-> {
            new Tracciato("");
        });
    }

    @Test
    void testGetTrackMatrixValidFileName(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        assertFalse(tracciato.getTrackMatrix().isEmpty());
    }

    @Test
    void getPartenza(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        assertNotNull(tracciato.getPartenza());
    }

    @Test
    void notParenzaOrFinishLineShouldThrow(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Tracciato("TracciatoTest.txt");
        });
    }


    @Test
    void isOutOfMap(){
        Tracciato tracciato = new Tracciato("Tracciato3.txt");
        Posizione posizione = new Posizione(-1,-1);
        assertTrue(tracciato.isOutOfMap(posizione));
    }




}
