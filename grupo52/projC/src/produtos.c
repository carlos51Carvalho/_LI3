#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"


/*
void imprimeprod(char **prod, int p){
	for (int i = 0; i < p; ++i){
		printf("%s\n", prod[i]);
	}
}
*/

int validaproduto(char *produto){ 
	return (produto[0]>='A' && produto[0]<='Z' && produto[1]>='A' && produto[1]<='Z' && atoi(produto+2)>=1000 && atoi(produto+2)<=9999);
}


int ler_prod (THash *prod, int size){
	FILE *ficheiro = NULL;
	char *chave=NULL;
	char linha[128];
	int i;
	ficheiro = fopen("Dados_Iniciais/Produtos.txt", "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; i < size && fgets(linha, sizeof(linha), ficheiro) ; ){
		chave = strtok(linha, " \r\n");
		
		if (validaproduto(chave)){
			acrecenstaTab(prod, chave);
			i++;
		}
		
		//prod[i] =strdup(chave);
		chave = strtok(NULL, "\r\n");
	}


	fclose(ficheiro);

	int j;

	for (j = 0; j < prod->size ; j++){
		quicksortc(prod->tbl[j].arr, prod->tbl[j].size);
	}
	return i;
}