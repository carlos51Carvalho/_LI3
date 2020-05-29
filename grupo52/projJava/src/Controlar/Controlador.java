package Controlar;


import Model.*;
import View.Vista;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Controlador {

    private Queries q;
    private Vista v;
    private Input i;

    public Controlador(){
        this.q = new Queries();
        this.v = new Vista();
        this.i = new Input();
    }

    public Controlador(Queries q, Vista v, Input i){
        this.q=q;
        this.v = v;
        this.i = i;
    }
    
    
    public void run() throws Exception {
        int op;
        boolean load = false;
        int value;

        //->leitura do ficheiro config
        String fc,fp,fv;
        int nfiliais;
        int nlinhasoutput;
        fc = "Dados_Iniciais/Clientes.txt";
        fp = "Dados_Iniciais/Produtos.txt";
        fv = "Dados_Iniciais/Vendas_3M.txt";
        nfiliais=3;
        nlinhasoutput=20;
        try {
            String st;
            String[] linhaPartida;
            File file = new File("configFile.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                linhaPartida = st.split(":", 2);
                switch(linhaPartida[0]){
                    case "Clientes":
                        fc=linhaPartida[1];
                        break;
                    case "Produtos":
                        fp=linhaPartida[1];
                        break;
                    case "Vendas":
                        fv=linhaPartida[1];
                        break;
                    case "Filiais":
                        nfiliais=Integer.parseInt(linhaPartida[1]);
                        break;
                    case "Linhas":
                        nlinhasoutput=Integer.parseInt(linhaPartida[1]);
                        break;
                    default:
                        break;
                }
            }
        }catch (IOException e){
            v.printErrorFIle(e.getMessage());
            try{
                PrintWriter p = new PrintWriter("configFile.txt");
                p.print("Clientes:"+fc+ "\nProdutos:"+fp+"\nVendas:" + fv + "\nFiliais:"+nfiliais+"\nLinhas:"+nlinhasoutput);
                p.flush();
                p.close();
            }catch (IOException n){
                v.printErrorFIle(n.getMessage());
            }
        }
        v.flush();

        v.printHeader();
        do{//chamar menu da view
            this.v.printmenu();

            //chamar do input
            System.out.println("\nEscolha a instrução: ");
            op = this.i.lerInt();
            //switch
            this.v.flush();
            //processar no controlador
            switch (op){
                case 0:
                    v.flush();
                    v.printExit();
                    break;
                case 1:
                    load = case1(load,fc,fp,fv,nfiliais);
                    v.pressioneEnter();
                    this.i.lerString();
                    break;
                case 2:
                    if (load) {
                       v.queriesEstatisticas(q.getNome(),q.getErrados(),q.getTprod(),q.getDprod(),q.getPnc(),
                               q.getTcl(),q.getTclc(),q.getTclsc(),q.getTvendaszero(),q.getFattotal());

                        v.pressioneEnter();
                        this.i.lerString();
                    }
                    else v.printNotLoad();
                    break;

                case 3:
                    if (load){
                        v.querie_2_1(q.getRes());

                        v.pressioneEnter();
                        this.i.lerString();
                    }
                    else v.printNotLoad();
                    break;

                case 4:
                    if (load) {
                        v.querie_2_2(q.getRes2());

                        v.pressioneEnter();
                        this.i.lerString();
                    }
                    else v.printNotLoad();
                    break;
                case 5:
                    if (load) {
                        v.querie_2_3(q.getRes3());

                        v.pressioneEnter();
                        this.i.lerString();
                    }
                    else v.printNotLoad();
                    break;
                case 6:
                    if (load) {
                       case6(nlinhasoutput);

                        v.pressioneEnter();
                        this.i.lerString();
                    }
                    else v.printNotLoad();
                    break;

                case 7:
                    case7(load);

                    v.pressioneEnter();
                    this.i.lerString();
                    break;

                case 8:
                    case8(load);

                    v.pressioneEnter();
                    this.i.lerString();
                    break;
                case 9:
                    case9(load);

                    v.pressioneEnter();
                    this.i.lerString();
                    break;
                case 10:
                    case10(load,nlinhasoutput);
                    break;
                case 11:
                    case11(load,nlinhasoutput);
                    break;
                case 12:
                    case12(load);

                    v.pressioneEnter();
                    this.i.lerString();
                    break;
                case 13:
                    case13(load,nlinhasoutput);
                    break;
                case 14:
                    case14(load,nlinhasoutput);
                    break;
                case 15:
                    case15(load,nlinhasoutput);
                    break;
                case 16:
                    q.gravarObj("dados.dat");

                    v.pressioneEnter();
                    this.i.lerString();
                    break;
                default:
                    v.printError();

                    v.pressioneEnter();
                    this.i.lerString();
                    break;
            }

            v.flush();
            this.v.flush();
        } while(op!=0);

    }





    public boolean case1(boolean load,String fc,String fp,String fv,int nfiliais) throws Exception {
        //boolean load = false;
        boolean opl = true;
        boolean escrever = true;
        int opleit=0;
        int escrita;

        while(opleit<1||opleit>3){
            v.printOpLeitura();
            opleit = this.i.lerInt();
            if(opleit<1||opleit>3)v.printError();
        }

        if (opleit == 1) {//ler binario
            try {
                Crono.start();
                q=q.lerObj("dados.dat");         //nome do ficheiro pode estar no config
                double time = Crono.stop();
                //View.Vista
                load = true;
            }catch (IOException e){
                v.printErrorFIle(e.getMessage());
            }

        }else{
            if(load){
                while (escrever) {
                    v.printEscrever();
                    escrita = this.i.lerInt();
                    if (escrita == 1){
                        //reiniciar modelo;
                        q.reiniciarModelo();
                        load = false;
                        escrever =false;
                    }
                    else if (escrita == 2) escrever = false;
                    else v.printError();
                }
            }
            if (opleit == 3) {
                try {
                    opl = false;
                    v.fileNameC();
                    fc = this.i.lerString();
                    v.fileNameP();
                    fp = this.i.lerString();
                    v.fileNameV();
                    fv = this.i.lerString();
                    Crono.start();
                    q.leituras(fc, fp, fv,nfiliais);
                    double time = Crono.stop();
                    v.printFilePaths(fc, fp, fv, time);
                    load = true;
                } catch (IOException e) {
                    v.printErrorFIle(e.getMessage());
                }
            }else{
                try {
                    Crono.start();
                    q.leituras(fc, fp, fv,nfiliais);
                    double time = Crono.stop();
                    v.printFilePaths(fc, fp, fv, time);
                    load = true;
                } catch (IOException e) {
                    v.printErrorFIle(e.getMessage());
                }
            }
        }

        return load;
    }

    public void case6(int linhas){
        Crono.start();
        List<String> prods= q.querie1();
        double time = Crono.stop();


        //int linhas=10;
        int size = prods.size();
        int totalpag = size/linhas ;
        if(size%linhas!=0)totalpag++;
        int pag=1;
        int op=pag;

        while(op!=0){
            if(op>0 && op<=totalpag){
                   pag=op;
            }
            v.flush();
            v.querie1(prods,pag,linhas, time);

            if (op!=pag){
                v.printErrorPagina(op);
            }else v.printBarraN();

            v.insiraPag();
            op = this.i.lerInt();
        }

    }

    public void case7(boolean load){
        int mes;
        boolean valid = true;

        if (load) {
            while (valid) {
                v.printMes();
                mes = this.i.lerInt();
                try {
                    Crono.start();
                    Map<Integer,int[]> q2 =q.querie2(mes);
                    double time = Crono.stop();
                    v.flush();
                    v.querie2(q2, mes, time);
                    valid = false;
                    v.printDone();
                }catch (ValorInvalidoException e){
                    v.printErrorMes();
                }
            }
        } else v.printNotLoad();
    }

    public void case8(boolean load){
        String c;
        boolean valid = true;

        if (load) {
            while (valid) {
                v.printCliente();
                c = this.i.lerString();
                try {
                    Crono.start();
                    Map<Integer,double[]> q3 = q.querie3(c);
                    double time = Crono.stop();
                    v.flush();
                    v.querie3(q3, c, time);
                    valid = false;
                    v.printDone();
                }catch (ValorInvalidoException e){
                    v.printErrorCliente();
                }
            }
        } else v.printNotLoad();

    }

    public void case9(boolean load){
        String p;
        boolean valid = true;

        if (load) {
            while (valid) {
                v.printProduto();
                p = this.i.lerString();
                try {
                    Crono.start();
                    Map<Integer,double[]> q4 = q.querie4(p);
                    double time = Crono.stop();
                    v.flush();
                    v.querie4(q4, p, time);
                    valid = false;
                    v.printDone();
                }catch (ValorInvalidoException e){
                    v.printErrorProduto();
                }
            }
        } else v.printNotLoad();
    }

    public void case10(boolean load,int linhas){
        //int linhas=10;

        String c;
        boolean valid = true;
        TreeSet<Map.Entry<String,Integer>> q5result;

        if (load) {
            while (valid) {
                v.printCliente();
                c = this.i.lerString();
                try {
                    Crono.start();
                    q5result = q.querie5(c);
                    double time = Crono.stop();

                    int size = q5result.size();
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.flush();
                        v.querie5(q5result,c,pag,linhas,time);

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


    public void case11(boolean load,int linhas){
        //int linhas=10;
        int limite;
        boolean valid = true;
        TreeSet<Map.Entry<String, int[]>> q6result;
        if (load) {
            while (valid) {
                v.printLimite();
                limite = this.i.lerInt();
                try {
                    Crono.start();
                    q6result = q.querie6(limite);
                    double time = Crono.stop();
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
                        v.flush();
                        v.querie6(q6result,pag,linhas,limite, time);

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
                    Crono.start();
                    q7 = q.querie7();
                    double time = Crono.stop();
                    v.flush();
                    v.querie7(q7,time);
                    valid = false;
                    v.printDone();

//                }catch (ValorInvalidoException e) {
//                    v.printErrorMes();
//                }
            }

        }else v.printNotLoad();
    }


    public void case13(boolean load,int linhas){
        //int linhas=10;

        int limite;
        boolean valid = true;
        TreeSet<Map.Entry<String,Integer>> q8result;

        if (load) {
            while (valid) {
                v.printLimite();
                limite = this.i.lerInt();
                try {
                    Crono.start();
                    q8result = q.querie8(limite);
                    double time = Crono.stop();

                    int size = Math.min(q8result.size(), limite);
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.flush();
                        v.querie8(q8result,limite,pag,linhas , time);

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


    public void case14(boolean load,int linhas){
        //int linhas=10;

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
                    Crono.start();
                    q9result = q.querie9(limite,p);
                    double time = Crono.stop();

                    int size = Math.min(q9result.size(), limite);
                    int totalpag = size/linhas ;
                    if(size%linhas!=0)totalpag++;
                    int pag=1;
                    int op=pag;

                    while(op!=0){
                        if(op>0 && op<=totalpag){
                            pag=op;
                        }
                        v.flush();
                        v.querie9(q9result,limite,p,pag,linhas, time);

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

    public void case15(boolean load,int linhas){
        boolean valid = true;
        //Map<Integer,Map<Integer,Map<String,Double>>> querie10
        Map<Integer,Map<Integer,Map<String,Double>>> q10result;

        if (load) {
            Crono.start();
            q10result = q.querie10();
            double time = Crono.stop();
            int op=1;
            int m=op;
            int oppag=op;

            while(op!=0){
                v.printMes();
                op = this.i.lerInt();
                if(op>0 && op<=12){
                    oppag =1;
                    while(oppag!=0) {
                        int size = 0;
                        for (Map<String, Double> aux : q10result.get(op).values()) {
                            size = Math.max(size, aux.size());
                        }
                        //int nfil = q10result.get(op).size();
                        int totalpag = size / linhas;
                        if (size % linhas != 0) totalpag++;


                        v.insiraPag();
                        oppag = this.i.lerInt();
                        if (oppag > 0 && oppag <= totalpag) {
                            v.flush();
                            v.querie10(q10result.get(m),m,oppag,totalpag,linhas,size,time);
                            v.printBarraN();
                        } else if (oppag != 0) {
                            v.printErrorPagina(oppag);
                        }
                    }
                }else if(op!=0){
                    v.printErrorMes();
                }

            }
        } else v.printNotLoad();
    }

}