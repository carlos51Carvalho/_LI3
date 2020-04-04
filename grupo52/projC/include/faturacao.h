#ifndef _FAT_
#define _FAT_

typedef struct mes{
	double fN;
	double fP;
	int vN;
	int vP;
} Mes ;

// mes size = 12
typedef struct fil{
	int used ;
	Mes *mes;
} Fil;


typedef struct prd{
	char *pid;
	Fil *fil;
} Prd;

//void == Fil arr[3] 
typedef struct bucketv{
	int size;
	Prd *arr;
} Bucketv ;


typedef struct fat{
	Bucketv *tbl;
} Fat;


//void quicksortFat(Prd *args, int len);
Fat* initFat();

void destroiFat(Fat *f);



void acrescenta_prod(Fat *f, char* p);
int acrescenta_prods (Fat *f,char **p, int tam );
void acrescentaFat(Fat *h, char*p, double pr, int q, char e, char *c, int m, int f);
//int acrescenta_prods (Fat *f,char *filespath );

//int acrescenta_prods (Fat *f,char **p , int s)
//void swapf(Ft *arr, int i1, int i2);
//void quicksortFat(Ft *args, unsigned int len);

int getPosicaoProd(Fat *fat,char *productID);
int getVendasN(Fat *fat,int h,int pos,int m,int f);
int getVendasP(Fat *fat,int h,int pos,int m,int f);
double getFaturacaoN(Fat *fat,int h,int pos,int m,int f);
double getFaturacaoP(Fat *fat,int h,int pos,int m,int f);
int getSizeArrayP(Fat *f, int key);
int getFilialUsed(Fat *f, int key, int ip, int fil);
char* getProdFat(Fat *f, int key, int ip);

int hashfat(char *cont);
//void MaisVendidos (Fat *f, Filiais *fi, int fil);
//char* getMaisVendidos(Fat *f, int fil, char **c, int pos);
//char* getMaisVendido(Fat *f, int fil, char **c);
char** neverBoughtFil(Fat *f, int fil, int *tamp);
char** neverBoughtAllFil(Fat *f, int *tamp);
int ProdutosNaoComprados (Fat *f);
int VendasIntervalo (Fat *f, int m1, int m2);
void FaturacaoeVendasIntervalo (Fat *f, int m1, int m2, int *result, double *result2);

int existe_fat(Prd *arr, char *procurado, int Tam);


#endif /* _FAT_ */

