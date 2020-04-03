/* File produtos  */
#ifndef _PRODUTOS_
#define _PRODUTOS_

int validaproduto(char *produto);
int ler_prod(THash *prod, char *filespath );
char* getProduto(THash *p, int key, int i);
char** getArrayProd(THash *p, int key);

#endif /* PRODUTOS */
