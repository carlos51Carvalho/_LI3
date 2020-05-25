package Model;

import java.io.*;
import java.util.*;

/**
 * Classe que guarda todos os clientes lidos
 */
public class Clientes3 implements InterfaceClientes, Serializable
{
    // instance variables - replace the example below with your own
    private Map<Integer,Set<Cliente>> clientes;

    /**
     * Construtor sem argumentos
     */
    public Clientes3(){
        this.clientes= new HashMap<>();
    }

    /**
     * Construtor por cópia
     * @param c     cliente
     */
    public Clientes3(Clientes3 c){
        this.setClientes(c.getClientes());
    }

    /**
     * Set para a variavel clientes do objeto
     * @param c     Map com os clientes a inserir
     */

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

    /**
     * Get para a variavel clientes do objeto
     * @return Cópia dos clientes do objeto
     */
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

    /**
     * Método que devolve um set dos clientes do objeto
     * @return Set com cópia dos clientes
     */
   
    public TreeSet<String> getSetDeClientes(){
        TreeSet<String> res = new TreeSet<>();
        for(Set<Cliente> c:this.clientes.values()){
            for(Cliente s:c){
                res.add(s.getCliente());
            }
        }
        return res;
    }


    /**
     * Método que adiciona um cliente á variavel clientes do objeto
     * @param c     cliente
     */
    public void addCliente(Cliente c){
        int i=c.hashCliente();
        if(!this.clientes.containsKey(i)) this.clientes.put(i,new TreeSet<>(new ComparatorCliente()));

        this.clientes.get(i).add(c);
    }


    /**
     * Método que adiciona um cliente á variavel clientes do objeto
     * @param cc   String com código de cliente
     */
    public void addCliente(String cc){ //com String (respeitar interface)
        Cliente c= new Cliente(cc);
        int i=c.hashCliente();
        if(!this.clientes.containsKey(i)){
            this.clientes.put(i,new TreeSet<>(new ComparatorCliente()));
        }
        this.clientes.get(i).add(c);
    }

    /**
     * Método que remove um cliente a variavel clientes do objeto
     * @param s     String com código de cliente
     */
    
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

    /**
     * Método que clona este objeto
     * @return  clone do objeto
     */
    public Clientes3 clone(){
        return new Clientes3(this);
    }

    /**
     * Método que devolve um set com todos os clientes começados por uma letra
     * @param l     char com a letra a procurar
     * @return  Set com cópia dos clientes
     */
    public Set<String> clStartedByLetter(char l){
        Set<String> res = new TreeSet<>();
        if(this.clientes.containsKey(l-'A')){
            for(Cliente s:this.clientes.get(l-'A')){
                res.add(s.getCliente());
            }   
        }
        return res;
    }

    /**
     * Método que lê de um ficheiro
     * @param filepath ficheiro a ler
     * @return int com número de linhas lidas
     * @throws IOException excessao se da erro na abertura/leitura do ficheiro
     */

    public int ler_clientes(String filepath) throws IOException {
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

    /**
     * Método que devolve o tamanho da variavel clientes do objeto
     * @return int com o tamanho
     */
    
    public int size(){
        int res=0;
        for(Set<Cliente> c:this.clientes.values()){
            res+=c.size();
        }
        return res;
    }

    /**
     * Método que verifica se um cliente existe
     * @param s     String com codigo de cliente
     * @return  boolean com o resultado
     */

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