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



}
