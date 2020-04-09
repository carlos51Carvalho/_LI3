#include <stdio.h>
#include <string.h>
#include <stdlib.h>


#include "faturacao.h"

typedef struct mes{
	double fN;
	double fP;
	int vN;
	int vP;
} Mes ;

/* Mes size = 12 */
typedef struct fil{
	int used ;
	Mes *mes;
} Fil;


typedef struct prd{
	char *pid;
	Fil *fil;
} Prd;


typedef struct bucketv{
	int size;
	Prd *arr;
} Bucketv ;


typedef struct fat{
	Bucketv *tbl;
} Fat;




   /**
    * @brief Função que retorna o indice de um determinado produto da estrutura Fat
    *
    * @param Fat *fat                            Tabela de onde é retirado o cliente
    * @param *productID                          O produto a ser procurado
    *
    * @return int                                Retorno do indice deste na Fat
    */
int getPosicaoProd(Fat *fat,char *productID){
	int hash = hashfat(productID);

	return existe_fat(fat->tbl[hash].arr, productID, fat->tbl[hash].size);
}

   /**
    * @brief Função que retorna o numero de vendas em N de um terminado produto
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int h                               O indice do produto na tabela
    * @param int pos                             O indice do produto dentro do seu respetivo array
    * @param int m                               O indice do mes
    * @param int f                               O indice da filial
    *
    * @return int                                Retorno do numero de vendas em N
    */
int getVendasN(Fat *fat,int h,int pos,int m,int f){
	int result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f].mes[m].vN;
	}
	return result;
}


   /**
    * @brief Função que retorna o numero de vendas em P de um terminado produto
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int h                               O indice do produto na tabela
    * @param int pos                             O indice do produto dentro do seu respetivo array
    * @param int m                               O indice do mes
    * @param int f                               O indice da filial
    *
    * @return int                                Retorno do numero de vendas em P
    */
int getVendasP(Fat *fat,int h,int pos,int m,int f){
	int result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f].mes[m].vP;
	}
	return result;
}

   /**
    * @brief Função que retorna a faturacao em N de um terminado produto
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int h                               O indice do produto na tabela
    * @param int pos                             O indice do produto dentro do seu respetivo array
    * @param int m                               O indice do mes
    * @param int f                               O indice da filial
    *
    * @return int                                Retorno do faturado em N
    */
double getFaturacaoN(Fat *fat,int h,int pos,int m,int f){
	double result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f].mes[m].fN;
	}
	return result;
}

   /**
    * @brief Função que retorna a faturacao em P de um terminado produto
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int h                               O indice do produto na tabela
    * @param int pos                             O indice do produto dentro do seu respetivo array
    * @param int m                               O indice do mes
    * @param int f                               O indice da filial
    *
    * @return int                                Retorno do faturado em P
    */    
double getFaturacaoP(Fat *fat,int h,int pos,int m,int f){
	double result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f].mes[m].fP;
	}
	return result;
}

   /**
    * @brief Função que retorna o tamanho de um array num determinado indice da tabela
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int key                             O indice do arr na tabela
    *
    * @return int                                Retorno do size do array
    */
int getSizeArrayP(Fat *f, int key){
	return f->tbl[key].size;
}

   /**
    * @brief Função que retorna se a filiaĺ foi "usada" ou não 
    *
    * Esta encontra-se usada se tiverem sido realizadas compras desse produto nessa filial
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int key                             O indice do array na tabela
    * @param int ip                              O indice do produto dentro do seu respetivo array
    * @param int fil                             O indice da filial
    *
    * @return int                                Retorno do faturado em N
    */  
int getFilialUsed(Fat *f, int key, int ip, int fil){
	int i=f->tbl[key].arr[ip].fil[fil].used;
	return i;
}

   /**
    * @brief Função que retorna a string correspodente ao produto nos indices dados
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int key                             O indice do produto na tabela
    * @param int ip                              O indice do produto dentro do seu respetivo array
    *
    * @return int                                Retorno do faturado em N
    */  
char* getProdFat(Fat *f, int key, int ip){
	return strdup(f->tbl[key].arr[ip].pid);
}


   /**
    * @brief Função que recebe uma string e subtrai ao primeiro elemnto da string a letra A
    *
    * @param char *cont                          String genérica
    *
    * @return int                                O resultado inteiro dessa subtração
    */
