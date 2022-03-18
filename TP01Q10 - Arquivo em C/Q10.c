#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


int main(){
    int n;
    double aux;
    double num;

    FILE * arq = fopen("exemplo.txt", "wb");

    //Leitura da quantidade de valores
    scanf("%d", &n);  

    //Ler os numeros e colocar no arquivo
    for(int i = 0; i < n; i++){  
        scanf("%lf", &aux);
        fwrite(&aux, sizeof(double), 1, arq);
    }
    fclose(arq); 

    //Ler os numeros do arquivo e imprimir na ordem inverca
    FILE * arq2 = fopen("exemplo.txt", "r");

    for(int i = 1; i <= n; i++){  
        fseek(arq2,-i*(sizeof(double)),SEEK_END);
        fread(&num, sizeof(double), 1, arq2);
        printf("%g\n", num);
    }
    
    fclose(arq2);
    return 0;
}

