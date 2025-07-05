package _01_Classes;

import java.math.BigDecimal;

public class _06_Vendas_Model {

    // Declaração das variáveis de instância que armazenam os dados do usuário
    private int quantidade, desconto, numero;
    private String pedido, data, hora, nome, cpf, telefone, email, produto, pagamento, modo, prazo, rua, cep, bairro, cidade;
    private BigDecimal preco;

    // Construtor padrão (sem parâmetros)
    public _06_Vendas_Model() {
    }

    // Construtor com parâmetros para inicializar as variáveis de instância
    public _06_Vendas_Model(String pedido, int quantidade, BigDecimal preco, int desconto, int numero,
            String data, String hora, String nome, String cpf, String telefone, String email,
            String produto, String pagamento, String modo, String prazo, String rua, String cep, String bairro, String cidade) {
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
        this.numero = numero;
        this.data = data;
        this.hora = hora;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.produto = produto;
        this.pagamento = pagamento;
        this.modo = modo;
        this.prazo = prazo;
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    // Getters (Métodos para acessar os valores das variáveis de instância) 
    // Setters (Métodos para definir os valores das variáveis de instância)
    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }



    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
