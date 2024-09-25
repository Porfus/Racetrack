package it.unicam.cs.porfiriluca.progettofinaledatemplate.models;

public class Posizione {
    private int x;
    private int y;

    public Posizione(int h, int w) {
        this.x = h;
        this.y = w;
    }

    public void setX(int width) {
        this.x = width;
    }

    public void setY(int hight) {
        this.y = hight;
    }

    public Posizione nextPosition(Velocita velocita) {
        if(velocita==null)
            throw new NullPointerException("velocita null");
        int newX = this.getX() + velocita.getX();
        int newY = this.getY() + velocita.getY();
        return new Posizione(newX, newY);
    }

    public void aggiorna(Velocita velocita) {
        if(velocita==null)
            throw new NullPointerException("velocita null");
        int newX = this.getX() + velocita.getX();
        int newY = this.getY() + velocita.getY();
        this.setX(newX);
        this.setY(newY);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return  "["+x+","+y+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Posizione) {
            return this.x == ((Posizione) obj).getX() &&
                    this.y == ((Posizione) obj).getY();
        }
        return false;
    }

}
