import java.util.*;

public class Filiais {
    private Map<Integer, List<IDFat>> filiais;


    public Filiais() {
        this.filiais = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.filiais.put(i, new ArrayList<>());
    }

    public int hashProd(String p) {
        return p.charAt(0) - 'A';
    }

    public void addCl(String cl) {
        IDFat cls = new IDFat(cl);
        filiais.get(hashProd(cl)).add(cls);
    }

    public void addCls(TreeSet<String> clientes) {
        for (String cl : clientes)
            addCl(cl);
    }

    public List<IDFat> getArr(int i){
        return (List<IDFat>) filiais.get(i);
    }



    public static int pBinaria(String p, List<IDFat> f){
        int r = -1;
        int meio;
        int inicio = 0;
        int fim = f.size()-1;
        while (inicio <= fim && r==-1) {
            meio = (inicio + fim)/2;
            if (p.compareTo(f.get(meio).getID()) == 0) {
                r = meio;
            }
            if (p.compareTo(f.get(meio).getID()) < 0)
                fim = meio - 1;
            else
                inicio = meio + 1;
        }
        return r;

    }

    public void acrescentaFil(String[] cl) {

        int ip = pBinaria(cl[0],filiais.get(hashProd(cl[0])));
        if (ip >= 0) {
            String p = cl[0];
            double pr = Double.parseDouble(cl[1]);
            int q = Integer.parseInt(cl[2]);
            int m = Integer.parseInt(cl[5]);
            int f = Integer.parseInt(cl[6]);

            this.filiais.get(hashProd(cl[0])).get(ip).getObj().get(f).setUsed(1);
            //System.out.println(this.faturacoes.get(hashProd(p[0])).get(ip).getPrd());
            if (cl[3].equals("N")) {
                this.filiais.get(hashProd(cl[0])).get(ip).getObj().get(f).getFilF().get(m).get(p).addfN(pr * q);
                this.filiais.get(hashProd(cl[0])).get(ip).getObj().get(f).getFilF().get(m).get(p).incvN();

            } else if (p[3].equals("P")){
                this.faturacoes.get(hashProd(p[0])).get(ip).getObj().get(f).getFilF().get(m).addfP(pr * q);
                this.faturacoes.get(hashProd(p[0])).get(ip).getObj().get(f).getFilF().get(m).incvP();
            }

        }

    }


}