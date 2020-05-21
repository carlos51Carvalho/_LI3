import Model.*;

public class GestVendas {

    public static void main (String[] args) throws Exception {
        //init modelo
        InterfaceClientes cl = new Clientes3();
        InterfaceProdutos pr = new Produtos3();
        InterfaceFiliais filiais = new Filiais();
        InterfaceFaturacao faturacao = new Faturacao();

        //init vista
        Vista v = new Vista();
        Input i = new Input();


        //init controlador
        Controlador c = new Controlador(cl, pr, faturacao, filiais, v, i);
        c.run();

    }

}
