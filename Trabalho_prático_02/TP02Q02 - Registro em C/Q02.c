#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>


typedef struct Serie{

    char nome[50];
    char formato[50];
    char duracao[50];
    char paisDeOrigem[50];
    char idiomaOriginal[50];
    char emissora[50];
    char transmissaoOriginal[50];
    int numTeporadas;
    int numEpisodios;

}Serie;


//construtores
void Construtor1 (Serie *s){
    strcpy(s -> nome, "");
    strcpy(s -> formato, "");
    strcpy(s -> duracao, "");
    strcpy(s -> paisDeOrigem, "");
    strcpy(s -> idiomaOriginal, "");
    strcpy(s -> emissora, "");
    strcpy(s -> transmissaoOriginal, "");
    s -> numTeporadas = 0;
    s -> numEpisodios = 0;
}

void Construtor2 (Serie *s, char nome[], char formato[], char duracao[], char paisDeOrigem[], char idiomaOriginal[], char emissora[], char transmissaoOriginal[], int numTeporadas, int numEpisodios){
    strcpy(s -> nome, nome);
    strcpy(s -> formato, formato);
    strcpy(s -> duracao, duracao);
    strcpy(s -> paisDeOrigem, paisDeOrigem);
    strcpy(s -> idiomaOriginal, idiomaOriginal);
    strcpy(s -> emissora, emissora);
    strcpy(s -> transmissaoOriginal, transmissaoOriginal);
    s -> numTeporadas = numTeporadas;
    s -> numEpisodios = numEpisodios;
}


//setters
void setNome(Serie *s, char nome[]){
    strcpy(s -> nome, nome);
}

void setFormato(Serie *s, char formato[]){
    strcpy(s -> formato, formato);
}

void setDuracao(Serie *s, char duracao[]){
    strcpy(s -> duracao, duracao);
}

void setPaisDeOrigem(Serie *s, char paisDeOrigem[]){
    strcpy(s -> paisDeOrigem, paisDeOrigem);
}

void setIdiomaOriginal(Serie *s, char idiomaOriginal[]){
    strcpy(s -> idiomaOriginal, idiomaOriginal);
}

void setEmissora(Serie *s, char emissora[]){
    strcpy(s -> emissora, emissora);
}

void setTransmissaoOriginal(Serie *s, char transmissaoOriginal[]){
    strcpy(s -> transmissaoOriginal, transmissaoOriginal);
}

void setnumTeporadas(Serie *s, int numTeporadas){
    s -> numTeporadas = numTeporadas;
}

void setNumEpisodios(Serie *s, int numEpisodios){
    s -> numEpisodios = numEpisodios;
}


//getters
char* getNome(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.nome);
    return resp;
}

char* getFormato(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.formato);
    return resp;
}

char* getDuracao(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.duracao);
    return resp;
}

char* getPaisDeOrigem(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.paisDeOrigem);
    return resp;
}

char* getIdiomaOriginal(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.idiomaOriginal);
    return resp;
}

char* getEmissora(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.emissora);
    return resp;
}

char* getTransmissaoOriginal(Serie s){
    char string[100] = {};
    char *resp = string;
    strcpy(resp, s.transmissaoOriginal);
    return resp;
}

int getNumEpisodios(Serie s){
    return s.numEpisodios;
}

int getNumTeporadas(Serie s){
    return s.numTeporadas;
}


//clone 
Serie Clone(Serie s){
    Serie resp;

    strcpy(resp.nome, s.nome);
    strcpy(resp.formato, s.formato);
    strcpy(resp.duracao, s.duracao);
    strcpy(resp.paisDeOrigem, s.paisDeOrigem);
    strcpy(resp.idiomaOriginal, s.idiomaOriginal);
    strcpy(resp.emissora, s.emissora);
    strcpy(resp.transmissaoOriginal, s.transmissaoOriginal);
    resp.numEpisodios = s.numEpisodios;
    resp.numTeporadas = s.numTeporadas;

    return resp;
}


//retira as tags de uma string
char* tiraTags(char *linha){
    char string[100] = {};
    char *novaLinha = string;

    int i=0, j=0;

    while(i < strlen(linha)){
        if(linha[i] == '<'){
            i++;
            while(linha[i] != '>'){
                i++;
            }
        }else{
            novaLinha[j] = linha[i];
            j++;
        }
        i++;
    }

    novaLinha[j] = '\0';

    return novaLinha;
}

//transforma um numero em formato String para formato int
    int transformaEmInteiro(char s[]){
        int num = 0;
        double mult = 0;
        if(strlen(s)>=1){
            num += ((int) s[strlen(s)-1]) - 48;
        }
        if(strlen(s)>=2){
            num += (((int) s[strlen(s)-2]) - 48) * 10;
        }
        if(strlen(s)>=3){
            num += (((int) s[strlen(s)-3]) - 48) * 100;
        }
        if(strlen(s)>=4){
            num += (((int) s[strlen(s)-4]) - 48) * 1000;
        }

        return num;
    }

//retira todos os caracteres nao numeros de uma string
    char* soDeixaNumeros(char s[]){
        char string[100] = {};
        char *novaLinha = string;
        int j = 0;

        for(int i=0; i<strlen(s); i++){
            if(s[i]>='0' && s[i]<='9'){
                novaLinha[j] = s[i];
                j++;
            }
        }

        novaLinha[j] = '\0';
        return novaLinha;
    }

