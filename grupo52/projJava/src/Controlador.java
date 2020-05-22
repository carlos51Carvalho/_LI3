import Model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
        boolean load = false;
        int value;
        v.printHeader();
        do{//chamar menu da view
            this.v.printmenu();

            //chamar do input
            System.out.println("\nEscolha a instrução: ");
            op = this.i.lerInt();
            //switch

            //processar no controlador
            switch (op){
                case 0:
                    v.printExit();
                    break;
                case 1:
                    load = case1();
                    break;
                case 2:
                    if (load) {
                       v.queriesEstatisticas(vendas.getNome(),vendas.getErrados(),vendas.getTprod(),vendas.getDprod(),vendas.getPnc(),
                               vendas.getTcl(),vendas.getTclc(),vendas.getTclsc(),vendas.getTvendaszero(),vendas.getFattotal());
                        v.printDone();
                    }
                    else v.printNotLoad();
                    break;

                case 3:
                    if (load){
                        v.querie_2_1(vendas.getRes());
                        v.printDone();
                    }
                    else v.printNotLoad();
                    break;

                case 4:
                    if (load) {
                        v.querie_2_2(vendas.getRes2());
                        v.printDone();
                    }
                    else v.printNotLoad();
                    break;
                case 5:
                    if (load) {
                        v.querie_2_3(vendas.getRes3());
                        v.printDone();
                    }
                    else v.printNotLoad();
                    break;
                case 6:
                    if (load) {
                       case6();
                    }
                    else v.printNotLoad();
                    break;

                case 7:
                    case7(load);
                    break;

                case 8:
                    case8(load);
                    break;
                case 9:
                    case9(load);
                    break;
                case 10:
                    case10(load);
                    break;
                case 11:
                    case11(load);
                    break;
                case 12:
                    case12(load);
                    break;
                case 13:
                    case13(load);
                    break;
                case 14:
                    case14(load);
                    break;
                case 15:
                    break;
                case 16:
                    break;
                default:
                    v.printError();
                    break;

        }

            } while(op!=0);

    }




    public void leituras(String fc, String fp, String fv) throws Exception {
        //Crono.start();
        clientes.ler_clientes(fc);
        produtos.ler_produtos(fp);
        fat.addProds(produtos.getSetDeProdutos());
        fil.addCls(clientes.getSetDeClientes());
        vendas.ler_vendas(fat, fil, clientes.getSetDeClientes(), produtos.getSetDeProdutos(), fv);
        v.printFilePaths(fc, fp, fv);
        v.printDone();
        //System.out.println(Crono.getTImeString());
    }


    public boolean case1() throws Exception {
        String fc,fp,fv;
        boolean load = false;
        boolean opl = true;
        int opleit;

        while (opl) {
            v.printOpLeitura();
            opleit = this.i.lerInt();
            //falta o load de um binário
            if (opleit == 1) {
                opl = false;
                try {
                    leituras("Dados_Iniciais/Clientes.txt", "Dados_Iniciais/Produtos.txt", "Dados_Iniciais/Vendas_1M.txt");
                    load = true;
                }
                catch (IOException e){
                    v.printErrorFIle(e.getMessage());
                }
            } else if (opleit == 2) {
                try {
                    opl = false;
                    v.fileNameC();
                    fc = this.i.lerString();
                    v.fileNameP();
                    fp = this.i.lerString();
                    v.fileNameV();
                    fv = this.i.lerString();
                    leituras(fc, fp, fv);
                    load = true;
                } catch (IOException e) {
                    v.printErrorFIle(e.getMessage());
                }
            } else {
                v.printError();
                opleit = this.i.lerInt();
            }
        }
        return load;
    }

    public void case6() throws Exception{
        List<String> prods= Queries.querie1(fat);

        int linhas=10;
        int size = prods.size();
        int totalpag = size/linhas ;
        if(size%linhas!=0)totalpag++;
        int pag=1;
        int op=pag;

        while(op!=0){
            if(op>0 && op<=totalpag){
                   pag=op;
            }
            v.querie1(prods,pag,linhas);

            if (op!=pag){
                v.printErrorPagina(op);
            }else v.printBarraN();

            v.insiraPag();
            op = this.i.lerInt();
        }

    }

    public int case7(boolean load) throws ValorInvalidoException {
        int mes;
        boolean valid = true;

        if (load) {
            while (valid) {
                v.printMes();
                mes = this.i.lerInt();
                try {
                    v.querie2(Queries.querie2(fil, mes), mes);
                    valid = false;
                    v.printDone();
                }catch (ValorInvalidoException e){
                    v.printErrorMes();
                }
            }
        } else v.printNotLoad();

        return 0;
    }

    public void case8(boolean load) throws ValorInvalidoException {
        String c;
        boolean valid = true;

        if (load) {
            while (valid) {
                v.printCliente();
                c = this.i.lerString();
                try {
                    v.querie3(Queries.querie3(fil, c), c);
                    valid = false;
                    v.printDone();
                }catch (ValorInvalidoException e){
                    v.printErrorCliente();
                }
            }
        } else v.printNotLoad();

    }

    public void case9(boolean load) throws ValorInvalidoException {
        String p;
        boolean valid = true;

        if (load) {
            while (valid) {
                v.printProduto();
                p = this.i.lerString();
                try {
                    v.querie4(Queries.querie4(fil, fat,p), p);
                    valid = false;
                    v.printDone();
                }catch (ValorInvalidoException e){
                    v.printErrorProduto();
                }
            }
        } else v.printNotLoad();
    }

    public void case10(boolean load) throws ValorInvalidoException {
        int linhas=10;

        String c;
        boolean valid = true;
        TreeSet<Map.Entry<String,Integer>> q6result;

        if (load) {
            while (valid) {
                v.printCliente();
                c = this.i.lerString();
                try {
                    q6result = Queries.querie5(c,fil);

                    int size = q6result.size();
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.querie5(q6result,c,pag,linhas);

                        if (op!=pag){
                            v.printErrorPagina(op);
                        }else v.printBarraN();

                        v.insiraPag();
                        op = this.i.lerInt();
                    }
                    valid = false;

                }catch (ValorInvalidoException e){
                    v.printErrorCliente();
                }
            }
        } else v.printNotLoad();

    }


    public void case11(boolean load) throws ValorInvalidoException {
        int linhas=10;
        int limite;
        boolean valid = true;
        TreeSet<Map.Entry<String, int[]>> q6result;
        if (load) {
            while (valid) {
                v.printLimite();
                limite = this.i.lerInt();
                try {

                    q6result = Queries.querie6(limite,fil);
                    valid = false;

                    int size = Math.min(q6result.size(), limite);
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.querie6(q6result,pag,linhas,limite);

                        if (op!=pag){
                            v.printErrorPagina(op);
                        }else v.printBarraN();

                        v.insiraPag();
                        op = this.i.lerInt();
                    }


                }catch (ValorInvalidoException e){
                    v.printErrorLimite();
                }
            }
        } else v.printNotLoad();

    }


    public void case12(boolean load){

        boolean valid = true;
        Map<Integer, TreeSet<Map.Entry <String,Double>>> q7;
        if (load){
            while (valid) {
                //try {
                    q7 = Queries.querie7(fil);

                    v.querie7(q7);
                    valid = false;
                    v.printDone();

//                }catch (ValorInvalidoException e) {
//                    v.printErrorMes();
//                }
            }

        }else v.printNotLoad();
    }


    public void case13(boolean load) throws ValorInvalidoException {
        int linhas=10;

        int limite;
        boolean valid = true;
        TreeSet<Map.Entry<String,Integer>> q8result;

        if (load) {
            while (valid) {
                    v.printLimite();
                limite = this.i.lerInt();
                try {
                    q8result = Queries.querie8(fil,limite);

                    int size = q8result.size();
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.querie8(q8result,limite,pag,linhas);

                        if (op!=pag){
                            v.printErrorPagina(op);
                        }else v.printBarraN();

                        v.insiraPag();
                        op = this.i.lerInt();
                    }
                    valid = false;

                }catch (ValorInvalidoException e){
                    v.printErrorLimite();
                }
            }
        } else v.printNotLoad();

    }




    public void case14(boolean load) throws ValorInvalidoException {
        int linhas=10;

        String p;
        int limite;
        boolean valid = true;
        TreeSet<Map.Entry<String,Double>> q9result;

        if (load) {
            while (valid) {
                v.printLimite();
                limite = this.i.lerInt();
                v.printProduto();
                p = this.i.lerString();
                try {
                    q9result = Queries.querie9(fil,fat,limite,p);

                    int size = q9result.size();
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.querie9(q9result,limite,p,pag,linhas);

                        if (op!=pag){
                            v.printErrorPagina(op);
                        }else v.printBarraN();

                        v.insiraPag();
                        op = this.i.lerInt();
                    }
                    valid = false;

                }catch (NotValideException e){
                    v.printErrorProduto();
                }catch (ValorInvalidoException es){
                    v.printErrorLimite();
                }
            }
        } else v.printNotLoad();

    }







}