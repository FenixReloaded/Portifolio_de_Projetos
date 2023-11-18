package pilha;

/**
 * Escreva a descricao da classe testaPilha aqui.
 * 
 * @author (seu nome) 
 * @version (número de versao ou data)
 */
public class TestaPilha {
    public static void main(String args[]) {

        System.out.println("\nCriando pilha...");
        Pilha p = new Pilha(3);      
        System.out.println("Tamanho da pilha = " + p.getTam());

        System.out.println("\nInserindo...");
        System.out.println("Livro 1: " + p.inserir("Livro 1"));
        System.out.println("Livro 2: " + p.inserir("Livro 2"));
        System.out.println("Livro 3: " + p.inserir("Livro 3"));
        System.out.println("Pilha: " + p);

        
        System.out.println("Inserindo Livro 4 na pilha cheia: " + p.inserir("Livro 4"));
        System.out.println("Topo = " + p.topo());

        System.out.println("\nRemovendo...");
        System.out.println("Remover = " + p.remover());
        System.out.println("Topo = " + p.topo());
        System.out.println("Remover = " + p.remover());
        System.out.println("Topo = " + p.topo());
        System.out.println("Remover = " + p.remover());
        System.out.println("Topo = " + p.topo());

        System.out.println("\nRemovendo da pilha vazia...");
        System.out.println("Remover = " + p.remover());
        System.out.println("Topo = " + p.topo());

        System.out.println("\nInserindo...");
        System.out.println("Livro 8: " + p.inserir("Livro 8"));
        System.out.println("Topo = " + p.topo());

        System.out.println("\nInserindo...");
        System.out.println("Livro 9: " + p.inserir("Livro 9"));
        System.out.println("Topo = " + p.topo());

        System.out.println("\nInserindo...");
        System.out.println("Livro 10: " + p.inserir("Livro 10"));
        System.out.println("Topo = " + p.topo());

        System.out.println("\nInserindo...");
        System.out.println("Inserindo Livro 11 na pilha cheia: " + p.inserir("Livro 11"));
        System.out.println("Topo = " + p.topo());
    }
}
