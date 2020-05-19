import java.util.*;

public class Filiais implements InterfaceFiliais{
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

    public void addCls(Set<String> clientes) {
        for (String cl : clientes)
            addCl(cl);
    }

    public List<ClFil> getArr(int i){
        return filiais.get(i);
    }



//    public static int pBinaria(String p, List<ClFil> f){
//        int r = -1;
//        int meio;
//        int inicio = 0;
//        int fim = f.size()-1;
//        while (inicio <= fim && r==-1) {
//            meio = (inicio + fim)/2;
//            if (p.compareTo(f.get(meio).getCl()) == 0) {
//                r = meio;
//            }
//            if (p.compareTo(f.get(meio).getCl()) < 0)
//                fim = meio - 1;
//            else
//                inicio = meio + 1;
//        }
//        return r;
//    }
    public int pBinaria(String p, int k){
        List<ClFil> f = filiais.get(k);
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
        return this.filiais.get(hashProd(cl)).get(ip).existeProd(f,m,p);
    }


    public void acrescentaFil(String[] cl) {

        int ip = pBinaria(cl[4], hashProd(cl[4]));
        if (ip >= 0) {
            this.filiais.get(hashProd(cl[4])).get(ip).setUsed(true);
            String p = cl[0];
            double pr = Double.parseDouble(cl[1]);
            int q = Integer.parseInt(cl[2]);
            int m = Integer.parseInt(cl[5]);
            int f = Integer.parseInt(cl[6]);
            int key = hashProd(cl[4]);

            this.filiais.get(key).get(ip).setFilialUsada(f,1);
            this.filiais.get(key).get(ip).incnVendasMes(f,m);
            this.filiais.get(key).get(ip).setUsedFilMes(f,m,true);

            if (!existeProd(cl[4], ip, f, m, p)) this.filiais.get(key).get(ip).addPrs(f,m,p);

            //System.out.println(cl[3] + cl[3].equals("N"));
            if (cl[3].equals("N")) {
                this.filiais.get(key).get(ip).addgN(f,m,p,pr * q);
                this.filiais.get(key).get(ip).addqN(f,m,p,q);

            }else{
                this.filiais.get(key).get(ip).addgP(f,m,p,pr * q);
                this.filiais.get(key).get(ip).addqP(f,m,p,q);
            }

        }
    }

        public int numeroComparadores(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += (int) filiais.get(i).stream().filter(ClFil::getUsed).count();
        }
        return count;
    }

    public int naocompram(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += filiais.get(i).size();
        }
        return count-numeroComparadores();
    }

    public boolean getMesUsed(int key, int ip, int f, int m){
        return filiais.get(key).get(ip).getMesUsed(f,m);
    }


    public int getNVendasMes(int key, int ip, int f, int m){
        return filiais.get(key).get(ip).getFil().get(f).getFilF().get(m).getnVendas();
    }


    public Map<Integer, int[]> getUsedFilialMes(Map<Integer, int[]> res){
        for (int i =0; i<26; i++){
            for (ClFil c: filiais.get(i)){
                res = c.getUsedFilialMes(res);
            }
        }
        return res;
    }

    public Map<Integer, int[]> getVendasTotaisFiliaisPorMes(int mes,Map<Integer, int[]> res){
        for (int i =0; i<26; i++){
            for (ClFil c: filiais.get(i)){
                res = c.getVendasTotaisFiliaisPorMes(mes,res);
            }
        }
        return res;
    }


    public Map<Integer,double[]> getQuerie3(int kc, int ip){
        return filiais.get(kc).get(ip).getQuerie3();
    }

    public Map<Integer,double[]> getQuerie4(String prod) {
        Map<Integer,double[]> res = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            for (ClFil cl : filiais.get(i)) {
                res = cl.getQuerie4(prod,res);
            }
        }
        return res;
    }

    public void getQuerie5(int kc, int ip,Map<String, Integer> q5){
        filiais.get(kc).get(ip).getQuerie5(q5);
    }



    public Map<String,int[]> getQuerie6() {
        Map<String,int[]> res = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            for (ClFil c : filiais.get(i)) {
                c.getQuerie6(res);
            }
        }
        return res;
    }

    public Map<Integer, Map<String, Double>> getQuerie7() {
        Map<Integer, Map<String, Double>> res = new TreeMap<>();

        for (int i = 0; i < 26; i++) {
            for (ClFil c : filiais.get(i)) {
                c.getQuerie7(res, c.getCl());
                //Avaliar se é maior e inserir

            }
        }
        return res;
    }

    public Map<String, Integer> getQuerie8(){
        Map<String, Integer> res = new TreeMap<>();

        for (int i = 0; i < 26; i++) {
            for (ClFil c : filiais.get(i)) {
                res.put(c.getCl(),c.getQuerie8());
            }
        }
        return res;
    }
}