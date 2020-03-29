
#ifndef _FAT_
#define _FAT_

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

#endif
