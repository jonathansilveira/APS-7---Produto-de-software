package _01_Classes;

import java.math.BigDecimal;

public class _05_Produtos_Model {

    // Declaração das variáveis de instância que armazenam os dados do usuário
    private int id, estoque;
    private String nome, status, tipo, etaria, materiais, producao;
    private BigDecimal preco, custo;

    // Construtor padrão (sem parâmetros)
    public _05_Produtos_Model() {
    }

    // Construtor com parâmetros para inicializar as variáveis de instância
    public _05_Produtos_Model(int id, BigDecimal preco,BigDecimal custo, int estoque, String nome, String status, String tipo, String etaria, String materiais, String producao ) {
        this.id =id; 
        this.preco = preco;
        this.custo = custo;
        this.estoque = estoque;
        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
        this.etaria = etaria;
        this.materiais = materiais;
        this.producao = producao;
                
    }

    // Getters (Métodos para acessar os valores das variáveis de instância) 
    // Setters (Métodos para definir os valores das variáveis de instância)


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEtaria() {
        return etaria;
    }

    public void setEtaria(String etaria) {
        this.etaria = etaria;
    }

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }

    public String getProducao() {
        return producao;
    }

    public void setProducao(String producao) {
        this.producao = producao;
    }

    

    
  
}
