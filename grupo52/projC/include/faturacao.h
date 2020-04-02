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
	int size;
	Bucketv *tbl;
} Fat;


//void quicksortFat(Prd *args, int len);
Fat* initFat();
//FatP* initFat();
void acrescenta_prod(Fat *f, char* p);
int acrescenta_prods (Fat *f,char **p, int tam );
void acrescentaFat(Fat *h, char*p, double pr, int q, char e, char *c, int m, int f);
//int acrescenta_prods (Fat *f,char *filespath );

//int acrescenta_prods (Fat *f,char **p , int s)
//void swapf(Ft *arr, int i1, int i2);
//void quicksortFat(Ft *args, unsigned int len);



#endif /* _FAT_ */

