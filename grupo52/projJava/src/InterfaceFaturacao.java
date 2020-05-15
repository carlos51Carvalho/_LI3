import java.util.List;
import java.util.TreeSet;

public interface InterfaceFaturacao {

    List<ProdFat> getArr(int i);
    void addProd(String p);
    void addProds(TreeSet<String> produtos);
    void acrescentaFat(String[] p);


}
