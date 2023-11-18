package pilha;

class TestaListaDuplamenteLigadaCircular {
    public static void main(String[] args) {                             // make a new list
        // Instancia uma lista ligada circular
        IListaDuplamenteLigadaCircular ldlc = new ListaDuplamenteLigadaCircular();
        ldlc.inserirFim(44);
        ldlc.inserirInicio(22);
        ldlc.inserirFim(111);
        ldlc.inserirInicio(1);
        ldlc.inserirFim(99);
        
        // mostra a lista
        System.out.println(ldlc);
        // mostra a lista
        System.out.println(((ListaDuplamenteLigadaCircular)ldlc).toStringDoFim());
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.inserirFim(-1);
        ldlc.inserirInicio(111);
        ldlc.inserirApos(111, 65);
        ldlc.inserirApos(-1, 1024);
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerPelaChave(-1);
        ldlc.removerPelaChave(111);
        // mostra a lista
        System.out.println(ldlc);
        ldlc.inserirApos(65, 256);
        ldlc.inserirInicio(-55);
        ldlc.inserirFim(13);
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerInicio();
        ldlc.removerPelaChave(13);
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerInicio();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerInicio();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();
        System.out.println(ldlc);
        ldlc.removerFim();
        System.out.println(ldlc);
        ldlc.removerFim();
        System.out.println(ldlc);
        ldlc.removerFim();
        // mostra a lista
        System.out.println(ldlc);
        ldlc.removerFim();

        // mostra a lista
        System.out.println(ldlc);
    } 
}

