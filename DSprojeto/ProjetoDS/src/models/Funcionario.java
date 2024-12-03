package models; //Serve para poder importar esse arquivo para outros arquivos

public class Funcionario { //Classe principal 
    //Criação de variáveis
    private int id;
    private String nome;
    private String cpf;
    private boolean ativo;
    private String cep;
    private boolean faxineiro;
    private boolean confeiteiro;
    private boolean atendente;
    private String sexo;

    //Metodos construtores
    public Funcionario(){
    }

    public Funcionario(String nome, String cpf, boolean ativo, String cep, boolean faxineiro, boolean confeiteiro, boolean atendente, String sexo){
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        this.cep = cep;
        this.faxineiro = faxineiro;
        this.confeiteiro = confeiteiro;
        this.atendente = atendente;
        this.sexo = sexo;
    }

    public Funcionario(int id, String nome, String cpf, boolean ativo, String cep, boolean faxineiro, boolean confeiteiro, boolean atendente, String sexo){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        this.cep = cep;
        this.faxineiro = faxineiro;
        this.confeiteiro = confeiteiro;
        this.atendente = atendente;
        this.sexo = sexo;
    }

    //Getters e Setters

    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean getFaxineiro() {
        return faxineiro;
    }

    public void setFaxineiro(boolean faxineiro) {
        this.faxineiro = faxineiro;
    }

    public boolean getConfeiteiro() {
        return confeiteiro;
    }

    public void setConfeiteiro(boolean confeiteiro) {
        this.confeiteiro = confeiteiro;
    }

    public boolean getAtendente() {
        return atendente;
    }

    public void setAtendente(boolean atendente) {
        this.atendente = atendente;
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    //ToString
    @Override
    public String toString(){
        return "Funcionario [id=" + id + ", nome=" + nome + ", CPF=" + cpf + ", ativo=" + ativo + ", CEP=" + cep + ", faxineiro=" + faxineiro + ", confeiteiro=" + confeiteiro + ", atendente=" + atendente + ", sexo=" + sexo + "]";       
    }
}
