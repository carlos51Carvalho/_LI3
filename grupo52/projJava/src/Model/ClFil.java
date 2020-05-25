
package Model;

import java.io.Serializable;
import java.util.*;

public class ClFil implements Serializable {
    private String cl;
    private boolean used;
    private Map<Integer, FilFil> fil;

    /**
     * Construtor sem parâmetros
     */
    public ClFil(){
        this.cl = "";
        this.used = false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFil());
    }

    /**
     * Construtor paramtrizado
     * @param p     codigo de cliente
     */
    public ClFil(String p,int nfilias){
        this.cl = p;
        this.used = false;
        this.fil = new HashMap<>();
        for (int i = 1; i<=nfilias ; i++)
            this.fil.put(i, new FilFil());
    }

    /**
     * Get da variavel cl do objeto
     * @return String com o codigo de cliente
     */
    public String getCl() {
        return this.cl;
    }

    /**
     * Set d variavel cl do objeto
     * @param ID        String com codigo de cliente
     */
    public void setCl(String ID) {
        this.cl = ID;
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
     * @param used boolean
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Método que altera a utilização de uma filial
     * @param f     int com filial
     * @param v     int 0 ou 1
     */
    public void setFilialUsada(int f,int v){
        this.fil.get(f).setUsed(v);
    }

    /**
     * étodo que verifica se um clientes comprou um produto
     * @param f     int com a filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @return boolean
     */
    public boolean existeProd(int f, int m,String p){
        return this.fil.get(f).existeProd(m,p);
    }

    /**
     * Método que adiciona um gasto em modo 'N' á filial
     * @param f     int com  filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     double com valor a adicionar
     */

    public void addgN(int f,int m, String p, double t){
        this.fil.get(f).addgN(m,p,t);
    }
    /**
     * Método que adiciona uma quantidade em modo 'N' á filial
     * @param f     int com  filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     int com valor a adicionar
     */
    public void addqN(int f,int m, String p, int t){
        this.fil.get(f).addqN(m,p,t);
    }
    /**
     * Método que adiciona um gasto em modo 'P' á filial
     * @param f     int com  filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     double com valor a adicionar
     */
    public void addgP(int f,int m, String p, double t){
        this.fil.get(f).addgP(m,p,t);
    }
    /**
     * Método que adiciona uma quantidade em modo 'P' á filial
     * @param f     int com  filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     int com valor a adicionar
     */
    public void addqP(int f,int m, String p, int t){
        this.fil.get(f).addqP(m,p,t);
    }
    /**
     * Método que adiciona um produto á filial
     * @param f     int com  filial
     * @param m     int com o mês
     * @param p     codigo de produto
     */
    public void addPrs(int f, int m, String p){
        this.fil.get(f).addPrs(m,p);
    }

    /**
     * Método que incremente o total de vendas num mês
     * @param f     int com filial
     * @param m     int com mês
     */
    public void incnVendasMes(int f, int m){
        this.fil.get(f).incnVendasMes(m);
    }

    /**
     * Método que altera se um mẽs é usado ou não
     * @param f     int com filial
     * @param m     int com mês
     * @param b     boolean
     */
    public void setUsedFilMes(int f,int m,boolean b){
        this.fil.get(f).setUsedMes(m,b);
    }
    /*
    public Map<Integer, Model.FilFat> getPrd(){
        Map<Integer, Model.FilFat> p = new HashMap<>();
        Set <Map.Entry<Integer, Model.FilFat>> aux = prod.entrySet();
        for ( Map.Entry<Integer,Model.FilFat> aux2: aux )
            p.put(aux2.getKey(),aux2.getValue());

        return p;
    }
*/

    /**
     * Get da variavel fil do objeto
     * @return  Map com objeto FilFil
     */
    public Map<Integer, FilFil> getFil(){
        return this.fil;
    }

    /**
     * Método que devolve a utilização de um mẽs
     * @param f     int com filial
     * @param m     int com mês
     * @return  boolean
     */
    public boolean getMesUsed( int f, int m){
        return fil.get(f).getMesUsed(m);
    }

    /**Método que verifica filial a filial em quais meses sao efetuadas compras
     * @param res       Map onde vai ser guardada a informação
     * @return Map com o resultado
     * */
    public Map<Integer, int[]> getUsedFilialMes(Map<Integer, int[]> res){
        for (Map.Entry<Integer, FilFil> f: this.fil.entrySet() ){

            if (!res.containsKey(f.getKey())) res.put(f.getKey(),new int[13]);

            res.put(f.getKey(),f.getValue().getUsedByMes(res.get(f.getKey())));
        }
        return res;
    }

    /**
     * Método que devolve o total de vendas num mês
     * @param f     int com filial
     * @param m     int com mês
     * @return int com total de vendas
     */

    public int getNVendasMes( int f, int m){
        return fil.get(f).getFilF().get(m).getnVendas();
    }

    /**
     * Método que conta o total de vendas efetuadas num mês
     * @param mes       mês a verificar
     * @param res       Map onde vai ser guardada a informação
     * @return Map com a informação
     */
    public Map<Integer, int[]> getVendasTotaisFiliaisPorMes(int mes,Map<Integer, int[]> res){
        int aux;
        boolean alterado=false;
        int [] res0 = new int[2];
        if (!res.containsKey(0))res.put(0,new int[2]);

        for (Map.Entry<Integer, FilFil> f: this.fil.entrySet() ){
            if (!res.containsKey(f.getKey()))res.put(f.getKey(),new int[2]);

            aux = f.getValue().getVendasPorMes(mes);

            if(aux!=0){
                if (!alterado){
                    alterado=true;
                    res.get(0)[0]++;
                }
                res.get(f.getKey())[0]++;
                res.get(f.getKey())[1]+=aux;
                res.get(0)[1]+=aux;
            }

        }
        return res;
    }

    /**
     * Get para a Querie 3
     * @return Map com resposta á querie 3
     */
    public Map<Integer,double[]> getQuerie3(){
        Map<Integer,double[]> res = new TreeMap<>();
        for (FilFil f: this.fil.values() ){
            f.getQuerie3(res);
        }
        return res;
    }

    /**
     * Get para a Querie 4
     * @param prod      codigo de produto
     * @param res       Map onde vai ser guardada a informação
     * @return Map com resposta á querie 4
     */
    public Map<Integer,double[]> getQuerie4(String prod, Map<Integer,double[]> res){
        for (FilFil f: this.fil.values() ){
            f.getQuerie4(prod,res);
        }
        return res;
    }

    /**
     * Get para a Querie 5 (retorna  Map com resposta á querie 5 por referencia)
     * @param q5        Map onde vai ser guardada a informação
     */
    public void getQuerie5(Map<String, Integer> q5){
        for (FilFil f: this.fil.values() ) {
            if (f.getUsed() == 1) f.getQuerie5(q5);
        }
    }
    /**
     * Get para a Querie 6 (retorna Map com resposta á querie 6 por referencia)
     * @param res       Map onde vai ser guardada a informação
     */
    public void getQuerie6(Map<String,int[]> res){
        for (FilFil f: this.fil.values() )
            f.getQuerie6(res);
    }
    /**
     * Get para a Querie 7 (retorna Map com resposta á querie 7 por referencia)
     * @param res       Map onde vai ser guardada a informação
     * @param c         String com codigo de cliente
     */

    public void getQuerie7(Map<Integer, Map<String, Double>> res, String c ) {
        double med;
        String d = null;
        double aux;
        for (Map.Entry<Integer, FilFil> f : this.fil.entrySet()) {
            if (f.getValue().getUsed() == 1) {
                med = f.getValue().getQuerie7();

                if (!res.containsKey(f.getKey())) {
                    res.put(f.getKey(), new TreeMap<>());
                    //res.get(f.getKey()).put(c,med);
                }
                if (res.get(f.getKey()).size() < 3) {
                    res.get(f.getKey()).put(c, med);
                } else {
                    aux = med;
                    for (Map.Entry<String, Double> cs : res.get(f.getKey()).entrySet()) {
                        if (aux > cs.getValue()) {
                            d = cs.getKey();
                            aux = cs.getValue();
                        }
                    }
                    if (aux != med) {
                        res.get(f.getKey()).remove(d);
                        res.get(f.getKey()).put(c, med);
                    }
                }
            }
        }
    }

    /**
     * Get para a Querie 8
     * @return int com resposta á querie 8
     */
    public int getQuerie8(){
        Set<String> aux = new HashSet<>();
        for (FilFil f: this.fil.values() ) {
            if (f.getUsed() == 1) f.getSetProds(aux);
        }

        return aux.size();
    }

    /**
     * Get para a Querie 9
     * @param prod      codigo de produto
     * @param res       Map onde vai ser guardada a informação
     * @param c         codigo de cliente
     * @return Map com resposta á querie 9
     */
    public Map<String,Double> getQuerie9(String prod, Map<String,Double> res, String c){
        for (FilFil f: this.fil.values() ){
            f.getQuerie9(prod,res,c);
        }
        return res;
    }
}
