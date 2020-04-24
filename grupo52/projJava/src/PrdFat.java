import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PrdFat {
    private String prodID;
    private Map<Integer, FilFat> prod;

    public PrdFat(){
        this.prodID = "";
        this.prod = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.prod.put(i, new FilFat());
    }

    public PrdFat(String p){
        this.prodID = p;
        this.prod = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.prod.put(i, new FilFat());
    }

    public String getProdID() {
        return this.prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
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

    public Map<Integer, FilFat> getPrd(){
        return this.prod;
    }


}
