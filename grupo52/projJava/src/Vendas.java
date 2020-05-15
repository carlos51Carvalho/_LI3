import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;

public class Vendas {

    public static int hashp(String p) {
        return p.charAt(0) - 'A';
    }
    public static int hashc(String p) {
        return p.charAt(0) - 'A';
    }


    public static boolean validate(String[] s, Clientes clientes, Produtos produtos){
        int kp = hashp(s[0]);
        int kc = hashc(s[4]);
        return s.length == 7
                && produtos.existe(s[0],kp)
                && Double.parseDouble(s[1]) >= 0.0
                && Integer.parseInt(s[2]) >=0
                && (s[3].length()==1) && (s[3].equals("P") || s[3].equals("N"))
                && clientes.existe(s[4],kc)
                && Integer.parseInt(s[5]) >= 0 && Integer.parseInt(s[5]) <= 12
                && Integer.parseInt(s[6]) >= 0 && Integer.parseInt(s[6]) <= 3;
    }


    public static int ler_vendas(Faturacao fat,Filiais fil,Clientes c, Produtos p,String filepath) throws Exception
    {
    int i = 0;

        File file = new File(filepath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            String[] st = line.split(" ");
            if (validate(st,c,p)) {
                //System.out.println(hashCL(st));
                fat.acrescentaFat(st);
                fil.acrescentaFil(st);
                i++;
            }
        }
    System.out.println("Vendas lidas Lidos \n");
    return i;
    }

}
