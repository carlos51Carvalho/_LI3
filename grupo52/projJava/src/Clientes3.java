

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Clientes3 implements InterfaceClientes
{
    // instance variables - replace the example below with your own
    private Map<Integer,Set<Cliente>> clientes;
    
    public Clientes3(){
        this.clientes= new HashMap<>();
    }
    
    public Clientes3(Clientes3 c){
        this.setClientes(c.getClientes());
    }

    public void setClientes(Map<Integer,Set<Cliente>> c){
        this.clientes = new TreeMap<>();
        
        Set<Map.Entry<Integer,Set<Cliente>>> es = c.entrySet();
        for(Map.Entry<Integer,Set<Cliente>> e:es){
            this.clientes.put(e.getKey(),new TreeSet<>(new ComparatorCliente()));
            for(Cliente s:e.getValue()){
                this.clientes.get(e.getKey()).add(s.clone());
            }
        }
    }
    public Map<Integer,Set<Cliente>> getClientes(){
        Map<Integer,Set<Cliente>> aux = new TreeMap<>();
        
        Set<Map.Entry<Integer,Set<Cliente>>> es = this.clientes.entrySet();
        for(Map.Entry<Integer,Set<Cliente>> e:es){
            aux.put(e.getKey(),new TreeSet<>(new ComparatorCliente()));
            for(Cliente s:e.getValue()){
                aux.get(e.getKey()).add(s.clone());
            }
        }
        return aux;
    }
    
   
    public TreeSet<String> getSetDeClientes(){
        TreeSet<String> res = new TreeSet<>();
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
            this.clientes.put(i,new TreeSet<>(new ComparatorCliente()));
        }
        this.clientes.get(i).add(c);
    }

    public void addCliente(String cc){ //com String (respeitar interface)
        Cliente c= new Cliente(cc);
        int i=c.hashCliente();
        if(!this.clientes.containsKey(i)){
            this.clientes.put(i,new TreeSet<>(new ComparatorCliente()));
        }
        this.clientes.get(i).add(c);
    }
    
    public void rmCliente(String s){
        Iterator<Cliente> it;
        int i=Cliente.hashString(s);
        boolean menor=true;
        if(this.clientes.containsKey(i)){
            it=this.clientes.get(i).iterator();
            while(it.hasNext() && menor){
                Cliente p = it.next();
                if(p.getCliente().compareTo(s)==0){
                    menor=false;
                    this.clientes.get(i).remove(p);
                }
                else{
                    if(p.getCliente().compareTo(s)>0)menor=false;
                }
            }
        }
    }
    
    public Clientes3 clone(){
        return new Clientes3(this);
    }
    
    public Set<String> clStartedByLetter(char l){
        Set<String> res = new TreeSet<>();
        if(this.clientes.containsKey(l-'A')){
            for(Cliente s:this.clientes.get(l-'A')){
                res.add(s.getCliente());
            }   
        }
        return res;
    }

    public int ler_clientes(String filepath) throws Exception{
        Crono.start();
        String st;
        int i=0;
        File file= new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((st = br.readLine()) != null) {
            if(Cliente.validaCliente(st)){
                this.addCliente(new Cliente(st));
                i++;
            }
        }
        System.out.println("Clientes Lidos em:");
        System.out.println(Crono.getTImeString());
        return i;
    }
    
    public int size(){
        int res=0;
        for(Set<Cliente> c:this.clientes.values()){
            res+=c.size();
        }
        return res;
    }

    public boolean existe(String s){
        int i = Cliente.hashString(s);
        Iterator<Cliente> it;
        boolean res=false,menor=true;

        if(this.clientes.containsKey(i)){
            it=this.clientes.get(i).iterator();
            while(it.hasNext() && !res && menor){
                Cliente p = it.next();
                if(p.getCliente().compareTo(s)==0)res=true;
                else{
                    if(p.getCliente().compareTo(s)>0)menor=false;
                }
            }
            
        }
        return res;
    }

}