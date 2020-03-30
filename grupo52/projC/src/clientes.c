#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "faturacao.h"
#include "clientes.h"


int hash(char *cont){
	int r = cont[0] - 'A';
	return r;
}


THash* initTab(){
	int i;
	THash *h = malloc(sizeof(THash));
	h->size =HSIZE;
	h->u1 = 0;
	h->u2 = 0;
	h->u3 = 0;
	h->tbl = malloc(HSIZE *sizeof(Bucket));
	for ( i =0; i < HSIZE ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = malloc(sizeof(char*));
		//printf("%d\n",h->tbl[i].size );
	}
	//printf("%d\n",i );
	return h;
}


void destroiTab(THash *h){
	int i,j;
	for (i = 0; i < h->size; i++){
		for (j = 0; j < h->tbl[i].size; j++){
			free((h->tbl[i]).arr);
		}
		free(h->tbl);
	}
	free(h);
}

void acrecenstaTab(THash *h, char *cont){
	int key = hash(cont);
	int tam = h->tbl[key].size;
	h->tbl[key].arr = realloc(h->tbl[key].arr, (tam +1)*sizeof(char*));
	h->tbl[key].arr[tam] = strdup(cont);
	h->tbl[key].size++;
	//printf("%s\n",h->tbl[key].arr[tam].id);
	return ;
}



void imprimecliente(THash *h){
	int tam = h->size;
	int tam2;
	for (int i = 0; (i < tam); i++){
		tam2 = h->tbl[i].size;
		for (int j = 0; j < tam2 ; j++){
			printf("%s\n",  h->tbl[i].arr[j]);
		}
	}
}


void escrever_c(THash *h , char *s) {
	int tam2=0;
    FILE *cena = fopen(s, "w");

    for (int i = 0; i < h->size; i++){
    	tam2 = h->tbl[i].size;
		for (int j = 0; j < tam2; j++){
			fprintf(cena,"%s\n", h->tbl[i].arr[j] );
        }
    }
    fclose(cena);
}



void swapc(char **arg1, char **arg2)
{
    char *tmp = *arg1;
    *arg1 = *arg2;
    *arg2 = tmp;
}


void quicksortc(char **args, unsigned int len)
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



int ler_clientes (THash *cliente , char *filespath){
	FILE *ficheiro = NULL;
	char aux[80];
	strcpy(aux, filespath); 
	strcat(aux,"/Clientes.txt");
	char *chave=NULL;
	char linha[128];
	int i;
	ficheiro = fopen(aux, "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; fgets(linha, sizeof(linha), ficheiro) ;){
		chave = strtok(linha, "\r\n");

		if (validacliente(chave)){
			acrecenstaTab(cliente, chave);
			i++;
		}

		chave = strtok(NULL, "\r\n");
	}
	
	fclose(ficheiro);

	int j;

	for (j = 0; j < cliente->size ; j++){
		quicksortc(cliente->tbl[j].arr, cliente->tbl[j].size);
	}

	return i;
}


int validacliente(char *cliente){
	return (cliente[0]>='A' && cliente[0]<='Z' && atoi(cliente+1)>=1000 && atoi(cliente+1)<=9999);
}



/*
int letra_cl(THash *h, char* letra, char **p ){
	int k = hash(letra);
	char **p = h->tbl[k]->arr;
	
	return (h->tbl[k].size);
}
*/
/*
char* ultimoElemTB(THash *h){
	int tam = h->size -1;
	int tam2 = h->tbl[tam].size-1;
	return (h->tbl[tam].cl[tam2]);
}


*/
