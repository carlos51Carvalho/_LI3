#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "produtos.h"


typedef struct bucket{
	int size;
	char **arr;
} Bucket ;


typedef struct thash{
	int size;
	Bucket *tbl;
} THash;

   /**
    * @brief Função que recebe uma string e subtrai ao primeiro elemnto da string a letra A
    *
    * @param char *cont                          String genérica
    *
    * @return int                                O resultado inteiro dessa subtração
    */
int hash_p(char *cont){
	int r = cont[0] - 'A';
	return r;
}

   /**
    * @brief Função que inicia uma estrutura Thash alocando espaço para todas as estruturas adjacentes
    *
    * @return THash*                             Devolve a THash inicializada
    */
THash* initTab_p(){
	int i;
	THash *h = malloc(sizeof(THash));
	h->size =HSIZE;
	h->tbl = malloc(HSIZE *sizeof(Bucket));
	for ( i =0; i < HSIZE ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = malloc(sizeof(char*));
		//printf("%d\n",h->tbl[i].size );
	}
	//printf("%d\n",i );
	return h;
}

   /**
    * @brief Funçao que destroi uma estrutura Thash libertando o espaço ocupado por esta
    *
    * @param THash *h                            THash previamente inicializada
    */
void destroiTab_p(THash *h){
	int i,j;
	for (i = 0; i < h->size; i++){
		for (j = 0; j < h->tbl[i].size; j++){
			free((h->tbl[i]).arr[j]);
		}
		free((h->tbl[i]).arr);
	}
	free(h->tbl);
	//free(h);                  //-> descobrir o porque de dar double free or corruption (out) -> dá este erro quando a funcao é usada pela THash prod (dunno why)
}


   /**
    * @brief Função que acrescenta a uma Thash uma derminada string
    *
    * Aplicando a função hash descobre a posicao correta desta na THash e sucessivamente realocando espaço para a adicionar á mesma
    *
    *
    * @param THash *h                            THash previamente inicializada
    * @param char *cont                          String genérica
    *
    */
void acrecenstaTab_p(THash *h, char *cont){
	int key = hash_p(cont);
	int tam = h->tbl[key].size;
	h->tbl[key].arr = realloc(h->tbl[key].arr, (tam +1)*sizeof(char*));
	h->tbl[key].arr[tam] = strdup(cont);
	h->tbl[key].size++;
	//printf("%s\n",h->tbl[key].arr[tam].id);
	return ;
}



   /**
    * @brief Função que troca duas strings de posicao
    *
    * @param char **arg1                         String genérica
    * @param char **arg2                         String genérica
    *
    */
void swapp(char **arg1, char **arg2)
{
    char *tmp = *arg1;
    *arg1 = *arg2;
    *arg2 = tmp;
}


   /**
    * @brief Função que ordena um array de strings
    *
    *
    * @param unsigned int len                    int tam generico
    * @param char **args                         String genérica
    *
    */
void quicksortp(char **args, unsigned int len)
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
            swapp(args+i, args+pvt++);
    }

    // move the pivot value into its place
    swapp(args+pvt, args+len-1);

    // and invoke on the subsequences. does NOT include the pivot-slot
    quicksortp(args, pvt++);
    quicksortp(args+pvt, len - pvt);
}





   /**
    * @brief Função que recebe uma string de um produto e verifica se este é valido 
    *
    * @param char *produto                       String (produto)
    *
    * @return int 
    */
int validaproduto(char *produto){ 
	return (produto[0]>='A' && produto[0]<='Z' && produto[1]>='A' && produto[1]<='Z' && atoi(produto+2)>=1000 && atoi(produto+2)<=9999);
}


   /**
    * @brief Função que le de um ficheiro para uma Thash
    *
    * Recebendo uma Thash e um file path, lê de um ficheiro linha a linha e vai colocando cada linha na thash
    * na sua posição correspondente na mesma
    *
    * @param THash *prod                         THash onde vai ser colocada a informação
    * @param char *filespath                     String com o file path
    * @param int *p                              Inteiro onde é guardado o numero de produtos lidos
    *
    * @return int                                O numero de produtos válidos
    */
int ler_prod (THash *prod,char *filespath, int *p ){
	FILE *ficheiro = NULL;
	//char aux[80];
	//strcpy(aux, filespath); 
	//strcat(aux,"/Produtos.txt");
	int pl =0;
	char *chave=NULL;
	char linha[128];
	int i;
	ficheiro = fopen(filespath, "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; fgets(linha, sizeof(linha), ficheiro) ; ){
		pl++;
		chave = strtok(linha, " \r\n");
		
		if (validaproduto(chave)){
			acrecenstaTab_p(prod, chave);
			i++;
		}
		
		//prod[i] =strdup(chave);
		chave = strtok(NULL, "\r\n");
	}


	fclose(ficheiro);

	int j;

	for (j = 0; j < prod->size ; j++){
		quicksortp(prod->tbl[j].arr, prod->tbl[j].size);
	}
	*p = pl;
	return i;
}



   /**
    * @brief Função que cria um clone de uma string produto
    *
    * @param THash *c                            Tabela de onde é retirado o produto
    * @param int key                             indice na tabela
    * @param int i                               indice no array
    *
    * @return char*                              Retorno da copia
    */
char* getProduto(THash *p, int key, int i){
	return strdup(p->tbl[key].arr[i]);
}


   /**
    * @brief Função que cria um clone de um array de strings produto
    *
    * @param THash *c                            Tabela de onde é retirado o produto
    * @param int key                             indice na tabela
    *
    * @return char**                             Retorno da copia
    */
char** getArrayProd(THash *p, int key){
	int tam = getArrayProdSize(p,key);
	char **res = malloc(tam*sizeof(char*));

	for (int i = 0; i < tam; i++){
		res[i] = getProduto(p, key, i);
	}

	return res;
}


   /**
    * @brief Função que retorna o size um determinado array na tabela
    *
    * @param THash *c                            Tabela de onde é retirado o produto
    * @param int key                             indice na tabela
    *
    * @return int                                Retorno do size
    */
int getArrayProdSize(THash *p, int key){
	return (p->tbl[key].size);
}
