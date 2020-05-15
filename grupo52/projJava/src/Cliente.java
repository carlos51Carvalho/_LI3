
/**
 * Write a description of class Cliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cliente
{
    private String cliente;
    
    public static boolean validaCliente(String c){
        return true;
    }
    
    public Cliente(String c){
        this.cliente=c;
    }
    public Cliente(Cliente c){
        this.cliente=c.getCliente();
    }
    
    public String getCliente(){
        return this.cliente;
    }
    
    public int hashCliente(){
        return this.cliente.charAt(0) - 'A';
    }
    
    public Cliente clone(){
        return new Cliente(this);
    }
    public int compareTo(Cliente c){ 
        return this.cliente.compareTo(c.getCliente());
    }
}
