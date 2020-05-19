
import java.util.*;

public class Faturacao implements InterfaceFaturacao{
    private Map<Integer, List<ProdFat>> faturacoes;


    public Faturacao() {
        this.faturacoes = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.faturacoes.put(i, new ArrayList<>());
    }

    public int hashProd(String p) {
        return p.charAt(0) - 'A';
    }

    public void addProd(String p) {
        ProdFat prd = new ProdFat(p);
        faturacoes.get(hashProd(p)).add(prd);
    }

    public void addProds(Set<String> produtos) {
        for (String p : produtos)
            addProd(p);
    }

    public List<ProdFat> getArr(int i){
        return faturacoes.get(i);
    }



    public static int pBinaria(String p, List<ProdFat> f){
        int r = -1;
        int meio;
        int inicio = 0;
        int fim = f.size()-1;
        while (inicio <= fim && r==-1) {
                 meio = (inicio + fim)/2;
                 if (p.compareTo(f.get(meio).getProd()) == 0) {
                     r = meio;
                 }
                 if (p.compareTo(f.get(meio).getProd()) < 0)
                     fim = meio - 1;
                 else
                     inicio = meio + 1;
        }
    return r;

}

    public void acrescentaFat(String[] p) {

        int ip = pBinaria(p[0],faturacoes.get(hashProd(p[0])));
        if (ip >= 0) {
            this.faturacoes.get(hashProd(p[0])).get(ip).setUsed(true);
            double pr = Double.parseDouble(p[1]);
            int q = Integer.parseInt(p[2]);
            int m = Integer.parseInt(p[5]);
            int f = Integer.parseInt(p[6]);
            int key = hashProd(p[0]);

            //this.faturacoes.get(key).get(ip).getFil().get(f).setUsed(1);
            this.faturacoes.get(key).get(ip).incFilialUsed(f);
            //System.out.println(this.faturacoes.get(hashProd(p[0])).get(ip).getPrd());
            if (p[3].equals("N")) {
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).addfN(pr * q);
                this.faturacoes.get(key).get(ip).addFN(f,m,pr*q);
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).incvN();
                this.faturacoes.get(key).get(ip).incVN(f,m);

            } else if (p[3].equals("P")){
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).addfP(pr * q);
                this.faturacoes.get(key).get(ip).addFP(f,m,pr*q);
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).incvP();
                this.faturacoes.get(key).get(ip).incVP(f,m);
            }

        }

    }

    public int numeroProdUsados(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += (int) faturacoes.get(i).stream().filter(ProdFat::getUsed).count();
        }
        return count;
    }

    public int numeroProdNuncaUsados(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += faturacoes.get(i).size();
        }
        return count-numeroProdUsados();
    }






}
