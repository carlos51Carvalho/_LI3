
/**
 * @file interface.c
 * @brief Modulo que contém as funcções para apresentação das respostas as queries.
 *
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#include "queries.h"

#define LTAM 30

void flush(){
	printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
}

/*	return 1 para sair
	return 2 para imprimir header
	return 0 se nao entra no if
*/
int menudelistagem(int j, int limit){
	int i=0;
	char o;
	int f;
	if((j+1)%LTAM==0 && j+1<limit){
        printf("\nA mostrar de %d a %d\n\n", (j+2-LTAM),(j+1));
        printf("Pressione 'enter' para continuar ou 'q' + 'enter' para sair ");
        f=1;
        do{
        	o=getchar();
        	if(o=='q'||o=='\n')f=0;
        }while(f);
        
        if(o=='q')i=1;
        else i=2;
    }
    return i;
}

/*	imprime espaços para alinhar a pabela impressa
*/
void espacosparalistagem(int i){
	int j;
    if(i%LTAM!=0)for(j=0;j<LTAM-(i%LTAM);j++)printf("\n");
    printf("\nA mostrar de %d a %d\n", i==0?0:i-(i%LTAM)+1,i);

}

void q2header(char c,int i){
	flush();
    printf("O numero total de elementos começados pela letra %c é %d.\n", c, i);

}

void imprimeQ2(Q245 q, char op){
    int i;
    int sair=0;
    int limit=99999999;
    int rmenudelistagem;

    if (q->tam != -1){
    	q2header(op,q->tam);
        for ( i = 0;!sair && i < q->tam; i++){
            printf("%s\n",q->p[i]);

			rmenudelistagem=menudelistagem(i,limit);
			if(rmenudelistagem==1) sair=1;
			else if (rmenudelistagem==2) q2header(op,q->tam);
        }
    	
    	if(!sair) espacosparalistagem(i);
    
    }else printf("\n\n\nO caracter lido não é válido\n");
}

void imprimeQ3(Q3 q, int mes, int op ){
    if (q->vN1 != -1){

        int globN = q->vN1 + q->vN2 + q->vN3;
        int globP = q->vP1 + q->vP2 + q->vP3;
        double fN = q->fN1 + q->fN2 + q->fN3;
        double fP = q->fP1 + q->fP2 + q->fP3;
        printf("\n\n\nNo mês %d\n\n", mes);
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
        printf("\n\n\nO produto ou o mes inserido não sao válidos!\n");
    }

}

void q4header(int f,int t){
	flush();
    if(f <1 || f >3) printf("O número total global de produtos que ninguem comprou é : %d\n",t);
    else printf("O número total de produtos que ninguem comprou na filial %d é : %d\n",f,t);
}

void imprimeQ4(Q245 q, int f){
    int i,limit=999999;
    int sair=0,rmenudelistagem;

    q4header(f,q->tam);
    for ( i = 0;!sair && i < q->tam; i++){
        printf("%s\n",q->p[i]);

		rmenudelistagem=menudelistagem(i,limit);
		if(rmenudelistagem==1) sair=1;
		else if (rmenudelistagem==2) q4header(f,q->tam);
    }
    	
   	if(!sair) espacosparalistagem(i);

}

void q5header(int t){
	flush();
    printf("Existem %d clientes que fizeram compras nas 3 filiais \n",t);
}

void imprimeQ5(Q245 q){
    int i;
    int rmenudelistagem,sair=0,limit=999999;
    q5header(q->tam);
    for (i = 0;!sair && i < q->tam; i++){
        printf("%s\n",q->p[i]);

		rmenudelistagem=menudelistagem(i,limit);
		if(rmenudelistagem==1) sair=1;
		else if (rmenudelistagem==2) q5header(q->tam);
    }

   	if(!sair) espacosparalistagem(i);
}

void imprimeQ6(Q6 q){
    printf("\n\n\nO numero de clientes sem compras é: %d\nO numero de produtos que ninguem comprou é: %d\n",q->c,q->p);
}


void imprimeQ7(Q7 q){
    int i,j;
    if (q->use != -1){
    	printf("\n\n\n\nNumero total de produtos comprados por mês\n\n");
    	printf("     MES:");
    	for (i = 1; i <= 12; i++) printf("%5d",i);
    	printf("\n\n");
        
        for (i = 0; i < 3; i++){

            printf("FILIAL %d:", i+1);
            for ( j = 0; j < 11; j++) printf("%5d", q->f[i].m[j]);
			printf("%5d\n", q->f[i].m[j]);
            
        }
        printf("\n\n");
    }
    else{
        printf("\n\n\nO código de cliente que inseriu não é válido\n");
    }
}


