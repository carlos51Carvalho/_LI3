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

//	Q2

int getProductsStartedByLetter(SGV sgv, char letter){
	int k = hash(&letter);
	int tam = sgv->produtos->tbl[k].size;
	char **p = malloc(tam*sizeof(char*));
	for (int i = 0; i < tam; i++){
		p[i] = sgv->produtos->tbl[k].arr[i];
	}
	return tam;
}

// Q3
Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month){
	int h=hashfat(productID);
	int posProd = getPosicaoProd(sgv->fat,productID);
	Q3 q3 = malloc(sizeof(Q3));

	q3->vN1=getVendasN(sgv->fat,h,posProd,month-1,0);
	q3->vP1=getVendasP(sgv->fat,h,posProd,month-1,0);
	q3->fN1=getFaturacaoN(sgv->fat,h,posProd,month-1,0);
	q3->fP1=getFaturacaoP(sgv->fat,h,posProd,month-1,0);


	q3->vN2=getVendasN(sgv->fat,h,posProd,month-1,1);
	q3->vP2=getVendasP(sgv->fat,h,posProd,month-1,1);
	q3->fN2=getFaturacaoN(sgv->fat,h,posProd,month-1,1);
	q3->fP2=getFaturacaoP(sgv->fat,h,posProd,month-1,1);

	q3->vN3=getVendasN(sgv->fat,h,posProd,month-1,2);
	q3->vP3=getVendasP(sgv->fat,h,posProd,month-1,2);
	q3->fN3=getFaturacaoN(sgv->fat,h,posProd,month-1,2);
	q3->fP3=getFaturacaoP(sgv->fat,h,posProd,month-1,2);

	return q3;
}



// Q4

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


//Q5
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


//Q6

Q6 getClientsAndProductsNeverBoughtCount(SGV sgv){
	Q6 q6 = malloc(sizeof(Q6));
	q6->p = ProdutosNaoComprados(sgv->fat);
	q6->c = ClientesSemCompras(sgv->fil);
	return q6;
}

//Q7


Q7 getProductsBoughtByClient(SGV sgv, char *clientID){
	Q7 q7 = malloc(sizeof(Q7));
	q7->f = malloc(3*sizeof(BYFil));
	for (int i = 0; i < 3; i++){
		for (int j = 0; j < 12; j++)
		{
			q7->f[i].m[j] = QuantidadesUmClientePorMes(sgv->fil, clientID ,i, j);
		}
	}
	return q7;
} 


//Q8

Q8 getSalesAndProfif(SGV sgv, int minMonth, int maxMonth){
	Q8 q8 = malloc(sizeof(Q8));

	int v=0;
	double f=0;

	FaturacaoeVendasIntervalo(sgv->fat,minMonth, maxMonth, &v, &f);

	q8->v=v;
	q8->f=f;
	printf("%d %f\n",q8->v,q8->f );

	return q8;
}

//Q11

/*Q11 getTopSelledProducts(SGV sgv, int limit){
	Q11 q11 = malloc (sizeof(Q11));
	q11->cl = 0;
	q11->un = 0;

	q11->c1= malloc(limit * sizeof(char*));
	q11->c2= malloc(limit * sizeof(char*));
	q11->c3= malloc(limit * sizeof(char*));

    q11->c1[0]=getMaisVendido(sgv->fat, 0, q11->c1);
    q11->c2[0]=getMaisVendido(sgv->fat, 1, q11->c2);
    q11->c3[0]=getMaisVendido(sgv->fat, 2, q11->c3);
    
    for(int i=1; i<limit; i++){
   	    q11->c1[i] = getMaisVendidos(sgv->fat,0,q11->c1,i);
   	    q11->c2[i] = getMaisVendidos(sgv->fat,1,q11->c2,i);
   	    q11->c3[i] = getMaisVendidos(sgv->fat,2,q11->c3,i);
	}
	
	for(int i=0; i< limit ;i++){
		printf("%s\n", q11->c1[i]);	
	}
	
	return q11;

*/



//Q12

void swapq12(SpentOnP *args , int i1, int i2)
{
    char *tmp = args[i1].pid;
    double s = args[i1].spent;
    args[i1].pid = args[i2].pid;
    args[i1].spent = args[i2].spent;
    args[i2].pid = tmp;
    args[i2].spent = s;
}

/*Função que ordena um array de strings*/
void queriesort(SpentOnP *args, int len)
{
    unsigned int i, pvt=0;

    if (len <= 1)
        return;

    for (i=0;i<len-1;++i)
    {
        if (args[i].spent > args[len-1].spent)
            swapq12(args,i,pvt++);
    }
    swapq12(args,pvt,len-1);

    queriesort(args, pvt++);
    queriesort(args+pvt, len - pvt);
}




Q12 getClientTopProfitProducts(SGV sgv, char *clientID , int limit){
	int tam2 =0,size;
	Q12 q12 = malloc(sizeof(Q12));
	q12->tam = 0;
	q12->arr = NULL;
	int k = hashfat(clientID);
	int tam = getSizeArrClient(sgv->fil,k);
	int r= existe_fil(getArrByLetter(sgv->fil,k), clientID, tam);

	if (r>=0 && limit > 0){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 12; j++){
				tam2 = getSizeQprd(sgv->fil,k,r,i,j);
				if(tam2 != 0){
					for (int c = 0; c < tam2; c++){
						size = q12->tam;
						q12->arr = realloc(q12->arr, (size+1)*sizeof(SpentOnP));
						q12->arr[size].pid = strdup(getOneProd(sgv->fil,k,r,i,j,c));
						q12->arr[size].spent = getGastoP(sgv->fil,k,r,i, j, c)+ getGastoN(sgv->fil,k,r,i, j, c);
						q12->tam++;
					}
				}
			}
		}
		queriesort(q12->arr, q12->tam);
		//q12->tam = limit;

		//seria nice limpar todas as posiçoes daqui ate limite

		for (int i = 0; i < limit; i++){
				printf("%f %s\n", q12->arr[i].spent, q12->arr[i].pid );
		}	
	}
	return q12;
}
