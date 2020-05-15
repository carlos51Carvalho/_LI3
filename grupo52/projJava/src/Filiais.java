import java.util.*;

public class Filiais {
    private Map<Integer, List<ClFil>> filiais;


    public Filiais() {
        this.filiais = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.filiais.put(i, new ArrayList<>());
    }

    public int hashProd(String p) {
        return p.charAt(0) - 'A';
    }

    public void addCl(String cl) {
        ClFil cls = new ClFil(cl);
        filiais.get(hashProd(cl)).add(cls);
    }

    public void addCls(TreeSet<String> clientes) {
        for (String cl : clientes)
            addCl(cl);
    }

    public List<ClFil> getArr(int i){
        return filiais.get(i);
    }



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

    public boolean existeProd(String cl, int ip, int f, int m, String p){
        return this.filiais.get(hashProd(cl)).get(ip).getFil().get(f).getFilF().get(m).getPrs().containsKey(p);
    }

    public void acrescentaFil(String[] cl) {

        int ip = pBinaria(cl[4], filiais.get(hashProd(cl[4])));
        if (ip >= 0) {
            String p = cl[0];
            double pr = Double.parseDouble(cl[1]);
            int q = Integer.parseInt(cl[2]);
            int m = Integer.parseInt(cl[5]);
            int f = Integer.parseInt(cl[6]);

            this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).setUsed(1);
            //System.out.println(this.faturacoes.get(hashProd(p[0])).get(ip).getPrd());

            if (existeProd(cl[4], ip, f, m, p)) {
                if (cl[3].equals("N")) {
                    this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).getFilF().get(m).getPrs().get(p).addgN(pr * q);
                    this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).getFilF().get(m).getPrs().get(p).addqN(q);

                } else if (cl[3].equals("P")) {
                    this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).getFilF().get(m).getPrs().get(p).addgP(pr * q);
                    this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).getFilF().get(m).getPrs().get(p).addqP(q);
                }

            }
            else{
                double g = q*pr;
                if(cl[3].equals("N")){
                    PrdFil pf = new PrdFil(q,0,g,0 );
                    this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).getFilF().get(m).getPrs().put(p,pf);
                }
                else if(cl[3].equals("P")){
                    PrdFil pf = new PrdFil(0,q,0,g);
                    this.filiais.get(hashProd(cl[4])).get(ip).getFil().get(f).getFilF().get(m).getPrs().put(p,pf);
                }
            }

        }
    }


}