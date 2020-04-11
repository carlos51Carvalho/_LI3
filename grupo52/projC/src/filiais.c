#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "filiais.h"


typedef struct qprd{
	char *pid;
	int qN;
	int qP;
	double gN;  
	double gP;    
} Qprd ;


typedef struct mesf{
	int size;
	Qprd *prs;
} Mesf ;

typedef struct fili{
	int used;
	Mesf *mes;
} Fili;


typedef struct cl{
	char *cid;
	Fili *fil;
} Cl;

typedef struct bucketf{
	int size;
	Cl *arr;
} Bucketf ;


typedef struct filial{
	Bucketf *tbl;
} Filial;
	/**
     * @brief Função que verifica se uma filial é usada
     *
     * caso sim dá retorno 1, caso contrário 0
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial que pretende verificar
     * @return int 
     */

int getFilUsed(Filial *f, int k, int ip, int fil){
	return f->tbl[k].arr[ip].fil[fil].used;
}

	/**
     * @brief Função que devolve um cliente
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @return char*
     */

char* getCLiente(Filial *f, int k, int ip){
	return strdup(f->tbl[k].arr[ip].cid);
}

	/**
     * @brief Função que devolve o tamanho do array num determinado indice da tabela
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @return int 
     */
int getSizeArrClient(Filial *f, int k){
	return f->tbl[k].size;
}

	/**
     * @brief Função que devolve um array de clientes começados por uma determinada letra.
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @return Cl* 
     */

Cl* getArrByLetter(Filial *f, int key){
	return f->tbl[key].arr;
}

	/**
     * @brief Função que devolve o tamanho do array de produtos num determinado mês
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial 
     * @param int m                            Indice do mês 
     * @return int 
     */
int getSizeQprd(Filial *f, int k, int id, int fil, int m){
	return f->tbl[k].arr[id].fil[fil].mes[m].size;
}

	/**
     * @brief Função que devolve q quantidade de compras em N para um determinado produto num mês
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial 
     * @param int m                            Indice do mês 
     * @param int pid                          Indice do produto.
     * @return int 
     */
int getQuantN(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].qN;
}

	/**
     * @brief Função que devolve a quantidade de compras em P para um determinado produto num mês
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial 
     * @param int m                            Indice do mês 
     * @param int pid                          Indice do produto
     * @return int 
     */
int getQuantP(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].qP;
}

    /**
     * @brief Função que devolve gasto total em P de um determinado produto
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial 
     * @param int m                            Indice do mês 
     * @param int pid                          Indice do produto
     * @return int 
     */
int getGastoP(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].gP;
}

    /**
     * @brief Função que devolve o gasto total em N de um determinado produto
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial 
     * @param int m                            Indice do mês 
     * @param int pid                          Indice do produto
     * @return int 
     */
int getGastoN(Filial *f, int k, int id, int fil, int m, int pid){
	return f->tbl[k].arr[id].fil[fil].mes[m].prs[pid].gN;
}

    /**
     * @brief Função que devolve um produto
     *
     *
     * @param Filial *f                        Estrutura filiais
     * @param int k                            Indice do cliente na tabela
     * @param int ip                           Indice do cliente no array
     * @param int fil                          Indice da filial 
     * @param int m                            Indice do mês 
     * @param int p                            Indice do produto
     * @return int 
     */
char* getOneProd(Filial *f, int k, int id, int fil, int m, int p){
	return strdup(f->tbl[k].arr[id].fil[fil].mes[m].prs[p].pid);
}

   /**
    * @brief Função que recebe uma string e subtrai ao primeiro elemnto da string a letra A
    *
    * @param char *cont                          String genérica
    *
    * @return int                                O resultado inteiro dessa subtração
    */

int hashfil(char *cont){
	int r = cont[0] - 'A';
	return r;
}

   /**
    * @brief Função que inicia uma estrutura Filial alocando espaço para todas as estruturas adjacentes
    *
    * @return Filial*                             Devolve a Filial inicializada
    */

Filial* initFilial(){
	int i;
	Filial *h = malloc(sizeof(Filial));
	h->tbl = malloc(26 *sizeof(Bucketf));
	for ( i =0; i < 26 ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = NULL;
		
	}
	return h ;
}

   /**
    * @brief Funçao que destroi uma estrutura Filial libertando o espaço ocupado por esta
    *
    * @param Filial *f                              Filial previamente inicializada
    */

