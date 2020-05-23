
package Model;

import java.io.Serializable;

/**
 * Write a description of class Model.Produto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

/**
 * Classe que guarda a informação relativa a um produto
 */
public class Produto implements Serializable
{
    private String produto;

    /**
     * Método que verifica se os numeros de um codigo de produto sao válidos
     * @param p     String com Codigo de produto
     * @return boolean
     */

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

    /**
     * Método que verifica se um código de produto é válido
     * @param c     código de produto
     * @return boolean
     */

    public static boolean validaProduto(String c){
        return c.charAt(0) >= 'A' && c.charAt(0) <= 'Z' && c.charAt(1) >= 'A' && c.charAt(1) <= 'Z'&& validaNum(c);
    }

    /**
     * Método que calcula a chave para um produto
     * @param c     código de produto
     * @return int que representa a chave
     */
    public static int hashString(String c){
        return c.charAt(0) - 'A';
    }

    /**
     * Contrutor com argumento
     * @param c     String com código de produto
     */
    public Produto(String c){
        this.produto=c;
    }

    /**
     * Contrutor por cópia
     * @param c     código de produto
     */
    public Produto(Produto c){
        this.produto=c.getProduto();
    }

    /**
     * Get da variável produto do objeto
     * @return String com código do produto
     */
    public String getProduto(){
        return this.produto;
    }

    /**
     * Método que devolve a chave do produto
     * @return int que representa a chave
     */
    public int hashProduto(){
        return this.produto.charAt(0) - 'A';
    }

    /**
     * Método que clona este objeto
     * @return clone do objeto
     */
    
    public Produto clone(){
        return new Produto(this);
    }

    /**
     * Método que compara dois produtos
     * @param c      produto
     * @return resultado da comparação
     */
    public int compareTo(Produto c){ 
        return this.produto.compareTo(c.getProduto());
    }
}