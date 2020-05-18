import java.util.Objects;

public class PrdFil {
    private String prd;
    private int qN;
    private int qP;
    private double gN;
    private double gP;

    public PrdFil(String prd, int qN, int qP, double gN, double gP) {
        this.prd = prd;
        this.qN = qN;
        this.qP = qP;
        this.gN = gN;
        this.gP = gP;
    }

    public String getPrd(){return this.prd;}

    public void setPrd(String prd){ this.prd = prd;}

    public int getqN() {
        return qN;
    }

    public void setqN(int qN) {
        this.qN = qN;
    }

    public int getqP() {
        return qP;
    }

    public void setqP(int qP) {
        this.qP = qP;
    }

    public double getgN() {
        return gN;
    }

    public void setgN(double gN) {
        this.gN = gN;
    }

    public double getgP() {
        return gP;
    }

    public void setgP(double gP) {
        this.gP = gP;
    }

    public void addqN(int qn){
        this.qN += qN;
    }

    public void addqP(int qp){
        this.qP += qP;
    }

    public void addgP(double gp){
        this.gP+=gP;
    }

    public void addgN(double gn){
        this.gN+=gN;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrdFil prdFil = (PrdFil) o;
        return prd == prdFil.prd &&
                qN == prdFil.qN &&
                qP == prdFil.qP &&
                Double.compare(prdFil.gN, gN) == 0 &&
                Double.compare(prdFil.gP, gP) == 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrdFil{");
        sb.append("qN=").append(qN);
        sb.append(", qP=").append(qP);
        sb.append(", gN=").append(gN);
        sb.append(", gP=").append(gP);
        sb.append('}');
        return sb.toString();
    }
}
