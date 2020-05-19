import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface InterfaceFiliais {

    boolean existeProd(String cl, int ip, int f, int m, String p);
    void acrescentaFil(String[] cl);
    void addCl(String cl);
    void addCls(Set<String> clientes);
    int numeroComparadores();
    int naocompram();



}
