/* Demonstracao de problema de concorrencia */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <pthread.h>
#include <assert.h>

#define MAX 2000

int n;

void f1(void *argp)
{
     int i;
     for (i = 0; i < MAX; i++) 
	  n++;
}

void f2(void *argp)
{
     int i;
     for (i = 0; i < MAX; i++) 
	  n--;
}

int main(void)
{
    pthread_t t1, t2;
    int rc;

    n = 0;
    rc = pthread_create(&t1, NULL, (void *) f1, NULL);  assert(rc == 0);
    rc = pthread_create(&t2, NULL, (void *) f2, NULL);  assert(rc == 0);
    rc = pthread_join(t1, NULL);  assert(rc == 0);
    rc = pthread_join(t2, NULL);  assert(rc == 0);
    printf("n=%d\n", n);
    return 0;
}
