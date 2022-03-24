#include<stdio.h>
#include<string.h>

//checa se a string é composta por somente vogais
int soVogais(char frase[]){
    for(int i=0; i<strlen(frase); i++){
            if(frase[i] != 'a' && frase[i] != 'A' &&
             frase[i] != 'e' && frase[i] != 'E' &&
              frase[i] != 'i' && frase[i] != 'I' &&
              frase[i] != 'o' && frase[i] != 'O' &&
              frase[i] != 'u' && frase[i] != 'U'){
                return 0;
              }
        }

    return 1;
}

//checa se a string é composta por somente consoantes
int soConsoantes(char frase[]){

    for(int i=0; i<strlen(frase); i++){
        
        if((!(frase[i] >= 'a' && frase[i] <= 'z')) || 
            frase[i] == 'a' || frase[i] == 'A' ||
            frase[i] == 'e' || frase[i] == 'E' ||
            frase[i] == 'i' || frase[i] == 'I' ||
            frase[i] == 'u' || frase[i] == 'U' ||
            frase[i] == 'o' || frase[i] == 'O'
            ){
                return 0;
        }
    }
    return 1;
}

//checa se a string é um numero inteiro
int numInteiro(char frase[]){
    int numTabelaASCII;

    for(int i=0; i<frase[i]; i++){
        numTabelaASCII = (int) frase[i];
            
        //se tiver um caractere que nao é numero retorna false
        if(numTabelaASCII<48 || numTabelaASCII>57){
            return 0;
        }
    }

    return 1;   
}

//checa se a string é um numero real
int numReal(char frase[]){
    int numTabelaASCII, contVirgulaPonto=0, contNums=0;
        

        for(int i=0; i<strlen(frase); i++){
            numTabelaASCII = (int) frase[i];
            
            if((numTabelaASCII>48 && numTabelaASCII<57) || numTabelaASCII==46 || numTabelaASCII==44){
                //conta a quantidade de numeros e a quantidade de sinais de separação (ponto ou virgula)
                if(numTabelaASCII>48 && numTabelaASCII<57){
                    contNums++;
                }
                if(numTabelaASCII==46 || numTabelaASCII==44){
                    contVirgulaPonto++;
                }
            }else{
                return 0;
            }
        }

        //se eu tiver pelo menos 1 numero e no maximo 1 sinal de separação retorna true
        if(contNums>0 && contVirgulaPonto<2){
            return 1;
        }else{
            return 0;
        }
}

//Checa o comando de parada (FIM)
int isFim(char s[]){
     if(strlen(s)==3 && s[0]=='F' && s[1]=='I' && s[2]=='M'){
        return 1;
    }else{
        return 0;
    }
}

//entrada e saida padrão
int main (void){
    char entrada[1000];
    char saida[1000];

    do{
        //entrada padrão
        scanf("%[^\n]s", entrada);
        /* Limpar o buffer */
        scanf("%*[^\n]"); scanf("%*c");

        if(isFim(entrada)==0){
            if(soVogais(entrada) == 0){
                printf("NAO");
            }else{
                printf("SIM");
            }
            if(soConsoantes(entrada) == 0){
                printf(" NAO");
            }else{
                printf(" SIM");
            }
            if(numInteiro(entrada) == 0){
                printf(" NAO");
            }else{
                printf(" SIM");
            }
            if(numReal(entrada) == 0){
                printf(" NAO\n");
            }else{
                printf(" SIM\n");
            }
        }
        
    }while(isFim(entrada) == 0);
}