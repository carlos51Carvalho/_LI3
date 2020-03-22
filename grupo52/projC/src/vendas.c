#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "vendas.h"
#include "produtos.h"
#include "clientes.h"

void imprimevendas(char **vendas, int v){
	for (int i = 0; i < v; i++){
		for (int j = 0; j < 7; j++){
			printf("%s ", *(vendas+(7*i + j)));
		}
		printf("\n");
	}
}

/*
int existeproduto(char **produto, char *prod, int p){
	int result=0;
	for (int i = 0; i < p && result==0; ++i){
		if(strcmp(produto[i],prod)==0) result = 1;
	}
	return result;
}


int existeProduto (char **produto, char *prod_vendas, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     while (inf <= sup)
     {
          meio = inf +(sup - inf)/2;
          if (strcmp(prod_vendas, produto[meio]) ==0)
               return meio;
          if (strcmp(prod_vendas, produto[meio])<0)
               sup = meio-1;
          else
               inf = meio+1;
     }
     return -1;   // não encontrado
}


int existecliente(char **clientes,char *cliente,int c){
	int result=1;
	for (int i = 0; i < c && result>0; ++i){
		result=strcmp(clientes[i],cliente);
	}
	return result;
}

int existe (char *emvendas, int ini, int fim, char **testado) {
   if (ini > fim) return -1;
   else {
      int m = (ini + fim)/2;
      if (strcmp(testado[m], emvendas) == 0) return m;
      if (strcmp(testado[m], emvendas) < 0)  
         return existe (emvendas, m+1, fim, testado);
      else  
         return existe (emvendas, ini, m-1, testado); 
   } 
}
*/


int existe(char **testado, char *nas_vendas, int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     int r=0;
     while (inf <= sup && r==0)
     {
          meio = inf +(sup- inf)/2;
          if (strcmp(nas_vendas, testado[meio]) ==0)
               r =1;
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



int ler_venda(char ***vendas, int size,char **cliente, char **prod, int c, int p){
	FILE *ficheiro = NULL;
	int i,j;
	char* part = NULL, * aux = NULL;
	char linha[1024];

	char cenas[7][1024];
	
	ficheiro = fopen("Dados_Iniciais/Vendas_1M.txt", "r");

	if (ficheiro == NULL) return -1;


	for (i = 0; i < size && fgets(linha,sizeof(linha), ficheiro);)
	{

		*vendas = realloc(*vendas, (i+1)*7*sizeof(char*));
		aux = strtok(linha, "\r\n");
	
		//ler para cenas
		for (part = strtok(aux, " "), j = 0; part != NULL ; part = strtok(NULL, " "),j++){


			*(*vendas+(7*i + j)) = strdup(part);

			strcpy(cenas[j],*(*vendas+(7*i + j)));
		}

		//se todos os campos estao certos
		if(part==NULL && j==7 
			//&& validaproduto(cenas[0])
			&& existe(prod,   *(*vendas+(7*i+0)),  p) 
			&& validapreco(*(*vendas+(7*i+1))) 
			&& valida3campo(*(*vendas+(7*i+2))) 
			&& valida4campo(*(*vendas+(7*i+3))) 
			//&& validacliente(cenas[4])
			&& existe(cliente,*(*vendas+(7*i+4)),c) 
			&& valida6mes(*(*vendas+(7*i+5))) 
			&& valida7filial(*(*vendas+(7*i+6)))
			){
			i++;
		}
	}

	free(part);
	fclose(ficheiro);
	
	return i;
}


int vendas_1c(char **vendas, int size_v, char *cliente){
	int cont =0, i;
	for (i = 0; i < size_v; i++){
		if (strcmp(*(vendas+(7*i+4)), cliente) == 0) {
			cont++;
		}
	}
	return cont;
}


int vendas_fil1(char **vendas, int size_v, int filial){
	int count =0,i;
	int fil;
	for (i = 0; i < size_v; i++){
		fil = atoi(*(vendas+(7*i+6)));
		if (fil==filial){
			count++;
		}
	}
	return count;
}












void escrever(char **vendas , char *s, int size) {

    FILE *cena = fopen(s, "w");

    for (int i = 0; i < size; i++){
		for (int j = 0; j < 7; j++){
			fprintf(cena,"%s ", *(vendas+(7*i + j)));
        }
        fprintf(cena,"\n");
    }
    fclose(cena);
}