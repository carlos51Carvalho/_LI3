
/**
 * Write a description of class in_out here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
public class in_out
{
    public static void main() throws Exception{
        //List<String> prods = read("input_files/Produtos.txt");
        //System.out.println("Produtos: "+prods.size());
        Clientes cl = new Clientes();
        Produtos pr = new Produtos();
        //Clientes2 cl2 = new Clientes2();
        
        Clientes3 cl3 = new Clientes3();
        Produtos3 pr3 = new Produtos3();
        
        
        try{
            cl3.ler_clientes("input_files/Clientes.txt");
        }catch(IOException e){
            System.out.println(e);
        }
        
        
        for(String c:cl3.getSetDeClientes())
            System.out.println(c);
        
        pr3.ler_produtos("input_files/Produtos.txt");
       
        //for(String c:pr3.getSetDeProdutos())System.out.println(c);
        
        System.out.println(cl3.getSetDeClientes().size());
        System.out.println(pr3.getSetDeProdutos().size());
        
       
    }
}