
package Model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FilFil implements Serializable {
    private int used;
    private Map<Integer, MesFil> filial;

    /**
     * Constutor sem parâmetros
     */
    public FilFil() {
        this.used = 0;
        this.filial = new HashMap<>();
        for (int i = 1; i < 13; i++)
            this.filial.put(i, new MesFil());
    }

    /**
     * Get da variavel used do objeto
     * @return int 0 ou 1
     */

    public int getUsed() {
        return this.used;
    }

    /**
     * Set da variavel used do objeto
     * @param used      int 0 ou 1
     */

    public void setUsed(int used) {
        this.used = used;
    }

    /**
     * Método que incremente a variavel used do objeto
     */
    public void incUsed() {
        this.used += 1;
    }

    /**
     * Método que verifica se um produto existe
     * @param m     int com mês
     * @param p     codigo de produto
     * @return boolean
     */
    public boolean existeProd(int m, String p) {
        return this.filial.get(m).existeProd(p);
    }

    /**
     * Método que incrementa o numero de vendas num mês
     * @param m     int com mês
     */
    public void incnVendasMes(int m) {
        this.filial.get(m).incnVendas();
    }

    /**
     * Método que altera a utilização de um mês
     * @param m     int mẽs
     * @param b     boolean
     */
    public void setUsedMes(int m, boolean b) {
        this.filial.get(m).setUsed(b);
    }

    /**
     * Método que adiciona um gasto em modo 'N' á filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     double com valor a adicionar
     */
    public void addgN(int m, String p, double t) {
        this.filial.get(m).addgN(p, t);
    }
    /**
     * Método que adiciona uma quantidade em modo 'N' á filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     int com valor a adicionar
     */

    public void addqN(int m, String p, int t) {
        this.filial.get(m).addqN(p, t);
    }
    /**
     * Método que adiciona um gasto em modo 'P' á filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     double com valor a adicionar
     */

    public void addgP(int m, String p, double t) {
        this.filial.get(m).addgP(p, t);
    }
    /**
     * Método que adiciona uma quantidade em modo 'P' á filial
     * @param m     int com o mês
     * @param p     codigo de produto
     * @param t     int com valor a adicionar
     */

    public void addqP(int m, String p, int t) {
        this.filial.get(m).addqP(p, t);
    }

    /**
     * Método que adiciona um produto á filial
     * @param m     int com o mês
     * @param p     codigo de produto
     */
    public void addPrs(int m, String p) {
        this.filial.get(m).addPrs(p);
    }

    /**
     * Get da variavel filial do objeto
     * @return
     */
    public Map<Integer, MesFil> getFilF() {
        return this.filial;
    }

    /**
     * Método que verifica se um mẽs é usado
     * @param mes    int mẽs
     * @return boolean
     */
    public boolean getMesUsed(int mes) {
        return this.getFilF().get(mes).isUsed();
    }
    /**Método que verifica em quais meses sao efetuadas compras
     * @param res       Array onde vai ser guardada a informação
     * @return Array com o resultado
     * */

    public int[] getUsedByMes(int[] res) {
        for (int i = 0; i < 12; i++) {
            if (getMesUsed(i+1))
                res[i]++;
        }
        return res;
    }

    /**
     * Método que devolve o numero de vendas realizadas num mês
     * @param mes       int mês
     * @return  int com total de vendas
     */

    public int getVendasPorMes(int mes) {
        return this.filial.get(mes).getnVendas();
    }

    /**
     * Get para a Querie 3
     * @param res       Map onde vai ser guardada a informação
     */

    public void getQuerie3(Map<Integer, double[]> res) {
        for (Map.Entry<Integer, MesFil> m : this.filial.entrySet()) {
            if (!res.containsKey(m.getKey())) res.put(m.getKey(), new double[3]);

            res.get(m.getKey())[0] += (double) m.getValue().getnVendas();
            res.get(m.getKey())[1] += (double) m.getValue().getSizePrs();
            res.get(m.getKey())[2] += m.getValue().getTotalPago();
        }
    }

    /**
     * Get para a Querie 4
     * @param prod      codigo de produto
     * @param res       Map onde vai ser guardada a informação
     */
    public void getQuerie4(String prod, Map<Integer, double[]> res) {
        boolean v = true;
        for (Map.Entry<Integer, MesFil> m : this.filial.entrySet()) {
            if (!res.containsKey(m.getKey())) res.put(m.getKey(), new double[3]);
            if (existeProd(m.getKey(), prod)) {
                res.get(m.getKey())[0] += (double) m.getValue().getProdQnt(prod);
                res.get(m.getKey())[2] += (double) m.getValue().getPago1Prod(prod);
                if (v == true) {
                    res.get(m.getKey())[1]++;
                    v = false;
                }
            }
        }
    }

    /**
     * Get para a Querie 5
     * @param res       Map onde vai ser guardada a informação
     */
    public void getQuerie5(Map<String, Integer> res) {
        for (MesFil m : this.filial.values()) {
            if (m.isUsed()) m.getQuerie5(res);
        }
    }

    /**
     * Get para a Querie 6
     * @param res       Map onde vai set guardada a informação
     */
    public void getQuerie6(Map<String, int[]> res) {
        for (MesFil m : this.filial.values())
            if (m.isUsed()) m.getQuerie6(res);
    }

    /**
     * Get para a Querie 7
     * @return  double com total pago num mês
     */
    public double getQuerie7() {
        double pay = 0.0;
        for (MesFil m : this.filial.values()) {
            if (m.isUsed()) pay += m.getTotalPago();
        }
        return pay;
    }

    /**
     * Método que devolve um Set de produtos
     * @param aux       Set onde vao ser guardados os produtos
     */
    public void getSetProds(Set<String> aux) {
        for (MesFil m : this.filial.values()) {
            if (m.isUsed()) m.getSetProds(aux);
        }
    }


    /**
     * Get para a Querie 9
     * @param prod      codigo de produto
     * @param res       Map onde vai ser guardada a informação
     * @param c         codigo de cliente
     */
    public void getQuerie9(String prod, Map<String, Double> res, String c) {
        boolean v = true;
        for (MesFil m : this.filial.values()) {
            if (!res.containsKey(c)) res.put(c, 0.0);
            if (m.existeProd(prod)) {
                double d = res.get(c) + m.getPago1Prod(prod);
                res.put(c,d);
            }

        }
    }
}
