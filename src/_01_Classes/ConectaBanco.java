package _01_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaBanco {

    // Declaração dos objetos necessários para conexão e manipulação do banco de dados
    public Statement stm; // Objeto responsável por executar queries SQL
    public ResultSet rs;  // Objeto que armazenará o resultado das queries
    public Connection conn; // Objeto de conexão com o banco de dados

    // Dados de configuração do banco de dados
    private final String driver = "org.mysql.Driver"; // Driver do MySQL
    private final String path = "jdbc:mysql://localhost:3306/APS_7?user=root&password=&useTimezone=true&useSSL=false&requireSSL=false&serverTimezone=UTC"; // URL de conexão com o banco
    private final String user = "root"; // Usuário do banco de dados
    private final String pass = ""; // Senha do banco de dados

    // Método para estabelecer a conexão com o banco de dados
    public void conexao() {
        try {
            // Configura o driver do banco de dados
            System.setProperty("jdbc.Driver", driver);
            // Estabelece a conexão com o banco usando as informações fornecidas
            conn = DriverManager.getConnection(path, user, pass);
        } catch (SQLException e) {
            // Caso haja algum erro na conexão, será impresso uma mensagem de erro
            System.out.println("Error: " + e);
        }
    }

    // Método para desconectar do banco de dados
    public void desconecta() {
        try {
            // Fecha a conexão com o banco
            conn.close();
        } catch (SQLException e) {
            // Caso haja algum erro ao fechar a conexão, será impresso uma mensagem de erro
            System.out.println("Error: " + e);
        }
    }

    // Método para executar um comando SQL
    public void executaSQL(String SQL) {
        try {
            // Cria o Statement para executar a query
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Executa a query e armazena o resultado no ResultSet
            rs = stm.executeQuery(SQL);
        } catch (Exception e) {
            // Caso haja algum erro ao executar a query, será impresso uma mensagem de erro
            System.out.println("Error: " + e);
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
