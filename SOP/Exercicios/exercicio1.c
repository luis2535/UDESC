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
void *QuadraticNumber(void *arg) {
   long n;
   long tid = (long)arg;
   n = tid * tid;
   printf("Numero quadrado: %ld, thread %ld\n",n, tid);
   pthread_exit(NULL);
}

int main (int argc, char *argv[]) {
   pthread_t threads[NUM_THREADS + 3];
   int rc;
   long t;
   long i = 0;
   for (t=0; t<NUM_THREADS + 3; t++){

      if(t < NUM_THREADS){
      printf("main: criando thread %ld\n", i);
      i++;
      rc = pthread_create(&threads[t], 
                          NULL, 
                          PrintHello, 
                          (void *)i);
      if (rc) {
         printf("ERRO - rc=%d\n", rc);
         exit(-1);
      }
      }
      printf("main: criando thread %ld\n", i);
      i++;
      rc = pthread_create(&threads[t],
                           NULL,
                           QuadraticNumber,
                           (void *)i);

   }
   
   /* Ultima coisa que main() deve fazer */
   pthread_exit(NULL); 
}
