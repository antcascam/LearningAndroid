#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
 
int main(int argc,char *argv[]) {
 
	if(argc!=3) {
		printf("uso: sen2.c [pid] [señal]\n");
		exit(EXIT_FAILURE);
	}
 
	if (kill(atoi(argv[1]), atoi(argv[2])) == -1) {
		perror("fallo en kill");
		exit(EXIT_FAILURE);
	}
}