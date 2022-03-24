public class Q6{
    
    //checa se a string é composta por somente vogais
    public static boolean soVogais(String frase){
        
        for(int i=0; i<frase.length(); i++){
            if(frase.charAt(i) != 'a' && frase.charAt(i) != 'A' &&
             frase.charAt(i) != 'e' && frase.charAt(i) != 'E' &&
              frase.charAt(i) != 'i' && frase.charAt(i) != 'I' &&
              frase.charAt(i) != 'u' && frase.charAt(i) != 'U' &&
              frase.charAt(i) != 'o' && frase.charAt(i) != 'O'){
                return false;
              }
        }

        return true;
    }
    
    //checa se a string é composta por somente consoantes
    public static boolean soConsoantes(String frase){
        int numTabelaASCII;

        for(int i=0; i<frase.length(); i++){
            numTabelaASCII = (int) frase.charAt(i);

            if(frase.charAt(i) == 'a' || frase.charAt(i) == 'A' ||
             frase.charAt(i) == 'e' || frase.charAt(i) == 'E' ||
              frase.charAt(i) == 'i' || frase.charAt(i) == 'I' ||
              frase.charAt(i) == 'u' || frase.charAt(i) == 'U' ||
              frase.charAt(i) == 'o' || frase.charAt(i) == 'O' ||
              numTabelaASCII>47 || numTabelaASCII<58
              ){
                return false;
              }
        }
        
        return true;
    }

    //checa se a string é um numero inteiro
    public static boolean numInteiro(String frase){
        int numTabelaASCII;

        for(int i=0; i<frase.length(); i++){
            numTabelaASCII = (int) frase.charAt(i);
            
            //se tiver um caractere que nao é numero retorna false
            if(numTabelaASCII<48 || numTabelaASCII>57){
                return false;
            }
        }

        return true;
    }

    //checa se a string é um numero real
    public static boolean numReal(String frase){
        int numTabelaASCII, contVirgulaPonto=0, contNums=0;
        

        for(int i=0; i<frase.length(); i++){
            numTabelaASCII = (int) frase.charAt(i);
            
            if((numTabelaASCII>48 && numTabelaASCII<57) || numTabelaASCII==46 || numTabelaASCII==44){
                //conta a quantidade de numeros e a quantidade de sinais de separação (ponto ou virgula)
                if(numTabelaASCII>48 && numTabelaASCII<57){
                    contNums++;
                }
                if(numTabelaASCII==46 || numTabelaASCII==44){
                    contVirgulaPonto++;
                }
            }
        }

        //se eu tiver pelo menos 1 numero e no maximo 1 sinal de separação retorna true
        if(contNums>0 && contVirgulaPonto<2){
            return true;
        }else{
            return false;
        }

    }

    //Checa o comando de parada (FIM)
    public static boolean isFim(String s){

        if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M'){
            return true;
        }else{
            return false;  
        }

    }

    //entrada e saida padrão
    public static void main(String[] args) {
        String[] saida = new String[1000];
        String aux;
        int numLinhas = 0;
        String entrada = "";

        //Leitura da entrada padrão
        do{
            entrada = MyIO.readLine();

            if(soVogais(entrada)==true){
                saida[numLinhas] = "SIM ";
            }else{
                saida[numLinhas] = "NAO ";
            }

            if(soConsoantes(entrada)==true){
                saida[numLinhas] += "SIM ";
            }else{
                saida[numLinhas] += "NAO ";
            }

            if(numInteiro(entrada)==true){
                saida[numLinhas] += "SIM ";
            }else{
                saida[numLinhas] += "NAO ";
            }

            if(numReal(entrada)==true){
                saida[numLinhas] += "SIM";
            }else{
                saida[numLinhas] += "NAO";
            }
            
            numLinhas++;
        }while(isFim(entrada)==false);

        //print da saida, sem contar com o FIM
        for(int i=0; i<numLinhas-1; i++){
            MyIO.println(saida[i]);
        }
        
    }
}