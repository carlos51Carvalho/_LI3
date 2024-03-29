package View;

import java.io.Serializable;
import java.util.*;

public class Vista implements InterfaceVista {

    //public View.Vista(){



    public void printHeader(){
        System.out.println("|============================================================================================|");
        System.out.println("||                                     Welcome to                                           ||");
        System.out.println("||                              SISTEMA GESTÃO DE VENDAS                                    ||");
        System.out.println("|============================================================================================|");
    }


    public void printmenu(){
        System.out.println("_____________________________________________________________________________________________");
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
        System.out.println("|14 -> Q9 -Determinar os códigos de clientes que mais compraram um X produto, e quanto gastou|");
        System.out.println("|15 -> Q10-Determinar a faturação total de cada produto, mês a mês, filial a filial          |");
        System.out.println("|16 -> Gravar em Binário                                                                     |");
        System.out.println("|0  -> Exit Program                                                                          |");
        System.out.println("|____________________________________________________________________________________________|");
    }

    public void printBarraN(){
        System.out.println();
    }
    public void flush(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void pressioneEnter(){
        System.out.println("\nPressione enter para continuar");
    }

    public void printExit(){
        System.out.println("\nObrigado por usar GestVendasApp!");
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

    public void printEscrever(){
        System.out.println("Deseja escrever em novas estrutuas (1) , ou nas já criadas(2)");
    }

    public void printErrorFIle(String e){
        System.out.println("Não encontrou fiheiro -> " +e);
    }
    public void printOpLeitura(){
        System.out.println("\nInsira (1) para ler do ficheiro .dat, (2) para ler dos ficheiros predefinidos, (3) para ler de ficheiros escolhidos por si, ou (0) sair sem ler.");
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

    public void printFilePaths(String fc,String fp,String fv, double time){
        System.out.printf("FilePath Clientes lido: %s\nFilePath Produtos lido:%s\nFilePath Vendas lido:%s\nTempo de leitura: %f",fc,fp,fv,time );
    }
    public void printFilePath(String f,double time){
        System.out.printf("FilePath do ficheiro .dat: %s\nTempo de leitura: %f",f,time );
    }
    public void aEscreverParaFicheiro(String f){
        System.out.printf("A escrever para o ficheiro %s\n",f);
    }

    public void printSaidaSemLeitura(){
        System.out.println("Saida sem Leitura de ficheiros");
    }

    //====== Queries =======//

    public void printMes(){
        System.out.println("Insira um Més:" );
    }

    public void printErrorMes(){
        System.out.println("Més inserido não válido. Por favor tente outra vez:");
    }
    public void printErrorCliente(){
        System.out.println("Cliente inserido não válido ou não existente. Por favor tente outra vez:");
    }

    public void printCliente(){
        System.out.println("Insira um Cliente:" );
    }
   public void printErrorProduto(){
        System.out.println("Produto inserido não válido ou não existente. Por favor tente outra vez:");
    }

    public void printProduto(){
        System.out.println("Insira um Produto:" );
    }

    public void printLimite(){
        System.out.println("Insira um limite:");
    }

    public void printErrorLimite(){
        System.out.println("O Limite que inseriu não é válido. Por favor tente outra vez.");
    }
    public void printErrorPagina(int pag){
        System.out.printf("A pagina %d não é válida. Por favor tente outra vez.\n",pag);
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
        System.out.println("Número total de vendas mes a mes:\n");
        System.out.println("_______________________________");
        for (int i =1; i<13 ; i++){
            System.out.printf("|Mes %3d:___________ %d    |\n" ,i,a[i-1]);
        }
        System.out.println("|_____________________________|");
    }

    public void querie_2_2(Map<Integer,double[] > res2){
        System.out.println("Faturação total mês a mẽs filial a filial:\n");
        System.out.printf("Global ->   %f\n\n", res2.get(0)[0]);
        System.out.print("\n           Filial 1                Filial 2                Filial 3   \n");
        for(Map.Entry<Integer,double[]> e: res2.entrySet()){
            if (e.getKey() != 0){
                System.out.printf("Mes %3d:   %8f       %8f       %8f\n", e.getKey(),e.getValue()[1],e.getValue()[2],e.getValue()[3]);
                }
            }
        }

    public void querie_2_3(Map<Integer,int[] > res3) {
        System.out.println("Total de clientes que realizaram compras mẽs a mẽs filial a filial: \n");
        System.out.print("\nMes:            1         2         3         4         5         6         7         8         9         10        11        12      \n");
        for (Map.Entry<Integer, int[]> e : res3.entrySet()) {
            if (e.getKey() != 0) {
                System.out.printf("Filial %3d:", e.getKey());
                for (int i = 0; i < 12; i++) {
                    System.out.printf("     %5d", e.getValue()[i]);
                }
                System.out.print("\n");
            }
        }
    }


    //============================================Interativas ==========================================================


    public void insiraPag(){
        System.out.println("Insira a pagina que quer ver ou 0 para sair: ");
    }

    public void querie1(List<String> q1,int pag, int linhas, double time){
        int i;
        int size = q1.size();
        int totalpag = size/linhas ;
        System.out.println("Tempo de execução: " + time + "\n");
        if(size%linhas!=0)totalpag++;
        System.out.printf("O total de produtos nunca comprados é %d.\n",q1.size());
        System.out.printf("A apresentar pagina %d de %d :\n",pag,totalpag);
        for (i=(pag-1)*linhas;i<pag*linhas;i++){
            if(i<size)System.out.println(q1.get(i));
            else System.out.println();
        }

    }

    public void querie2(Map<Integer,int[]> q2, int mes, double time){
        System.out.println("Tempo de execução: " + time + "\n");
        System.out.printf("Para o Més %d:\n\n", mes);
        System.out.printf("O numero global de clientes diferentes que fizeram compras -> %d   \n", q2.get(0)[0]);
        System.out.printf("O numero total de vendas diferentes efetuadas -> %d    \n\n", q2.get(0)[1]);
        System.out.println("               nºclientes        nºvendas");
        for (Map.Entry<Integer,int[]> e : q2.entrySet()){
            if (e.getKey()!=0) System.out.printf("FILIAL %d:     %6d            %6d\n", e.getKey(),e.getValue()[0],e.getValue()[1]);
        }
    }



    //int[0] nvendas
    //int[1] n produtos diferentes
    //int[2] faturado

    public void querie3(Map<Integer,double[]> q3, String c, double time){
        System.out.println("Tempo de execução: " + time + "\n");
        System.out.println("Para o cliente:"+ c +"\n");
        System.out.println("            nªvendas          n0ºprodutos        faturado");
        for (Map.Entry<Integer,double[]> e : q3.entrySet()){
            System.out.printf("Mes %2d:     %2d                 %2d                 %8f  \n", e.getKey(),(int)e.getValue()[0],(int) e.getValue()[1], e.getValue()[2]);
        }
    }

    //quantidade
    //n clientes
    //gasto

    public void querie4(Map<Integer,double[]> q4, String p, double time){
        System.out.println("Tempo de execução: " + time + "\n");
        System.out.println("Para o produto:"+ p +"\n");
        System.out.println("              quant           nºclientes       gasto");
        for (Map.Entry<Integer,double[]> e : q4.entrySet()){
            System.out.printf("Mes %2d:     %5d          %5d               %6.6f  \n", e.getKey(),(int)e.getValue()[0], (int)e.getValue()[1], e.getValue()[2]);
        }
    }

    public void querie5(TreeSet<Map.Entry<String,Integer>>q5, String c,int pag,int linhas, double time){
        System.out.println("Tempo de execução: " + time + "\n");
        System.out.println("Para o cliente "+ c +":\n");

        int size = q5.size();
        int totalpag = size/linhas ;
        if(size%linhas!=0)totalpag++;

        System.out.printf("Output com %d linhas.\n",size);
        System.out.printf("A apresentar pagina %d de %d :\n",pag,totalpag);

        int i=0;
        System.out.println("Produto   ->  Quantidade");

        Iterator<Map.Entry<String,Integer>> it = q5.iterator();
        Map.Entry<String,Integer> aux;
        while(it.hasNext()){
            aux=it.next();
            if(i>=pag*linhas || i>size)break;
            if(i>=(pag-1)*linhas)System.out.println(aux.getKey()+"        " + aux.getValue());
            i++;
        }

        for (int k = i;k<=pag*linhas;k++) System.out.println();
    }


    //corrigir
    public void querie6(TreeSet<Map.Entry<String, int[]>> q6,int pag, int linhas, int limite, double time){
        int i;
        int size = Math.min(q6.size(), limite);
        int totalpag = size/linhas ;
        System.out.println("Tempo de execução: " + time + "\n");
        if(size%linhas!=0)totalpag++;


        if (q6.size()<= limite){
            System.out.print("O limite ultrapassa o tamanho da lista dos produtos. ");
        }else System.out.println();
        System.out.printf("Output com %d linhas.\n",size);


        System.out.printf("A apresentar pagina %d de %d :\n",pag,totalpag);

        i=0;
        System.out.println("Produto  ->  Quantidade    NºClientes");

        Iterator<Map.Entry<String,int[]>> it = q6.iterator();
        Map.Entry<String,int[]> aux;
        while(it.hasNext()){
            aux=it.next();
            if(i>=pag*linhas || i>size)break;
            if(i>=(pag-1)*linhas)System.out.printf("%s\t%10d\t%10d\n",aux.getKey(),aux.getValue()[0],aux.getValue()[1]);
            i++;
        }


        for (int k = i;k<=pag*linhas;k++) System.out.println();

    }

    public void querie7(Map<Integer, TreeSet<Map.Entry <String,Double>>> q7, double time){
        System.out.println("Tempo de execução: " + time + "\n");
        System.out.println("Os 3 maiores compradores para cada filial foram: ");
        for (Map.Entry<Integer, TreeSet<Map.Entry <String,Double>>> e : q7.entrySet()){
            System.out.printf("Filial %2d:\n",e.getKey());
            for (Map.Entry <String,Double> m : e.getValue()){
                System.out.println("->" + m.getKey());
            }
            System.out.println();
        }

    }

    //13 -> Q8 -Determinar os códigos dos X clientes que compraram mais produtos diferentes
    public void querie8(TreeSet<Map.Entry<String,Integer>> q8, int limite, int pag, int linhas, double time){
        int size = Math.min(q8.size(), limite);
        int totalpag = size/linhas;
        System.out.println("Tempo de execução: " + time + "\n");

        if(size%linhas!=0)totalpag++;

        if (q8.size()<= limite){
            System.out.print("O limite ultrapassa o tamanho da lista de clientes. ");
        }else System.out.println();
        System.out.printf("Output dos %d clientes que mais compraram produtos diferentes.\n",size);


        System.out.printf("A apresentar pagina %d de %d :\n",pag,totalpag);

        int i=0;
        System.out.println("Clientes   ->  NºProdutos");
        Iterator<Map.Entry<String,Integer>> it = q8.iterator();
        Map.Entry<String,Integer> aux;
        while(it.hasNext()){
            aux=it.next();
            if(i>=pag*linhas || i>size)break;
            if(i>=(pag-1)*linhas)System.out.println(aux.getKey()+"   ->  [" + aux.getValue()+"]");
            i++;
        }

        for (int k = i;k<=pag*linhas;k++) System.out.println();
    }


//14 -> Q9 -Determinar os códigos de clientes que mais compraram um X produto, e quanto gatou");
   public void querie9(TreeSet<Map.Entry<String,Double>> q9, int limite, String prod, int pag, int linhas, double time){
       int size = Math.min(q9.size(), limite);
       int totalpag = size/linhas;
       System.out.println("Tempo de execução: " + time + "\n");

       if(size%linhas!=0)totalpag++;

       if (q9.size()<= limite){
           System.out.print("O limite ultrapassa o tamanho da lista dos produtos. ");
       }else System.out.println();
       System.out.printf("Output dos %d clientes que mais compraram o produto %s.\n",size,prod);


       System.out.printf("A apresentar pagina %d de %d :\n",pag,totalpag);

       int i=0;
       System.out.println("Clientes       Dinheiro Gasto");
       Iterator<Map.Entry<String,Double>> it = q9.iterator();
       Map.Entry<String,Double> aux;
       while(it.hasNext()){
           aux=it.next();
           if(i>=pag*linhas || i>size)break;
           if(i>=(pag-1)*linhas)System.out.printf("%6s     %.2f\n",aux.getKey(),aux.getValue());
           i++;
       }

       for (int k = i;k<=pag*linhas;k++) System.out.println();
   }

//|15 -> Q10-Determinar a faturação total de cada produto, mês a mês, filial a filial         |
    public void querie10(Map<Integer,Map<String,Double>> q10, int mes, int pag,int totalpag, int linhas,int size, double time) {
        System.out.println("Tempo de execução: " + time + "\n");

        List<Iterator<Map.Entry<String, Double>>> lista = new ArrayList<>();
        Map.Entry<String, Double> aux;

        System.out.printf("Mes %d.\n", mes);
        System.out.printf("A apresentar pagina %d de %d :\n", pag, totalpag);
        for (Map.Entry<Integer, Map<String, Double>> m : q10.entrySet()) {
            System.out.printf("Filial %2d\t\t\t\t", m.getKey());
            lista.add(m.getValue().entrySet().iterator());
        }
        System.out.print("\n");
        int i = 0;
        int k;
        for (k = 0; k <= (pag - 1) * linhas; k++){
            for (Iterator<Map.Entry<String, Double>> m : lista) {
                if (m.hasNext()) {
                    m.next();
                }
            }
        }

        for (k =  (pag - 1) * linhas; k <= (pag) * linhas; k++){
            for (Iterator<Map.Entry<String, Double>> m : lista) {
                if (m.hasNext()) {
                    aux=m.next();
                    System.out.printf("%s->%8.2f\t\t",aux.getKey(),aux.getValue());
                }else System.out.print("         \t\t\t\t");
            }
            System.out.print("\n");
        }
    }

}






















































































