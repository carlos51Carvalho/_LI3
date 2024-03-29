#ifndef _QUERIES_
#define _QUERIES_


typedef struct sgv *SGV;


/*Estrutura Querie 3*/
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



/*Estrutura Querie 245*/

typedef struct q245{
	char** p;
	int tam;
} *Q245;



/* Estrutura Querie 6*/

typedef struct q6{
	int p;
	int c;
} *Q6;



/*Estrutura 7*/

typedef struct byfil{
	int m[12];
} BYFil;

typedef struct q7{
	int use;
	BYFil *f; 
} *Q7;





/*Estrutura Querie 8*/

typedef struct q8{
	int v;
	double f;
}*Q8;



 

/*Estrutura Querie 9*/

typedef struct q9{
	int sizeN;
	int sizeP;
	char** n;
	char** p;
}*Q9;

 

/* Estrutura Querie 11*/
typedef struct qt{
	int clientes;
	int quant;
    char* pid;
}Qt;

typedef struct filiais{
	int size;
	Qt *qts;	
}Filiais;

typedef struct q11{
	Filiais *f;
}*Q11;




/* Estrutura Querie 10 e 12*/
typedef struct spentonP{
	char *pid;
	double spent;
	int qnt;
} QntNSpent;


typedef struct q12{
	int tam ;
	QntNSpent *arr;
}*Q12;




/*Estrutura Querie 13*/

typedef struct q13{
	int pv;
	int cv;
	int vv;
	int pl;
	int cl;
	int vl;
	char *p;
	char *c;
	char *v;
} *Q13;



SGV initSGV();
void distroySGV(SGV sgv);
SGV loadSGVFromFiles(SGV sgv, char *clientsFilePath, char *productsFilePath, char *salesFilePath );
Q245 getProductsStartedByLetter(SGV sgv, char letter);
Q3 getProductsSalesAndProfit( SGV sgv, char *productID, int month);
Q245 getProductsNeverBought(SGV sgv , int branchID);
Q245 getClientsOfAllBranches(SGV sgv);
Q6 getClientsAndProductsNeverBoughtCount(SGV sgv);
Q7 getProductsBoughtByClient(SGV sgv, char *clientID);
Q8 getSalesAndProfif(SGV sgv, int minMonth, int maxMonth);
Q9 getProductBuyers (SGV sgv, char *productID, int branch);
Q12 getClientFavouriteProducts(SGV sgv, char* clientID, int month);


void swapq11(Qt *args , int i1, int i2);
void q11sort(Qt *args, int len);
Q11 initQ11();
int existeProd(Qt *arr, char *procurado, int Tam);
Q11 toArray (SGV sgv);
void umCliente (SGV sgv, Q11 q, int k, int id);
Q11 getTopSelledProducts (SGV sgv, int limit);

Q12 getClientTopProfitProducts(SGV sgv, char *clientID , int limit);
Q12 initQ12();
void q12sort(QntNSpent *args, int len);
void q10sort(QntNSpent *args, int len);
int existe_q12(QntNSpent *arr, char *procurado, int Tam);

Q13 getCurrentFilesInfo(SGV sgv);
char* getReadFile(char *filePath);


void destroiQ245(Q245);
void destroiQ3(Q3);
void destroiQ6(Q6);
void destroiQ7(Q7);
void destroiQ8(Q8);
void destroiQ9(Q9);
void destroiQ12(Q12);
void destroiQ11(Q11);
void destroiQ13(Q13);



#endif /* _QUERIES_ */
