#ifndef _QUERIES_
#define _QUERIES_


typedef struct sgv{
	THash *produtos;
	THash *clientes;
	//TVendas *vendas;
	Fat *fat;
	Filial *fil;
} *SGV;


SGV initSGV();
SGV loadSGVFromFiles(SGV sgv, char *filespath );
int getProductsStartedByLetter(SGV sgv, char letter);
/*int getProductsSalesAndProfit( SGV sgv, char *productID, int month, int porfil);
int getProductsNeverBought(SGV sgv , int branchID);
int getClientsOfAllBranches(SGV sgv);

*/
double* getProductsSalesAndProfit( SGV sgv, char *productID, int month);


#endif /* _QUERIES_ */
