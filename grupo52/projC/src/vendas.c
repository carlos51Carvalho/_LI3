#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "faturacao.h"
#include "produtos.h"
#include "vendas.h"




int existe(char **testado, char *nas_vendas, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     int r=0;
     while (inf <= sup && r==0)
     {
          meio = inf +(sup- inf)/2;
          if (strcmp(nas_vendas, testado[meio]) ==0){
               	r =1;
          }
          if (strcmp(nas_vendas, testado[meio])<0)
               sup = meio-1;
          else
               inf = meio+1;
     }
     return r; 
}



int validapreco(char *preco){
	double p = atof(preco);
	if (p>0.0 && p<=999.99) return 1;
	else return -1;
}

int valida3campo(char *campo){
	int uni = atoi(campo);
	if (uni>0 && uni<=200) return 1;
	else return -1;
}

int valida4campo(char *campo){
	return ((campo[0]=='P' || campo[0]=='N') && campo[1]=='\0');
}

int valida6mes(char *campo){
	int mes = atoi(campo);
	if (mes>0 && mes<13) return 1;
	else return -1;
}

int valida7filial(char *filial){
	int fil = atoi(filial);
	if (fil>0 && fil<4) return 1;
	else return -1;
}

/*
TVendas* initTv(){
	//int i;
	TVendas *v = malloc(sizeof(TVendas));
	v->size = 0;
	v->arr = malloc(sizeof(Venda));
	return v;
}

void acrescentaV(TVendas *v, char*p, double pr, int q, char e, char *c, int m, int f){

	int tam = v->size;
	v->arr = realloc(v->arr, (tam +1)*sizeof(Venda));
	v->arr[tam].prod = strdup(p);
	v->arr[tam].preco = pr;
	v->arr[tam].qnt = q;
	v->arr[tam].est = e;
	v->arr[tam].cliente = strdup(c);
	v->arr[tam].mes = m;
	v->arr[tam].fil = f;
	v->size++;
}

*/

int ler_venda(FatP *fat, THash *cliente, THash *prod, char *filespath){
	FILE *ficheiro = NULL;
	char a[80];
	strcpy(a, filespath); 
	strcat(a,"/Vendas_1M.txt");
	char* part = NULL;
	char* aux = NULL;
	int i=0,j;
	char linha[1024];
	char *args[15];

	ficheiro = fopen(a, "r");

	if (ficheiro == NULL) return -1;

	while(fgets(linha,sizeof(linha), ficheiro)){

		aux = strtok(linha, "\r\n");
	
		//ler para cenas
		for (part = strtok(aux, " "), j = 0; part != NULL ; part = strtok(NULL, " "),j++){
			args[j] = strdup(part);
		}


		int keyp = hash(args[0]);
		int keyc = hash(args[4]);

		if (part==NULL && j==7 && 
			existe(prod->tbl[keyp].arr , args[0],  prod->tbl[keyp].size)
			&& validapreco(args[1]) 
			&& valida3campo(args[2]) 
			&& valida4campo(args[3]) 
			&& existe(cliente->tbl[keyc].arr, args[4], cliente->tbl[keyc].size) 
			&& valida6mes(args[5]) 
			&& valida7filial(args[6])){
			
			//acrescentaV(v,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			acrescentaFat(fat,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			acrecenstaUsado(prod, atoi(args[6]));
			i++;
		}
		for(j = 0 ; j< 7 ; j++){
			free(args[j]);
		}
	}
	fclose(ficheiro);
	free(part);
	return i;
}

/*
int vendas_1c(TVendas *v, char *cliente){
	int cont =0, i;
	for (i = 0; i < v->size; i++){
		if (strcmp(v->arr[i].cliente, cliente) == 0) {
			cont++;
		}
	}
	return cont;
}


int vendas_fil1(TVendas *v, int filial){
	int count =0,i;
	for (i = 0; i < v->size; i++){
		if (v->arr[i].fil==filial){
			count++;
		}
	}
	return count;
}


void imprimevendas(TVendas *v){
	int tam = v->size;
	for (int i = 0; i < tam; i++){
		printf("%s %f %d %c %s %d %d \n",v->arr[i].prod,v->arr[i].preco,v->arr[i].qnt,v->arr[i].est, v->arr[i].cliente, v->arr[i].mes, v->arr[i].fil);
	}
}

void escrever_v(TVendas *v , char *s) {
	int tam = v->size;
    FILE *cena = fopen(s, "w");

    for (int i = 0; i < tam; i++){
		fprintf(cena, "%s %f %d %c %s %d %d \n",v->arr[i].prod,v->arr[i].preco,v->arr[i].qnt,v->arr[i].est, v->arr[i].cliente, v->arr[i].mes, v->arr[i].fil);
    }
    fclose(cena);
}

*/


