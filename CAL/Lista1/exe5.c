struct Lista
{
    int elem;
    struct Lista *ptr;
};

struct Lista insere_Inicio(struct Lista *lista, int elemento){
    struct Lista *aux; // O(1)
    aux->elem = elemento; // O(1)
    aux->ptr = lista; // O(1)
    return *aux;
}
void insere_Fim(struct Lista *lista, int elemento){
    struct Lista *aux = lista->ptr; // O(1)
    while(aux->ptr != NULL) // O(N)
    { 
        aux = aux->ptr; // O(1) -> repetido n vezes
    }
    aux->elem = elemento; // O(1)
}