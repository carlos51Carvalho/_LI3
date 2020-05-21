import Model.*;

import java.io.IOException;

public class Controlador {

    private InterfaceClientes clientes;
    private InterfaceProdutos produtos;
    private InterfaceFaturacao fat;
    private InterfaceFiliais fil;
    private Vista v;
    private Input i;
    private Vendas vendas;


    public Controlador(InterfaceClientes cl, InterfaceProdutos pr, InterfaceFaturacao fat, InterfaceFiliais fil, Vista v, Input i){
        this.clientes = cl;
        this.produtos = pr;
        this.fat = fat;
        this.fil = fil;
        this.v = v;
        this.i = i;
        this.vendas = new Vendas();

    }
    
    
    public void run() throws Exception {
        int op = 1;
        v.printHeader();
        do{//chamar menu da view
            this.v.printmenu();

            //chamar do input
            System.out.println("\nEscolha a instrução: ");
            op = this.i.lerInt();
            //switch

            //processar no controlador
            executeChoice(op);

            } while( op!=0);

    }


    public void executeChoice(int op) throws Exception {
        int limite;
        String fc,fp,fv;
        String cl;
        String pr;
        boolean load = false;
        int opleit;

        switch (op){
            case 0:
                 v.printExit();
            case 1:
                while (true) {
                    v.printOpLeitura();
                    opleit = this.i.lerInt();
                    if (opleit == 1) {
                        Crono.start();
                        clientes.ler_clientes("Dados_Iniciais/Clientes.txt");
                        produtos.ler_produtos("Dados_Iniciais/Produtos.txt");
                        fat.addProds(produtos.getSetDeProdutos());
                        fil.addCls(clientes.getSetDeClientes());
                        vendas.ler_vendas(fat, fil, clientes.getSetDeClientes(), produtos.getSetDeProdutos(), "Dados_Iniciais/Vendas_1M.txt");
                        v.printDone();
                        Crono.getTImeString();
                        break;
                    } else if (opleit == 2) {
                        try {

                            v.fileNameC();
                            fc = this.i.lerString();
                            v.fileNameP();
                            fp = this.i.lerString();
                            v.fileNameV();
                            fv = this.i.lerString();
                            clientes.ler_clientes(fc);
                            produtos.ler_produtos(fp);
                            fat.addProds(produtos.getSetDeProdutos());
                            fil.addCls(clientes.getSetDeClientes());
                            vendas.ler_vendas(fat, fil, clientes.getSetDeClientes(), produtos.getSetDeProdutos(), fv);
                            v.printFilePaths(fc, fp, fv);
                            v.printDone();
                            break;
                        }catch (IOException e){
                            v.printErrorFIle(e.getMessage());
                        }

                    } else {
                        v.printError();
                        opleit = this.i.lerInt();
                    }
                }
            case 2:



        }
    }

}