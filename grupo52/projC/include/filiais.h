#ifndef _FILIAL_
#define _FILIAL_



typedef struct qprd{
	char *pid;
	int qN;
	int qP;
} Qprd ;


typedef struct mesf{
	int size;
	Qprd *prs;
} Mesf ;

typedef struct fili{
	Mesf *mes;
} Fili;


typedef struct cl{
	char *cid;
	int u;
	Fili *fil;
} Cl;

typedef struct bucketf{
	int size;
	Cl *arr;
} Bucketf ;


typedef struct filial{
	int size;
	Bucketf *tbl;
} Filial;

Filial* initFilial();
void acrescenta_cl(Filial *f, char *p);
int acrescenta_cls (Filial *f, char **p, int tam );
int existe_fil(Cl *arr, char *procurado, int Tam);
void acrescentaPtoFil(Filial *h, char *p, int it, int a, int f, int m, char e, int qnt);
void acrescentaFil(Filial *h, char*p, double pr, int q, char e, char *c, int m, int f);


#endif /* _FILIAL_ */