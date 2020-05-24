package Model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/**
 * Classe que contem o comparador para a Querie 5
 */
public class ComparatorQ5 implements Comparator<Map.Entry<String,Integer>>, Serializable {

    @Override
    public int compare(Map.Entry<String, Integer> q1, Map.Entry<String, Integer> q2) {
        if (q1.getValue().equals(q2.getValue())) return q1.getKey().compareTo(q2.getKey());
        return q2.getValue() - q1.getValue();
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