public class Q13 {
 //criptografa a frase com uma chave de ciframento = a 3
 public static String criptografar(String frase, int pos){
    String crypt;
    char letra;
    int aux;

    crypt = "";
    letra = frase.charAt(pos);
    aux = (int) letra + 3;
    letra = (char) aux;
    crypt += letra;

    if(pos==frase.length()-1){
        return crypt;
    }
    
        
    return crypt+criptografar(frase, pos+1);

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
    String entrada = "";
    int numLinhas = 0;

    //Leitura da entrada padrão
    do{
        entrada = MyIO.readLine();

        saida[numLinhas] = criptografar(entrada, 0);
        numLinhas++;
    }while(isFim(entrada)==false);

    //print da saida, sem contar com o FIM
    for(int i=0; i<numLinhas-1; i++){
        MyIO.println(saida[i]);
    }
    
}
}
