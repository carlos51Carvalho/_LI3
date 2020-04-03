#include <stdio.h>
#include <string.h>
#include <stdlib.h>


#include "faturacao.h"


int getPosicaoProd(Fat *fat,char *productID){
	int i,result=-1;
	int hash = hashfat(productID);
	for (i = 0; i < fat->tbl[hash].size; i++){
		if(strcmp(fat->tbl[hash].arr[i].pid, productID)==0)
			result=i;
	}
	return result;
}

int getVendasN(Fat *fat,int h,int pos,int m,int f){
	int result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].vN;
	}
	return result;
}
int getVendasP(Fat *fat,int h,int pos,int m,int f){
	int result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].vP;
	}
	return result;
}

double getFaturacaoN(Fat *fat,int h,int pos,int m,int f){
	double result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].fN;
	}
	return result;
}
double getFaturacaoP(Fat *fat,int h,int pos,int m,int f){
	double result=0;
	if(pos>=0){
		result=fat->tbl[h].arr[pos].fil[f-1].mes[m-1].fP;
	}
	return result;
}

int getSizeArrayP(Fat *f, int key){
	return f->tbl[key].size;
}

int getFilialUsed(Fat *f, int key, int ip, int fil){
	return f->tbl[key].arr[ip].fil[fil].used;
}

char* getProdFat(Fat *f, int key, int ip){
	return f->tbl[key].arr[ip].pid;
}


int hashfat(char *cont){
	int r = cont[0] - 'A';
	return r;
}



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

void acrescenta_prod(Fat *f, char *p){
	int k = hashfat(p);
	int tam = f->tbl[k].size;
	f->tbl[k].arr = realloc(f->tbl[k].arr, (tam+1)*sizeof(Prd));
	f->tbl[k].arr[tam].pid = strdup(p);
	f->tbl[k].arr[tam].fil = malloc(3*sizeof(Fil));
	for (int i = 0; i < 3; ++i)
	{
		f->tbl[k].arr[tam].fil[i].used = 0;
		f->tbl[k].arr[tam].fil[i].mes = malloc(12*sizeof(Mes));
		for (int j = 0; j < 12; j++)
		{
			f->tbl[k].arr[tam].fil[i].mes[j].fN =0;
			f->tbl[k].arr[tam].fil[i].mes[j].fP =0;
			f->tbl[k].arr[tam].fil[i].mes[j].vN =0;
			f->tbl[k].arr[tam].fil[i].mes[j].vP =0;
		}
	}
	f->tbl[k].size++;
}



int acrescenta_prods (Fat *f, char **p, int tam ){
	int i;
	for (i = 0; i < tam; i++){
		acrescenta_prod(f, p[i]);
	}

	return i;
}


int existe_fat(Prd *arr, char *procurado, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
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


/*
int neverBoughtFil(Fat *f, int fil, char **p, int tamp){
	int cont =0;

	for (int i = 0; i < 26 ; i++){
		
		for (int j = 0; j < getSizeArrayP(f, i); j++){
			if (getFilialUsed(f,i,j,fil) == 0)
			{
				p = realloc(p, (tamp+1)*sizeof(char*));
				p[tamp] = strdup(getProdFat(f,i,j));
				tamp++;
				cont++;
			}
		}
	}
	return cont;
}

*/
int ProdutosNaoComprados (Fat *f){
	int count=0;
	for(int i=0;i<26;i++){
		int t=f->tbl[i].size;
		for(int j=0;j<t;j++){
			if(getFilialUsed(f,i,j,0) == 0 && getFilialUsed(f,i,j,1) == 0 && getFilialUsed(f,i,j,2) == 0) count ++;
		}
	}
	return count;
}

void FaturacaoeVendasIntervalo (Fat *f, int m1, int m2, int *result, double *result2){
	int count=0;
	double count2 = 0;
	for(int i =0; i<26; i++){
		int t = f->tbl[i].size;
		
		for(int j= 0; j<t; j++){
			
			for(int fil =0; fil<3; fil++){
				if (getFilialUsed(f,i,j,fil) == 1){
					
					for(int m =m1 ; m<=m2; m++) count += getFaturacaoP(f,i,j,m,fil) + getFaturacaoN(f,i,j,m,fil);

					for(int m =m1; m<m2; m++){ 
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

/*void MaisVendidos (Fat *f, Filiais *fi, int fil){
	for(int i = 0; i<26; i++){
		int tam=0;
		int t= f->tbl[i].size;
		for(int j =0; j<t; j++){
			for(int m=0; m<12; m++){
				fi->qts = realloc (fi.qts, tam+t *sizeof (Qt));
				fi->qts[i+j].vendas += getVendasN(f,i,j,m,fil) + getVendasP(f,i,j,m,fil);
			}
			fi->qts[i+j].p = realloc(fi->qts[i+j].p,sizeof(char*));
			fi->qts[i+j].p = strdup (getProdFat(f,i,j));
			tam+=t;
		}
	}
}*/

/*void quicksortp(Qt **args, unsigned int len)
{
    unsigned int i, pvt=0;

    if (len <= 1)
        return;

    // swap a randomly selected value to the last node
    //swapc(args+((unsigned int)rand() % len), args+len-1);

    // reset the pivot index to zero, then scan
    for (i=0;i<len-1;++i)
    {
        if (strcmp(args[i], args[len-1]) < 0)
            swapc(args+i, args+pvt++);
    }

    // move the pivot value into its place
    swapc(args+pvt, args+len-1);

    // and invoke on the subsequences. does NOT include the pivot-slot
    quicksortc(args, pvt++);
    quicksortc(args+pvt, len - pvt);
}
*/

/*char* getMaisVendidos(Fat *f, int fil, char **c, int pos){
	int maior=0;
	char* pr=NULL;
	for(int i =0; i<26; i++){
		int t = f->tbl[i].size;
		
		for(int j=0; j<t; j++){
            
            for(int m=0; m<12; m++){
            	int n = getVendasN(f,i,j,m,fil);
            	int pro = getVendasP(f,i,j,m,fil);
            	int vendas = n+pro;
                if(vendas > maior){
                	for(int p =0; p<pos && strcmp(c[p], getProdFat(f,i,j)) !=0; p++){
                		if(p == pos){
                			maior = vendas;
                	        pr = strdup(getProdFat(f,i,j));
                		}
                	 }         
                }
            }
		}
	}
	return pr;
}

char* getMaisVendido(Fat *f, int fil, char **c){
	int maior=0;
	char* pr=NULL;
	for(int i =0; i<26; i++){
		int t = f->tbl[i].size;
		
		for(int j=0; j<t; j++){
            
            for(int m=0; m<12; m++){
            	int vendas = getVendasN(f,i,j,m,fil) + getVendasP(f,i,j,m,fil);
                if(vendas > maior){
                			maior = vendas;
                	        pr = strdup(getProdFat(f,i,j));
                }
            }
		}
	}

	return pr;
}*/



