import java.util.Random;

public class Q4 {
    
    public static String trocaLetras(String frase, Random gerador){
        String strFinal = "";
        char[] letraSorteada = new char[2];

        
        //sorteia as letras
        for(int i=0; i<2; i++){
            letraSorteada[i] = ( char ) ( 'a' + (Math.abs (gerador.nextInt()) %26) );
        }
        
        //faz a troca da primeira com a segunda letra
        for(int i=0; i<frase.length(); i++){
            if(frase.charAt(i) == letraSorteada[0]){
                strFinal += letraSorteada[1];
            }else{
                strFinal += frase.charAt(i);
            }
        }
        return strFinal;
    }
    
    //checa a condição de saida (FIM)
    public static boolean isFim(String s){
        if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M'){
            return true;
        }else{
            return false;  
        }
    }
    
    public static void main(String[] args) {
        String[] saida = new String[1000];
        String entrada = "";
        int numLinhas = 0;

        Random gerador = new Random( );
        gerador.setSeed( 4 );

        //Leitura da entrada padrão
        do{
            entrada = MyIO.readLine();

            saida[numLinhas] = trocaLetras(entrada, gerador);
            numLinhas++;
        }while(isFim(entrada)==false);

        //print da saida, sem contar com o FIM
        for(int i=0; i<numLinhas-1; i++){
            MyIO.println(saida[i]);
    
        }

    }

}
