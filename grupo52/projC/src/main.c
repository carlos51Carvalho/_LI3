#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#include "interface.h"


#define SIZE_CL 1000000
#define SIZE_PR 1000000
#define SIZE_VD 1000000

void prettyprintmenu(){
	printf("\tEscolha um comando\n"
               " 1 - Ler os ficheiros (Produtos, Clientes e Vendas)  \n"
               " 2 - Obter a lista e o no total de produtos começados por uma letra maiuscula \n"
               " 3 - Obter o numero total de vendas e o total faturado para determinado produto num mes \n"
               " 4 - Lista ordenada dos códigos dos produtos que ninguém comprou \n"
               " 5 - Lista ordenada de códigos de clientes que realizaram compras em todas as filiais\n"
               " 6 - Obter o número de clientes que não realizaram compras e o numero de produtos que ningém comprou\n"
               " 7 - Criar uma tabela com o numero total de produtos comprados mes a mes divididos por filial\n"
               " 8 - Determinar o total de vendas registadas e o total faturado num intervalo de meses\n"
               " 9 - Determinar os codigos dos clientes de uma filial que compraram um produto\n"
               "10 - Determinar uma lista dos produtos mais comprados num determinado mes por um cliente\n"
               "11 - Determinar os N produtos mais vendidos durante um ano\n"
               "12 - Determinar os codigos dos N produtos que um cliente gastou mais dinheiro durante um ano\n"
               "13 - Apresentar os resultados de leitura dos ficheiros da Opção 1\n\n"
          );
}

int alldigits(char* buf){
	int flag=1;
	for(int i=0;i<strlen(buf)&&flag;i++){
		flag=isdigit(buf[i]);
	}
	return flag;
}

int toint(char* buf){
	if(!alldigits(buf)){
		if ((buf[0]=='q' || buf[0]=='Q') && buf[1]=='\0')return 0;
		else return -1;
	}
	else return atoi(buf);
}

void flush(){
	printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
}

int main(){
	//interpertador();




	
	char buf[10];
	int op;
	do{
		flush();
		prettyprintmenu();

		printf("A sua opção: ");
		scanf("%10s",buf);
		//printf("\n");
		getchar();//gets the enter

		op=toint(buf);
		//printf("%d\n", op);
		
		switch (op){
			case 0:
				printf("Exiting...\n");
				break;
			case 1:
				printf("1\n");
				break;
			case 2:
				printf("2\n");
				break;
			case 3:
				printf("3\n");
				break;
			case 4:
				printf("4\n");
				break;
			case 5:
				printf("5\n");
				break;
			case 6:
				printf("6\n");
				break;
			case 7:
				printf("7\n");
				break;
			case 8:
				printf("8\n");
				break;
			case 9:
				printf("9\n");
				break;
			case 10:
				printf("10\n");
				break;
			case 11:
				printf("11\n");
				break;
			case 12:
				printf("12\n");
				break;
			case 13:
				printf("13\n");
				break;
			default:
				printf("Erro na escolha de opção\n");
				break;
		}

		printf("\n");
		printf("press Enter to continue");
		getchar();//pausa para observar o resultado
	}while(op);
	



	//p = getProductsSalesAndProfit( sgv, "BR1925", 2, 1);
	//p = getProductsNeverBought(sgv,1);
	//p = getClientsOfAllBranches(sgv);
	

	//distroySGV(sgv);
	return 0;
}