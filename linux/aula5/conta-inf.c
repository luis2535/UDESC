#include <stdio.h>
#include <unistd.h>

#define DELTA 100000000UL

int main(void) {
     unsigned long i;
     for (i = 1; i != 0; i++)
	  if (i % DELTA == 0) {
	       printf("Proc %u: %lu\n", getpid(), i / DELTA);
	       fflush(stdout);
	  }
     return 0;
}
