#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#include "queries.h"




void imprimeQ2(Q245 q, char op){
    int i;
    if (q->tam != -1){
        for ( i = 0; i < q->tam; i++){
            printf("%s\n",q->p[i]);
        }
        printf("O numero total de elementps começados pela letra %c é : %d\n", op, q->tam);
    }
    else printf("O caracter lido não é válido\n");
}

void imprimeQ3(Q3 q, int mes, int op ){
    if (q->vN1 != -1){

        int globN = q->vN1 + q->vN2 + q->vN3;
        int globP = q->vP1 + q->vP2 + q->vP3;
        double fN = q->fN1 + q->fN2 + q->fN3;
        double fP = q->fP1 + q->fP2 + q->fP3;
        printf("No mês %d\n\n", mes);
        if ( op == 1){
            printf("Vendas na filial 1:\n");
            printf("Em modo N: %d\nFaturação em N: %f\nEm modo P: %d\nFaturação em P: %f \n\n",q->vN1, q->fN1, q->vP1, q->fP1);
            printf("Vendas na filial 2:\n");
            printf("Em modo N: %d\nFaturação em N: %f\nEm modo P: %d\nFaturação em P: %f \n\n",q->vN2, q->fN2, q->vP2, q->fP2);
            printf("Vendas na filial 3:\n");
            printf("Em modo N: %d\nFaturação em N: %f\nEm modo P: %d\nFaturação em P: %f \n\n",q->vN3, q->fN3, q->vP3, q->fP3);
        }
        else{
            printf("Vendas num modo global:\n");
            printf("Em modo N: %d\nFaturação em N: %f\nEm modo P: %d\nFaturação em P: %f \n\n",globN,fN,globP,fP);
        }
    }
    else{
        printf("O produto ou o mes inserido não sao válidos!\n");
    }

}

void imprimeQ4(Q245 q, int f){
    int i;
    for ( i = 0; i < q->tam; i++)
    {
        printf("%s\n",q->p[i]);
    }
    if(f <1 || f >3){
        printf("O numero total global de produtos que ninguem comprou é : %d\n",q->tam);
    }
    else printf("O numero total de produtos que ninguem comprou na filial %d é : %d\n",f,q->tam);
}

void imprimeQ5(Q245 q){
    int i;
    for (i = 0; i < q->tam; i++)
    {
        printf("%s\n",q->p[i]);
    }
    printf("%d dos clientes fizeram compras nas 3 filiais \n",q->tam);
}

void imprimeQ6(Q6 q){
    printf("O numero de clientes sem compras é: %d\nO numero de produtos que ninguem comprou é: %d\n",q->c,q->p);
}


void imprimeQ7(Q7 q){
    int i,j;
    if (q->use != -1){
        printf("          MES :   1         2         3         4         5         6         7         8         9        10        11        12 \n\n\n\n");
        for (i = 0; i < 3; i++)
        {
            printf("FILIAL %d:", i+1);
            for ( j = 0; j < 12; j++)
            {
                printf("      %4d", q->f[i].m[j]);
            }
            printf("\n\n\n\n\n\n");
        }
    }
    else{
        printf("O código de cliente que inseriu não é válido\n");
    }
}


void imprimeQ8(Q8 q, int us, int op){
    if(q->v != -1) printf("No intervalo de meses entre %d e %d:\nForam feitas %d vendas e faturou-se %f euros\n", us,op,q->v,q->f);
    else printf("Os meses que inseriu não são válidos\n");
}

void imprimeQ9(Q9 q, int fil){
    if (q->sizeN != -1){
    
        int i, j;
        printf("Na filial %d:\n", fil);
        printf("Em modo N:          Em modo P:\n");
        for (i = 0 , j = 0; i < q->sizeN || j< q->sizeP; i++,j++)
        {
            printf("%s               %s\n",i < q->sizeN?q->n[i]:"    ", j <q->sizeP?q->p[i]:"    ");
        }

        printf("\n\nTotal em N: %d\n", q->sizeN );
        printf("Total em P: %d\n\n", q->sizeP );
    }
    else{
        printf("Ou a filial inserida ou o código de produto estão errados\n");
    }   
}

void imprimeQ10(Q12 q, int mes){
    if(q->tam > 0){
        int i;
        printf("A lista de produtos que o cliente mais comprou e respetivas quantidades para o mês %d são:\n", mes);
        for (i = 0; i < q->tam; ++i)
        {
            printf("%s  %d\n",q->arr[i].pid, q->arr[i].qnt );
        }
    }
    else if (q->tam == -1) printf("O cliente ou més inserido não são válidos\n");

    else printf("O cliente não realizou compras nesse mes \n");
}



