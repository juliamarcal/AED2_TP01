public class Q5 {


    //em uma string todas as ocorrencias de um caractere (passado como uma string de tamanho 1) sao substituidas por outro (pode ter qualquer tamanho)
    public static String substituiCaractere(String entrada, String substituido, String substituidor) {
        String saida="";

        for(int i=0; i<entrada.length(); i++){
            if(entrada.charAt(i)==substituido.charAt(0)){
                saida += substituidor;
            }else{
                saida+= entrada.charAt(i);
            }
        }

        return saida;
    }


    //se uma expressao not(?) estiver completa ela e substituida por seu resultado
    public static String resolveNot(String expressao) {
        String result="";

        for(int i=0; i<expressao.length(); i++){
            if((i+5<expressao.length()) && expressao.charAt(i)=='n' && expressao.charAt(i+1)=='o' && expressao.charAt(i+2)=='t' && 
            expressao.charAt(i+3)=='(' && expressao.charAt(i+4)=='1' && expressao.charAt(i+5)==')'){// not(1)
                result += '0';
                i += 5;
            }else if((i+5<expressao.length()) && expressao.charAt(i)=='n' && expressao.charAt(i+1)=='o' && expressao.charAt(i+2)=='t' && 
            expressao.charAt(i+3)=='(' && expressao.charAt(i+4)=='0' && expressao.charAt(i+5)==')'){// not(0)
                result += '1';
                i += 5;
            }else{
                result+=expressao.charAt(i);
            }
        }

        return result;
    }


    //se uma expressao and(?,?) com de 2 a 4 variaveis, estiver completa ela e substituida por seu resultado
    public static String resolveAnd(String expressao) {
        String result = "";

        for(int i=0; i<expressao.length(); i++){

            if(expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd' && expressao.charAt(i+3) == '(' && expressao.charAt(i+5) == ','
            && expressao.charAt(i+7) == ')'){
                if(expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0'){
                    result += '0';
                }else{
                    result += '1';
                }
                i+=7;
            }else if(expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd' && expressao.charAt(i+3) == '(' && 
            expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',' && expressao.charAt(i+9) == ')'){
                if(expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0' || expressao.charAt(i+8) == '0'){
                    result += '0';
                }else{
                    result += '1';
                }
                i+=9;
            }else if(expressao.charAt(i) == 'a' && expressao.charAt(i+1) == 'n' && expressao.charAt(i+2) == 'd' && expressao.charAt(i+3) == '(' && 
            expressao.charAt(i+5) == ',' && expressao.charAt(i+7) == ',' && expressao.charAt(i+9) == ',' && expressao.charAt(i+11) == ')'){
                if(expressao.charAt(i+4) == '0' || expressao.charAt(i+6) == '0' || expressao.charAt(i+8) == '0' || expressao.charAt(i+10) == '0'){
                    result += '0';
                }else{
                    result += '1';
                }
                i+=11;
            }else{
                result += expressao.charAt(i);
            }

        }//fim do for

        return result;
    }


    //se uma expressao or(?,?), com de 2 a 4 variaveis, estiver completa ela e substituida por seu resultado
    public static String resolveOr(String expressao) {
        String result = "";
        for(int i=0; i<expressao.length(); i++){

            if(expressao.charAt(i)=='o' && expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='(' && expressao.charAt(i+4)==',' && expressao.charAt(i+6)==')'){
                if(expressao.charAt(i+3)=='1' || expressao.charAt(i+5)=='1'){
                    result += '1';
                }else{
                    result += '0';
                }
                i += 6;
            }else if(expressao.charAt(i)=='o' && expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='(' && expressao.charAt(i+4)==',' && expressao.charAt(i+6)==',' &&
            expressao.charAt(i+8)==')'){
                    if(expressao.charAt(i+3)=='1' || expressao.charAt(i+5)=='1' || expressao.charAt(i+7)=='1'){
                        result += '1';
                    }else{
                        result += '0';
                    }
                    i += 8;

            }else if(expressao.charAt(i)=='o' && expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='(' && expressao.charAt(i+4)==',' && expressao.charAt(i+6)==',' &&
            expressao.charAt(i+8)==',' && expressao.charAt(i+10)==')'){
                if(expressao.charAt(i+3)=='1' || expressao.charAt(i+5)=='1'|| expressao.charAt(i+7)=='1' || expressao.charAt(i+9)=='1'){
                    result += '1';
                }else{
                    result += '0';
                }
                i += 10;

            }else{
                result += expressao.charAt(i);
            }

        }//fim do for

        return result;
    }


    //le as variaveis da expressao e formata ela de modo a deixar na string somente a expressao sem variaveis
    public static String leExpressao(String expressao) {
        char quantVar = expressao.charAt(0);
        expressao = substituiCaractere(expressao, " ", "");//retira espacos
        char A = expressao.charAt(1), B = expressao.charAt(2);
        char C = expressao.charAt(3);

        //retira os numeros da frente da expressao
        if(quantVar=='3'){
            expressao =substituiCaractere(expressao, ""+C+"", "");
        }
        expressao = substituiCaractere(expressao, ""+quantVar+"", "");
        expressao = substituiCaractere(expressao, ""+A+"", "");
        expressao = substituiCaractere(expressao, ""+B+"", "");
        
        //substirui as variaveis na expressao
        expressao = substituiCaractere(expressao, "A", ""+A+"");
        expressao = substituiCaractere(expressao, "B", ""+B+"");
        if(quantVar=='3'){
            expressao = substituiCaractere(expressao, "C", ""+C+"");
        }

        //resolvendo a expressao
        while(expressao.length()>1){
            expressao = resolveNot(expressao);
            expressao = resolveAnd(expressao);
            expressao = resolveOr(expressao); 
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
