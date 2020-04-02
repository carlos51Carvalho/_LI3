/* File clientes  */
#ifndef _CLIENTES_
#define _CLIENTES_

#define HSIZE 26

typedef struct bucket{
	int size;
	char **arr;
} Bucket ;


typedef struct thash{
	int size;
	Bucket *tbl;
} THash;



int ler_clientes (THash *cliente , char *filespath);
void quicksortc(char **args, unsigned int len);

int validacliente(char *cliente);
int letra_cl(THash *cliente, char *letra);


int hash(char *cliente);
THash* initTab();
void destroiTab(THash *h);
void acrecenstaTab(THash *h, char *cliente);
void imprimecliente(THash *h);
void destroiTab(THash *h);


void escrever_c(THash *h , char *s);


#endif /* _CLIENTES_ */
