import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Date;

class Serie{

    // declaracao de atributos
    private String nome;
    private String formato;
    private String duracao;
    private String paisDeOrigem;
    private String idiomaOriginal;
    private String emissora;
    private String transmissaoOriginal;
    private int numTeporadas;
    private int numEpisodios;

    //construtores
    Serie(){
        nome = "";
        formato = "";
        duracao = "";
        paisDeOrigem = "";
        idiomaOriginal = "";
        emissora = "";
        transmissaoOriginal = "";
        numTeporadas = 0;
        numEpisodios = 0;
    }

    Serie(String nome, String formato, String duracao, String paisDeOrigem, String idiomaOriginal, String emissora, String transmissaoOriginal, int numTeporadas, int numEpisodios){
        nome = this.nome;
        formato = this.formato;
        duracao = this.duracao;
        paisDeOrigem = this.paisDeOrigem;
        idiomaOriginal = this.idiomaOriginal;
        emissora = this.emissora;
        transmissaoOriginal = this.transmissaoOriginal;
        numTeporadas = this.numTeporadas;
        numEpisodios = this.numEpisodios;
    }

    //setters e getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao; 
    }

    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getTransmissaoOriginal() {
        return transmissaoOriginal;
    }

    public void setTransmissaoOriginal(String transmissaoOriginal) {
        this.transmissaoOriginal = transmissaoOriginal;
    }

    public int getNumTeporadas() {
        return numTeporadas;
    }

    public void setNumTeporadas(int numTeporadas) {
        this.numTeporadas = numTeporadas;
    }

    public int getNumEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    //metodos clone
    public Serie clone(){
        Serie resp = new Serie();

        resp.nome = this.nome;
        resp.formato = this.formato;
        resp.duracao = this.duracao;
        resp.paisDeOrigem = this.paisDeOrigem;
        resp.idiomaOriginal = this.idiomaOriginal;
        resp.emissora = this.emissora;
        resp.transmissaoOriginal = this.transmissaoOriginal;
        resp.numTeporadas = this.numTeporadas;
        resp.numEpisodios = this.numEpisodios;

        return resp;
    }

    //metodo imprimir
    public void imprimir(){
        System.out.println(nome + " " + formato + " " + duracao + " " + paisDeOrigem + " " + idiomaOriginal + " " + emissora + " " + transmissaoOriginal + " " + numTeporadas + " " + numEpisodios);
    }

    //retira as tags de uma string
    public String tiraTags(String html) {
        String semTags = "";
        int i=0;

        while(i<html.length()){
            if(html.charAt(i)=='<'){
                i++;
                while(html.charAt(i)!='>'){
                    i++;
                }
            }else{
                semTags += html.charAt(i);
            }
            i++;
        }

        return semTags;
    }

    //transforma um numero em formato String para formato int
    public int transformaEmInteiro(String s){
        int num = 0;
        int mult = 0;

        for(int i=s.length()-1; i>=0; i--){
            num += (((int) s.charAt(i)) - 48) * Math.pow(10, mult);
            mult++;
        }

        return num;
    }

    //retira todos os caracteres nao numeros de uma string
    public String soDeixaNumeros(String s){
        String novaLinha="";

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)==' '){
                i = s.length();
            }else{
                novaLinha += s.charAt(i);
            }
        }

        return novaLinha;
    }

    //retira espacos no inicio ou final da string
    public String trim(String s) {
        String novaLinha="";
        int posInicio=0, posFinal=s.length()-1;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)!=' '){
                posInicio = i;
                i = s.length();
            }
        }
        
        for(int i=s.length()-1; i>0; i--){
            if(s.charAt(i)!=' '){
                posFinal = i;
                i = 0;
            }
        }

        for(int i=posInicio; i<=posFinal; i++){
            novaLinha += s.charAt(i);
        }
    
        return novaLinha;
    }

    //retira informacoes disnecessarias como "&#160;" da transmissao original
    public String retiraSpan(String s) {
        String novaLinha="";

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='&'){
                i++;
                while(s.charAt(i)!=';'){
                    i++;
                }
            }else{
                novaLinha += s.charAt(i);
            }
        }

        return novaLinha;
    }

    //formata o nome da serie
    public String formataNome(String nome){
        String novoNome="";

        for(int i=0; i<nome.length(); i++){
            if(nome.charAt(i)=='_'){
                novoNome += ' ';
            }else if(nome.charAt(i)=='/'){
                i += 11;
                

            }else if(nome.charAt(i)=='.' && nome.charAt(i+1)=='h' && nome.charAt(i+2)=='t' && nome.charAt(i+3)=='m' && nome.charAt(i+4)=='l'){
                i += 5;
            }else{
                novoNome += nome.charAt(i);
            }
        }

        return novoNome;
    }

    //metodo ler
    public void ler(String nomeArquivo) throws Exception{
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));
        BufferedReader br = new BufferedReader(isr);
        
        this.nome = formataNome(nomeArquivo);

        while(!br.readLine().contains("Formato"));
        this.formato = tiraTags(br.readLine());

        while(!br.readLine().contains("Duração")); 
        this.duracao = tiraTags(br.readLine());

        while(!br.readLine().contains("País de origem"));
        this.paisDeOrigem = trim(retiraSpan(tiraTags(br.readLine())));

        while(!br.readLine().contains("Idioma original"));
        this.idiomaOriginal = trim(tiraTags(br.readLine()));

        while(!br.readLine().contains("Emissora"));
        this.emissora = trim(tiraTags(br.readLine()));

        while(!br.readLine().contains("Transmissão original"));
        this.transmissaoOriginal = trim(retiraSpan(tiraTags(br.readLine())));

        while(!br.readLine().contains("temporadas"));
        this.numTeporadas = transformaEmInteiro(soDeixaNumeros(tiraTags(br.readLine())));

        while(!br.readLine().contains("episódios"));
        this.numEpisodios = Integer.parseInt(soDeixaNumeros(tiraTags(br.readLine())));

        br.close();
    }

}



