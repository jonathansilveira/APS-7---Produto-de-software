package _01_Classes;

public class _03_Professores_Model {

    // Declaração das variáveis de instância que armazenam os dados do usuário
    private int id;
    private String atuacao, cpf, status, formacao, curso, horario, nome, nascimento, email, genero, observacoes;

    // Construtor padrão (sem parâmetros)
    public _03_Professores_Model() {
    }

    // Construtor com parâmetros para inicializar as variáveis de instância
    public _03_Professores_Model(int id,String atuacao, String cpf, String status, String formacao, String curso, String horario, String nome, String nascimento, String email, String genero, String observacoes ) {
        this.id =id; 
        this.atuacao = atuacao; 
        this.cpf = cpf; 
        this.status = status;
        this.formacao = formacao;
        this.curso = curso;
        this.horario = horario;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.genero = genero;
        this.observacoes = observacoes;

    }

    // Getters (Métodos para acessar os valores das variáveis de instância) 
    // Setters (Métodos para definir os valores das variáveis de instância)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAtuacao() {
        return atuacao;
    }
    public void setAtuacao(String atuacao) {
        this.atuacao = atuacao;
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

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String formacaonascimento) {
        this.nascimento = formacaonascimento;
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
