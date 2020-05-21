package Model;
/**
 * Write a description of class Model.ComparatorProduto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class ComparatorProduto implements Comparator<Produto>
{

    public int compare(Produto c1, Produto c2){
        return c1.getProduto().compareTo(c2.getProduto());
    }
}