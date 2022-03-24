#include<stdio.h>
#include<string.h>

//se a string inverdida e a normal forem iguais, retorna true (1), se não, false (0)
int isPalindromo(char s[]){
    char inv[1000] = "";
    int tam = strlen(s);    

    //cria uma string invertida
    for(int i=0; i<=tam; i++){
        inv[tam-i-1] = s[i];
    }


    for(int i=0; i<tam; i++){
        if(inv[i] != s[i]){
            return 0;
        }
    }

    return 1;
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
            if(isPalindromo(entrada) == 1){
                printf("SIM\n");
            }else{
                printf("NAO\n");
            }
        }
    }while(isFim(entrada) == 0);
    
}