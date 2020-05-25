package Model;



import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Queries implements Serializable{

    private InterfaceClientes clientes;
    private InterfaceProdutos produtos;
    private InterfaceFaturacao fat;
    private InterfaceFiliais fil;
    private Vendas vendas;

    public Queries(){
        this.clientes = new Clientes3();
        this.produtos = new Produtos3();
        this.fat = new Faturacao();
        this.fil = new Filiais();
        this.vendas = new Vendas();
    }
    public Queries(InterfaceClientes cl, InterfaceProdutos pr, InterfaceFaturacao fat, InterfaceFiliais fil, Vendas v){
        this.clientes = cl;
        this.produtos = pr;
        this.fat = fat;
        this.fil = fil;
        this.vendas = v;

    }

    public void reiniciarModelo(){//mudar para apenas limpar as estruturas?
        this.clientes = new Clientes3();
        this.produtos = new Produtos3();
        this.fat = new Faturacao();
        this.fil = new Filiais();
        this.vendas = new Vendas();
    }

    public void leituras(String fc, String fp, String fv,int nfiliais) throws Exception {
        //Crono.start();
        clientes.ler_clientes(fc);
        produtos.ler_produtos(fp);
        fat.addProds(produtos.getSetDeProdutos(),nfiliais);
        fil.addCls(clientes.getSetDeClientes(),nfiliais);
        vendas.ler_vendas(fat, fil, clientes.getSetDeClientes(), produtos.getSetDeProdutos(), fv,nfiliais);
        //System.out.println(Crono.getTImeString());
    }

    public static boolean validaNum(String p) {
        boolean r = true;
        for (int i = 2; i < 6 && r; i++) {
            if (i == 2) {
                if (p.charAt(i) < '1' && p.charAt(i) > '9') r = false;
                else {
                    if (p.charAt(i) < '0' && p.charAt(i) > '9') r = false;
                }
            }
        }
        return r;
    }

    public static boolean validaCliente(String c){
        return c.charAt(0) >= 'A' && c.charAt(0) <= 'Z' && validaNum(c);
    }

    public static boolean validaProduto(String c) {
        return c.charAt(0) >= 'A' && c.charAt(0) <= 'Z' && c.charAt(1) >= 'A' && c.charAt(1) <= 'Z' && validaNum(c);
    }

    public static boolean mesvalido(int mes){
        return mes >= 1 && mes <= 12;
    }

    public static int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }


    //========================================estaticas============================================================

    public String getNome(){
        return vendas.getNome();
    }
    public int getErrados(){
        return vendas.getErrados();
    }
    public int getTprod(){
        return vendas.getTprod();
    }
    public int getDprod(){
        return vendas.getDprod();
    }
    public int getPnc(){
        return vendas.getPnc();
    }
    public int getTcl(){
        return vendas.getTcl();
    }
    public int getTclc(){
        return vendas.getTclc();
    }
    public int getTclsc(){
        return vendas.getTclsc();
    }
    public int getTvendaszero(){
        return vendas.getTvendaszero();
    }
    public int getFattotal(){
        return vendas.getTvendaszero();
    }
    public int[] getRes(){
        return vendas.getRes();
    }
    public Map<Integer,double[]> getRes2(){
        return vendas.getRes2();
    }
    public Map<Integer,int[]> getRes3(){
        return vendas.getRes3();
    }


    //====================================== interativas ===========================================================

    // querie 1
    public List<String> querie1(){
        Crono.start();
        List<String> res = this.fat.getQuerie1();
        Crono.getTImeString();
        return res;
    }


// querie 2
    //int[0] nclientes
    //int[1] nvendas
    public Map<Integer,int[]> querie2(int mes) throws ValorInvalidoException{

        Crono.start();
        Map<Integer,int[]> res = new TreeMap<>();
        if (mesvalido(mes)) {
            res=this.fil.getVendasTotaisFiliaisPorMes(mes,res);
        }
        else throw new ValorInvalidoException("Mês não válido!");

        System.out.println(Crono.getTImeString());
        return res;
    }



