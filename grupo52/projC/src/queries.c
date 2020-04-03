#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"
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
	printf("%s\n", sgv->fat->tbl[3].arr[2].pid );
	printf("%f\n", sgv->fat->tbl[3].arr[2].fil[1].mes[4].fP );
	printf("%d\n", sgv->fat->tbl[3].arr[2].fil[1].mes[4].vP );
	printf("%f\n", sgv->fat->tbl[0].arr[0].fil[2].mes[4].fN );
	printf("%d\n\n\n", sgv->fat->tbl[0].arr[0].fil[2].mes[4].vN );

	printf("%s\n", sgv->fil->tbl[0].arr[0].cid );
	printf("%d\n", sgv->fil->tbl[0].arr[0].fil[0].mes[0].size);
	printf("%s\n", sgv->fil->tbl[0].arr[10].fil[0].mes[0].prs[0].pid);
	printf("%d\n", sgv->fil->tbl[6].arr[3].fil[2].mes[7].prs[1].qP );
	printf("%d\n\n\n", sgv->fil->tbl[0].arr[0].fil[2].mes[7].prs[0].qN );



	printf("%d\n",p );
	printf("%d\n",c );
	printf("%d\n",d );
	printf("%d\n",e );
	printf("%d\n",v );

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

int getCenas(int i){
	if(i >0) return i;
}

/*
int vendas_emMes(SGV sgv, char *productID,int month, double aux[], int f){
	int kp = hash(productID);
	int t = sgv->fat->fil[f].p[kp].size;
	int tv=0;
	int cn=0;
	int cp=0;
	for (int i = 0; i < t; i++){
		if(strcmp(sgv->fat->fil[f].p[kp].arr[i].p, productID)==0 && sgv->fat->fil[f].p[kp].arr[i].m == month){
			tv++;
			if(sgv->fat->fil[f].p[kp].arr[i].e == 'N'){
				cn += sgv->fat->fil[f].p[kp].arr[i].pr; 	
			}
			else{ 
				cp += sgv->fat->fil[f].p[kp].arr[i].pr;}
		}
	}
	aux[0] = cn;
	aux[1] = cp;
	return tv;
}
int getProductsSalesAndProfit( SGV sgv, char *productID, int month, int porfil){
	double a1[2]={0}; 
	double a2[2]={0}; 
	double a3[2]={0};
	int tv1 = vendas_emMes(sgv, productID,month, a1,0);
	int tv2 = vendas_emMes(sgv, productID,month, a2,1);
	int tv3 = vendas_emMes(sgv, productID,month, a3,2);
	int tv = tv1+tv2+tv3;
	double n = a1[0]+a1[0]+a2[0];
	double p = a1[1]+a1[1]+a2[1];
	if(porfil == 1){
		printf("O total de vendas em f1 é %d\n", tv1);
		printf("Em N faturou-se %f\nEm P faturou-se %f\n ",a1[0],a1[1]);
		printf("O total de vendas em f2 é %d\n", tv2);
		printf("Em N faturou-se %f\nEm P faturou-se %f\n ",a2[0],a2[1]);
		printf("O total de vendas em f3 é %d\n", tv3);
		printf("Em N faturou-se %f\nEm P faturou-se %f\n ",a3[0],a3[1]);
	}
	else{
		printf("O total de vendas é %d\n", tv);
		printf("Em N faturou-se %f\nEm P faturou-se %f\n ",n,p);
	}
	return tv;
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
/*
int getClientsOfAllBranches(SGV sgv){
	int tam=0;
	for (int i = 0; i < 3; i++){
		printf("Na filial %d :\n",i+1 );
		for (int j = 0; j < 26; j++){
			tam = sgv->fat->fil[i].c[j].size;
			for (int k = 0; k < tam ; k++)
			{
				printf("%s\n",sgv->fat->fil[i].c[j].arr[k].cl );
			}
		}
	}
	return tam;
}
*/