package Armazenador;

import java.io.Serializable;

/**
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class No implements Serializable{
    /**
     * Atributos
     */
    Object conteudo; // conteudo
    No proximo; // proximo

    /**
     * No Construtor
     *
     * @param conteudo Object a ser inserido no no
     */
    public No(Object conteudo){
        setConteudo(conteudo);
        setProximo(null);
    }

    /**
     * setters e getters
     */
    public void setConteudo(Object conteudo){
        this.conteudo = conteudo;
    }

    public void setProximo(No proximo){
        this.proximo = proximo;
    }

    public Object getConteudo(){
        return(this.conteudo);
    }

    public No getProximo(){
        return(this.proximo);
    }

    public String toString(){
        return(conteudo.toString());
    }
}