package pilha; 

public interface IPilha {
    Object topo();

    boolean inserir(Object o);

    Object remover();

    boolean estaVazia();

    boolean estaCheia();
}
