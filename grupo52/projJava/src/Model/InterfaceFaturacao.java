package Model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface InterfaceFaturacao {
    void addProd(String p,int nfiliais);
    void addProds(Set<String> produtos,int nfiliais);
    void acrescentaFat(String[] p);
    int numeroProdUsados();
    int pBinaria(String p, int k);
    int numeroProdNuncaUsados();
    List<String> getQuerie1();
    void getFatPorMesEFil(Map<Integer,Map<Integer, Map<String,Double>>> res);



}