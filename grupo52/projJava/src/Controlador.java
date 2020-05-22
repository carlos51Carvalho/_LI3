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
        boolean opl = true;
        int opleit;

        switch (op){
            case 0:
                 v.printExit();
            case 1:
                while (opl) {
                    v.printOpLeitura();
                    opleit = this.i.lerInt();
                    //falta o load de um binário
                    if (opleit == 1) {
                        opl = false;
                        leituras("Dados_Iniciais/Clientes.txt","Dados_Iniciais/Produtos.txt","Dados_Iniciais/Vendas_1M.txt");
                        load =true;
                    } else if (opleit == 2) {

                        try {
                            opl = false;
                            v.fileNameC();
                            fc = this.i.lerString();
                            v.fileNameP();
                            fp = this.i.lerString();
                            v.fileNameV();
                            fv = this.i.lerString();
                            leituras(fc,fp,fv);
                            load =true;
                        }catch (IOException e){
                            v.printErrorFIle(e.getMessage());
                        }
                    } else {
                        v.printError();
                        opleit = this.i.lerInt();
                    }
                }
            case 2:
                if (load) {
                    v.queriesEstatisticas(vendas.getNome(),vendas.getErrados(),vendas.getTprod(),vendas.getDprod(),vendas.getPnc(),
                            vendas.getTcl(),vendas.getTclc(),vendas.getTclsc(),vendas.getTvendaszero(),vendas.getFattotal());
                    v.printDone();
                }
                else v.printNotLoad();

            case 3:
                if (load){
                    v.querie_2_1(vendas.getRes());
                    v.printDone();
                }
                else v.printNotLoad();

            case 4:
                if (load) {
                    v.querie_2_2(vendas.getRes2());
                    v.printDone();
                }
                else v.printNotLoad();
            case 5:
                if (load) {
                    v.querie_2_3(vendas.getRes3());
                    v.printDone();
                }
                else v.printNotLoad();
            case 6:

        }
    }


    public void leituras(String fc, String fp, String fv) throws Exception {
        Crono.start();
        clientes.ler_clientes(fc);
        produtos.ler_produtos(fp);
        fat.addProds(produtos.getSetDeProdutos());
        fil.addCls(clientes.getSetDeClientes());
        vendas.ler_vendas(fat, fil, clientes.getSetDeClientes(), produtos.getSetDeProdutos(), fv);
        v.printFilePaths(fc, fp, fv);
        v.printDone();
        System.out.println(Crono.getTImeString());
    }

}