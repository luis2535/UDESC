#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS    5

void *PrintHello(void *arg) {
   long tid = (long)arg;
   printf("Alo da thread %ld\n", 
          tid);
   pthread_exit(NULL);
}
void *Quadrado(void *arg){
    long tid = (long)arg;
    long sqrt = tid * tid;
    printf("Valor da thread %ld ao quadrado: %ld\n", tid, sqrt);
    pthread_exit(NULL);
}

int main (int argc, char *argv[]) {
   pthread_t threads[2*NUM_THREADS+3];
   int rc;
   long t;
   for (t=0; t<NUM_THREADS; t++){
      printf("main: criando thread %ld\n", t);
      rc = pthread_create(&threads[t], 
                          NULL, 
                          PrintHello, 
                          (void *)t);
      if (rc) {
         printf("ERRO - rc=%d\n", rc);
         exit(-1);
      }
   }
   for (t=NUM_THREADS; t<2*NUM_THREADS+3; t++){
      printf("main: criando thread %ld\n", t);
      rc = pthread_create(&threads[t], 
                          NULL, 
                          Quadrado, 
                          (void *)t);
      if (rc) {
         printf("ERRO - rc=%d\n", rc);
         exit(-1);
      }
   }
   
   
   /* Ultima coisa que main() deve fazer */
   pthread_exit(NULL); 
}
