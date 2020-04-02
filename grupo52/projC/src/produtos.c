#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "produtos.h"



/*
int hashP(char *cont){
	int r = cont[0] - 'A' + cont[1]-'A';
	return r;
}*/

int validaproduto(char *produto){ 
	return (produto[0]>='A' && produto[0]<='Z' && produto[1]>='A' && produto[1]<='Z' && atoi(produto+2)>=1000 && atoi(produto+2)<=9999);
}


int ler_prod (THash *prod,char *filespath ){
	FILE *ficheiro = NULL;
	char aux[80];
	strcpy(aux, filespath); 
	strcat(aux,"/Produtos.txt");
	char *chave=NULL;
	char linha[128];
	int i;
	ficheiro = fopen(aux, "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; fgets(linha, sizeof(linha), ficheiro) ; ){
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
