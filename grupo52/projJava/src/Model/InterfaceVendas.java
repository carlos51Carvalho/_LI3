package Model;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public interface InterfaceVendas {
    //int hashp(String p);
    //int hashc(String p);
    //boolean validate(String[] s, TreeSet<String> clientes, TreeSet<String> produtos, int nfiliais);
    int ler_vendas(InterfaceFaturacao fat, InterfaceFiliais fil, TreeSet<String> c, TreeSet<String> p, String filepath,int nfiliais) throws IOException;
    void numeroClientesByFil(InterfaceFiliais fil);
    String getNome();
    Vendas setNome(String nome);
    int getErrados();
    Vendas setErrados(int errados);
    int getTprod();
    Vendas setTprod(int tprod);
    int getDprod();
    Vendas setDprod(int dprod);
    int getPnc();
    Vendas setPnc(int pnc);
    int getTcl();
    Vendas setTcl(int tcl);
    int getTclc();
    Vendas setTclc(int tclc);
    int getTclsc();
    Vendas setTclsc(int tclsc);
    int getTvendaszero();
    Vendas setTvendaszero(int tvendaszero);
    double getFattotal();
    Vendas setFattotal(double fattotal);
    int[] getRes();
    //Vendas setRes(int[] res);
    Map<Integer,double[]> getRes2();
    Map<Integer,int[]> getRes3();


}
