import java.util.Comparator;
import java.util.Map;

public class ComparatorQ5 implements Comparator<Map.Entry<String,Integer>> {
    public int compare(Map.Entry<String, Integer> q1, Map.Entry<String, Integer> q2) {
        if (q1.getValue() > q2.getValue()) return 1;
        else if (q1.getValue() < q2.getValue()) return -1;
        else {
            return q1.getKey().compareTo(q2.getKey());
        }
    }
}




//
//    @Override
//    public int compare(Map<String, Integer> q1, Map<String, Integer> q2) {
//
//        return q1.get().getValue()-q2.getValue();
//    }
//
//    @Override
//    public Comparator<Map<String, Integer>> thenComparing(Comparator<> other) {
//        return null;
//    }
//}