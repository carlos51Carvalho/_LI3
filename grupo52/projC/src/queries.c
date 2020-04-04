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
	//q->cvalidos = 0;
	//q->pvalidos = 0;
	//q->vendas = initTv();
	q->fat = initFat();
	q->fil = initFilial();
	return q;
}

void distroySGV(SGV sgv){
	destroiTab(sgv->produtos);
	destroiTab(sgv->clientes);
	destroiFat(sgv->fat);
	destroiFilial(sgv->fil);
}


SGV loadSGVFromFiles(SGV sgv, char *clientsFilePath, char *productsFilePath, char *salesFilePath ){
	int p, c,d,e ,v;
	p=c=d=e=v=0;
	p = ler_prod(sgv->produtos,productsFilePath);
	c = ler_clientes(sgv->clientes, clientsFilePath);
	for (int i = 0; i < 26; ++i){
		d += acrescenta_prods(sgv->fat, sgv->produtos->tbl[i].arr, sgv->produtos->tbl[i].size );
	}
	for (int j = 0; j < 26; j++){
		e += acrescenta_cls(sgv->fil, sgv->clientes->tbl[j].arr, sgv->clientes->tbl[j].size );
	}
	v = ler_venda( sgv->fat, sgv->fil, sgv->clientes, sgv->produtos, salesFilePath);

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









//Q9

Q9 getProductBuyers (SGV sgv, char *productID, int branch){
	Q9 q = malloc (sizeof(Q9));
	q->sizeN = 0;
	q->sizeP = 0;
	q->n = NULL;
	q->p = NULL;
	int t;
	int u;
	int s;
	int sn,qn,sp,qp;

	for(int i=0;i<26;i++){
		t = getSizeArrClient (sgv->fil, i);
		
		for(int j=0; j<t; j++){
			u = getFilUsed(sgv->fil, i, j, branch-1);
			if(u==1){
				int found=0;

			   for(int m = 0; m<12 && !found; m++){
				   s = getSizeQprd(sgv->fil, i, j, branch-1, m);
				
				   for(int mid =0; mid < s && !found; mid++){
					   if (strcmp(productID, getOneProd(sgv->fil, i, j, branch-1, m, mid)) == 0){
					   	   qn = getQuantN (sgv->fil, i, j, branch-1, m, mid);
					   	   qp = getQuantP (sgv->fil, i, j, branch-1, m, mid);
					   	   sn = q->sizeN;
					   	   sp = q->sizeP;
					   	   if(qn>0 && qp>0){
					   	   	q->n = realloc (q->n, (sn+1) * sizeof(char*));
					   	   	q->n[sn] = strdup(getCLiente(sgv->fil, i, j));
					   	   	q->sizeN++;
					   	   	q->p = realloc (q->p, (sp+1) * sizeof(char*));
					   	   	q->p[sp] = strdup(getCLiente(sgv->fil, i, j));
					   	   	q->sizeP++;
					   	   	found=1;
					   	   }
					   	   else if(qn>0){
					   	   	q->n = realloc (q->n, (sn+1) * sizeof(char*));
					   	   	q->n[sn] = strdup(getCLiente(sgv->fil, i, j));
					   	   	q->sizeN++;
					   	   	found=1;
					   	   }
					   	   else if (qp>0){
					   	   	q->p = realloc (q->p, (sp+1) * sizeof(char*));
					   	   	q->p[sp] = strdup(getCLiente(sgv->fil, i, j));
					   	   	q->sizeP++;
					   	   	found=1;
					   	   }

					   }
				   }
				}
			}
			
		}

	}
	for(int i=0; i<q->sizeN;i++){
		printf("Compras N %s\n", q->n[i]);
	}

	for(int i=0;i<q->sizeP;i++){
		printf("Compras P %s\n",q->p[i]);
	}
	return q;
}







//Q10


void swapq12(QntNSpent *args , int i1, int i2)
{
    char *tmp = args[i1].pid;
    double s = args[i1].spent;
    int q = args[i1].qnt;
    args[i1].pid = args[i2].pid;
    args[i1].spent = args[i2].spent;
    args[i1].qnt = args[i2].qnt;
    args[i2].pid = tmp;
    args[i2].spent = s;
    args[i2].qnt = q;
}

void q10sort(QntNSpent *args, int len){
    int i, pvt=0;
    if (len <= 1)
        return;

    for (i=0;i<len-1;++i){
        if (args[i].qnt > args[len-1].qnt)
            swapq12(args,i,pvt++);
    }
    swapq12(args,pvt,len-1);
    q10sort(args, pvt++);
    q10sort(args+pvt, len - pvt);
}




Q12 getClientFavouriteProducts(SGV sgv, char* clientID, int month){
	int tam2 =0,size, id;
	Q12 q10 = initQ12();
	int k = hashfat(clientID);
	int tam = getSizeArrClient(sgv->fil,k);
	int r = existe_fil(getArrByLetter(sgv->fil,k), clientID, tam);

	if (r>=0 ){
		for (int i = 0; i < 3; i++){
			tam2 = getSizeQprd(sgv->fil,k,r,i,month-1);
			if(tam2 != 0){
				for (int c = 0; c < tam2; c++){
					size = q10->tam;
					id = existe_q12(q10->arr,getOneProd(sgv->fil,k,r,i,month-1,c),size );
					if (id >= 0){
						q10->arr[id].qnt += getQuantP(sgv->fil,k,r,i, month-1, c)+ getQuantN(sgv->fil,k,r,i, month-1, c);
					}
					else{
						q10->arr = realloc(q10->arr, (size+1)*sizeof(QntNSpent));
						q10->arr[size].pid = strdup(getOneProd(sgv->fil,k,r,i,month-1,c));
						q10->arr[size].qnt = getQuantP(sgv->fil,k,r,i, month-1, c)+ getQuantN(sgv->fil,k,r,i, month-1, c);
						q10->tam++;
					}
				}
			}
		}
		q10sort(q10->arr, q10->tam);
		for (int i = 0; i < q10->tam; i++){
			printf("%d %s\n", q10->arr[i].qnt, q10->arr[i].pid );
		}	
	}
	return q10;
}










//Q11



void swapq11(Qt *args , int i1, int i2)
{
    char *tmp = args[i1].pid;
    int c = args[i1].clientes;
    int q = args[i1].quant;
    args[i1].pid = args[i2].pid;
    args[i1].clientes = args[i2].clientes;
    args[i1].quant = args[i2].quant;
    args[i2].pid = tmp;
    args[i2].clientes = c;
    args[i2].quant = q;
}

void q11sort(Qt *args, int len){
    int i, pvt=0;
    if (len <= 1)
        return;

    for (i=0;i<len-1;++i){
        if (args[i].quant > args[len-1].quant)
            swapq11(args,i,pvt++);
    }
    swapq11(args,pvt,len-1);
    q11sort(args, pvt++);
    q11sort(args+pvt, len - pvt);
}


Q11 initQ11(){
	Q11 q = malloc (sizeof(Q11));
	q->f = malloc (3 * sizeof(Filiais));
	for(int i=0; i<3; i++){
		q->f[i].size = 0;
		q->f[i].qts = NULL;
	}
	return q;
}

int existeProd(Qt *arr, char *procurado, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     int r=-1;
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

Q11 toArray (SGV sgv){
    Q11 q = initQ11();
    int t = 0; 
    int u = 0;
    int s = 0;

	for(int i =0; i<26; i++){
		t = getSizeArrayP(sgv->fat, i);
		for(int j=0; j<t; j++){
			for(int fi=0; fi<3;fi++){
				u = getFilialUsed(sgv->fat, i, j, fi);
                if( u==1){
                	s = q->f[fi].size;
                	q->f[fi].qts = realloc ( q->f[fi].qts, (s+1) * sizeof(Qt));
                	q->f[fi].qts[s].clientes = 0;
                	q->f[fi].qts[s].quant = 0;
                	q->f[fi].qts[s].pid = strdup(getProdFat(sgv->fat,i,j));
                	q->f[fi].size++;
                }

			}
		}
	}
	return q;
}

void umCliente (SGV sgv, Q11 q, int k, int id){
     int u = 0;
     int s = 0;
     int e = 0;

     for(int i=0; i<3; i++){
     	u= getFilUsed(sgv->fil, k, id, i);
     	if(u == 1){
     		for(int m =0; m<12; m++){
     			s = getSizeQprd ( sgv->fil, k, id, i, m);
     			for(int mid = 0; mid <s; mid++){

     				e = existeProd(q->f[i].qts, getOneProd(sgv->fil, k, id, i, m, mid), q->f[i].size);
     				q->f[i].qts[e].clientes++;
     				q->f[i].qts[e].quant += getQuantN(sgv->fil, k, id, i, m, mid) + getQuantP(sgv->fil, k, id, i, m, mid);
     			}
     		}
     	}

     }
}

Q11 getTopSelledProducts (SGV sgv, int limit){

	Q11 q = toArray(sgv);
	int t = 0;

	for(int i=0; i<26; i++){
		t = getSizeArrClient(sgv->fil, i);
        for(int j = 0; j<t; j++){
        	umCliente(sgv, q, i, j);
        }
	}

	for(int i= 0; i<3; i++){
		q11sort(q->f[i].qts, q->f[i].size);
    }
	
	return q;
}












//Q12

Q12 initQ12(){
	Q12 q12 = malloc(sizeof(Q12));
	q12->tam = 0;
	q12->arr = NULL;
	return q12;
}


/*Função que ordena um array de strings*/
void q12sort(QntNSpent *args, int len){
    int i, pvt=0;
    if (len <= 1)
        return;

    for (i=0;i<len-1;++i){
        if (args[i].spent > args[len-1].spent)
            swapq12(args,i,pvt++);
    }
    swapq12(args,pvt,len-1);
    q12sort(args, pvt++);
    q12sort(args+pvt, len - pvt);
}

int existe_q12(QntNSpent *arr, char *procurado, int Tam){
     int inf = 0;  
     int sup = Tam-1; 
     int meio;
     int r=-1;
     while (inf <= sup && r==-1){
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



Q12 getClientTopProfitProducts(SGV sgv, char *clientID , int limit){
	int tam2 =0,size, id;
	Q12 q12 = initQ12();
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
						id = existe_q12(q12->arr,getOneProd(sgv->fil,k,r,i,j,c),size );
						if (id >= 0){
							q12->arr[id].spent += getGastoP(sgv->fil,k,r,i, j, c)+ getGastoN(sgv->fil,k,r,i, j, c);
						}
						else{
							q12->arr = realloc(q12->arr, (size+1)*sizeof(QntNSpent));
							q12->arr[size].pid = strdup(getOneProd(sgv->fil,k,r,i,j,c));
							q12->arr[size].spent = getGastoP(sgv->fil,k,r,i, j, c)+ getGastoN(sgv->fil,k,r,i, j, c);
							q12->tam++;
						}
					}
				}
			}
		}
		q12sort(q12->arr, q12->tam);
		//q12->tam = limit;

		//seria nice limpar todas as posiçoes daqui ate limite

		for (int i = 0; i < limit; i++){
				printf("%f %s\n", q12->arr[i].spent, q12->arr[i].pid );
		}	
	}
	return q12;
}





// Q13


