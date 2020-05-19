import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ClFil {
    private String cl;
    private boolean used;
    private Map<Integer, FilFil> fil;

    public ClFil(){
        this.cl = "";
        this.used = false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFil());
    }

    public ClFil(String p){
        this.cl = p;
        this.used = false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFil());
    }

    public String getCl() {
        return this.cl;
    }

    public void setCl(String ID) {
        this.cl = ID;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setFilialUsada(int f,int v){
        this.fil.get(f).setUsed(v);
    }

    public boolean existeProd(int f, int m,String p){
        return this.fil.get(f).existeProd(m,p);
    }


    public void addgN(int f,int m, String p, double t){
        this.fil.get(f).addgN(m,p,t);
    }
    public void addqN(int f,int m, String p, int t){
        this.fil.get(f).addqN(m,p,t);
    }
    public void addgP(int f,int m, String p, double t){
        this.fil.get(f).addgP(m,p,t);
    }
    public void addqP(int f,int m, String p, int t){
        this.fil.get(f).addqP(m,p,t);
    }

    public void addPrs(int f, int m, String p){
        this.fil.get(f).addPrs(m,p);
    }


    public void incnVendasMes(int f, int m){
        this.fil.get(f).incnVendasMes(m);
    }

    public void setUsedFilMes(int f,int m,boolean b){
        this.fil.get(f).setUsedMes(m,b);
    }
    /*
    public Map<Integer, FilFat> getPrd(){
        Map<Integer, FilFat> p = new HashMap<>();
        Set <Map.Entry<Integer, FilFat>> aux = prod.entrySet();
        for ( Map.Entry<Integer,FilFat> aux2: aux )
            p.put(aux2.getKey(),aux2.getValue());

        return p;
    }
*/

    public Map<Integer, FilFil> getFil(){
        return this.fil;
    }

    public boolean getMesUsed( int f, int m){
        return fil.get(f).getMesUsed(m);
    }

    public Map<Integer, int[]> getUsedFilialMes(Map<Integer, int[]> res){
        for (Map.Entry<Integer,FilFil> f: this.fil.entrySet() ){

            if (!res.containsKey(f.getKey())) res.put(f.getKey(),new int[13]);

            res.put(f.getKey(),f.getValue().getUsedByMes(res.get(f.getKey())));
        }
        return res;
    }

    public int getNVendasMes( int f, int m){
        return fil.get(f).getFilF().get(m).getnVendas();
    }

    public Map<Integer, int[]> getVendasTotaisFiliaisPorMes(int mes,Map<Integer, int[]> res){
        int aux;
        boolean alterado=false;
        int [] res0 = new int[2];
        if (!res.containsKey(0))res.put(0,new int[2]);

        for (Map.Entry<Integer,FilFil> f: this.fil.entrySet() ){
            aux=0;
            if (!res.containsKey(f.getKey()))res.put(f.getKey(),new int[2]);

            aux = f.getValue().getVendasPorMes(mes);

            if(aux!=0){
                if (!alterado){
                    alterado=true;
                    res.get(0)[0]++;
                }
                res.get(f.getKey())[0]++;
                res.get(f.getKey())[1]+=aux;
                res.get(0)[1]+=aux;
            }

        }
        return res;
    }

    public Map<Integer,double[]> getQuerie3(){
        Map<Integer,double[]> res = new TreeMap<>();
        for (FilFil f: this.fil.values() ){
            f.getQuerie3(res);
        }
        return res;
    }

    public void getQuerie5(Map<String, Integer> q5){
        for (FilFil f: this.fil.values() ) {
            if (f.getUsed() == 1) f.getQuerie5(q5);
        }
    }
    public Map<Integer,int[]> getQuerie6(Map<Integer,int[]> res){
        Map<Integer,int[]> res = new TreeMap<>();
        for (FilFil f: this.fil.values() ){
            f.getQuerie6(res);
        }
        return res;
    }

}
