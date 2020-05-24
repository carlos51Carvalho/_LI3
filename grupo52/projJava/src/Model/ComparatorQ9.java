package Model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/**
 * Classe que contem o comparador para a Querie 9
 */
public class ComparatorQ9 implements Comparator<Map.Entry<String,Double>>, Serializable {

    @Override
    public int compare(Map.Entry<String, Double> q1, Map.Entry<String, Double> q2) {
        if (q1.getValue().equals(q2.getValue())) return q1.getKey().compareTo(q2.getKey());
        return (int) (q2.getValue() - q1.getValue());
    }
}
