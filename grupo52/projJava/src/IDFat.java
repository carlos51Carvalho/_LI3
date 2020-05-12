import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IDFat {
    private String ID;
    private Map<Integer, Fil> obj;

    public IDFat(){
        this.ID = "";
        this.obj = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.obj.put(i, new Fil());
    }

    public IDFat(String p){
        this.ID = p;
        this.obj = new HashMap<>();
        for (int i = 1; i<4 ; i++)
            this.obj.put(i, new Fil());
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public Map<Integer, Fil> getObj(){
        return this.obj;
    }


}
