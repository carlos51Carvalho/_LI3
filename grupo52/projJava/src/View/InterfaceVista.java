package View;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface InterfaceVista {
    void printHeader();
    void printmenu();
    void printBarraN();
    void flush();
    void pressioneEnter();
    void printExit();
    void printDone();
    void printError();
    void printNotLoad();
    void printEscrever();
    void printErrorFIle(String e);
    void printOpLeitura();
    void fileNameC();
    void fileNameP();
    void fileNameV();
    void printFilePaths(String fc,String fp,String fv, double time);
    void printMes();
    void printErrorMes();
    void printErrorCliente();
    void printCliente();
    void printErrorProduto();
    void printProduto();
    void printLimite();
    void printErrorLimite();
    void printErrorPagina(int pag);
    //void printfileName(String nome);
    void queriesEstatisticas(String n, int errados, int tprod, int dprod, int pnc, int tcl,int tclc,int tclsc,int tvendaszero,double fattotal);
    void querie_2_1(int[] a);
    void querie_2_2(Map<Integer,double[] > res2);
    void querie_2_3(Map<Integer,int[] > res3);
    void insiraPag();
    void querie1(List<String> q1, int pag, int linhas, double time);
    void querie2(Map<Integer,int[]> q2, int mes, double time);
    void querie3(Map<Integer,double[]> q3, String c, double time);
    void querie4(Map<Integer,double[]> q4, String p, double time);
    void querie5(TreeSet<Map.Entry<String,Integer>> q5, String c, int pag, int linhas, double time);
    void querie6(TreeSet<Map.Entry<String, int[]>> q6,int pag, int linhas, int limite, double time);
    void querie7(Map<Integer, TreeSet<Map.Entry <String,Double>>> q7, double time);
    void querie8(TreeSet<Map.Entry<String,Integer>> q8, int limite, int pag, int linhas, double time);
    void querie9(TreeSet<Map.Entry<String,Double>> q9, int limite, String prod, int pag, int linhas, double time);
    void querie10(Map<Integer,Map<String,Double>> q10, int mes, int pag,int totalpag, int linhas,int size, double time);


}
