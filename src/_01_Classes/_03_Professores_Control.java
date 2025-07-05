package _01_Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class _03_Professores_Control {

    // Instância da classe ConectaBanco para realizar operações no banco de dados
    ConectaBanco conecta = new ConectaBanco();

    // Método para inserir um novo usuário no banco de dados
    public void insert_professores(_03_Professores_Model mod) {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados
            // Prepara o comando SQL para inserir um novo usuário
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO professores (atuacao,  formacao, status, horario, curso, nome,  nascimento, email, cpf,  genero, observacoes ) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Preenche os parâmetros da query com os dados do _02_Jovens_Model
            pst.setString(1, mod.getAtuacao());
            pst.setString(2, mod.getFormacao());
            pst.setString(3, mod.getStatus());
            pst.setString(4, mod.getHorario());
            pst.setString(5, mod.getCurso());
            pst.setString(6, mod.getNome());
            pst.setString(7, mod.getNascimento());
            pst.setString(8, mod.getEmail());
            pst.setString(9, mod.getCpf());
            pst.setString(10, mod.getGenero());
            pst.setString(11, mod.getObservacoes());

            pst.executeUpdate(); // Executa a query no banco de dados
            // Exibe uma mensagem de confirmação de cadastro
            JOptionPane.showMessageDialog(null, "Dados do(a): " + mod.getNome() + " cadastrados", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            conecta.desconecta(); // Desconecta do banco de dados
        } catch (SQLException ex) {
            // Em caso de erro ao inserir, exibe o erro no console
            System.out.println("Error: " + ex);
        }
    }

    // Método para atualizar os dados de um usuário existente no banco de dados
    public void update(_03_Professores_Model mod, int id) {
        conecta.conexao(); // Estabelece a conexão com o banco de dados
        try {
            // Prepara o comando SQL para atualizar os dados do usuário
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE professores SET atuacao= ?, formacao= ?, status= ?, horario= ?, curso= ?, nome= ?, nascimento=?, email=?, cpf=?, genero=?, observacoes=? WHERE id=?");
            // Preenche os parâmetros da query com os dados do _02_Jovens_Model
            pst.setString(1, mod.getAtuacao());
            pst.setString(2, mod.getFormacao());
            pst.setString(3, mod.getStatus());
            pst.setString(4, mod.getHorario());
            pst.setString(5, mod.getCurso());
            pst.setString(6, mod.getNome());
            pst.setString(7, mod.getNascimento());
            pst.setString(8, mod.getEmail());
            pst.setString(9, mod.getCpf());
            pst.setString(10, mod.getGenero());
            pst.setString(11, mod.getObservacoes());
            pst.setInt(12, id); // Define o id do usuário que será atualizado
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
    public void delete(int id) {
        // Peatuacaounta ao usuário se ele tem certeza de que deseja excluir os dados
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
                PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM professores WHERE id = ?");
                pst.setInt(1, id); // Define o id do usuário a ser excluído
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