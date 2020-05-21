package Model;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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

    public static int hashp(String p) {
        return p.charAt(0) - 'A';
    }
    public static int hashc(String p) {
        return p.charAt(0) - 'A';
    }


    public static boolean validate(String[] s, TreeSet<String> clientes, TreeSet<String> produtos){
        return s.length == 7
                && produtos.contains(s[0])
                && Double.parseDouble(s[1]) >= 0.0
                && Integer.parseInt(s[2]) >=0
                && (s[3].length()==1) && (s[3].equals("P") || s[3].equals("N"))
                && clientes.contains(s[4])
                && Integer.parseInt(s[5]) >= 0 && Integer.parseInt(s[5]) <= 12
                && Integer.parseInt(s[6]) >= 0 && Integer.parseInt(s[6]) <= 3;
    }


    public int ler_vendas(InterfaceFaturacao fat, InterfaceFiliais fil, TreeSet<String> c, TreeSet<String> p, String filepath) throws IOException
    {
        Crono.start();
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
            if (validate(st,c,p)) {
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
/*
        // querie 1.2.1
        for (int k = 0; k<12; k++){
            System.out.println(res[k]);
        }

        // querie 1.2.2
        for(Map.Entry<Integer,double[]> e: res2.entrySet()) {
            System.out.println("Mes " + e.getKey() + ": ");
            for (int g = 0; g < 4; g++) {
                System.out.println("Fat" + g + ": " + e.getValue()[g]);
            }
        }

 */
            /*
        // querie 1.2.3
        for (Map.Entry<Integer,int[]> r : res3.entrySet()) {
            System.out.println(r.getKey());
            for (int h= 1; h<13; h++)
                System.out.println(r.getValue()[h]);
        }
*/
     System.out.println("Model.Vendas lidas Lidos \n");
     System.out.println(Crono.getTImeString());
    return i;

    }

    public void numeroClientesByFil(InterfaceFiliais fil) {
            this.res3 = fil.getUsedFilialMes(res3);
    }

    public String getNome() {
        return nome;
    }

    public Vendas setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getErrados() {
        return errados;
    }

    public Vendas setErrados(int errados) {
        this.errados = errados;
        return this;
    }

    public int getTprod() {
        return tprod;
    }

    public Vendas setTprod(int tprod) {
        this.tprod = tprod;
        return this;
    }

    public int getDprod() {
        return dprod;
    }

    public Vendas setDprod(int dprod) {
        this.dprod = dprod;
        return this;
    }

    public int getPnc() {
        return pnc;
    }

    public Vendas setPnc(int pnc) {
        this.pnc = pnc;
        return this;
    }

    public int getTcl() {
        return tcl;
    }

    public Vendas setTcl(int tcl) {
        this.tcl = tcl;
        return this;
    }

    public int getTclc() {
        return tclc;
    }

    public Vendas setTclc(int tclc) {
        this.tclc = tclc;
        return this;
    }

    public int getTclsc() {
        return tclsc;
    }

    public Vendas setTclsc(int tclsc) {
        this.tclsc = tclsc;
        return this;
    }

    public int getTvendaszero() {
        return tvendaszero;
    }

    public Vendas setTvendaszero(int tvendaszero) {
        this.tvendaszero = tvendaszero;
        return this;
    }

    public double getFattotal() {
        return fattotal;
    }

    public Vendas setFattotal(double fattotal) {
        this.fattotal = fattotal;
        return this;
    }

    public int[] getRes() {
        return res;
    }

    public Vendas setRes(int[] res) {
        this.res = res;
        return this;
    }

    public Map<Integer,double[]> getRes2(){
        Map<Integer,double[]> aux = new TreeMap<>();
        for(Map.Entry<Integer,double[]> r: res2.entrySet()){
            aux.put(r.getKey(),r.getValue());
        }
        return aux;
    }



    public Map<Integer,int[]> getRes3(){
        Map<Integer,int[]> aux = new TreeMap<>();
        for(Map.Entry<Integer,int[]> r: res3.entrySet()){
            aux.put(r.getKey(),r.getValue());
        }
        return aux;
    }

}