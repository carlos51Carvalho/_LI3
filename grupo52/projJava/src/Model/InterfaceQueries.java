package Model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface InterfaceQueries {
    void reiniciarModelo();
    void leituras(String fc, String fp, String fv,int nfiliais) throws Exception ;
    //========================================estaticas============================================================

    String getNome();
    int getErrados();
    int getTprod();
    int getDprod();
    int getPnc();
    int getTcl();
    int getTclc();
    int getTclsc();
    int getTvendaszero();
    double getFattotal();
    int[] getRes();
    Map<Integer,double[]> getRes2();
    Map<Integer,int[]> getRes3();


    //====================================== interativas ===========================================================


    List<String> querie1();
    Map<Integer,int[]> querie2(int mes) throws ValorInvalidoException;
    Map<Integer,double[]> querie3(String cliente) throws ValorInvalidoException;
    Map<Integer,double[]> querie4(String prod) throws ValorInvalidoException;
    TreeSet<Map.Entry<String, Integer>> querie5(String c) throws ValorInvalidoException;
    TreeSet<Map.Entry<String, int[]>> querie6(int limite) throws ValorInvalidoException;
    Map<Integer, TreeSet<Map.Entry <String,Double>>> querie7();
    TreeSet<Map.Entry<String, Integer>> querie8(int limite) throws ValorInvalidoException;
    TreeSet<Map.Entry<String,Double>> querie9(int limite, String prod) throws ValorInvalidoException, NotValideException;
    Map<Integer,Map<Integer,Map<String,Double>>> querie10();

    void gravarObj(String filename) throws IOException;
    InterfaceQueries lerObj(String filename) throws IOException, ClassNotFoundException;
}
