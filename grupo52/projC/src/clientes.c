#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"

int comparaclientes(char *c1,char *c2){
	int result;
	if (c1[0]==c2[0]){
		result=atoi(c1+1)-atoi(c2+1);
	}else{
		result=c1[0]-c2[0];
	}
	return result;
}
/*
void sortcliente(char ***cliente, int c){
	char *aux=NULL;
	for (int i = 0; i < c-1; ++i){
		for (int j = i+1; j < c; j++){
			if (comparaclientes(((*cliente)[i]),((*cliente)[j]))>0){
				aux=(*cliente)[i];
				(*cliente)[i]=(*cliente)[j];
				(*cliente)[j]=aux;		
			}

		}
	}
}
*/

void swapc(char **arg1, char **arg2)
{
    char *tmp = *arg1;
    *arg1 = *arg2;
    *arg2 = tmp;
}

void quicksortc(char **args, int len)
{
    unsigned int i, pvt=0;

    if (len <= 1)
        return;

    // swap a randomly selected value to the last node
    //swapc(args+((unsigned int)rand() % len), args+len-1);

    // reset the pivot index to zero, then scan
    for (i=0;i<len-1;++i)
    {
        if (strcmp(args[i], args[len-1]) < 0)
            swapc(args+i, args+pvt++);
    }

    // move the pivot value into its place
    swapc(args+pvt, args+len-1);

    // and invoke on the subsequences. does NOT include the pivot-slot
    quicksortc(args, pvt++);
    quicksortc(args+pvt, len - pvt);
}



void imprimecliente(char **cliente, int c){
	for (int i = 0; i < c; ++i){
		printf("%s\n", cliente[i]);
	}
}


int ler_clientes (char ***cliente, int size){
	FILE *ficheiro = NULL;
	char *chave=NULL;
	char linha[128];
	int i;
	ficheiro = fopen("Dados_Iniciais/Clientes.txt", "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; i < size && fgets(linha, sizeof(linha), ficheiro) ;){
		chave = strtok(linha, "\r\n");

		if (validacliente(chave)){
			*cliente = realloc(*cliente, (i+1)*sizeof(char*));
			(*cliente)[i] =strdup(chave);
			//printf("%s\n", (*cliente)[i]);
			i++;
		}

		chave = strtok(NULL, "\r\n");
	}
	
	fclose(ficheiro);

	return i;
}

int validacliente(char *cliente){
	return (cliente[0]>='A' && cliente[0]<='Z' && atoi(cliente+1)>=1000 && atoi(cliente+1)<=9999);
	
}

int let_c (char *cliente, char letra){
	if (cliente[0] == letra){
		return 1;
	}
	else return 0;
}

int letra_cl(char **cliente, char letra, int size){
	int cont =0, i;
	for (i = 0; i < size; i++){
		cont += let_c(cliente[i],letra); 
	}
	return cont;
}
