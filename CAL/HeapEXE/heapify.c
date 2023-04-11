#include<stdio.h>
#include<stdlib.h>


int left(int i) {return (2* i + 1);}
int right(int i) {return (2*i+2);}

void heapify(int *a, int n, int i){
    int e, d , max, aux;
    e = left(i);
    d=right(i);
    if(e < n && a[e] > a[i])
        max = e;
    else
        max = i;
    if(d < n && a[d] > a[max])
        max = d;
    if(max != i){
        aux = a[i];
        a[i] = a[max];
        a[max] = aux;
        heapify(a, n, max);
    }
}

void buildHeap(int *a, int n){
    int i;
    for(i = (n-1)/2; i>=0; i--){
        heapify(a, n, i);
    }
    
}
int main(){
    int i;
    int n = 8;
    int a[8] ={2, 5, 8, 13, 21, 1, 3, 34};
    
    buildHeap(a, n);
    printf("[ ");
    for (i = 0; i < n; i++){
        printf("%d ", a[i]);
    }
    printf("]\n");
    return 0;
}