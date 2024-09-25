package it.unicam.cs.porfiriluca.progettofinaledatemplate;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.Tracciato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 *
 * <p>La classe implementa le responsabilità dell'astrazione {@code ConsoleInputHandlerInterface}
 * <ul>
 *   <li>Implementa metodi per l'inserimento di informazioni da console.</li>
 *   <li>Utilizza le classi Scanner e InputStream, con la gestione di eccezioni.</li>
 * </ul>
 *
 * @author lucaporfiri
 *
 */
public class ConsoleInputHandler implements ConsoleInputHandlerInterface {

    public ConsoleInputHandler() {
    }

    public void chiediSeContinuare(GameEngine engine, boolean confirm) {
        if(engine== null)
            throw new NullPointerException("Null GameEngine");
        if(!confirm)
            return;
        System.out.println("Vuoi continuare la gara? (s/n): ");
        Scanner scanner = new Scanner(System.in);
        String risposta = scanner.nextLine().trim().toLowerCase();
        if (risposta.equals("n"))
            engine.setGaraInCorso(false);
    }
    public void InputPlayersNumber(GameEngine engine){
        if(engine==null)
            throw new NullPointerException("Null GameEngine");
        System.out.println("Inserisci il numero di giocatori: ");
        String input = null;
        int number = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine();
            number = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.out.println("Errore: numero giocatori non valido!");
            InputPlayersNumber(engine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (number<=0){
            System.out.println("Errore: numero giocatori negativo/assente!");
            InputPlayersNumber(engine);
        }
        engine.playerBotFarm(number);
    }
    public void InputSelectTrack(GameEngine engine){
        if(engine==null)
            throw new NullPointerException("Null GameEngine");
        String input = null;
        try{
            System.out.print("Inserisci il numero del tracciato (1, 2, 3, 4 o 5): ");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine();
            switch (input) {
                case "1":
                    engine.setTracciato(new Tracciato("Tracciato1.txt"));
                    break;
                case "2":
                    engine.setTracciato(new Tracciato("Tracciato2.txt"));
                    break;
                case "3":
                    engine.setTracciato(new Tracciato("Tracciato3.txt"));
                    break;
                case "4":
                    engine.setTracciato(new Tracciato("Tracciato4.txt"));
                    break;
                case "5":
                    engine.setTracciato(new Tracciato("Tracciato5.txt"));
                    break;
                default:
                    System.out.println("Errore: Tracciato non valido. Seleziona 1, 2, 3, 4 o 5");
                    InputSelectTrack(engine);  // Richiama il metodo per consentire una nuova selezione.
                    break;
            }
        } catch (IOException e) {
            System.out.println("Errore di input/output: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public boolean inputConfirmAfterEveryTurn() {
        System.out.println("Rimuovere la conferma dopo ogni turno? (s/n):");
        Scanner scanner = new Scanner(System.in);
        String risposta = scanner.nextLine().trim().toLowerCase();
        return !risposta.equals("s");
    }
}
