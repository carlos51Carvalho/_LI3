
package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FilFat implements Serializable {
    private int used;
    private Map<Integer, MesFat> filial;

    /**
     * Construtor sem parametros
     */
    public FilFat(){
        this.used = 0;
        this.filial = new HashMap<>();
        for (int i = 1; i<13 ; i++)
            this.filial.put(i, new MesFat());
    }

    /**
     * Get da variavel used do objeto
     * @return
     */
    public int getUsed() {
        return this.used;
    }

    /**
     * Set da variavel used do objeto
     * @param used      boolean
     */
    public void setUsed(int used) {
        this.used = used;
    }

    /**
     * Método que incrementa a variavel used do objeto
     */
    public void incUsed(){
        this.used += 1;
    }

    /**
     * Get da variavel filial do objeto
     * @return Map de objetos MesFat
     */
    public Map<Integer,MesFat> getFilF(){
        return this.filial;
    }

    /**
     * Método que adiciona uma faturação em N à existente
     * @param m     int com mes
     * @param p     double com faturação
     */
    public void addFN(int m, double p){
        this.filial.get(m).addfN(p);
    }

    /**
     * Método que adiciona uma faturação em P à existente
     * @param m     int com mes
     * @param p     double com faturação
     */
    public void addFP(int m, double p){
        this.filial.get(m).addfP(p);
    }

    /**
     * Método que incrementa as vendas em N
     * @param m     int com mes
     */
    public void incVN(int m){
        this.filial.get(m).incvN();
    }
    /**
     * Método que incrementa as vendas em P
     * @param m     int com mes
     */
    public void incVP(int m){
        this.filial.get(m).incvP();
    }

    /**
     * Método que devolve a faturação total num mês
     * @param i     int com chave
     * @return double com faturação total
     */

    public double getFatPorMes(int i){
        return this.filial.get(i).totalFatMes();
    }
}