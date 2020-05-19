
/**
 * Write a description of class Cliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cliente
{
    private String cliente;

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

    public static boolean validaCliente(String c){
        return c.charAt(0) >= 'A' && c.charAt(0) <= 'Z' && validaNum(c);
    }


    public int hashCliente(){
        return this.cliente.charAt(0) - 'A';
    }
    public static int hashString(String c){
        return c.charAt(0) - 'A';
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


    public Cliente clone(){
        return new Cliente(this);
    }

    public int compareTo(Cliente c){ 
        return this.cliente.compareTo(c.getCliente());
    }

}