package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

public class Velocita {
    private int x;
    private int y;

    public Velocita(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void aggiornaVelocita(int deltaX, int deltaY) {
        this.setX(deltaX);
        this.setY(deltaY);
    }

    @Override
    public String toString() {
        return "["+x+","+y+"]";
    }
}
