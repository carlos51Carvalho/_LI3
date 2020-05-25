package Model;

import java.io.Serializable;
import java.util.*;

public class Faturacao implements InterfaceFaturacao, Serializable {
    private Map<Integer, List<ProdFat>> faturacoes;


    /**
     * Construtor sem parametros
     */
    public Faturacao() {
        this.faturacoes = new HashMap<>();
        for (int i = 0; i < 26; i++)
            this.faturacoes.put(i, new ArrayList<>());
    }

    /***
     * Método que calcula a chave de um produto
     * @param p     codigo de produto
     * @return int com a chave
     */

    public int hashProd(String p) {
        return p.charAt(0) - 'A';
    }

    /**
     * Método que adiciona um produto a variavel faturações do bojeto
     * @param p     codigo de produto
     */
    public void addProd(String p,int nfiliais) {
        if(pBinaria(p,hashProd(p))==-1) {
            ProdFat prd = new ProdFat(p,nfiliais);
            faturacoes.get(hashProd(p)).add(prd);
        }
    }

    /**
     * Método que adiciona um set de produtos a variavel faturaçõoes do objeto
     * @param produtos      set de codigos de produto
     */
    public void addProds(Set<String> produtos,int nfiliais) {
        for (String p : produtos)
            addProd(p,nfiliais);
    }


    /**
     * Método que faz uma procura binaria numa lista de produtos
     * @param p     codigo de produto
     * @param k     int chave
     * @return posicao do produto na lista (-1 se nao existe)
     */
    public int pBinaria(String p, int k){
        List<ProdFat> f = faturacoes.get(k);
        int r = -1;
        int meio;
        int inicio = 0;
        int fim = f.size()-1;
        while (inicio <= fim && r==-1) {
            meio = (inicio + fim)/2;
            if (p.compareTo(f.get(meio).getProd()) == 0) {
                r = meio;
            }
            if (p.compareTo(f.get(meio).getProd()) < 0)
                fim = meio - 1;
            else
                inicio = meio + 1;
        }
        return r;
    }

    /**
     * Método que adiciona a faturação de uma venda á faturação
     * @param p     Array com parametros a venda
     */
    public void acrescentaFat(String[] p) {

        int ip = pBinaria(p[0],hashProd(p[0]));
        if (ip >= 0) {
            this.faturacoes.get(hashProd(p[0])).get(ip).setUsed(true);
            double pr = Double.parseDouble(p[1]);
            int q = Integer.parseInt(p[2]);
            int m = Integer.parseInt(p[5]);
            int f = Integer.parseInt(p[6]);
            int key = hashProd(p[0]);

            //this.faturacoes.get(key).get(ip).getFil().get(f).setUsed(1);
            this.faturacoes.get(key).get(ip).incFilialUsed(f);
            //System.out.println(this.faturacoes.get(hashProd(p[0])).get(ip).getPrd());
            if (p[3].equals("N")) {
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).addfN(pr * q);
                this.faturacoes.get(key).get(ip).addFN(f,m,pr*q);
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).incvN();
                this.faturacoes.get(key).get(ip).incVN(f,m);

            } else if (p[3].equals("P")){
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).addfP(pr * q);
                this.faturacoes.get(key).get(ip).addFP(f,m,pr*q);
                //this.faturacoes.get(key).get(ip).getFil().get(f).getFilF().get(m).incvP();
                this.faturacoes.get(key).get(ip).incVP(f,m);
            }

        }

    }

    /**
     * Método que devolve o numero de produtos comprados
     * @return  int com total de produtos
     */

    public int numeroProdUsados(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += (int) faturacoes.get(i).stream().filter(ProdFat::getUsed).count();
        }
        return count;
    }

    /**
     * Método que devolve numero de produtos nunca comprados
     * @return int com total de produtos nunca comprados
     */
    public int numeroProdNuncaUsados(){
        int count = 0;
        for (int i = 0; i<26; i++){
            count += faturacoes.get(i).size();
        }
        return count-numeroProdUsados();
    }

    /**
     * Get da Querie 1
     * @return Lista de produtos de resposta da querie 1
     */
    public List<String> getQuerie1() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (ProdFat p : faturacoes.get(i)) {
                if (!p.getUsed()) {
                    res.add(p.getProd());
                }
            }
        }
        return res;
    }


    /**
     * Método que calcula a faturação total por mes e filial
     * @param res       Map a preencher
     */
    public void getFatPorMesEFil(Map<Integer,Map<Integer,Map<String,Double>>> res){
        for (int i = 0; i<26; i++){
            for (ProdFat f : this.faturacoes.get(i)){
                f.getFatPorMesEFil(res);
            }
        }
    }



}