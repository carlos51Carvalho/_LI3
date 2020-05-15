
/**
 * Write a description of class Produto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Produto
{
    private String produto;
    
    public static boolean validaCliente(String c){
        return true;
    }
    
    public Produto(String c){
        this.produto=c;
    }
    public Produto(Produto c){
        this.produto=c.getProduto();
    }
    
    public String getProduto(){
        return this.produto;
    }
    
    public int hashProduto(){
        return this.produto.charAt(0) - 'A';
    }
    
    public Produto clone(){
        return new Produto(this);
    }
    public int compareTo(Produto c){ 
        return this.produto.compareTo(c.getProduto());
    }
}
