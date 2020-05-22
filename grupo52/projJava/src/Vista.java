import java.io.Serializable;
import java.util.Map;

public class Vista implements Serializable {

    //public Vista(){



    public void printHeader(){
        System.out.println("|==========================================================================================|");
        System.out.println("||                                    Welcome to                                          ||");
        System.out.println("||                             SISTEMA GESTÃO DE VENDAS                                   ||");
        System.out.println("|==========================================================================================|");
    }


    public void printmenu(){
        System.out.println("______________________________________________________________________________________________");
        System.out.println("|Por favor escolha a instrução a executar:                                                   |");
        System.out.println("|1  -> Load dos ficheiros.                                                                   |");
        System.out.println("|2  -> Dados obtidos, diretamente após leitura (Queries estatisticas 1.1).                   |");
        System.out.println("|3  -> Número TOTAL de compras relizadas por més.                                            |");
        System.out.println("|4  -> Faturação total por mês para cada filial e o valor total global.                      |");
        System.out.println("|5  -> Número de distintos clientes que compraram em cada mês filial a filial.               |");
        System.out.println("|6  -> Q1 -Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados.       |");
        System.out.println("|7  -> Q2 -Número total de vendas realizadas e o total de compradores distintos num mês.     |");
        System.out.println("|8  -> Q3 -Determinar para  cada  mês,  quantas  compras um certo cliente fez,               |");
        System.out.println("|          quantos produtos distintos comprou e quanto gastou no total.                      |");
        System.out.println("|9  -> Q4 -Determinar, mês a mês, quantas vezes um certo produto foi comprado, por quantos   |");
        System.out.println("|          clientes diferentes e o total faturado.                                           |");
        System.out.println("|10 -> Q5 -Determinar a lista ordenada por ordem  decrescente de quantidade de códigos de    |");
        System.out.println("|          produtos que mais comprou.                                                        |");
        System.out.println("|11 -> Q6 -Determinar o conjunto dos X produtos mais vendidos em todo o ano indicando o      |");
        System.out.println("|          número total de distintos clientes que o compraram.                               |");
        System.out.println("|12 -> Q7 -Determinar, para cada filial, a lista dos três  maiores  compradores (dinheiro)   |");
        System.out.println("|13 -> Q8 -Determinar os códigos dos X clientes que compraram mais produtos diferentes       |");
        System.out.println("|14 -> Q9 -Determinar os códigos de clientes que mais compraram um X produto, e quanto gatou |");
        System.out.println("|15 -> Q10-Determinar a faturação total de cada produto, mês a mês, filial a filial          |");
        System.out.println("|16 -> Gravar em Binário                                                                     |");
        System.out.println("|0  -> Exit Program                                                                          |");
        System.out.println("|____________________________________________________________________________________________|");
    }


    public void printExit(){
        System.out.println("\nHasta la vista BaBy!");
    }
    public void printDone(){
        System.out.println("\nInstrução Concluida");
    }
    public void printError(){
        System.out.println("\nNão escreveu nenhuma das duas opções válidas!");
        System.out.print("Por favor tente outra vez: ");
    }

    public void printNotLoad(){
        System.out.println("\nAinda não leu nenhuns ficheiros");
    }


    //==== Leitura =====//

    public void printErrorFIle(String e){
        System.out.println("Não encontrou fiheiro -> " +e);
    }
    public void printOpLeitura(){
        System.out.println("\nDeseja ler de ficheiros predfenidos (1) , ou ficheiros escolhidos por si (2) ?");
    }

    public void fileNameC(){
        System.out.println("FilePath Clientes : ");
    }

    public void fileNameP(){
        System.out.println("FilePath Produtos : ");
    }

    public void fileNameV(){
        System.out.println("FilePath Vendas: ");
    }

