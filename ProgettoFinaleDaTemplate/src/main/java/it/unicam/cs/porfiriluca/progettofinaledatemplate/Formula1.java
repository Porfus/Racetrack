package it.unicam.cs.porfiriluca.progettofinaledatemplate;

import it.unicam.cs.porfiriluca.progettofinaledatemplate.controller.GameController;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.models.GameEngine;
import it.unicam.cs.porfiriluca.progettofinaledatemplate.view.ConsoleView;


public class Formula1 {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        ConsoleView vista = new ConsoleView();
        ConsoleInputHandler inputHandler =new ConsoleInputHandler();
        GameController gameController = new GameController(vista, engine, inputHandler);

        gameController.startRace();


    }

}
