#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "queries.h"




void imprimeQ2(Q245 q, char *op){
    if (q->tam != -1){
        for (int i = 0; i < q->tam; i++){
            printf("%s\n",q->p[i]);
        }
        printf("O numero total de elementps começados pela letra %s é : %d\n", op, q->tam);
    }
    else printf("O caraer lido não é válido\n");
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
    for (int i = 0; i < q->tam; i++)
    {
        printf("%s\n",q->p[i]);
    }
    if(f <1 || f >3){
        printf("O numero total global de produtos que ninguem comprou é : %d\n",q->tam);
    }
    else printf("O numero total de produtos que ninguem comprou na filial %c é : %d\n",f,q->tam);
}

void imprimeQ5(Q245 q){
    for (int i = 0; i < q->tam; i++)
    {
        printf("%s\n",q->p[i]);
    }
    printf("%d dos clientes fizeram compras nas 3 filiais \n",q->tam);
}

void imprimeQ6(Q6 q){
    printf("O numero de clientes sem compras é: %d\nO numero de produtos que ninguem comprou é: %d\n",q->c,q->p);
}


void imprimeQ7(Q7 q){
    if (q->use != -1){
        printf("          MES :   1         2         3         4         5         6         7         8         9        10        11        12 \n\n\n\n");
        for (int i = 0; i < 3; i++)
        {
            printf("FILIAL %d:", i+1);
            for (int j = 0; j < 12; j++)
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
            printf("%s               %s\n",q->n[i]?q->n[i]:"    ", q->p[j]?q->p[i]:"    ");
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
        printf("A lista de produtos que o cliente mais comprou e respetivas quantidades para o mês %d são:\n", mes);
        for (int i = 0; i < q->tam; ++i)
        {
            printf("%s  %d\n",q->arr[i].pid, q->arr[i].qnt );
        }
    }
    if (q->tam == -1) printf("O cliente ou més inserido não são válidos\n");

    else printf("O cliente não realizou compras nesse mes \n");
}


void imprimeQ11(Q11 q){
    for (int i = 0; i < 3; i++)
    {
        printf("\n\n\nNa filial %d:\n",i+1 );
        for (int j = 0; j < q->f[i].size; j++){
            printf("%s  QNT:%d   nºclientes:%d\n", q->f[i].qts[j].pid, q->f[i].qts[j].quant, q->f[i].qts[j].clientes);
        }
    }
}
void imprimeQ12(Q12 q, int lim){
    if(q->tam != -1){
        printf("Os %d produtos em que o cliente mais gastou dinheiro durante um ano foram:\n", lim);
        for (int i = 0; i < lim; ++i)
        {
            printf("%s  %f\n",q->arr[i].pid, q->arr[i].spent );
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


void interpertador(){
    char opcao;
    SGV sgv = NULL;
    int l = 0;
    char *us;
    int z,x;
    char c[256];
    char p[256];
    char v[256];
    char s[256];
    /*
    sgv = initSGV();
    sgv = loadSGVFromFiles(sgv, "Dados_Iniciais/Clientes.txt", "Dados_Iniciais/Produtos.txt", "Dados_Iniciais/Vendas_1M.txt" );
    //imprimeQ2(getProductsStartedByLetter(sgv,'A'),'A');
    //imprimeQ3(getProductsSalesAndProfit(sgv,c,z),z,x)
    imprimeQ7(getProductsBoughtByClient(sgv, "A1231"));
    imprimeQ12(getClientTopProfitProducts(sgv, "A1231" ,6 ),6);
*/
    do {
        printf("Escolha um comando!\n"
               " 1  - Ler os ficheiros (Produtos, Clientes e Vendas)  \n"
               " 2  - Obter a lista e o no total de produtos começados por uma letra maiuscula \n"
               " 3  - Obter o numero total de vendas e o total faturado para determinado produto num mes \n"
               " 4  - Lista ordenada dos códigos dos produtos que ninguém comprou \n"
               " 5  - Lista ordenada de códigos de clientes que realizaram compras em todas as filiais\n"
               " 6  - Obter o número de clientes que não realizaram compras e o numero de produtos que ningém comprou\n"
               " 7  - Criar uma tabela com o numero total de produtos comprados mes a mes divididos por filial\n"
               " 8  - Determinar o total de vendas registadas e o total faturado num intervalo de meses\n"
               " 9  - Determinar os codigos dos clientes de uma filial que compraram um produto\n"
               " A - Determinar uma lista dos produtos mais comprados num determinado mes por um cliente\n"
               " B - Determinar os N produtos mais vendidos durante um ano\n"
               " C - Determinar os codigos dos N produtos que um cliente gastou mais dinheiro durante um ano\n"
               " D - Apresentar os resultados de leitura dos ficheiros da Opção 1\n\n"
               " COMANDO >");


        fgets(s, 256, stdin);
        switch (toupper(s[0])) {
            case '1' : {
                /*printf("Insira o file path para ler os Clientes:\n");
                scanf("%s", c);
                printf("Insira o file path para ler os Produtos:\n");
                scanf("%s", p);
                printf("Insira o file path para ler as Vendas:\n");
                scanf("%s", v);
                */
                if(l == 0){
                    sgv = initSGV();
                    sgv = loadSGVFromFiles(sgv, "Dados_Iniciais/Clientes.txt", "Dados_Iniciais/Produtos.txt", "Dados_Iniciais/Vendas_1M.txt" );
                    l =1;
                }
                else{
                    distroySGV(sgv);
                    sgv = initSGV();
                    printf("Insira o file path para ler os Clientes:\n");
                    scanf("%s", c);
                    printf("Insira o file path para ler os Produtos:\n");
                    scanf("%s", p);
                    printf("Insira o file path para ler as Vendas:\n");
                    scanf("%s", v);
                    sgv = loadSGVFromFiles(sgv, c, p, v );
                    l =1;
                }
                break;
            }

            case '2': {
                if(l ==1){
                    printf("Insira uma letra maiuscula:\n");
                    scanf("%c", us);
                    imprimeQ2(getProductsStartedByLetter(sgv,toupper(us)),us);
                }
                else printf("Leia os ficheiros primeiro!\n");
                break;
            }

            case '3': {
                if(l ==1){
                    printf("Insira um código de produto:\n");
                    scanf("%s", c);
                    printf("Insira um mês (1 a 12):\n");
                    scanf("%d", &z);
                    printf("Deseja o resultado por filial (1) ou o resultado global (2)\n");
                    scanf("%d",&x );
                    imprimeQ3(getProductsSalesAndProfit(sgv,c,z),z,x);
                }
                else printf("Leia os ficheiros primeiro!\n");
               
                break;
            }

            case '4': {
                 if(l ==1){
                    printf("Insira 0 para global, ou um numero entre 1 e 3 por filial\n");
                    scanf("%d", &z);
                    imprimeQ4(getProductsNeverBought(sgv,z), z);
                }
                else printf("Leia os ficheiros primeiro!\n");
                break;
            }
            case '5': {
                if(l ==1){
                imprimeQ5(getClientsOfAllBranches(sgv));
                }
                else printf("Leia os ficheiros primeiro!\n");
                break;
            }
            case '6': {
                if(l ==1){
                imprimeQ6(getClientsAndProductsNeverBoughtCount(sgv));
                }
                else printf("Leia os ficheiros primeiro!\n");
                break;
            }
            case '7': {
                if(l ==1){
                    printf("Insira um código de cliente:\n");
                    scanf("%s", c);
                    imprimeQ7(getProductsBoughtByClient(sgv, c));
                }
                else printf("Leia os ficheiros primeiro!\n");
                break;
            }
            case '8': {
                if(l ==1){
                    printf("Insira um mês (1 a 12):\n");
                    scanf("%d", &z);
                    printf("Insira um mês (1 a 12):\n");
                    scanf("%d", &x);
                    imprimeQ8(getSalesAndProfif(sgv,z,x),z,x);

                }
                else printf("Leia os ficheiros primeiro!\n"); 
                break;
            }

            case '9': {
                if(l ==1){
                    printf("Insira um código de produto:\n");
                    scanf("%s", c);
                    printf("Insira uma filial (1 a 3):\n");
                    scanf("%d", &z);
                    imprimeQ9(getProductBuyers(sgv, c, z),z);
                    

                }
                else printf("Leia os ficheiros primeiro!\n"); 
                break;
            }
        
            case 'A': {
                if(l ==1){
                    printf("Insira um código de cliente!\n");
                    scanf("%s", c);
                    printf("Insira um mês (1 a 12):\n");
                    scanf("%d", &x);
                    imprimeQ10(getClientFavouriteProducts(sgv, c, x),x);
                }
                else printf("Leia os ficheiros primeiro!\n"); 
                break;
            }
            case 'B': {
                if(l ==1){
                    printf("Insira um limite :\n");
                    scanf("%d", &z);
                    if (z >0){
                        imprimeQ11(getTopSelledProducts (sgv,z));
                    }
                    else printf("Intervalo não valido\n");
                }
                else printf("Leia os ficheiros primeiro!\n"); 
                break;
            }
            case 'C': {
                if(l ==1){
                    printf("Insira um código de cliente\n");
                    scanf("%s", c);
                    printf("Insira um limite :\n");
                    scanf("%d", &x);
                    imprimeQ12(getClientTopProfitProducts(sgv, c ,x ),x);
                }
                else printf("Leia os ficheiros primeiro!\n"); 
                break;
            }
            case 'D': {
                if(l ==1){
                    imprimeQ13(getCurrentFilesInfo(sgv));
                }
                else printf("Leia os ficheiros primeiro!\n"); 
                break;
            }

            
            case 'Q':
                if(l ==1) distroySGV(sgv);
                exit(0);

            default: {
                printf("Comando Invalido \n");
                break;
            }
        }
    } while (toupper(opcao) != 'Q');
}


