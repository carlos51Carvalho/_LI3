#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"
#include "vendas.h"

#define SIZE_CL 1000000
#define SIZE_VD 1000000
#define SIZE_PR 1000000


int main(){
	char **cliente = NULL;
	char **prod = NULL;
	char **vendas = NULL;
	int c=0;
	int p=0;
	int v=0;
	
	
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

	free(cliente);
	free(prod);
	free(vendas);

	return 0;
}