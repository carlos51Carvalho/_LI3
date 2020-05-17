import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queries {
    //private List<String> q1;

    //queries interativas
/*
    public Queries(){
        List
    }*/

    // estaticas 1.2

    public int[] numeroCompPMes(Filiais fil){
        int[] res = new int[12];
         for (int i = 0; i < 26; i++) {
             for (ClFil c : fil.getArr(i)) {
                 for (int j = 1; j < 4; j++) {
                     for (int m = 1; m < 13; m++) {
                         res[m - 1] += c.getNVendasMes(j, m);
                     }
                 }
             }
         }
         return res;
    }












    //interativas


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


    public static int querie2(Filiais fil, int mes){
        int total =0;
        int v1=0;
        int v2 =0;
        int v3 =0;
        int c1 =0;
        int c2 =0;
        int c3 =0;
        int ct =0; // sem olhar a filiais
        //Map<Integer, Integer > res = new HashMap<>();
        if (mesvalido(mes)) {
            for (int i = 0; i < 26; i++) {
                for (ClFil c : fil.getArr(i)){
                    if (c.getMesUsed(1,mes) || c.getMesUsed(2,mes) || c.getMesUsed(3,mes)){
                        ct++;
                        for (int j = 1; j < 4; j++) {
                            if (i ==1) {
                                c1++;
                                v1 += c.getFil().get(j).getFilF().get(mes).getnVendas();
                            }
                            else if (i ==2){
                                c2++;
                                v2+= c.getFil().get(j).getFilF().get(mes).getnVendas();
                            }
                            else if (i ==3 && c.getUsed()){
                                c3++;
                                v3 += c.getFil().get(j).getFilF().get(mes).getnVendas();
                            }
                        }
                    }
                }
            }

        }
        total = v1+v2+v3;
        return c1;
    }

    public static  int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }



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







    //querie 5



}
