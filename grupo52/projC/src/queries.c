#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"
#include "faturacao.h"
#include "filiais.h"
#include "vendas.h"
#include "queries.h"



SGV initSGV(){
	SGV q = malloc(sizeof(SGV));
	q->produtos = initTab();
	q->clientes = initTab();
	//q->vendas = initTv();
	q->fat = initFat();
	q->fil = initFilial();
	return q;
}

// void distroySGV(SGV sgv)


SGV loadSGVFromFiles(SGV sgv, char *filespath ){
	int p, c,d,e ,v;
	p=c=d=e=v=0;
	p = ler_prod(sgv->produtos,filespath);
	c = ler_clientes(sgv->clientes, filespath);
	for (int i = 0; i < 26; ++i){
		d += acrescenta_prods(sgv->fat, sgv->produtos->tbl[i].arr, sgv->produtos->tbl[i].size );
	}
	for (int j = 0; j < 26; j++){
		e += acrescenta_cls(sgv->fil, sgv->clientes->tbl[j].arr, sgv->clientes->tbl[j].size );
	}
	v = ler_venda( sgv->fat, sgv->fil, sgv->clientes, sgv->produtos, filespath);

	return sgv;
}

int getProductsStartedByLetter(SGV sgv, char letter){
	int k = hash(&letter);
	int tam = sgv->produtos->tbl[k].size;
	char **p = malloc(tam*sizeof(char*));
	for (int i = 0; i < tam; i++){
		p[i] = sgv->produtos->tbl[k].arr[i];
		printf("%s\n", p[i]);
	}
	return tam;
}


/*double* getProductsSalesAndProfit( SGV sgv, char *productID, int month){
	int h=hashfat(productID);
	int posProd = getPosicaoProd(sgv->fat,productID);
	double result[12]={0};
	int a,b;
	printf("->%d\n", h);
	for (int i = 1; i <=3; ++i){
		a=getVendasN(sgv->fat,h,posProd,month,i);
		result[(i-1)*4+0]=(double) a;
		printf("%f\n", result[(i-1)*4+0]);

		b=getVendasP(sgv->fat,h,posProd,month,i);
		result[(i-1)*4+1]=(double) b;
		printf("%f\n", result[(i-1)*4+1]);

		result[(i-1)*4+2]=getFaturacaoN(sgv->fat,h,posProd,month,i);
		printf("%f\n", result[(i-1)*4+2]);

		result[(i-1)*4+3]=getFaturacaoP(sgv->fat,h,posProd,month,i);
		printf("%f\n", result[(i-1)*4+3]);
	}

	return result;
}*/

// falta ver a filial e provavelmente não está bem feito

/*int getProductsNeverBought(SGV sgv , int branchID){
	int i,j,tam, cont =0;
	char **p = malloc(sizeof(char*));
	for (i = 0; i < 26; i++){
		tam = sgv->produtos->tbl[i].size;
		for (j = 0; j< tam; j++){
			if (sgv->produtos->tbl[i].arr[j].used == 0){
				cont++;
				p = realloc(p,cont*sizeof(char*));
				p[cont-1]= sgv->produtos->tbl[i].arr[j].id;
			}
		}
	}
	for (i = 0; i < cont; ++i)
	{
		printf("%s\n",p[i] );
	}
	printf("%d dos produtos não foram usados\n",cont );
	return cont;
}
*/

int getClientsOfAllBranches(SGV sgv){
	int tam=0;
	char **c = malloc(sizeof(char*));
	int r = ClientsOfAllBranches(sgv->fil,c,tam);
	for(int i=0; i<r ;i++){
	printf("%s\n", c[i]);
	}
	return r;
}


