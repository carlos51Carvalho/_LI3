import java.util.Comparator;
import java.util.Map;

public class ComparatorQ6 implements Comparator<Map.Entry<String,int[]>> {

    public int  compare(Map.Entry<String,int[]> a , Map.Entry<String,int[]> b){
        if (a.getValue()[0] == b.getValue()[0]) return b.getValue()[1]-a.getValue()[1];
        return b.getValue()[0]-a.getValue()[0];
    }
}
