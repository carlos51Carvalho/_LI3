import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Produtos {
    private Map<Integer, Set<String>> produtos;

    public Produtos() {
        this.produtos = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.produtos.put(i, new TreeSet<>());
    }

    public int hashP(String p) {
        return p.charAt(0) - 'A';
    }

    public boolean validaNum(String p) {
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


    public boolean validaProduto(String cl) {
        return cl.charAt(0) >= 'A' && cl.charAt(0) <= 'Z' && cl.charAt(1) >= 'A' && cl.charAt(1) <= 'Z'&& validaNum(cl);
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("Produtos{");
        sb.append("produtos=\n").append(produtos);
        sb.append('}');
        return sb.toString();
    }

    public int ler_produtos(String filepath) throws Exception
    {
        int i = 0;

        File file = new File(filepath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {

            if (validaProduto(st)) {
                //System.out.println(hashCL(st));
                produtos.get(hashP(st)).add(st);
                i++;
            }
        }
    System.out.println("Produtos Lidos \n");
    return i;
    }

    public TreeSet<String> getP(int i) {
        return (TreeSet<String>) produtos.get(i);
    }

    public Set<String> get(int kp) {
        return produtos.get(kp);
    }
}
