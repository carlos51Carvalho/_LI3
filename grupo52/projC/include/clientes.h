/* File clientes  */
#ifndef _CLIENTES_
#define _CLIENTES_

int ler_clientes (char ***cliente, int size);
//void sortcliente(char ***cliente, int c);
int comparaclientes(char *c1,char *c2);
void swapc(char **arg1, char **arg2);
void quicksortc(char **args,unsigned int len);
void imprimecliente(char **cliente, int c);
int validacliente(char *cliente);
int letra_cl(char **cliente, char letra, int size);

#endif /* _CLIENTES_ */