package Model;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Classe responsavel por guardar a informação relativa a leitura de um ficheiro de vendas
 */
public class Vendas implements Serializable {

    private String nome;
    private int errados;
    private int tprod;
    private int dprod;
    private int pnc;
    private int tcl;
    private int tclc;
    private int tclsc;
    private int tvendaszero;
    private double fattotal;
    private  int[] res;
    private Map<Integer,double[] > res2;
    private Map<Integer, int[]> res3 ;

    /**
     * Construtor sem parâmetros
     */
    public Vendas() {
        this.nome = new String();
        this.errados = 0;
        this.tprod = 0;
        this.dprod = 0;
        this.pnc = 0;
        this.tcl = 0;
        this.tclc = 0;
        this.tclsc = 0;
        this.tvendaszero = 0;
        this.fattotal = 0.0;
        this.res = new int[12];
        this.res2 = new TreeMap<>();
        this.res3 = new TreeMap<>();
    }

    /**
     * Construtor parametrizado
     * @param nome        String com o nome do ficheiro
     * @param errados     int com as vendas erradas
     * @param tprod       int com o total de produtos
     * @param dprod       int com o total de diferentes produtos comprados
     * @param pnc         int com o total de produtos nunca comprados
     * @param tcl         int com o total de clientes
     * @param tclc        int com o total de diferentes clientes que fizeram compras
     * @param tclsc       int com o total de clientes que nunca compraram
     * @param tvendaszero int com o total de vendas a zero
     * @param fattotal    double com a faturação total
     * @param res         Array com o total de compras por mês
     * @param res2        Map com a faturação total por mês
     * @param res3        Map com om numero de diferentes clientes que compraram por mês
     */
    public Vendas(String nome, int errados, int tprod, int dprod, int pnc, int tcl, int tclc, int tclsc, int tvendaszero, double fattotal, int[] res, Map<Integer, double[]> res2, Map<Integer, int[]> res3) {
        this.nome = nome;
        this.errados = errados;
        this.tprod = tprod;
        this.dprod = dprod;
        this.pnc = pnc;
        this.tcl = tcl;
        this.tclc = tclc;
        this.tclsc = tclsc;
        this.tvendaszero = tvendaszero;
        this.fattotal = fattotal;
        this.res = res;
        this.res2 = res2;
        this.res3 = res3;
    }

    /**
     * Método que calcula a chave para um produto
     * @param p     codigo de produto
     * @return  int com a chave
     */
    public static int hashp(String p) {
        return p.charAt(0) - 'A';
    }

    /**
     * Método que calcula a chave para um cliente
     * @param p     codigo de cliente
     * @return int com a chave
     */
    public static int hashc(String p) {
        return p.charAt(0) - 'A';
    }

    /**
     * Método que verifica se uma venda é válida
     * @param s         Arry com os parâmetros de uma venda
     * @param clientes  Set de clientes
     * @param produtos  Set de produtos
     * @return boolean de verificaçao
     */

    public static boolean validate(String[] s, TreeSet<String> clientes, TreeSet<String> produtos,int nfiliais){
        return s.length == 7
                && produtos.contains(s[0])
                && Double.parseDouble(s[1]) >= 0.0
                && Integer.parseInt(s[2]) >=0
                && (s[3].length()==1) && (s[3].equals("P") || s[3].equals("N"))
                && clientes.contains(s[4])
                && Integer.parseInt(s[5]) >= 0 && Integer.parseInt(s[5]) <= 12
                && Integer.parseInt(s[6]) >= 0 && Integer.parseInt(s[6]) <= nfiliais;
    }

    /**
     * Método que lê um ficheiro de vendas e preenche a faturação e filiais com a informação
     * @param fat       Faturação
     * @param fil       Filiais
     * @param c         Set de clientes
     * @param p         set de produtos
     * @param filepath  caminho ate ao ficheiro a ler
     * @return int com quantas vendas lidas
     * @throws IOException exceção quando dá erro na abertura/leitura do ficheiro
     */
    public int ler_vendas(InterfaceFaturacao fat, InterfaceFiliais fil, TreeSet<String> c, TreeSet<String> p, String filepath,int nfiliais) throws IOException
    {
    int i = 0;
    int t = 0;
    int zero = 0;
    double ft = 0;
    int key ;

    TreeSet<String> pt = new TreeSet<>(p);
    TreeSet<String> ct = new TreeSet<>(c);

        File file = new File(filepath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            String[] st = line.split(" ");
            t++;
            if (validate(st,c,p,nfiliais)) {
                fat.acrescentaFat(st);
                fil.acrescentaFil(st);
                i++;
                int pk = hashp(st[0]);
                int ck = hashc(st[0]);
                if(Double.parseDouble(st[1]) == 0.0) zero++;
                pt.remove(st[0]);
                ct.remove(st[4]);

                ft += Double.parseDouble(st[1]) * Integer.parseInt(st[2]);

                // querie 1.2.1
                res[Integer.parseInt(st[5])-1]++;

                //querie 1.2.2
                key = Integer.parseInt(st[5]);
                if (!res2.containsKey(key)){
                    res2.put(key,new double[4]); }

                res2.get(key) [Integer.parseInt(st[6])] += Double.parseDouble(st[1]) * Integer.parseInt(st[2]);

            }
        }
        String[] parsepath = filepath.split("/");
        setNome(parsepath[parsepath.length-1]);

        setErrados(t-i);
        setTprod(p.size());
        setPnc(pt.size());
        setDprod(getTprod()- getPnc());
        setTcl(c.size());
        setTclsc(ct.size());
        setTclc(getTcl()-getTclsc());
        setTvendaszero( zero);
        setFattotal(ft);

        // querie 1.2.2 inicialização da faturaçao global
        if (!res2.containsKey(0)){
            //double[] a = new Double[4];
            res2.put(0, new double[4] );
            //res2.get(0)[0] =fattotal;
        }
        res2.get(0)[0] += fattotal;

        // querie 1.2.3
        numeroClientesByFil(fil);

    return i;

    }

