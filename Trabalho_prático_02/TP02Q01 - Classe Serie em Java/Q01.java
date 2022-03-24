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


public class Q01{

    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) throws Exception {
        String entrada = MyIO.readLine();
        int i=0;
        Serie[] s = new Serie[1000];
        
        
        while(isFIM(entrada)==false){
            s[i] = new Serie();

            s[i].ler("/tmp/series/" + entrada);
            s[i].imprimir();
            i++;
            entrada = MyIO.readLine();
        }
    }
}