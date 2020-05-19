import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ProdFat {
    private String prod;
    private boolean used;
    private Map<Integer, FilFat> fil;

    public ProdFat(){
        this.prod = "";
        this.used =false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFat());
    }

    public ProdFat(String p){
        this.prod = p;
        this.used =false;
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFat());
    }

    public String getProd() {
        return this.prod;
    }

    public void setProd(String ID) {
        this.prod = ID;
    }

    public boolean getUsed() {
        return used;
    }

    public ProdFat setUsed(boolean used) {
        this.used = used;
        return this;
    }

    public void incFilialUsed(int f){
        this.fil.get(f).incUsed();
    }
    public void addFN(int f,int m, double p){
        this.fil.get(f).addFN(m,p);
    }

    public void addFP(int f,int m, double p){
        this.fil.get(f).addFP(m,p);
    }

     public void incVN(int f,int m){
        this.fil.get(f).incVN(m);
    }
     public void incVP(int f,int m){
        this.fil.get(f).incVP(m);
    }
/*
    public Map<Integer, FilFaor eu vou int> getPrd(){
        Map<Integer, FilFat> p = new HashMap<>();
        Set <Map.Entry<Integer, FilFat>> aux = prod.entrySet();
        for ( Map.Entry<Integer,FilFat> aux2: aux )
            p.put(aux2.getKey(),aux2.getValue());

        return p;
    }
*/

    public Map<Integer, FilFat> getFil(){
        return this.fil;
    }


    public double getFatN(int f, int m){
        return fil.get(f).getFilF().get(m).getfN();
    }

    public double getFatP(int f, int m){
        return fil.get(f).getFilF().get(m).getfP();
    }
    public double getVenP(int f, int m){
        return fil.get(f).getFilF().get(m).getvP();
    }
    public double getVenN(int f, int m){
        return fil.get(f).getFilF().get(m).getvN();
    }


    public void getFatPorMesEFil(Map<Integer,Map<Integer,Map<String,Double>>> res){
        //this.prod;
        for(Map.Entry<Integer, FilFat> f : this.fil.entrySet()){
            for (int i=1;i<13;i++){
                if(!res.get(1).containsKey(f.getKey())) res.get(i).put(f.getKey(),new TreeMap<>());
                res.get(i).get(f.getKey()).put(this.prod,f.getValue().getFatPorMes(i));


            }
        }
    }
}