package pilha;

/**
 * No da lista duplamente ligada
 */
public class No{
    /**
     * Atributos
     */
    Object conteudo; // conteudo
    No proximo;  // proximo
    No anterior; // anterior

    /**
     * Construtor
     */
    public No(Object conteudo){
        setConteudo(conteudo);
        setProximo(null);
        setAnterior(null);
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
    
    public void setAnterior(No anterior){
        this.anterior = anterior;
    }

    public Object getConteudo(){
        return(this.conteudo);
    }
    
    public No getProximo(){
        return(this.proximo);
    }

    public No getAnterior(){
        return(this.anterior);
    }
    
    public String toString(){
        return conteudo.toString();
    }
}
