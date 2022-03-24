import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


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


class Celula{
    public Serie elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.

	//Construtor
	public Celula() {
		this(null);
	}

	//Construtor com parametros
	public Celula(Serie elemento) {
      this.elemento = elemento;
      this.prox = null;
	}

}


class Lista{
    private Celula primeiro;
	private Celula ultimo;

	//Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}


	//Insere um elemento na primeira posicao da lista.
	public void inserirInicio(Serie x) {
		Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
		primeiro.prox = tmp;

		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}

      tmp = null;
	}


    //Insere um elemento na ultima posicao da lista.
	public void inserirFim(Serie x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}


    // Remove um elemento da primeira posicao da lista.
	public Serie removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Serie resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
		return resp;
	}


    //Remove um elemento da ultima posicao da lista.
	public Serie removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Serie resp = ultimo.elemento; 
        ultimo = i; 
        i = ultimo.prox = null;
      
		return resp;
	}


    //Insere um elemento em uma posicao especifica considerando que o primeiro elemento valido esta na posicao 0
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
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
   }


    //Remove um elemento de uma posicao especifica da lista considerando que o primeiro elemento valido esta na posicao 0.
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
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

		return resp;
	}

	//Mostra os elementos da lista
	public void mostrar() {
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			i.elemento.imprimir();
		}
		
	}

	//Calcula e retorna o tamanho, em numero de elementos, da lista.
   public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}


public class Q11 {

    //verifica a condicao de parada (FIM)
    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        Serie serie = new Serie();
        Lista lista = new Lista();
        
        while(isFIM(entrada)==false){

            try {
                serie.ler("/tmp/series/" + entrada);
            } catch (Exception e) {}
               
            lista.inserirFim(serie);

            serie = new Serie();
            entrada = MyIO.readLine();
        }

        //realizar as operacoes de insercao e remocao
        int num = MyIO.readInt();
        String comando;
        String complemento;
        serie = new Serie();
        
        

        for(int k=0; k < num; k++){
            entrada = MyIO.readLine();
            comando = "";
            complemento = "";

            
            

            for(int j=0; j<entrada.length(); j++){
                if(j<2){
                    comando += entrada.charAt(j);
                }else if(j>2){
                    complemento += entrada.charAt(j);
                }
            }


            
            //execucao do comando
            if(comando.equals("II")){
                
                try {
                    serie.ler("/tmp/series/" + complemento);
                } catch (Exception e) {}
    
                try {
                    lista.inserirInicio(serie);
                } catch (Exception e) {
                }

            }
    
            if(comando.equals("IF")){
                
                try {
                    serie.ler("/tmp/series/" + complemento);
                } catch (Exception e) {}
    
                try {
                    lista.inserirFim(serie);
                } catch (Exception e) {}
            }
    
            if(comando.equals("I*")){
                
                int pos;
                String aux = "";
                String nome = "";
                boolean espaco = false;

                for(int j=0; j<complemento.length(); j++){
                    if(complemento.charAt(j) == ' '){
                        espaco = true;
                        j++;
                    }

                    if(espaco == false){
                        aux += complemento.charAt(j);
                    }else{
                        nome += complemento.charAt(j);
                    }
                }
                pos = Integer.parseInt(aux);

                try {
                    serie.ler("/tmp/series/" + nome);
                } catch (Exception e) {}
    
                try {
                    lista.inserir(serie, pos);
                } catch (Exception e) {}

            }
    
            if(comando.equals("RI")){
                
                try {
                    serie = lista.removerInicio();
                } catch (Exception e) {}
                    
                MyIO.println("(R) " + serie.getNome());
            }
    
            if(comando.equals("RF")){
                
                try {
                    serie = lista.removerFim();
                } catch (Exception e) {}
                    
                MyIO.println("(R) " + serie.getNome());
            }
    
            if(comando.equals("R*")){
                int pos = Integer.parseInt(complemento);
                try {
                    serie = lista.remover(pos);
                } catch (Exception e) {}
                    
                MyIO.println("(R) " + serie.getNome());
            }

            serie = new Serie();
            
            
        }
        lista.mostrar();
    }
    

    


    

}
