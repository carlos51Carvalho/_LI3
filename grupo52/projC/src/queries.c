#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"
#include "faturacao.h"
#include "filiais.h"
#include "vendas.h"
#include "queries.h"

typedef struct sgv{
	int pv;
	int cv;
	int vv;
	int pl;
	int cl;
	int vl;
	char *p;
	char *c;
	char *v;
	THash *produtos;
	THash *clientes;
	Fat *fat;
	Filial *fil;
} *SGV;

SGV initSGV(){
	SGV q = malloc(sizeof(struct sgv));
	q->pv = 0;
	q->cv= 0;
	q->vv = 0;
	q->pl = 0;
	q->cl= 0;
	q->vl = 0;
	q->p =NULL;
	q->c = NULL;
	q->v =NULL;
	q->produtos = initTab_p();
	q->clientes = initTab();
	q->fat = initFat();
	q->fil = initFilial();
	return q;
}

void distroySGV(SGV sgv){
	destroiTab_p(sgv->produtos);
	destroiTab(sgv->clientes);
	destroiFat(sgv->fat);
	destroiFilial(sgv->fil);

}

void destroiArrayStrings(char** a, int size){
	for (int j = 0; j < size; j++){
		free(a[j]);
	}
	free(a);
}







   /**
    * @brief Função que verifica se um mes é valido testando se o int dado está entre 0 e 12
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param int m                               Mes a testar
    *
    * @return int 
    */

int validames(int m){
	if (m>0 && m<13) return 1;
	else return -1;
}

   /**
    * @brief Função que verifica se uma filial é valida testando se o int dado está entre 1 e 3 
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param int f                               Filial a testar
    *
    * @return int 
    */

int validafilial(int f){
	if (f>0 && f<4) return 1;
	else return -1;
}

int letravalida(char l){
	if (l>='A' && l<='Z') return 1;
	else return -1;
}



SGV loadSGVFromFiles(SGV sgv, char *clientsFilePath, char *productsFilePath, char *salesFilePath ){
	int p,c,v,d,e;
	char** pr=NULL;
	char** cl=NULL;
	int prsize,clsize;
	p=c=v=d=e=0;
	sgv->pv = ler_prod(sgv->produtos,productsFilePath, &p);
	sgv->cv = ler_clientes(sgv->clientes, clientsFilePath, &c);
	for (int i = 0; i < 26; ++i){
		pr=getArrayProd(sgv->produtos,i);
		prsize=getArrayProdSize(sgv->produtos,i);
		d += acrescenta_prods(sgv->fat,pr,prsize);
		destroiArrayStrings(pr,prsize);
	}
	for (int j = 0; j < 26; j++){
		cl=getArrayCl(sgv->clientes,j);
		clsize=getArrayClSize(sgv->clientes,j);
		e += acrescenta_cls(sgv->fil, cl, clsize );
		destroiArrayStrings(cl,clsize);
	}
	sgv->vv = ler_venda( sgv->fat, sgv->fil, sgv->clientes, sgv->produtos, salesFilePath, &v);
	sgv->p = strdup(productsFilePath);
	sgv->c = strdup(clientsFilePath);
	sgv->v = strdup(salesFilePath);
	sgv->pl = p;
	sgv->cl = c;
	sgv->vl = v;

	return sgv;
}

//	Q2

Q245 getProductsStartedByLetter(SGV sgv, char letter){

	Q245 q2 = malloc(sizeof(Q245));
	q2->tam = 0;
	q2->p = NULL;
	if (letravalida(letter)){
	
		int k = hash_p(&letter);
		q2->tam = getArrayProdSize(sgv->produtos,k);
		q2->p = getArrayProd(sgv->produtos,k);
	}
	else{
		q2->tam = -1;
	}
	return q2;
}

// Q3
Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month){
	Q3 q3 = malloc(sizeof(Q3));

	if (validaproduto(productID) && validames(month)){

		int h=hashfat(productID);
		int posProd = getPosicaoProd(sgv->fat,productID);

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
	}
	else{
		q3->vN1 = -1;
	}

	return q3;
}



// Q4

Q245 getProductsNeverBought(SGV sgv , int branchID){
	int j =0;
	Q245 q4 = malloc(sizeof(Q245));
	q4->tam = 0;
	q4->p = NULL;
	if (validafilial(branchID)){
		q4->p = neverBoughtFil(sgv->fat, branchID -1, &j);
		q4->tam = j;
	}
	else{
		q4->p = neverBoughtAllFil(sgv->fat, &j);
		q4->tam = j;
	}

	return q4;
}



//Q5
Q245 getClientsOfAllBranches(SGV sgv){
	Q245 q5 = malloc(sizeof(Q245));
	q5->tam = 0;
	q5->p = NULL;
	int j = 0;
	q5->p= ClientsOfAllBranches(sgv->fil, &j );
	q5->tam = j;

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
	q7->use = 0;
	q7->f = NULL;
	if (validacliente(clientID)){
		q7->f = malloc(3*sizeof(BYFil));
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 12; j++)
			{
				q7->f[i].m[j] = QuantidadesUmClientePorMes(sgv->fil, clientID ,i, j);
			}
		}

	}
	else{
		q7->use = -1;
	}
	return q7;
} 

//Q8

Q8 getSalesAndProfif(SGV sgv, int minMonth, int maxMonth){
	Q8 q8 = malloc(sizeof(Q8));

	if (validames(minMonth) && validames(maxMonth) && minMonth < maxMonth){

		int v=0;
		double f=0;

		FaturacaoeVendasIntervalo(sgv->fat,minMonth, maxMonth, &v, &f);

		q8->v=v;
		q8->f=f;
	}
	else{
		q8->v=-1;
		q8->f= 0;
	}

	return q8;
}










//Q9

Q9 getProductBuyers (SGV sgv, char *productID, int branch){

	Q9 q = malloc (sizeof(Q9));
	q->sizeN = 0;
	q->sizeP = 0;
	q->n = NULL;
	q->p = NULL;
	if(validaproduto(productID) && validafilial(branch)){

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
	}
	else{
		q->sizeN = -1;
		q->sizeP = -1;
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

	Q12 q10 = initQ12();
	if(validacliente(clientID) && validames(month)){

		int tam2 =0,size, id;
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
		}
	}
	else{
		q10->tam = -1;
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
		for (int j = limit; j < q->f[i].size; j++)
    	{
    		free(q->f[i].qts[j].pid);
    	}
		q->f[i].size = limit;
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
	Q12 q12 = initQ12();
	if (validacliente(clientID)){
		
		int tam2 =0,size, id;
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
	
		}
	}
	else{
		q12->tam=-1;
	}
	return q12;
}



///////////////////////////////////////////////////////////////////////

// Q13

char* getReadFile(char *filePath){
	char *part = NULL;
	char *ant = NULL;
	char *file = strdup(filePath);
	for (part = strtok(file, "/"); part != NULL ; part = strtok(NULL, "/")){

		ant = strdup(part);
	}
	free(part);
	return ant;
}



Q13 getCurrentFilesInfo(SGV sgv){
	Q13 q = malloc(sizeof(Q13));
	q->pv = sgv->pv;
	q->cv = sgv->cv;
	q->vv = sgv->vv;
	q->pl = sgv->pl;
	q->cl = sgv->cl;
	q->vl = sgv->vl;
	q->p = getReadFile(sgv->p);
	q->c = getReadFile(sgv->c);
	q->v = getReadFile(sgv->v);

	return q;
}

