import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queries {

    //queries interativas

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


    public static Map<Integer, List<Integer>> querie2(Filiais fil, int mes){
        int total =0;
        int v1=0;
        int v2 =0;
        int v3 =0;
        int c1 =0;
        int c2 =0;
        int c3 =0;
        Map<Integer, List<Integer>> res = new HashMap<>();
        if (mesvalido(mes)) {
            for (int i = 0; i < 26; i++) {
                for (ClFil c : fil.getArr(i)){
                    for (int j = 1; j < 4; j++) {
                        if (i ==1){

                        }
                    }

                }
            }
        }
        return res;
    }




}
