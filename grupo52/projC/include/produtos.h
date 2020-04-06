/* File produtos  */
#ifndef _PRODUTOS_
#define _PRODUTOS_

#define HSIZE 26

typedef struct thash THash;

int hash_p(char *cont);

int ler_prod(THash *prod, char *filespath );
void quicksortp(char **args, unsigned int len);

THash* initTab_p();
void destroiTab_p(THash *h);

void acrecenstaTab_p(THash *h, char *produto);

int validaproduto(char *produto);
char* getProduto(THash *p, int key, int i);
char** getArrayProd(THash *p, int key);
int getArrayProdSize(THash *p, int key);

char** badgetArrayProd(THash *p, int key);


#endif /* PRODUTOS */

