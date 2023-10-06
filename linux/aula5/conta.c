#include <stdio.h>
#include <unistd.h>

#define DELTA 100000000UL
#define K1 1000UL
#define LIM 20*K1*K1*K1

int main(void) {
     unsigned long i;
     for (i = 1; i != LIM; i++)
	  if (i % DELTA == 0) {
	       printf("Proc %u: %lu\n", getpid(), i / DELTA);
	       fflush(stdout);
	  }
     return 0;
}
