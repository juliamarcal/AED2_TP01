public class Quest01 {

    //cria uma string que receberá o valor da string de entrada invertido
    public static String invertString(String s){
        String inv = "";
        int tam = s.length();

        for(int i=tam-1; i>=0; i--){
            inv += s.charAt(i);
        }

        return inv;
    }

    //Se a string inicial e a invertida forem iguais, retorna true, se não, false
    public static boolean isPalindromo(String s){
        
        String inv = invertString(s);

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) != inv.charAt(i)){
                return false;
            }
        }

        return true;
        
    }

    //Checa o comando de parada (FIM)
    public static boolean isFim(String s){

        if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M'){
            return true;
        }else{
            return false;  
        }

    }

    
    public static void main(String[] args) {
        String[] saida = new String[1000];
        int numLinhas = 0;
        String entrada = "";

        //Leitura da entrada padrão
        do{
            entrada = MyIO.readLine();

            if(isPalindromo(entrada)==true){
                saida[numLinhas] = "SIM";
            }else{
                saida[numLinhas] = "NAO";
            }
            numLinhas++;
        }while(isFim(entrada)==false);

        //print da saida, sem contar com o FIM
        for(int i=0; i<numLinhas-1; i++){
            MyIO.println(saida[i]);
        }

    }

}
