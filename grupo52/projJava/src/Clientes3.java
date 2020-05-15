
/**
 * Write a description of class Clientes3 here.
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

public class Clientes3
{
    // instance variables - replace the example below with your own
    private Map<Integer,Set<Cliente>> clientes;
    
    public Clientes3(){
        this.clientes= new TreeMap<Integer,Set<Cliente>>();
    }
    
    public Clientes3(Clientes3 c){
        this.setClientes(c.getClientes());
    }
    public void setClientes(Map<Integer,Set<Cliente>> c){
        this.clientes = new TreeMap<Integer,Set<Cliente>>();
        
        Set<Map.Entry<Integer,Set<Cliente>>> es = c.entrySet();
        for(Map.Entry<Integer,Set<Cliente>> e:es){
            this.clientes.put(e.getKey(),new TreeSet<Cliente>());
            for(Cliente s:e.getValue()){
                this.clientes.get(e.getKey()).add(s);
            }
        }
    }
    public Map<Integer,Set<Cliente>> getClientes(){
        Map<Integer,Set<Cliente>> aux = new TreeMap<Integer,Set<Cliente>>();
        
        Set<Map.Entry<Integer,Set<Cliente>>> es = this.clientes.entrySet();
        for(Map.Entry<Integer,Set<Cliente>> e:es){
            aux.put(e.getKey(),new TreeSet<Cliente>());
            for(Cliente s:e.getValue()){
                aux.get(e.getKey()).add(s);
            }
        }
        return aux;
    }
    
   
    public Set<String> getSetDeClientes(){
        Set<String> res = new TreeSet<String>();
        for(Set<Cliente> c:this.clientes.values()){
            for(Cliente s:c){
                res.add(s.getCliente());
            }
        }
        return res;
    }
   
    
    
    public void addCliente(Cliente c){
        int i=c.hashCliente();
        if(!this.clientes.containsKey(i)){
            this.clientes.put(i,new TreeSet<Cliente>(new ComparatorCliente()));
        }
        this.clientes.get(i).add(c);
    }
    
    public Clientes3 clone(){
        return new Clientes3(this);
    }
    
}
