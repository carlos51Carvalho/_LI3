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

	free(cliente);
	free(prod);
	free(vendas);


	return 0;
}