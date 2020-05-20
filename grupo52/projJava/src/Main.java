import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main
{

    public static void main (String[] args) throws Exception {
        int f = 0;
        int fi = 0;
        InterfaceClientes clientes = new Clientes3();
        InterfaceProdutos produtos = new Produtos3();
        Faturacao faturacao = new Faturacao();
        Filiais filiais = new Filiais();
        //Controlador q = new Controlador();
        System.out.println("-> " + clientes.ler_clientes("Dados_Iniciais/Clientes.txt") + "\n");
        System.out.println("-> " +produtos.ler_produtos("Dados_Iniciais/Produtos.txt")+ "\n");

        faturacao.addProds(produtos.getSetDeProdutos());
        filiais.addCls(clientes.getSetDeClientes());

        System.out.println("-> " + Vendas.ler_vendas(faturacao,filiais,clientes.getSetDeClientes(),produtos.getSetDeProdutos(), "Dados_Iniciais/Vendas_1M.txt"));


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


//        int[] r = q.numeroCompPMes(filiais);
//        for (int i=0; i<12 ; i++){
//            System.out.println(r[i]);
//        }

        //q.querie3(filiais, "A1231");

        //System.out.println(q.querie3(filiais, "A1231"));




       /* Map<Integer, int[]> q2= q.querie2( filiais,1);
        for(Map.Entry<Integer, int[]> e : q2.entrySet())
            System.out.println(e.getKey() + "[" + e.getValue()[0]+" , "+e.getValue()[1] +"]");


        Map<Integer, double[]> q3= q.querie3( filiais,"A1231");
        for(Map.Entry<Integer, double[]> e : q3.entrySet())
            System.out.println(e.getKey() + "[" + e.getValue()[0]+" , "+e.getValue()[1] +" , "+e.getValue()[2]+"]");

        Map<Integer, double[]> q4= q.querie4( filiais,"XA1231");
        for(Map.Entry<Integer, double[]> e : q4.entrySet())
            System.out.println(e.getKey() + "[" + e.getValue()[0]+" , "+e.getValue()[1] +" , "+e.getValue()[2]+"]");


        TreeSet<Map.Entry<String, Integer>> q5 = Controlador.querie5("A1231", filiais);
        for(Map.Entry<String, Integer> e : q5 )
            System.out.println(e.getKey() + " -> " + e.getValue());


        TreeSet<Map.Entry<String, int[]>> q6= Controlador.querie6( 5,filiais);
        int jdk =0;
        for(Map.Entry<String, int[]> e : q6) {
            if (jdk < 5) System.out.println(e.getKey() + "[ " + e.getValue()[0] + " , " + e.getValue()[1] + " ]");
            jdk++;
        }

        Map<Integer, Map<String, Double>> q7= q.querie7( filiais);
        for(Map.Entry<Integer, Map<String, Double>> e : q7.entrySet()) {
            System.out.println(e.getKey());
            for (Map.Entry<String, Double> gh : e.getValue().entrySet()) {
                System.out.println(gh.getKey() + " -> [ " + gh.getValue() + " ]");
            }
        }

        TreeSet<Map.Entry<String, Integer>> q8 = Controlador.querie8( filiais, 5);
        for(Map.Entry<String, Integer> e : q8 )
            System.out.println(e.getKey() + " -> " + e.getValue());


       TreeSet<Map.Entry<String,Double>> q9= Controlador.querie9( filiais,"XA1231");
        for(Map.Entry<String, Double> e : q9)
            System.out.println(e.getKey() + " -> [" + e.getValue() + "]");

        Map<Integer,Map<Integer,Map<String,Double>>> q10= Controlador.querie10( faturacao);
        for( Map.Entry<Integer,Map<Integer,Map<String,Double>>> m : q10.entrySet()) {
            System.out.println("Mes" + m.getKey());
            for (Map.Entry<Integer, Map<String, Double>> fil : m.getValue().entrySet()){
                for(Map.Entry<String, Double> prod: fil.getValue().entrySet()) {
                    System.out.println("Filial" + fil.getKey() + " -> [" + prod.getKey() + "|" + prod.getValue() + "]");
                }
            }

        }

       */
/*
        try {
            gravarObj("FileObj");
        }
        catch (IOException e){
            System.out.println("Erro a gravar!");
        }


        try {
            Controlador.lerObj("FileObj");
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Erro na leitura!");
        }

 */

    }

}










