import java.io.*;
import java.net.*;

class ExemploURL {
   public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } 

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }
}

public class Q8{

    //conta a quantidade dos Padroes < table > em uma dada string
   public static int contarPadraoTable(String s) {
    String padrao = "<table>";
     int cont=0;

    for(int i=0; i<s.length(); i++){
         if(s.charAt(i) == padrao.charAt(0) && s.charAt(i+1) == padrao.charAt(1) && s.charAt(i+2) == padrao.charAt(2) &&
             s.charAt(i+3) == padrao.charAt(3) && s.charAt(i+4) == padrao.charAt(4) && s.charAt(i+5) == padrao.charAt(5) &&
             s.charAt(i+6) == padrao.charAt(6)){
             cont++;
         }
     }

    return cont;
}


   //conta a quantidade dos Padroes < br > em uma dada string
   public static int contarPadraoBr(String s) {
       String padrao = "<br>";
        int cont=0;

       for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == padrao.charAt(0) && s.charAt(i+1) == padrao.charAt(1) && s.charAt(i+2) == padrao.charAt(2) &&
                s.charAt(i+3) == padrao.charAt(3)){
                cont++;
            }
        }

       return cont;
   }


   //conta quantas consoantes tem em uma string
   public static int contarConsoantes(String s) {
        int cont=0;
            
        for(int i=0; i<s.length(); i++){
            if((s.charAt(i) != 'a' &&  s.charAt(i) != 'e' &&  s.charAt(i) != 'i' &&  s.charAt(i) != 'o' &&
                s.charAt(i) != 'u' &&  s.charAt(i) != 'á' &&  s.charAt(i) != 'é' &&  s.charAt(i) != 'í'
                &&  s.charAt(i) != 'ó' &&  s.charAt(i) != 'ú' &&  s.charAt(i) != 'à'
                &&  s.charAt(i) != 'è' &&  s.charAt(i) != 'ì' &&  s.charAt(i) != 'ò' &&  s.charAt(i) != 'ù'
                &&  s.charAt(i) != 'ã' &&  s.charAt(i) != 'õ' &&  s.charAt(i) != 'â' &&  s.charAt(i) != 'ê'
                &&  s.charAt(i) != 'î' &&  s.charAt(i) != 'ô' &&  s.charAt(i) != 'û' ) && (s.charAt(i)>= 'a' && s.charAt(i)<= 'z') ){
            }else{
                cont++;
            }
        }
        
        return cont;
   }


    //conta a quantidade de um caractere especifico em uma string fornecida
    public static int contarCaractere(String s, char elemento) {
        int cont=0;

        for(int i=0; i<s.length(); i++){
            if(elemento==s.charAt(i)){
                cont++;
            }
        }

        return cont;
    }


    public static String montarStringSaida(String nome, String endereco) {
        String saida, html;
        html = ExemploURL.getHtml(endereco);

        
        //padroes
        int contBr = contarPadraoBr(html);
        int contTable = contarPadraoTable(html);

        //letras sem acentuacao
        int contA = contarCaractere(html, 'a') - contTable;
        int contE = contarCaractere(html, 'e') - contTable;
        int contI = contarCaractere(html, 'i');
        int contO = contarCaractere(html, 'o');
        int contU = contarCaractere(html, 'u');

        //elementos com acento agudo
        int contAAgudo = contarCaractere(html, 'á');
        int contEAgudo = contarCaractere(html, 'é');
        int contIAgudo = contarCaractere(html, 'í');
        int contOAgudo = contarCaractere(html, 'ó');
        int contUAgudo = contarCaractere(html, 'ú');

        //elementos com crase
        int contACrase = contarCaractere(html, 'à');
        int contECrase = contarCaractere(html, 'è');
        int contICrase = contarCaractere(html, 'ì');
        int contOCrase = contarCaractere(html, 'ò');
        int contUCrase = contarCaractere(html, 'ù');

        //elementos com tiu
        int contAtiu = contarCaractere(html, 'ã');
        int contOtiu = contarCaractere(html, 'õ');

        //elementos com acento circunflexo
        int contACircunflex = contarCaractere(html, 'â');
        int contECircunflex = contarCaractere(html, 'ê');
        int contICircunflex = contarCaractere(html, 'î');
        int contOCircunflex = contarCaractere(html, 'ô');
        int contUCircunflex = contarCaractere(html, 'û');

        //consoantes
        int contConsoantes = contarConsoantes(html) - (2*contBr) - (3*contTable);
        
        //montagem da string de saida
        saida = "a("+contA+") e("+contE+") i("+contI+") o("+contO+") u("+contU+") á("+contAAgudo+") é("+contEAgudo+") í("+contIAgudo+") ó("+contOAgudo+") ú("+contUAgudo+") à("+contACrase+") è("+contECrase+") ì("+contICrase+") ò("+contOCrase+") ù("+contUCrase+") ã("+contAtiu+") õ("+contOtiu+") â("+contACircunflex+") ê("+contECircunflex+") î("+contICircunflex+") ô("+contOCircunflex+") û("+contUCircunflex+") consoante("+contConsoantes+") <br>("+contBr+") <table>("+contTable+") "+nome;

        return saida;
    }
  
    
    //checa se a confi��o de parada (FIM) foi atingida
    public static boolean isFim(String s) {
        if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M'){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        String nome="", endereco="", saida;
        int cont=0;

        do{
            if(cont%2==0){
                nome = MyIO.readLine();
            }else{
                endereco = MyIO.readLine();
                saida = montarStringSaida(nome, endereco);
                System.out.println(saida); 
            }

            cont++;
        }while(isFim(nome)==false);

        

    }
}