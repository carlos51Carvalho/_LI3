
package Model;

import java.io.Serializable;

import java.util.*;


public class MesFil implements Serializable {
    private boolean used;
    private int nVendas;
    private Map<String,PrdFil> prs;

    /**
     * Construtor sem parâmetros
     */
    public MesFil()
    {
        this.used =false;
        this.nVendas = 0;
        this.prs = new HashMap<>();
    }

    /**
     * Construtor parametrizado
     * @param n     int com numero de vendas
     * @param prs   Map de objetos PrdFil
     */

    public MesFil(int n, Map<String, PrdFil> prs) {
        this.used = false;
        this.nVendas = n;
        this.prs = prs;
    }

    /**
     * Get da variavel nvendas do objeto
     * @return int com numero de vendas
     */
    public int getnVendas() {
        return nVendas;
    }

    /**
     * Set da variavel nvendas do objeto
     * @param nVendas       int com numero de vendas
     * @return objeto
     */

    public MesFil setnVendas(int nVendas) {
        this.nVendas = nVendas;
        return this;
    }

    /**
     * Get da variavel used do objeto
     * @return boolean
     */

    public boolean isUsed() {
        return used;
    }

    /**
     * Set da variavel used do objeto
     * @param used      boolean
     */

    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Método que verifica se um produto foi comprado
     * @param p         codigo de produto
     * @return  boolean
     */
    public boolean existeProd(String p){
        return this.prs.containsKey(p);
    }

    /**
     * Método que adiciona um gasto em modo 'N' á filial
     * @param p     codigo de produto
     * @param t     double com valor a adicionar
     */
    public void addgN(String p, double t){
        this.prs.get(p).addgN(t);
    }
    /**
     * Método que adiciona uma quantidade em modo 'N' á filial
     * @param p     codigo de produto
     * @param t     int com valor a adicionar
     */
    public void addqN(String p, int t){
        this.prs.get(p).addqN(t);
    }
    /**
     * Método que adiciona um gasto em modo 'P' á filial
     * @param p     codigo de produto
     * @param t     double com valor a adicionar
     */
    public void addgP(String p, double t){
        this.prs.get(p).addgP(t);
    }
    /**
     * Método que adiciona uma quantidade em modo 'P' á filial
     * @param p     codigo de produto
     * @param t     int com valor a adicionar
     */
    public void addqP(String p, int t){
        this.prs.get(p).addqP(t);
    }

    /**
     * Método que adiciona um produto á filial
     * @param p     codigo de produto
     */
    public void addPrs(String p){
        this.prs.put(p, new PrdFil(p));
    }

    /**
     * Método que incrementa o numero de vendas
     */

    public void incnVendas(){
        int n = this.nVendas;
        setnVendas(n+1);
    }

    /**
     * Get da variavel prs do objeto
     * @return Map de objetos PrdFil
     */
    public Map<String, PrdFil> getPrs() {
        return new HashMap<>(this.prs);
    }

    /**
     * Set da variavel prs do objeto
     * @param prs       Map de objetos PrdFil
     */
    public void setPrs(Map<String, PrdFil> prs) {
        this.prs = prs;
    }

    /**
     * Get do tamanho da variavel prs do objeto
     * @return int com tamanho
     */

    public int getSizePrs(){
        return this.prs.size();
    }

    /**
     * Método que devolve o total pago em compras
     * @return  double com total gasto
     */
    public double getTotalPago(){
        double res=0;
        for(PrdFil p:this.prs.values()){
            res+=p.getTotalPago();
        }
        return res;
    }

    /**
     * Método que devolve o total pago num produto
     * @param prod      codigo de produto
     * @return double com total gasto
     */
    public double getPago1Prod(String prod){
        double r = this.prs.get(prod).getgN() + this.prs.get(prod).getgP();
        return r;
    }

    /**
     * Método que devolve a quantidade comprada de um produto
     * @param prod      codigo de produto
     * @return double com quantidade
     */
    public double getProdQnt(String prod){
        double r = this.prs.get(prod).getqN() + this.prs.get(prod).getqP();
        return r;

    }

    /**
     * Get da Querie 5
     * @param res       Map onde é guardada a informação
     */

    public void getQuerie5(Map<String, Integer> res){
        for(PrdFil p: this.prs.values()){
            if (!res.containsKey(p.getPrd())) res.put(p.getPrd(),p.getTotalComprado());
            else res.put(p.getPrd(),res.get(p.getPrd()) + p.getTotalComprado());
        }
    }

    /**
     * Get da Querie 6
     * @param res       Map onde é guardada a informação
     */
    public void getQuerie6(Map<String,int[]> res){

        for(PrdFil p: this.prs.values()){
            if (!res.containsKey(p.getPrd())) res.put(p.getPrd(),new int[2]);

            res.get(p.getPrd())[0] += p.getqN() + p.getqP();
            res.get(p.getPrd())[1]++;
        }
    }

    /**
     * Método que devolve um set de produtos
     * @param aux       Set a ser preenchido com produtos
     */

    public void getSetProds(Set<String> aux){
        aux.addAll(this.prs.keySet());
    }
}