//retira espacos no inicio ou final da string
    char* trim(char s[]) {
        char string[100] = {};
        char *novaLinha = string;
        int posInicio=0, posFinal=strlen(s)-1;
        int j=0;

        for(int i=0; i<strlen(s); i++){
            if(s[i] != ' '){
                posInicio = i;
                i = strlen(s);
            }
        }
        
        for(int i=strlen(s)-1; i>0; i--){
            if(s[i]!=' '){
                posFinal = i;
                i = 0;
            }
        }

        for(int i=posInicio; i<=posFinal; i++){
            novaLinha[j] = s[i];
            j++;
        }
    
        novaLinha[j] = '\0';
        return novaLinha;
    }

//retira informacoes disnecessarias como "&#160;"
    char* retiraSpan(char s[]) {
        char string[100] = {};
        char *novaLinha = string;
        int j=0;

        for(int i=0; i<strlen(s); i++){
            if(s[i]=='&'){
                i++;
                while(s[i]!=';'){
                    i++;
                }
            }else{
                novaLinha[j] = s[i];
                j++;
            }
        }

        novaLinha[j] = '\0';
        return novaLinha;
    }

//formata o nome da serie
    char* formataNome(char nome[]){
        char string[100] = {};
        char *novoNome = string;
        int j=0;

        for(int i=0; i<strlen(nome); i++){
            if(nome[i]=='_'){
                novoNome[j] = ' ';
                j++;
            }else if(nome[i]=='.' && nome[i+1]=='h' && nome[i+2]=='t' && nome[i+3]=='m' && nome[i+4]=='l'){
                i += 5;
            }else{
                novoNome[j] += nome[i];
                j++;
            }
        }

        novoNome[j] = '\0';
        return novoNome;
    }

//retira quebras de linha de uma string
    char* retiraQuebraDeLinha(char s[]){
        char string[100] = {};
        char *novaLinha = string;
        int j=0;

        for(int i=0; i<strlen(s); i++){
            if(s[i]=='\n'){
                i++;
            }else{
                novaLinha[j] = s[i];
                j++;
            }
        }

        novaLinha[j] = '\0';
        return novaLinha;

    }


//metodo ler
void ler(Serie *s, char entrada[]){
    
    char arquivo[100] = "/tmp/series/";
    strcat(arquivo, entrada);
    
    FILE *arq = fopen(arquivo, "r");
        char aux[200];
        char aux2[200];


        char buf[2000]; 
        fgets(buf, 2000, arq);

        //nome
        setNome(s, formataNome(entrada));

        //formato
        while(strstr(buf, "Formato") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        setFormato(s, tiraTags(buf));

        //Duração
        while(strstr(buf, "Duração") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        setDuracao(s, tiraTags(buf));

        //País de origem
        while(strstr(buf, "País de origem") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        strcpy(aux, tiraTags(buf));
        strcpy(aux2, trim(aux));
        setPaisDeOrigem(s, retiraSpan(aux2));

        //Idioma original
        while(strstr(buf, "Idioma original") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        strcpy(aux, tiraTags(buf));
        setIdiomaOriginal(s, trim(aux));

        //Emissora
        while(strstr(buf, "Emissora") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        strcpy(aux,tiraTags(buf) );
        setEmissora(s, trim(aux));
        

        //Transmissão original
        while(strstr(buf, "Transmissão original") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        strcpy(aux, tiraTags(buf));
        strcpy(aux2, trim(aux));
        setTransmissaoOriginal(s, retiraSpan(aux2));

        //temporadas
        while(strstr(buf, "temporadas") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        strcpy(aux, tiraTags(buf));
        strcpy(aux2, soDeixaNumeros(aux));
        setnumTeporadas(s, transformaEmInteiro(aux2));

        //episódios
        while(strstr(buf, "episódios") == NULL){
            fgets(buf, 2000, arq);
        }
        fgets(buf, 2000, arq);
        strcpy(aux, tiraTags(buf));
        strcpy(aux2, soDeixaNumeros(aux));
        setNumEpisodios(s, transformaEmInteiro(aux2));


    fclose(arq);
}


//metodo imprimir
void imprimir(Serie s){
    printf("%s ", retiraQuebraDeLinha(s.nome));
    printf("%s ", retiraQuebraDeLinha(s.formato));
    printf("%s ", retiraQuebraDeLinha(s.duracao));
    printf("%s ", retiraQuebraDeLinha(s.paisDeOrigem));
    printf("%s ", retiraQuebraDeLinha(s.idiomaOriginal));
    printf("%s ", retiraQuebraDeLinha(s.emissora));
    printf("%s ", retiraQuebraDeLinha(s.transmissaoOriginal));
    printf("%i ", s.numTeporadas);
    printf("%i\n", s.numEpisodios);
}




//checa a condicao de parada
int isFim(char string[]){
    if(strlen(string)==3 && string[0]=='F' && string[1]=='I' && string[2]=='M'){
        return 1;
    }else{
        return 0;
    }
}

int main (void){

    char entrada[100];
    scanf("%[^\n]s", entrada);
    scanf("%*[^\n]s"); scanf("%*c");

    Serie serie;
    int i=0;

    while( isFim(entrada) == 0){
        Construtor1(&serie);

        ler(&serie, entrada);

        imprimir(serie);

        scanf("%[^\n]s", entrada);
        scanf("%*[^\n]s"); scanf("%*c");
        
        i++;
    }
    
}



