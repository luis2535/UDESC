#include<stdlib.h>
#include<stdio.h>
#define n 50
struct Arvore
{
    int elem;
    struct Arvore *esq, *dir;
};
struct Arvore stack[n];
int cont = 0;
int vazia(){
    return cont;
}
void empilhar(struct Arvore *nodo){
    stack[cont] = *nodo;
    cont++;
}
struct Arvore *desempilhar(){
    cont--;
    return &stack[cont];
}

void imprimir(struct Arvore *arvore){
    struct Arvore *aux = arvore;
    while(cont != 0 || aux != NULL){
        while(aux != NULL){
            empilhar(aux);
            aux = aux->esq;
        }
        aux = desempilhar();
        printf("%d", aux->elem);
        aux = aux->dir;
    }
}