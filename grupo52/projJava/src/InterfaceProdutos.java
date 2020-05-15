import java.util.Collection;


public interface InterfaceProdutos {

    void  addProduto(String p);
    void rmProduto(String p);
    Collection<String> prodStartedByLetter(char l);
    int numeroProdValidos();
    int ler_produtos(String filepath) throws Exception;

}
