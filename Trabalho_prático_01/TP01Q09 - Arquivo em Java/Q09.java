import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class Q09 {


    //lê os n numeros do teclado e grava eles em um arquivo txt
    public static void lerESalvarNoArquivo(int n) throws java.io.IOException{
        double num;
        RandomAccessFile raf = new RandomAccessFile("exemplo.txt","rw");
        
        for(int i=0; i<n; i++){
            num = MyIO.readDouble();
            raf.writeDouble(num);
        }
        
        raf.close();
    }


    //cria um randomAccessFile para ler o arquivo criado de traz para frente e imprime os numeros lidos na tela
    public static void escreverAoContrario(int n) throws java.io.IOException{
        RandomAccessFile raf = new RandomAccessFile("exemplo.txt","r");
        double num;

        for(int i=0; i<n; i++){
            raf.seek(8*(n-i-1));
            num = raf.readDouble();
            if(num%1==0){
                
                MyIO.println((int)num);
            }else{
                MyIO.println(num);
            }
            
            
        }

        raf.close();
    }
    
    public static void main(String[] args){
        int n = MyIO.readInt();
        

        //impede o erro de exesão de arquivo
        try {
            lerESalvarNoArquivo(n);
            escreverAoContrario(n);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
