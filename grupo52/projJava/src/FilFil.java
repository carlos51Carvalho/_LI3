import java.util.HashMap;
import java.util.Map;

public class FilFil {
    private int used;
    private Map<Integer, MesFil> filial;

    public FilFil(){
        this.used = 0;
        this.filial = new HashMap<>();
        for (int i = 1; i<13 ; i++)
            this.filial.put(i, new MesFil());
    }


    public int getUsed() {
        return this.used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void incUsed(){
        this.used += 1;
    }

    public boolean existeProd(int m, String p){
        return this.filial.get(m).existeProd(p);
    }

    public void incnVendasMes(int m){
        this.filial.get(m).incnVendas();
    }

    public void setUsedMes(int m,boolean b){
        this.filial.get(m).setUsed(b);
    }

    public void addgN(int m, String p, double t){
        this.filial.get(m).addgN(p,t);
    }
    public void addqN(int m, String p, int t){
        this.filial.get(m).addqN(p,t);
    }
    public void addgP(int m, String p, double t){
        this.filial.get(m).addgP(p,t);
    }
    public void addqP(int m, String p, int t){
        this.filial.get(m).addqP(p,t);
    }

    public void addPrs(int m, String p){
        this.filial.get(m).addPrs(p);
    }


    public Map<Integer,MesFil> getFilF(){
        return this.filial;
    }

    public boolean getMesUsed(int mes){
        return this.getFilF().get(mes).isUsed();
    }

    public int[] getUsedByMes(int[] res){
        for (int i =1; i<13 ; i++){
            if (getMesUsed(i))
            res[i]++;
        }
        return res;
    }

    public int getVendasPorMes(int mes){
        return this.filial.get(mes).getnVendas();
    }

    public void getQuerie3(Map<Integer,double[]> res){
        for(Map.Entry<Integer,MesFil> m: this.filial.entrySet()){
            if (!res.containsKey(m.getKey()))res.put(m.getKey(),new double[3]);

            res.get(m.getKey())[0]+=(double) m.getValue().getnVendas();
            res.get(m.getKey())[1]+=(double) m.getValue().getSizePrs();
            res.get(m.getKey())[2]+= m.getValue().getTotalPago();
        }
    }

    public void getQuerie5(Map<String, Integer> res){
        for(MesFil m: this.filial.values()){
            if (m.isUsed()) m.getQuerie5(res);
        }
    }

    public void getQuerie6(Map<String, int[]> res){
        for (MesFil m : this.filial.values())
            if (m.isUsed()) m.getQuerie6(res);
    }
    public double getQuerie7(){
        double pay = 0.0;
        for (MesFil m : this.filial.values()) {
            if (m.isUsed()) pay += m.getTotalPago();
        }
        return pay;
    }

}