#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>

#define T_PAR 1
#define T_IMPAR 2

#define NELEM 2000000
int v[NELEM];

struct arg_st {
     int *v;
     char tipo;
};
void *conta(void *arg) 
{
    struct arg_st *argp = (struct arg_st *) arg;
    int *v = argp->v;
    char tipo = argp->tipo;
     long i, total = 0;
     
     for (i = 0; i < NELEM; i++) {
	  if ((tipo == T_PAR) && ((v[i] % 2) == 0))
	      total++;
	  else if ((tipo == T_IMPAR) && ((v[i] % 2) != 0))
	       total++;
     }
     pthread_exit((void *)total);
}

int main(int argc, char *argv[])
{
    pthread_t thread_par;
    pthread_t thread_impar;
    void *status;
     int i, pares, impares, rc;
     struct timeval tv_ini, tv_fim;
     unsigned long time_diff, sec_diff, usec_diff, msec_diff;
     struct arg_st arg_par, arg_impar;

     srandom(time(NULL));
     for (i = 0; i < NELEM; i++) {
	  v[i] = (int)random();
/*	  vetor[i] = i*2;*/
     }
     
     /* marca o tempo de inicio */
     rc = gettimeofday(&tv_ini, NULL);
     if (rc != 0) {
	  perror("erro em gettimeofday()");
	  exit(1);
     }

     /* faz o processamento de interesse */
     arg_par.v = v;
     arg_par.tipo = T_PAR;
     rc = pthread_create(&thread_par, NULL, conta,(void *)&arg_par);
     arg_impar.v = v;
     arg_impar.tipo = T_IMPAR;
     rc = pthread_create(&thread_impar, NULL, conta,(void *)&arg_impar);

     rc = pthread_join(thread_par, &status);
     pares = (long) status;
     rc = pthread_join(thread_impar, &status);
     impares = (long) status;
     /* marca o tempo de final */
     rc = gettimeofday(&tv_fim, NULL);
     if (rc != 0) {
	  perror("erro em gettimeofday()");
	  exit(1);
     }
     /* calcula a diferenca entre os tempos, em usec */
     time_diff = (1000000L*tv_fim.tv_sec + tv_fim.tv_usec) - 
  	         (1000000L*tv_ini.tv_sec + tv_ini.tv_usec);
     /* converte para segundos + microsegundos (parte fracion�ria) */
     sec_diff = time_diff / 1000000L;
     usec_diff = time_diff % 1000000L;
     
     /* converte para msec */
     msec_diff = time_diff / 1000;
     
     printf("O vetor tem %d numeros pares e %d numeros impares.\n", pares,
	    impares);
/*     printf("Tempo de execucao: %lu.%06lu seg\n", sec_diff, usec_diff);*/
     printf("Tempo de execucao: %lu.%03lu mseg\n", msec_diff, usec_diff%1000);
     pthread_exit(NULL);
}
