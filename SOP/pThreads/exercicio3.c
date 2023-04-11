#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS    5

void *ReturnSquare(void *arg) {
   long tid = (long)arg;
   long sqrt = tid * tid;
   printf("Valor da thread %ld ao quadrado: %ld\n", tid, sqrt);
   pthread_exit((void *) sqrt);
}

int main (int argc, char *argv[]) {
   pthread_t threads[NUM_THREADS];
   int rc;
   long t;
   long soma = 0;
   void *status;
   for (t=0; t<NUM_THREADS; t++){
      printf("main: criando thread %ld\n", t);
      rc = pthread_create(&threads[t], 
                          NULL, 
                          ReturnSquare, 
                          (void *)t);
      if (rc) {
         printf("ERRO - rc=%d\n", rc);
         exit(-1);
      }
      pthread_join(threads[t], &status);
      soma += (long) status;
   }
   printf("A soma das threads eh: %ld\n", soma);
   
   /* Ultima coisa que main() deve fazer */
   pthread_exit(NULL); 
}
