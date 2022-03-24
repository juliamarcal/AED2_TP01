public class Q11 {

    //Se a string inicial e a invertida forem iguais, retorna true, se não, false
    public static boolean isPalindromo(String s, int posicao){
        //checa se todos os caracteres já foram comparados
        if(posicao >= s.length() - posicao - 1){
            return true;
        }

        
        return (s.charAt(posicao)==s.charAt(s.length() - posicao - 1)) && isPalindromo(s, posicao+1);    
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

            if(isPalindromo(entrada, 0)==true){
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
