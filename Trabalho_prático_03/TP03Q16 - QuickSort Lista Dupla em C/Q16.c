
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <err.h>

#define MAX_FIELD_SIZE 100
#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"


//Serie
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
    strcpy(serie->pais, trim(texto));
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





#define bool   short
#define true   1
#define false  0

//Ceula Dupla
typedef struct CelulaDupla {
    Serie elemento;        // Elemento inserido na celula.
    struct CelulaDupla* prox; // Aponta a celula prox.
    struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Serie elemento) {
    CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    nova->elemento = elemento;
    nova->ant = nova->prox = NULL;
    return nova;
}



// Lista duplamente encadeada
CelulaDupla* primeiro;
CelulaDupla* ultimo;


/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start () {
    Serie serie;
    primeiro = novaCelulaDupla(serie);
    ultimo = primeiro;
}


// Insere um elemento na ultima posicao da lista.
void inserirFim(Serie x) {
   ultimo->prox = novaCelulaDupla(x);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
}



// Calcula e retorna o tamanho, em numero de elementos, da lista.
int tamanho() {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}


// Mostra os elementos da lista separados por espacos.
void mostrar() {
   CelulaDupla* i;
   
   for (i = primeiro->prox; i != NULL; i = i->prox) {
      print_serie(&i->elemento);
   }
   
}


// Encontra uma celula em determinada posicao
CelulaDupla indice(int pos){
    CelulaDupla *i = primeiro->prox;
    for(int j = 0; j < pos; j++, i = i->prox);
    return *i;      
}


// troca duas celualas de posicao
void swap(int i, int j){
    
    CelulaDupla *el1 = primeiro->prox;
    CelulaDupla *el2 = primeiro->prox;

    for(int k = 0; k < i; k++, el1 = el1->prox);
    for(int k = 0; k < j; k++, el2 = el2->prox);

    Serie aux = el1->elemento;
    el1->elemento = el2->elemento;
    el2->elemento = aux;
    
    /* print_serie(*el1->elemento);
    print_serie(*el2->elemento); */
}


//Verifica se um elemento e maior que outro com base na chave paisDeOrigem
int isMaior(Serie i, Serie j){


    if(strcmp(i.pais, j.pais) > 0){
        return 1;
    }else if(strcmp(i.pais, j.pais) == 0){
        
        if(strcmp(i.nome, j.nome) > 0){
            return 1;
        }else if(strcmp(i.nome, j.nome) == 0){
            return 0;
        }else{
            return -1;
        }

    }else{
        return -1;
    }
}

// Ordena os elementos da lista por meio da ordenacao por selecao com a chave idioma
void ordenar(int esq, int dir){
    int i = esq, j = dir;
    int pivo_posicao = ((dir+esq)/2);
    Serie pivo = indice(pivo_posicao).elemento;

    while (i <= j) {

        while (isMaior(pivo, indice(i).elemento) == true) i++;
        while (isMaior(indice(j).elemento, pivo) == true) j--;

        if (i <= j) {
            swap(i, j);
            i++;
            j--;
        }
    }
    if (esq < j)  ordenar(esq, j);
    if (i < dir)  ordenar(i, dir);
}









//checa se a condicao de parada (FIM) foi atendida
int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}



int main() {
    Serie serie;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];
    start();
    
    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);
    

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        free(html);
        inserirFim(serie);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }


    int n = tamanho();
    ordenar(0, n-1); 
    
    mostrar();
 
 
}