void imprimeQ11(Q11 q, int limit){
    int j;

   	printf("FILIAL 1\t\t\t\t\tFILIAL 2\t\t\t\t\tFILIAL3\n");
    printf("%5s\t%6s\t%10s\t\t%5s\t%6s\t%10s\t\t%5s\t%6s\t%10s\n","Prod.","Quant.","Nº Cliente","Prod.","Quant.","Nº Cliente","Prod.","Quant.","Nº Cliente");
    for ( j = 0;j < limit && (j < q->f[0].size || j < q->f[1].size || j < q->f[2].size); j++){
        if(j < q->f[0].size) printf("%5s\t%6d\t%10d",q->f[0].qts[j].pid, q->f[0].qts[j].quant, q->f[0].qts[j].clientes);
        else printf("\t\t\t\t");

        if(j < q->f[1].size) printf("\t\t%5s\t%6d\t%10d",q->f[1].qts[j].pid, q->f[1].qts[j].quant, q->f[1].qts[j].clientes);
        else printf("\t\t\t\t\t");
        
        if(j < q->f[2].size) printf("\t\t%5s\t%6d\t%10d\n",q->f[2].qts[j].pid, q->f[2].qts[j].quant, q->f[2].qts[j].clientes);
        else printf("\n");
    }

}
/*
void imprimeQ11(Q11 q, int limit){
    int i,j;

    for ( i = 0; i < 3; i++)
    { 
        if (q->f[i].size > limit)   
        {
            printf("\n\n\nNa filial %d:\n",i+1 );
            for ( j = 0; j < limit; j++){
                printf("%s  QNT:%d   nºclientes:%d\n", q->f[i].qts[j].pid, q->f[i].qts[j].quant, q->f[i].qts[j].clientes);
            }
        }
        else{
            printf("\n\n\nNa filial %d:\n",i+1 );
            for ( j = 0; j < q->f[i].size; j++){
                printf("%s  QNT:%d   nºclientes:%d\n", q->f[i].qts[j].pid, q->f[i].qts[j].quant, q->f[i].qts[j].clientes);
            }
        }
    }
}
*/

void imprimeQ12(Q12 q, int limit){
    if(q->tam != -1){
        int i;
        if(q->tam > limit){
            printf("Os %d produtos em que o cliente mais gastou dinheiro durante um ano foram:\n", limit);
            for (i = 0; i < limit; ++i){
                printf("%s  %f\n",q->arr[i].pid, q->arr[i].spent );
            }
        }
        else{
            printf("O limite inserido (%d) supera o tamanho porém os produtos em que o cliente mais gastou dinheiro durante um ano foram: \n", limit);
            for (i = 0; i < q->tam; ++i){
                printf("%s  %f\n",q->arr[i].pid, q->arr[i].spent );
            }
        } 
    }
    else printf("O codigo do cliente introduzido não é válido\n");
}


