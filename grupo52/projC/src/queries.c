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


//Querie 1

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




//Querie 2

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


// Querie 3



Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month){
	int h=hashfat(productID);
	int posProd = getPosicaoProd(sgv->fat,productID);

	Q3 q3 = malloc(sizeof(Q3));
	q3->vN1 = getVendasN(sgv->fat,h,posProd,month,1);
	q3->vP1 = getVendasP(sgv->fat,h,posProd,month,1);
	q3->fN1 = getFaturacaoN(sgv->fat,h,posProd,month,1);
	q3->fP1 = getFaturacaoP(sgv->fat,h,posProd,month,1);
	printf("%d\n%d\n%f\n%f\n\n", q3->vN1,q3->vP1,q3->fN1,q3->fP1 );

	q3->vN2 = getVendasN(sgv->fat,h,posProd,month,2);
	q3->vP2 = getVendasP(sgv->fat,h,posProd,month,2);
	q3->fN2 = getFaturacaoN(sgv->fat,h,posProd,month,2);
	q3->fP2 = getFaturacaoP(sgv->fat,h,posProd,month,2);
	printf("%d\n%d\n%f\n%f\n\n", q3->vN2,q3->vP2,q3->fN2,q3->fP2 );

	q3->vN3 = getVendasN(sgv->fat,h,posProd,month,3);
	q3->vP3 = getVendasP(sgv->fat,h,posProd,month,3);
	q3->fN3 = getFaturacaoN(sgv->fat,h,posProd,month,3);
	q3->fP3 = getFaturacaoP(sgv->fat,h,posProd,month,3);
	printf("%d\n%d\n%f\n%f\n\n", q3->vN3,q3->vP3,q3->fN3,q3->fP3 );


	return q3;
}





// Querie 4
/*
int getProductsNeverBought(SGV sgv , int branchID){
	char **p = NULL;
	int tam = 0;

	if(branchID > 0 && branchID <=3 ){
		tam = neverBoughtFil(sgv->fat, branchID -1, p, tam);
	}
	for (int i = 0; i < tam; i++){
		printf("%s\n",p[i] );
	}
	return tam;
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


