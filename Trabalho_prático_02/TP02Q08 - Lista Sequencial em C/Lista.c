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



Serie array[50];
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
      imprimir(array[i]);
   }
}