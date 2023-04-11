#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


int main(void){
    pid_t f1, f2, f3, f4;

    printf("A\n");
    f1 = fork();
    if(f1 == 0){
        printf("B\n");
        f2 = fork();
        if(f2 == 0){
            printf("C\n");
            f3 = fork();
            if(f3 == 0){
            printf("D\n");
            }
        }
    }
    

    return 0;
}