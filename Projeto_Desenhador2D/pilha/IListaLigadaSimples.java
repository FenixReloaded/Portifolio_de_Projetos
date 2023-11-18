package pilha;

/**
 * @author Julio Arakaki
 *
 */
public interface IListaLigadaSimples {
    public boolean estaVazia();

    public void inserirInicio(Object obj);

    public void inserirFim(Object obj);

    public No removerInicio();

    public No removerFim();
}