    /**
     * Método que conta quantos clientes compraram  uma filial
     * @param fil   Filiais
     */
    public void numeroClientesByFil(InterfaceFiliais fil) {
            this.res3 = fil.getUsedFilialMes(res3);
    }

    /**
     * Get da variavel nome do objeto
     * @return String com nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set da variavel nome do objeto
     * @param nome      String com nome do ficheiro
     * @return objeto
     */
    public Vendas setNome(String nome) {
        this.nome = nome;
        return this;
    }

    /**
     * Get da variavel Errados do objeto
     * @return int com vendas erradas
     */
    public int getErrados() {
        return errados;
    }

    /**
     * Set da variavel errados do objeto
     * @param errados      int com vendas erradas
     * @return objeto
     */
    public Vendas setErrados(int errados) {
        this.errados = errados;
        return this;
    }
    /**
     * Get da variavel tprod do objeto
     * @return int com o total de produtos
     */
    public int getTprod() {
        return tprod;
    }
    /**
     * Set da variavel tprod do objeto
     * @param tprod      int com total de produtos
     * @return objeto
     */

    public Vendas setTprod(int tprod) {
        this.tprod = tprod;
        return this;
    }

    /**
     * Get da variavel dprod do objeto
     * @return int com total de diferentes produtos comprados
     */
    public int getDprod() {
        return dprod;
    }

    /**
     * Set da variavel dprod do objeto
     * @param dprod      int com diferentes produtos comprados
     * @return objeto
     */
    public Vendas setDprod(int dprod) {
        this.dprod = dprod;
        return this;
    }

    /**
     * Get da variavel pnc do objeto
     * @return int com total de produtos nunca comprados
     */
    public int getPnc() {
        return pnc;
    }
    /**
     * Set da variavel pnc do objeto
     * @param pnc      int com produtos nunca comprados
     * @return objeto
     */

    public Vendas setPnc(int pnc) {
        this.pnc = pnc;
        return this;
    }

    /**
     * Get da variavel tcl do objeto
     * @return int com o total de clientes
     */
    public int getTcl() {
        return tcl;
    }

    /**
     * Set da variavel tcl do objeto
     * @param tcl      int com total de clientes
     * @return objeto
     */
    public Vendas setTcl(int tcl) {
        this.tcl = tcl;
        return this;
    }

    /**
     * Get da variavel tclc do objeto
     * @return int com o total de diferentes clientes que fizeram compras
     */
    public int getTclc() {
        return tclc;
    }

    /**
     * Set da variavel tclc do objeto
     * @param tclc      int com diferentes clientes que fizeram compras
     * @return objeto
     */

    public Vendas setTclc(int tclc) {
        this.tclc = tclc;
        return this;
    }

    /**
     * Get da variavel tclsc do objeto
     * @return int com o total de clientes que nunca fizeram compras
     */
    public int getTclsc() {
        return tclsc;
    }
    /**
     * Set da variavel tclsc do objeto
     * @param tclsc      int de clientes que nao fizeram compras
     * @return objeto
     */

    public Vendas setTclsc(int tclsc) {
        this.tclsc = tclsc;
        return this;
    }

    /**
     * Get da variavel tvendaszero do objeto
     * @return int com o total de vendas a zero
     */
    public int getTvendaszero() {
        return tvendaszero;
    }
    /**
     * Set da variavel tvendaszero do objeto
     * @param tvendaszero      int com vendas a zero
     * @return objeto
     */

    public Vendas setTvendaszero(int tvendaszero) {
        this.tvendaszero = tvendaszero;
        return this;
    }

    /**
     * Get da variavel fattotal do objeto
     * @return double com a faturação total
     */
    public double getFattotal() {
        return fattotal;
    }

    /**
     * Set da variavel fattotal do objeto
     * @param fattotal      double com a faturação total
     * @return objeto
     */
    public Vendas setFattotal(double fattotal) {
        this.fattotal = fattotal;
        return this;
    }

    /**
     * Get da variavel res do objeto
     * @return Array com total de compras por mês
     */
    public int[] getRes() {
        return res;
    }
    /**
     * Set da variavel res do objeto
     * @param res      Array de int com numero total de compras por mês
     * @return objeto
     */
    public Vendas setRes(int[] res) {
        this.res = res;
        return this;
    }

    /**
     * Get da variavel res2 do objeto
     * @return Map com a faturação total por mês e filial
     */
    public Map<Integer,double[]> getRes2(){
        Map<Integer,double[]> aux = new TreeMap<>();
        for(Map.Entry<Integer,double[]> r: res2.entrySet()){
            aux.put(r.getKey(),r.getValue());
        }
        return aux;
    }


    /**
     * Get da variavel res3 do objeto
     * @return Map com total de clientes diferentes que compraram por mes e filial
     */

    public Map<Integer,int[]> getRes3(){
        Map<Integer,int[]> aux = new TreeMap<>();
        for(Map.Entry<Integer,int[]> r: res3.entrySet()){
            aux.put(r.getKey(),r.getValue());
        }
        return aux;
    }

}