public class Q15 {

    //checa se a string é composta por somente vogais
    public static boolean soVogais(String frase, int i){
        if(i==frase.length()){
            return true;
        } else if(frase.charAt(i) != 'a' && frase.charAt(i) != 'A' &&
            frase.charAt(i) != 'e' && frase.charAt(i) != 'E' &&
            frase.charAt(i) != 'i' && frase.charAt(i) != 'I' &&
            frase.charAt(i) != 'u' && frase.charAt(i) != 'U' &&
            frase.charAt(i) != 'o' && frase.charAt(i) != 'O'){
                return false;
        }

        return soVogais(frase, i+1);
    }
    

    //checa se a string é composta por somente consoantes
    public static boolean soConsoantes(String frase, int i){
        
            if(i==frase.length()){
                return true;
            }else if((frase.charAt(i) == 'a' || frase.charAt(i) == 'A' ||
            frase.charAt(i) == 'e' || frase.charAt(i) == 'E' ||
            frase.charAt(i) == 'i' || frase.charAt(i) == 'I' ||
            frase.charAt(i) == 'u' || frase.charAt(i) == 'U' ||
            frase.charAt(i) == 'o' || frase.charAt(i) == 'O') ||
            ((frase.charAt(i)< 'a' || frase.charAt(i)> 'z') && (frase.charAt(i)< 'A' || frase.charAt(i)> 'Z'))){
                return false;
            }
       
        return soConsoantes(frase, i+1);
    }


    //checa se a string é um numero inteiro
    public static boolean numInteiro(String frase, int i){

            if(i==frase.length()){
                return true;
            }else if(frase.charAt(i)<'0' || frase.charAt(i)>'9'){
                //se tiver um caractere que nao é numero retorna false
                return false;
            }
            
        return numInteiro(frase, i+1);
    }


    //checa se a string é um numero real
    public static boolean numReal(String frase, int i, int contVirgulaPonto, int contNums){
    
        if(i==frase.length()){
            //se eu tiver pelo menos 1 numero e no maximo 1 sinal de separação retorna true
            if(contNums>0 && contVirgulaPonto<2){
                return true;
            }else{
                return false;
            }
        }else if((frase.charAt(i)>='0' && frase.charAt(i)<='9') || frase.charAt(i)=='.' ||frase.charAt(i)==','){
            //conta a quantidade de numeros e a quantidade de sinais de separação (ponto ou virgula)
            if((frase.charAt(i)>='0' && frase.charAt(i)<='9')){
                contNums++;
            }
            if(frase.charAt(i)=='.' ||frase.charAt(i)==','){
                contVirgulaPonto++;
            }
        }else{
            return false;
        }

        return numReal(frase, i+1, contVirgulaPonto, contNums);
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

            if(soVogais(entrada, 0)==true){
                saida[numLinhas] = "SIM ";
            }else{
                saida[numLinhas] = "NAO ";
            }

            if(soConsoantes(entrada, 0)==true){
                saida[numLinhas] += "SIM ";
            }else{
                saida[numLinhas] += "NAO ";
            }

            if(numInteiro(entrada, 0)==true){
                saida[numLinhas] += "SIM ";
            }else{
                saida[numLinhas] += "NAO ";
            }

            if(numReal(entrada, 0, 0, 0)==true){
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
