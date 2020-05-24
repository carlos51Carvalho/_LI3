
package Model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class ProdFat implements Serializable {

    private String prod;
    private boolean used;
    private Map<Integer, FilFat> fil;

    /**
     * Construtor sem parametros
     */
    public ProdFat(){
        this.prod = "";
        this.used =false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFat());
    }

    /**
     * Construtor so com parametro prod
     * @param p     codigo de produto
     */
    public ProdFat(String p){
        this.prod = p;
        this.used =false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFat());
    }

    /**
     * Get da variavel prod do objeto
     * @return codigo de produto
     */
    public String getProd() {
        return this.prod;
    }

    /**
     * Set da variavel prod do objeto
     * @param ID        codigo de produto
     */
    public void setProd(String ID) {
        this.prod = ID;
    }

    /**
     * Get da variavel used do objeto
     * @return boolean
     */
    public boolean getUsed() {
        return used;
    }

    /**
     * Set da variavel used do objeto
     * @param used      boolean
     * @return objeto
     */
    public ProdFat setUsed(boolean used) {
        this.used = used;
        return this;
    }

    /**
     * Método que incrementa a variavel used numa filial
     * @param f     int com filial
     */
    public void incFilialUsed(int f){
        this.fil.get(f).incUsed();
    }

    /**
     * Método que adiciona uma faturação em N á existente
     * @param f     int com filial
     * @param m     int com mes
     * @param p     double com faturação
     */
    public void addFN(int f,int m, double p){
        this.fil.get(f).addFN(m,p);
    }
    /**
     * Método que adiciona uma faturação em P á existente
     * @param f     int com filial
     * @param m     int com mes
     * @param p     double com faturação
     */

    public void addFP(int f,int m, double p){
        this.fil.get(f).addFP(m,p);
    }

    /**
     * Método que incrementa o total de vendas em N
     * @param f     int com filial
     * @param m     int com mes
     */
     public void incVN(int f,int m){
        this.fil.get(f).incVN(m);
    }
    /**
     * Método que incrementa o total de vendas em P
     * @param f     int com filial
     * @param m     int com mes
     */
     public void incVP(int f,int m){
        this.fil.get(f).incVP(m);
    }
/*
    public Map<Integer, FilFaor eu vou int> getPrd(){
        Map<Integer, Model.FilFat> p = new HashMap<>();
        Set <Map.Entry<Integer, Model.FilFat>> aux = prod.entrySet();
        for ( Map.Entry<Integer,Model.FilFat> aux2: aux )
            p.put(aux2.getKey(),aux2.getValue());

        return p;
    }
*/

    /**
     * Get da variavel fil do objeto
     * @return Map de objetos FilFat
     */
    public Map<Integer, FilFat> getFil(){
        return this.fil;
    }


    /**
     * Get da faturação em N numa filial e num mês
     * @param f     int com filial
     * @param m     int com mes
     * @return double com faturação em N
     */
    public double getFatN(int f, int m){
        return fil.get(f).getFilF().get(m).getfN();
    }
    /**
     * Get da faturação em P numa filial e num mês
     * @param f     int com filial
     * @param m     int com mes
     * @return double com faturação em P
     */

    public double getFatP(int f, int m){
        return fil.get(f).getFilF().get(m).getfP();
    }
    /**
     * Get das vendas em P numa filial e num mês
     * @param f     int com filial
     * @param m     int com mes
     * @return double com vendas em P
     */
    public double getVenP(int f, int m){
        return fil.get(f).getFilF().get(m).getvP();
    }
    /**
     * Get da vendas em N numa filial e num mês
     * @param f     int com filial
     * @param m     int com mes
     * @return double com vendas em N
     */
    public double getVenN(int f, int m){
        return fil.get(f).getFilF().get(m).getvN();
    }

    /**
     * Método que calcula a faturação total por mes e filial
     * @param res       Map a preencher
     */

    public void getFatPorMesEFil(Map<Integer,Map<Integer,Map<String,Double>>> res){
        //this.prod;
        for(Map.Entry<Integer, FilFat> f : this.fil.entrySet()){
            for (int i=1;i<13;i++){
                if(!res.get(i).containsKey(f.getKey())) res.get(i).put(f.getKey(),new TreeMap<>());
                res.get(i).get(f.getKey()).put(this.prod,f.getValue().getFatPorMes(i));


            }
        }
    }
}