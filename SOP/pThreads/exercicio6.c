#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <errno.h>

#define N 100

int main(){
    pthread_t threads[N];
    void *status;
    int rc;
    long soma = 0;
}