import java.util.*;


public class MesFil {
    private boolean used;
    private int nVendas;
    private Map<String,PrdFil> prs;

    public MesFil()
    {
        this.used =false;
        this.nVendas = 0;
        this.prs = new HashMap<>();
    }

    public MesFil(int n, Map<String, PrdFil> prs) {
        this.used = false;
        this.nVendas = n;
        this.prs = prs;
    }

    public int getnVendas() {
        return nVendas;
    }

    public MesFil setnVendas(int nVendas) {
        this.nVendas = nVendas;
        return this;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean existeProd(String p){
        return this.prs.containsKey(p);
    }

    public void addgN(String p, double t){
        this.prs.get(p).addgN(t);
    }
    public void addqN(String p, int t){
        this.prs.get(p).addqN(t);
    }
    public void addgP(String p, double t){
        this.prs.get(p).addgP(t);
    }
    public void addqP(String p, int t){
        this.prs.get(p).addqP(t);
    }

    public void addPrs(String p){
        this.prs.put(p, new PrdFil(p));
    }


    public void incnVendas(){
        int n = this.nVendas;
        setnVendas(n+1);
    }

    public Map<String, PrdFil> getPrs() {
        return new HashMap<>(this.prs);
    }

    public void setPrs(Map<String, PrdFil> prs) {
        this.prs = prs;
    }

    public int getSizePrs(){
        return this.prs.size();
    }

    public double getTotalPago(){
        double res=0;
        for(PrdFil p:this.prs.values()){
            res+=p.getTotalPago();
        }
        return res;
    }

    public void getQuerie5(Map<String, Integer> res){
        for(PrdFil p: this.prs.values()){
            if (!res.containsKey(p.getPrd())) res.put(p.getPrd(),p.getTotalComprado());
            else res.put(p.getPrd(),res.get(p.getPrd()) + p.getTotalComprado());
        }
    }
}
