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

//Estrutura 7

typedef struct byfil{
	int m[12];
} BYFil;

//tamanho 3
typedef struct q7{
	BYFil *f; 
} *Q7;



///////////////////////////////////////////


//Estrutura Querie 8
typedef struct q8{
	int v;
	double f;
}*Q8;


///////////////////////////////////////////

// Estrutura Querie 12
typedef struct spentonP{
	char *pid;
	double spent;
} SpentOnP;

typedef struct q12{
	int tam ;
	SpentOnP *arr;
}*Q12;


SGV initSGV();
SGV loadSGVFromFiles(SGV sgv, char *filespath );
int getProductsStartedByLetter(SGV sgv, char letter);
Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month);
//int getProductsNeverBought(SGV sgv , int branchID);
Q5 getClientsOfAllBranches(SGV sgv);
Q6 getClientsAndProductsNeverBoughtCount(SGV sgv);
Q7 getProductsBoughtByClient(SGV sgv, char *clientID);
Q8 getSalesAndProfif(SGV sgv, int minMonth, int maxMonth);



Q12 getClientTopProfitProducts(SGV sgv, char *clientID , int limit);
void queriesort(SpentOnP *args, int len);




#endif /* _QUERIES_ */
