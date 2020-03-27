#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"
#include "vendas.h"

#define SIZE_CL 1000000
#define SIZE_PR 1000000
#define SIZE_VD 1000000


int main(){
	THash *cliente = NULL;
	THash *prod = NULL;
	TVendas *vendas = NULL;
	int p=0;
	int v=0;
<<<<<<< HEAD
	int c=0;
	char letra = 'D';
	//char *cl = "H4511" ;
	cliente = initTab();
	c = ler_clientes(cliente,SIZE_CL);
	//imprimecliente(cliente);
	prod = initTab();
	p = ler_prod(prod,SIZE_PR);
	//imprimecliente(prod);
	vendas = initTv();
	v = ler_venda(vendas, cliente, prod);
	//imprimevendas(vendas);
	printf("\n%d",c );
	printf("\n%d",p );
	printf("\n%d\n", v);

	//printf("Existem %d clientes começádos pela letra %c \n", letra_cl(cliente,&letra), letra);

	//printf("Vendas para o Cliente %s : %d\n",cl, vendas_1c(vendas,v,cl) );

	//printf("Vendas na filial 1 : %d\n",vendas_fil1(vendas,v,1) );
	//printf("Vendas na filial 2 : %d\n",vendas_fil1(vendas,v,2) );
	//printf("Vendas na filial 3 : %d\n",vendas_fil1(vendas,v,3) );

	escrever_c(cliente, "ClientesVAL.txt");
	escrever_c(prod, "ProdutosVAL.txt");
	//escrever_v(vendas, "VendasVAL.txt", v);

	//printf("O ultimo elemento dos clientes é : %s\n", ultimoElemTB(cliente));
	//printf("O ultimo elemento dos produtos é : %s\n", ultimoElemTB(prod));
	//printf("O ultimo elemento das vendas é : %s\n", (*vendas +(7*(v)+0)));
=======
	
	
	c = ler_clientes(&cliente ,SIZE_CL);
	quicksortc(cliente,c);
	//imprimecliente(cliente,c);
	//ultimo cliente
	//printf("\n%s\n", cliente[c-1]);
	//Quantos clientes começam pela letra ?
	//printf("%d\n", letra_cl(cliente,"B",c) );
	printf("\n\n%d===============================================================\n",c);
	p = ler_prod (&prod , SIZE_PR);
	quicksortc(prod,p);
	//imprimeprod(prod,p);
	printf("%d===============================================================\n",p);
	v = ler_venda(&vendas,SIZE_VD,cliente,prod,c,p);
	//imprimevendas(vendas,v);
	printf("%d===============================================================\n",v);
	printf("%d\n", vendas_1c(vendas,v,"H4511"));
	printf("Vendas filial 1 .... %d\n", vendas_fil1(vendas,v,1));
	printf("Vendas filial 2 .... %d\n", vendas_fil1(vendas,v,2));

	escrever(vendas,"vendasmm.txt",v);
>>>>>>> 42add232be9b05b1817bf6959b7ed44623f85761

	free(cliente);
	free(prod);
	free(vendas);


	return 0;
}