    public void printFilePaths(String fc,String fp,String fv){
        System.out.printf("FilePath Clientes lido: %s\nFilePath Produtos lido:%s\nFilePath Vendas lido:%s\n",fc,fp,fv );
    }



    // ===================================== QUERIES ESTATISTICAS 1.1 ================================================


    public void printfileName(String nome){
        System.out.println("| Nome do ficheiro lido: ------------------------------ " + nome + "          |");
    }

    public void printVendasErradas(int errados){
        System.out.println("| Número de vendas não válidas: ----------------------- "+ errados + "                 |");
    }

    public void printNumProdutosLidos(int tprod){
        System.out.println("| Número de produtos lidos: --------------------------- "+ tprod+ "                 |");
    }

    public void printNumProdutosDiferentes(int dprod){
        System.out.println("| Número de produtos diferentes comprados: ------------ "+dprod+ "                 |");
    }

    public void printProdutosNuncaComprados(int pnc){
        System.out.println("| Número de produtos diferentes comprados: ------------ "+pnc+ "                    |");
    }

    public void printTotalClientesLidos(int tcl){
        System.out.println("| Número de clientes lidos: --------------------------- "+ tcl+ "                  |");
    }

    public void printClientesRealizarmComp(int tclc){
        System.out.println("| Número de clientes que realizaram compras: ---------- "+tclc+ "                  |");
    }

    public void printClientesNaoComp(int tclsc){
        System.out.println("| Número de clientes que não realizaram compras: ------ "+tclsc+ "                      |");
    }

    public void printVendasZero(int tvendaszero){
        System.out.println("| Número de vendas com preço a 0: --------------------- "+tvendaszero+ "                     |");
    }
    public void printFatTotal(double fattotal){
        System.out.println("| Total Faturado: ------------------------------------- "+fattotal+ "    |");
    }

    public void queriesEstatisticas(String n, int errados, int tprod, int dprod, int pnc, int tcl,int tclc,int tclsc,int tvendaszero,double fattotal){
        System.out.println("Estatisticas sobre os Ficheiros Lidos:\n");
        System.out.println("________________________________________________________________________________");
        printfileName(n);printVendasErradas(errados);printNumProdutosLidos(tprod);printNumProdutosDiferentes(dprod);
        printProdutosNuncaComprados(pnc);printTotalClientesLidos(tcl);printClientesRealizarmComp(tclc);printClientesNaoComp(tclsc);
        printVendasZero(tvendaszero);printFatTotal(fattotal);
        System.out.println("|______________________________________________________________________________|");
    }



    // ================================= Estatisticas 1.2 ==============================================================


    public void querie_2_1(int[] a){
        System.out.println("_______________________________");
        for (int i =1; i<13 ; i++){
            System.out.printf("|Mes %3d:___________ %d    |\n" ,i,a[i-1]);
        }
        System.out.println("|_____________________________|");
    }

    public void querie_2_2(Map<Integer,double[] > res2){
        System.out.printf("Global ->   %f\n\n", res2.get(0)[0]);
        System.out.printf("\n           Filial 1                Filial 2                Filial 3   \n");
        for(Map.Entry<Integer,double[]> e: res2.entrySet()){
            if (e.getKey() != 0){
                System.out.printf("Mes %3d:   %8f       %8f       %8f\n", e.getKey(),e.getValue()[1],e.getValue()[2],e.getValue()[3]);
                }
            }
        }

    public void querie_2_3(Map<Integer,int[] > res3) {
        System.out.printf("\nMes:            1         2         3         4         5         6         7         8         9         10        11        12      \n");
        for (Map.Entry<Integer, int[]> e : res3.entrySet()) {
            if (e.getKey() != 0) {
                System.out.printf("Filial %3d:", e.getKey());
                for (int i = 0; i < 12; i++) {
                    System.out.printf("     %5d", e.getValue()[i]);
                }
                    System.out.printf("\n");
            }
        }
    }


    //============================================Interativas ==========================================================

































}






















































































