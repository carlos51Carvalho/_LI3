#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#include "clientes.h"
#include "produtos.h"
#include "faturacao.h"
#include "filiais.h"
#include "vendas.h"
#include "queries.h"

#define SIZE_CL 1000000
#define SIZE_PR 1000000
#define SIZE_VD 1000000

void prettyprintmenu(){
	printf("op1->blah\n");
}

int alldigits(char* buf){
	int flag=1;
	for(int i=0;i<strlen(buf)&&flag;i++){
		flag=isdigit(buf[i]);
	}
	return flag;
}

int toint(char* buf){
	if(!alldigits(buf))return -1;
	else return atoi(buf);
}

void flush(){
	printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
}

int main(){

	int p=0;
	char letra = 'D';
	SGV sgv = initSGV();
	sgv = loadSGVFromFiles(sgv, "Dados_Iniciais");
	p = getProductsStartedByLetter(sgv, letra);



	char buf[10];
	int op;
	do{
		flush();
		prettyprintmenu();
		printf("A sua opção: ");
		scanf("%10s",buf);
		getchar();//gets the enter

		op=toint(buf);
		printf("%d\n", op);
		
		//switch op

		printf("\n");
		printf("press Enter to continue");
		getchar();//pausa para observar o resultado

	}while(op);




	//p = getProductsSalesAndProfit( sgv, "BR1925", 2, 1);
	//p = getProductsNeverBought(sgv,1);
	//p = getClientsOfAllBranches(sgv);
	/*
	FatP *h =NULL ;
	h = initFat();
	acrescentaFat(h,"AA2222", 77.72, 100, 'P', "A4891", 2, 1);
	acrescentaFat(h,"AB2122", 77.72, 18, 'N', "F4891", 2, 2);
	acrescentaFat(h,"AA3122", 77.72, 123, 'P', "T4891", 2, 3);
	acrescentaFat(h,"AC3221", 77.72, 444, 'N', "G4891", 2, 2);
	acrescentaFat(h,"AA2222", 15.00, 300, 'N', "B4831", 2, 1);
	*/


	/*
	THash *cliente = NULL;
	THash *prod = NULL;
	TVendas *vendas = NULL;
<<<<<<< HEAD
=======
	int p=0;
	int v=0;
>>>>>>> 41b1f566b3f01a2d605fb00eec8467828ccf20f5
	int c=0;
	int v=0;
	char *cl = "H4511" ;
	cliente = initTab();
	c = ler_clientes(cliente);
	imprimecliente(cliente);
	prod = initTab();
	p = ler_prod(prod);
	imprimecliente(prod);
	vendas = initTv();
	v = ler_venda(vendas, cliente, prod);
	imprimevendas(vendas);
	
	printf("\n%d",c );
	printf("\n%d",p );
	//printf("\n%d\n", v);
	
	printf("Existem %d clientes começádos pela letra %c \n", letra_cl(cliente,&letra), letra);
	printf("Vendas para o Cliente %s : %d\n",cl, vendas_1c(vendas,cl) );
	printf("Vendas na filial 1 : %d\n",vendas_fil1(vendas,1) );
	printf("Vendas na filial 2 : %d\n",vendas_fil1(vendas,2) );
	printf("Vendas na filial 3 : %d\n",vendas_fil1(vendas,3) );
	escrever_c(cliente, "ClientesVAL.txt");
	escrever_c(prod, "ProdutosVAL.txt");
<<<<<<< HEAD
	escrever_v(vendas, "VendasVAL.txt");
=======
	//escrever_v(vendas, "VendasVAL.txt", v);
	//printf("O ultimo elemento dos clientes é : %s\n", ultimoElemTB(cliente));
	//printf("O ultimo elemento dos produtos é : %s\n", ultimoElemTB(prod));
	//printf("O ultimo elemento das vendas é : %s\n", (*vendas +(7*(v)+0)));
>>>>>>> 41b1f566b3f01a2d605fb00eec8467828ccf20f5
	free(cliente);
	free(prod);
	free(vendas);
	*/


	return p;
}
