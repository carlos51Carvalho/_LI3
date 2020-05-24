
package Model;

import java.io.Serializable;

public class MesFat implements Serializable {
    private double fN;
    private double fP;
    private int vN;
    private int vP;

    /**
     * Construtor sem parâmetros
     */
    public MesFat(){
        this.fN = 0;
        this.vN = 0;
        this.fP = 0;
        this.vP = 0;
    }

    /**
     * Construtor por cópia
     * @param m     Objeto a ser copiado
     */
    public MesFat(MesFat m){
        this.fN = m.getfN();
        this.vN = m.getvN();
        this.fP = m.getfP();
        this.vP = m.getvP();
    }

    /**
     * Get da variavel fN do objeto
     * @return double com faturação em N
     */
    public double getfN() {
        return this.fN;
    }

    /**
     * Set da variavel fN do objeto
     * @param fN        double com faturação em N
     */
    public void setfN(double fN) {
        this.fN = fN;
    }

    /**
     * Get da variavel fP do objeto
     * @return double com faturação em P
     */
    public double getfP() {
        return this.fP;
    }
    /**
     * Set da variavel fP do objeto
     * @param fP        double com faturação em P
     */

    public void setfP(double fP) {
        this.fP = fP;
    }

    /**
     * Get da variavel vN do objeto
     * @return int com vendas em N
     */
    public int getvN() {
        return this.vN;
    }

    /**
     * Set da variavel vN do objeto
     * @param vN        int com vendas em N
     */
    public void setvN(int vN) {
        this.vN = vN;
    }

    /**
     * Get da variavel vP do objeto
     * @return int com vendas em P
     */
    public int getvP() {
        return this.vP;
    }

    /**
     * Set da variavel vP do objeto
     * @param vP        int com vendas em P
     */
    public void setvP(int vP) {
        this.vP = vP;
    }

    /**
     * Método que incrementa a variavel vN do objeto
     */
    public void incvN(){
        this.vN += 1;
    }
    /**
     * Método que incrementa a variavel vP do objeto
     */

    public void incvP(){
        this.vP += 1;
    }

    /**
     * Método que adiciona uma faturação em N à existente
     * @param n     double com faturação em N
     */
    public void addfN(double n){
        this.fN += n;
    }

    /**
     * Método que adiciona uma faturação em P à existente
     * @param p     double com faturação em P
     */
    public void addfP(double p){
        this.fP += p;
    }

    /**
     * Método que devolve o total de vendas realizadas
     * @return int com vendas em N e P
     */
    public int totalVendasMes(){
        return getvN()+getvP();
    }

    /**
     * Método que devolve o total faturado
     * @return double com faturação em N e P
     */
    public double totalFatMes(){
        return getfN()+getfP();
    }

}