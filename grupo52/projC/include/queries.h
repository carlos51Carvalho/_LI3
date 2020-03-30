#ifndef _QUERIES_
#define _QUERIES_


typedef struct sgv{
	THash *produtos;
	THash *clientes;
	//TVendas *vendas;
	FatP *fat;
} *SGV;


SGV initSGV();
SGV loadSGVFromFiles(SGV sgv, char *filespath );
int getProductsStartedByLetter(SGV sgv, char letter);
int getProductsSalesAndProfit( SGV sgv, char *productID, int month, int porfil);
int getProductsNeverBought(SGV sgv , int branchID);
int getClientsOfAllBranches(SGV sgv);





#endif /* _QUERIES_ */