void imprimeQ13(Q13 q){
    printf("%s\n",q->p );
    printf("Os produtos lidos: %d\nOs produtos validos: %d\nNome do ficheiro lido : %s\n\n",q->pl, q->pv, q->p);
    printf("Os clientes lidos: %d\nOs clientes validos: %d\nNome do ficheiro lido : %s\n\n",q->cl, q->cv, q->c);
    printf("As vendas lidas: %d\nAs vendas validas: %d\nNome do ficheiro lido : %s\n\n",q->vl, q->vv, q->v);
}


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
	int i,flag=1;
	for( i=0;i<strlen(buf)&&flag;i++){
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

void getstr(char *text,char *s){
	printf("%s", text);
	scanf("%s", s);
	getchar();
}

void getint(char *text,int* i){
	printf("%s", text);
	scanf("%d", i);
	getchar();
}

void interpertador(){
	Q245 q245=NULL;
	Q3 q3=NULL;
	Q6 q6=NULL;
	Q7 q7=NULL;
	Q8 q8=NULL;
	Q9 q9=NULL;
	Q12 q1012=NULL;
	Q11 q11=NULL;
	Q13 q13=NULL;

    SGV sgv = NULL;
    int l = 0;
    char us;
    int z,x;
    char c[256];
    char p[256];
    char v[256];

    char enter;
    char buf[10];
	int op;
	do{
		flush();
		prettyprintmenu();

		printf("A sua opção: ");
		scanf("%10s",buf);
		getchar();

		op=toint(buf);
		
		switch (op){
			case 0:
				printf("Exiting...\n");
                if(l ==1) distroySGV(sgv);
				exit(0);
				break;

			case 1:
				if(l == 0){
                    sgv = initSGV();
                    sgv = loadSGVFromFiles(sgv, "Dados_Iniciais/Clientes.txt", "Dados_Iniciais/Produtos.txt", "Dados_Iniciais/Vendas_1M.txt" );
                    l =1;
                }
                else{
                    distroySGV(sgv);
                    sgv = initSGV();

					getstr("Insira o file path para ler os Clientes: ",c);
					getstr("Insira o file path para ler os Produtos: ",p);
					getstr("Insira o file path para ler as Vendas: ",v);

                    sgv = loadSGVFromFiles(sgv, c, p, v );
                    l =1;
                }
				break;

			case 2:
				if(l==0)break;

                printf("Insira uma letra maiuscula: ");
                scanf("%c", &us);
                getchar();

                q245=getProductsStartedByLetter(sgv,toupper(us));
                imprimeQ2(q245,us);
                destroiQ245(q245);

				break;

			case 3:
				if(l==0)break;

				getstr("Insira um código de produto: ",c);
				getint("Insira um mês (1 a 12): ",&z);
				getint("Deseja o resultado por filial (1) ou o resultado global (2)? ",&x);
				
				q3=getProductsSalesAndProfit(sgv,c,z);
                imprimeQ3(q3,z,x);
                destroiQ3(q3);

				break;

			case 4:
				if(l==0)break;

				getint("Insira 0 para global, ou um numero entre 1 e 3 por filial: ",&z);
				
				q245=getProductsNeverBought(sgv,z);
                imprimeQ4(q245, z);
                destroiQ245(q245);

				break;

			case 5:
				if(l==0)break;

				q245=getClientsOfAllBranches(sgv);
                imprimeQ5(q245);
                destroiQ245(q245);

				break;

			case 6:
				if(l==0)break;

				q6=getClientsAndProductsNeverBoughtCount(sgv);
                imprimeQ6(q6);
                destroiQ6(q6);

				break;

			case 7:
				if(l==0)break;

				getstr("Insira um código de cliente: ",c);

				q7=getProductsBoughtByClient(sgv, c);
                imprimeQ7(q7);
                destroiQ7(q7);

				break;

			case 8:
				if(l==0)break;

				getint("Insira um mês (1 a 12) para limite inferior: ",&z);
				getint("Insira um mês (1 a 12) para limite superior: ",&x);

				q8=getSalesAndProfif(sgv,z,x);
                imprimeQ8(q8,z,x);
                destroiQ8(q8);
				break;

			case 9:
				if(l==0)break;

				getstr("Insira um código de produto: ",c);
                getint("Insira uma filial (1 a 3): ",&z);

                q9=getProductBuyers(sgv, c, z);
                imprimeQ9(q9,z);
                destroiQ9(q9);

				break;

			case 10:
				if(l==0)break;

				getstr("Insira um código de cliente: ",c);
                getint("Insira um mês (1 a 12): ",&x);
				
				q1012=getClientFavouriteProducts(sgv, c, x);
	            imprimeQ10(q1012,x);
	            destroiQ12(q1012);

				break;

			case 11:
				if(l==0)break;
                    
                getint("Insira um limite : ",&z);
                if (z >0){
                	q11=getTopSelledProducts(sgv,z);
                    imprimeQ11(q11, z);
                    destroiQ11(q11);
                }
                else printf("Intervalo não valido\n");

				break;

			case 12:
				if(l==0)break;

				getstr("Insira um código de cliente: ",c);
                getint("Insira um limite: ",&x);

                q1012=getClientTopProfitProducts(sgv,c,x);
                imprimeQ12(q1012,x);
                destroiQ12(q1012);

				break;

			case 13:
				if(l==0)break;
                
                q13=getCurrentFilesInfo(sgv);
                imprimeQ13(q13);
                destroiQ13(q13);

				break;

			default:
				printf("Erro na escolha de opção\n");
				break;
		}

		if (l==0 && op>0) printf("Ficheiros não lidos. Por favor use a opção 1.\n"); 

		printf("\n");
		printf("press Enter to continue");
		do{
			enter=getchar();
		}while(enter!='\n');
		
	}while(op);
}


