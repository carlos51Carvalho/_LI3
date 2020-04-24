public class Main
{

    public static void main (String[] args) throws Exception {
        int f =0;
        Clientes clientes = new Clientes();
        Produtos produtos = new Produtos();
        Faturacao faturacao = new Faturacao();
        Vendas v = new Vendas();
        int c = clientes.ler_clientes("Dados_Iniciais/Clientes.txt");
        int p = produtos.ler_produtos("Dados_Iniciais/Produtos.txt");
        for (int i =0; i < 26 ; i++)
            faturacao.addProds(produtos.getP(i));

        System.out.println("\n\n"+ c);
        System.out.println("\n\n"+ p);
        for (int j = 0 ; j<26; j++)
            f = f + faturacao.getArr(j).size();

        System.out.println("\n\n"+ f+ "\n");
        int vend = v.ler_vendas(faturacao,clientes,produtos, "Dados_Iniciais/Vendas_1M.txt");
        System.out.println("\n\n"+ vend+ "\n");
    }
}









