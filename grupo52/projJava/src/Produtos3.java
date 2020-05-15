
/**
 * Write a description of class Produtos3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Produtos3
{
    // instance variables - replace the example below with your own
    private Map<Integer,Set<Produto>> produtos;
    
    public Produtos3(){
        this.produtos= new TreeMap<Integer,Set<Produto>>();
    }
    
    public Produtos3(Produtos3 c){
        this.setProdutos(c.getProdutos());
    }
    public void setProdutos(Map<Integer,Set<Produto>> c){
        this.produtos = new TreeMap<Integer,Set<Produto>>();
        
        Set<Map.Entry<Integer,Set<Produto>>> es = c.entrySet();
        for(Map.Entry<Integer,Set<Produto>> e:es){
            this.produtos.put(e.getKey(),new TreeSet<Produto>());
            for(Produto s:e.getValue()){
                this.produtos.get(e.getKey()).add(s);
            }
        }
    }
    public Map<Integer,Set<Produto>> getProdutos(){
        Map<Integer,Set<Produto>> aux = new TreeMap<Integer,Set<Produto>>();
        
        Set<Map.Entry<Integer,Set<Produto>>> es = this.produtos.entrySet();
        for(Map.Entry<Integer,Set<Produto>> e:es){
            aux.put(e.getKey(),new TreeSet<Produto>());
            for(Produto s:e.getValue()){
                aux.get(e.getKey()).add(s);
            }
        }
        return aux;
    }
    
   
    public Set<String> getSetDeProdutos(){
        Set<String> res = new TreeSet<String>();
        for(Set<Produto> c:this.produtos.values()){
            for(Produto s:c){
                res.add(s.getProduto());
            }
        }
        return res;
    }
   
    
    
    public void addProduto(Produto c){
        int i=c.hashProduto();
        if(!this.produtos.containsKey(i)){
            this.produtos.put(i,new TreeSet<Produto>(new ComparatorProduto()));
        }
        this.produtos.get(i).add(c);
    }
    
    public Produtos3 clone(){
        return new Produtos3(this);
    }
    
}
