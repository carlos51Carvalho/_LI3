#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "clientes.h"
#include "faturacao.h"
#include "filiais.h"
#include "produtos.h"
#include "vendas.h"

void destroiArrayString(char** a, int size){
	for (int j = 0; j < size; j++){
		free(a[j]);
	}
	free(a);
}
/*Função de procura binária, que procura num array de strings uma string, quando a encontra retorna
1 , caso não encontre retorna 0*/

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


/*Função que verifica se um preçe é valido testando se esse preço se encontra entre dois valores
caso sim dá retorno 1, caso contrário -1*/
int validapreco(char *preco){
	double p = atof(preco);
	if (p>0.0 && p<=999.99) return 1;
	else return -1;
}
/*Função que verifica se uma quantidade é valida testando se esse valor se encontra entre dois valores
caso sim dá retorno 1, caso contrário -1*/
int valida3campo(char *campo){
	int uni = atoi(campo);
	if (uni>0 && uni<=200) return 1;
	else return -1;
}

/*Função que verifica se um tipo de compra é valido testando se o char dado é válidp 
caso sim dá retorno 1, caso contrário -1*/
int valida4campo(char *campo){
	return ((campo[0]=='P' || campo[0]=='N') && campo[1]=='\0');
}
/*Função que verifica se um mes é valido testando se o int dado está entre 0 e 12 
caso sim dá retorno 1, caso contrário -1*/
int valida6mes(char *campo){
	int mes = atoi(campo);
	if (mes>0 && mes<13) return 1;
	else return -1;
}
/*Função que verifica se uma filial é valida testando se o int dado está entre 1 e 3 
caso sim dá retorno 1, caso contrário -1*/
int valida7filial(char *filial){
	int fil = atoi(filial);
	if (fil>0 && fil<4) return 1;
	else return -1;
}

/* Função que recebendo uma Fat (estrutura Faturação),uma Filial (estrutura Filial) e duas THash(estruturas clientes e produtos) e um file path, lê linha a linha de um determinado ficheiro
fazendo enquanto este válido a divisão por espaços \r e \n, vai repassando as partes retiradas dessa linha para uma string part, que vai sendo guardada em auxiliares-
Postriormente os campos retirados da linha são validados e são alocados pelas funcçoes acrescentaFat e acrescentaFil as respetivas estruturas que estas recebem como argumento;
*/
int ler_venda(Fat *fat, Filial *fil, THash *cliente, THash *prod, char *filespath, int *v){
	FILE *ficheiro = NULL;
	//char a[80];
	//strcpy(a, filespath); 
	//strcat(a,"/Vendas_1M.txt");
	char* part = NULL;
	char* aux = NULL;
	int i=0,j, vl =0;
	char linha[1024];
	char *args[15];

	ficheiro = fopen(filespath, "r");

	if (ficheiro == NULL) return -1;

	char ***prds=malloc(26*sizeof(char*));
	char ***clts=malloc(26*sizeof(char*));
	for (int i = 0; i < 26; ++i){
		prds[i]=getArrayProd(prod,i);
		clts[i]=getArrayCl(cliente,i);
	}

	while(fgets(linha,sizeof(linha), ficheiro)){
		vl++;
		aux = strtok(linha, "\r\n");
	
		//ler para cenas
		for (part = strtok(aux, " "), j = 0; part != NULL ; part = strtok(NULL, " "),j++){
			args[j] = strdup(part);
		}


		int keyp = hash(args[0]);
		int keyc = hash(args[4]);

		if (part==NULL && j==7 
			&& existe(prds[keyp], args[0],  getArrayProdSize(prod,keyp))
			&& validapreco(args[1]) 
			&& valida3campo(args[2]) 
			&& valida4campo(args[3]) 
			&& existe(clts[keyc], args[4], getArrayClSize(cliente,keyc)) 
			&& valida6mes(args[5]) 
			&& valida7filial(args[6])){
			
			//acrescentaV(v,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			acrescentaFat(fat,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			acrescentaFil(fil,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			//acrecenstaUsado(prod, atoi(args[6]));
			i++;
		}
		for(j = 0 ; j< 7 ; j++){
			free(args[j]);
		}
	}
	fclose(ficheiro);
	free(part);
	for (int i = 0; i < 26; ++i){
		destroiArrayString(prds[i],getArrayProdSize(prod,i));
		destroiArrayString(clts[i],getArrayClSize(cliente,i));
	}
	*v = vl;
	free(prds);
	free(clts);
	return i;
}

/*
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