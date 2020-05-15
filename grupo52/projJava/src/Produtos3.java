
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
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Produtos3 implements InterfaceProdutos
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
    }public void addProduto(String cc){//respeitar a interface
        Produto c = new Produto(cc);
        int i=c.hashProduto();
        if(!this.produtos.containsKey(i)){
            this.produtos.put(i,new TreeSet<Produto>(new ComparatorProduto()));
        }
        this.produtos.get(i).add(c);
    }
    
    public Produtos3 clone(){
        return new Produtos3(this);
    }
    
    public Set<String> prodStartedByLetter(char l){
        Set<String> res = new TreeSet<>();
        if(this.produtos.containsKey(l-'A')){
            for(Produto s:this.produtos.get(l-'A')){
                res.add(s.getProduto());
            }   
        }
        return res;
    }
    public void rmProduto(String c){
        int i=Produto.hashString(c);
        if(this.produtos.containsKey(i)){
            this.produtos.get(i).remove(c);
        }
    }
    
    public int ler_produtos(String filepath) throws Exception{
        File file2= new File(filepath);
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        String st;
        int i=0;
        while ((st = br2.readLine()) != null) {
            
            if(Produto.validaProduto(st)){
                i++;
                this.addProduto(new Produto(st));
            }
        }
        return i;
    }
    
    public int size(){
        int res=0;
        for(Set<Produto> c:this.produtos.values()){
            res+=c.size();
        }
        return res;
    }
}