// querie 3
    //int[0] nvendas
    //int[1] n produtos diferentes
    //int[2] faturado


    public  Map<Integer,double[]> querie3(String cliente) throws ValorInvalidoException {
        Crono.start();
        Map<Integer,double[]> res;

        if(validaCliente(cliente)){
            int kc = hashCL(cliente);
            int ip = this.fil.pBinaria(cliente, kc);

            if(ip!=-1){
                res = this.fil.getQuerie3(kc,ip);
            }
            else throw  new ValorInvalidoException("Cliente não existente");
        }else throw new ValorInvalidoException("Cliente não válido");
        System.out.println( Crono.getTImeString());
        return res;
    }


    // querie 4
        //quantidade
        //n clientes
        //gasto

    public Map<Integer,double[]> querie4(String prod) throws ValorInvalidoException {
        Crono.start();
        Map<Integer,double[]> res;

        if(validaProduto(prod) && this.fat.pBinaria(prod,hashCL(prod)) !=-1) res = this.fil.getQuerie4(prod);
        else throw new ValorInvalidoException("Produto não válido");

        System.out.println(Crono.getTImeString());
        return res;

    }


    //querie 5
    public TreeSet<Map.Entry<String, Integer>> querie5(String c) throws ValorInvalidoException {
        Crono.start();
        //Comparator<Map.Entry<String,Integer>> cp = new Model.ComparatorQ5();
        Map<String, Integer> q5 = new TreeMap<>();

        if(validaCliente(c)){
            int kc = hashCL(c);
            int ip = this.fil.pBinaria(c, kc);

            if(ip!=-1){
                //Lista de produtos e quantidades
                this.fil.getQuerie5(kc,ip,q5);
            }else throw new ValorInvalidoException("Cliente não existe");
        }
        else throw new ValorInvalidoException("Cliente inválido!");
        //ordenar!

        TreeSet<Map.Entry<String, Integer>> res =  new TreeSet<>(new ComparatorQ5());
        res.addAll(q5.entrySet());

        System.out.println( Crono.getTImeString());
        return res;
    }




    //querie 6

    //int[0] nquantidade vendida;
    //int[1] n clientes;
    public TreeSet<Map.Entry<String, int[]>> querie6(int limite) throws ValorInvalidoException {
        Crono.start();
        Map<String,int[]> res;

        if(limite >0){
            //Lista de produtos, com a quantidade e numero de clientes que o compraram
            res = this.fil.getQuerie6();
        }
        else throw new ValorInvalidoException("Limite inválido");

        //Falta dar apenas o limite

        TreeSet<Map.Entry<String, int[]>> q6 = new TreeSet<>(new ComparatorQ6());
        assert res != null;
        q6.addAll(res.entrySet());

        System.out.println( Crono.getTImeString());
        return q6;
    }



    //querie 7
    public Map<Integer, TreeSet<Map.Entry <String,Double>>> querie7(){
        Crono.start();
        Map<Integer, Map<String, Double>> res;
        res = this.fil.getQuerie7();
        //ordenar

        Map<Integer,TreeSet <Map.Entry<String, Double>>> res2= new TreeMap<>();

        assert res != null;
        for(Map.Entry<Integer,Map<String, Double>> m : res.entrySet()){
            res2.put(m.getKey(),new TreeSet<>(new ComparatorQ9()));
            res2.get(m.getKey()).addAll(m.getValue().entrySet());
        }
        System.out.println( Crono.getTImeString());

        return res2;
    }



    // querie 8
    public TreeSet<Map.Entry<String, Integer>> querie8(int limite) throws ValorInvalidoException {
        Crono.start();
        Map<String, Integer> res;

        if(limite >0) {
            res = this.fil.getQuerie8();
        }else throw new ValorInvalidoException("Limite inválido!");

        //Ordenar
         TreeSet<Map.Entry<String, Integer>> q8 =  new TreeSet<>(new ComparatorQ5());
        assert res != null;
        q8.addAll(res.entrySet());


        System.out.println( Crono.getTImeString());
        return q8;
    }

    // querie 9

    public TreeSet<Map.Entry<String,Double>> querie9(int limite, String prod) throws ValorInvalidoException, NotValideException {
        Crono.start();
        Map<String,Double> res;

        if (limite > 0) {
            if (validaProduto(prod) && this.fat.pBinaria(prod,hashCL(prod)) != -1) res = this.fil.getQuerie9(prod);
            else throw new NotValideException("produto inválido");
        }else throw new ValorInvalidoException("limite inválido");

        TreeSet<Map.Entry<String, Double>> q9 =  new TreeSet<>(new ComparatorQ9());
        assert res != null;
        q9.addAll(res.entrySet());

        System.out.println( Crono.getTImeString());
        return q9;

    }


    // querie 10

    public  Map<Integer,Map<Integer,Map<String,Double>>> querie10(){
        Map<Integer,                    //mes
                Map<Integer,            //fil
                        Map<String,     //Model.Produto
                                Double>>> res = new TreeMap<>();
        for (int i=1;i<13;i++)res.put(i,new TreeMap<>());

        fat.getFatPorMesEFil(res);


        System.out.println( Crono.getTImeString());
        return res;

    }



    // Gravar para ficheiro

    public void gravarObj(String filename) throws IOException {
        Crono.start();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
        o.writeObject(this);
        o.flush();
        o.close();
        System.out.println(Crono.getTImeString());
    }


    //Ler de ficheiros bin

    public Queries lerObj(String filename) throws IOException, ClassNotFoundException {
        Crono.start();
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
        Queries c = (Queries) o.readObject();
        o.close();
        System.out.println(Crono.getTImeString());
        return c;
    }



}

