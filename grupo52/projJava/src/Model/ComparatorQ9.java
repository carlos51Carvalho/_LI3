package Model;

import java.util.Comparator;
import java.util.Map;

public class ComparatorQ9 implements Comparator<Map.Entry<String,Double>> {

    @Override
    public int compare(Map.Entry<String, Double> q1, Map.Entry<String, Double> q2) {
        if (q1.getValue().equals(q2.getValue())) return q1.getKey().compareTo(q2.getKey());
        return (int) (q2.getValue() - q1.getValue());
    }
}