#ifndef _FAT_
#define _FAT_


typedef struct ft {
	char *p;
	double pr;
	int q;
	char e;
	char *cl;
	int m;
} Ft;

typedef struct bucketv {
	int size;
	Ft *arr;
}Bucketv;


typedef struct fill{
	Bucketv *p;
	Bucketv *c;
} Fill ;


typedef struct fatp{
	Fill *fil;
} FatP;


FatP* initFat();
void acrescentaFat(FatP *h, char*p, double pr, int q, char e, char *c, int m, int f);
void swapf(Ft *arr, int i1, int i2);
void quicksortFat(Ft *args, unsigned int len);



/*
typedef struct fat{
	double total;
	int univ;
	int cliented;
	int prodc;
}Fat;

Fat* initfat ();
void fatt(Fat *f, TVendas *v);
void univ(Fat *f, TVendas *v);
void cliented(Fat *f, TVendas *v, THash *clientes);
*/

#endif /* _FAT_ */

