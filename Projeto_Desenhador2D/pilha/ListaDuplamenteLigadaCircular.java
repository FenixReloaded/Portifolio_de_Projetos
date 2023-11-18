package pilha; 

public class ListaDuplamenteLigadaCircular implements IListaDuplamenteLigadaCircular {
    private No inicio; // ref para primeiro elemento

    // Setters e Getters
    /**
     * Method getInicio
     *
     * @return No - endereco do no inicial
     */
    public No getInicio() {
        return inicio;
    }

    /**
     * Method setInicio
     *
     * @param inicio No - altera o endereco do no inicial
     */
    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    /**
     * ListaDuplamenteLigadaCircular Constructor
     *
     */
    public ListaDuplamenteLigadaCircular() {   
        setInicio(null);
    }

    /**
     * Method estaVazia
     *
     * @return boolean - true (esta vazia), false nao vazia
     */
    public boolean estaVazia() {
        return (getInicio() == null); // Nao existe nenhum no??
    }

    /**
     * Method inserirInicio
     *
     * @param Object - conteudo do No a ser inserido
     */
    public void inserirInicio(Object elem) {
        // Cria novo no (1, 2)
        No novoNo = new No(elem);

        if(estaVazia()){  // se lista vazia
            novoNo.setProximo(novoNo); // 2
            novoNo.setAnterior(novoNo); // 3
            //setInicio(novoNo); // 4
        } else { // lista nao vazia
            No ultimo = getInicio().getAnterior(); //1
            novoNo.setProximo(getInicio()); //3
            getInicio().setAnterior(novoNo); //4
            novoNo.setAnterior(ultimo);//5
            ultimo.setProximo(novoNo); // 6
            //setInicio(novoNo); //7
        }
        setInicio(novoNo);

    }

    /**
     * Method inserirFim
     *
     * @param Object - conteudo do No a ser inserido
     */
    public void inserirFim(Object elem) {
        // Cria novo no (1, 2)
        No novoNo = new No(elem);

        if(estaVazia()){  // se lista vazia
            novoNo.setProximo(novoNo); // 2
            novoNo.setAnterior(novoNo); // 3
            setInicio(novoNo); //4
        } else { // lista nao vazia
            No ultimo = getInicio().getAnterior(); //2
            ultimo.setProximo(novoNo); // 3
            novoNo.setAnterior(ultimo);//4
            novoNo.setProximo(getInicio()); //5
            getInicio().setAnterior(novoNo); //4
        }
    }

    /**
     * Method removerInicio
     *
     * @return No - no a ser removido
     */
    public No removerInicio() {
        No temp = getInicio(); // 1
        if (temp != null){// Existe lista
            if ((temp == temp.getProximo()) && (temp == temp.getAnterior())) { // no unico
                setInicio(null);
            } else { // mais de um no
                No ultimo = getInicio().getAnterior(); //2
                getInicio().getProximo().setAnterior(ultimo);//3
                ultimo.setProximo(getInicio().getProximo()); //4
                setInicio(getInicio().getProximo());//5
            }
            // isola o no removido (remove as referencias para proximo e anterior)
            temp.setProximo(null);
            temp.setAnterior(null);
        }
        return temp;
    }

    /**
     * Method removerFim
     *
     * @return No - no a ser removido
     */
    public No removerFim() {
        No temp = getInicio(); 
        if (temp != null){// Existe lista
            if ((temp == temp.getProximo()) && (temp == temp.getAnterior())) { // no unico
                setInicio(null);
            } else { // mais de um no
                temp = getInicio().getAnterior(); // 1
                getInicio().setAnterior(temp.getAnterior()); //2
                temp.getAnterior().setProximo(inicio); // 3
            }
            // isola o no removido (remove as referencias para proximo e anterior)
            temp.setProximo(null);
            temp.setAnterior(null);
        }
        return temp;
    }

    /**
     * Method buscarPorChave
     *
     * @param chave int - chave de busca
     * @return - retorna o No encontrado ou null se nao encontrar
     */
    public No buscarPorChave(int chave){
        No noAtual = getInicio();  // Aponta para o inicio
        boolean verif = false;
        if(noAtual != null) {
            do {   
                 if((Integer)noAtual.getConteudo() == chave){
                    verif = true;
                    break;
                }
                noAtual = noAtual.getProximo();   
            } while(noAtual != getInicio());
        }
        return ((verif) ? noAtual: null);
    }

    /**
     * Method inserirApos
     *
     * @param chave int - chave. O novo no sera inserido apos
     * @param elem Object - conteudo do no a ser inserido
     * @return boolean - se a chave for encontrada, insere o no (true), caso contrario (false) 
     */
    public boolean inserirApos(int chave, Object elem) {
        boolean ret = false;
        No noAtual = buscarPorChave(chave);
        if(noAtual != null) {
            // Cria novo no (1)
            No novoNo = new No(elem);
            novoNo.setProximo(noAtual.getProximo()); //2
            novoNo.setAnterior(noAtual); //3
            noAtual.getProximo().setAnterior(novoNo); //4
            noAtual.setProximo(novoNo); //5
            ret = true;
        }
        return ret; 
    }

    /**
     * Method removerPelaChave
     *
     * @param chave int - chave de busca
     * @return No - no a ser removido
     */
    public No removerPelaChave(int chave) {
        No temp = buscarPorChave(chave);  
        if(temp != null){ // achou o no
            if ((temp == temp.getProximo()) && (temp == temp.getAnterior())) { // no unico
                setInicio(null);
            } else { // mais de um no

                if(temp == getInicio()) { // se for inicio
                    setInicio(temp.getProximo());
                }
                temp.getAnterior().setProximo(temp.getProximo());
                temp.getProximo().setAnterior(temp.getAnterior());
            }
            // isola o no removido (remove as referencias para proximo e anterior)
            temp.setProximo(null);
            temp.setAnterior(null);
        }
        return temp;
    }

    /**
     * Method toString
     *
     * @return String - Conteudo da lista ligada circular
     */
    public String toString() {
        String s = "[ ";
        No noAtual = getInicio();  // inicia do inicio
        if(noAtual != null) {
            do {   
                s = s + noAtual.toString() + " ";  
                noAtual = noAtual.getProximo();   
            } while(noAtual != null && noAtual != getInicio());
        }
        s = s + "]";
        return s;
    }

    
    /**
     * Retorna o conteudo da Lista como String (do fim ate o inicio)
     */
    public String toStringDoFim() {

        String s = "[ ";
        No noAtual = getInicio();  // inicia no fim
        if(noAtual != null) {
            do {   
                noAtual = noAtual.getAnterior(); // vai para o anterior
                s = s + noAtual.toString() + " "; // monta os dados como string
            }while(noAtual != null && noAtual != getInicio());
        }
        s = s + "]";

        return s;
    }
}  
