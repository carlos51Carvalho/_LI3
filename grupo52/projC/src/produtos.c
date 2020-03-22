#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "produtos.h"

int comparaprodutos(char *c1,char *c2){
	int result;
	if (c1[0]==c2[0]){
		if (c1[1]==c2[1]){
			result=atoi(c1+2)-atoi(c2+2);
		}else{
			result=c1[1]-c2[1];				
		}
	}else{
		result=c1[0]-c2[0];
	}
	return result;
}
/*
void sortprod(char ***prod, int p){
	char *aux=NULL;
	for (int i = 0; i < p-1; ++i){
		for (int j = i+1; j < p; j++){
			if (comparaprodutos(((*prod)[i]),((*prod)[j]))>0){
				//printf("%s(%d)switch%s(%d)\n", (*prod)[i],atoi((*prod)[i]+2),(*prod)[j],atoi((*prod)[j]+2));
				aux=(*prod)[i];
				(*prod)[i]=(*prod)[j];
				(*prod)[j]=aux;		
			}

		}
	}
}
*/

void imprimeprod(char **prod, int p){
	for (int i = 0; i < p; ++i){
		printf("%s\n", prod[i]);
	}
}


int validaproduto(char *produto){ 
	return (produto[0]>='A' && produto[0]<='Z' && produto[1]>='A' && produto[1]<='Z' && atoi(produto+2)>=1000 && atoi(produto+2)<=9999);
}


int ler_prod (char ***prod, int size){
	FILE *ficheiro = NULL;
	char *chave=NULL;
	char linha[128];
	int i;
	ficheiro = fopen("Dados_Iniciais/Produtos.txt", "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; i < size && fgets(linha, sizeof(linha), ficheiro) ; ){
		chave = strtok(linha, " \r\n");
		
		if (validaproduto(chave)){
			*prod = realloc(*prod, (i+1)*sizeof(char*));
			(*prod)[i] =strdup(chave);	
			//printf("%s\n", (*prod)[i]);
			i++;
		}
		
		//prod[i] =strdup(chave);
		chave = strtok(NULL, "\r\n");
	}


	fclose(ficheiro);
	return i;
}