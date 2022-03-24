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
            if(s.charAt(i)>='0' && s.charAt(i)<='9'){
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
        

        
        this.nome = trim(formataNome(nomeArquivo));

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



class LNomes{
    
    private String[] arrayNomes;
    private int n;

    //construtor
    public LNomes(int size){
        arrayNomes = new String[size];
        n = 0;
    }

    /*
     *  inserir elementos de forma ordenada na lista
     *  como a lista esta no modo string ela sera ordenada alfabeticamente 
     */
    public void inserirOrdenado(String item) {
		if (n < arrayNomes.length) {
            //procura a posicao de insercao do novo elemento, movendo os demais elementos para o final
            int pos;
            for(pos = n-1; pos > 0 && checaOrdemAlfabetica(item, arrayNomes[pos]) == true; pos--){
                arrayNomes[pos+1] = arrayNomes[pos];
            }
            //insercao do novo elemento
            arrayNomes[pos+1] = item;
            n++;
		}
	}

    //checa se um elemento esta na frente de outro na ordem alabetica
    private boolean checaOrdemAlfabetica(String antes, String depois){
        if(antes.charAt(0) < depois.charAt(0)){
            return true;
        }else if(antes.charAt(0)==depois.charAt(0)){
            if(antes.charAt(1) < depois.charAt(1)){
                return true;
            }else if(antes.charAt(1)==depois.charAt(1)){
                if(antes.charAt(2) < depois.charAt(2)){
                    return true;
                }else if(antes.charAt(2)==depois.charAt(2)){
                    if(antes.charAt(3) < depois.charAt(3)){
                        return true;
                    }else if(antes.charAt(3)==depois.charAt(3)){
                        if(antes.charAt(4) < depois.charAt(4)){
                            return true;
                        }else if(antes.charAt(4)==depois.charAt(4)){
                            if(antes.charAt(5) < depois.charAt(5)){
                                return true;
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //pesquisa binaria
    public boolean pesquisar(String item, Log log) {
      int dir = (arrayNomes.length - 1), esq = 0, meio;

      while (esq <= dir){
        meio = (esq + dir) / 2;

        log.comp++;
        if(arrayNomes[meio].equals(item)){
            return true;
        } else if (checaOrdemAlfabetica(arrayNomes[meio], item) == true) {
            log.comp++;
            esq = meio + 1;
        } else {
            log.comp++;
            dir = meio - 1;
        }
    }
        return false;
	}


    //mostrar os elementos da lista
    public void mostrar() {
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			System.out.print(arrayNomes[i] + " ");
		}
		System.out.println("]");
	}


}



class Log{
    public int comp;
}



public class Q04{

    //checa se a condicao de parada (FIM) foi alcancada
    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) {
        long inicio = new Date().getTime(); //pega a hora exata de inicio
        Log log = new Log();
        //preencher um vetor com os elementos Serie
        String entrada = MyIO.readLine();
        int i=0;
        Serie[] series = new Serie[1000];
        
        while(isFIM(entrada)==false){
            series[i] = new Serie();

            try {
                series[i].ler("/tmp/series/" + entrada);
            } catch (Exception e) {}
            i++;
            entrada = MyIO.readLine();
        }

        //preencher uma lista com os nomes das series
        LNomes nomes = new LNomes(i);

        for(int j=0; j<i; j++){
            nomes.inserirOrdenado(series[j].getNome());
        }        


        //efetuar a pesquisa binaria
        entrada = MyIO.readLine();

        while(isFIM(entrada)==false){
            if(nomes.pesquisar(entrada, log) == true){
                MyIO.println("SIM");
            }else{
                System.out.println("NÃO");
            }
            entrada = MyIO.readLine();
        }

    
        //preenchimento do arquivo log
        long fim = new Date().getTime(); //pega a hora exata do fim
        double tempo = (fim-inicio)/1000; //calcula o tempo de execucao
        
        int numComparacoes = log.comp;
        
        Arq.openWrite("matricula binaria.txt", "UTF-8");
        Arq.print("741019\t"); //matricula
        Arq.print(tempo+"\t");//tempo de execucao
        Arq.print(numComparacoes+"\t");// comparacoes
        Arq.close();
    
    
    }

}