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
	int used;
	Mesf *mes;
} Fili;


typedef struct cl{
	char *cid;
	Fili *fil;
} Cl;

typedef struct bucketf{
	int size;
	Cl *arr;
} Bucketf ;

//26 (26letras)
typedef struct filial{
	Bucketf *tbl;
} Filial;

Filial* initFilial();
void acrescenta_cl(Filial *f, char *p);
int acrescenta_cls (Filial *f, char **p, int tam );
int existe_fil(Cl *arr, char *procurado, int Tam);
void acrescentaPtoFil(Filial *h, char *p, int it, int a, int f, int m, char e, int qnt);
void acrescentaFil(Filial *h, char*p, double pr, int q, char e, char *c, int m, int f);

int getFilUsed(Filial *f, int k, int ip, int fil);
char* getCLiente(Filial *f, int k, int ip);
int getSizeArrClient(Filial *f, int k);
Cl* getArrByLetter(Filial *f, int key);
int getSizeQprd(Filial *f, int k, int id, int fil, int m);
int getQuantN(Filial *f, int k, int id, int fil, int m, int pid);
int getQuantP(Filial *f, int k, int id, int fil, int m, int pid);


int ClientesSemCompras (Filial *f);

char** ClientsOfAllBranches (Filial *f, int *tam);


int QuantidadesUmClientePorMes(Filial *f, char *clienteID ,int fil, int mes);

#endif /* _FILIAL_ */