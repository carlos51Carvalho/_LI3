package Model;
/**
 * Write a description of class Model.ComparatorCliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class ComparatorCliente implements Comparator<Cliente>
{

    public int compare(Cliente c1, Cliente c2){
        return c1.compareTo(c2);
    }
}