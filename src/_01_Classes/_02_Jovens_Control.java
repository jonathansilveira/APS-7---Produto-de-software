package _01_Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class _02_Jovens_Control {

    // Instância da classe ConectaBanco para realizar operações no banco de dados
    ConectaBanco conecta = new ConectaBanco();
   


    // Método para inserir um novo usuário no banco de dados
public void insert_jovens(_02_Jovens_Model mod) {
    // 1. Instancia o gerador de matrículas
    GeradorMatriculaJovens gerador = new GeradorMatriculaJovens();
    
    try {
        conecta.conexao();
        
        // 2. Gera matrícula única
        String matricula;
        int tentativas = 0;
        final int MAX_TENTATIVAS = 5;
        
        do {
            matricula = gerador.gerar();
            tentativas++;
            
            if (tentativas >= MAX_TENTATIVAS) {
                throw new SQLException("Não foi possível gerar uma matrícula única após " + MAX_TENTATIVAS + " tentativas");
            }
            
        } while (matriculaExiste(matricula));

        // 3. Prepara a query SQL
        String sql = "INSERT INTO jovens (matricula, rg, status, data_ingresso, "
                   + "curso, responsavel, nome_completo, data_nascimento, "
                   + "email, cpf, genero, observacoes) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = conecta.conn.prepareStatement(sql)) {
            // 4. Preenche os parâmetros
            pst.setString(1, matricula);
            pst.setString(2, mod.getRg());
            pst.setString(3, mod.getStatus());
            pst.setString(4, mod.getData());
            pst.setString(5, mod.getCurso());
            pst.setString(6, mod.getResponsavel());
            pst.setString(7, mod.getNome());
            pst.setString(8, mod.getDatanascimento());
            pst.setString(9, mod.getEmail());
            pst.setString(10, mod.getCpf());
            pst.setString(11, mod.getGenero());
            pst.setString(12, mod.getObservacoes());
            
            // 5. Executa e atualiza o model
            int affectedRows = pst.executeUpdate();
            
            if (affectedRows > 0) {
                mod.setMatricula(matricula);
                JOptionPane.showMessageDialog(null, 
                    "Cadastro realizado com sucesso!\nMatrícula: " + matricula,
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new SQLException("Falha ao cadastrar, nenhuma linha afetada");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,
            "Erro ao cadastrar jovem: " + ex.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        conecta.desconecta();
    }
}

// Método auxiliar para verificar matrícula existente
private boolean matriculaExiste(String matricula) throws SQLException {
    String sql = "SELECT 1 FROM jovens WHERE matricula = ? LIMIT 1";
    
    try (PreparedStatement pst = conecta.conn.prepareStatement(sql)) {
        pst.setString(1, matricula);
        
        try (ResultSet rs = pst.executeQuery()) {
            return rs.next(); // Retorna true se a matrícula já existir
        }
    }
}


    // Método para atualizar os dados de um usuário existente no banco de dados
    public void update(_02_Jovens_Model mod, int matricula) {
        conecta.conexao(); // Estabelece a conexão com o banco de dados
        try {
            // Prepara o comando SQL para atualizar os dados do usuário
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE jovens SET rg= ?, status= ?, data_ingresso= ?, curso= ?, responsavel= ?, nome_completo= ?, data_nascimento=?"
                    + ", email= ?, cpf= ?, genero= ?, observacoes= ? WHERE matricula=?");
            // Preenche os parâmetros da query com os dados do _02_Jovens_Model
            pst.setString(1, mod.getRg());
            pst.setString(2, mod.getStatus());
            pst.setString(3, mod.getData());
            pst.setString(4, mod.getCurso());
            pst.setString(5, mod.getResponsavel());
            pst.setString(6, mod.getNome());
            pst.setString(7, mod.getDatanascimento());
            pst.setString(8, mod.getEmail());
            pst.setString(9, mod.getCpf());
            pst.setString(10, mod.getGenero());
            pst.setString(11, mod.getObservacoes());
            pst.setString(12, mod.getMatricula()); // Define o id do usuário que será atualizado
            pst.execute(); // Executa a atualização no banco de dados
            // Exibe uma mensagem de confirmação de atualização
            JOptionPane.showMessageDialog(null, "Dados do(a): " + mod.getNome() + " atualizados", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            // Exibe mensagem de erro caso ocorra algum problema durante a atualização
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados\n ERRO: " + ex);
        }
        conecta.desconecta(); // Desconecta do banco de dados após a execução
    }

    // Método para excluir um usuário do banco de dados
    public void delete(String matricula) {
        // Pergunta ao usuário se ele tem certeza de que deseja excluir os dados
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Deseja excluir este dado?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        // Se o usuário confirmar a exclusão, executa a operação
        if (resposta == JOptionPane.YES_OPTION) {
            conecta.conexao(); // Estabelece a conexão com o banco de dados

            try {
                // Prepara o comando SQL para excluir o dado do usuário com o id informado
                PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM jovens WHERE matricula = ?");
                pst.setString(1, matricula); // Define o id do usuário a ser excluído
                pst.executeUpdate(); // Executa a exclusão no banco de dados
                // Exibe uma mensagem de sucesso após a exclusão
                JOptionPane.showMessageDialog(null, "Excluído com sucesso", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                // Em caso de erro, exibe uma mensagem de erro
                JOptionPane.showMessageDialog(null, "Erro ao excluir. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                conecta.desconecta(); // Desconecta do banco de dados
            }
        } else {
            // Caso o usuário cancele a exclusão, exibe uma mensagem informando que a operação foi cancelada
            JOptionPane.showMessageDialog(null, "Exclusão cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public ResultSet ListarCursos() {
        conecta.conexao();

        // Consulta SQL
        String sql = "SELECT nome FROM cursos";
        try {
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);
            return conecta.rs;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
            return null;
        }

    }
}
