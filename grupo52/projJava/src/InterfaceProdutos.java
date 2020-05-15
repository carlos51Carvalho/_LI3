import java.util.Collection;


public interface InterfaceProdutos {

    void  addProduto(String p);
    void rmProd(String p);
    Collection<String> prodStartedByLetter(char l);
    int ler_produtos(String filepath) throws Exception;

}
