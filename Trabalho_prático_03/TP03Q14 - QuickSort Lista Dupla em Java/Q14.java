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
        this.formato = trim(tiraTags(br.readLine()));

        while(!br.readLine().contains("Duração")); 
        this.duracao = trim(tiraTags(br.readLine()));

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



class CelulaDupla {
	public Serie elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	// Construtor da classe.
	public CelulaDupla() {
		this(null);
	}

	// Construtor da classe.
	public CelulaDupla(Serie elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}



class ListaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;
    private int quantidade;


	// Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
        quantidade = 0;
	}


    // Retorna a quantudade de elementos presentes na lista
    public int getQuantidade() {
        return quantidade;
    }


    // Insere um elemento na primeira posicao da lista.
	public void inserirInicio(Serie x) {
		CelulaDupla tmp = new CelulaDupla(x);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if(primeiro == ultimo){
            ultimo = tmp;
        }else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
        quantidade++;
	}


    // Insere um elemento na ultima posicao da lista.
	public void inserirFim(Serie x) {
		ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
        quantidade++;
	}


    // Remove um elemento da primeira posicao da lista.
	public Serie removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;

        Serie resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;

        quantidade--;
        return resp;
	}


    // Remove um elemento da ultima posicao da lista.
	public Serie removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
        
        Serie resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        quantidade--;
        return resp;
	}


    // Insere um elemento em uma posicao especifica considerando que o primeiro elemento valido esta na posicao 0.
    public void inserir(Serie x, int pos) throws Exception {

        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");

        } else if (pos == 0){
            inserirInicio(x);

        } else if (pos == tamanho){
            inserirFim(x);

        } else {
            // Caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
        quantidade++;
    }


    // Remove um elemento de uma posicao especifica da lista considerando que o primeiro elemento valido esta na posicao 0.
	public Serie remover(int pos) throws Exception {
        Serie resp;
        int tamanho = tamanho();

        if (primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");

        } else if(pos < 0 || pos >= tamanho){
                throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");

        } else if (pos == 0){
            resp = removerInicio();

        } else if (pos == tamanho - 1){
            resp = removerFim();

        } else {
            // Caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro.prox;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            resp = i.elemento;
            i.prox = i.ant = null;
            i = null;
    }
        quantidade--;
		return resp;
	}


    // Mostra os elementos da lista.
	public void mostrar() {
		
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			i.elemento.imprimir();
		}
		
	}


    // Mostra os elementos da lista de forma invertida.
	public void mostrarInverso() {
		
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			i.elemento.imprimir();
        }
		
	}


    // Calcula e retorna o tamanho, em numero de elementos, da lista.
    public int tamanho() {
      int tamanho = 0; 
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

    // Retorna a CelulaDupla no indice correspondente da lista
    public CelulaDupla indice(int pos){
        CelulaDupla i = primeiro.prox;
        for(int j = 0; j < pos; j++, i = i.prox);
        
        return i;
    }

    //Ordenacao por quickSort dos elementos da lista com chave formato
    public void ordenar(int esq, int dir, Log log){
        int i = esq, j = dir;
        Serie pivo = indice(((dir+esq)/2)).elemento;


        while (i <= j) {
            while (isMaior(pivo, indice(i).elemento, log) == true) i++;
            while (isMaior(indice(j).elemento, pivo, log) == true) j--;
            if (i <= j) {
                swap(i, j, log);
                i++;
                j--;
            }
        }
        if (esq < j)  ordenar(esq, j, log);
        if (i < dir)  ordenar(i, dir, log);

    }

    //troca de elementos do array
    public void swap(int i, int j, Log log) {
        log.mov++;
        CelulaDupla el1 = indice(i);
        CelulaDupla el2 = indice(j);

        Serie tmp = el1.elemento.clone();
        el1.elemento = el2.elemento.clone();
        el2.elemento = tmp.clone();  
    }


    //compara se o elemento i e maior que o elemento j com base na chave PaisDeOrigem e, se der empate, na chave PaisDeOrigem
    public boolean isMaior(Serie i, Serie j, Log log){
        log.comp++;
        return(i.getPaisDeOrigem().compareTo(j.getPaisDeOrigem()) > 0 || ((i.getPaisDeOrigem().compareTo(j.getPaisDeOrigem()) == 0) && i.getNome().compareTo(j.getNome()) > 0));
    }


}



class Log{
    public int comp;
    public int mov;
}



public class Q14 {

    //verifica a condicao de parada (FIM)
    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        Serie serie = new Serie();
        ListaDupla lista = new ListaDupla();
        
        while(isFIM(entrada)==false){
            try {
                serie.ler("/tmp/series/" + entrada);
            } catch (Exception e) {}
                
            lista.inserirFim(serie);

            serie = new Serie();
            entrada = MyIO.readLine();
        }

        int n = lista.getQuantidade();
        
        long inicio = new Date().getTime(); //pega a hora exata de inicio
        Log log = new Log();

        lista.ordenar(0, n-1, log);

        //preenchimento do arquivo log
        long fim = new Date().getTime(); //pega a hora exata do fim
        double tempo = (fim-inicio)/1000; //calcula o tempo de execucao

        int numComparacoes = log.comp;
        int numMovimentacoes = log.mov;

        Arq.openWrite("matricula quicksort2.txt", "UTF-8");
        Arq.print("741019\t"); //matricula
        Arq.print(numComparacoes+"\t");// comparacoes
        Arq.print(numMovimentacoes+"\t");//movimentacoes
        Arq.print(tempo+"\t");//tempo de execucao
        Arq.close();


        lista.mostrar();

    }//fim main

}
