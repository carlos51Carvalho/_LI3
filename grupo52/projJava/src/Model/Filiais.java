package Model;

import java.io.Serializable;
import java.util.*;

public class Filiais implements InterfaceFiliais, Serializable {
    private Map<Integer, List<ClFil>> filiais;

    /**
     * Construtor sem parametros
     */

    public Filiais() {
        this.filiais = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.filiais.put(i, new ArrayList<>());
    }

    /**
     * Método que calcula a chave de um cliente
     * @param p     codigo de pcliente
     * @return chave do cliente
     */
    public int hashProd(String p) {
        return p.charAt(0) - 'A';
    }

    /**
     * Método que adiciona um cliente á variavel filiais
     * @param cl        codigo de cliente
     */
    public void addCl(String cl,int nfilias) {
        if(pBinaria(cl,hashProd(cl))==-1) {
            ClFil cls = new ClFil(cl,nfilias);
            filiais.get(hashProd(cl)).add(cls);
        }
    }

    /**
     * Método que adiciona um Set de clientes a variavel filiais
     * @param clientes      Set de codigos de cliente
     */
    public void addCls(Set<String> clientes,int nfilias) {
        for (String cl : clientes)
            addCl(cl,nfilias);
    }

//    public List<ClFil> getArr(int i){
//        return filiais.get(i);
//    }


    /**
     * Método que faz uma procura binaria numa lista
     * @param p     codigo de cliente
     * @param k     int com chave
     * @return posicao do cliente no array
     */
    public int pBinaria(String p, int k){
        List<ClFil> f = filiais.get(k);
        int r = -1;
        int meio;
        int inicio = 0;
        int fim = f.size()-1;
        while (inicio <= fim && r==-1) {
            meio = (inicio + fim)/2;
            if (p.compareTo(f.get(meio).getCl()) == 0) {
                r = meio;
            }
            if (p.compareTo(f.get(meio).getCl()) < 0)
                fim = meio - 1;
            else
                inicio = meio + 1;
        }
        return r;
    }

    /**
     * Método que verifica se um cliente comprou um produto
     * @param cl        codigo de cliente
     * @param ip        int com posição na lista
     * @param f         int com filial
     * @param m         int com mês
     * @param p         codigo de produto
     * @return          boolean
     */
    public boolean existeProd(String cl, int ip, int f, int m, String p){
        return this.filiais.get(hashProd(cl)).get(ip).existeProd(f,m,p);
    }

    /**
     * Método que acrescenta a informação de uma venda ás filiais
     * @param cl        String com parametros de uma venda
     */

    public void acrescentaFil(String[] cl) {

        int ip = pBinaria(cl[4], hashProd(cl[4]));
        if (ip >= 0) {
            this.filiais.get(hashProd(cl[4])).get(ip).setUsed(true);
            String p = cl[0];
            double pr = Double.parseDouble(cl[1]);
            int q = Integer.parseInt(cl[2]);
            int m = Integer.parseInt(cl[5]);
            int f = Integer.parseInt(cl[6]);
            int key = hashProd(cl[4]);

            this.filiais.get(key).get(ip).setFilialUsada(f,1);
            this.filiais.get(key).get(ip).incnVendasMes(f,m);
            this.filiais.get(key).get(ip).setUsedFilMes(f,m,true);

            if (!existeProd(cl[4], ip, f, m, p)) this.filiais.get(key).get(ip).addPrs(f,m,p);

            //System.out.println(cl[3] + cl[3].equals("N"));
            if (cl[3].equals("N")) {
                this.filiais.get(key).get(ip).addgN(f,m,p,pr * q);
                this.filiais.get(key).get(ip).addqN(f,m,p,q);

            }else{
                this.filiais.get(key).get(ip).addgP(f,m,p,pr * q);
                this.filiais.get(key).get(ip).addqP(f,m,p,q);
            }

        }
    }

    /**
     * Método que conta quantos clientes fizera compras
     * @return int com total de compradores
     */

