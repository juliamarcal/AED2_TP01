public class Q14 {
        //em uma string todas as ocorrencias de um caractere (passado como uma string de tamanho 1) sao substituidas por outro (pode ter qualquer tamanho)
        public static String substituiCaractere(String entrada, String substituido, String substituidor, int i) {
            String saida="";
            if(i==entrada.length()){
                return "";
            }else if(entrada.charAt(i) == substituido.charAt(0)){
                return substituidor + substituiCaractere(entrada, substituido, substituidor, i+1);
            }else{
                return entrada.charAt(i) + substituiCaractere(entrada, substituido, substituidor, i+1);
            }
        }
    
    
        //se uma expressao not(?) estiver completa ela e substituida por seu resultado
        public static String resolveNot(String expressao, int i) {
            if(i==expressao.length()){

                return "";

            }else if(expressao.charAt(i)=='n' && expressao.charAt(i+1)=='o' && expressao.charAt(i+2)=='t' && 
            expressao.charAt(i+3)=='(' && expressao.charAt(i+4)=='1' && expressao.charAt(i+5)==')'){

                return "0" + resolveNot(expressao, i+6);

            }else if(expressao.charAt(i)=='n' && expressao.charAt(i+1)=='o' && expressao.charAt(i+2)=='t' && 
            expressao.charAt(i+3)=='(' && expressao.charAt(i+4)=='0' && expressao.charAt(i+5)==')'){

                return "1" + resolveNot(expressao, i+6);

            }else{

                return expressao.charAt(i) + resolveNot(expressao, i+1);

            }
            
        }
    
    
        //se uma expressao and(?,?) com de 2 a 4 variaveis, estiver completa ela e substituida por seu resultado
        public static String resolveAnd(String expressao, int i) {
            
            if(i==expressao.length()){

                return "";

            }else if(expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd' && expressao.charAt(i+3) == '(' && expressao.charAt(i+5) == ','
            && expressao.charAt(i+7) == ')'){

                if(expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0'){
                    return "0" + resolveAnd(expressao, i+8);
                }else{
                    return "1" + resolveAnd(expressao, i+8);
                }

            }else if(expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd' && expressao.charAt(i+3) == '(' && 
            expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',' && expressao.charAt(i+9) == ')'){

                if(expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0' || expressao.charAt(i+8) == '0'){
                    return "0" + resolveAnd(expressao, i+10);
                }else{
                    return "1" + resolveAnd(expressao, i+10);
                }

            }else if(expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd' && expressao.charAt(i+3) == '(' && 
            expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',' && expressao.charAt(i+9) == ',' && expressao.charAt(i+11) == ')'){

                if(expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0' || expressao.charAt(i+8) == '0' || expressao.charAt(i+10) == '0'){
                    return "0" + resolveAnd(expressao, i+12);
                }else{
                    return "1" + resolveAnd(expressao, i+12);
                }

            }else{

                return expressao.charAt(i) + resolveAnd(expressao, i+1);

            }
            
        }
    
    
        //se uma expressao or(?,?), com de 2 a 4 variaveis, estiver completa ela e substituida por seu resultado
        public static String resolveOr(String expressao, int i) {

            if(i==expressao.length()){

                return "";

            }else if(expressao.charAt(i)=='o' && expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='(' && expressao.charAt(i+4)==',' && expressao.charAt(i+6)==')'){

                if(expressao.charAt(i+3)=='1' || expressao.charAt(i+5)=='1'){
                    return "1" + resolveOr(expressao, i+7);
                }else{
                    return "0" + resolveOr(expressao, i+7);
                }

            }else if(expressao.charAt(i)=='o' && expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='(' && expressao.charAt(i+4)==',' && expressao.charAt(i+6)==',' &&
            expressao.charAt(i+8)==')'){

                if(expressao.charAt(i+3)=='1' || expressao.charAt(i+5)=='1' || expressao.charAt(i+7)=='1'){
                    return "1" + resolveOr(expressao, i+9);
                }else{
                    return "0" + resolveOr(expressao, i+9);
                }

            }else if(expressao.charAt(i)=='o' && expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='(' && expressao.charAt(i+4)==',' && expressao.charAt(i+6)==',' &&
            expressao.charAt(i+8)==',' && expressao.charAt(i+10)==')'){

                if(expressao.charAt(i+3)=='1' || expressao.charAt(i+5)=='1'|| expressao.charAt(i+7)=='1' || expressao.charAt(i+9)=='1'){
                    return "1" + resolveOr(expressao, i+11);
                }else{
                    return "0" + resolveOr(expressao, i+11);
                }
                
            }else{

                return expressao.charAt(i) + resolveOr(expressao, i+1);

            }

        }
    
    
        //le as variaveis da expressao e formata ela de modo a deixar na string somente a expressao sem variaveis
        public static String leExpressao(String expressao) {
            char quantVar = expressao.charAt(0);
            expressao = substituiCaractere(expressao, " ", "", 0);//retira espacos
            char A = expressao.charAt(1), B = expressao.charAt(2);
            char C = expressao.charAt(3);
    
            //retira os numeros da frente da expressao
            if(quantVar=='3'){
                expressao =substituiCaractere(expressao, ""+C+"", "", 0);
            }
            expressao = substituiCaractere(expressao, ""+quantVar+"", "", 0);
            expressao = substituiCaractere(expressao, ""+A+"", "", 0);
            expressao = substituiCaractere(expressao, ""+B+"", "", 0);
            
            //substirui as variaveis na expressao
            expressao = substituiCaractere(expressao, "A", ""+A+"", 0);
            expressao = substituiCaractere(expressao, "B", ""+B+"", 0);
            if(quantVar=='3'){
                expressao = substituiCaractere(expressao, "C", ""+C+"", 0);
            }
    
            //resolvendo a expressao
            while(expressao.length()>1){
                expressao = resolveNot(expressao, 0);                  
                expressao = resolveAnd(expressao, 0);              
                expressao = resolveOr(expressao, 0);
            }
    
            return expressao;
        }
    
    
        //checa se a condição de parada (0) chegou ao fim
        public static boolean isFim (String s) {
            if(s.charAt(0)=='0'){
                return true;
            }else{
                return false;
            }
        }
        
    
        public static void main(String[] args) {
            String entrada = "";
            String saida;
    
            do{
                entrada = MyIO.readLine();
                if(isFim(entrada)==false){
                    saida = leExpressao(entrada);
                     MyIO.println(saida);
                }
            }while(isFim(entrada)==false);
        }
        
}