class No {
	public char elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
   public No2 outro;
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No(char elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
      this.outro = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	No(char elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
      this.outro = null;
	}
}



class No2 {
	public String elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No2(String elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No2 da esquerda.
	 * @param dir No2 da direita.
	 */
	No2(String elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}



class ArvoreArvore {
	private No raiz; // Raiz da arvore.

    // Construtor da classe
    public ArvoreArvore() {
        raiz = null;
        try {
            inserir('D');
        } catch (Exception e) {}
        try {
            inserir('R');
        } catch (Exception e) {}
        try {
            inserir('Z');
        } catch (Exception e) {}  
        try {
            inserir('X');
        } catch (Exception e) {}
        try {
            inserir('V');
        } catch (Exception e) {}
        try {
            inserir('B');
        } catch (Exception e) {}
        try {
            inserir('F');
        } catch (Exception e) {}
        try {
            inserir('P');
        } catch (Exception e) {}
        try {
            inserir('U');
        } catch (Exception e) {}
        try {
            inserir('I');
        } catch (Exception e) {}
        try {
            inserir('G');
        } catch (Exception e) {}
        try {
            inserir('E');
        } catch (Exception e) {}
        try {
            inserir('J');
        } catch (Exception e) {}
        try {
            inserir('L');
        } catch (Exception e) {}
        try {
            inserir('H');
        } catch (Exception e) {}
        try {
            inserir('T');
        } catch (Exception e) {}
        try {
            inserir('A');
        } catch (Exception e) {}
        try {
            inserir('W');
        } catch (Exception e) {}
        try {
            inserir('S');
        } catch (Exception e) {}
        try {
            inserir('O');
        } catch (Exception e) {}
        try {
            inserir('M');
        } catch (Exception e) {}
        try {
            inserir('N');
        } catch (Exception e) {}
        try {
            inserir('K');
        } catch (Exception e) {}
        try {
            inserir('C');
        } catch (Exception e) {}
        try {
            inserir('Y');
        } catch (Exception e) {}
        try {
            inserir('Q');
        } catch (Exception e) {}
    }

    //inserir letra
    public void inserir(char letra) throws Exception {
        raiz = inserir(letra, raiz);
    }

    private No inserir(char x, No i) throws Exception {
        if (i == null) {
            i = new No(x);

        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        }

        return i;
	}

    // Inserir string
    public void inserir(String s, Log log) throws Exception{
        inserir(s, raiz, log);
    }

    public void inserir(String s, No i, Log log) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");

        } else if (s.charAt(0) < i.elemento) {
            log.comp++;
            inserir(s, i.esq, log);
            

        } else if (s.charAt(0) > i.elemento) {
            log.comp++;
            log.comp++;
            inserir(s, i.dir, log);

        } else {
            log.comp++;
            log.comp++;
            i.outro = inserir(s, i.outro, log);
        }
    }

