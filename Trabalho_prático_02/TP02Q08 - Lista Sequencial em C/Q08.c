//Serie
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_FIELD_SIZE 100
#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"

typedef struct {
    char nome[MAX_FIELD_SIZE];
    char formato[MAX_FIELD_SIZE];
    char duracao[MAX_FIELD_SIZE];
    char pais[MAX_FIELD_SIZE];
    char idioma[MAX_FIELD_SIZE];
    char emissora[MAX_FIELD_SIZE];
    char transmissao[MAX_FIELD_SIZE];
    int num_temporadas;
    int num_episodios;
} Serie;

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

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) {
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size) {
    return freadline(line, max_size, stdin);
}

void print_serie(Serie *serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie->nome,
        serie->formato,
        serie->duracao,
        serie->pais,
        serie->idioma,
        serie->emissora,
        serie->transmissao,
        serie->num_temporadas,
        serie->num_episodios
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }
    long tam = tam_arquivo(file);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, file);
    fclose(file);
    html[tam] = '\0';
    return html;
}

/**
 * @brief Extrai os textos de uma tag html.
 * 
 * @param html Ponteiro para o caractere que abre a tag '<'.
 * @param texto Ponteiro para onde o texto deve ser colocado.
 * 
 * @return Ponteiro para o texto extraído.
 */
char *extrair_texto(char *html, char *texto) {
    char *start = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *start == ' ' ? start + 1 : start;
}

/**
 * @brief Lê o HTML da série e popula os campos da struct.
 * 
 * @param serie Struct Serie que vai receber os dados.
 * @param html String contendo todo o HTML do arquivo.
 */
void ler_serie(Serie *serie, char *html) {
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_episodios);
}




//lista
Serie array[100];
int n=0;

//inserir um elemento no inicio da lista
void inserirInicio(Serie serie){
    for(int i = n; i > 0; i--){
      array[i] = array[i-1];
   }
   array[0] = serie;
   n++;
}

//inserir um elemento no fim da lista
void inserirFim(Serie serie){
    array[n] = serie;
    n++;
}

//inserir um elemento em uma posicao especifica da lista
void inserir(Serie serie, int pos){
    for(int i = n; i > pos; i--){
      array[i] = array[i-1];
   }
   array[pos] = serie;
   n++;
}

//remove o elemento que se encontra no inicio da lista
Serie removerInicio(){
    Serie resp = array[0];
    n--;
   for(int i = 0; i < n; i++){
      array[i] = array[i+1];
   }
   return resp;
}

//remover o elemento que se encontra no final da fila
Serie removerFim(){
    return array[--n];
}

//remover um elemento que se encontra em uma posicao expecifica
Serie remover(int pos){
    Serie resp;
    resp = array[pos];
    n--;
   for(int i = pos; i < n; i++){
      array[i] = array[i+1];
   }
   return resp;
}

//mostra os elementos da lista
void mostrar(){
int i;
   for(i = 0; i < n; i++){
      print_serie(&array[i]);
   }
}



//separar os elementos da segunda parte da entrada

char* separaComando(char *entrada){
    char *comando = (char*) malloc(sizeof(char)*strlen(entrada));
    comando[0] = entrada[0];
    comando[1] = entrada[1];
    comando[2] = '\0';
    
    return comando;
}

char* separaNomeArquivo(char entrada[], char comando[]){
    char *nomeArq = (char*) malloc(sizeof(char)*strlen(entrada));
    int contEspaco = 0;
    int inicio, j=0;
    int i=0;

    if(comando[1] == '*'){
        for(i=3; i<strlen(entrada); i++){
            if(contEspaco>=1){
                nomeArq[j] = entrada[i];
                j++;
            }
            if(entrada[i]==' '){
                contEspaco++;
            }
        }
        nomeArq[j] = '\0';
    }else{
        for(i=3; i<strlen(entrada); i++){
            nomeArq[i-3] = entrada[i];
        }
        nomeArq[i-3] = '\0';
    }
    
    return nomeArq;
}

int separaPos(char entrada[], char comando[]){
    int pos;
    char aux[2];
    if(comando[0] == 'I'){
        for(int i=3; entrada[i]!=' '; i++){
            aux[i-3] = entrada[i];
        }
    }else if(comando[0] = 'R'){
        for(int i=3; entrada[i]!='\0'; i++){
            aux[i-3] = entrada[i];
        }
    }
    
    pos = atoi(aux);
    return pos;
}







//checa se a condicao de parada (FIM) foi atendida
int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

int main() {
    Serie serie;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];
    
    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);
    

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        free(html);
        inserirFim(serie);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }

    int num;
    char entrada[MAX_LINE_SIZE];
    char comando[3];
    char nomeArq[MAX_LINE_SIZE];
    int pos;
    

    scanf("%i", &num);


    for(int i=0; i<num; i++){
        strcpy(line, "");

        

        scanf(" %[^\n]s", entrada);
        /* scanf("%*[^\n]s"); scanf("%*c"); */
        

        strcpy(comando, separaComando(entrada));
        
        
         

        if(entrada[0] == 'I'){
            if(comando[1] == 'I'){
                strcpy(nomeArq, separaNomeArquivo(entrada, comando));
                strcat(line, PREFIXO);
                strcat(line, nomeArq);
                char *html = ler_html(line);
                ler_serie(&serie, html);
                free(html);
                inserirInicio(serie);

            }else if(comando[1] == 'F'){
                strcpy(nomeArq, separaNomeArquivo(entrada, comando));
                strcat(line, PREFIXO);
                strcat(line, nomeArq);
                char *html = ler_html(line);
                ler_serie(&serie, html);
                free(html);
                inserirFim(serie);

            }else if(comando[1] == '*'){
                pos = separaPos(entrada, comando);
                strcpy(nomeArq, separaNomeArquivo(entrada, comando));
                strcat(line, PREFIXO);
                strcat(line, nomeArq);
                char *html = ler_html(line);
                ler_serie(&serie, html);
                free(html);
                inserir(serie, pos); 

            }
        }else if(comando[0] == 'R'){
            if(comando[1] == 'I'){
                serie = removerInicio();
                printf("(R) %s\n", serie.nome);
   
            }else if(comando[1] == 'F'){
                serie = removerFim();
                printf("(R) %s\n", serie.nome);

            }else if(comando[1] == '*'){
                pos = separaPos(entrada, comando);
                serie = remover(pos);
                printf("(R) %s\n", serie.nome);

            }
        }

    }
    
    mostrar();
 
}