int hashfat(char *cont){
	int r = cont[0] - 'A';
	return r;
}


   /**
    * @brief Função que inicia uma estrutura Fat alocando espaço para todas as estruturas adjacentes
    *
    * @return Fat*                             Devolve a Fat inicializada
    */
Fat* initFat(){
	int i;
	Fat *h = malloc(sizeof(Fat));
	h->tbl = malloc(26 *sizeof(Bucketv));
	for ( i =0; i < 26 ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = NULL;
		
	}
	return h ;
}

   /**
    * @brief Funçao que destroi uma estrutura Fat libertando o espaço ocupado por esta
    *
    * @param Fat *f                              Fat previamente inicializada
    */

void destroiFat(Fat *f){
	int i,j,fi;
	for (i = 0; i < 26; i++){
		for (j = 0; j < f->tbl[i].size; j++){
			for (fi = 0; fi < 3; fi++){
				free(f->tbl[i].arr[j].fil[fi].mes);
			}
			free(f->tbl[i].arr[j].fil);
			free(f->tbl[i].arr[j].pid);
		}
		free(f->tbl[i].arr);
	}
	free(f->tbl);
	free(f);
}


   /**
    * @brief Função que acrescenta a uma Fat um produto
    *
    * Aplicando a função hash descobre a posicao correta desta na Fat e sucessivamente realocando espaço para o adicionar á mesma
    *
    *
    * @param Fat *f                              Fat previamente inicializada
    * @param char *cont                          String genérica (produto)
    *
    */
void acrescenta_prod(Fat *f, char *p){
	int i,j;
	int k = hashfat(p);
	int tam = f->tbl[k].size;
	f->tbl[k].arr = realloc(f->tbl[k].arr, (tam+1)*sizeof(Prd));
	f->tbl[k].arr[tam].pid = strdup(p);
	f->tbl[k].arr[tam].fil = malloc(3*sizeof(Fil));
	for (i = 0; i < 3; ++i)
	{
		f->tbl[k].arr[tam].fil[i].used = 0;
		f->tbl[k].arr[tam].fil[i].mes = malloc(12*sizeof(Mes));
		for (j = 0; j < 12; j++)
		{
			f->tbl[k].arr[tam].fil[i].mes[j].fN =0;
			f->tbl[k].arr[tam].fil[i].mes[j].fP =0;
			f->tbl[k].arr[tam].fil[i].mes[j].vN =0;
			f->tbl[k].arr[tam].fil[i].mes[j].vP =0;
		}
	}
	f->tbl[k].size++;
}


   /**
    * @brief Função que acrescenta a uma Fat vários produtos lidos de um array a strings
    *
    *
    * @param Fat *f                              Fat previamente inicializada
    * @param char **p                            Array de strings(produtos)
    * @param int Tam                             Size do array de strings
    *
    */
int acrescenta_prods (Fat *f, char **p, int tam ){
	int i;
	for (i = 0; i < tam; i++){
		acrescenta_prod(f, p[i]);
	}

	return i;
}



   /**
    * @brief Função que retorna o indice de um determinado produto da estrutura Fat caso ele exista -1 caso não
    *
    *
    * @param Prd *arr                            Array de Prd ( estrutura complementar de Fat)
    * @param char *procurado                     Produto procurado
    * @param int Tam                             Size do array de Prd
    *
    *
    * @return int r                              Indice do produto se encontrado 
    */
int existe_fat(Prd *arr, char *procurado, int Tam)
{
     int inf = 0;     
     int sup = Tam-1; 
     int meio;
     int r = -1;
     while (inf <= sup && r==-1)
     {
          meio = inf +(sup- inf)/2;
          if (strcmp(procurado, arr[meio].pid) ==0){
               	r = meio;
          }
          if (strcmp(procurado, arr[meio].pid)<0)
               sup = meio-1;
          else
               inf = meio+1;
     }
     return r; 
}


   /**
    * @brief Função que acrescenta a uma Fat informção que recebe como argumento ao respetivo produto. 
    *
    *
    * @param Fat *h                              Estrutua Fat
    * @param char *p                             Produto 
    * @param double pr                           Preco
    * @param int q                               Quantidade
    * @param char e                              Modo de compra
    * @param char *c                             Cliente
    * @param int m                               Mes
    * @param int f                               Filial
    *
    *
    */
