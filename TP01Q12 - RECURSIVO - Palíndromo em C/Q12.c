#include<stdio.h>
#include<string.h>

/*
* se a comparação entre o caractere na posiçã inicial e na posição final correspondentes forem iguais em 
* todas as posições do vetor retorna 1, se não 0 
*/
int isPalindromo(char s[], int posicao){
    int aux = 0;
    //checa se todos os caracteres ja foram comparados
    if(posicao>=strlen(s)-1-posicao){
        return 1;
    }

    if(s[posicao] == s[strlen(s)-1-posicao]){
        aux = 1;
    }

    return(aux*isPalindromo(s, posicao+1));
}

//checa se a condição de parada (FIM) foi atendida
int isFim (char s[]){
    if(strlen(s)==3 && s[0]=='F' && s[1]=='I' && s[2]=='M'){
        return 1;
    }else{
        return 0;
    }
}


int main (void){
    char entrada[1000];
    
    do{
        scanf("%[^\n]", entrada);
        /* Limpar o buffer */
        scanf("%*[^\n]"); scanf("%*c");
        if(isFim(entrada)==0){
            if(isPalindromo(entrada, 0) == 1){
                printf("SIM\n");
            }else{
                printf("NAO\n");
            }
        }
    }while(isFim(entrada) == 0);
    
}