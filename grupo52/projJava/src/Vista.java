import java.util.Map;

public class Vista {

    //public Vista(){


    public void printmenu(){
        System.out.println("1-Querie1");
        System.out.println("2-Querie2");
        System.out.println("3-Querie3");
        System.out.println("4-Querie4");
        System.out.println("5-Querie5");
        System.out.println("6-Querie6");
        System.out.println("7-Querie7");
        System.out.println("8-Querie8");
        System.out.println("9-Querie9");
        System.out.println("10-Querie10");
    }
    // ===================================== QUERIES ESTATISTICAS 1.1 ================================================


    public static void printfileName(String nome){
        System.out.println("| Nome do ficheiro lido: ------------------------------ " + nome + "                |");
    }

    public static void printVendasErradas(int errados){
        System.out.println("| Número de vendas não válidas: ----------------------- "+ errados + "                |");
    }

    public static void printNumProdutosLidos(int tprod){
        System.out.println("| Número de produtos lidos: --------------------------- "+ tprod+ "                |");
    }

    public static void printNumProdutosDiferentes(int dprod){
        System.out.println("| Número de produtos diferentes comprados: ------------ "+dprod+ "                |");
    }

    public static void printProdutosNuncaComprados(int pnc){
        System.out.println("| Número de produtos diferentes comprados: ------------ "+pnc+ "                |");
    }

    public static void printTotalClientesLidos(int tcl){
        System.out.println("| Número de clientes lidos: --------------------------- "+ tcl+ "                |");
    }

    public static void printClientesRealizarmComp(int tclc){
        System.out.println("| Número de clientes que realizaram compras: ---------- "+tclc+ "                |");
    }

    public static void printClientesNaoComp(int tclsc){
        System.out.println("| Número de clientes que não realizaram compras: ------ "+tclsc+ "                |");
    }

    public static void printVendasZero(int tvendaszero){
        System.out.println("| Número de vendas com preço a 0: --------------------- "+tvendaszero+ "                |");
    }
    public static void printFatTotal(double fattotal){
        System.out.println("| Total Faturado: ------------------------------------- "+fattotal+ "                |");
    }

    public static void queriesEstatisticas(String n, int errados, int tprod, int dprod, int pnc, int tcl,int tclc,int tclsc,int tvendaszero,double fattotal){
        System.out.println("Estatisticas sobre os Ficheiros Lidos:\n");
        System.out.println("\n________________________________________________________________________________________________________________\n");
        printfileName(n);printVendasErradas(errados);printNumProdutosLidos(tprod);printNumProdutosDiferentes(dprod);
        printProdutosNuncaComprados(pnc);printTotalClientesLidos(tcl);printClientesRealizarmComp(tclc);printClientesNaoComp(tclsc);
        printVendasZero(tvendaszero);printFatTotal(fattotal);
        System.out.println("\n________________________________________________________________________________________________________________\n");
    }



    // ================================= Estatisticas 1.2 ==============================================================


    public static void querie_2_1(int[] a){
        for (int i =1; i<13 ; i++){
            System.out.printf("Mes %3d:___________ %d    \n" ,i,a[i-1]);
        }
    }

    public static void querie_2_2(Map<Integer,Double > res2){
        System.out.printf("\n     Mes:     1      2      3      4      5      6      7      8      9      10      11     12 \n");
        for(Map.Entry<Integer,Double> e: res2.entrySet()){
            System.out.println("Filial %d");
            for (int i=1; i<13; i++){
            }
        }
    }
}


















































































