package _01_Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class _06_Vendas_Control {

    // Instância da classe ConectaBanco para realizar operações no banco de dados
    ConectaBanco conecta = new ConectaBanco();

    // Método para inserir um novo usuário no banco de dados

    public void insert_vendas(_06_Vendas_Model mod) {
    // 1. Instancia o gerador de pedidos
    GeradorPedidoVendas gerador = new GeradorPedidoVendas();
    
    try {
        conecta.conexao();
        
        // Sincroniza o contador com o último pedido do banco
        int ultimoPedido = buscarUltimoPedido();
        GeradorPedidoVendas.sincronizarContador(ultimoPedido);

        // 2. Gera pedido único
        String pedido;
        int tentativas = 0;
        final int MAX_TENTATIVAS = 5;

        do {
            pedido = gerador.gerar();
            tentativas++;

            if (tentativas >= MAX_TENTATIVAS) {
                throw new SQLException("Não foi possível gerar um pedido único após " + MAX_TENTATIVAS + " tentativas");
            }

        } while (pedidoExiste(pedido));

        // Restante do código permanece igual...
        String sql = "INSERT INTO vendas (pedido, data_venda, hora, nome, cpf, telefone, email, "
                   + "produto, quantidade, preco, desconto, pagamento, modo, prazo, "
                   + "rua, numero, cep, bairro, cidade) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conecta.conn.prepareStatement(sql)) {
            // 3. Preenche os parâmetros
                pst.setString(1, pedido);
                pst.setString(2, mod.getData());
                pst.setString(3, mod.getHora());
                pst.setString(4, mod.getNome());
                pst.setString(5, mod.getCpf());
                pst.setString(6, mod.getTelefone());
                pst.setString(7, mod.getEmail());
                pst.setString(8, mod.getProduto());
                pst.setInt(9, mod.getQuantidade());
                pst.setBigDecimal(10, mod.getPreco());
                pst.setInt(11, mod.getDesconto());
                pst.setString(12, mod.getPagamento());
                pst.setString(13, mod.getModo());
                pst.setString(14, mod.getPrazo());
                pst.setString(15, mod.getRua());
                pst.setInt(16, mod.getNumero());
                pst.setString(17, mod.getCep());
                pst.setString(18, mod.getBairro());
                pst.setString(19, mod.getCidade());

                // 5. Executa e atualiza o model
                int affectedRows = pst.executeUpdate();

                if (affectedRows > 0) {
                    mod.setPedido(pedido);
                    JOptionPane.showMessageDialog(null,
                            "Cadastro realizado com sucesso!\nPedido: " + pedido,
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new SQLException("Falha ao cadastrar, nenhuma linha afetada");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar venda: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            conecta.desconecta();
        }
    }

// Novo método para buscar o último pedido
private int buscarUltimoPedido() throws SQLException {
    String sql = "SELECT MAX(CAST(SUBSTRING(pedido, 2) AS UNSIGNED)) FROM vendas";
    try (PreparedStatement pst = conecta.conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        return rs.next() ? rs.getInt(1) : 0;
    }
}

// Método pedidoExiste permanece igual
private boolean pedidoExiste(String pedido) throws SQLException {
    String sql = "SELECT 1 FROM vendas WHERE pedido = ? LIMIT 1";
    try (PreparedStatement pst = conecta.conn.prepareStatement(sql)) {
        pst.setString(1, pedido);
        try (ResultSet rs = pst.executeQuery()) {
            return rs.next();
        }
    }
}
    
    // Método para atualizar os dados de um usuário existente no banco de dados
    public void update(_06_Vendas_Model mod, String pedido) {
        conecta.conexao(); // Estabelece a conexão com o banco de dados
        try {
            // Prepara o comando SQL para atualizar os dados do usuário
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE vendas SET data_venda=?,  hora=?, nome=?, cpf=?, telefone=?, email=?, produto=?, quantidade=?, preco=?, "
                    + "                                                                desconto=?, pagamento=?, modo=?, prazo=?, rua=?, numero=?, cep=?, bairro=?, cidade=? WHERE pedido=?");
            // Preenche os parâmetros da query com os dados do _02_Jovens_Model
            pst.setString(1, mod.getData());
            pst.setString(2, mod.getHora());
            pst.setString(3, mod.getNome());
            pst.setString(4, mod.getCpf());
            pst.setString(5, mod.getTelefone());
            pst.setString(6, mod.getEmail());
            pst.setString(7, mod.getProduto());
            pst.setInt(8, mod.getQuantidade());
            pst.setBigDecimal(9, mod.getPreco());
            pst.setInt(10, mod.getDesconto());
            pst.setString(11, mod.getPagamento());
            pst.setString(12, mod.getModo());
            pst.setString(13, mod.getPrazo());
            pst.setString(14, mod.getRua());
            pst.setInt(15, mod.getNumero());
            pst.setString(16, mod.getCep());
            pst.setString(17, mod.getBairro());
            pst.setString(18, mod.getCidade());
            pst.setString(19, pedido); // Define o id do usuário que será atualizado
            pst.execute(); // Executa a atualização no banco de dados
            // Exibe uma mensagem de confirmação de atualização
            JOptionPane.showMessageDialog(null, "Venda do Pedido: " + mod.getPedido() + " atualizada", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            // Exibe mensagem de erro caso ocorra algum problema durante a atualização
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados\n ERRO: " + ex);
        }
        conecta.desconecta(); // Desconecta do banco de dados após a execução
    }

    // Método para excluir um usuário do banco de dados
    public void delete(String pedido) {
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
                PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM vendas WHERE pedido = ?");
                pst.setString(1, pedido); // Define o id do usuário a ser excluído
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

    public ResultSet ListarProdutos() {
        conecta.conexao();

        // Consulta SQL
        String sql = "SELECT nome FROM produtos";
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
