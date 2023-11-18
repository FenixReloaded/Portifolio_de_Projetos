package pilha;

public class TestaLista {
    public static void main(String[] args) {
       
        // Cria lista simples
        ListaLigadaSimples lls = new ListaLigadaSimples();

        // Inserir no fim e inicio
        lls.inserirFim("2");
        lls.inserirFim("12");
        lls.inserirInicio("22");
        lls.inserirFim("32");
        lls.inserirFim("42");
        System.out.println(lls);
        
        // remover inicio e fim
        System.out.println(lls.removerInicio());
        System.out.println(lls);
        System.out.println(lls.removerFim());
        System.out.println(lls);
        System.out.println(lls.removerFim());
        System.out.println(lls.removerFim());
        System.out.println(lls);
        System.out.println(lls.removerFim());
        System.out.println(lls);
        
        // remover de lista vazia
        System.out.println(lls.removerFim());
        System.out.println(lls.removerFim());
        System.out.println(lls);
        
        // inserir
        lls.inserirInicio("222");
        lls.inserirInicio("223");
        System.out.println(lls);
       
    }

}