void imprimeQ8(Q8 q, int us, int op){
    if(q->v != -1) printf("\n\n\nNo intervalo de meses entre %d e %d:\nForam feitas %d vendas e faturou-se %f euros\n", us,op,q->v,q->f);
    else printf("\n\n\nOs meses que inseriu não são válidos\n");
}

void imprimeQ9(Q9 q, int fil){
    if (q->sizeN != -1){
    
        int i, j;
        printf("\n\n\nNa filial %d:\n\n", fil);
        printf("Em modo N\t\tEm modo P\n");
        for (i = 0 , j = 0; i < q->sizeN || j< q->sizeP; i++,j++){
            printf("%9s\t\t%9s\n",i < q->sizeN?q->n[i]:"    ", j <q->sizeP?q->p[i]:"    ");
        }

        printf("\nTotal de %d\t\tTotal de %d\n\n", q->sizeN,q->sizeP);
    }
    else{
        printf("\n\n\nOu a filial inserida ou o código de produto estão errados\n");
    }   
}

void imprimeQ10(Q12 q, int mes){
    if(q->tam > 0){
        int i;
        printf("\n\n\nA lista de produtos que o cliente mais comprou e respetivas quantidades para o mês %d são:\n", mes);
        for (i = 0; i < q->tam; ++i){
            printf("%s  %d\n",q->arr[i].pid, q->arr[i].qnt );
        }
    }
    else if (q->tam == -1) printf("O cliente ou més inserido não são válidos\n");

    else printf("O cliente não realizou compras nesse mes \n");
}


void q11header(){
	flush();
    printf("Filial 1\t\t\t\t\tFilial 2\t\t\t\t\tFilial 3\n");
   	printf("%7s\t%10s\t%10s\t\t%7s\t%10s\t%10s\t\t%7s\t%10s\t%10s\n","Produto","Quantidade","Nº Cliente","Produto","Quantidade","Nº Cliente","Produto","Quantidade","Nº Cliente");
}

void imprimeQ11(Q11 q, int limit){
    int j;
    int sair=0;
    int rmenudelistagem;
 	q11header();

   	for ( j = 0;!sair && j < limit && (j < q->f[0].size || j < q->f[1].size || j < q->f[2].size); j++){
        if(j < q->f[0].size) printf("%7s\t%10d\t%10d",q->f[0].qts[j].pid, q->f[0].qts[j].quant, q->f[0].qts[j].clientes);
        else printf("\t\t\t\t");

        if(j < q->f[1].size) printf("\t\t%7s\t%10d\t%10d",q->f[1].qts[j].pid, q->f[1].qts[j].quant, q->f[1].qts[j].clientes);
        else printf("\t\t\t\t\t");
        
        if(j < q->f[2].size) printf("\t\t%7s\t%10d\t%10d\n",q->f[2].qts[j].pid, q->f[2].qts[j].quant, q->f[2].qts[j].clientes);
        else printf("\n");

        rmenudelistagem=menudelistagem(j,limit);
        if(rmenudelistagem==1) sair=1;
        else if (rmenudelistagem==2) q11header();

    }

    if(!sair) espacosparalistagem(j);

}

void q12header(int l,int t){
	flush();
	if(t>=l){
		if(l==1) printf("O produto em que o cliente mais gastou dinheiro durante um ano foi:\n");
		else printf("Os %d produtos em que o cliente mais gastou dinheiro durante um ano foram:\n", l);
	}
    else printf("O limite inserido (%d) supera o número de produtos comprados (%d). Os produtos em que o cliente mais gastou dinheiro durante um ano foram: \n", l,t);
}

void imprimeQ12(Q12 q, int limit){
	int i;
	int rmenudelistagem,sair=0;

    if(q->tam != -1){
    	q12header(limit,q->tam);
        for (i = 0;!sair && i < q->tam && i<limit; ++i){
            printf("%s  %f\n",q->arr[i].pid, q->arr[i].spent);

        	rmenudelistagem=menudelistagem(i,limit);
        	if(rmenudelistagem==1) sair=1;
        	else if (rmenudelistagem==2) q12header(limit,q->tam);
        }

    	if(!sair) espacosparalistagem(i);
    
    }else printf("O codigo do cliente introduzido não é válido\n");
}


