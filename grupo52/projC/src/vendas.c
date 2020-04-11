/**
 * @file vendas.c
 * @brief Modulo que contém as funcções para leitura e validação de vendas e incorporação destas nas estruturas filiais e faturação.
 *
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#include "clientes.h"
#include "faturacao.h"
#include "filiais.h"
#include "produtos.h"
#include "vendas.h"


int alldigit(char* buf){
	int i,flag=1;
	for( i=0;i<strlen(buf)&&flag;i++){
		flag=isdigit(buf[i]);
	}
	return flag;
}

int isfloat(char* campo){
	int i,flag=1,flagdot=0;
	for(i=0;i<strlen(campo)&&flag&&flagdot<2;i++){
		if(campo[i]=='.')flagdot++;
		else flag=isdigit(campo[i]);
	}
	return flag;
}


/**
 * Função que destroi (liberta) um array de strings
 * @param char **a
 * @param int size
*/

void destroiArrayString(char** a, int size){
	int j;
	for ( j = 0; j < size; j++){
		free(a[j]);
	}
	free(a);
}





   /**
    * @brief Função de procura binária, que procura num array de strings uma string
    *
    * Quando a encontra retorna 1 , caso não encontre retorna 0
    *
    * @param char **testado                      Array genérico
    * @param char *nas_vendas                    Uma string genérica
    * @param int Tam                             Um int size
    *
    * @return int r                              O indice se encontrado 
    */


int existe(char **testado, char *nas_vendas, int Tam)
{
     int inf = 0;    
     int sup = Tam-1;
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




   /**
    * @brief Função que verifica se um preçe é valido testando se esse preço se encontra entre dois valores
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param char *preco                         Preco testetado
    *
    * @return int 
    */

int validapreco(char *preco){
	double p;
	if(isfloat(preco)){
		p = atof(preco);
		if (p>=0.0 && p<=999.99) return 1;
	}
	
	return 0;
}


   /**
    * @brief Função que verifica se uma quantidade é valida testando se esse valor se encontra entre dois valores
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param char *campo                         Quantidade testetada
    *
    * @return int 
    */

int valida3campo(char *campo){
	int uni=0;
	if (alldigit(campo)){
		uni = atoi(campo);
		if (uni>=0 && uni<=200) return 1;
	}
	
	return 0;
}

   /**
    * @brief Função que verifica se um tipo de compra é valido testando se o char dado é válido
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param char *campo                         Modo a testar
    *
    * @return int 
    */
int valida4campo(char *campo){
	return ((campo[0]=='P' || campo[0]=='N') && campo[1]=='\0');
}



   /**
    * @brief Função que verifica se um mes é valido testando se o int dado está entre 0 e 12
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param char *campo                         Mes a testar
    *
    * @return int 
    */

int valida6mes(char *campo){
	int mes;
	mes = atoi(campo);
	if (mes>0 && mes<13) return 1;
	return 0;
}

   /**
    * @brief Função que verifica se uma filial é valida testando se o int dado está entre 1 e 3 
    *
    * caso sim dá retorno 1, caso contrário -1
    *
    * @param char *filial                        Filial a testar
    *
    * @return int 
    */

int valida7filial(char *filial){
	int fil;
	fil = atoi(filial);
	if (fil>0 && fil<4) return 1;
	
	return 0;

}



   /**
    * @brief Função que le de um ficheiro Vendas
    *
    * Recebendo uma Fat (estrutura Faturação),uma Filial (estrutura Filial) e duas THash(estruturas clientes e produtos) e um file path, lê linha a linha de um determinado ficheiro
    * fazendo enquanto este válido a divisão por espaços \r e \n, vai repassando as partes retiradas dessa linha para uma string part, que vai sendo guardada em auxiliares-
    * Postriormente os campos retirados da linha são validados e são alocados pelas funcçoes acrescentaFat e acrescentaFil as respetivas estruturas que estas recebem como argumento;
    *
    * @param Fat *fat                            Estrutura Fat para onde vai ser extraida informação do ficheiro
    * @param Filial *fil                         Estrutura Filial para onde vai ser extraida informação do ficheiro
    * @param THash *cliente                      Estrutura THash para onde vai ser extraida informação do ficheiro
    * @param char *filespath                     File path do ficheiro a ler
    * @param int *v                              *int onde se vai guardar o total de vendas lidas
    *
    *
    * @return int                                int numero de vendas válidas
    */

int ler_venda(Fat *fat, Filial *fil, THash *cliente, THash *prod, char *filespath, int *v){
	FILE *ficheiro = NULL;
	char* part = NULL;
	char* aux = NULL;
	int i=0,j,result =0, vl =0;
	char linha[1024];
	char *args[15];

	ficheiro = fopen(filespath, "r");

	if (ficheiro == NULL) return -1;

	char ***prds=malloc(26*sizeof(char*));
	char ***clts=malloc(26*sizeof(char*));
	for (i = 0; i < 26; ++i){
		prds[i]=getArrayProd(prod,i);
		clts[i]=getArrayCl(cliente,i);
	}
	while(fgets(linha,sizeof(linha), ficheiro)){
		vl++;
		aux = strtok(linha, "\r\n");
	
		/*ler para as estruturas*/
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
			
			acrescentaFat(fat,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			acrescentaFil(fil,args[0],atof(args[1]),atoi(args[2]),args[3][0],args[4],atoi(args[5]),atoi(args[6]));
			result++;
		}
		for(j = 0 ; j< 7 ; j++){
			free(args[j]);
		}
	}
	fclose(ficheiro);
	free(part);
	for (i = 0; i < 26; ++i){
		destroiArrayString(prds[i],getArrayProdSize(prod,i));
		destroiArrayString(clts[i],getArrayClSize(cliente,i));
	}
	*v = vl;
	free(prds);
	free(clts);
	return result;
}

