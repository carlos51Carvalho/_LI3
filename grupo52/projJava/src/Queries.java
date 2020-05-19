import java.util.*;

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

    public static boolean mesvalido(int mes){
        return mes >= 1 && mes <= 12;
    }

    public static  int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }



    // estaticas 1.2

    // estatistica 1.2.1

//    public int[] numeroCompPMes(Filiais fil){
//        int[] res = new int[12];
//         for (int i = 0; i < 26; i++) {
//             for (ClFil c : fil.getArr(i)) {
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
    public Map<Integer, int[]> fatTotalMes(Faturacao fat){
        int[] mes = new int[12];

        Map<Integer, int[]> res = new HashMap<>();
        for (int k =1 ; k<4; k++ ){
            res.put(k,mes);
        }
        for (int i = 0; i < 26; i++) {
             for (ProdFat p : fat.getArr(i)) {
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
    public static Map<Integer, int[]> numeroClientesByFil(Filiais fil) {
        Map<Integer, int[]> res1 = new TreeMap<>();
//        int[] res = new int[3];
//        for (int i = 1; i < 13; i++) {
//            res1.put(i, res);
//        }

        for (int i = 0; i < 26; i++) {
            for (ClFil c : fil.getArr(i)) {
                for (FilFil f : c.getFil().values()) {
                    if (f.getUsed() == 1) {
                        for (MesFil m : f.getFilF().values()) {
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


    public static List<String> querie1(Faturacao fat){
        int total =0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i<26 ; i++){
            for (ProdFat p : fat.getArr(i))
                if (!p.getUsed()){
                    res.add(p.getProd());
                    total++;
                }
        }
        System.out.println(total);
        System.out.println(res);
        return res;
    }


// querie 2

    public static Map<Integer,int[]> querie2(Filiais fil, int mes){
//        int total =0;
//        int v1=0;
//        int v2 =0;
//        int v3 =0;
//        int c1 =0;
//        int c2 =0;
//        int c3 =0;
//        int ct =0; // sem olhar a filiais

        Map<Integer,int[]> res = new TreeMap<>();
        if (mesvalido(mes)) {
            res=fil.getVendasTotaisFiliaisPorMes(mes,res);
        }

        //Map<Integer, Integer > res = new HashMap<>();

//            for (int i = 0; i < 26; i++) {
//                for (ClFil c : fil.getArr(i)){
//                    if (c.getMesUsed(1,mes) || c.getMesUsed(2,mes) || c.getMesUsed(3,mes)){
//                        ct++;
//                        for (int j = 1; j < 4; j++) {
//                            if (i ==1) {
//                                c1++;
//                                v1 += c.getFil().get(j).getFilF().get(mes).getnVendas();
//                            }
//                            else if (i ==2){
//                                c2++;
//                                v2+= c.getFil().get(j).getFilF().get(mes).getnVendas();
//                            }
//                            else if (i ==3 && c.getUsed()){
//                                c3++;
//                                v3 += c.getFil().get(j).getFilF().get(mes).getnVendas();
//                            }
//                        }
//                    }
//                }

//            }
//
//        }
//        total = v1+v2+v3;
        return res;
    }



// querie 3

    public static Map<Integer,double[]> querie3(Filiais fil, String cliente){
        Map<Integer,double[]> res = null;

        int kc = hashCL(cliente);
        int ip = fil.pBinaria(cliente, kc);

        if(validaCliente(cliente)){
            if(ip!=-1){
                res = fil.getQuerie3(kc,ip);
            }
        }

        return res;
    }


// querie 4




    //querie 5
    public static Map<String,Integer> querie5(String c, Filiais f)  {

        ComparatorQ5 comp = new ComparatorQ5();
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


        return q5;
    }

}
