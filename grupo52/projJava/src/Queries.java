import java.util.*;

public class Queries {
    //private List<String> q1;

    //queries interativas
/*
    public Queries(){
        List
    }*/

    // estaticas 1.2

    public int[] numeroCompPMes(Filiais fil) {
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


    //interativas


    public static List<String> querie1(Faturacao fat) {
        int total = 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (ProdFat p : fat.getArr(i))
                if (!p.getUsed()) {
                    res.add(p.getProd());
                    total++;
                }
        }
        System.out.println(total);
        System.out.println(res);
        return res;
    }

    public static boolean mesvalido(int mes) {
        return mes >= 1 && mes <= 12;
    }


    public static int querie2(Filiais fil, int mes) {
        int total = 0;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int ct = 0; // sem olhar a filiais
        //Map<Integer, Integer > res = new HashMap<>();
        if (mesvalido(mes)) {
            for (int i = 0; i < 26; i++) {
                for (ClFil c : fil.getArr(i)) {
                    if (c.getMesUsed(1, mes) || c.getMesUsed(2, mes) || c.getMesUsed(3, mes)) {
                        ct++;
                        for (int j = 1; j < 4; j++) {
                            if (i == 1) {
                                c1++;
                                v1 += c.getFil().get(j).getFilF().get(mes).getnVendas();
                            } else if (i == 2) {
                                c2++;
                                v2 += c.getFil().get(j).getFilF().get(mes).getnVendas();
                            } else if (i == 3 && c.getUsed()) {
                                c3++;
                                v3 += c.getFil().get(j).getFilF().get(mes).getnVendas();
                            }
                        }
                    }
                }
            }

        }
        total = v1 + v2 + v3;
        return c1;
    }

    public static int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }



}
