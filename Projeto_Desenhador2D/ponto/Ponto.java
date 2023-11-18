package ponto;

/**
 * Representacao de ponto matematico
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class Ponto {
    private double x;
    private double y;

    public Ponto() {
        setX(0);
        setY(0);
    }

    public Ponto(Ponto p) {
        setX(p.getX());
        setY(p.getY());
    }

    public Ponto(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double calcularDistancia(Ponto p) {

        double d = Math.sqrt(Math.pow(p.getY()-getY(), 2) + Math.pow(p.getX()-getX(), 2));

        return(d);

    }

    @Override
    public String toString() {
        return "Ponto [" + getX() + ", " + getY() +  "]";
    }
}
