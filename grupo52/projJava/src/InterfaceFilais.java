import java.util.List;
import java.util.TreeSet;

public interface InterfaceFilais {

    boolean existeProd(String cl, int ip, int f, int m, String p);
    void acrescentaFil(String[] cl);
    List<ClFil> getArr(int i);
    void addCl(String cl);
    void addCls(TreeSet<String> clientes);

}