    private No2 inserir(String s, No2 i, Log log) throws Exception {
        if (i == null) {
            i = new No2(s);

        } else if (s.compareTo(i.elemento) < 0) {
            log.comp++;
            i.esq = inserir(s, i.esq, log);

        } else if (s.compareTo(i.elemento) > 0) {
            log.comp++;
            log.comp++;
            i.dir = inserir(s, i.dir, log);

        } else {
            throw new Exception("Erro ao inserir: elemento existente!");
        }

        return i;
    }


    // Mostrar o caminho e pesquisar elementos
    public boolean mostrar(String x, Log log){
        return mostrar(raiz, x, log);
    }

    public boolean mostrar(No i, String x, Log log){
        boolean resp = false;
        if (i != null){
            if(mostrar(i.outro, x, log) == true){
                resp = true;
                return resp;
            }
            if(resp == false){
                MyIO.print("esq ");
                resp = mostrar(i.esq, x, log);

                if(resp == false){
                    MyIO.print("dir ");
                    resp = mostrar(i.dir, x, log);

                }
            }
        }
        return resp;
    }

    public boolean mostrar(No2 i, String x, Log log){
        boolean resp = false;
        if (i != null){
            if(x.compareTo(i.elemento) == 0){
                log.comp++;
                resp = true;
                return resp;
            }
            if(resp == false){
                log.comp++;
                MyIO.print("ESQ ");
                resp = mostrar(i.esq, x, log);

                if(resp == false){
                    MyIO.print("DIR ");
                    resp = mostrar(i.dir, x, log);

                }
            }
        }
    
        return resp;
    }


}



class Log{
    public int comp;
}

public class Q02 {

    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }



    public static void main(String[] args) {
        long inicio = new Date().getTime(); //pega a hora exata de inicio
        String entrada = MyIO.readLine();
        Serie serie = new Serie();
        ArvoreArvore arvore = new ArvoreArvore();
        Log log = new Log();
        
        // inserir elementos na arvore
        while(isFIM(entrada)==false){
            
            try {
                serie.ler("/tmp/series/" + entrada);
            } catch (Exception e) {}
            
            try {
                arvore.inserir(serie.getNome(), log);
            } catch (Exception e) {}
            
            serie = new Serie();
            entrada = MyIO.readLine();

        }

        // realizar pesquisas
        entrada = MyIO.readLine();
        
        while(isFIM(entrada) == false ){
            MyIO.print("raiz ");
            if(arvore.mostrar(entrada, log) == true){
                MyIO.print(" SIM\n");
            }else{
                MyIO.print(" NAO\n");
            }
            
            entrada = MyIO.readLine();
        }


        long fim = new Date().getTime(); //pega a hora exata do fim
        double tempo = (fim-inicio)/1000; //calcula o tempo de execucao
        int numComparacoes = log.comp;

        //preenchimento do arquivo log
        Arq.openWrite(" matricula arvoreArvore.txt", "UTF-8");
        Arq.print("741019\t"); //matricula
        Arq.print(tempo+"\t"); //tempo de execucao
        Arq.print(numComparacoes+"\t"); // comparacoe
        Arq.close();

    }

}