void destroiFilial(Filial *f){
	int i,j,fi,m,p,sizeQprod;
	for (i = 0; i < 26; i++){
		for (j = 0; j < f->tbl[i].size; j++){
			for (fi = 0; fi < 3; fi++){
				for (m = 0; m < 12; m++){
					sizeQprod=getSizeQprd(f, i, j,fi,m);
					for (p = 0; p <sizeQprod ; p++){
						free(f->tbl[i].arr[j].fil[fi].mes[m].prs[p].pid);
					}
					free(f->tbl[i].arr[j].fil[fi].mes[m].prs);
				}
				free(f->tbl[i].arr[j].fil[fi].mes);
			}
			free(f->tbl[i].arr[j].fil);
			free(f->tbl[i].arr[j].cid);
		}
		free(f->tbl[i].arr);
	}
	free(f->tbl);
	free(f);
}

   /**
    * @brief Função que acrescenta a uma Filial um cliente
    *
    * Aplicando a função hash descobre a posicao correta desta na Filial e sucessivamente realocando espaço para o adicionar á mesma
    *
    *
    * @param Filial *f                              Fat previamente inicializada
    * @param char *p                                String genérica (cliente)
    *
    */

void acrescenta_cl(Filial *f, char *p){
	int i,j;
	int k = hashfil(p);
	int tam = f->tbl[k].size;
	f->tbl[k].arr = realloc(f->tbl[k].arr, (tam+1)*sizeof(Cl));
	f->tbl[k].arr[tam].cid = strdup(p);
	f->tbl[k].arr[tam].fil = malloc(3*sizeof(Fili));
	for (i = 0; i < 3; ++i)
	{
		f->tbl[k].arr[tam].fil[i].used =0;
		f->tbl[k].arr[tam].fil[i].mes = malloc(12*sizeof(Mesf));
		for (j = 0; j < 12 ; j++)
		{
			f->tbl[k].arr[tam].fil[i].mes[j].size=0;
			f->tbl[k].arr[tam].fil[i].mes[j].prs = NULL;
		}
	}
	f->tbl[k].size++;
}

   /**
    * @brief Função que acrescenta a uma Filial vários clientes lidos de um array a strings
    *
    *
    * @param Filial *f                              Filial previamente inicializada
    * @param char **p                               Array de strings(clientes)
    * @param int Tam                                Size do array de strings
    *
    * @return int i                                 Número de clientes acrescentados
    */

int acrescenta_cls (Filial *f, char **p, int tam ){
	int i;
	for (i = 0; i < tam; i++){
		acrescenta_cl(f, p[i]);
	}

	return i;
}

   /**
    * @brief Função que retorna o indice de um determinado cliente da estrutura Filial caso ele exista, -1 caso não
    *
    *
    * @param Cl *arr                             Array de Cl ( estrutura complementar de Filial)
    * @param char *procurado                     Cliente procurado
    * @param int Tam                             Size do array de Cl
    *
    *
    * @return int r                              Indice do cliente se encontrado 
    */
int existe_fil(Cl *arr, char *procurado, int Tam)
{
     int inf = 0;     
     int sup = Tam-1; 
     int meio;
     int r=-1;
     while (inf <= sup && r==-1)
     {
          meio = inf +(sup- inf)/2;
          if (strcmp(procurado, arr[meio].cid) ==0){
               	r = meio;
          }

          if (strcmp(procurado, arr[meio].cid)<0)
               sup = meio-1;
          else
               inf = meio+1;
     }
     return r; 
}


   /**
    * @brief Função que retorna o indice de um determinado produto de um cliente caso ele exista, -1 caso não
    *
    *
    * @param Qprd *arr                            Array de Qprd ( estrutura complementar de Filial)
    * @param char *procurado                      Produto procurado
    * @param int Tam                              Size do array de Qprod
    *
    *
    * @return int r                               Indice do produto se encontrado 
    */
int existe_prod(Qprd *arr, char *procurado, int Tam)
{
	int i,r=-1;
     for (i = 0; i < Tam && r == -1; i++){
     	if (strcmp(arr[i].pid, procurado)==0){
     		r=i;
     	}
     }
     return r; 
}

   /**
    * @brief Função que acrescenta um produto a um cliente na Filial
    *
    *
    * @param Filial *h                            Estrutua Filial
    * @param char *p                              Produto 
    * @param int it                               Indice do cliente na tabela
    * @param int a                                Indice do cliente no array
    * @param int f                                Filial
    * @param int m                                Mes
    * @param char e                               Modo de compra
    * @param int qnt                              Quantidade
    * @param int preco                            Preço
    *
    *
    */

