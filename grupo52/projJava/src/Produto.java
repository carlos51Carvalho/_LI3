
/**
 * Write a description of class Produto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Produto
{
    private String produto;


    public static boolean validaNum(String p) {
        boolean r = true;
        for (int i = 2; i < 6 && r; i++) {
            if (i == 2) {
                if (p.charAt(i) < '1' && p.charAt(i) > '9') r = false;
                else {
                    if (p.charAt(i) < '0' && p.charAt(i) > '9') r = false;
                }
            }
        }
        return r;
    }


    public static boolean validaProduto(String c){
        return c.charAt(0) >= 'A' && c.charAt(0) <= 'Z' && c.charAt(1) >= 'A' && c.charAt(1) <= 'Z'&& validaNum(c);
    }
    public static int hashString(String c){
        return c.charAt(0) - 'A';
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