void acrescentaFat(Fat *h, char*p, double pr, int q, char e, char *c, int m, int f){
	int k = hashfat(p);
	int tam = h->tbl[k].size;
	int  r = existe_fat(h->tbl[k].arr, p, tam);
	if (r >= 0){

		h->tbl[k].arr[r].fil[f-1].used = 1;
		if (e == 'N'){
			h->tbl[k].arr[r].fil[f-1].mes[m-1].vN++;
			h->tbl[k].arr[r].fil[f-1].mes[m-1].fN += pr*q;

		}
		else{
			h->tbl[k].arr[r].fil[f-1].mes[m-1].vP++;
			h->tbl[k].arr[r].fil[f-1].mes[m-1].fP += pr*q;
		}
	}
}



   /**
    * @brief Função que retorna um array de strings com os produtos que nunca foram comprados numa filial em especifico
    *
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int fil                             Filial
    * @param int tamp                            um size que a função vai alterar
    *
    * @return char**                             array de strings com os produtos
    */  
char** neverBoughtFil(Fat *f, int fil, int *tamp){
	int i,j,count =0;
	char **p = NULL;

	for (i = 0; i < 26 ; i++){
		for (j = 0; j < getSizeArrayP(f, i); j++){
			if (getFilialUsed(f,i,j,fil) == 0)
			{
				p = realloc (p,(count+1) *sizeof (char*));
				p[count] = getProdFat(f,i,j);
				count++;
			}
		}
	}
	*tamp = count;
	return p;
}

   /**
    * @brief Função que retorna um array de strings com os produtos que nunca foram comprados em nenhuma filial
    *
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int tamp                            um size que a função vai alterar
    *
    * @return char**                             array de strings com os produtos
    */  
char** neverBoughtAllFil(Fat *f, int *tamp){
	int i,j,count =0;
	char **p = NULL;

	for (i = 0; i < 26 ; i++){
		for (j = 0; j < getSizeArrayP(f, i); j++){
			if (getFilialUsed(f,i,j,0) == 0 && getFilialUsed(f,i,j,1) == 0  && getFilialUsed(f,i,j,2) == 0)
			{
				p = realloc (p,(count+1) *sizeof (char*));
				p[count] = getProdFat(f,i,j);
				count++;
			}
		}
	}
	*tamp = count;
	return p;
}


   /**
    * @brief Função que retorna o numero do produtos que nunca foram comprados
    *
    *
    * @param Fat *fat                            Estrutura Faturaçao
    *
    * @return int                                Numero de produtos nunca comprados
    */  
int ProdutosNaoComprados (Fat *f){
	int i,j,t,count=0;
	for(i=0;i<26;i++){
		t = f->tbl[i].size;
		for(j=0;j<t;j++){
			if(getFilialUsed(f,i,j,0) == 0 && getFilialUsed(f,i,j,1) == 0 && getFilialUsed(f,i,j,2) == 0) count ++;
		}
	}
	return count;
}



   /**
    * @brief Função que retorna a faturacao(numero de vendas e faturado) num intervalo de meses
    *
    *
    * @param Fat *fat                            Estrutura Faturaçao
    * @param int m1                              Primeiro mes
    * @param int m2                              Segundo mes
    * @param int *result                         Resultado a ser preenchido com o numero de vendas
    * @param double *result2                     Resultado a ser preenchido com o total faturado 
    *
    */ 
void FaturacaoeVendasIntervalo (Fat *f, int m1, int m2, int *result, double *result2){
	int i,j,fil,m,t,count=0;
	double count2 = 0;
	for(i =0; i<26; i++){
		t = f->tbl[i].size;
		
		for(j= 0; j<t; j++){
			
			for(fil =0; fil<3; fil++){
				if (getFilialUsed(f,i,j,fil) == 1){
					
					for( m =m1; m <= m2; m++){ 
						count2 += getFaturacaoP(f,i,j,m,fil) + getFaturacaoN(f,i,j,m,fil);
						count += getVendasP(f,i,j,m,fil) + getVendasN(f,i,j,m,fil);
					}

				}
			}
		}
	}
	*result=count;
	*result2=count2;
}