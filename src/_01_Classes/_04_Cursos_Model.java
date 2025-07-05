package _01_Classes;

public class _04_Cursos_Model {

    // Declaração das variáveis de instância que armazenam os dados do usuário
    private int id;
    private String nome, status, modalidade, professor, descricao;

    // Construtor padrão (sem parâmetros)
    public _04_Cursos_Model() {
    }

    // Construtor com parâmetros para inicializar as variáveis de instância
    public _04_Cursos_Model(int id,String nome, String status, String modalidade, String professor, String descricao ) {
        this.id =id; 
        this.nome = nome;
        this.status = status;
        this.modalidade = modalidade;
        this.professor = professor;
        this.descricao = descricao;
                
    }

    // Getters (Métodos para acessar os valores das variáveis de instância) 
    // Setters (Métodos para definir os valores das variáveis de instância)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
        public String getDecricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    
  
}
