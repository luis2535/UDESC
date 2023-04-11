struct Arvore
{
    int elem;
    struct Arvore *esq, *dir;
};
void imprimir(struct Arvore *r)
{
    if (r != NULL) // O(1)
    {
    imprimir (r->esq);
    printf("%d ", r->elem); // O(1)
    imprimir (r->dir);
    }
}