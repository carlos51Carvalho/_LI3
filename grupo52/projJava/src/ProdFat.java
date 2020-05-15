import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProdFat {
    private String prod;
    private Map<Integer, FilFat> fil;

    public ProdFat(){
        this.prod = "";
        this.fil = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.fil.put(i, new FilFat());
    }

    public ProdFat(String p){
        this.prod = p;
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
/*
    public Map<Integer, FilFat> getPrd(){
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


}
