#include<stdlib.h>
#include<stdio.h>
#include<time.h>

long long int fibRec(int n){
    if(n < 2){
        return n;
    }
    return fibRec(n-2) + fibRec(n-1);
}
long long int fibN(int n){
    long long int ultimo = 1;
    long long int penult = 1;
    if (n == 1){
        return penult;
    }else if(n == 2){
        return ultimo;
    }else{
        long long int atual = 0;
        for(int i = 2; i < n; i++){
            atual = ultimo + penult;
            penult = ultimo;
            ultimo = atual;
        }
        return atual;
    }
    
}

int main(){
    time_t inicial, final;
    double time_spent = 0.0;
    int n = 50;
    //printf("Digite o valor de N:");
    //scanf("%d",&n);
    //printf("O valor da %d-esima posicao de fibonacci eh: %lld\n", n, fib(n));
    

    //O(n)
    clock_t begin = clock();
    printf("%lld \n", fibN(n));
    clock_t end = clock();
    time_spent += (double)(end - begin) /CLOCKS_PER_SEC;
    printf("The elapsed time is %f seconds\n", time_spent);
    
    
    //Recursiva
    double time_spent2 = 0.0;
    clock_t begin2 = clock();
    printf("%lld \n", fibRec(n));
    clock_t end2 = clock();
    time_spent2 += (double)(end2 - begin2) /CLOCKS_PER_SEC;
    printf("The elapsed time is %f seconds\n", time_spent2);
    return 0;
}