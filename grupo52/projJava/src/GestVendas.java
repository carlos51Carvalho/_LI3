import Controlar.Controlador;
import Controlar.Input;
import Controlar.InterfaceControlador;
import Controlar.InterfaceInput;
import Model.*;
import View.InterfaceVista;
import View.Vista;

public class GestVendas {

    public static void main (String[] args){
        //init modelo
        InterfaceClientes cl = new Clientes3();
        InterfaceProdutos pr = new Produtos3();
        InterfaceFiliais filiais = new Filiais();
        InterfaceFaturacao faturacao = new Faturacao();
        InterfaceVendas vendas = new Vendas();

        InterfaceQueries q = new Queries(cl,pr,faturacao,filiais,vendas);

        //init vista
        InterfaceVista v = new Vista();
        InterfaceInput i = new Input();


        //init controlador
        InterfaceControlador c = new Controlador(q, v, i);
        c.run();

    }

}
