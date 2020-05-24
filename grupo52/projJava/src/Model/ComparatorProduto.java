package Model;
/**
 * Write a description of class Model.ComparatorProduto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.Serializable;
import java.util.Comparator;
/**
 * Classe que contem um comparador para produtos
 */
public class ComparatorProduto implements Comparator<Produto>, Serializable
{

    public int compare(Produto c1, Produto c2){
        return c1.getProduto().compareTo(c2.getProduto());
    }
}