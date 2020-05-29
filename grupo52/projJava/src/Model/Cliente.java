
package Model;

import java.io.Serializable;

/**
 * Write a description of class Model.Cliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

/**
 * Classe que guarda a informação relativa a um cliente
 */
public class Cliente implements Serializable
{
    private String cliente;

    /**
     * Método que verifica se os numeros de um codigo de cliente sao válidos
     * @param p     Codigo de cliente
     * @return boolean
     */

    public static boolean validaNum(String p) {
        boolean r = true;
        for (int i = 1; i < 5 && r; i++) {
            if (i == 1) {
                if (p.charAt(i) < '1' && p.charAt(i) > '9') r = false;
                else {
                    if (p.charAt(i) < '0' && p.charAt(i) > '9') r = false;
                }
            }
        }
        return r;
    }

    /**
     * Método que verifica se um código de cliente é válido
     * @param c     código de cliente
     * @return boolean
     */

    public static boolean validaCliente(String c){
        return c.charAt(0) >= 'A' && c.charAt(0) <= 'Z' && validaNum(c);
    }

    /**
     * Método que devolve a chave do cliente
     * @return int que representa a chave
     */

    public int hashCliente(){
        return this.cliente.charAt(0) - 'A';
    }

    /**
     * Método que calcula a chave para um cliente
     * @param c     código de cliente
     * @return int que representa a chave
     */
    public static int hashString(String c){
        return c.charAt(0) - 'A';
    }

    /**
     * Contrutor com argumento
     * @param c     código de cliente
     */

    public Cliente(String c){
        this.cliente=c;
    }

    /**
     * Contrutor por cópia
     * @param c     código de cliente
     */
    public Cliente(Cliente c){
        this.cliente=c.getCliente();
    }

    /**
     * Get da variável cliente do objeto
     * @return String com código do cliente
     */

    public String getCliente(){
        return this.cliente;
    }

    /**
     * Método que clona este objeto
     * @return clone do objeto
     */

    public Cliente clone(){
        return new Cliente(this);
    }

    /**
     * Método que compara dois clientes
     * @param c     código de cliente
     * @return resultado da comparação
     */
    public int compareTo(Cliente c){ 
        return this.cliente.compareTo(c.getCliente());
    }

}