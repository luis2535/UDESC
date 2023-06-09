#include "compiler.h"

int num_lin = 0;
int num_col = 0;
int cont_tokens = 0;
int count_label = 0;
int localizacoesUsadas = 0;
Token *tabela = NULL;
FILE *f;
typedef enum {INT_T, VOID_T} type_enum;
int varaiblesNum = 1; 


void retorno(){
    printf("%d", cont_tokens);
}

void TabelaToken(){
Token *aux;
	aux = (Token *)malloc(sizeof(Token));
	aux = tabela;

	FILE *filePointer;
    filePointer = fopen("Tabela_simbolos.txt", "w+");
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	fprintf(filePointer,"                               Tabela de Simbolos                              \n");
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	fprintf(filePointer, "\t\tToken\t\t|\t\tTipo\t\t|\t\tLinha\t\t|\t\tColuna\t\t|\t\tLocalizacao\n");
	for(int i = 0; i < cont_tokens; i++){
        printf("hello world");
        printf("\t%12s\t|\t%12s\t|\t%12i\t|\t%12i\t|\t%12i\t\n", aux->conteudo, aux->tipo, aux->linha, aux->coluna, aux->localizacao);
		fprintf(filePointer, "\t%12s\t|\t%12s\t|\t%12i\t|\t%12i\t|\t%12i\t\n", aux->conteudo, aux->tipo, aux->linha, aux->coluna, aux->localizacao);
		aux = aux->proximo;
	}
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	

}
void addTabela(char* conteudo, char* tipo, int linha, int coluna){
	if(tabela == NULL)
		tabela = createTabela(conteudo,tipo,linha,coluna);
	else{
		Token *aux = tabela;
		while(aux->proximo != NULL){
			aux = aux->proximo;
		}
		Token *novoToken;
		novoToken = createToken(conteudo, tipo, linha, coluna);
		novoToken->localizacao = findLocalizacao(conteudo);
		aux->proximo = novoToken;
		novoToken->anterior = aux;
	}
	num_col += strlen(conteudo);
}
Token *createTabela(char* conteudo, char* tipo, int linha, int coluna){
	cont_tokens++;
	Token *novaTabela;
	novaTabela = (Token *) malloc(sizeof(Token));
	strcpy(novaTabela->conteudo,conteudo);
	strcpy(novaTabela->tipo,tipo);
	novaTabela->linha = linha;
	novaTabela->coluna = coluna;
	novaTabela->proximo = NULL;
	novaTabela->anterior = NULL;
	novaTabela->localizacao = -1;
}
Token *createToken(char* conteudo, char* tipo, int linha, int coluna){
	cont_tokens++;
	Token *novoToken = (Token*)malloc(sizeof(Token));

	strcpy(novoToken->conteudo,conteudo);
	strcpy(novoToken->tipo,tipo);
	novoToken->linha = linha;
	novoToken->coluna = coluna;
	novoToken->proximo = NULL;
	novoToken->localizacao = -1;

}

int findLocalizacao(char *string){
	if(tabela == NULL)
		return -1;
	Token *aux = tabela;
	while(aux != NULL){
		if(strcmp(aux->conteudo, string) == 0)
			return aux->localizacao;
		aux = aux->proximo;
	}
}
