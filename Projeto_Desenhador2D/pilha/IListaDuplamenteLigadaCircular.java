package pilha;

/**
 * Interface da TAD Lista Duplamente Ligada Circular
 * 
 * @author Julio Arakaki
 * @version 25/05/2020
 */
public interface IListaDuplamenteLigadaCircular{
    /**
     * Method estaVazia
     *
     * @return boolean - true (esta vazia), false nao vazia
     */
    public boolean estaVazia(); 
    
    /**
     * Method inserirInicio
     *
     * @param Object - conteudo do No a ser inserido
     */
    public void inserirInicio(Object novo); 

    /**
     * Method inserirFim
     *
     * @param Object - conteudo do No a ser inserido
     */
    public void inserirFim(Object novo);
    
    /**
     * Method inserirApos
     *
     * @param key int - chave. O novo no sera inserido apos
     * @param elem Object - conteudo do no a ser inserido
     * @return boolean - se a chave for encontrada, insere o no (true), caso contrario (false) 
     */
    public boolean inserirApos(int chave, Object novo);

    /**
     * Method removerInicio
     *
     * @return No - no a ser removido
     */
    public Object removerInicio();

    /**
     * Method removerFim
     *
     * @return No - no a ser removido
     */
    public Object removerFim();
    
    /**
     * Method removerPelaChave
     *
     * @param chave int - chave de busca
     * @return No - no a ser removido
     */
    public Object removerPelaChave(int chave);
}
