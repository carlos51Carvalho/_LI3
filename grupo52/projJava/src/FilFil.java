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
}
