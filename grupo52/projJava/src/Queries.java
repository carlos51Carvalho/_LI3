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
        //Map<Integer, Integer > res = new HashMap<>();
        if (mesvalido(mes)) {
            for (int i = 0; i < 26; i++) {
                for (ClFil c : fil.getArr(i)){
                    for (int j = 1; j < 4; j++) {
                        if (i ==1){
                            v1++;
                            c1++;
                        }
                        else if (i ==2){
                            v2++;
                            c2++;
                        }
                        else{
                            v3++;
                            c3++;
                        }
                    }
                }
            }

        }
        total = v1+v2+v3;
        return total;
    }

    public static  int hashCL(String cl) {
        return cl.charAt(0) - 'A';
    }

    public static void querie3(Filiais fil, String cliente){
        List<Integer> res = new ArrayList<>();
        int kc = hashCL(cliente);
        int ip = Filiais.pBinaria(cliente, fil.getArr(kc));
        for (int j = 1; j < 4; j++) {
            for (int i =1; i<13; i++){

            }
        }
    }


}