void acrescentaPtoFil(Filial *h, char *p, int it, int a, int f, int m, char e, int qnt, int preco){
	int size = h->tbl[it].arr[a].fil[f].mes[m].size;

	h->tbl[it].arr[a].fil[f].mes[m].prs = realloc(h->tbl[it].arr[a].fil[f].mes[m].prs, (size+1)*sizeof(Qprd));
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].pid = strdup(p);

	h->tbl[it].arr[a].fil[f].mes[m].prs[size].qN = 0;
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].qP = 0;
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].gN = 0;
	h->tbl[it].arr[a].fil[f].mes[m].prs[size].gP = 0;

	if (e == 'N'){
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].qN += qnt;
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].gN += qnt * preco;
	}
	else{
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].qP += qnt;
		h->tbl[it].arr[a].fil[f].mes[m].prs[size].gP += qnt * preco;
	}
	h->tbl[it].arr[a].fil[f].mes[m].size++;
}

   /**
    * @brief Função que acrescenta um produto a um cliente na Filial
    *
    *
    * @param Filial *h                            Estrutua Filial
    * @param char *p                              Produto 
    * @param double pr                            Preço
    * @param int q                                Quantidade
    * @param char e                               Modo de compra
    * @param char *c                              Cliente
    * @param int m                                Mes
    * @param int f                                Filial
    *
    *
    */


void acrescentaFil(Filial *h, char*p, double pr, int q, char e, char *c, int m, int f){
	int k = hashfil(c);
	int tam = getSizeArrClient(h,k);
	int r= existe_fil(getArrByLetter(h,k), c, tam);
	int pi=0, t=0;
	if (r >= 0){
		t = h->tbl[k].arr[r].fil[f-1].mes[m-1].size;
		pi = existe_prod(h->tbl[k].arr[r].fil[f-1].mes[m-1].prs, p, t);
		h->tbl[k].arr[r].fil[f-1].used=1;

		if (pi < 0){
			acrescentaPtoFil(h, p,k,r,f-1,m-1,e,q, pr);
		}else{
			if (e == 'N'){
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].qN += q;
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].gN += q * pr;
			}
			else{
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].qP += q;
				h->tbl[k].arr[r].fil[f-1].mes[m-1].prs[pi].gP += q * pr;
			}
		}
	}
}

   /**
    * @brief Função que retorna um array de strings com os clientes que compraram em todas as filiais
    *
    *
    * @param Filial *f                           Estrutura Filial
    * @param int *tam                            Um size que a função vai alterar
    *
    * @return char**                             array de strings com os clientes
    */  

char** ClientsOfAllBranches (Filial *f, int *tam){
	int i,j,count =0;
	char **c = NULL;
	for(i=0; i<26; i++)
	{
		int t= f->tbl[i].size;
		for(j=0; j<t;j++)
		{
			if(getFilUsed(f,i,j,0)==1 && getFilUsed(f,i,j,1)==1 && getFilUsed(f,i,j,2)==1){
				c = realloc (c,(count+1) *sizeof (char*));
				c[count] = getCLiente(f,i,j);
				count++;
			}
		}
	}
	*tam = count;
	return c;
}

   /**
    * @brief Função que retorna um array de strings com os clientes que nunca fizeram compras
    *
    *
    * @param Filial *f                            Estrutura Filial
    *
    * @return char**                              array de strings com os clientes
    */  

int ClientesSemCompras (Filial *f){
	int i,j,t,count =0;
	for(i=0;i<26;i++){
		t = f->tbl[i].size;
		for(j =0; j<t; j++){
			if(getFilUsed(f,i,j,0)==0 && getFilUsed(f,i,j,1)==0 && getFilUsed(f,i,j,2)==0) count++;
		}
	}
	return count;
}


   /**
    * @brief Função que retorna a quantidade total de produtos comprados num mês
    *
    *
    * @param Filial *f                            Estrutura Filial
    * @param char *clienteID                      Cliente
    * @param int fil                              Filial
    * @param int mes                              Mês
    *
    * @return int                                 Quantidade total
    */  

int QuantidadesUmClientePorMes(Filial *f, char *clienteID ,int fil, int mes){
	int i, k = hashfil(clienteID);
	int r = existe_fil(getArrByLetter(f,k), clienteID, getSizeArrClient(f,k));
	int result =0;

	if (r >= 0){
		for (i = 0; i < getSizeQprd(f,k,r,fil,mes); i++)
		{
			result += getQuantN(f,k,r,fil,mes,i) + getQuantP(f,k,r,fil,mes,i);
		}
	}
	return result;
}
