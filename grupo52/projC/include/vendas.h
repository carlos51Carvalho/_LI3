/* File vendas  */
#ifndef _VENDAS_
#define _VENDAS_

typedef struct venda {
	char *prod;
	double preco;
	int qnt;
	char est;
	char *cliente;
	int mes;
	int fil;
} Venda;

typedef struct tvendas {
	int size;
	Venda *arr;
}TVendas;



TVendas* initTv();
void acrescentaV(TVendas *v, char*p, double pr, int q, char e, char *c, int m, int f);
int ler_venda(FatP *fat, THash *cliente, THash *prod, char *filespath);
//void imprimevendas(TVendas *v);
//void escrever_v(TVendas *v , char *s);





/*



int validapreco(char *preco);
int valida3campo(char *campo);
int valida4campo(char *campo);
int valida6mes(char *campo);
int valida7filial(char *filial);
int existe(Elem *testado, char *nas_vendas, int Tam);

int vendas_1c(char **vendas, int size_v, char *cliente);
int vendas_fil1(char **vendas, int size_v, int filial);

int ler_venda(char ***vendas, int size,THash *cliente, THash *prod);


*/












/*
typedef struct listas Listas;

typedef struct venda{
	char *prod;
	float preco;
	int uni;
	char tipo;
	char *cliente;
	int mes;
	int fil;
}VENDA;

typedef struct vendas{
	int size;
	int ocup;
	VENDA *vendas;
}VENDAS;

*/

#endif /* _VENDAS_ */