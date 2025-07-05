package _01_Classes;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class _05_Produtos_Control {

    // Instância da classe ConectaBanco para realizar operações no banco de dados
    ConectaBanco conecta = new ConectaBanco();

    // Método para inserir um novo usuário no banco de dados
    public void insert_produtos(_05_Produtos_Model mod) {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados
            // Prepara o comando SQL para inserir um novo usuário
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO produtos (nome,  status, tipo, faixa_etaria, materiais, preco, custo_producao, estoque, tempo_producao ) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Preenche os parâmetros da query com os dados do _02_Jovens_Model
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getStatus());
            pst.setString(3, mod.getTipo());
            pst.setString(4, mod.getEtaria());
            pst.setString(5, mod.getMateriais());
            pst.setBigDecimal(6, mod.getPreco());
            pst.setBigDecimal(7, mod.getCusto());
            pst.setInt(8, mod.getEstoque());
            pst.setString(9, mod.getProducao());
            


            pst.executeUpdate(); // Executa a query no banco de dados
            // Exibe uma mensagem de confirmação de cadastro
            JOptionPane.showMessageDialog(null, "Dados do produto: " + mod.getNome() + " cadastrados", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            conecta.desconecta(); // Desconecta do banco de dados
        } catch (SQLException ex) {
            // Em caso de erro ao inserir, exibe o erro no console
            System.out.println("Error: " + ex);
        }
    }

    // Método para atualizar os dados de um usuário existente no banco de dados
    public void update(_05_Produtos_Model mod, int id) {
        conecta.conexao(); // Estabelece a conexão com o banco de dados
        try {
            // Prepara o comando SQL para atualizar os dados do usuário
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE produtos SET nome= ?, status= ?, tipo= ?, faixa_etaria= ?, materiais= ?, preco= ?, custo_producao=?, estoque=? , tempo_producao=? WHERE id=?");
            // Preenche os parâmetros da query com os dados do _02_Jovens_Model
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getStatus());
            pst.setString(3, mod.getTipo());
            pst.setString(4, mod.getEtaria());
            pst.setString(5, mod.getMateriais());
            pst.setBigDecimal(6, mod.getPreco());
            pst.setBigDecimal(7, mod.getCusto());
            pst.setInt(8, mod.getEstoque());
            pst.setString(9, mod.getProducao());
            pst.setInt(10, id); // Define o id do usuário que será atualizado
            pst.execute(); // Executa a atualização no banco de dados
            // Exibe uma mensagem de confirmação de atualização
            JOptionPane.showMessageDialog(null, "Dados do produto: " + mod.getNome() + " atualizados", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
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
                PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM produtos WHERE id = ?");
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

    // Método para preencher uma tabela com dados de usuários a partir de uma consulta SQL
    public void preencherTabela(String SQL, JTable tabela) {
        String id = null;

        conecta.conexao(); // Estabelece a conexão com o banco de dados
        ArrayList dados = new ArrayList(); // Cria uma lista para armazenar os dados
        // Define os nomes das colunas da tabela
        String[] Colunas = new String[]{"Face", "ID", "Nome", "Telefone", "Cpf", "Email", "Sexo", "Caatuacaoo"};
        conecta.executaSQL(SQL); // Executa a consulta SQL passada como parâmetro
        try {
            conecta.rs.first(); // Vai para o primeiro registro do ResultSet
            do {
                // Adiciona os dados do usuário na lista para preencher a tabela
                dados.add(new Object[]{
                    "", 
                    conecta.rs.getString("id"), 
                    conecta.rs.getString("nome") + " " + conecta.rs.getString("sobrenome"), 
                    conecta.rs.getString("telefone"), 
                    conecta.rs.getString("cpf"), 
                    conecta.rs.getString("email"),
                    conecta.rs.getString("sexo"),
                    conecta.rs.getString("caatuacaoo")
                });
            } while (conecta.rs.next()); // Continua enquanto houverem registros no ResultSet
        } catch (SQLException ex) {
            // Caso não haja registros ou ocorra algum erro, pode-se descomentar esta linha para exibir uma mensagem
             JOptionPane.showMessageDialog(null, "Lista de Cadastro Vazia!");
        } finally {
            conecta.desconecta(); // Desconecta do banco de dados
        }

        // Cria o modelo de tabela com os dados e colunas
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        tabela.setModel((TableModel) modelo); // Define o modelo da tabela
        tabela.getColumnModel().getColumn(0).setCellRenderer(new _05_Produtos_Control.ImageRenderer()); // Define o renderizador para a coluna de imagem
        tabela.getColumnModel().getColumn(1).setMaxWidth(0); // Esconde a coluna ID da tabela
        tabela.getColumnModel().getColumn(1).setMinWidth(0); // Esconde a coluna ID da tabela
        tabela.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0); // Esconde a coluna ID no cabeçalho
        tabela.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0); // Esconde a coluna ID no cabeçalho
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desativa o redimensionamento automático das colunas
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Define que somente uma linha pode ser selecionada por vez
    }

    // Classe interna para renderizar as imagens na tabela
    class ImageRenderer implements TableCellRenderer {

        public JLabel lbl = new JLabel(); // Cria uma JLabel para exibir a imagem

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                // Tenta obter o ID do usuário a partir da tabela para carregar a imagem correspondente
                Object text = table.getValueAt(row, 1);
                File image = new File("C:\\photos\\person." + text + ".1.jpg"); // Caminho para a imagem
                String path = image.getAbsolutePath(); // Obtém o caminho absoluto da imagem
                // Redimensiona a imagem para se ajustar ao tamanho da célula da tabela
                ImageIcon i = new ImageIcon(new ImageIcon(String.valueOf(path)).getImage().getScaledInstance(lbl.getWidth() + 50, lbl.getHeight() + 50, Image.SCALE_SMOOTH));
                lbl.setIcon(i); // Define a imagem como o ícone da JLabel
            } catch (Exception e) {
                // Em caso de erro, imprime o stack trace
                e.printStackTrace();
            }
            return lbl;
        }
    }
}