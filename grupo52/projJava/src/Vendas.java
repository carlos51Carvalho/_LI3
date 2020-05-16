import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Vendas {

    private static String nome;
    private static int errados;
    private static int tprod;
    private static int dprod;
    private static int pnc;
    private static int tcl;
    private static int tclc;
    private static int tclsc;
    private static int tvendaszero;
    private static double fattotal;

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


    public static int ler_vendas(Faturacao fat,Filiais fil,TreeSet<String> c, TreeSet<String> p,String filepath) throws Exception
    {
    int i = 0;
    int t = 0;
    int zero = 0;
    double ft = 0;
    //TreeSet<String> pv = (TreeSet<String>) p.getSetDeProdutos();
    TreeSet<String> pt = new TreeSet<>(p);
    //TreeSet<String> cv = (TreeSet<String>) c.getSetDeClientes();
    TreeSet<String> ct = new TreeSet<>(c);

        File file = new File(filepath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            String[] st = line.split(" ");
            t++;
            if (validate(st,c,p)) {
                //System.out.println(hashCL(st));
                fat.acrescentaFat(st);
                fil.acrescentaFil(st);
                i++;
                int pk = hashp(st[0]);
                int ck = hashc(st[0]);
                if(Double.parseDouble(st[1]) == 0.0) zero++;
                if(pt.contains(st[0])) pt.remove(st[0]);
                if(ct.contains(st[4])) ct.remove(st[4]);

                ft += Double.parseDouble(st[1]) * Integer.parseInt(st[2]);



            }
        }
        errados = t-i;
        tprod = p.size();
        pnc = pt.size();
        dprod = tprod - pnc;
        tcl = c.size();
        tclsc = ct.size();
        tclc = tcl - tclsc;
        tvendaszero = zero;
        fattotal = ft;


        System.out.println("\n" + errados);
        System.out.println(tprod);
        System.out.println(dprod);
        System.out.println(pnc);
        System.out.println(tcl);
        System.out.println(tclc);
        System.out.println(tclsc);
        System.out.println(tvendaszero);
        System.out.println(fattotal + "\n");
    System.out.println("Vendas lidas Lidos \n");
    return i;
    }

}
