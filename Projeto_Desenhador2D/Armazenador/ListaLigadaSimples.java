package Armazenador;

import java.io.Serializable;

/**
 * 
 * Caio Caio Pereira Guimar�es 
 * Gustavo Leite Ioels 
 * Pedro Fran�a de Godoi 
 * 
 */

public class ListaLigadaSimples implements IArmazenador,Serializable {
    No inicio, fim;
    int qtd;

    /**
     * ListaLigadaSimples Construtor
     *
     */
    public ListaLigadaSimples(){
        setInicio(null);
        setFim(null);
        setQtd(0);
    }

    public ListaLigadaSimples(Object[] elementos) {
        this(); // Chama o construtor padr�o para inicializar a lista vazia
        for (Object elem : elementos) {
            adicionar(elem);
        }
    }

    /**
     * remover
     *
     * @param int i, posição da lista a ser removida
     * 
     * @return Objeto obj,lista atualizada com o pedido de remoçaõ do item i
     */
    public Object remover(int i){
        int v = 0;

        No ant = getInicio();
        No prox = getInicio();
        No aux = null;

        Object obj = null;

        if(!estaVazia()){
            if(i >= 0 && i < getQtd()){
                if(i == 0)
                    obj = removerInicio();
                else if(i == getQtd() - 1)
                    obj = removerFim();
                else{
                    while(v != i){
                        ant = prox;
                        prox = prox.getProximo();
                        v++;
                    }

                    ant.setProximo(prox.getProximo());
                    setQtd(getQtd() - 1);
                    obj = prox.getConteudo();

                }
            }
        }

        return obj;
    }

    /**
     * buscar
     *
     * @return Objeto obj , conteudo da posição
     */
    public Object buscar (int i){
        int aux = 0;

        No prox = getInicio();
        Object obj = null;

        if(!estaVazia()){

            if(i >= 0 && i < getQtd()){
                while(aux != i)
                {
                    prox = prox.getProximo(); 
                    aux++;
                }  
                obj = prox.getConteudo();
            }

        }

        return obj;
    }

    public String toString(){
        return toString();
    }

    /**
     * setInicio
     *
     * @param inicio No inicio da lista ligada
     */
    private void setInicio(No inicio){
        this.inicio = inicio;
    }

    /**
     * getInicio
     *
     * @return No de inicio da lista ligada
     */
    public No getInicio(){
        return(this.inicio);
    }

    /**
     * getFim
     *
     * @return No do final da lista ligada
     */
    public No getFim(){
        return(this.fim);
    }

    /**
     * setFim
     *
     * @param fim No fim da lista ligada
     */
    private void setFim(No fim){
        this.fim = fim;
    }

    /**
     * getQtdNos
     *
     * @return int, qtde de nos na lista ligada
     */
    public int getQtd(){
        return this.qtd;
    }

    /**
     * setQtdNos
     *
     * @param qtdNos Um parâmetro
     */
    private void setQtd(int qtd){
        this.qtd = qtd;
    }

    /**
     * estaVazia
     *
     * @return boolean, true se estiver vazia e false caso contrario
     */
    public boolean estaVazia(){
        boolean vazia = false; 
        if (getQtd() == 0 && getInicio() == null && getFim() == null){
            vazia = true;
        }
        return vazia;
    }

    /**
     * inserirInicio
     *
     * @param elem Objeto a ser inserido no inicio da lista ligada
     */
    public void inserirInicio(Object elem) {
        No novo = new No(elem); //1

        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            novo.setProximo(inicio);
            setInicio(novo);
        }
        setQtd(getQtd() + 1);
    }

    /**
     * adicionar
     *
     * @param a Objeto a ser inserido no fim da lista ligada
     */
    public void adicionar(Object a){
        No novo = new No(a);
        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            getFim().setProximo(novo);
            setFim(novo);
        }
        setQtd(getQtd() + 1);
    }

    /**
     * removerInicio
     *
     * @return Objeto removido
     */
    public Object removerInicio(){
        No aux = null;
        Object obj = null; 
        if(!estaVazia()){
            if ((getInicio() == getFim())){
                aux = getInicio();
                setInicio(null);
                setFim(null);
            } else {
                aux = getInicio();
                setInicio(aux.getProximo());
                aux.setProximo(null);
            }
            setQtd(getQtd() - 1);
            obj = aux.getConteudo();
        }

        return(obj);
    }

    /**
     * removerFim
     *
     * @return Objeto removido
     */
    public Object removerFim(){
        No ant = getInicio();
        No aux = null;
        Object obj = null; 
        if (!estaVazia()){
            if ((getInicio() == getFim())){
                aux = getInicio();
                setInicio(null);
                setFim(null);
            } else {            
                // percorre ate achar o no antes do fim
                while(ant.getProximo() != fim){
                    ant = ant.getProximo();
                }
                ant.setProximo(null);
                aux = fim;
                setFim(ant);
            }
            setQtd(getQtd() - 1);
            obj = aux.getConteudo();           
        }
        return obj;
    }
}
