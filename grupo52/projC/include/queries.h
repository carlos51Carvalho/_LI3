#ifndef _QUERIES_
#define _QUERIES_


typedef struct sgv{
	THash *produtos;
	THash *clientes;
	//TVendas *vendas;
	Fat *fat;
	Filial *fil;
} *SGV;


//Estrutura Querie 3
typedef struct q3{
	int vN1;
	int vP1;
	int vN2;
	int vP2;
	int vN3;
	int vP3;
	double fN1;
	double fP1;
	double fN2;
	double fP2;
	double fN3;
	double fP3;
}*Q3;

////////////////////////////////////////////////

//Estrutura Querie 4
/*
typedef struct q4{
	cahr **p;


};
*/

///////////////////////////////////////////////

//Estrutura Querie 5

typedef struct q5{
	char** c;
	int tam;
} *Q5;


/////////////////////////////////////////////

// Estrutura Querie 6

typedef struct q6{
	int p;
	int c;
} *Q6;

/////////////////////////////////////////////

//Estrutura Querie 8
typedef struct q8{
	int v;
	double f;
}*Q8;



SGV initSGV();
SGV loadSGVFromFiles(SGV sgv, char *filespath );
Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month);

Q5 getClientsOfAllBranches(SGV sgv);
/*int getProductsSalesAndProfit( SGV sgv, char *productID, int month, int porfil);
int getProductsNeverBought(SGV sgv , int branchID);
int getClientsOfAllBranches(SGV sgv);

*/
int getProductsNeverBought(SGV sgv , int branchID);


#endif /* _QUERIES_ */
