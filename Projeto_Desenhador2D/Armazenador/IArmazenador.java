package Armazenador;

/**
 * Write a description of interface Armazenador here.
 * 
 * Caio Caio Pereira Guimar�es 
 * Gustavo Leite Ioels 
 * Pedro Fran�a de Godoi 
 * 
 */

public interface IArmazenador
{
    public void adicionar(Object a);

    public Object remover(int i);

    public boolean estaVazia();

    public Object buscar (int i);

    public int getQtd();

    public String toString();
}
