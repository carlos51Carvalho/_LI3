package Model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface InterfaceFiliais {

    boolean existeProd(String cl, int ip, int f, int m, String p);
    void acrescentaFil(String[] cl);
    void addCl(String cl,int nfilias);
    void addCls(Set<String> clientes,int nfilias);
    int numeroComparadores();
    int naocompram();
    boolean getMesUsed(int key, int ip, int f, int m);
    int getNVendasMes(int key, int ip, int f, int m);
    int pBinaria(String p, int k);
    Map<Integer, int[]> getUsedFilialMes(Map<Integer, int[]> res);
    Map<Integer, int[]> getVendasTotaisFiliaisPorMes(int mes,Map<Integer, int[]> res);
    Map<Integer,double[]> getQuerie3(int kc, int ip);
    Map<Integer,double[]> getQuerie4(String prod);
    void getQuerie5(int kc, int ip,Map<String, Integer> q5);
    Map<String,int[]> getQuerie6();
    Map<Integer, Map<String, Double>> getQuerie7();
    Map<String, Integer> getQuerie8();
    Map<String,Double> getQuerie9(String prod);


}