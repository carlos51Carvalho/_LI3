import java.util.HashMap;
import java.util.Map;

public class FilFat {
    private int used;
    private Map<Integer, MesFat> filial;

    public FilFat(){
        this.used = 0;
        this.filial = new HashMap<>();
        for (int i = 1; i<13 ; i++)
            this.filial.put(i, new MesFat());
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

    public Map<Integer,MesFat> getFilF(){
        return this.filial;
    }

    public void addFN(int m, double p){
        this.filial.get(m).addfN(p);
    }

    public void addFP(int m, double p){
        this.filial.get(m).addfP(p);
    }

    public void incVN(int m){
        this.filial.get(m).incvN();
    }
    public void incVP(int m){
        this.filial.get(m).incvP();
    }


    public double getFatPorMes(int i){
        return this.filial.get(i).totalFatMes();
    }
}