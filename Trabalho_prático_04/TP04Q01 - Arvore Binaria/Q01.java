import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Date;
import java.lang.*;

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
    public Serie elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
    public No(Serie elemento) {
        this(elemento, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(Serie elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}



class ArvoreBinaria {
	private No raiz; // Raiz da arvore.

	// Construtor da classe
	public ArvoreBinaria() {
		raiz = null;
	}


	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public void pesquisar(String x, Log log) {
        MyIO.print("raiz ");
		if( pesquisar(x, raiz, log) == true ){
            MyIO.print("SIM\n");
        }else{
            MyIO.print("NAO\n");
        }
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No i, Log log) {
      boolean resp;

		if (i == null) {
            resp = false;

        } else if (x.compareTo(i.elemento.getNome()) == 0) {
            resp = true;
            log.comp++;

        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            MyIO.print("esq ");
            resp = pesquisar(x, i.esq, log);
            log.comp++;
            log.comp++;

        } else {
            MyIO.print("dir ");
            resp = pesquisar(x, i.dir, log);
            log.comp++;
            log.comp++;
        }

         return resp;
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Serie x, Log log) throws Exception {
		raiz = inserir(x, raiz, log);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(Serie x, No i, Log log) throws Exception {
        if (i == null) {
            i = new No(x);

        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(x, i.esq, log);
            log.comp++;
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(x, i.dir, log);
            log.comp++;
            log.comp++;
        } else {
            throw new Exception("Erro ao inserir!");
        }

            return i;
	}


	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover(String x, Log log) throws Exception {
		raiz = remover(x, raiz, log);
	}

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private No remover(String x, No i, Log log) throws Exception {

        if (i == null) {
            

        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            i.esq = remover(x, i.esq, log);
            log.comp++;

        } else if (x.compareTo(i.elemento.getNome()) > 0) {
            i.dir = remover(x, i.dir, log);
            log.comp++;
            log.comp++;

        // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;
            log.comp++;

        // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

        // No a esquerda e no a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
            }

            return i;
	}


    /**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharCentral() {
		
		caminharCentral(raiz);
		
	}

    /**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			i.elemento.imprimir();
			caminharCentral(i.dir); // Elementos da direita.
		}
	}



	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No maiorEsq(No i, No j) {

      // Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

      // Existe no a direita.
		} else {
         // Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

    public Serie getRaiz() throws Exception {
        return raiz.elemento;
    }

    public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
        return igual(a1.raiz, a2.raiz);
    }

    private static boolean igual (No i1, No i2){
        boolean resp;
        if(i1 != null && i2 != null){
            resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
        } else if(i1 == null && i2 == null){
            resp = true;
        } else {
            resp = false; 
        }
        return resp;
    }
  
}



class Log{
    public int comp;
}



public class Q01{

    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) throws Exception {
        long inicio = new Date().getTime(); //pega a hora exata de agora
        String entrada = MyIO.readLine();
        Serie serie = new Serie();
        double n = 0;
        ArvoreBinaria arvore = new ArvoreBinaria();
        Log log = new Log();
        
        // inserir elementos na arvore
        while(isFIM(entrada)==false){
            
            serie.ler("/tmp/series/" + entrada);
            arvore.inserir(serie, log);
            n++;

            serie = new Serie();
            entrada = MyIO.readLine();

        }


        //realizar as operacoes de insercao e remocao
        double num = MyIO.readInt();
        String comando;
        String complemento;
        serie = new Serie();
        
        for(int k=0; k <= num; k++){
            entrada = MyIO.readLine();
            comando = "";
            complemento = "";

            for(int j=0; j<entrada.length(); j++){
                if(j<1){
                    comando += entrada.charAt(j);
                }else if(j>1){
                    complemento += entrada.charAt(j);
                }
            }

            //execucao do comando
            if(comando.equals("I")){
                
                try {
                    serie.ler("/tmp/series/" + complemento);
                } catch (Exception e) {}
    
                arvore.inserir(serie, log);
                n++;
                
            }
    
            if(comando.equals("R")){
               arvore.remover(complemento, log);
               n--;
            }
    
            serie = new Serie();
            
        }


        // realizar pesquisas
        while(isFIM(entrada) == false ){
            arvore.pesquisar(entrada, log);
            entrada = MyIO.readLine();
            
        }

        long fim = new Date().getTime(); // pega a data do final do algoritmo
        double tempo = (fim-inicio)/1000;
        int numComparacoes = log.comp;
        
        /* double numComparacoes = Math.log(n)/  Math.log(2); */
        //Preenchimento do arquivo log
        Arq.openWrite("matricula_arvoreBinaria.txt");
		Arq.print("741019\t");// Matricula
		Arq.print(tempo+"\t");// Tempo de execucao
        Arq.print(numComparacoes+"\t");//Numero de comparações
        Arq.close();

        

    }

    
}


