/* File clientes  */
#ifndef _CLIENTES_
#define _CLIENTES_

<<<<<<< HEAD
#define HSIZE 26

typedef struct elem{
	int used;
	char *id;
}Elem;

typedef struct bucket{
	int size;
	Elem *arr;
} Bucket ;


typedef struct thash{
	int size;
	Bucket *tbl;
} THash;


//typedef Bucket THash[HSIZE];

int ler_clientes (THash *cliente, int size);
//void swapc(Elem *arg1, Elem *arg2);
void quicksortc(Elem *args, unsigned int len);

=======
int ler_clientes (char ***cliente, int size);
//void sortcliente(char ***cliente, int c);
int comparaclientes(char *c1,char *c2);
void swapc(char **arg1, char **arg2);
void quicksortc(char **args,int len);
void imprimecliente(char **cliente, int c);
>>>>>>> 42add232be9b05b1817bf6959b7ed44623f85761
int validacliente(char *cliente);
int letra_cl(THash *cliente, char *letra);


int hash(char *cliente);
//void initTab(THash *h);
THash* initTab();
void acrecenstaTab(THash *h, char *cliente);
void imprimecliente(THash *h);
void distroiTab(THash *h);


void escrever_c(THash *h , char *s);
//void ultimoElemTB(THash *h, char* r);


#endif /* _CLIENTES_ */