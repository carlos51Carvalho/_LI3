import java.util.Collection;


public interface InterfaceProdutos {

    void addProduto(String p);
    void rmProduto(String p);
    Collection<String> prodStartedByLetter(char l);
    int ler_produtos(String filepath) throws Exception;
    int size();
    boolean existe(String s);

}