void imprimeQ13(Q13 q){
	printf("\n\n\n");
    printf("Os produtos lidos: %d\nOs produtos validos: %d\nNome do ficheiro lido : %s\n\n",q->pl, q->pv, q->p);
    printf("Os clientes lidos: %d\nOs clientes validos: %d\nNome do ficheiro lido : %s\n\n",q->cl, q->cv, q->c);
    printf("As vendas lidas: %d\nAs vendas validas: %d\nNome do ficheiro lido : %s\n\n",q->vl, q->vv, q->v);
    printf("\n\n");
}


void prettyprintmenu(){
        printf(" _____________________________________________________________________________________________________________ \n"
               "|       Escolha um comando                                                                                    |\n"
               "| 1 - Ler os ficheiros (Produtos, Clientes e Vendas)                                                          |\n"
               "| 2 - Obter a lista e o no total de produtos começados por uma letra maiuscula                                |\n"
               "| 3 - Obter o numero total de vendas e o total faturado para determinado produto num mes                      |\n"
               "| 4 - Lista ordenada dos códigos dos produtos que ninguém comprou                                             |\n"
               "| 5 - Lista ordenada de códigos de clientes que realizaram compras em todas as filiais                        |\n"
               "| 6 - Obter o número de clientes que não realizaram compras e o numero de produtos que ningém comprou         |\n"
               "| 7 - Criar uma tabela com o numero total de produtos comprados mes a mes divididos por filial                |\n"
               "| 8 - Determinar o total de vendas registadas e o total faturado num intervalo de meses                       |\n"
               "| 9 - Determinar os codigos dos clientes de uma filial que compraram um produto                               |\n"
               "|10 - Determinar uma lista dos produtos mais comprados num determinado mes por um cliente                     |\n"
               "|11 - Determinar os N produtos mais vendidos durante um ano                                                   |\n"
               "|12 - Determinar os codigos dos N produtos que um cliente gastou mais dinheiro durante um ano                 |\n"
               "|13 - Apresentar os resultados de leitura dos ficheiros da Opção 1                                            |\n"
               "|_____________________________________________________________________________________________________________|\n\n"
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

    int firsttime=1;

    char enter;
    char buf[10];
    int op;
    do{
        flush();
        if(firsttime){
            firsttime=0;
            printf("%s\n\n\n", "Welcome to 𝓢𝓖𝓥");            
        }
        prettyprintmenu();

        printf("A sua opção: ");
        scanf("%10s",buf);
        getchar();

        op=toint(buf);
        flush();
		if(op>0)printf("A sua opção: %d\n",op);
        
        switch (op){
            case 0:
                printf("Exiting...\n\n");
                if(l ==1) distroySGV(sgv);
                flush();
                exit(0);
                break;

            case 1:
                if(l==1)distroySGV(sgv);
                printf("\n");
                getstr("Insira o file path para ler os Clientes: ",c);
                getstr("Insira o file path para ler os Produtos: ",p);
                getstr("Insira o file path para ler as Vendas: ",v);
                sgv = initSGV();
                sgv = loadSGVFromFiles(sgv, c, p, v );
                l=1;

            	printf("\n\n");
				q13=getCurrentFilesInfo(sgv);
                if(q13->pv>=0 && q13->cv>=0 && q13->vv>=0){
                	printf("Ficheiros lidos com sucesso\n");
                	l=1;
                }
                else{
                	printf("Erro na leitura dos ficheiros:\n");
                	l=0;
                	if(q13->pv<0)printf("\t->%s\n",q13->p);
                	if(q13->cv<0)printf("\t->%s\n",q13->c);
                	if(q13->vv<0)printf("\t->%s\n",q13->v);
                	printf("\nPor favor tente de novo.\n");
                    distroySGV(sgv);
                }
                destroiQ13(q13);

                printf("\n\n");
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
                if(x>0){
                	q1012=getClientTopProfitProducts(sgv,c,x);
                	imprimeQ12(q1012,x);
                	destroiQ12(q1012);
                }
                else printf("Intervalo não valido\n");
                
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

        if (l==0 && op>1) printf("\n\nFicheiros não lidos. Por favor use a opção 1.\n\n\n"); 

        printf("\n");
        printf("press Enter to continue");
        do{
            enter=getchar();
        }while(enter!='\n');
        
    }while(op);
}