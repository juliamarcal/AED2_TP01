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


class NoAN{
    public boolean cor;
    public Serie elemento;
    public NoAN esq, dir;


    public NoAN (){
        cor = false;
        elemento = null;
        esq = dir = null;
    }

    public NoAN (Serie elemento){
        this(elemento, false, null, null);
    }

    public NoAN (Serie elemento, boolean cor){
        this(elemento, cor, null, null);
    }

    public NoAN (Serie elemento, boolean cor, NoAN esq, NoAN dir){
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }

}


class Alvinegra {
	private NoAN raiz; // Raiz da arvore.

	// Construtor da classe.
	public Alvinegra() {
		raiz = null;
	}

	// Pesquisar um elemento na arvore (chama o metodo recursivo)
	public void pesquisar(String elemento, Log log) {
        MyIO.print("raiz");
        if(pesquisar(elemento, raiz, log) == true){
            MyIO.print(" SIM\n");
        }else{
            MyIO.print(" NAO\n");
        }
		
	}

	// Metodo recursivo para pesquisar ume elemento
	private boolean pesquisar(String elemento, NoAN i, Log log) {
      boolean resp;
		if (i == null) {
            resp = false;

        } else if (elemento.compareTo(i.elemento.getNome())==0) {
            log.comp++;
            resp = true;

        } else if (elemento.compareTo(i.elemento.getNome()) < 0) {
            log.comp++;
            log.comp++;
            MyIO.print(" esq");
            resp = pesquisar(elemento, i.esq, log);

        } else {
            log.comp++;
            log.comp++;
            MyIO.print(" dir");
            resp = pesquisar(elemento, i.dir, log);
        }
        return resp;
	}



	// Inserir um elemento na arvore
	public void inserir(Serie elemento, Log log) throws Exception {

        //Se a arvore estiver vazia
        if(raiz == null){
            raiz = new NoAN(elemento);

        //Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null){
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0){
                log.comp++;
                raiz.esq = new NoAN(elemento);
            } else {
                log.comp++;
                raiz.dir = new NoAN(elemento);

            }

        //Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null){

            if(elemento.getNome().compareTo(raiz.elemento.getNome()) < 0){
                log.comp++;
                raiz.esq = new NoAN(elemento);

            } else if (elemento.getNome().compareTo(raiz.dir.elemento.getNome()) < 0){
                log.comp++;
                log.comp++;
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                log.comp++;
                log.comp++;
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;
         
        //Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null){
         
            if(elemento.getNome().compareTo(raiz.elemento.getNome()) > 0){ 
                log.comp++;
                raiz.dir = new NoAN(elemento);

            } else if (elemento.getNome().compareTo(raiz.esq.elemento.getNome()) > 0){
                log.comp++;
                log.comp++;
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                log.comp++;
                log.comp++;
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;

        //Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz, log);
        }

        raiz.cor = false;
    }

	// Metodo privado recursivo para inserir elemento.
	private void inserir(Serie elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i, Log log) throws Exception {
		if (i == null) {

            if(elemento.getNome().compareTo(pai.elemento.getNome()) < 0){ 
                log.comp++;
                i = pai.esq = new NoAN(elemento, true);
            } else {
                log.comp++;
                i = pai.dir = new NoAN(elemento, true);
            }

            if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
            }

        } else {

            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                i.cor = true;
                i.esq.cor = i.dir.cor = false;

                if(i == raiz){
                i.cor = false;

                }else if(pai.cor == true){
                balancear(bisavo, avo, pai, i);

                }

            }

            if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                log.comp++; 
                inserir(elemento, avo, pai, i, i.esq, log);
            } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
                log.comp++;
                log.comp++;
                inserir(elemento, avo, pai, i, i.dir, log);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }

        }

	}


    
    //balancear os elementos da arvore
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i){

        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){

            //4 tipos de reequilibrios e acoplamento
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0){
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null){
                raiz = avo;
            } else if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0){
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }

            //reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;

        } //if(pai.cor == true)

    }

    // rotcao simples a direita
    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    // rotacao simples a esquerda
   private NoAN rotacaoEsq(NoAN no) {
      NoAN noDir = no.dir;
      NoAN noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   // rotacao dupla a esquerda
   private NoAN rotacaoDirEsq(NoAN no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   // rotacao dupla a direita
   private NoAN rotacaoEsqDir(NoAN no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }

}


class Log{
    public int comp;
}


public class Q04 {

    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) throws Exception {
        long inicio = new Date().getTime(); //pega a hora exata de inicio
        String entrada = MyIO.readLine();
        Serie serie = new Serie();
        Alvinegra arvore = new Alvinegra();
        Log log = new Log();
        
        // inserir elementos na arvore
        while(isFIM(entrada)==false){
            
            serie.ler("/tmp/series/" + entrada);
            arvore.inserir(serie, log);
            

            serie = new Serie();
            entrada = MyIO.readLine();

        }


        // realizar pesquisas
        entrada = MyIO.readLine();
        while(isFIM(entrada) == false ){
            arvore.pesquisar(entrada, log);
            entrada = MyIO.readLine();

        }
        

        //preenchimento do arquivo log
        long fim = new Date().getTime(); //pega a hora exata do fim
        double tempo = (fim-inicio)/1000; //calcula o tempo de execucao
        int numComparacoes = log.comp;

        Arq.openWrite("matricula avinegra.txt", "UTF-8");
        Arq.print("741019\t"); //matricula
        Arq.print(tempo+"\t");//tempo de execucao
        Arq.print(numComparacoes+"\t");// comparacoes
        Arq.close();

    }


}
