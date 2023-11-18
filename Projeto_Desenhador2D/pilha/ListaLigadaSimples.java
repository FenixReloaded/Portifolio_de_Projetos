package pilha; 

public class ListaLigadaSimples implements IListaLigadaSimples {
    No inicio, fim;
    int qtdNos;
    public ListaLigadaSimples(){
        setInicio(null);
        setFim(null);
        setQtdNos(0);
    }

    private void setInicio(No inicio){
        this.inicio = inicio;
    }

    public No getInicio(){
        return(this.inicio);
    }

    public No getFim(){
        return(this.fim);
    }

    private void setFim(No fim){
        this.fim = fim;
    }

    public int getQtdNos(){
        return this.qtdNos;
    }

    private void setQtdNos(int qtdNos){
        this.qtdNos = qtdNos;
    }

    public boolean estaVazia(){
        boolean vazia = false; 
        if (getQtdNos() == 0 && getInicio() == null && getFim() == null){
            vazia = true;
        }
        return vazia;
    }

    public void inserirInicio(Object obj) {
        No novo = new No(obj);
        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            novo.setProximo(inicio);
            setInicio(novo);
        }
        setQtdNos(getQtdNos() + 1);
    }

    public void inserirFim(Object obj){
        No novo = new No(obj);
        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            getFim().setProximo(novo);
            setFim(novo);
        }
        setQtdNos(getQtdNos() + 1);
    }

    public No removerInicio(){
        No aux = null;
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
            setQtdNos(getQtdNos() - 1);
        }

        return(aux);
    }

    public No removerFim(){
        No ant = getInicio();
        No aux = null;
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
            setQtdNos(getQtdNos() - 1);
        }
        return aux;
    }

    public String toString(){
        No temp = getInicio();
        String ret = "[ ";
        if (! estaVazia() ){
            while (temp != null){
                ret += temp.getConteudo()+" ";
                temp = temp.getProximo();
            }
        }
        ret = ret + "]";

        return(ret);
    }
}
