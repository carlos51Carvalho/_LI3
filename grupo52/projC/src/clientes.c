#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "clientes.h"


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

int hash(char *cont){
	int r = cont[0] - 'A';
	return r;
}


   /**
    * @brief Função que inicia uma estrutura Thash alocando espaço para todas as estruturas adjacentes
    *
    * @return THash*                             Devolve a THash inicializada
    */

THash* initTab(){
	int i;
	THash *h = malloc(sizeof(THash));
	h->size =HSIZE;
	h->tbl = malloc(HSIZE *sizeof(Bucket));
	for ( i =0; i < HSIZE ; i++){
		h->tbl[i].size = 0;
		h->tbl[i].arr = malloc(sizeof(char*));
	}
	return h;
}

   /**
    * @brief Funçao que destroi uma estrutura Thash libertando o espaço ocupado por esta
    *
    * @param THash *h                            THash previamente inicializada
    */

void destroiTab(THash *h){
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

void acrecenstaTab(THash *h, char *cont){
	int key = hash(cont);
	int tam = h->tbl[key].size;
	h->tbl[key].arr = realloc(h->tbl[key].arr, (tam +1)*sizeof(char*));
	h->tbl[key].arr[tam] = strdup(cont);
	h->tbl[key].size++;
	return ;
}


   /**
    * @brief Função que troca duas strings de posicao
    *
    * @param char **arg1                         String genérica
    * @param char **arg2                         String genérica
    *
    */

void swapc(char **arg1, char **arg2)
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



   /**
    * @brief Função que le de um ficheiro para uma Thash
    *
    * Recebendo uma Thash e um file path, lê de um ficheiro linha a linha e vai colocando cada linha na thash
    * na sua posição correspondente na mesma
    *
    * @param THash *cliente                      THash onde vai ser colocada a informação
    * @param char *filespath                     String com o file path
    * @param int *c                              Inteiro onde é guardado o numero de clientes lidos
    *
    * @return int                                O numero de clientes válidos
    */
int ler_clientes (THash *cliente , char *filespath, int *c){
	FILE *ficheiro = NULL;
	//char aux[80];
	//strcpy(aux, filespath); 
	//strcat(aux,"/Clientes.txt");
	char *chave=NULL;
	char linha[128];
	int i,cl=0;
	ficheiro = fopen(filespath, "r");

	if (ficheiro == NULL) return -1;

	for (i = 0 ; fgets(linha, sizeof(linha), ficheiro) ;){
		cl++;
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
	*c = cl;

	return i;
}



   /**
    * @brief Função que recebe uma string de um cliente e verifica se este é valido 
    *
    * @param char *cliente                       String (um cliente)
    *
    * @return int 
    */

int validacliente(char *cliente){
	return (cliente[0]>='A' && cliente[0]<='Z' && atoi(cliente+1)>=1000 && atoi(cliente+1)<=9999);
}


   /**
    * @brief Função que cria um clone de uma string cliente
    *
    * @param THash *c                            Tabela de onde é retirado o cliente
    * @param int key                             indice na tabela
    * @param int i                               indice no array
    *
    * @return char*                              Retorno da copia
    */
char* getCliente(THash *c, int key, int i){
	return strdup(c->tbl[key].arr[i]);
}


   /**
    * @brief Função que cria um clone de um array de strings cliente
    *
    * @param THash *c                            Tabela de onde é retirado o cliente
    * @param int key                             indice na tabela
    *
    * @return char**                             Retorno da copia
    */
char** getArrayCl(THash *c, int key){
	int tam = getArrayClSize(c,key);
	char **res = malloc(tam*sizeof(char*));

	for (int i = 0; i < tam; i++){
		res[i] = getCliente(c, key, i);
	}

	return res;
}

   /**
    * @brief Função que retorna o size um determinado array na tabela
    *
    * @param THash *c                            Tabela de onde é retirado o cliente
    * @param int key                             indice na tabela
    *
    * @return int                                Retorno do size
    */
int getArrayClSize(THash *c, int key){
	return (c->tbl[key].size);
}