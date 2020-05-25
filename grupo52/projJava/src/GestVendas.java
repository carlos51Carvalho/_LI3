import Controlar.Controlador;
import Controlar.Input;
import Model.*;
import View.Vista;

public class GestVendas {

    public static void main (String[] args) throws Exception {
        //init modelo
        InterfaceClientes cl = new Clientes3();
        InterfaceProdutos pr = new Produtos3();
        InterfaceFiliais filiais = new Filiais();
        InterfaceFaturacao faturacao = new Faturacao();
        Vendas vendas = new Vendas();

        Queries q = new Queries(cl,pr,faturacao,filiais,vendas);

        //init vista
        Vista v = new Vista();
        Input i = new Input();


        //init controlador
        Controlador c = new Controlador(q, v, i);
        c.run();

    }

}
