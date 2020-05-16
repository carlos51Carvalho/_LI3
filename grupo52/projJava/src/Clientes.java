import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Clientes {
    private Map<Integer, Set<String>> clientes;

    public Clientes() {
        this.clientes = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.clientes.put(i, new TreeSet<>());
    }
    public Clientes(Clientes c){
        setClientes(c.getClientes());
    }

    public int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }

    public boolean validaNum(String cl) {
        boolean r = true;
        for (int i = 1; i < 5 && r; i++) {
            if (i == 1) {
                if (cl.charAt(i) < '1' && cl.charAt(i) > '9') r = false;
                else {
                    if (cl.charAt(i) < '0' && cl.charAt(i) > '9') r = false;
                }
            }
        }
        return r;
    }

    public Clientes clone(){
        return new Clientes(this);
    }

    public Map<Integer, Set<String>> getClientes(){
        Map<Integer, Set<String>> novo = new HashMap();
        for(int i = 0; i<26; i++){
            novo.put(i, cloneSet(this.clientes.get(i)));
        }
        return novo;
    }

    public void setClientes(Map<Integer, Set<String>> clientes) {
        this.clientes = new HashMap<>();
        for(int i=0; i<26; i++) {
            this.clientes.put(i,cloneSet(clientes.get(i)));
        }
    }

    public Set<String> cloneSet(Set<String> t){
        Set<String> novo = new TreeSet<>(t);
        return novo;
    }


    public boolean validaCliente(String cl) {
        return cl.charAt(0) >= 'A' && cl.charAt(0) <= 'Z' && validaNum(cl);
    }

    public void  addCliente(String p){
        this.clientes.get(hashCL(p)).add(p);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Clientes{");
        sb.append("clientes=").append(clientes);
        sb.append('}');
        return sb.toString();
    }

    public void rmCliente(String cl){
        this.clientes.get(hashCL(cl)).remove(cl);
    }

    public int ler_clientes(String filepath) throws Exception
    {
        int i = 0;

        File file = new File(filepath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {

            if (validaCliente(st)) {
                //System.out.println(hashCL(st));
                addCliente(st);
                i++;
            }
        }
    System.out.println("Clientes Lidos \n");
    return i;
    }

    public TreeSet<String> getC(int i) {
        return new TreeSet<>(clientes.get(i));
    }

    private Set<String> get(int kc) {
        return clientes.get(kc);
    }

    public Collection<String> clStartedByLetter(char l){
        return new ArrayList<>(clientes.get(l-65));

    }


    public int size() {
        int res =0;
        for (int i = 0; i < 26; i++) {
            res += clientes.get(i).size();
        }
        return res;
    }


    public boolean existe(String s) {
        return clientes.get(hashCL(s)).contains(s);
    }

}