        public int numeroComparadores(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += (int) filiais.get(i).stream().filter(ClFil::getUsed).count();
        }
        return count;
    }

    /**
     * Método que calcula quantos clientes nunca fizeram compras
     * @return int com total de clientes
     */
    public int naocompram(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += filiais.get(i).size();
        }
        return count-numeroComparadores();
    }

    /**
     * Método que verifica se um mês é usado
     * @param key       int com chave
     * @param ip        int com posição na lista
     * @param f         int com filial
     * @param m         int com mês
     * @return  boolean
     */
    public boolean getMesUsed(int key, int ip, int f, int m){
        return filiais.get(key).get(ip).getMesUsed(f,m);
    }

    /**
     * Método que devolve o numero de vendas num mês
     * @param key       int com chave
     * @param ip        int com posição na lista
     * @param f         int com filial
     * @param m         int com mês
     * @return int com total de vendas
     */
    public int getNVendasMes(int key, int ip, int f, int m){
        return filiais.get(key).get(ip).getFil().get(f).getFilF().get(m).getnVendas();
    }


    /**
     * Método que verifica quais meses sao usados
     * @param res       Map a preencher
     * @return Map com utilização de todos os meses
     */
    public Map<Integer, int[]> getUsedFilialMes(Map<Integer, int[]> res){
        for (int i =0; i<26; i++){
            for (ClFil c: filiais.get(i)){
                res = c.getUsedFilialMes(res);
            }
        }
        return res;
    }

    /**
     * Método que devolve o total de vendas por mês e filial
     * @param mes       int com mês
     * @param res       Map a preencher
     * @return Map com total de vendas por mês
     */
    public Map<Integer, int[]> getVendasTotaisFiliaisPorMes(int mes,Map<Integer, int[]> res){
        for (int i =0; i<26; i++){
            for (ClFil c: filiais.get(i)){
                res = c.getVendasTotaisFiliaisPorMes(mes,res);
            }
        }
        return res;
    }


    /**
     * Get para Querie 3
     * @param kc        int com chave
     * @param ip        int com posição na lista
     * @return Map com resposta da querie 3
     */
    public Map<Integer,double[]> getQuerie3(int kc, int ip){
        return filiais.get(kc).get(ip).getQuerie3();
    }

    /**
     * Get para Querie 4
     * @param prod      codigo de produto
     * @return Map com resposta da querie 4
     */
    public Map<Integer,double[]> getQuerie4(String prod) {
        Map<Integer,double[]> res = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            for (ClFil cl : filiais.get(i)) {
                res = cl.getQuerie4(prod,res);
            }
        }
        return res;
    }

    /**
     * Get para Querie 5 (retorna Map com resposta da querie 5 por referencia)
     * @param kc        int com chave
     * @param ip        int com posição na lista
     * @param q5        Map a preencher
     */
    public void getQuerie5(int kc, int ip,Map<String, Integer> q5){
        filiais.get(kc).get(ip).getQuerie5(q5);
    }


    /**
     * Get para Querie 6
     * @return Map com resposta da querie 6
     */

    public Map<String,int[]> getQuerie6() {
        Map<String,int[]> res = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            for (ClFil c : filiais.get(i)) {
                c.getQuerie6(res);
            }
        }
        return res;
    }

    /**
     * Get para Querie 7
     * @return Map com resposta da querie 7
     */
    public Map<Integer, Map<String, Double>> getQuerie7() {
        Map<Integer, Map<String, Double>> res = new TreeMap<>();

        for (int i = 0; i < 26; i++) {
            for (ClFil c : filiais.get(i)) {
                c.getQuerie7(res, c.getCl());
                //Avaliar se é maior e inserir

            }
        }
        return res;
    }
    /**
     * Get para Querie 8
     * @return Map com resposta da querie 8
     */

    public Map<String, Integer> getQuerie8(){
        Map<String, Integer> res = new TreeMap<>();

        for (int i = 0; i < 26; i++) {
            for (ClFil c : filiais.get(i)) {
                res.put(c.getCl(),c.getQuerie8());
            }
        }
        return res;
    }

    /**
     * Get para Querie 9
     * @param prod      codigo de produto
     * @return Map com resposta da querie 9
     */
    public Map<String,Double> getQuerie9(String prod) {
        Map<String,Double> res = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            for (ClFil cl : filiais.get(i)) {
                String c = cl.getCl();
                res = cl.getQuerie9(prod,res,c);
            }
        }
        return res;
    }
}