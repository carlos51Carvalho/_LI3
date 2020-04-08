#ifndef _INTERFACE_
#define _INTERFACE_


/*Estrutura Querie 3*/
typedef struct q3 *Q3;


/*Estrutura Querie 245*/

typedef struct q245 *Q245;



/*Estrutura Querie 6*/

typedef struct q6 *Q6;


/*Estrutura Querie 7*/

typedef struct q7 *Q7;


/*Estrutura Querie 8*/

typedef struct q8 *Q8;

 

/*Estrutura Querie 9*/

typedef struct q9 *Q9;

/*Estrutura Querie 11*/

typedef struct q11 *Q11;


/*Estrutura Querie 12*/

typedef struct q12 *Q12;



/*Estrutura Querie 13*/

typedef struct q13 *Q13;


void imprimeQ2(Q245 q, char *op);
void imprimeQ3(Q3 q, int mes, int op );
void imprimeQ4(Q245 q, int f);
void imprimeQ5(Q245 q);
void imprimeQ6(Q6 q);
void imprimeQ7(Q7 q);
void imprimeQ8(Q8 q, int us, int op);
void imprimeQ9(Q9 q, int fil);
void imprimeQ10(Q12 q, int mes);
void imprimeQ11(Q11 q);
void imprimeQ12(Q12 q, int lim);
void imprimeQ13(Q13 q);

void interpertador();




#endif /* _INTERFACE_ */