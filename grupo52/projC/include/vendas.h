/* File vendas  */
#ifndef _VENDAS_
#define _VENDAS_

void imprimevendas(char **vendas, int v);

int validapreco(char *preco);
int valida3campo(char *campo);
int valida4campo(char *campo);
//int valida6ou7campo(char *campo);
int valida6mes(char *campo);
int valida7filial(char *filial);
//int existeproduto(char **produto, char *prod, int p);
//int existeProduto (char **produto, char *prod_vendas, int Tam);
int existe(char **testado, char *nas_vendas, int Tam);
//int existecliente(char **clientes,char *cliente,int c);
int vendas_1c(char **vendas, int size_v, char *cliente);
int vendas_fil1(char **vendas, int size_v, int filial);
//int existe (char *emvendas, int ini, int fim, char **testado);

int ler_venda(char ***vendas, int size,char **cliente, char **prod, int c, int p);


void escrever(char **vendas , char *s, int size);


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