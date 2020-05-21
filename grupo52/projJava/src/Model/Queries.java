package Model;


import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Queries {


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

    public static  int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }



    // estaticas 1.2

    // estatistica 1.2.1

//    public int[] numeroCompPMes(Model.Filiais fil){
//        int[] res = new int[12];
//         for (int i = 0; i < 26; i++) {
//             for (Model.ClFil c : fil.getArr(i)) {
//                 for (int j = 1; j < 4; j++) {
//                     for (int m = 1; m < 13; m++) {
//                         res[m - 1] += c.getNVendasMes(j, m);
//                     }
//                 }
//             }
//         }
//         return res;
//    }


    // estatistica 1.2.2
/*
    public Map<Integer, int[]> fatTotalMes(Model.Faturacao fat){
        int[] mes = new int[12];

        Map<Integer, int[]> res = new HashMap<>();
        for (int k =1 ; k<4; k++ ){
            res.put(k,mes);
        }
        for (int i = 0; i < 26; i++) {
             for (Model.ProdFat p : fat.getArr(i)) {
                 for (int j = 1; j < 4; j++) {
                     for (int m = 1; m < 13; m++) {
                         mes[m-1] += (p.getFatN(j,m) + p.getFatP(j,m));
                     }
            }
        }
    }



*/

    // estatistica 1.2.3
/*
    public static Map<Integer, int[]> numeroClientesByFil(Model.Filiais fil) {
        Map<Integer, int[]> res1 = new TreeMap<>();
//        int[] res = new int[3];
//        for (int i = 1; i < 13; i++) {
//            res1.put(i, res);
//        }

        for (int i = 0; i < 26; i++) {
            for (Model.ClFil c : fil.getArr(i)) {
                for (Model.FilFil f : c.getFil().values()) {
                    if (f.getUsed() == 1) {
                        for (Model.MesFil m : f.getFilF().values()) {
                            int[] res = new int[3];
                            if (m.isUsed()) {
                                res[]
                            }
                        }
                    }
                }
            }
        }
        return res1;
    }


*/



    //====================================== interativas ===========================================================


    public static List<String> querie1(InterfaceFaturacao fat){
        Crono.start();
        List<String> res = fat.getQuerie1();
        Crono.getTImeString();
        return res;
    }


// querie 2

    public static Map<Integer,int[]> querie2(InterfaceFiliais fil, int mes) throws ValorInvalidoException{

        Crono.start();
        Map<Integer,int[]> res = new TreeMap<>();
        if (mesvalido(mes)) {
            res=fil.getVendasTotaisFiliaisPorMes(mes,res);
        }

        System.out.println(Crono.getTImeString());
        return res;
    }



// querie 3

    public static Map<Integer,double[]> querie3(InterfaceFiliais fil, String cliente){
        Crono.start();
        Map<Integer,double[]> res = null;

        int kc = hashCL(cliente);
        int ip = fil.pBinaria(cliente, kc);

        if(validaCliente(cliente)){
            if(ip!=-1){
                res = fil.getQuerie3(kc,ip);
            }
        }
        System.out.println( Crono.getTImeString());
        return res;
    }


    // querie 4

    public static Map<Integer,double[]> querie4(InterfaceFiliais fil, String prod){
        Crono.start();
        Map<Integer,double[]> res = null;

        if(validaProduto(prod)) res = fil.getQuerie4(prod);

        System.out.println( Crono.getTImeString());
        return res;

    }


    //querie 5
    public static TreeSet<Map.Entry<String, Integer>> querie5(String c, InterfaceFiliais f)  {
        Crono.start();
        //Comparator<Map.Entry<String,Integer>> cp = new Model.ComparatorQ5();
        Map<String, Integer> q5 = new TreeMap<>();

        int kc = hashCL(c);
        int ip = f.pBinaria(c, kc);

        if(validaCliente(c)){
            if(ip!=-1){
                //Lista de produtos e quantidades
                f.getQuerie5(kc,ip,q5);
            }
        }
        //ordenar!

        TreeSet<Map.Entry<String, Integer>> res =  new TreeSet<>(new ComparatorQ5());
        res.addAll(q5.entrySet());

        System.out.println( Crono.getTImeString());
        return res;
    }




    //querie 6

    //int[0] nquantidade vendida;
    //int[1] n clientes;
    public static TreeSet<Map.Entry<String, int[]>> querie6(int limite, InterfaceFiliais fil){
        Crono.start();
        Map<String,int[]> res = null;

        if(limite >0){
            //Lista de produtos, com a quantidade e numero de clientes que o compraram
            res = fil.getQuerie6();
        }

        //Falta dar apenas o limite

        TreeSet<Map.Entry<String, int[]>> q6 = new TreeSet<>(new ComparatorQ6());
        assert res != null;
        q6.addAll(res.entrySet());

        System.out.println( Crono.getTImeString());
        return q6;
    }



    //querie 7
    public static Map<Integer, Map<String,Double>> querie7(InterfaceFiliais f){
        Crono.start();
        Map<Integer, Map<String, Double>> res = null;
        res = f.getQuerie7();

        System.out.println( Crono.getTImeString());
        return res;
    }



    // querie 8
    public static TreeSet<Map.Entry<String, Integer>> querie8(InterfaceFiliais f, int limite){
        Crono.start();
        Map<String, Integer> res = null;

        if(limite >0) {
            res = f.getQuerie8();
        }

        //Ordenar e limitar
         TreeSet<Map.Entry<String, Integer>> q8 =  new TreeSet<>(new ComparatorQ5());
        assert res != null;
        q8.addAll(res.entrySet());


        System.out.println( Crono.getTImeString());
        return q8;
    }

    // querie 9

    public static TreeSet<Map.Entry<String,Double>> querie9(InterfaceFiliais fil, String prod){
        Crono.start();
        Map<String,Double> res = null;

        if(validaProduto(prod)) res = fil.getQuerie9(prod);

        TreeSet<Map.Entry<String, Double>> q9 =  new TreeSet<>(new ComparatorQ9());
        assert res != null;
        q9.addAll(res.entrySet());

        System.out.println( Crono.getTImeString());
        return q9;

    }


    // querie 10

    public static  Map<Integer,Map<Integer,Map<String,Double>>> querie10(InterfaceFaturacao fat){
        Map<Integer,                    //mes
                Map<Integer,            //fil
                        Map<String,     //Model.Produto
                                Double>>> res = new TreeMap<>();
        for (int i=1;i<13;i++)res.put(i,new TreeMap<>());

        fat.getFatPorMesEFil(res);


        System.out.println( Crono.getTImeString());
        return res;

    }


/*
    // Gravar para ficheiro

    public void gravarObj(String filename) throws IOException {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
        o.writeObject(this);
        o.flush();
        o.close();
    }


    //Ler de ficheiros bin

    public static Controlador lerObj(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
        Controlador c = (Controlador) o.readObject();
        o.close();
        return c;
    }
*/


}

