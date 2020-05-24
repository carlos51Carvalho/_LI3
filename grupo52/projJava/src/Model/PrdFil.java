
package Model;

import java.io.Serializable;
import java.util.Objects;

public class PrdFil implements Serializable {
    private String prd;
    private int qN;
    private int qP;
    private double gN;
    private double gP;

    /**
     * Construtor so com codigo de produto
     * @param prd       codigo de produto
     */
    public PrdFil(String prd) {
        this.prd = prd;
        this.qN = 0;
        this.qP = 0;
        this.gN = 0;
        this.gP = 0;
    }

    /**
     * Construtor parametrizado
     * @param prd       cosigo de produto
     * @param qN        int com quantidade em N
     * @param qP        int com quantidade em P
     * @param gN        double com gasto em N
     * @param gP        double com gasto em P
     */
    public PrdFil(String prd, int qN, int qP, double gN, double gP) {
        this.prd = prd;
        this.qN = qN;
        this.qP = qP;
        this.gN = gN;
        this.gP = gP;
    }

    /**
     * Get da variavel prd do objeto
     * @return String com codigo de produto
     */

    public String getPrd(){return this.prd;}

    /**
     * Set da variavel prd do objeto
     * @param prd       codigo de produto
     */

    public void setPrd(String prd){ this.prd = prd;}

    /**
     * Get da variavel qn do objeto
     * @return  int com quantidade em N
     */
    public int getqN() {
        return qN;
    }

    /**
     * Set da variavel qn do obeto
     * @param qN        int com quantidade em N
     */

    public void setqN(int qN) {
        this.qN = qN;
    }

    /**
     * Get da variavel qn do objeto
     * @return  int com quantidade em N
     */
    public int getqP() {
        return qP;
    }

    /**
     * Set da variavel qp do obeto
     * @param qP        int com quantidade em P
     */
    public void setqP(int qP) {
        this.qP = qP;
    }

    /**
     * Get da variavel gn do objeto
     * @return  double com gasto em N
     */
    public double getgN() {
        return gN;
    }

    /**
     * Set da variavel gn do obeto
     * @param gN        double com gasto em N
     */
    public void setgN(double gN) {
        this.gN = gN;
    }

    /**
     * Get da variavel gp do objeto
     * @return  double com gasto em P
     */
    public double getgP() {
        return gP;
    }

    /**
     * Set da variavel qp do obeto
     * @param gP       double com gasto em P
     */
    public void setgP(double gP) {
        this.gP = gP;
    }

    /**
     * Método que adiciona uma quantidade em N á existente
     * @param qN        int com quantidade em N
     */
    public void addqN(int qN){ this.qN += qN;
    }

    /**
     * Método que adiciona uma quantidade em P á existente
     * @param qP        int com quantidade em P
     */
    public void addqP(int qP){
        this.qP += qP;
    }

    /**
     * Método que adiciona uma gasto em P ao existente
     * @param gP        double com gasto em P
     */
    public void addgP(double gP){
        this.gP+=gP;
    }

    /**
     * Método que adiciona uma gasto em N ao existente
     * @param gN        double com gasto em N
     */
    public void addgN(double gN){
        this.gN+=gN;
    }

    /**
     * Método que devolve o total gasto neste produto
     * @return double com gasto N e P
     */
    public double getTotalPago(){
        return this.gP + this.gN;
    }

    /**
     * Método que devolve a quantidade total comprada neste produto
     * @return int com quantidade em N e P
     */

    public int getTotalComprado(){
        return this.qP + this.qN;
    }

    /**
     * Método que verifica se dois objetos PrdFil sao iguais
     * @param o     Objeto
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrdFil prdFil = (PrdFil) o;
        return prd == prdFil.prd &&
                qN == prdFil.qN &&
                qP == prdFil.qP &&
                Double.compare(prdFil.gN, gN) == 0 &&
                Double.compare(prdFil.gP, gP) == 0;
    }

    /**
     * Método que passa todas a variaveis do objeto para uma string
     * @return  String do objeto
     */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Model.PrdFil{");
        sb.append("qN=").append(qN);
        sb.append(", qP=").append(qP);
        sb.append(", gN=").append(gN);
        sb.append(", gP=").append(gP);
        sb.append('}');
        return sb.toString();
    }
}