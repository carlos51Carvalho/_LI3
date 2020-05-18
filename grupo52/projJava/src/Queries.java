import java.util.*;

public class Queries {
    //private List<String> q1;

    //queries interativas
/*
    public Queries(){
        List
    }*/

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

    public static boolean mesvalido(int mes){
        return mes >= 1 && mes <= 12;
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


    public static  int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }

// querie 3

    public static Map<Integer,Map<Integer,double[]>> querie3(Filiais fil, String cliente){
        int vendas =0;
        int dprod =0;
        int gasto =0;
        Map<Integer,Map<Integer,double[]>> res = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            res.put(i,new HashMap<Integer, double[]>());
        }
        int kc = hashCL(cliente);
        int ip = Filiais.pBinaria(cliente, fil.getArr(kc));

        for (int i = 1; i < 4; i++) {
            for (int j =1; j<13; j++){
                vendas = fil.getNVendasMes(kc,ip,i,j);
                dprod = fil.getArr(kc).get(ip).getFil().get(i).getFilF().get(j).getPrs().size();
                Map<String,PrdFil> prs = fil.getArr(kc).get(ip).getFil().get(i).getFilF().get(j).getPrs();

                //gasto = prs.forEach(gasto+= g);
                System.out.println(i);
                //System.out.printf(vendas);
                System.out.printf(" " + dprod);

                double [] a ={vendas, dprod};
                res.get(i).put(j,a);
            }
        }
        return res;
    }


// querie 4

















    //querie 5
    public static int pBinaria(String p, List<ClFil> f){
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

    public static Map<String,Integer> querie5(String c, Filiais f) {
        int cl = hashCL(c);
        int r = pBinaria(c, f.getArr(cl));
        Map<String, Integer> q5 = new TreeMap<>();
        if (!f.getArr(cl).get(r).getUsed()) return q5;

        for (FilFil fi : f.getArr(cl).get(r).getFil().values()) {
            if (fi.getUsed() == 1) {
                for (MesFil m : fi.getFilF().values()) {
                    if (m.isUsed()) {
                        for (PrdFil p : m.getPrs().values()) {
                            if (q5.containsKey((p.getPrd()))) {
                                int q = q5.get(p.getPrd());
                                q += p.getqN() + p.getqP();
                                q5.put(p.getPrd(), q);

                            } else {
                                q5.put(p.getPrd(), p.getqN() * p.getqP());
                            }
                        }
                    }
                }
            }
        }
        for(Map.Entry<String, Integer> e : q5.entrySet())
            System.out.println(e.getKey() + e.getValue());


        return q5;

    }

}
