import java.io.Serializable;

public class MesFat implements Serializable {
    private double fN;
    private double fP;
    private int vN;
    private int vP;

    public MesFat(){
        this.fN = 0;
        this.vN = 0;
        this.fP = 0;
        this.vP = 0;
    }

    public MesFat(MesFat m){
        this.fN = m.getfN();
        this.vN = m.getvN();
        this.fP = m.getfP();
        this.vP = m.getvP();
    }

    public double getfN() {
        return this.fN;
    }

    public void setfN(double fN) {
        this.fN = fN;
    }

    public double getfP() {
        return this.fP;
    }

    public void setfP(double fP) {
        this.fP = fP;
    }

    public int getvN() {
        return this.vN;
    }

    public void setvN(int vN) {
        this.vN = vN;
    }

    public int getvP() {
        return this.vP;
    }

    public void setvP(int vP) {
        this.vP = vP;
    }

    public void incvN(){
        this.vN += 1;
    }

    public void incvP(){
        this.vP += 1;
    }

    public void addfN(double n){
        this.fN += n;
    }

    public void addfP(double p){
        this.fP += p;
    }

    public int totalVendasMes(){
        return getvN()+getvP();
    }

    public double totalFatMes(){
        return getfN()+getfP();
    }

}