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


Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month){
	int h=hashfat(productID);
	int posProd = getPosicaoProd(sgv->fat,productID);
	Q3 q3 = malloc(sizeof(Q3));

	q3->vN1=getVendasN(sgv->fat,h,posProd,month,1);
	q3->vP1=getVendasP(sgv->fat,h,posProd,month,1);
	q3->fN1=getFaturacaoN(sgv->fat,h,posProd,month,1);
	q3->fP1=getFaturacaoP(sgv->fat,h,posProd,month,1);


	q3->vN2=getVendasN(sgv->fat,h,posProd,month,2);
	q3->vP2=getVendasP(sgv->fat,h,posProd,month,2);
	q3->fN2=getFaturacaoN(sgv->fat,h,posProd,month,2);
	q3->fP2=getFaturacaoP(sgv->fat,h,posProd,month,2);

	q3->vN3=getVendasN(sgv->fat,h,posProd,month,3);
	q3->vP3=getVendasP(sgv->fat,h,posProd,month,3);
	q3->fN3=getFaturacaoN(sgv->fat,h,posProd,month,3);
	q3->fP3=getFaturacaoP(sgv->fat,h,posProd,month,3);

	return q3;
}

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


Q5 getClientsOfAllBranches(SGV sgv){
	Q5 q5 = malloc(sizeof(Q5));
	q5->tam = 0;
	q5->c = NULL;
	int j = 0;
	q5->c= ClientsOfAllBranches(sgv->fil, &j );
	q5->tam = j;

	for(int i=0; i< q5->tam ;i++){
		printf("%s\n", q5->c[i]);
	}
	return q5;
}



Q8 getSalesAndProfif(SGV sgv, int minMonth, int maxMonth){
	Q8 q8 = malloc(sizeof(Q8));

	int v=0;
	double f=0;

	FaturacaoeVendasIntervalo(sgv->fat,minMonth, maxMonth, &v, &f);

	q8->v=v;
	q8->f=f;

	return q8;
}


