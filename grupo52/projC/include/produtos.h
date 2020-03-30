/* File produtos  */
#ifndef _PRODUTOS_
#define _PRODUTOS_

void imprimeprod(char **prod, int p);
int validaproduto(char *produto);
int ler_prod(THash *prod, char *filespath );
void acrecenstaUsado(THash *prod, int fil);


#endif /* PRODUTOS */