
class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;
 
    public Celula(){
       this(0);
    }
 
    public Celula(int elemento){
       this(elemento, null, null, null, null);
    }
 
    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
       this.elemento = elemento;
       this.inf = inf;
       this.sup = sup;
       this.esq = esq;
       this.dir = dir;
    }
}


class Matriz {
   private Celula inicio;
   private int linha, coluna;


   public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;

      if(coluna>0 && linha>0){
         inicio = new Celula();
      }

      Celula auxCol = inicio;
      Celula auxLin = inicio;

      for(int i=0; i<linha; i++){
         for(int j=1; j<coluna; j++){
               auxCol.dir = new Celula();
               auxCol.dir.esq = auxCol;

               auxCol = auxCol.dir;

               if(i>0){
                  auxCol.sup = auxCol.esq.sup.dir;
                  auxCol.sup.inf = auxCol;
               }

               
         }
         auxLin.inf = new Celula();
         auxLin.inf.sup = auxLin;
         auxLin = auxLin.inf;
         auxCol = auxLin;
      }

   }


public void inserir(){
   Celula auxCol = inicio;
   Celula auxLin = inicio;
   int el;

   for(int i = 0; i<linha; i++){
      for(int j=0; j<coluna; j++){
            el = MyIO.readInt();
            auxCol.elemento = el;
            auxCol = auxCol.dir;
      }
      auxCol = auxLin.inf;
      auxLin = auxLin.inf;
   }

   
}


   public Matriz soma (Matriz m) {
      Matriz resp = null;
      
      if(this.linha == m.linha && this.coluna == m.coluna){
         resp = new Matriz(this.linha, this.coluna);
         Celula a = this.inicio, b = m.inicio, c = resp.inicio;
         Celula linA = this.inicio, linB = m.inicio, linC = resp.inicio;
         for(int i=0; i<linha; i++){
            for(int j=0; j<coluna; j++){
               //sendo c (pont em resp), a (em this) e b (em m)
               c.elemento = a.elemento + b.elemento;
               a = a.dir;
               c = c.dir;
               b = b.dir;
            }

            linA = linA.inf;
            linB = linB.inf;
            linC = linC.inf;

            a = linA;
            b = linB;
            c = linC;

         }

      }

      return resp;
   }


   public Matriz multiplicacao (Matriz m) {
      Matriz resp = null;

      if(this.coluna == m.linha){
         resp = new Matriz(this.linha, m.coluna);
         Celula colA = this.inicio, colB = m.inicio, colC = resp.inicio;
         Celula linA = this.inicio, linB = m.inicio, linC = resp.inicio;

         for(int i=0; i<this.linha; i++){
            for(int j=0; j<m.coluna; j++){

               for(int k = 0; k<this.coluna; k++){
                  
                  colC.elemento += (colA.elemento * linB.elemento);

                  colA = colA.dir;
                  linB = linB.inf;


               }

               colC = colC.dir;

               colA = linA;

               colB = colB.dir;
               linB = colB;
               

            }

            colC = linC.inf;
            linC = linC.inf;

            linA = linA.inf;
            colA = linA;

            colB = m.inicio;
            linB = m.inicio;

         }

      }

      return resp;
   }


   public boolean isQuadrada(){
      return (this.linha == this.coluna);
   }


   public void mostrarDiagonalPrincipal (){
      if(isQuadrada() == true){
         Celula auxCol = inicio;
         Celula auxLin = inicio;

         for(int i = 0; i<linha; i++){
            for(int j=0; j<coluna; j++){
                  if(i==j){
                     MyIO.print(auxCol.elemento + " ");
                  }
                  auxCol = auxCol.dir;
            }
            auxCol = auxLin.inf;
            auxLin = auxLin.inf;
         }
         MyIO.print("\n");
      }
   }


   public void mostrarDiagonalSecundaria (){
      if(isQuadrada() == true){
         Celula auxCol = inicio;
         Celula auxLin = inicio;

         for(int i = 1; i<=linha; i++){
            for(int j=1; j<=coluna; j++){
                  if(i+j == linha + 1){
                     MyIO.print(auxCol.elemento + " ");
                  }
                  auxCol = auxCol.dir;
            }
            auxCol = auxLin.inf;
            auxLin = auxLin.inf;
         }
         MyIO.print("\n");
      }
   }


   public void mostrar(){
      Celula auxCol = inicio;
      Celula auxLin = inicio;

      for(int i = 0; i<linha; i++){
         for(int j=0; j<coluna; j++){
               MyIO.print(auxCol.elemento + " ");
               auxCol = auxCol.dir;
         }
         MyIO.print("\n");
         auxCol = auxLin.inf;
         auxLin = auxLin.inf;
      }

   }




}



public class Q17 {
   public static void main(String[] args) {
      int numEntradas = MyIO.readInt();
      int lin=0, col=0;

      for(int i=0; i<numEntradas; i++){
         //leitura matriz 1
         lin = MyIO.readInt();
         col = MyIO.readInt();

         Matriz mat1 = new Matriz(lin, col);
         mat1.inserir();


         //leitura matriz 2
         lin = MyIO.readInt();
         col = MyIO.readInt();

         Matriz mat2 = new Matriz(lin, col);
         mat2.inserir();

         // metodos das matrizes
         mat1.mostrarDiagonalPrincipal();
         mat1.mostrarDiagonalSecundaria();
         mat1.soma(mat2).mostrar();
         mat1.multiplicacao(mat2).mostrar();


      }
      
   }  

}
