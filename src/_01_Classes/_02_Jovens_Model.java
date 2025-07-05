package _01_Classes;

public class _02_Jovens_Model {

    // Declaração das variáveis de instância que armazenam os dados do usuário

    private String matricula, rg, cpf, status, data, curso, responsavel, nome, datanascimento, email, genero, observacoes;

    // Construtor padrão (sem parâmetros)
    public _02_Jovens_Model() {
    }

    // Construtor com parâmetros para inicializar as variáveis de instância
    public _02_Jovens_Model(String matricula,String rg, String cpf, String status, String data, String curso, String responsavel, String nome, String datanascimento, String email, String genero, String observacoes ) {
        this.matricula = matricula; 
        this.rg = rg; 
        this.cpf = cpf; 
        this.status = status;
        this.data = data;
        this.curso = curso;
        this.responsavel = responsavel;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.email = email;
        this.genero = genero;
        this.observacoes = observacoes;

    }

    // Getters (Métodos para acessar os valores das variáveis de instância) 
    // Setters (Métodos para definir os valores das variáveis de instância)


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

  
}
