import java.util.TreeSet;

public class Main
{

    public static void main (String[] args) throws Exception {
        int f = 0;
        int fi = 0;
        InterfaceClientes clientes = new Clientes3();
        InterfaceProdutos produtos = new Produtos3();
        Faturacao faturacao = new Faturacao();
        Filiais filiais = new Filiais();
        Queries q = new Queries();
        Vendas v = new Vendas();
        int c = clientes.ler_clientes("Dados_Iniciais/Clientes.txt");
        int p = produtos.ler_produtos("Dados_Iniciais/Produtos.txt");

        faturacao.addProds(produtos.getSetDeProdutos());

        System.out.println("\n\n" + c);
        System.out.println("\n\n" + p);
        for (int j = 0; j < 26; j++)
            f = f + faturacao.getArr(j).size();

        filiais.addCls(clientes.getSetDeClientes());

        for(int j =0 ; j<26;j++)
            fi += filiais.getArr(j).size();

        System.out.println("\n\n"+ f+ "\n");
        System.out.println("\n\n" + fi +  "\n");
        int vend = v.ler_vendas(faturacao,filiais,clientes.getSetDeClientes(),produtos.getSetDeProdutos(), "Dados_Iniciais/Vendas_1M.txt");
        System.out.println("\n\n"+ vend+ "\n");

        /*for (Object s: produtos.prodStartedByLetter('Z'))
            System.out.println(s +"\n");
        */
       // for (Object s: clientes.clStartedByLetter('F'))
          //  System.out.println(s+"\n");
    /*
        System.out.println(produtos.size());
        System.out.println(faturacao.numeroProdUsados());
        System.out.println(faturacao.numeroProdNuncaUsados());
        System.out.println(filiais.naocompram());
        System.out.println(filiais.numeroComparadores());
*/
        //System.out.println(q.querie1(faturacao));
        //System.out.println(q.querie2(filiais, 1));
        //System.out.println(q.querie2(filiais, 2));

        int[] r = q.numeroCompPMes(filiais);
        for (int i=0; i<12 ; i++){
            System.out.println(r[i]);
        }

        //q.querie3(filiais, "A1231");

        //System.out.println(q.querie3(filiais, "A1231"));

    }
}









