#ifndef _FILIAL_
#define _FILIAL_

typedef struct qprd Qprd ;


typedef struct mesf Mesf;

typedef struct fili Fili;

typedef struct cl Cl;

typedef struct bucketf Bucketf ;

typedef struct filial Filial;

Filial* initFilial();
void destroiFilial(Filial *f);


void acrescenta_cl(Filial *f, char *p);
int acrescenta_cls (Filial *f, char **p, int tam );
int existe_fil(Cl *arr, char *procurado, int Tam);
void acrescentaPtoFil(Filial *h, char *p, int it, int a, int f, int m, char e, int qnt, int preco);
void acrescentaFil(Filial *h, char*p, double pr, int q, char e, char *c, int m, int f);

int getFilUsed(Filial *f, int k, int ip, int fil);
char* getCLiente(Filial *f, int k, int ip);
int getSizeArrClient(Filial *f, int k);
Cl* getArrByLetter(Filial *f, int key);
int getSizeQprd(Filial *f, int k, int id, int fil, int m);
int getQuantN(Filial *f, int k, int id, int fil, int m, int pid);
int getQuantP(Filial *f, int k, int id, int fil, int m, int pid);
int getGastoP(Filial *f, int k, int id, int fil, int m, int pid);
int getGastoN(Filial *f, int k, int id, int fil, int m, int pid);
char* getOneProd(Filial *f, int k, int id, int fil, int m, int p);


int ClientesSemCompras (Filial *f);

char** ClientsOfAllBranches (Filial *f, int *tam);


int QuantidadesUmClientePorMes(Filial *f, char *clienteID ,int fil, int mes);


#endif /* _FILIAL_ */