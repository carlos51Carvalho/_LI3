/* File clientes  */
#ifndef _CLIENTES_
#define _CLIENTES_

#define HSIZE 26

typedef struct thash THash;



int ler_clientes (THash *cliente , char *filespath);
void quicksortc(char **args, unsigned int len);

int validacliente(char *cliente);


int hash(char *cliente);
THash* initTab();
void destroiTab(THash *h);
void acrecenstaTab(THash *h, char *cliente);

char* getCliente(THash *c, int key, int i);
char** getArrayCl(THash *c, int key);
int getArrayClSize(THash *c, int key);

void escrever_c(THash *h , char *s);


#endif /* _CLIENTES_ */
