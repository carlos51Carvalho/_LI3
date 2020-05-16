public class Main
{

    public static void main (String[] args) throws Exception {
        int f = 0;
        int fi = 0;
        Clientes clientes = new Clientes();
        Produtos produtos = new Produtos();
        Faturacao faturacao = new Faturacao();
        Filiais filiais = new Filiais();
        Queries q = new Queries();
        Vendas v = new Vendas();
        int c = clientes.ler_clientes("Dados_Iniciais/Clientes.txt");
        int p = produtos.ler_produtos("Dados_Iniciais/Produtos.txt");
        for (int i = 0; i < 26; i++)
            faturacao.addProds(produtos.getP(i));

        System.out.println("\n\n" + c);
        System.out.println("\n\n" + p);
        for (int j = 0; j < 26; j++)
            f = f + faturacao.getArr(j).size();

        for (int j = 0; j < 26; j++)
            filiais.addCls(clientes.getC(j));

        for(int j =0 ; j<26;j++)
            fi += filiais.getArr(j).size();

        System.out.println("\n\n"+ f+ "\n");
        System.out.println("\n\n" + fi +  "\n");
        int vend = v.ler_vendas(faturacao,filiais,clientes,produtos, "Dados_Iniciais/Vendas_1M.txt");
        System.out.println("\n\n"+ vend+ "\n");

        /*for (Object s: produtos.prodStartedByLetter('Z'))
            System.out.println(s +"\n");
        */
       // for (Object s: clientes.clStartedByLetter('F'))
          //  System.out.println(s+"\n");

        System.out.println(produtos.size());
        System.out.println(faturacao.numeroProdUsados());
        System.out.println(faturacao.numeroProdNuncaUsados());
        System.out.println(filiais.naocompram());
        System.out.println(filiais.numeroComparadores());

        System.out.println(q.querie2(filiais, 1));

    }
}









