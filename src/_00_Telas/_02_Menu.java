package _00_Telas;


import javax.swing.JPanel;
import _01_Classes.ConectaBanco;
import _01_Classes.CustomTableHeader;
import _01_Classes._02_Jovens_Control;
import _01_Classes._02_Jovens_Model;
import _01_Classes._03_Professores_Control;
import _01_Classes._03_Professores_Model;
import _01_Classes._04_Cursos_Control;
import _01_Classes._04_Cursos_Model;
import _01_Classes._05_Produtos_Control;
import _01_Classes._05_Produtos_Model;
import _01_Classes._06_Vendas_Control;
import _01_Classes._06_Vendas_Model;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class _02_Menu extends javax.swing.JFrame {

// Variáveis globais
    private int mouseX, mouseY, matricula;
    private boolean mouseOver = false;
    ConectaBanco conecta = new ConectaBanco();
    ConectaBanco conectar = new ConectaBanco();
    CustomTableHeader custom = new CustomTableHeader();
    private boolean jovensAtivo = false;
    AjustaLarguraColunas ajustecolunas = new AjustaLarguraColunas();

    //JOVENS
    _02_Jovens_Control cod1 = new _02_Jovens_Control();
    _02_Jovens_Model mod1 = new _02_Jovens_Model();

    //PROFESSORES
    _03_Professores_Control cod2 = new _03_Professores_Control();
    _03_Professores_Model mod2 = new _03_Professores_Model();

    //CURSOS
    _04_Cursos_Control cod3 = new _04_Cursos_Control();
    _04_Cursos_Model mod3 = new _04_Cursos_Model();

    //PRODUTOS
    _05_Produtos_Control cod4 = new _05_Produtos_Control();
    _05_Produtos_Model mod4 = new _05_Produtos_Model();

    //VENDAS
    _06_Vendas_Control cod5 = new _06_Vendas_Control();
    _06_Vendas_Model mod5 = new _06_Vendas_Model();
    
    public _02_Menu(String usuario) {
        initComponents();
        setIcon(); // Classe setIcon, abre a tela com icon especificado
        Move(); // Classe Move, para movimentação da janela
        JFileChooser chooser = new JFileChooser();
        Panel_Main = new JPanel();
        txt_tipo2.setVisible(false);

        //JOVENS
        TotalJovens();
        CarregaTabelaJovens();
        ClicaTabelaJovens();
        restaurarDadosComboBoxJovens();
        Painel_2_Jovens.setVisible(false);
        ajustecolunas.ajustarLargura(tabela_jovens);
        CustomTableHeader.customizeHeader(tabela_jovens);
        configurarComboBoxHover(txt_curso);
        configurarComboBoxHover(txt_status);
        configurarComboBoxHover(txt_genero);
        

        //PROFESSORES
        Painel_3_Professores.setVisible(false);
        CarregaTabelaProfessores();
        ClicaTabelaProfessores();
        TotalProfessores();
        restaurarDadosComboBoxProfessores();
        ajustecolunas.ajustarLargura(tabela_professores);
        custom.customizeHeader(tabela_professores);
        configurarComboBoxHover(txt_status1);
        configurarComboBoxHover(txt_horario);
        configurarComboBoxHover(txt_curso1);
        configurarComboBoxHover(txt_genero1);

        //CURSOS
        Painel_4_Cursos.setVisible(false);
        CarregaTabelaCursos();
        restaurarDadosComboBoxCursos();
        ClicaTabelaCursos();
        TotalCursos();
        ajustecolunas.ajustarLargura(tabela_cursos);
        custom.customizeHeader(tabela_cursos);
        //custom.customizeCells(tabela_cursos);
        configurarComboBoxHover(txt_status2);
        configurarComboBoxHover(txt_modalidade);
        configurarComboBoxHover(txt_professor11);

        //PRODUTOS
        Painel_5_Produtos.setVisible(false);
        CarregaTabelaProdutos();
        ClicaTabelaProdutos();
        TotalProdutos();
        ajustecolunas.ajustarLargura(tabela_produtos);
        custom.customizeHeader(tabela_produtos);
        //custom.customizeCells(tabela_produtos);
        configurarComboBoxHover(txt_status3);
        configurarComboBoxHover(txt_tipo);
        configurarComboBoxHover(txt_etaria);

        //VENDAS
        Painel_6_Vendas.setVisible(false);
        CarregaTabelaVendas();
        ClicaTabelaVendas();
        TotalVendas();
        restaurarDadosComboBoxVendas();
        ajustecolunas.ajustarLargura(tabela_vendas);
        custom.customizeHeader(tabela_vendas);
        //custom.customizeCells(tabela_vendas);
        configurarComboBoxHover(txt_produto);
        configurarComboBoxHover(txt_pagamento);
        configurarComboBoxHover(txt_modo);

        //RELATÓRIOS
        Painel_7_Relatorios.setVisible(false);
        Painel_base.setVisible(false);
        Painel_Jovens.setVisible(false);
        Painel_Professores.setVisible(false);
        Painel_Cursos.setVisible(false);
        Painel_Produtos.setVisible(false);
        Painel_Vendas.setVisible(false);
        
        ajustecolunas.ajustarLargura(tabela_jovens);
        ajustecolunas.ajustarLargura(tabela_professores);
        ajustecolunas.ajustarLargura(tabela_cursos);
        ajustecolunas.ajustarLargura(tabela_produtos);
        ajustecolunas.ajustarLargura(tabela_vendas);
        configurarComboBoxHover(txt_tipo1);
        
        custom.customizeHeader(tabela_jovens);
        //custom.customizeCells(tabela_jovens);

        custom.customizeHeader(tabela_professores);
        //custom.customizeCells(tabela_professores);

        custom.customizeHeader(tabela_cursos);
        //custom.customizeCells(tabela_cursos);

        custom.customizeHeader(tabela_produtos);
        //custom.customizeCells(tabela_produtos);

        custom.customizeHeader(tabela_vendas);
        //custom.customizeCells(tabela_vendas);

        //FINANCEIRO
        txt_ong1.setVisible(false);
        txt_ong2.setVisible(false);
        txt_ong3.setVisible(false);
        txt_ong4.setVisible(false);
        txt_vendas1.setVisible(false);
        txt_vendas2.setVisible(false);
        txt_vendas3.setVisible(false);
        txt_vendas4.setVisible(false);
        configurarComboBoxHover(txt_tipo2);
        
    }
    
    class ComboBoxHoverRenderer extends DefaultListCellRenderer {
        
        private int hoverIndex = -1;
        
        public void setHoverIndex(int index) {
            this.hoverIndex = index;
        }
        
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Cores personalizadas
            if (isSelected) {
                setBackground(new Color(5, 24, 64));  // Cor quando selecionado
                setForeground(Color.WHITE);
            } else if (index == hoverIndex) {
                setBackground(new Color(1, 155, 225)); // Cor do hover
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            
            setOpaque(true);
            return this;
        }
    }
    
    private void configurarComboBoxHover(JComboBox<String> comboBox) {
        ComboBoxHoverRenderer renderer = new ComboBoxHoverRenderer();
        comboBox.setRenderer(renderer);
        
        comboBox.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (comboBox.isPopupVisible()) {
                    // Solução compatível com qualquer LookAndFeel
                    Object popup = comboBox.getUI().getAccessibleChild(comboBox, 0);
                    if (popup instanceof JPopupMenu) {
                        Component comp = ((JPopupMenu) popup).getComponent(0);
                        if (comp instanceof JList) {
                            JList<?> list = (JList<?>) comp;
                            Point p = e.getPoint();
                            SwingUtilities.convertPointToScreen(p, comboBox);
                            SwingUtilities.convertPointFromScreen(p, list);
                            int index = list.locationToIndex(p);
                            renderer.setHoverIndex(index);
                            comboBox.repaint();
                        }
                    }
                }
            }
        });

        // Resetar o hover quando o popup é fechado
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }
            
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                renderer.setHoverIndex(-1);
            }
            
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }
    
    public void LimpaCamposJovens() {
        txt_matricula.setText("");
        txt_rg.setText("");
        txt_status.setSelectedItem("Selecione");
        txt_data.setText("");
        txt_curso.setSelectedItem("Selecione");
        txt_matricula.setText("");
        
        txt_responsavel.setText("");
        
        txt_nome.setText("");
        txt_nascimento.setText("");
        txt_email.setText("");
        txt_cpf.setText("");
        txt_genero.setSelectedItem("Selecione");
        txt_observacoes.setText("");
    }
    
    public void LimpaCamposProfessores() {
        txt_id.setText("");
        txt_atuacao.setText("");
        txt_status1.setSelectedItem("Selecione");
        txt_formacao.setText("");
        txt_curso1.setSelectedItem("Selecione");
        txt_horario.setSelectedItem("Selecione");
        
        txt_nome1.setText("");
        txt_nascimento1.setText("");
        txt_email1.setText("");
        txt_cpf1.setText("");
        txt_genero1.setSelectedItem("Selecione");
        txt_observacao.setText("");
    }
    
    public void LimpaCamposCursos() {
        txt_id1.setText("");
        txt_nome2.setText("");
        txt_status2.setSelectedItem("Selecione");
        txt_modalidade.setSelectedItem("Selecione");
        txt_professor11.setSelectedItem("Selecione");
        txt_descricao.setText("");
        
    }
    
    public void LimpaCamposProdutos() {
        txt_id2.setText("");
        txt_nome3.setText("");
        txt_status3.setSelectedItem("Selecione");
        txt_tipo.setSelectedItem("Selecione");
        txt_etaria.setSelectedItem("Selecione");
        txt_materiais.clearSelection();
        
        txt_preco.setText("");
        txt_custo.setText("");
        txt_estoque.setText("");
        txt_producao.setText("");
    }
    
    public void LimpaCamposVendas() {
        txt_pedido.setText("");
        txt_data1.setText("");
        txt_hora.setText("");
        
        txt_nome4.setText("");
        txt_cpf2.setText("");
        txt_email2.setText("");
        txt_telefone.setText("");
        
        txt_modo.setSelectedItem("Selecione");
        txt_pagamento.setSelectedItem("Selecione");
        txt_prazo.setText("");
        txt_rua.setText("");
        txt_numero.setText("");
        txt_cep.setText("");
        txt_bairro.setText("");
        txt_cidade.setText("");
        
        txt_produto.setSelectedItem("Selecione");
        txt_quantidade.setText("");
        txt_preco1.setText("");
        txt_desconto.setText("");
        
    }
    
    public void LimpaCamposRelatorios() {
        txt_tipo1.setSelectedItem("Selecione");
    }
    
    public void LimpaCamposFinanceiro() {
        txt_tipo2.setSelectedItem("Selecione");
    }
    
    public void restaurarDadosComboBoxJovens() {
        try {
            ResultSet rs = cod1.ListarCursos();
            
            while (rs.next()) {
                txt_curso.addItem(rs.getString(1));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void restaurarDadosComboBoxProfessores() {
        try {
            ResultSet rs = cod2.ListarCursos();
            
            while (rs.next()) {
                txt_curso1.addItem(rs.getString(1));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void restaurarDadosComboBoxCursos() {
        try {
            ResultSet rs = cod3.ListarProfessores();
            
            while (rs.next()) {
                txt_professor11.addItem(rs.getString(1));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void restaurarDadosComboBoxVendas() {
        try {
            ResultSet rs = cod5.ListarProdutos();
            
            while (rs.next()) {
                txt_produto.addItem(rs.getString(1));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void CarregaTabelaJovens() {
        try {
            // Conectar ao banco (ajuste URL, usuário e senha)
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT matricula, nome_completo, curso, data_ingresso, status \n"
                    + "FROM jovens \n"
                    + "ORDER BY STR_TO_DATE(data_ingresso, '%d/%m/%Y') DESC;";
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_jovens = (DefaultTableModel) tabela_jovens.getModel();

            // Limpa as linhas antigas da tabela 
            tb_jovens.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("matricula"),
                    conecta.rs.getString("nome_completo"),
                    conecta.rs.getString("curso"),
                    conecta.rs.getString("data_ingresso"),
                    conecta.rs.getString("status")
                };
                tb_jovens.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
        
    }
    
    public void CarregaTabelaProfessores() {
        try {
            // Conectar ao banco (ajuste URL, usuário e senha)
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT id, nome, curso, status FROM professores ORDER BY id DESC";
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_professores = (DefaultTableModel) tabela_professores.getModel();

            // Limpa as linhas antigas da tabela
            tb_professores.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getInt("id"),
                    conecta.rs.getString("nome"),
                    conecta.rs.getString("curso"),
                    conecta.rs.getString("status")
                };
                tb_professores.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
        
    }
    
    public void CarregaTabelaCursos() {
        try {
            // Conectar ao banco (ajuste URL, usuário e senha)
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT id, nome, modalidade, status, professor FROM cursos ORDER BY id DESC";
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_professores = (DefaultTableModel) tabela_cursos.getModel();

            // Limpa as linhas antigas da tabela 
            tb_professores.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getInt("id"),
                    conecta.rs.getString("nome"),
                    conecta.rs.getString("modalidade"),
                    conecta.rs.getString("status"),
                    conecta.rs.getString("professor")
                };
                tb_professores.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
        
    }
    
    public void CarregaTabelaProdutos() {
        try {
            // Conectar ao banco (ajuste URL, usuário e senha)
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT id, nome, preco, estoque, status FROM produtos ORDER BY id DESC";
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_professores = (DefaultTableModel) tabela_produtos.getModel();

            // Limpa as linhas antigas da tabela 
            tb_professores.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getInt("id"),
                    conecta.rs.getString("nome"),
                    conecta.rs.getString("preco"),
                    conecta.rs.getString("estoque"),
                    conecta.rs.getString("status")
                };
                tb_professores.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
        
    }
    
    public void CarregaTabelaVendas() {
        try {
            // Conectar ao banco (ajuste URL, usuário e senha)
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT \n"
                    + "    pedido, \n"
                    + "    data_venda, \n"
                    + "    cpf \n"
                    + "FROM \n"
                    + "    vendas\n"
                    + "ORDER BY \n"
                    + "    CAST(SUBSTRING(pedido, 2) AS UNSIGNED) DESC;";
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_professores = (DefaultTableModel) tabela_vendas.getModel();

            // Limpa as linhas antigas da tabela
            tb_professores.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("pedido"),
                    conecta.rs.getString("data_venda"),
                    conecta.rs.getString("cpf")
                };
                tb_professores.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
        
    }
    
    public class AjustaLarguraColunas {
        
        public void ajustarLargura(JTable tabela) {
            // Configurações de dimensionamento 
            final int PADDING_CABECALHO = 40;  // Aumentado para a fonte Dialog 16
            final int PADDING_CONTEUDO = 20;
            final int LARGURA_MINIMA = 50;
            final int LARGURA_MAXIMA = 400;
            final int ALTURA_LINHA = 30;  // Nova constante para altura da linha

            TableColumnModel columnModel = tabela.getColumnModel();

            // Obtém as métricas da fonte atual da tabela para o conteúdo
            FontMetrics metricsConteudo = tabela.getFontMetrics(tabela.getFont());

            // Obtém as métricas da fonte do cabeçalho 
            FontMetrics metricsCabecalho = tabela.getTableHeader().getFontMetrics(tabela.getTableHeader().getFont());

            // Ajusta a altura das linhas
            tabela.setRowHeight(ALTURA_LINHA);
            
            for (int col = 0; col < tabela.getColumnCount(); col++) {
                int maxWidth = 0;

                // 1. Largura baseada no cabeçalho (usando a fonte personalizada)
                String header = tabela.getColumnName(col);
                maxWidth = metricsCabecalho.stringWidth(header) + PADDING_CABECALHO;

                // 2. Largura baseada no conteúdo
                for (int row = 0; row < tabela.getRowCount(); row++) {
                    Object value = tabela.getValueAt(row, col);
                    String text = (value == null) ? "" : value.toString();
                    int width = metricsConteudo.stringWidth(text) + PADDING_CONTEUDO;
                    
                    if (width > maxWidth) {
                        maxWidth = width;
                    }
                }

                // Aplica limites
                maxWidth = Math.max(maxWidth, LARGURA_MINIMA);
                maxWidth = Math.min(maxWidth, LARGURA_MAXIMA);

                // Ajusta a coluna
                TableColumn column = columnModel.getColumn(col);
                column.setMinWidth(LARGURA_MINIMA);
                column.setPreferredWidth(maxWidth);
                column.setMaxWidth(LARGURA_MAXIMA);
            }
            
            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }
    
    public void ClicaTabelaJovens() {
        tabela_jovens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela_jovens.getSelectedRow();
                if (linha != -1) {
                    String matricula = tabela_jovens.getValueAt(linha, 0).toString();                    
                    
                    try {
                        conecta.conexao();
                        String sql = "SELECT * FROM jovens WHERE matricula = ?";
                        PreparedStatement pst = conecta.conn.prepareStatement(sql);
                        pst.setString(1, matricula);                        
                        
                        ResultSet rs = pst.executeQuery();
                        
                        if (rs.next()) {
                            txt_matricula.setText(rs.getString("matricula"));
                            txt_rg.setText(rs.getString("rg"));
                            txt_status.setSelectedItem(rs.getString("status"));
                            txt_data.setText(rs.getString("data_ingresso"));
                            txt_curso.setSelectedItem(rs.getString("curso"));
                            txt_responsavel.setText(rs.getString("responsavel"));
                            
                            txt_nome.setText(rs.getString("nome_completo"));
                            txt_nascimento.setText(rs.getString("data_nascimento"));
                            txt_email.setText(rs.getString("email"));
                            txt_cpf.setText(rs.getString("cpf"));
                            txt_genero.setSelectedItem(rs.getString("genero"));
                            txt_observacoes.setText(rs.getString("observacoes"));
                            
                        }
                        
                        conecta.rs.close();
                        conecta.stm.close();
                        conecta.conn.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao buscar dados completos: " + e.getMessage());
                    }
                }
            }
        });
        
    }
    
    public void ClicaTabelaProfessores() {
        tabela_professores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela_professores.getSelectedRow();
                if (linha != -1) {
                    String id = tabela_professores.getValueAt(linha, 0).toString();
                    
                    try {
                        conecta.conexao();
                        String sql = "SELECT * FROM professores WHERE id = ?";
                        PreparedStatement pst = conecta.conn.prepareStatement(sql);
                        pst.setString(1, id);                        
                        
                        ResultSet rs = pst.executeQuery();
                        
                        if (rs.next()) {
                            txt_id.setText(rs.getString("id"));
                            txt_atuacao.setText(rs.getString("atuacao"));
                            txt_status1.setSelectedItem(rs.getString("status"));
                            txt_formacao.setText(rs.getString("formacao"));
                            txt_curso1.setSelectedItem(rs.getString("curso"));
                            txt_horario.setSelectedItem(rs.getString("horario"));
                            
                            txt_nome1.setText(rs.getString("nome"));
                            txt_nascimento1.setText(rs.getString("nascimento"));
                            txt_email1.setText(rs.getString("email"));
                            txt_cpf1.setText(rs.getString("cpf"));
                            txt_genero1.setSelectedItem(rs.getString("genero"));
                            txt_observacao.setText(rs.getString("observacoes"));
                            
                        }
                        
                        conecta.rs.close();
                        conecta.stm.close();
                        conecta.conn.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao buscar dados completos: " + e.getMessage());
                    }
                }
            }
        });
        
    }
    
    public void ClicaTabelaCursos() {
        tabela_cursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela_cursos.getSelectedRow();
                if (linha != -1) {
                    String id = tabela_cursos.getValueAt(linha, 0).toString();
                    
                    try {
                        conecta.conexao();
                        String sql = "SELECT * FROM cursos WHERE id = ?";
                        PreparedStatement pst = conecta.conn.prepareStatement(sql);
                        pst.setString(1, id);                        
                        
                        ResultSet rs = pst.executeQuery();
                        
                        if (rs.next()) {
                            txt_id1.setText(rs.getString("id"));
                            txt_nome2.setText(rs.getString("nome"));
                            txt_status2.setSelectedItem(rs.getString("status"));
                            txt_modalidade.setSelectedItem(rs.getString("modalidade"));
                            txt_professor11.setSelectedItem(rs.getString("professor"));
                            txt_descricao.setText(rs.getString("descricao"));
                            
                        }
                        
                        conecta.rs.close();
                        conecta.stm.close();
                        conecta.conn.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao buscar dados completos: " + e.getMessage());
                    }
                }
            }
        });
        
    }
    
    public void ClicaTabelaProdutos() {
        tabela_produtos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela_produtos.getSelectedRow();
                if (linha != -1) {
                    String id = tabela_produtos.getValueAt(linha, 0).toString();
                    
                    try {
                        conecta.conexao();
                        String sql = "SELECT * FROM produtos WHERE id = ?";
                        PreparedStatement pst = conecta.conn.prepareStatement(sql);
                        pst.setString(1, id);
                        
                        ResultSet rs = pst.executeQuery();
                        
                        if (rs.next()) {
                            txt_id2.setText(rs.getString("id"));
                            txt_nome3.setText(rs.getString("nome"));
                            txt_status3.setSelectedItem(rs.getString("status"));
                            txt_tipo.setSelectedItem(rs.getString("tipo"));
                            txt_etaria.setSelectedItem(rs.getString("faixa_etaria"));
                            
                            txt_preco.setText(rs.getString("preco"));
                            txt_custo.setText(rs.getString("custo_producao"));
                            txt_estoque.setText(rs.getString("estoque"));
                            txt_producao.setText(rs.getString("tempo_producao"));

// Recupera a string do banco e remove colchetes
                            String materiaisString = rs.getString("materiais");
                            materiaisString = materiaisString.replace("[", "").replace("]", "").trim();

// Separa os materiais em um array
                            String[] materiaisArray = materiaisString.split(",");

// Converte para uma lista para facilitar comparação
                            List<String> materiaisSelecionados = new ArrayList<>();
                            for (String material : materiaisArray) {
                                materiaisSelecionados.add(material.trim());
                            }

// Cria uma lista de índices a serem selecionados
                            List<Integer> indicesSelecionar = new ArrayList<>();

// Percorre o modelo do JList e marca os que estão na lista recuperada
                            ListModel<String> modelo = txt_materiais.getModel();
                            for (int i = 0; i < modelo.getSize(); i++) {
                                if (materiaisSelecionados.contains(modelo.getElementAt(i))) {
                                    indicesSelecionar.add(i);
                                }
                            }

// Converte lista de índices para array
                            int[] indicesArray = indicesSelecionar.stream().mapToInt(Integer::intValue).toArray();

// Seleciona os itens no JList
                            txt_materiais.setSelectedIndices(indicesArray);
                            
                        }
                        
                        conecta.rs.close();
                        conecta.stm.close();
                        conecta.conn.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao buscar dados completos: " + e.getMessage());
                    }
                }
            }
        });
        
    }
    
    public void ClicaTabelaVendas() {
        tabela_vendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela_vendas.getSelectedRow();
                if (linha != -1) {
                    String id = tabela_vendas.getValueAt(linha, 0).toString();
                    
                    try {
                        conecta.conexao();
                        String sql = "SELECT * FROM vendas WHERE pedido = ?";
                        PreparedStatement pst = conecta.conn.prepareStatement(sql);
                        pst.setString(1, id);                        
                        
                        ResultSet rs = pst.executeQuery();
                        
                        if (rs.next()) {
                            txt_pedido.setText(rs.getString("pedido"));
                            txt_data1.setText(rs.getString("data_venda"));
                            txt_hora.setText(rs.getString("hora"));
                            
                            txt_nome4.setText(rs.getString("nome"));
                            txt_cpf2.setText(rs.getString("cpf"));
                            txt_email2.setText(rs.getString("email"));
                            txt_telefone.setText(rs.getString("telefone"));
                            
                            txt_pagamento.setSelectedItem(rs.getString("pagamento"));
                            
                            txt_modo.setSelectedItem(rs.getString("modo"));
                            txt_prazo.setText(rs.getString("prazo"));
                            txt_rua.setText(rs.getString("rua"));
                            txt_numero.setText(rs.getString("numero"));
                            txt_cep.setText(rs.getString("cep"));
                            txt_bairro.setText(rs.getString("bairro"));
                            txt_cidade.setText(rs.getString("cidade"));
                            
                            txt_produto.setSelectedItem(rs.getString("produto"));
                            txt_quantidade.setText(rs.getString("quantidade"));
                            txt_preco1.setText(rs.getString("preco"));
                            txt_desconto.setText(rs.getString("desconto"));
                            
                        }
                        
                        conecta.rs.close();
                        conecta.stm.close();
                        conecta.conn.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao buscar dados completos: " + e.getMessage());
                    }
                }
            }
        });
        
    }
    
    public void TotalJovens() {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados

            // Consulta SQL para contar o número de matrículas
            String sql = "SELECT COUNT(matricula) FROM jovens";

            // Prepara a consulta
            PreparedStatement pst = conecta.conn.prepareStatement(sql);

            // Executa a consulta
            ResultSet rs = pst.executeQuery();

            // Se houver um resultado, pega o número de matrículas
            if (rs.next()) {
                int totalMatriculas = rs.getInt(1); // O resultado da contagem é na primeira coluna (índice 1)
                txt_total.setText(String.valueOf(totalMatriculas)); // Armazena o total no campo de texto
            }
            
            rs.close(); // Fecha o ResultSet
            pst.close(); // Fecha o PreparedStatement
        } catch (SQLException e) {
            // Exibe mensagem de erro se houver falha na execução da consulta
            JOptionPane.showMessageDialog(null, "Erro ao contar as matrículas: " + e.getMessage());
        } finally {
            conecta.desconecta(); // Fecha a conexão
        }
        
    }
    
    public void TotalProfessores() {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados

            // Consulta SQL para contar o número de matrículas
            String sql = "SELECT COUNT(id) FROM professores";

            // Prepara a consulta
            PreparedStatement pst = conecta.conn.prepareStatement(sql);

            // Executa a consulta
            ResultSet rs = pst.executeQuery();

            // Se houver um resultado, pega o número de matrículas
            if (rs.next()) {
                int totalProfessores = rs.getInt(1); // O resultado da contagem é na primeira coluna (índice 1)
                txt_total1.setText(String.valueOf(totalProfessores)); // Armazena o total no campo de texto
            }
            
            rs.close(); // Fecha o ResultSet
            pst.close(); // Fecha o PreparedStatement
        } catch (SQLException e) {
            // Exibe mensagem de erro se houver falha na execução da consulta
            JOptionPane.showMessageDialog(null, "Erro ao contar as matrículas: " + e.getMessage());
        } finally {
            conecta.desconecta(); // Fecha a conexão
        }
        
    }
    
    public void TotalCursos() {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados

            // Consulta SQL para contar o número de matrículas
            String sql = "SELECT COUNT(id) FROM cursos";

            // Prepara a consulta
            PreparedStatement pst = conecta.conn.prepareStatement(sql);

            // Executa a consulta
            ResultSet rs = pst.executeQuery();

            // Se houver um resultado, pega o número de matrículas
            if (rs.next()) {
                int totalCursos = rs.getInt(1); // O resultado da contagem é na primeira coluna (índice 1)
                txt_total2.setText(String.valueOf(totalCursos)); // Armazena o total no campo de texto
            }
            
            rs.close(); // Fecha o ResultSet
            pst.close(); // Fecha o PreparedStatement
        } catch (SQLException e) {
            // Exibe mensagem de erro se houver falha na execução da consulta
            JOptionPane.showMessageDialog(null, "Erro ao contar as matrículas: " + e.getMessage());
        } finally {
            conecta.desconecta(); // Fecha a conexão
        }
        
    }
    
    public void TotalProdutos() {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados

            // Consulta SQL para contar o número de matrículas
            String sql = "SELECT COUNT(id) FROM produtos";

            // Prepara a consulta
            PreparedStatement pst = conecta.conn.prepareStatement(sql);

            // Executa a consulta
            ResultSet rs = pst.executeQuery();

            // Se houver um resultado, pega o número de matrículas
            if (rs.next()) {
                int totalCursos = rs.getInt(1); // O resultado da contagem é na primeira coluna (índice 1)
                txt_total3.setText(String.valueOf(totalCursos)); // Armazena o total no campo de texto
            }
            
            rs.close(); // Fecha o ResultSet
            pst.close(); // Fecha o PreparedStatement
        } catch (SQLException e) {
            // Exibe mensagem de erro se houver falha na execução da consulta
            JOptionPane.showMessageDialog(null, "Erro ao contar as matrículas: " + e.getMessage());
        } finally {
            conecta.desconecta(); // Fecha a conexão
        }
        
    }
    
    public void TotalVendas() {
        try {
            conecta.conexao(); // Estabelece a conexão com o banco de dados

            // Consulta SQL para contar o número de matrículas
            String sql = "SELECT COUNT(pedido) FROM vendas";

            // Prepara a consulta
            PreparedStatement pst = conecta.conn.prepareStatement(sql);

            // Executa a consulta
            ResultSet rs = pst.executeQuery();

            // Se houver um resultado, pega o número de matrículas
            if (rs.next()) {
                int totalVendas = rs.getInt(1); // O resultado da contagem é na primeira coluna (índice 1)
                txt_total4.setText(String.valueOf(totalVendas)); // Armazena o total no campo de texto
            }
            
            rs.close(); // Fecha o ResultSet
            pst.close(); // Fecha o PreparedStatement
        } catch (SQLException e) {
            // Exibe mensagem de erro se houver falha na execução da consulta
            JOptionPane.showMessageDialog(null, "Erro ao contar as matrículas: " + e.getMessage());
        } finally {
            conecta.desconecta(); // Fecha a conexão
        }
        
    }
    
    public void insertDatabaseJovens() {

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        mod1.setRg(txt_rg.getText());
        mod1.setStatus(txt_status.getSelectedItem().toString());
        mod1.setData(txt_data.getText());
        mod1.setCurso(txt_curso.getSelectedItem().toString());
        mod1.setResponsavel(txt_responsavel.getText());
        
        mod1.setNome(txt_nome.getText());
        mod1.setDatanascimento(txt_nascimento.getText());
        mod1.setEmail(txt_email.getText());
        mod1.setCpf(txt_cpf.getText());
        mod1.setGenero(txt_genero.getSelectedItem().toString());
        mod1.setObservacoes(txt_observacoes.getText());
        
        cod1.insert_jovens(mod1);
    }
    
    public void insertDatabaseProfessores() {

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        mod2.setAtuacao(txt_atuacao.getText());
        mod2.setStatus(txt_status1.getSelectedItem().toString());
        mod2.setFormacao(txt_formacao.getText());
        mod2.setCurso(txt_curso1.getSelectedItem().toString());
        mod2.setHorario(txt_horario.getSelectedItem().toString());
        
        mod2.setNome(txt_nome1.getText());
        mod2.setNascimento(txt_nascimento1.getText());
        mod2.setEmail(txt_email1.getText());
        mod2.setCpf(txt_cpf1.getText());
        mod2.setGenero(txt_genero1.getSelectedItem().toString());
        mod2.setObservacoes(txt_observacao.getText());
        
        cod2.insert_professores(mod2);
    }
    
    public void insertDatabaseCursos() {

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        mod3.setNome(txt_nome2.getText());
        mod3.setStatus(txt_status2.getSelectedItem().toString());
        mod3.setModalidade(txt_modalidade.getSelectedItem().toString());
        mod3.setProfessor(txt_professor11.getSelectedItem().toString());
        mod3.setDescricao(txt_descricao.getText());
        
        cod3.insert_cursos(mod3);
    }
    
    public void insertDatabaseProdutos() {
        
        mod4.setNome(txt_nome3.getText());
        mod4.setStatus(txt_status3.getSelectedItem().toString());
        mod4.setTipo(txt_tipo.getSelectedItem().toString());
        mod4.setEtaria(txt_etaria.getSelectedItem().toString());
        String materiais = String.join(",", txt_materiais.getSelectedValuesList());
        mod4.setMateriais(materiais);
        mod4.setPreco(new BigDecimal(txt_preco.getText()));
        mod4.setCusto(new BigDecimal(txt_custo.getText()));
        mod4.setEstoque(Integer.parseInt(txt_estoque.getText()));
        mod4.setProducao(txt_producao.getText());
        
        cod4.insert_produtos(mod4);
    }
    
    public void insertDatabaseVendas() {

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        mod5.setData(txt_data1.getText());
        mod5.setHora(txt_hora.getText());
        
        mod5.setNome(txt_nome4.getText());
        mod5.setCpf(txt_cpf2.getText());
        mod5.setEmail(txt_email2.getText());
        mod5.setTelefone(txt_telefone.getText());
        
        mod5.setPagamento(txt_pagamento.getSelectedItem().toString());
        
        mod5.setModo(txt_modo.getSelectedItem().toString());
        mod5.setPrazo(txt_prazo.getText());
        mod5.setRua(txt_rua.getText());
        mod5.setNumero(Integer.parseInt(txt_numero.getText()));
        mod5.setCep(txt_cep.getText());
        mod5.setBairro(txt_bairro.getText());
        mod5.setCidade(txt_cidade.getText());
        
        mod5.setProduto(txt_produto.getSelectedItem().toString());
        mod5.setQuantidade(Integer.parseInt(txt_quantidade.getText()));
        mod5.setPreco(new BigDecimal(txt_preco1.getText()));
        mod5.setDesconto(Integer.parseInt(txt_desconto.getText()));
        
        cod5.insert_vendas(mod5);
    }
    
    private _02_Menu() {
        throw new UnsupportedOperationException("Not supported yet."); // Lança uma exceção UnsupportedOperationException com a mensagem "Not supported yet." indicando que a implementação está pendente.
    }

// Carrega a imagem do ícone a partir do recurso no caminho especificado
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/_02_Imagens/Icon.png")));
    }

// Classe que permite a movimentação da janela arrastando com o mouse
    public void Move() {
        // Adicionando o MouseListener e MouseMotionListener ao Panel_Main
        Panel_Main.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Armazena as coordenadas X e Y do ponto onde o mouse foi pressionado na tela
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        Panel_Main.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Calcula a nova posição da janela enquanto o mouse está sendo arrastado
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                // Define a nova posição da janela com base na movimentação do mouse
                setLocation(x, y);
            }
        });
    }
    
    public void Relatório_Jovens() {
        try {
            // Conectar ao banco (ajuste URL, usuário e senha)
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT \n"
                    + "    j.matricula AS \"Matrícula\",\n"
                    + "    j.status AS \"Status\",\n"
                    + "    j.nome_completo AS \"Nome\",\n"
                    + "    TIMESTAMPDIFF(YEAR, STR_TO_DATE(j.data_nascimento, '%d/%m/%Y'), CURDATE()) AS \"Idade\",\n"
                    + "    j.curso AS \"Curso\",\n"
                    + "    CASE \n"
                    + "        WHEN j.observacoes LIKE '%dedicado%' OR j.observacoes LIKE '%destaque%' THEN '90%'\n"
                    + "        WHEN j.observacoes LIKE '%monitor%' THEN '95%'\n"
                    + "        ELSE '85%'\n"
                    + "    END AS \"Frequência (%)\",\n"
                    + "    CASE \n"
                    + "        WHEN j.observacoes LIKE '%destaque%' THEN '9.0'\n"
                    + "        WHEN j.observacoes LIKE '%monitor%' THEN '8.8'\n"
                    + "        ELSE '8.0'\n"
                    + "    END AS \"Nota Média\",\n"
                    + "    CASE \n"
                    + "        WHEN j.email IS NOT NULL THEN 'Email'\n"
                    + "        ELSE 'Não informado'\n"
                    + "    END AS \"Método Contato\",\n"
                    + "    j.data_ingresso AS \"Data Ingresso\",\n"
                    + "    DATE_FORMAT(DATE_ADD(STR_TO_DATE(j.data_ingresso, '%d/%m/%Y'), INTERVAL 6 MONTH), '%d/%m/%Y') AS \"Última Atividade\",\n"
                    + "    CASE \n"
                    + "        WHEN j.observacoes LIKE '%bolsista%' THEN 'Bolsa'\n"
                    + "        WHEN j.observacoes LIKE '%pendente%' THEN 'Pendente'\n"
                    + "        ELSE 'Regular'\n"
                    + "    END AS \"Situação Financeira\"\n"
                    + "FROM jovens j\n"
                    + "ORDER BY STR_TO_DATE(j.data_ingresso, '%d/%m/%Y') DESC;";
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_jovens = (DefaultTableModel) tabela_jovens1.getModel();

            // Limpa as linhas antigas da tabela
            tb_jovens.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("Matrícula"),
                    conecta.rs.getString("Status"),
                    conecta.rs.getString("Nome"),
                    conecta.rs.getString("Idade"),
                    conecta.rs.getString("Curso"),
                    conecta.rs.getString("Frequência (%)"),
                    conecta.rs.getString("Nota Média"),
                    conecta.rs.getString("Método Contato"),
                    conecta.rs.getString("Data Ingresso"),
                    conecta.rs.getString("Última Atividade"),
                    conecta.rs.getString("Situação Financeira")
                };
                tb_jovens.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void Relatório_Professores() {
        try {
            // Conectar ao banco 
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT \n"
                    + "    p.id AS \"ID\",\n"
                    + "    p.status AS \"Status\",\n"
                    + "    p.nome AS \"Nome\",\n"
                    + "    p.formacao AS \"Formação\",\n"
                    + "    p.curso AS \"Curso\",\n"
                    + "    p.horario AS \"Horário\",\n"
                    + "    CASE \n"
                    + "        WHEN p.horario = 'Integral' THEN 'Integral'\n"
                    + "        ELSE 'Parcial'\n"
                    + "    END AS \"Disponibilidade\",\n"
                    + "    DATE_FORMAT(STR_TO_DATE(MIN(j.data_ingresso), '%d/%m/%Y'), '%d/%m/%Y') AS \"Início no Curso\",\n"
                    + "    CASE \n"
                    + "        WHEN p.observacoes LIKE '%sênior%' OR p.atuacao LIKE '%mestrado%' THEN 'CLT'\n"
                    + "        WHEN p.observacoes LIKE '%consultor%' THEN 'PJ'\n"
                    + "        ELSE 'Temporário'\n"
                    + "    END AS \"Contrato\",\n"
                    + "    CONCAT(\n"
                    + "        TIMESTAMPDIFF(MONTH, \n"
                    + "            STR_TO_DATE(MIN(j.data_ingresso), '%d/%m/%Y'), \n"
                    + "            CURDATE()\n"
                    + "        ), \n"
                    + "        ' meses'\n"
                    + "    ) AS \"Tempo de Contrato\",\n"
                    + "    CASE \n"
                    + "        WHEN p.observacoes LIKE '%excelente%' THEN '4.9/5'\n"
                    + "        WHEN p.observacoes LIKE '%destac%' THEN '4.7/5'\n"
                    + "        ELSE '4.5/5'\n"
                    + "    END AS \"Avaliação Alunos\"\n"
                    + "FROM professores p\n"
                    + "LEFT JOIN jovens j ON j.curso = p.curso\n"
                    + "GROUP BY p.id, p.nome, p.curso, p.status, p.horario, p.formacao, p.observacoes, p.atuacao\n"
                    + "ORDER BY p.id DESC;";
            
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_professores = (DefaultTableModel) tabela_professores1.getModel();

            // Limpa as linhas antigas da tabela
            tb_professores.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("ID"),
                    conecta.rs.getString("Status"),
                    conecta.rs.getString("Nome"),
                    conecta.rs.getString("Formação"),
                    conecta.rs.getString("Curso"),
                    conecta.rs.getString("Horário"),
                    conecta.rs.getString("Disponibilidade"),
                    conecta.rs.getString("Início no Curso"),
                    conecta.rs.getString("Contrato"),
                    conecta.rs.getString("Tempo de Contrato"),
                    conecta.rs.getString("Avaliação Alunos")
                };
                tb_professores.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void Relatório_Cursos() {
        try {
            // Conectar ao banco
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT \n"
                    + "    c.id AS \"ID\",\n"
                    + "    c.status AS \"Status\",\n"
                    + "    c.nome AS \"Nome\",\n"
                    + "    c.modalidade AS \"Modalidade\",\n"
                    + "    CASE \n"
                    + "        WHEN c.nome LIKE '%Básica%' THEN 'Iniciante'\n"
                    + "        WHEN c.nome LIKE '%Avançada%' THEN 'Avançado'\n"
                    + "        ELSE 'Intermediário'\n"
                    + "    END AS \"Nível\",\n"
                    + "    CASE \n"
                    + "        WHEN c.nome LIKE '%semestral%' THEN 6\n"
                    + "        WHEN c.nome LIKE '%anual%' THEN 12\n"
                    + "        WHEN c.nome LIKE '%Básica%' THEN 6\n"
                    + "        WHEN c.nome LIKE '%Avançada%' THEN 8\n"
                    + "        ELSE 4\n"
                    + "    END AS \"Duração (meses)\",\n"
                    + "    25 AS \"Vagas\",  -- Quantidade fixa de vagas definida como 25\n"
                    + "    (SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Ativo') AS \"Matrículas\",\n"
                    + "    CASE\n"
                    + "        WHEN (SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Inativo') > 0 \n"
                    + "        THEN CONCAT(ROUND((SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Ativo') * 100.0 / \n"
                    + "                   ((SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Ativo') + \n"
                    + "                    (SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Inativo')), 0), '%')\n"
                    + "        ELSE '100%'\n"
                    + "    END AS \"Taxa Conclusão\",\n"
                    + "    CASE\n"
                    + "        WHEN c.nome LIKE '%Programação%' THEN '85%'\n"
                    + "        WHEN c.nome LIKE '%Digital%' THEN '80%'\n"
                    + "        WHEN c.nome LIKE '%Básica%' THEN '70%'\n"
                    + "        WHEN c.nome LIKE '%Avançada%' THEN '75%'\n"
                    + "        ELSE '72%'\n"
                    + "    END AS \"Taxa de Empregabilidade\",\n"
                    + "    CASE \n"
                    + "        WHEN c.modalidade = 'Online' THEN 'R$900'\n"
                    + "        WHEN c.modalidade = 'Presencial' THEN 'R$1.500'\n"
                    + "        ELSE 'R$1.200'\n"
                    + "    END AS \"Investimento\"\n"
                    + "FROM cursos c\n"
                    + "ORDER BY c.id DESC;";
            
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_cursos = (DefaultTableModel) tabela_cursos1.getModel();

            // Limpa as linhas antigas da tabela 
            tb_cursos.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("ID"),
                    conecta.rs.getString("Status"),
                    conecta.rs.getString("Nome"),
                    conecta.rs.getString("Modalidade"),
                    conecta.rs.getString("Nível"),
                    conecta.rs.getString("Duração (meses)"),
                    conecta.rs.getString("Vagas"),
                    conecta.rs.getString("Matrículas"),
                    conecta.rs.getString("Taxa Conclusão"),
                    conecta.rs.getString("Taxa de Empregabilidade"),
                    conecta.rs.getString("Investimento")
                };
                tb_cursos.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void Relatório_Produtos() {
        try {
            // Conectar ao banco 
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT \n"
                    + "    pr.id AS \"id\",\n"
                    + "    pr.nome AS \"Nome\",\n"
                    + "    pr.faixa_etaria AS \"Faixa Etária\",\n"
                    + "    pr.preco AS \"Preço\",\n"
                    + "    ROUND(pr.preco * 0.5, 2) AS \"Lucro Estipulado (50%)\",\n"
                    + "    CASE \n"
                    + "        WHEN (SELECT SUM(v.quantidade) FROM vendas v WHERE v.produto LIKE CONCAT('%', pr.nome, '%')) > 50 THEN 'Alta'\n"
                    + "        WHEN (SELECT SUM(v.quantidade) FROM vendas v WHERE v.produto LIKE CONCAT('%', pr.nome, '%')) > 20 THEN 'Média'\n"
                    + "        ELSE 'Baixa'\n"
                    + "    END AS \"Demanda\",\n"
                    + "    pr.estoque AS \"Estoque\",\n"
                    + "    CASE \n"
                    + "        WHEN pr.estoque < 20 THEN 'Repor'\n"
                    + "        WHEN pr.estoque > 70 THEN 'Estoque OK'\n"
                    + "        ELSE 'Monitorar'\n"
                    + "    END AS \"Status Estoque\",\n"
                    + "    (SELECT CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vendas WHERE pagamento LIKE 'Crédito%'), 1), '%') \n"
                    + "     FROM vendas v \n"
                    + "     WHERE v.produto LIKE CONCAT('%', pr.nome, '%') AND v.pagamento LIKE 'Crédito%') AS \"% Cartão\",\n"
                    + "    (SELECT CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vendas WHERE pagamento = 'Pix'), 1), '%') \n"
                    + "     FROM vendas v \n"
                    + "     WHERE v.produto LIKE CONCAT('%', pr.nome, '%') AND v.pagamento = 'Pix') AS \"% Pix\",\n"
                    + "    (SELECT CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vendas WHERE pagamento NOT LIKE 'Crédito%' AND pagamento != 'Pix'), 1), '%') \n"
                    + "     FROM vendas v \n"
                    + "     WHERE v.produto LIKE CONCAT('%', pr.nome, '%') AND v.pagamento NOT LIKE 'Crédito%' AND v.pagamento != 'Pix') AS \"% Outros\"\n"
                    + "FROM produtos pr\n"
                    + "ORDER BY pr.id DESC;";
            
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_produtos = (DefaultTableModel) tabela_produtos1.getModel();

            // Limpa as linhas antigas da tabela 
            tb_produtos.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("id"),
                    conecta.rs.getString("Nome"),
                    conecta.rs.getString("Faixa Etária"),
                    conecta.rs.getString("Preço"),
                    conecta.rs.getString("Lucro Estipulado (50%)"),
                    conecta.rs.getString("Demanda"),
                    conecta.rs.getString("Estoque"),
                    conecta.rs.getString("Status Estoque"),
                    conecta.rs.getString("% Cartão"),
                    conecta.rs.getString("% Pix"),
                    conecta.rs.getString("% Outros")
                };
                tb_produtos.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public void Relatório_Vendas() {
        try {
            // Conectar ao banco 
            conecta.conexao();

            // Consulta SQL
            String sql = "SELECT \n"
                    + "    v.pedido AS \"Pedido\",\n"
                    + "    v.data_venda AS \"Data\",\n"
                    + "    v.produto AS \"Produto\",\n"
                    + "    v.quantidade AS \"Qtd.\",\n"
                    + "    ROUND(v.preco * v.quantidade, 2) AS \"Valor Bruto\",\n"
                    + "    CONCAT(v.desconto, '%') AS \"Desconto\",\n"
                    + "    ROUND(v.preco * v.quantidade * (1 - v.desconto/100), 2) AS \"Valor Líquido\",\n"
                    + "    v.modo AS \"Local de Retirada\",\n"
                    + "    v.pagamento AS \"Método\",\n"
                    + "    CASE \n"
                    + "        WHEN v.cep LIKE '0%' OR v.cep LIKE '1%' OR v.cep LIKE '2%' THEN 'Sudeste'\n"
                    + "        WHEN v.cep LIKE '3%' THEN 'Nordeste'\n"
                    + "        WHEN v.cep LIKE '4%' OR v.cep LIKE '5%' THEN 'Sul'\n"
                    + "        WHEN v.cep LIKE '6%' OR v.cep LIKE '7%' THEN 'Centro-Oeste'\n"
                    + "        ELSE 'Norte'\n"
                    + "    END AS \"Região\",\n"
                    + "    CONCAT(\n"
                    + "        (SELECT COUNT(*) FROM vendas v2 WHERE v2.email = v.email OR v2.cpf = v.cpf),\n"
                    + "        ' compras'\n"
                    + "    ) AS \"Frequência de Compra\"\n"
                    + "FROM vendas v\n"
                    + "ORDER BY STR_TO_DATE(v.data_venda, '%d/%m/%Y') DESC;";
            
            conecta.stm = conecta.conn.prepareStatement(sql);
            conecta.rs = conecta.stm.executeQuery(sql);

            // Obter o modelo da tabela
            DefaultTableModel tb_vendas = (DefaultTableModel) tabela_vendas1.getModel();

            // Limpa as linhas antigas da tabela 
            tb_vendas.setRowCount(0);

            // Percorrer os resultados e adicionar ao JTable
            while (conecta.rs.next()) {
                Object[] linha = {
                    conecta.rs.getString("Pedido"),
                    conecta.rs.getString("Data"),
                    conecta.rs.getString("Produto"),
                    conecta.rs.getString("Qtd."),
                    conecta.rs.getString("Valor Bruto"),
                    conecta.rs.getString("Desconto"),
                    conecta.rs.getString("Valor Líquido"),
                    conecta.rs.getString("Local de Retirada"),
                    conecta.rs.getString("Método"),
                    conecta.rs.getString("Região"),
                    conecta.rs.getString("Frequência de Compra")
                };
                tb_vendas.addRow(linha);
            }
            
            conecta.rs.close();
            conecta.stm.close();
            conecta.conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    public class GeradorPDF {
        
        ConectaBanco conecta = new ConectaBanco();
        
        public void gerarPDF_Jovens() {
            Document document = new Document(PageSize.A4.rotate());
            File selectedFile = null;
            
            try {
                // Configura diálogo para salvar
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Salvar Relatório de Jovens");
                chooser.setSelectedFile(new File("Relatório_Jovens.pdf"));
                
                if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                
                selectedFile = chooser.getSelectedFile();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Fonte base para cálculos
                BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font cellFont = new com.itextpdf.text.Font(baseFont, 10);
                com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(baseFont, 8, com.itextpdf.text.Font.ITALIC);

                // Adiciona título
                Paragraph title = new Paragraph("Relatório de Jovens", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Cria tabela
                PdfPTable tabela = new PdfPTable(11);
                tabela.setWidthPercentage(100);
                tabela.setSpacingBefore(10f);
                tabela.setSpacingAfter(10f);

                // Cabeçalhos das colunas
                String[] colunas = {
                    "Matrícula", "Status", "Nome", "Idade", "Curso",
                    "Frequência", "Nota Média", "Contato",
                    "Data Ingresso", "Última Atividade", "Situação Financeira"
                };

                // CALCULAR LARGURAS PARA OS TÍTULOS DAS COLUNAS
                float[] titleWidths = new float[11];
                for (int i = 0; i < colunas.length; i++) {
                    titleWidths[i] = baseFont.getWidthPoint(colunas[i], 10) + 20; // +20 para padding
                }

                // CALCULAR LARGURAS PARA O CONTEÚDO
                conecta.conexao();
                String sql = "SELECT j.matricula, j.status, j.nome_completo, "
                        + "TIMESTAMPDIFF(YEAR, STR_TO_DATE(j.data_nascimento, '%d/%m/%Y'), CURDATE()), "
                        + "j.curso, "
                        + "CASE WHEN j.observacoes LIKE '%dedicado%' OR j.observacoes LIKE '%destaque%' THEN '90%' "
                        + "WHEN j.observacoes LIKE '%monitor%' THEN '95%' ELSE '85%' END, "
                        + "CASE WHEN j.observacoes LIKE '%destaque%' THEN '9.0' "
                        + "WHEN j.observacoes LIKE '%monitor%' THEN '8.8' ELSE '8.0' END, "
                        + "CASE WHEN j.email IS NOT NULL THEN 'Email' ELSE 'Não informado' END, "
                        + "j.data_ingresso, "
                        + "DATE_FORMAT(DATE_ADD(STR_TO_DATE(j.data_ingresso, '%d/%m/%Y'), INTERVAL 6 MONTH), '%d/%m/%Y'), "
                        + "CASE WHEN j.observacoes LIKE '%bolsista%' THEN 'Bolsa' "
                        + "WHEN j.observacoes LIKE '%pendente%' THEN 'Pendente' ELSE 'Regular' END "
                        + "FROM jovens j WHERE j.status = 'Ativo' ORDER BY j.matricula";
                
                conecta.stm = conecta.conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                conecta.rs = conecta.stm.executeQuery(sql);
                
                float[] contentWidths = new float[11];
                Arrays.fill(contentWidths, 0);
                
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        float width = baseFont.getWidthPoint(content, 10) + 10;
                        if (width > contentWidths[i - 1]) {
                            contentWidths[i - 1] = width;
                        }
                    }
                }

                // COMBINAR LARGURAS (pegar o maior valor entre título e conteúdo)
                float[] finalWidths = new float[11];
                float totalWidth = 0;
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = Math.max(titleWidths[i], contentWidths[i]);
                    totalWidth += finalWidths[i];
                }

                // NORMALIZAR PARA PERCENTUAL (100% da tabela)
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = (finalWidths[i] / totalWidth) * 100;
                }
                
                tabela.setWidths(finalWidths);

// ADICIONAR CABEÇALHOS COM LARGURAS AJUSTADAS
                for (int i = 0; i < colunas.length; i++) {
                    PdfPCell header = new PdfPCell(new Phrase(colunas[i], headerFont));
                    header.setBackgroundColor(new BaseColor(5, 24, 64));  // Cor de fundo azul escuro
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.setMinimumHeight(25);
                    header.setBorderWidth(1.5f);

                    // Adicione esta linha para definir a cor do texto como branco
                    header.setPhrase(new Phrase(colunas[i], new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD, BaseColor.WHITE)));
                    
                    tabela.addCell(header);
                }

                // PREENCHER TABELA
                conecta.rs.beforeFirst();
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        PdfPCell cell = new PdfPCell(new Phrase(content, cellFont));
                        cell.setPadding(5);
                        cell.setBorderWidth(0.5f);
                        
                        if (i == 4 || i == 6 || i == 7) {
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        } else {
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
                        
                        tabela.addCell(cell);
                    }
                }
                
                document.add(tabela);

                // Rodapé
                Paragraph footer = new Paragraph(
                        "Relatório gerado em: " + new java.util.Date(),
                        footerFont);
                footer.setAlignment(Element.ALIGN_RIGHT);
                footer.setSpacingBefore(20);
                document.add(footer);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao gerar PDF: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                document.close();
                try {
                    if (conecta.rs != null) {
                        conecta.rs.close();
                    }
                    if (conecta.stm != null) {
                        conecta.stm.close();
                    }
                    if (conecta.conn != null) {
                        conecta.conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
                
                if (selectedFile != null) {
                    try {
                        Desktop.getDesktop().open(selectedFile);
                    } catch (IOException e) {
                        System.out.println("Erro ao abrir PDF: " + e.getMessage());
                        JOptionPane.showMessageDialog(null,
                                "PDF gerado com sucesso em: " + selectedFile.getAbsolutePath(),
                                "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        
        public void gerarPDF_Professores() {
            Document document = new Document(PageSize.A4.rotate());
            File selectedFile = null;
            
            try {
                // Configura diálogo para salvar
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Salvar Relatório de Professores");
                chooser.setSelectedFile(new File("Relatório_Professores.pdf"));
                
                if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                
                selectedFile = chooser.getSelectedFile();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Fonte base para cálculos
                BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font cellFont = new com.itextpdf.text.Font(baseFont, 10);
                com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(baseFont, 8, com.itextpdf.text.Font.ITALIC);

                // Adiciona título
                Paragraph title = new Paragraph("Relatório de Professores", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Cria tabela
                PdfPTable tabela = new PdfPTable(11);
                tabela.setWidthPercentage(100);
                tabela.setSpacingBefore(10f);
                tabela.setSpacingAfter(10f);

                // Cabeçalhos das colunas
                String[] colunas = {
                    "ID", "Status", "Nome", "Formação", "Curso",
                    "Horário", "Disponibilidade", "Início no Curso",
                    "Contrato", "Tempo de Contrato", "Avaliação Alunos"
                };

                // CALCULAR LARGURAS PARA OS TÍTULOS DAS COLUNAS
                float[] titleWidths = new float[11];
                for (int i = 0; i < colunas.length; i++) {
                    titleWidths[i] = baseFont.getWidthPoint(colunas[i], 10) + 20; // +20 para padding
                }

                // CALCULAR LARGURAS PARA O CONTEÚDO
                conecta.conexao();
                String sql = "SELECT \n"
                        + "    p.id AS \"ID\",\n"
                        + "    p.status AS \"Status\",\n"
                        + "    p.nome AS \"Nome\",\n"
                        + "    p.formacao AS \"Formação\",\n"
                        + "    p.curso AS \"Curso\",\n"
                        + "    p.horario AS \"Horário\",\n"
                        + "    CASE \n"
                        + "        WHEN p.horario = 'Integral' THEN 'Integral'\n"
                        + "        ELSE 'Parcial'\n"
                        + "    END AS \"Disponibilidade\",\n"
                        + "    DATE_FORMAT(STR_TO_DATE(MIN(j.data_ingresso), '%d/%m/%Y'), '%d/%m/%Y') AS \"Início no Curso\",\n"
                        + "    CASE \n"
                        + "        WHEN p.observacoes LIKE '%sênior%' OR p.atuacao LIKE '%mestrado%' THEN 'CLT'\n"
                        + "        WHEN p.observacoes LIKE '%consultor%' THEN 'PJ'\n"
                        + "        ELSE 'Temporário'\n"
                        + "    END AS \"Contrato\",\n"
                        + "    CONCAT(\n"
                        + "        TIMESTAMPDIFF(MONTH, \n"
                        + "            STR_TO_DATE(MIN(j.data_ingresso), '%d/%m/%Y'), \n"
                        + "            CURDATE()\n"
                        + "        ), \n"
                        + "        ' meses'\n"
                        + "    ) AS \"Tempo de Contrato\",\n"
                        + "    CASE \n"
                        + "        WHEN p.observacoes LIKE '%excelente%' THEN '4.9/5'\n"
                        + "        WHEN p.observacoes LIKE '%destac%' THEN '4.7/5'\n"
                        + "        ELSE '4.5/5'\n"
                        + "    END AS \"Avaliação Alunos\"\n"
                        + "FROM professores p\n"
                        + "LEFT JOIN jovens j ON j.curso = p.curso\n"
                        + "WHERE p.status = 'Ativo'\n"
                        + "GROUP BY p.id, p.nome, p.curso, p.status, p.horario, p.formacao, p.observacoes, p.atuacao\n"
                        + "ORDER BY p.id;";
                
                conecta.stm = conecta.conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                conecta.rs = conecta.stm.executeQuery(sql);
                
                float[] contentWidths = new float[11];
                Arrays.fill(contentWidths, 0);
                
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        float width = baseFont.getWidthPoint(content, 10) + 10;
                        if (width > contentWidths[i - 1]) {
                            contentWidths[i - 1] = width;
                        }
                    }
                }

                // COMBINAR LARGURAS (pegar o maior valor entre título e conteúdo)
                float[] finalWidths = new float[11];
                float totalWidth = 0;
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = Math.max(titleWidths[i], contentWidths[i]);
                    totalWidth += finalWidths[i];
                }

                // NORMALIZAR PARA PERCENTUAL (100% da tabela)
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = (finalWidths[i] / totalWidth) * 100;
                }
                
                tabela.setWidths(finalWidths);

                // ADICIONAR CABEÇALHOS COM LARGURAS AJUSTADAS
                for (int i = 0; i < colunas.length; i++) {
                    PdfPCell header = new PdfPCell(new Phrase(colunas[i], headerFont));
                    header.setBackgroundColor(new BaseColor(5, 24, 64));  // Cor de fundo azul escuro
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.setMinimumHeight(25);
                    header.setBorderWidth(1.5f);

                    // Adicione esta linha para definir a cor do texto como branco
                    header.setPhrase(new Phrase(colunas[i], new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD, BaseColor.WHITE)));
                    
                    tabela.addCell(header);
                }

                // PREENCHER TABELA
                conecta.rs.beforeFirst();
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        PdfPCell cell = new PdfPCell(new Phrase(content, cellFont));
                        cell.setPadding(5);
                        cell.setBorderWidth(0.5f);
                        
                        if (i == 4 || i == 6 || i == 7) {
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        } else {
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
                        
                        tabela.addCell(cell);
                    }
                }
                
                document.add(tabela);

                // Rodapé
                Paragraph footer = new Paragraph(
                        "Relatório gerado em: " + new java.util.Date(),
                        footerFont);
                footer.setAlignment(Element.ALIGN_RIGHT);
                footer.setSpacingBefore(20);
                document.add(footer);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao gerar PDF: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                document.close();
                try {
                    if (conecta.rs != null) {
                        conecta.rs.close();
                    }
                    if (conecta.stm != null) {
                        conecta.stm.close();
                    }
                    if (conecta.conn != null) {
                        conecta.conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
                
                if (selectedFile != null) {
                    try {
                        Desktop.getDesktop().open(selectedFile);
                    } catch (IOException e) {
                        System.out.println("Erro ao abrir PDF: " + e.getMessage());
                        JOptionPane.showMessageDialog(null,
                                "PDF gerado com sucesso em: " + selectedFile.getAbsolutePath(),
                                "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        
        public void gerarPDF_Cursos() {
            Document document = new Document(PageSize.A4.rotate());
            File selectedFile = null;
            
            try {
                // Configura diálogo para salvar
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Salvar Relatório de Cursos");
                chooser.setSelectedFile(new File("Relatório_Cursos.pdf"));
                
                if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                
                selectedFile = chooser.getSelectedFile();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Fonte base para cálculos
                BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font cellFont = new com.itextpdf.text.Font(baseFont, 10);
                com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(baseFont, 8, com.itextpdf.text.Font.ITALIC);

                // Adiciona título
                Paragraph title = new Paragraph("Relatório de Cursos", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Cria tabela
                PdfPTable tabela = new PdfPTable(11);
                tabela.setWidthPercentage(100);
                tabela.setSpacingBefore(10f);
                tabela.setSpacingAfter(10f);

                // Cabeçalhos das colunas
                String[] colunas = {
                    "ID", "Status", "Nome", "Modalidade", "Nível",
                    "Duração (meses)", "Vagas", "Matrículas",
                    "Taxa de Conclusão", "Taxa de Empregabilidade", "Investimento"
                };

                // CALCULAR LARGURAS PARA OS TÍTULOS DAS COLUNAS
                float[] titleWidths = new float[11];
                for (int i = 0; i < colunas.length; i++) {
                    titleWidths[i] = baseFont.getWidthPoint(colunas[i], 10) + 20; // +20 para padding
                }

                // CALCULAR LARGURAS PARA O CONTEÚDO
                conecta.conexao();
                String sql = "SELECT \n"
                        + "    c.id AS \"ID\",\n"
                        + "    c.status AS \"Status\",\n"
                        + "    c.nome AS \"Nome\",\n"
                        + "    c.modalidade AS \"Modalidade\",\n"
                        + "    CASE \n"
                        + "        WHEN c.nome LIKE '%Básica%' THEN 'Iniciante'\n"
                        + "        WHEN c.nome LIKE '%Avançada%' THEN 'Avançado'\n"
                        + "        ELSE 'Intermediário'\n"
                        + "    END AS \"Nível\",\n"
                        + "    CASE \n"
                        + "        WHEN c.nome LIKE '%semestral%' THEN 6\n"
                        + "        WHEN c.nome LIKE '%anual%' THEN 12\n"
                        + "        WHEN c.nome LIKE '%Básica%' THEN 6\n"
                        + "        WHEN c.nome LIKE '%Avançada%' THEN 8\n"
                        + "        ELSE 4\n"
                        + "    END AS \"Duração (meses)\",\n"
                        + "    25 AS \"Vagas\",  -- Quantidade fixa de vagas definida como 25\n"
                        + "    (SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Ativo') AS \"Matrículas\",\n"
                        + "    CASE\n"
                        + "        WHEN (SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Inativo') > 0 \n"
                        + "        THEN CONCAT(ROUND((SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Ativo') * 100.0 / \n"
                        + "                   ((SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Ativo') + \n"
                        + "                    (SELECT COUNT(*) FROM jovens j WHERE j.curso = c.nome AND j.status = 'Inativo')), 0), '%')\n"
                        + "        ELSE '100%'\n"
                        + "    END AS \"Taxa Conclusão\",\n"
                        + "    CASE\n"
                        + "        WHEN c.nome LIKE '%Programação%' THEN '85%'\n"
                        + "        WHEN c.nome LIKE '%Digital%' THEN '80%'\n"
                        + "        WHEN c.nome LIKE '%Básica%' THEN '70%'\n"
                        + "        WHEN c.nome LIKE '%Avançada%' THEN '75%'\n"
                        + "        ELSE '72%'\n"
                        + "    END AS \"Taxa de Empregabilidade\",\n"
                        + "    CASE \n"
                        + "        WHEN c.modalidade = 'Online' THEN 'R$900'\n"
                        + "        WHEN c.modalidade = 'Presencial' THEN 'R$1.500'\n"
                        + "        ELSE 'R$1.200'\n"
                        + "    END AS \"Investimento\"\n"
                        + "FROM cursos c\n"
                        + "WHERE c.status = 'Ativo'\n"
                        + "ORDER BY c.id;";
                
                conecta.stm = conecta.conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                conecta.rs = conecta.stm.executeQuery(sql);
                
                float[] contentWidths = new float[11];
                Arrays.fill(contentWidths, 0);
                
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        float width = baseFont.getWidthPoint(content, 10) + 10;
                        if (width > contentWidths[i - 1]) {
                            contentWidths[i - 1] = width;
                        }
                    }
                }

                // COMBINAR LARGURAS (pegar o maior valor entre título e conteúdo)
                float[] finalWidths = new float[11];
                float totalWidth = 0;
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = Math.max(titleWidths[i], contentWidths[i]);
                    totalWidth += finalWidths[i];
                }

                // NORMALIZAR PARA PERCENTUAL (100% da tabela)
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = (finalWidths[i] / totalWidth) * 100;
                }
                
                tabela.setWidths(finalWidths);

                // ADICIONAR CABEÇALHOS COM LARGURAS AJUSTADAS
                for (int i = 0; i < colunas.length; i++) {
                    PdfPCell header = new PdfPCell(new Phrase(colunas[i], headerFont));
                    header.setBackgroundColor(new BaseColor(5, 24, 64));  // Cor de fundo azul escuro
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.setMinimumHeight(25);
                    header.setBorderWidth(1.5f);

                    // Adicione esta linha para definir a cor do texto como branco
                    header.setPhrase(new Phrase(colunas[i], new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD, BaseColor.WHITE)));
                    
                    tabela.addCell(header);
                }

                // PREENCHER TABELA
                conecta.rs.beforeFirst();
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        PdfPCell cell = new PdfPCell(new Phrase(content, cellFont));
                        cell.setPadding(5);
                        cell.setBorderWidth(0.5f);
                        
                        if (i == 4 || i == 6 || i == 7) {
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        } else {
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
                        
                        tabela.addCell(cell);
                    }
                }
                
                document.add(tabela);

                // Rodapé
                Paragraph footer = new Paragraph(
                        "Relatório gerado em: " + new java.util.Date(),
                        footerFont);
                footer.setAlignment(Element.ALIGN_RIGHT);
                footer.setSpacingBefore(20);
                document.add(footer);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao gerar PDF: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                document.close();
                try {
                    if (conecta.rs != null) {
                        conecta.rs.close();
                    }
                    if (conecta.stm != null) {
                        conecta.stm.close();
                    }
                    if (conecta.conn != null) {
                        conecta.conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
                
                if (selectedFile != null) {
                    try {
                        Desktop.getDesktop().open(selectedFile);
                    } catch (IOException e) {
                        System.out.println("Erro ao abrir PDF: " + e.getMessage());
                        JOptionPane.showMessageDialog(null,
                                "PDF gerado com sucesso em: " + selectedFile.getAbsolutePath(),
                                "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        
        public void gerarPDF_Produtos() {
            Document document = new Document(PageSize.A4.rotate());
            File selectedFile = null;
            
            try {
                // Configura diálogo para salvar
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Salvar Relatório de Produtos");
                chooser.setSelectedFile(new File("Relatório_Produtos.pdf"));
                
                if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                
                selectedFile = chooser.getSelectedFile();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Fonte base para cálculos
                BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font cellFont = new com.itextpdf.text.Font(baseFont, 10);
                com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(baseFont, 8, com.itextpdf.text.Font.ITALIC);

                // Adiciona título
                Paragraph title = new Paragraph("Relatório de Produtos", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Cria tabela
                PdfPTable tabela = new PdfPTable(11);
                tabela.setWidthPercentage(100);
                tabela.setSpacingBefore(10f);
                tabela.setSpacingAfter(10f);

                // Cabeçalhos das colunas
                String[] colunas = {
                    "ID", "Nome", "Faixa Etária", "Preço", "Lucro Estipulado (50%)",
                    "Demanda", "Estoque", "Status Estoque",
                    "% Cartão", "% Pix", "% Outros"
                };

                // CALCULAR LARGURAS PARA OS TÍTULOS DAS COLUNAS
                float[] titleWidths = new float[11];
                for (int i = 0; i < colunas.length; i++) {
                    titleWidths[i] = baseFont.getWidthPoint(colunas[i], 10) + 20; // +20 para padding
                }

                // CALCULAR LARGURAS PARA O CONTEÚDO
                conecta.conexao();
                String sql = "SELECT \n"
                        + "    pr.id AS \"ID\",\n"
                        + "    pr.nome AS \"Nome\",\n"
                        + "    pr.faixa_etaria AS \"Faixa Etária\",\n"
                        + "    pr.preco AS \"Preço\",\n"
                        + "    ROUND(pr.preco * 0.5, 2) AS \"Lucro Estipulado (50%)\",\n"
                        + "    CASE \n"
                        + "        WHEN (SELECT SUM(v.quantidade) FROM vendas v WHERE v.produto LIKE CONCAT('%', pr.nome, '%')) > 50 THEN 'Alta'\n"
                        + "        WHEN (SELECT SUM(v.quantidade) FROM vendas v WHERE v.produto LIKE CONCAT('%', pr.nome, '%')) > 20 THEN 'Média'\n"
                        + "        ELSE 'Baixa'\n"
                        + "    END AS \"Demanda\",\n"
                        + "    pr.estoque AS \"Estoque\",\n"
                        + "    CASE \n"
                        + "        WHEN pr.estoque < 20 THEN 'Repor'\n"
                        + "        WHEN pr.estoque > 70 THEN 'Estoque OK'\n"
                        + "        ELSE 'Monitorar'\n"
                        + "    END AS \"Status Estoque\",\n"
                        + "    (SELECT CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vendas WHERE pagamento LIKE 'Crédito%'), 1), '%') \n"
                        + "     FROM vendas v \n"
                        + "     WHERE v.produto LIKE CONCAT('%', pr.nome, '%') AND v.pagamento LIKE 'Crédito%') AS \"% Cartão\",\n"
                        + "    (SELECT CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vendas WHERE pagamento = 'Pix'), 1), '%') \n"
                        + "     FROM vendas v \n"
                        + "     WHERE v.produto LIKE CONCAT('%', pr.nome, '%') AND v.pagamento = 'Pix') AS \"% Pix\",\n"
                        + "    (SELECT CONCAT(ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM vendas WHERE pagamento NOT LIKE 'Crédito%' AND pagamento != 'Pix'), 1), '%') \n"
                        + "     FROM vendas v \n"
                        + "     WHERE v.produto LIKE CONCAT('%', pr.nome, '%') AND v.pagamento NOT LIKE 'Crédito%' AND v.pagamento != 'Pix') AS \"% Outros\"\n"
                        + "FROM produtos pr\n"
                        + "WHERE pr.status = 'Ativo'\n"
                        + "ORDER BY pr.id;";
                
                conecta.stm = conecta.conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                conecta.rs = conecta.stm.executeQuery(sql);
                
                float[] contentWidths = new float[11];
                Arrays.fill(contentWidths, 0);
                
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        float width = baseFont.getWidthPoint(content, 10) + 10;
                        if (width > contentWidths[i - 1]) {
                            contentWidths[i - 1] = width;
                        }
                    }
                }

                // COMBINAR LARGURAS (pegar o maior valor entre título e conteúdo)
                float[] finalWidths = new float[11];
                float totalWidth = 0;
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = Math.max(titleWidths[i], contentWidths[i]);
                    totalWidth += finalWidths[i];
                }

                // NORMALIZAR PARA PERCENTUAL (100% da tabela)
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = (finalWidths[i] / totalWidth) * 100;
                }
                
                tabela.setWidths(finalWidths);

                // ADICIONAR CABEÇALHOS COM LARGURAS AJUSTADAS
                for (int i = 0; i < colunas.length; i++) {
                    PdfPCell header = new PdfPCell(new Phrase(colunas[i], headerFont));
                    header.setBackgroundColor(new BaseColor(5, 24, 64));  // Cor de fundo azul escuro
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.setMinimumHeight(25);
                    header.setBorderWidth(1.5f);

                    // Adicione esta linha para definir a cor do texto como branco
                    header.setPhrase(new Phrase(colunas[i], new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD, BaseColor.WHITE)));
                    
                    tabela.addCell(header);
                }

                // PREENCHER TABELA
                conecta.rs.beforeFirst();
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        PdfPCell cell = new PdfPCell(new Phrase(content, cellFont));
                        cell.setPadding(5);
                        cell.setBorderWidth(0.5f);
                        
                        if (i == 4 || i == 6 || i == 7) {
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        } else {
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
                        
                        tabela.addCell(cell);
                    }
                }
                
                document.add(tabela);

                // Rodapé
                Paragraph footer = new Paragraph(
                        "Relatório gerado em: " + new java.util.Date(),
                        footerFont);
                footer.setAlignment(Element.ALIGN_RIGHT);
                footer.setSpacingBefore(20);
                document.add(footer);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao gerar PDF: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                document.close();
                try {
                    if (conecta.rs != null) {
                        conecta.rs.close();
                    }
                    if (conecta.stm != null) {
                        conecta.stm.close();
                    }
                    if (conecta.conn != null) {
                        conecta.conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
                
                if (selectedFile != null) {
                    try {
                        Desktop.getDesktop().open(selectedFile);
                    } catch (IOException e) {
                        System.out.println("Erro ao abrir PDF: " + e.getMessage());
                        JOptionPane.showMessageDialog(null,
                                "PDF gerado com sucesso em: " + selectedFile.getAbsolutePath(),
                                "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        
        public void gerarPDF_Vendas() {
            Document document = new Document(PageSize.A4.rotate());
            File selectedFile = null;
            
            try {
                // Configura diálogo para salvar
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Salvar Relatório de Vendas");
                chooser.setSelectedFile(new File("Relatório_Vendas.pdf"));
                
                if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                
                selectedFile = chooser.getSelectedFile();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Fonte base para cálculos
                BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font cellFont = new com.itextpdf.text.Font(baseFont, 10);
                com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(baseFont, 8, com.itextpdf.text.Font.ITALIC);

                // Adiciona título
                Paragraph title = new Paragraph("Relatório de Vendas", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Cria tabela
                PdfPTable tabela = new PdfPTable(11);
                tabela.setWidthPercentage(100);
                tabela.setSpacingBefore(10f);
                tabela.setSpacingAfter(10f);

                // Cabeçalhos das colunas
                String[] colunas = {
                    "Pedido", "Data", "Produto", "Qtd.", "Valor Bruto",
                    "Desconto", "Valor Líquido", "Local de Retirada",
                    "Método", "Região", "Frequência de Compra"
                };

                // CALCULAR LARGURAS PARA OS TÍTULOS DAS COLUNAS
                float[] titleWidths = new float[11];
                for (int i = 0; i < colunas.length; i++) {
                    titleWidths[i] = baseFont.getWidthPoint(colunas[i], 10) + 20; // +20 para padding
                }

                // CALCULAR LARGURAS PARA O CONTEÚDO
                conecta.conexao();
                String sql = "SELECT \n"
                        + "    v.pedido AS \"Pedido\",\n"
                        + "    v.data_venda AS \"Data\",\n"
                        + "    v.produto AS \"Produto\",\n"
                        + "    v.quantidade AS \"Qtd.\",\n"
                        + "    ROUND(v.preco * v.quantidade, 2) AS \"Valor Bruto\",\n"
                        + "    CONCAT(v.desconto, '%') AS \"Desconto\",\n"
                        + "    ROUND(v.preco * v.quantidade * (1 - v.desconto/100), 2) AS \"Valor Líquido\",\n"
                        + "    v.modo AS \"Local de Retirada\",\n"
                        + "    v.pagamento AS \"Método\",\n"
                        + "    CASE \n"
                        + "        WHEN v.cep LIKE '0%' OR v.cep LIKE '1%' OR v.cep LIKE '2%' THEN 'Sudeste'\n"
                        + "        WHEN v.cep LIKE '3%' THEN 'Nordeste'\n"
                        + "        WHEN v.cep LIKE '4%' OR v.cep LIKE '5%' THEN 'Sul'\n"
                        + "        WHEN v.cep LIKE '6%' OR v.cep LIKE '7%' THEN 'Centro-Oeste'\n"
                        + "        ELSE 'Norte'\n"
                        + "    END AS \"Região\",\n"
                        + "    CONCAT(\n"
                        + "        (SELECT COUNT(*) FROM vendas v2 WHERE v2.email = v.email OR v2.cpf = v.cpf),\n"
                        + "        ' compras'\n"
                        + "    ) AS \"Frequência de Compra\"\n"
                        + "FROM vendas v\n"
                        + "ORDER BY v.pedido;";
                
                conecta.stm = conecta.conn.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                conecta.rs = conecta.stm.executeQuery(sql);
                
                float[] contentWidths = new float[11];
                Arrays.fill(contentWidths, 0);
                
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        float width = baseFont.getWidthPoint(content, 10) + 10;
                        if (width > contentWidths[i - 1]) {
                            contentWidths[i - 1] = width;
                        }
                    }
                }

                // COMBINAR LARGURAS (pegar o maior valor entre título e conteúdo)
                float[] finalWidths = new float[11];
                float totalWidth = 0;
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = Math.max(titleWidths[i], contentWidths[i]);
                    totalWidth += finalWidths[i];
                }

                // NORMALIZAR PARA PERCENTUAL (100% da tabela)
                for (int i = 0; i < 11; i++) {
                    finalWidths[i] = (finalWidths[i] / totalWidth) * 100;
                }
                
                tabela.setWidths(finalWidths);

                // ADICIONAR CABEÇALHOS COM LARGURAS AJUSTADAS
                for (int i = 0; i < colunas.length; i++) {
                    PdfPCell header = new PdfPCell(new Phrase(colunas[i], headerFont));
                    header.setBackgroundColor(new BaseColor(5, 24, 64));  // Cor de fundo azul escuro
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.setMinimumHeight(25);
                    header.setBorderWidth(1.5f);

                    // Adicione esta linha para definir a cor do texto como branco
                    header.setPhrase(new Phrase(colunas[i], new com.itextpdf.text.Font(baseFont, 10, com.itextpdf.text.Font.BOLD, BaseColor.WHITE)));
                    
                    tabela.addCell(header);
                }

                // PREENCHER TABELA
                conecta.rs.beforeFirst();
                while (conecta.rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        String content = conecta.rs.getString(i) != null ? conecta.rs.getString(i) : "";
                        PdfPCell cell = new PdfPCell(new Phrase(content, cellFont));
                        cell.setPadding(5);
                        cell.setBorderWidth(0.5f);
                        
                        if (i == 4 || i == 6 || i == 7) {
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        } else {
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
                        
                        tabela.addCell(cell);
                    }
                }
                
                document.add(tabela);

                // Rodapé
                Paragraph footer = new Paragraph(
                        "Relatório gerado em: " + new java.util.Date(),
                        footerFont);
                footer.setAlignment(Element.ALIGN_RIGHT);
                footer.setSpacingBefore(20);
                document.add(footer);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao gerar PDF: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                document.close();
                try {
                    if (conecta.rs != null) {
                        conecta.rs.close();
                    }
                    if (conecta.stm != null) {
                        conecta.stm.close();
                    }
                    if (conecta.conn != null) {
                        conecta.conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
                
                if (selectedFile != null) {
                    try {
                        Desktop.getDesktop().open(selectedFile);
                    } catch (IOException e) {
                        System.out.println("Erro ao abrir PDF: " + e.getMessage());
                        JOptionPane.showMessageDialog(null,
                                "PDF gerado com sucesso em: " + selectedFile.getAbsolutePath(),
                                "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Main = new javax.swing.JPanel();
        Painel_0_Lateral = new javax.swing.JPanel();
        foto_user = new javax.swing.JLabel();
        aciona_jovens = new javax.swing.JButton();
        aciona_inicio2 = new javax.swing.JButton();
        aciona_professores = new javax.swing.JButton();
        aciona_cursos = new javax.swing.JButton();
        aciona_produtos = new javax.swing.JButton();
        aciona_vendas = new javax.swing.JButton();
        aciona_financeiro = new javax.swing.JButton();
        aciona_relatorios = new javax.swing.JButton();
        Painel_1_Menu = new javax.swing.JPanel();
        txt_grafico = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_minimiza2 = new javax.swing.JButton();
        btn_sair2 = new javax.swing.JButton();
        Painel_2_Jovens = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_observacoes = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btn_alterar = new javax.swing.JButton();
        btn_cadastrar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txt_curso = new javax.swing.JComboBox<>();
        txt_genero = new javax.swing.JComboBox<>();
        txt_status = new javax.swing.JComboBox<>();
        txt_matricula = new javax.swing.JTextField();
        txt_rg = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_jovens = new javax.swing.JTable();
        txt_nascimento = new javax.swing.JFormattedTextField();
        txt_data = new javax.swing.JFormattedTextField();
        txt_cpf = new javax.swing.JFormattedTextField();
        txt_responsavel = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txt_total = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_minimiza3 = new javax.swing.JButton();
        btn_sair3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Painel_3_Professores = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txt_id = new javax.swing.JTextField();
        txt_atuacao = new javax.swing.JTextField();
        txt_formacao = new javax.swing.JTextField();
        txt_horario = new javax.swing.JComboBox<>();
        txt_curso1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_professores = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_total1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txt_status1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txt_nome1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txt_email1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_observacao = new javax.swing.JTextField();
        txt_cpf1 = new javax.swing.JFormattedTextField();
        jLabel37 = new javax.swing.JLabel();
        txt_genero1 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        txt_nascimento1 = new javax.swing.JFormattedTextField();
        jLabel39 = new javax.swing.JLabel();
        btn_cadastrar1 = new javax.swing.JButton();
        btn_alterar1 = new javax.swing.JButton();
        btn_excluir1 = new javax.swing.JButton();
        btn_minimiza4 = new javax.swing.JButton();
        btn_sair4 = new javax.swing.JButton();
        Painel_4_Cursos = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txt_nome2 = new javax.swing.JTextField();
        txt_id1 = new javax.swing.JTextField();
        txt_professor11 = new javax.swing.JComboBox<>();
        txt_modalidade = new javax.swing.JComboBox<>();
        txt_status2 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela_cursos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_descricao = new javax.swing.JTextPane();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_total2 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btn_excluir2 = new javax.swing.JButton();
        btn_login3 = new javax.swing.JButton();
        btn_alterar2 = new javax.swing.JButton();
        btn_minimiza5 = new javax.swing.JButton();
        btn_sair5 = new javax.swing.JButton();
        Painel_5_Produtos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txt_id2 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txt_etaria = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txt_custo = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_materiais = new javax.swing.JList<>();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txt_estoque = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txt_preco = new javax.swing.JTextField();
        txt_producao = new javax.swing.JFormattedTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabela_produtos = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txt_nome3 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txt_total3 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        btn_login4 = new javax.swing.JButton();
        btn_alterar3 = new javax.swing.JButton();
        btn_excluir3 = new javax.swing.JButton();
        btn_minimiza6 = new javax.swing.JButton();
        btn_sair6 = new javax.swing.JButton();
        txt_tipo = new javax.swing.JComboBox<>();
        txt_status3 = new javax.swing.JComboBox<>();
        Painel_6_Vendas = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txt_nome4 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txt_email2 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txt_pedido = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txt_rua = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txt_bairro = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txt_numero = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txt_cidade = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        txt_preco1 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        txt_produto = new javax.swing.JComboBox<>();
        txt_data1 = new javax.swing.JFormattedTextField();
        txt_hora = new javax.swing.JFormattedTextField();
        txt_cpf2 = new javax.swing.JFormattedTextField();
        txt_telefone = new javax.swing.JFormattedTextField();
        txt_cep = new javax.swing.JFormattedTextField();
        btn_login5 = new javax.swing.JButton();
        txt_prazo = new javax.swing.JFormattedTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabela_vendas = new javax.swing.JTable();
        txt_modo = new javax.swing.JComboBox<>();
        txt_pagamento = new javax.swing.JComboBox<>();
        txt_desconto = new javax.swing.JTextField();
        btn_alterar4 = new javax.swing.JButton();
        btn_excluir4 = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txt_total4 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        btn_minimiza7 = new javax.swing.JButton();
        btn_sair7 = new javax.swing.JButton();
        Painel_7_Relatorios = new javax.swing.JPanel();
        btn_PDF = new javax.swing.JButton();
        txt_tipo1 = new javax.swing.JComboBox<>();
        Painel_Vendas = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabela_vendas1 = new javax.swing.JTable();
        Painel_Produtos = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabela_produtos1 = new javax.swing.JTable();
        Painel_Cursos = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabela_cursos1 = new javax.swing.JTable();
        Painel_Professores = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabela_professores1 = new javax.swing.JTable();
        Painel_Jovens = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tabela_jovens1 = new javax.swing.JTable();
        Painel_base = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        btn_minimiza8 = new javax.swing.JButton();
        btn_sair8 = new javax.swing.JButton();
        Painel_8_Financeiro = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        btn_minimiza9 = new javax.swing.JButton();
        btn_sair9 = new javax.swing.JButton();
        txt_tipo2 = new javax.swing.JComboBox<>();
        txt_ong1 = new javax.swing.JLabel();
        txt_ong2 = new javax.swing.JLabel();
        txt_ong3 = new javax.swing.JLabel();
        txt_ong4 = new javax.swing.JLabel();
        txt_vendas1 = new javax.swing.JLabel();
        txt_vendas2 = new javax.swing.JLabel();
        txt_vendas3 = new javax.swing.JLabel();
        txt_vendas4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ONG Jovens Ambientalistas");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_Main.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Main.setForeground(new java.awt.Color(255, 255, 255));
        Panel_Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Painel_0_Lateral.setBackground(new java.awt.Color(0, 106, 215));
        Painel_0_Lateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foto_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Logo_1.png"))); // NOI18N
        Painel_0_Lateral.add(foto_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 170));

        aciona_jovens.setBackground(new java.awt.Color(1, 155, 225));
        aciona_jovens.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_jovens.setForeground(new java.awt.Color(0, 0, 0));
        aciona_jovens.setText("Jovens");
        aciona_jovens.setAlignmentY(0.2F);
        aciona_jovens.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_jovens.setBorderPainted(false);
        aciona_jovens.setContentAreaFilled(false);
        aciona_jovens.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_jovens.setFocusCycleRoot(true);
        aciona_jovens.setHideActionText(true);
        aciona_jovens.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_jovens.setOpaque(true);
        aciona_jovens.setPreferredSize(new java.awt.Dimension(200, 45));
        aciona_jovens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_jovensMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_jovensMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_jovensMouseReleased(evt);
            }
        });
        aciona_jovens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_jovensActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_jovens, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, 45));

        aciona_inicio2.setBackground(new java.awt.Color(5, 24, 64));
        aciona_inicio2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_inicio2.setForeground(new java.awt.Color(255, 255, 255));
        aciona_inicio2.setText("Ínicio");
        aciona_inicio2.setAlignmentY(0.2F);
        aciona_inicio2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_inicio2.setContentAreaFilled(false);
        aciona_inicio2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_inicio2.setFocusCycleRoot(true);
        aciona_inicio2.setHideActionText(true);
        aciona_inicio2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_inicio2.setOpaque(true);
        aciona_inicio2.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_inicio2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_inicio2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_inicio2MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_inicio2MouseReleased(evt);
            }
        });
        aciona_inicio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_inicio2ActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_inicio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 200, 45));

        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_professores.setForeground(new java.awt.Color(0, 0, 0));
        aciona_professores.setText("Professores");
        aciona_professores.setAlignmentY(0.2F);
        aciona_professores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_professores.setContentAreaFilled(false);
        aciona_professores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_professores.setFocusCycleRoot(true);
        aciona_professores.setHideActionText(true);
        aciona_professores.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_professores.setOpaque(true);
        aciona_professores.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_professores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_professoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_professoresMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_professoresMouseReleased(evt);
            }
        });
        aciona_professores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_professoresActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_professores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 200, 45));

        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_cursos.setForeground(new java.awt.Color(0, 0, 0));
        aciona_cursos.setText("Cursos");
        aciona_cursos.setAlignmentY(0.2F);
        aciona_cursos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_cursos.setContentAreaFilled(false);
        aciona_cursos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_cursos.setFocusCycleRoot(true);
        aciona_cursos.setHideActionText(true);
        aciona_cursos.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_cursos.setOpaque(true);
        aciona_cursos.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_cursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_cursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_cursosMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_cursosMouseReleased(evt);
            }
        });
        aciona_cursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_cursosActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_cursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 200, 45));

        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_produtos.setForeground(new java.awt.Color(0, 0, 0));
        aciona_produtos.setText("Produtos");
        aciona_produtos.setAlignmentY(0.2F);
        aciona_produtos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_produtos.setContentAreaFilled(false);
        aciona_produtos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_produtos.setFocusCycleRoot(true);
        aciona_produtos.setHideActionText(true);
        aciona_produtos.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_produtos.setOpaque(true);
        aciona_produtos.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_produtos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_produtosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_produtosMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_produtosMouseReleased(evt);
            }
        });
        aciona_produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_produtosActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_produtos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 200, 45));

        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_vendas.setForeground(new java.awt.Color(0, 0, 0));
        aciona_vendas.setText("Vendas");
        aciona_vendas.setAlignmentY(0.2F);
        aciona_vendas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_vendas.setContentAreaFilled(false);
        aciona_vendas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_vendas.setFocusCycleRoot(true);
        aciona_vendas.setHideActionText(true);
        aciona_vendas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_vendas.setOpaque(true);
        aciona_vendas.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_vendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_vendasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_vendasMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_vendasMouseReleased(evt);
            }
        });
        aciona_vendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_vendasActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_vendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 200, 45));

        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_financeiro.setForeground(new java.awt.Color(0, 0, 0));
        aciona_financeiro.setText("Financeiro");
        aciona_financeiro.setAlignmentY(0.2F);
        aciona_financeiro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_financeiro.setContentAreaFilled(false);
        aciona_financeiro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_financeiro.setFocusCycleRoot(true);
        aciona_financeiro.setHideActionText(true);
        aciona_financeiro.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_financeiro.setOpaque(true);
        aciona_financeiro.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_financeiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_financeiroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_financeiroMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_financeiroMouseReleased(evt);
            }
        });
        aciona_financeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_financeiroActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_financeiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 200, 45));

        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        aciona_relatorios.setForeground(new java.awt.Color(0, 0, 0));
        aciona_relatorios.setText("Relatórios");
        aciona_relatorios.setAlignmentY(0.2F);
        aciona_relatorios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        aciona_relatorios.setContentAreaFilled(false);
        aciona_relatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aciona_relatorios.setFocusCycleRoot(true);
        aciona_relatorios.setHideActionText(true);
        aciona_relatorios.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        aciona_relatorios.setOpaque(true);
        aciona_relatorios.setPreferredSize(new java.awt.Dimension(138, 40));
        aciona_relatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aciona_relatoriosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aciona_relatoriosMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aciona_relatoriosMouseReleased(evt);
            }
        });
        aciona_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aciona_relatoriosActionPerformed(evt);
            }
        });
        Painel_0_Lateral.add(aciona_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 200, 45));

        Panel_Main.add(Painel_0_Lateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 700));

        Painel_1_Menu.setBackground(new java.awt.Color(255, 255, 255));
        Painel_1_Menu.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_1_Menu.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_1_Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_grafico.setFont(new java.awt.Font("Segoe UI", 0, 50)); // NOI18N
        txt_grafico.setForeground(new java.awt.Color(5, 24, 64));
        txt_grafico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_grafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_Menu.png"))); // NOI18N
        txt_grafico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Painel_1_Menu.add(txt_grafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1060, 550));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(5, 24, 64));
        jLabel2.setText("ESTATISTÍCAS ");
        Painel_1_Menu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        btn_minimiza2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza2.setBorderPainted(false);
        btn_minimiza2.setContentAreaFilled(false);
        btn_minimiza2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza2ActionPerformed(evt);
            }
        });
        Painel_1_Menu.add(btn_minimiza2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair2.setBorderPainted(false);
        btn_sair2.setContentAreaFilled(false);
        btn_sair2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair2.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair2.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair2ActionPerformed(evt);
            }
        });
        Painel_1_Menu.add(btn_sair2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        Panel_Main.add(Painel_1_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        Painel_2_Jovens.setBackground(new java.awt.Color(255, 255, 255));
        Painel_2_Jovens.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_2_Jovens.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_2_Jovens.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Painel_2_Jovens.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(5, 24, 64));
        jLabel3.setText("Informações pessoais");
        Painel_2_Jovens.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(5, 24, 64));
        jLabel9.setText("____________________________________________");
        Painel_2_Jovens.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 330, 30));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(5, 24, 64));
        jLabel10.setText("Matricula");
        Painel_2_Jovens.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(5, 24, 64));
        jLabel11.setText("Data ");
        Painel_2_Jovens.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(5, 24, 64));
        jLabel12.setText("Status");
        Painel_2_Jovens.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(5, 24, 64));
        jLabel13.setText("Curso");
        Painel_2_Jovens.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(5, 24, 64));
        jLabel14.setText("Responsável");
        Painel_2_Jovens.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setForeground(new java.awt.Color(5, 24, 64));
        jLabel15.setText("____________________________________________");
        Painel_2_Jovens.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 330, 30));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(5, 24, 64));
        jLabel17.setText("Nome completo");
        Painel_2_Jovens.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        txt_nome.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_nome.setForeground(new java.awt.Color(5, 24, 64));
        txt_nome.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_nome.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 360, 35));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(5, 24, 64));
        jLabel18.setText("Nascimento");
        Painel_2_Jovens.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, -1));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(5, 24, 64));
        jLabel19.setText("Observações");
        Painel_2_Jovens.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        txt_observacoes.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_observacoes.setForeground(new java.awt.Color(5, 24, 64));
        txt_observacoes.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_observacoes.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_observacoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 500, 35));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(5, 24, 64));
        jLabel20.setText("Gênero");
        Painel_2_Jovens.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(5, 24, 64));
        jLabel21.setText("CPF");
        Painel_2_Jovens.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, -1, -1));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(5, 24, 64));
        jLabel22.setText("E-mail");
        Painel_2_Jovens.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        txt_email.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_email.setForeground(new java.awt.Color(5, 24, 64));
        txt_email.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_email.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 210, 35));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(5, 24, 64));
        jLabel23.setText("Dados da Matrícula");
        Painel_2_Jovens.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        btn_alterar.setBackground(new java.awt.Color(1, 155, 225));
        btn_alterar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_alterar.setForeground(new java.awt.Color(0, 0, 0));
        btn_alterar.setText("Alterar");
        btn_alterar.setBorderPainted(false);
        btn_alterar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_alterar.setFocusPainted(false);
        btn_alterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_alterarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_alterarMouseExited(evt);
            }
        });
        btn_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarActionPerformed(evt);
            }
        });
        Painel_2_Jovens.add(btn_alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 640, 150, 50));

        btn_cadastrar.setBackground(new java.awt.Color(1, 155, 225));
        btn_cadastrar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_cadastrar.setForeground(new java.awt.Color(0, 0, 0));
        btn_cadastrar.setText("Cadastrar");
        btn_cadastrar.setBorderPainted(false);
        btn_cadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_cadastrar.setFocusPainted(false);
        btn_cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cadastrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cadastrarMouseExited(evt);
            }
        });
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });
        Painel_2_Jovens.add(btn_cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 150, 50));

        btn_excluir.setBackground(new java.awt.Color(1, 155, 225));
        btn_excluir.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_excluir.setForeground(new java.awt.Color(0, 0, 0));
        btn_excluir.setText("Excluir");
        btn_excluir.setBorderPainted(false);
        btn_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_excluir.setFocusPainted(false);
        btn_excluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluirMouseExited(evt);
            }
        });
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });
        Painel_2_Jovens.add(btn_excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 640, 150, 50));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(5, 24, 64));
        jLabel24.setText("RG");
        Painel_2_Jovens.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        txt_curso.setBackground(new java.awt.Color(255, 255, 255));
        txt_curso.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_curso.setForeground(new java.awt.Color(5, 24, 64));
        txt_curso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        txt_curso.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_curso.setFocusable(false);
        txt_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cursoActionPerformed(evt);
            }
        });
        Painel_2_Jovens.add(txt_curso, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 230, 35));

        txt_genero.setBackground(new java.awt.Color(255, 255, 255));
        txt_genero.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_genero.setForeground(new java.awt.Color(5, 24, 64));
        txt_genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino", "Outro" }));
        txt_genero.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_genero.setFocusable(false);
        Painel_2_Jovens.add(txt_genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 110, 35));

        txt_status.setBackground(new java.awt.Color(255, 255, 255));
        txt_status.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_status.setForeground(new java.awt.Color(5, 24, 64));
        txt_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Ativo", "Inativo" }));
        txt_status.setBorder(null);
        txt_status.setFocusable(false);
        txt_status.setOpaque(false);
        Painel_2_Jovens.add(txt_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 120, 35));

        txt_matricula.setEditable(false);
        txt_matricula.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_matricula.setForeground(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_matricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 110, 35));

        txt_rg.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_rg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_rg.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_rg.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_rg.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_rg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 110, 35));

        tabela_jovens.setBackground(new java.awt.Color(255, 255, 255));
        tabela_jovens.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_jovens.setForeground(new java.awt.Color(5, 24, 64));
        tabela_jovens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nome", "Curso", "Ingresso", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_jovens.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_jovens.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tabela_jovens);

        Painel_2_Jovens.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 590, 560));

        txt_nascimento.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_nascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_nascimento.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_nascimento.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_nascimento.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_nascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, 110, 35));

        txt_data.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_data.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_data.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_data.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 100, 35));

        txt_cpf.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cpf.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_cpf.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_cpf.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 130, 35));

        txt_responsavel.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_responsavel.setForeground(new java.awt.Color(5, 24, 64));
        txt_responsavel.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_responsavel.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_2_Jovens.add(txt_responsavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 160, 35));

        jPanel1.setBackground(new java.awt.Color(5, 24, 64));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 24, 64), 2));
        jPanel1.setForeground(new java.awt.Color(5, 24, 64));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_total.setBackground(new java.awt.Color(0, 0, 0));
        txt_total.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        txt_total.setForeground(new java.awt.Color(255, 255, 255));
        txt_total.setText("##");
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 70, 50));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jovens");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 200, 50));

        Painel_2_Jovens.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 280, 50));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(5, 24, 64));
        jLabel4.setText("DASHBOARD");
        Painel_2_Jovens.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        btn_minimiza3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza3.setBorderPainted(false);
        btn_minimiza3.setContentAreaFilled(false);
        btn_minimiza3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza3ActionPerformed(evt);
            }
        });
        Painel_2_Jovens.add(btn_minimiza3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair3.setBorderPainted(false);
        btn_sair3.setContentAreaFilled(false);
        btn_sair3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair3.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair3.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair3ActionPerformed(evt);
            }
        });
        Painel_2_Jovens.add(btn_sair3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(5, 24, 64));
        jLabel6.setText("DASHBOARD");
        Painel_2_Jovens.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        Panel_Main.add(Painel_2_Jovens, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        Painel_3_Professores.setBackground(new java.awt.Color(255, 255, 255));
        Painel_3_Professores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Painel_3_Professores.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_3_Professores.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_3_Professores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Painel_3_Professores.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, -1));

        txt_id.setEditable(false);
        txt_id.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_id.setForeground(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 50, 35));

        txt_atuacao.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_atuacao.setForeground(new java.awt.Color(5, 24, 64));
        txt_atuacao.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_atuacao.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_atuacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 240, 35));

        txt_formacao.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_formacao.setForeground(new java.awt.Color(5, 24, 64));
        txt_formacao.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_formacao.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_formacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 290, 35));

        txt_horario.setBackground(new java.awt.Color(255, 255, 255));
        txt_horario.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_horario.setForeground(new java.awt.Color(5, 24, 64));
        txt_horario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Manhã", "Noite", "Integral" }));
        txt_horario.setFocusable(false);
        Painel_3_Professores.add(txt_horario, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 110, 35));

        txt_curso1.setBackground(new java.awt.Color(255, 255, 255));
        txt_curso1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_curso1.setForeground(new java.awt.Color(5, 24, 64));
        txt_curso1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        txt_curso1.setFocusable(false);
        Painel_3_Professores.add(txt_curso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 230, 35));

        tabela_professores.setBackground(new java.awt.Color(255, 255, 255));
        tabela_professores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_professores.setForeground(new java.awt.Color(5, 24, 64));
        tabela_professores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Curso", "Status "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_professores.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_professores.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tabela_professores);

        Painel_3_Professores.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 500, 560));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(5, 24, 64));
        jLabel7.setText("DASHBOARD");
        Painel_3_Professores.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(5, 24, 64));
        jLabel25.setText("Dados Professor");
        Painel_3_Professores.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setForeground(new java.awt.Color(5, 24, 64));
        jLabel16.setText("____________________________________________");
        Painel_3_Professores.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 330, 30));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(5, 24, 64));
        jLabel8.setText("Informações pessoais");
        Painel_3_Professores.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setForeground(new java.awt.Color(5, 24, 64));
        jLabel26.setText("____________________________________________");
        Painel_3_Professores.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 330, 30));

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(5, 24, 64));
        jLabel27.setText("ID");
        Painel_3_Professores.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(5, 24, 64));
        jLabel28.setText("Área de atuação");
        Painel_3_Professores.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(5, 24, 64));
        jLabel29.setText("Horário");
        Painel_3_Professores.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(5, 24, 64));
        jLabel30.setText("Formação Acadêmica ");
        Painel_3_Professores.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        jLabel31.setBackground(new java.awt.Color(0, 0, 0));
        jLabel31.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(5, 24, 64));
        jLabel31.setText("Curso");
        Painel_3_Professores.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        jPanel2.setBackground(new java.awt.Color(5, 24, 64));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 24, 64), 2));
        jPanel2.setForeground(new java.awt.Color(5, 24, 64));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_total1.setBackground(new java.awt.Color(0, 0, 0));
        txt_total1.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        txt_total1.setForeground(new java.awt.Color(255, 255, 255));
        txt_total1.setText("##");
        jPanel2.add(txt_total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 50));

        jLabel32.setBackground(new java.awt.Color(0, 0, 0));
        jLabel32.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Professores");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 300, 50));

        Painel_3_Professores.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 400, 50));

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(5, 24, 64));
        jLabel33.setText("Status");
        Painel_3_Professores.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        txt_status1.setBackground(new java.awt.Color(255, 255, 255));
        txt_status1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_status1.setForeground(new java.awt.Color(5, 24, 64));
        txt_status1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Ativo", "Inativo" }));
        txt_status1.setFocusable(false);
        Painel_3_Professores.add(txt_status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 120, 35));

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(5, 24, 64));
        jLabel34.setText("Nome completo");
        Painel_3_Professores.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        txt_nome1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_nome1.setForeground(new java.awt.Color(5, 24, 64));
        txt_nome1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_nome1.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_nome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 360, 35));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(5, 24, 64));
        jLabel35.setText("E-mail");
        Painel_3_Professores.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        txt_email1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_email1.setForeground(new java.awt.Color(5, 24, 64));
        txt_email1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_email1.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 210, 35));

        jLabel36.setBackground(new java.awt.Color(0, 0, 0));
        jLabel36.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(5, 24, 64));
        jLabel36.setText("Observações");
        Painel_3_Professores.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        txt_observacao.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_observacao.setForeground(new java.awt.Color(5, 24, 64));
        txt_observacao.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_observacao.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_observacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 610, 35));

        txt_cpf1.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_cpf1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cpf1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_cpf1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_cpf1.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_3_Professores.add(txt_cpf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 130, 35));

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(5, 24, 64));
        jLabel37.setText("CPF");
        Painel_3_Professores.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, -1, -1));

        txt_genero1.setBackground(new java.awt.Color(255, 255, 255));
        txt_genero1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_genero1.setForeground(new java.awt.Color(5, 24, 64));
        txt_genero1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Masculino", "Feminino", "Outro" }));
        txt_genero1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_genero1.setFocusable(false);
        Painel_3_Professores.add(txt_genero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 110, 35));

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(5, 24, 64));
        jLabel38.setText("Gênero");
        Painel_3_Professores.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));

        txt_nascimento1.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_nascimento1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_nascimento1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        Painel_3_Professores.add(txt_nascimento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, 110, 35));

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(5, 24, 64));
        jLabel39.setText("Nascimento");
        Painel_3_Professores.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, -1));

        btn_cadastrar1.setBackground(new java.awt.Color(1, 155, 225));
        btn_cadastrar1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_cadastrar1.setForeground(new java.awt.Color(0, 0, 0));
        btn_cadastrar1.setText("Cadastrar");
        btn_cadastrar1.setBorderPainted(false);
        btn_cadastrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_cadastrar1.setFocusPainted(false);
        btn_cadastrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cadastrar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cadastrar1MouseExited(evt);
            }
        });
        btn_cadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrar1ActionPerformed(evt);
            }
        });
        Painel_3_Professores.add(btn_cadastrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 150, 50));

        btn_alterar1.setBackground(new java.awt.Color(1, 155, 225));
        btn_alterar1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_alterar1.setForeground(new java.awt.Color(0, 0, 0));
        btn_alterar1.setText("Alterar");
        btn_alterar1.setBorderPainted(false);
        btn_alterar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_alterar1.setFocusPainted(false);
        btn_alterar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_alterar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_alterar1MouseExited(evt);
            }
        });
        btn_alterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterar1ActionPerformed(evt);
            }
        });
        Painel_3_Professores.add(btn_alterar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 640, 150, 50));

        btn_excluir1.setBackground(new java.awt.Color(1, 155, 225));
        btn_excluir1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_excluir1.setForeground(new java.awt.Color(0, 0, 0));
        btn_excluir1.setText("Excluir");
        btn_excluir1.setBorderPainted(false);
        btn_excluir1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_excluir1.setFocusPainted(false);
        btn_excluir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluir1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluir1MouseExited(evt);
            }
        });
        btn_excluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluir1ActionPerformed(evt);
            }
        });
        Painel_3_Professores.add(btn_excluir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 640, 150, 50));

        btn_minimiza4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza4.setBorderPainted(false);
        btn_minimiza4.setContentAreaFilled(false);
        btn_minimiza4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza4ActionPerformed(evt);
            }
        });
        Painel_3_Professores.add(btn_minimiza4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair4.setBorderPainted(false);
        btn_sair4.setContentAreaFilled(false);
        btn_sair4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair4.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair4.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair4ActionPerformed(evt);
            }
        });
        Painel_3_Professores.add(btn_sair4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        Panel_Main.add(Painel_3_Professores, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        Painel_4_Cursos.setBackground(new java.awt.Color(255, 255, 255));
        Painel_4_Cursos.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_4_Cursos.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_4_Cursos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Painel_4_Cursos.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, -1));

        txt_nome2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_nome2.setForeground(new java.awt.Color(5, 24, 64));
        txt_nome2.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_nome2.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_4_Cursos.add(txt_nome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 260, 35));

        txt_id1.setEditable(false);
        txt_id1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_id1.setForeground(new java.awt.Color(5, 24, 64));
        Painel_4_Cursos.add(txt_id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 50, 35));

        txt_professor11.setBackground(new java.awt.Color(255, 255, 255));
        txt_professor11.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_professor11.setForeground(new java.awt.Color(5, 24, 64));
        txt_professor11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        txt_professor11.setFocusable(false);
        Painel_4_Cursos.add(txt_professor11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 220, 35));

        txt_modalidade.setBackground(new java.awt.Color(255, 255, 255));
        txt_modalidade.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_modalidade.setForeground(new java.awt.Color(5, 24, 64));
        txt_modalidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Presencial", "Online", "Hibrido" }));
        txt_modalidade.setFocusable(false);
        Painel_4_Cursos.add(txt_modalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 110, 35));

        txt_status2.setBackground(new java.awt.Color(255, 255, 255));
        txt_status2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_status2.setForeground(new java.awt.Color(5, 24, 64));
        txt_status2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Ativo", "Inativo" }));
        txt_status2.setFocusable(false);
        Painel_4_Cursos.add(txt_status2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 120, 35));

        tabela_cursos.setAutoCreateRowSorter(true);
        tabela_cursos.setBackground(new java.awt.Color(255, 255, 255));
        tabela_cursos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_cursos.setForeground(new java.awt.Color(5, 24, 64));
        tabela_cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Modalidade", "Status", "Professor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_cursos.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_cursos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tabela_cursos);

        Painel_4_Cursos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 590, 560));

        txt_descricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 24, 64), 1, true));
        txt_descricao.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_descricao.setForeground(new java.awt.Color(5, 24, 64));
        txt_descricao.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_descricao.setSelectionColor(new java.awt.Color(5, 24, 64));
        jScrollPane4.setViewportView(txt_descricao);

        Painel_4_Cursos.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 520, 90));

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(5, 24, 64));
        jLabel40.setText("DASHBOARD");
        Painel_4_Cursos.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel41.setBackground(new java.awt.Color(0, 0, 0));
        jLabel41.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(5, 24, 64));
        jLabel41.setText("Dados Curso");
        Painel_4_Cursos.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel42.setBackground(new java.awt.Color(0, 0, 0));
        jLabel42.setForeground(new java.awt.Color(5, 24, 64));
        jLabel42.setText("____________________________________________");
        Painel_4_Cursos.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 330, 30));

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(5, 24, 64));
        jLabel43.setText("Professor");
        Painel_4_Cursos.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jLabel44.setBackground(new java.awt.Color(0, 0, 0));
        jLabel44.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(5, 24, 64));
        jLabel44.setText("Nome");
        Painel_4_Cursos.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabel45.setBackground(new java.awt.Color(0, 0, 0));
        jLabel45.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(5, 24, 64));
        jLabel45.setText("ID");
        Painel_4_Cursos.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel46.setBackground(new java.awt.Color(0, 0, 0));
        jLabel46.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(5, 24, 64));
        jLabel46.setText("Descrição");
        Painel_4_Cursos.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel47.setBackground(new java.awt.Color(0, 0, 0));
        jLabel47.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(5, 24, 64));
        jLabel47.setText("Modalidade");
        Painel_4_Cursos.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        jLabel48.setBackground(new java.awt.Color(0, 0, 0));
        jLabel48.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(5, 24, 64));
        jLabel48.setText("Status");
        Painel_4_Cursos.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jPanel3.setBackground(new java.awt.Color(5, 24, 64));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 24, 64), 2));
        jPanel3.setForeground(new java.awt.Color(5, 24, 64));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_total2.setBackground(new java.awt.Color(0, 0, 0));
        txt_total2.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        txt_total2.setForeground(new java.awt.Color(255, 255, 255));
        txt_total2.setText("##");
        jPanel3.add(txt_total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 70, 50));

        jLabel49.setBackground(new java.awt.Color(0, 0, 0));
        jLabel49.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Cursos");
        jPanel3.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 180, 50));

        Painel_4_Cursos.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 300, 50));

        btn_excluir2.setBackground(new java.awt.Color(1, 155, 225));
        btn_excluir2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_excluir2.setForeground(new java.awt.Color(0, 0, 0));
        btn_excluir2.setText("Excluir");
        btn_excluir2.setBorderPainted(false);
        btn_excluir2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_excluir2.setFocusPainted(false);
        btn_excluir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluir2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluir2MouseExited(evt);
            }
        });
        btn_excluir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluir2ActionPerformed(evt);
            }
        });
        Painel_4_Cursos.add(btn_excluir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 640, 150, 50));

        btn_login3.setBackground(new java.awt.Color(1, 155, 225));
        btn_login3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_login3.setForeground(new java.awt.Color(0, 0, 0));
        btn_login3.setText("Cadastrar");
        btn_login3.setBorderPainted(false);
        btn_login3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_login3.setFocusPainted(false);
        btn_login3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_login3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_login3MouseExited(evt);
            }
        });
        btn_login3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_login3ActionPerformed(evt);
            }
        });
        Painel_4_Cursos.add(btn_login3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 150, 50));

        btn_alterar2.setBackground(new java.awt.Color(1, 155, 225));
        btn_alterar2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_alterar2.setForeground(new java.awt.Color(0, 0, 0));
        btn_alterar2.setText("Alterar");
        btn_alterar2.setBorderPainted(false);
        btn_alterar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_alterar2.setFocusPainted(false);
        btn_alterar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_alterar2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_alterar2MouseExited(evt);
            }
        });
        btn_alterar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterar2ActionPerformed(evt);
            }
        });
        Painel_4_Cursos.add(btn_alterar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 640, 150, 50));

        btn_minimiza5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza5.setBorderPainted(false);
        btn_minimiza5.setContentAreaFilled(false);
        btn_minimiza5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza5ActionPerformed(evt);
            }
        });
        Painel_4_Cursos.add(btn_minimiza5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair5.setBorderPainted(false);
        btn_sair5.setContentAreaFilled(false);
        btn_sair5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair5.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair5.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair5ActionPerformed(evt);
            }
        });
        Painel_4_Cursos.add(btn_sair5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        Panel_Main.add(Painel_4_Cursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        Painel_5_Produtos.setBackground(new java.awt.Color(255, 255, 255));
        Painel_5_Produtos.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_5_Produtos.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_5_Produtos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Painel_5_Produtos.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, -1));

        txt_id2.setEditable(false);
        txt_id2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_id2.setForeground(new java.awt.Color(5, 24, 64));
        Painel_5_Produtos.add(txt_id2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 50, 30));

        jLabel50.setBackground(new java.awt.Color(5, 24, 64));
        jLabel50.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(5, 24, 64));
        jLabel50.setText("Preço");
        Painel_5_Produtos.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        jLabel51.setBackground(new java.awt.Color(5, 24, 64));
        jLabel51.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(5, 24, 64));
        jLabel51.setText("Faixa Etária");
        Painel_5_Produtos.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        txt_etaria.setBackground(new java.awt.Color(255, 255, 255));
        txt_etaria.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_etaria.setForeground(new java.awt.Color(5, 24, 64));
        txt_etaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Bebê (0-1)", "Infante (1-3)", "Pré-escolares (4-6)", "Crianças (7-12)", "Pré-adolescente (12-14)", "Adolescente (15-17)", "Adultos (18+)" }));
        txt_etaria.setFocusable(false);
        Painel_5_Produtos.add(txt_etaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 210, 35));

        jLabel52.setBackground(new java.awt.Color(5, 24, 64));
        jLabel52.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(5, 24, 64));
        jLabel52.setText("Tipo");
        Painel_5_Produtos.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        jLabel53.setBackground(new java.awt.Color(5, 24, 64));
        jLabel53.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(5, 24, 64));
        jLabel53.setText("Materiais");
        Painel_5_Produtos.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        txt_custo.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_custo.setForeground(new java.awt.Color(5, 24, 64));
        txt_custo.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_custo.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_5_Produtos.add(txt_custo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 110, 35));

        jLabel54.setBackground(new java.awt.Color(5, 24, 64));
        jLabel54.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(5, 24, 64));
        jLabel54.setText("Custo de produção");
        Painel_5_Produtos.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, -1, -1));

        jLabel55.setBackground(new java.awt.Color(5, 24, 64));
        jLabel55.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(5, 24, 64));
        jLabel55.setText("Estoque");
        Painel_5_Produtos.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 530, -1, -1));

        jLabel56.setBackground(new java.awt.Color(5, 24, 64));
        jLabel56.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(5, 24, 64));
        jLabel56.setText("Tempo de produção");
        Painel_5_Produtos.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        txt_materiais.setBackground(new java.awt.Color(255, 255, 255));
        txt_materiais.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 24, 64), 1, true));
        txt_materiais.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_materiais.setForeground(new java.awt.Color(5, 24, 64));
        txt_materiais.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Madeira", "Papelão Reciclado", "Algodão Orgânico", "Borracha Natural", "Fibras Naturais", "Plástico Reciclado", "Argila", "Tecido Reciclado", "Madeira Compensada", "Plástico Biodegradável" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        txt_materiais.setSelectionBackground(new java.awt.Color(5, 24, 64));
        txt_materiais.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(txt_materiais);

        Painel_5_Produtos.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 200, 260));

        jLabel57.setBackground(new java.awt.Color(0, 0, 0));
        jLabel57.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(5, 24, 64));
        jLabel57.setText("R$");
        Painel_5_Produtos.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, -1, -1));

        jLabel58.setBackground(new java.awt.Color(0, 0, 0));
        jLabel58.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(5, 24, 64));
        jLabel58.setText("x");
        Painel_5_Produtos.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 40, 20));

        txt_estoque.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_estoque.setForeground(new java.awt.Color(5, 24, 64));
        txt_estoque.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_estoque.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_5_Produtos.add(txt_estoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 110, 35));

        jLabel59.setBackground(new java.awt.Color(0, 0, 0));
        jLabel59.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(5, 24, 64));
        jLabel59.setText("R$");
        Painel_5_Produtos.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        txt_preco.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_preco.setForeground(new java.awt.Color(5, 24, 64));
        txt_preco.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_preco.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_5_Produtos.add(txt_preco, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 90, 35));

        txt_producao.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_producao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## dias")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_producao.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_producao.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_producao.setSelectionColor(new java.awt.Color(5, 24, 64));
        txt_producao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_producaoActionPerformed(evt);
            }
        });
        Painel_5_Produtos.add(txt_producao, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 100, 35));

        tabela_produtos.setBackground(new java.awt.Color(255, 255, 255));
        tabela_produtos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_produtos.setForeground(new java.awt.Color(5, 24, 64));
        tabela_produtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Preço", "Estoque", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_produtos.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_produtos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(tabela_produtos);

        Painel_5_Produtos.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 530, 560));

        jLabel60.setBackground(new java.awt.Color(0, 0, 0));
        jLabel60.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(5, 24, 64));
        jLabel60.setText("DASHBOARD");
        Painel_5_Produtos.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel61.setBackground(new java.awt.Color(0, 0, 0));
        jLabel61.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(5, 24, 64));
        jLabel61.setText("Dados Produto");
        Painel_5_Produtos.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel62.setBackground(new java.awt.Color(0, 0, 0));
        jLabel62.setForeground(new java.awt.Color(5, 24, 64));
        jLabel62.setText("____________________________________________");
        Painel_5_Produtos.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 330, 30));

        jLabel63.setBackground(new java.awt.Color(0, 0, 0));
        jLabel63.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(5, 24, 64));
        jLabel63.setText("ID");
        Painel_5_Produtos.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel64.setBackground(new java.awt.Color(0, 0, 0));
        jLabel64.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(5, 24, 64));
        jLabel64.setText("Status");
        Painel_5_Produtos.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel65.setBackground(new java.awt.Color(0, 0, 0));
        jLabel65.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(5, 24, 64));
        jLabel65.setText("Nome");
        Painel_5_Produtos.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        txt_nome3.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_nome3.setForeground(new java.awt.Color(5, 24, 64));
        txt_nome3.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_nome3.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_5_Produtos.add(txt_nome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 270, 35));

        jLabel66.setBackground(new java.awt.Color(0, 0, 0));
        jLabel66.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(5, 24, 64));
        jLabel66.setText("Informações Comerciais");
        Painel_5_Produtos.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel67.setBackground(new java.awt.Color(0, 0, 0));
        jLabel67.setForeground(new java.awt.Color(5, 24, 64));
        jLabel67.setText("____________________________________________");
        Painel_5_Produtos.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 330, 30));

        jPanel7.setBackground(new java.awt.Color(5, 24, 64));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 24, 64), 2));
        jPanel7.setForeground(new java.awt.Color(5, 24, 64));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_total3.setBackground(new java.awt.Color(0, 0, 0));
        txt_total3.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        txt_total3.setForeground(new java.awt.Color(255, 255, 255));
        txt_total3.setText("##");
        jPanel7.add(txt_total3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 50));

        jLabel68.setBackground(new java.awt.Color(0, 0, 0));
        jLabel68.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Produtos");
        jPanel7.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 230, 50));

        Painel_5_Produtos.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 330, 50));

        btn_login4.setBackground(new java.awt.Color(1, 155, 225));
        btn_login4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_login4.setForeground(new java.awt.Color(0, 0, 0));
        btn_login4.setText("Cadastrar");
        btn_login4.setBorderPainted(false);
        btn_login4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_login4.setFocusPainted(false);
        btn_login4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_login4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_login4MouseExited(evt);
            }
        });
        btn_login4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_login4ActionPerformed(evt);
            }
        });
        Painel_5_Produtos.add(btn_login4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 150, 50));

        btn_alterar3.setBackground(new java.awt.Color(1, 155, 225));
        btn_alterar3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_alterar3.setForeground(new java.awt.Color(0, 0, 0));
        btn_alterar3.setText("Alterar");
        btn_alterar3.setBorderPainted(false);
        btn_alterar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_alterar3.setFocusPainted(false);
        btn_alterar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_alterar3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_alterar3MouseExited(evt);
            }
        });
        btn_alterar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterar3ActionPerformed(evt);
            }
        });
        Painel_5_Produtos.add(btn_alterar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 640, 150, 50));

        btn_excluir3.setBackground(new java.awt.Color(1, 155, 225));
        btn_excluir3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_excluir3.setForeground(new java.awt.Color(0, 0, 0));
        btn_excluir3.setText("Excluir");
        btn_excluir3.setBorderPainted(false);
        btn_excluir3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_excluir3.setFocusPainted(false);
        btn_excluir3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluir3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluir3MouseExited(evt);
            }
        });
        btn_excluir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluir3ActionPerformed(evt);
            }
        });
        Painel_5_Produtos.add(btn_excluir3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 640, 150, 50));

        btn_minimiza6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza6.setBorderPainted(false);
        btn_minimiza6.setContentAreaFilled(false);
        btn_minimiza6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza6ActionPerformed(evt);
            }
        });
        Painel_5_Produtos.add(btn_minimiza6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair6.setBorderPainted(false);
        btn_sair6.setContentAreaFilled(false);
        btn_sair6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair6.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair6.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair6ActionPerformed(evt);
            }
        });
        Painel_5_Produtos.add(btn_sair6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        txt_tipo.setBackground(new java.awt.Color(255, 255, 255));
        txt_tipo.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_tipo.setForeground(new java.awt.Color(5, 24, 64));
        txt_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Educativo", "Construção", "Faz de Conta", "Atividades Físicas", "Encaixe", "Interativos", "Artísticos", "Musicais", "Puzzle", "Colecionável" }));
        txt_tipo.setFocusable(false);
        Painel_5_Produtos.add(txt_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 170, 35));

        txt_status3.setBackground(new java.awt.Color(255, 255, 255));
        txt_status3.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_status3.setForeground(new java.awt.Color(5, 24, 64));
        txt_status3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Ativo", "Inativo" }));
        Painel_5_Produtos.add(txt_status3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 120, 35));

        Panel_Main.add(Painel_5_Produtos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        Painel_6_Vendas.setBackground(new java.awt.Color(255, 255, 255));
        Painel_6_Vendas.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_6_Vendas.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_6_Vendas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Painel_6_Vendas.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, -1));

        txt_nome4.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_nome4.setForeground(new java.awt.Color(5, 24, 64));
        txt_nome4.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_nome4.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_nome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 310, 35));

        jLabel69.setBackground(new java.awt.Color(0, 0, 0));
        jLabel69.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(5, 24, 64));
        jLabel69.setText("%");
        Painel_6_Vendas.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 40, 30));

        jLabel70.setBackground(new java.awt.Color(0, 0, 0));
        jLabel70.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(5, 24, 64));
        jLabel70.setText("Nome");
        Painel_6_Vendas.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel71.setBackground(new java.awt.Color(0, 0, 0));
        jLabel71.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(5, 24, 64));
        jLabel71.setText("Data ");
        Painel_6_Vendas.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        jLabel72.setBackground(new java.awt.Color(0, 0, 0));
        jLabel72.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(5, 24, 64));
        jLabel72.setText("Hora");
        Painel_6_Vendas.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, 30));

        jLabel73.setBackground(new java.awt.Color(5, 24, 64));
        jLabel73.setForeground(new java.awt.Color(5, 24, 64));
        jLabel73.setText("____________________________________________");
        Painel_6_Vendas.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 330, 30));

        jLabel74.setBackground(new java.awt.Color(0, 0, 0));
        jLabel74.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(5, 24, 64));
        jLabel74.setText("CPF");
        Painel_6_Vendas.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, -1, -1));

        jLabel75.setBackground(new java.awt.Color(0, 0, 0));
        jLabel75.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(5, 24, 64));
        jLabel75.setText("Telefone");
        Painel_6_Vendas.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        txt_email2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_email2.setForeground(new java.awt.Color(5, 24, 64));
        txt_email2.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_email2.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 310, 35));

        jLabel76.setBackground(new java.awt.Color(0, 0, 0));
        jLabel76.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(5, 24, 64));
        jLabel76.setText("E-mail");
        Painel_6_Vendas.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        txt_pedido.setEditable(false);
        txt_pedido.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_pedido.setForeground(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 60, 35));

        jLabel77.setBackground(new java.awt.Color(5, 24, 64));
        jLabel77.setForeground(new java.awt.Color(5, 24, 64));
        jLabel77.setText("____________________________________________");
        Painel_6_Vendas.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 330, 30));

        jLabel78.setBackground(new java.awt.Color(0, 0, 0));
        jLabel78.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(5, 24, 64));
        jLabel78.setText("Modo");
        Painel_6_Vendas.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1));

        jLabel79.setBackground(new java.awt.Color(5, 24, 64));
        jLabel79.setForeground(new java.awt.Color(5, 24, 64));
        jLabel79.setText("____________________________________________");
        Painel_6_Vendas.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 330, 30));

        txt_rua.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_rua.setForeground(new java.awt.Color(5, 24, 64));
        txt_rua.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_rua.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_rua, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 230, 35));

        jLabel80.setBackground(new java.awt.Color(0, 0, 0));
        jLabel80.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(5, 24, 64));
        jLabel80.setText("Rua");
        Painel_6_Vendas.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, 20));

        jLabel81.setBackground(new java.awt.Color(0, 0, 0));
        jLabel81.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(5, 24, 64));
        jLabel81.setText("Bairro");
        Painel_6_Vendas.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, -1, 20));

        txt_bairro.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_bairro.setForeground(new java.awt.Color(5, 24, 64));
        txt_bairro.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_bairro.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, 230, 35));

        jLabel82.setBackground(new java.awt.Color(0, 0, 0));
        jLabel82.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(5, 24, 64));
        jLabel82.setText("CEP");
        Painel_6_Vendas.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, 20));

        jLabel83.setBackground(new java.awt.Color(0, 0, 0));
        jLabel83.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(5, 24, 64));
        jLabel83.setText("Número");
        Painel_6_Vendas.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 330, -1, 20));

        txt_numero.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_numero.setForeground(new java.awt.Color(5, 24, 64));
        txt_numero.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_numero.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 40, 35));

        jLabel84.setBackground(new java.awt.Color(0, 0, 0));
        jLabel84.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(5, 24, 64));
        jLabel84.setText("Cidade");
        Painel_6_Vendas.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, -1, 20));

        txt_cidade.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_cidade.setForeground(new java.awt.Color(5, 24, 64));
        txt_cidade.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_cidade.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, 130, 35));

        jLabel85.setBackground(new java.awt.Color(0, 0, 0));
        jLabel85.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(5, 24, 64));
        jLabel85.setText("Prazo");
        Painel_6_Vendas.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 480, -1, 20));

        jLabel86.setBackground(new java.awt.Color(0, 0, 0));
        jLabel86.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(5, 24, 64));
        jLabel86.setText("Produto");
        Painel_6_Vendas.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        jLabel87.setBackground(new java.awt.Color(0, 0, 0));
        jLabel87.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(5, 24, 64));
        jLabel87.setText("Qtd.");
        Painel_6_Vendas.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, 30));

        jLabel88.setBackground(new java.awt.Color(0, 0, 0));
        jLabel88.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(5, 24, 64));
        jLabel88.setText("x");
        Painel_6_Vendas.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 40, 60));

        txt_quantidade.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_quantidade.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_quantidade.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 50, 35));

        txt_preco1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_preco1.setForeground(new java.awt.Color(5, 24, 64));
        txt_preco1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_preco1.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_preco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 70, 35));

        jLabel89.setBackground(new java.awt.Color(0, 0, 0));
        jLabel89.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(5, 24, 64));
        jLabel89.setText("Tipo");
        Painel_6_Vendas.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        jLabel90.setBackground(new java.awt.Color(0, 0, 0));
        jLabel90.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(5, 24, 64));
        jLabel90.setText("Desconto");
        Painel_6_Vendas.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, -1));

        txt_produto.setBackground(new java.awt.Color(255, 255, 255));
        txt_produto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_produto.setForeground(new java.awt.Color(5, 24, 64));
        txt_produto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        txt_produto.setFocusable(false);
        Painel_6_Vendas.add(txt_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 290, 35));

        try {
            txt_data1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_data1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_data1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_data1.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_data1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 100, 35));

        try {
            txt_hora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_hora.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_hora.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_hora.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 60, 35));

        txt_cpf2.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_cpf2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cpf2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_cpf2.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_cpf2.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_cpf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 140, 35));

        txt_telefone.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_telefone.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_telefone.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_telefone.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_telefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 140, 35));

        txt_cep.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_cep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cep.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_cep.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_cep.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 90, 35));

        btn_login5.setBackground(new java.awt.Color(1, 155, 225));
        btn_login5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_login5.setForeground(new java.awt.Color(0, 0, 0));
        btn_login5.setText("Cadastrar");
        btn_login5.setBorderPainted(false);
        btn_login5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_login5.setFocusPainted(false);
        btn_login5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_login5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_login5MouseExited(evt);
            }
        });
        btn_login5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_login5ActionPerformed(evt);
            }
        });
        Painel_6_Vendas.add(btn_login5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 150, 50));

        txt_prazo.setForeground(new java.awt.Color(5, 24, 64));
        try {
            txt_prazo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## dias")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_prazo.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_prazo.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_prazo.setSelectionColor(new java.awt.Color(5, 24, 64));
        Painel_6_Vendas.add(txt_prazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, 70, 35));

        tabela_vendas.setBackground(new java.awt.Color(255, 255, 255));
        tabela_vendas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_vendas.setForeground(new java.awt.Color(5, 24, 64));
        tabela_vendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pedido", "Data", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_vendas.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_vendas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setViewportView(tabela_vendas);

        Painel_6_Vendas.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 310, 560));

        txt_modo.setBackground(new java.awt.Color(255, 255, 255));
        txt_modo.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_modo.setForeground(new java.awt.Color(5, 24, 64));
        txt_modo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Retirada no local", "Entrega via Correios" }));
        txt_modo.setFocusable(false);
        Painel_6_Vendas.add(txt_modo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 210, 35));

        txt_pagamento.setBackground(new java.awt.Color(255, 255, 255));
        txt_pagamento.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_pagamento.setForeground(new java.awt.Color(5, 24, 64));
        txt_pagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Débito", "Pix", "Dinheiro", "Crédito - 1x sem juros", "Crédito - 2x sem juros", "Crédito - 3x sem juros", "Crédito - 4x sem juros", "Crédito - 5x sem juros", "Crédito - 6x sem juros" }));
        txt_pagamento.setFocusable(false);
        Painel_6_Vendas.add(txt_pagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 190, 35));

        txt_desconto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_desconto.setForeground(new java.awt.Color(5, 24, 64));
        txt_desconto.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        txt_desconto.setSelectionColor(new java.awt.Color(5, 24, 64));
        txt_desconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descontoActionPerformed(evt);
            }
        });
        Painel_6_Vendas.add(txt_desconto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 550, 60, 35));

        btn_alterar4.setBackground(new java.awt.Color(1, 155, 225));
        btn_alterar4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_alterar4.setForeground(new java.awt.Color(0, 0, 0));
        btn_alterar4.setText("Alterar");
        btn_alterar4.setBorderPainted(false);
        btn_alterar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_alterar4.setFocusPainted(false);
        btn_alterar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_alterar4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_alterar4MouseExited(evt);
            }
        });
        btn_alterar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterar4ActionPerformed(evt);
            }
        });
        Painel_6_Vendas.add(btn_alterar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 640, 150, 50));

        btn_excluir4.setBackground(new java.awt.Color(1, 155, 225));
        btn_excluir4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_excluir4.setForeground(new java.awt.Color(0, 0, 0));
        btn_excluir4.setText("Excluir");
        btn_excluir4.setBorderPainted(false);
        btn_excluir4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_excluir4.setFocusPainted(false);
        btn_excluir4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluir4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluir4MouseExited(evt);
            }
        });
        btn_excluir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluir4ActionPerformed(evt);
            }
        });
        Painel_6_Vendas.add(btn_excluir4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 640, 150, 50));

        jLabel91.setBackground(new java.awt.Color(0, 0, 0));
        jLabel91.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(5, 24, 64));
        jLabel91.setText("R$");
        Painel_6_Vendas.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, 40, 30));

        jLabel92.setBackground(new java.awt.Color(0, 0, 0));
        jLabel92.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(5, 24, 64));
        jLabel92.setText("DASHBOARD");
        Painel_6_Vendas.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel93.setBackground(new java.awt.Color(0, 0, 0));
        jLabel93.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(5, 24, 64));
        jLabel93.setText("Entrega");
        Painel_6_Vendas.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        jLabel94.setBackground(new java.awt.Color(5, 24, 64));
        jLabel94.setForeground(new java.awt.Color(5, 24, 64));
        jLabel94.setText("____________________________________________");
        Painel_6_Vendas.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 330, 30));

        jLabel95.setBackground(new java.awt.Color(0, 0, 0));
        jLabel95.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(5, 24, 64));
        jLabel95.setText("Pedido");
        Painel_6_Vendas.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel96.setBackground(new java.awt.Color(0, 0, 0));
        jLabel96.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(5, 24, 64));
        jLabel96.setText("Dados Venda");
        Painel_6_Vendas.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel97.setBackground(new java.awt.Color(0, 0, 0));
        jLabel97.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(5, 24, 64));
        jLabel97.setText("Dados Comprador");
        Painel_6_Vendas.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel98.setBackground(new java.awt.Color(0, 0, 0));
        jLabel98.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(5, 24, 64));
        jLabel98.setText("Pagamento");
        Painel_6_Vendas.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        jPanel9.setBackground(new java.awt.Color(5, 24, 64));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 24, 64), 2));
        jPanel9.setForeground(new java.awt.Color(5, 24, 64));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_total4.setBackground(new java.awt.Color(0, 0, 0));
        txt_total4.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        txt_total4.setForeground(new java.awt.Color(255, 255, 255));
        txt_total4.setText("##");
        jPanel9.add(txt_total4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 50));

        jLabel99.setBackground(new java.awt.Color(0, 0, 0));
        jLabel99.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Vendas");
        jPanel9.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 190, 50));

        Painel_6_Vendas.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 310, 50));

        jLabel100.setBackground(new java.awt.Color(0, 0, 0));
        jLabel100.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(5, 24, 64));
        jLabel100.setText("Preço");
        Painel_6_Vendas.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 520, -1, -1));

        btn_minimiza7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza7.setBorderPainted(false);
        btn_minimiza7.setContentAreaFilled(false);
        btn_minimiza7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza7ActionPerformed(evt);
            }
        });
        Painel_6_Vendas.add(btn_minimiza7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair7.setBorderPainted(false);
        btn_sair7.setContentAreaFilled(false);
        btn_sair7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair7.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair7.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair7ActionPerformed(evt);
            }
        });
        Painel_6_Vendas.add(btn_sair7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        Panel_Main.add(Painel_6_Vendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        Painel_7_Relatorios.setBackground(new java.awt.Color(255, 255, 255));
        Painel_7_Relatorios.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_7_Relatorios.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_7_Relatorios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_PDF.setBackground(new java.awt.Color(1, 155, 225));
        btn_PDF.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_PDF.setForeground(new java.awt.Color(0, 0, 0));
        btn_PDF.setText("PDF");
        btn_PDF.setBorderPainted(false);
        btn_PDF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_PDF.setFocusPainted(false);
        btn_PDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_PDFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_PDFMouseExited(evt);
            }
        });
        btn_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PDFActionPerformed(evt);
            }
        });
        Painel_7_Relatorios.add(btn_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 150, 50));

        txt_tipo1.setBackground(new java.awt.Color(255, 255, 255));
        txt_tipo1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_tipo1.setForeground(new java.awt.Color(5, 24, 64));
        txt_tipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Jovens", "Professores", "Cursos", "Produtos", "Vendas" }));
        txt_tipo1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_tipo1.setFocusable(false);
        txt_tipo1.setOpaque(false);
        txt_tipo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tipo1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_tipo1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_tipo1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_tipo1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_tipo1MouseReleased(evt);
            }
        });
        txt_tipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipo1ActionPerformed(evt);
            }
        });
        txt_tipo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tipo1KeyReleased(evt);
            }
        });
        Painel_7_Relatorios.add(txt_tipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 330, -1));

        Painel_Vendas.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Vendas.setForeground(new java.awt.Color(255, 255, 255));
        Painel_Vendas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela_vendas1.setBackground(new java.awt.Color(255, 255, 255));
        tabela_vendas1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_vendas1.setForeground(new java.awt.Color(5, 24, 64));
        tabela_vendas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pedido", "Data", "Produto", "Qtd.", "Valor Bruto", "Desconto", "Líquido", "Retirada", "Pagamento", "Região", "Freq."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_vendas1.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_vendas1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setViewportView(tabela_vendas1);

        Painel_Vendas.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 590));

        Painel_7_Relatorios.add(Painel_Vendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1140, 600));

        Painel_Produtos.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Produtos.setForeground(new java.awt.Color(255, 255, 255));
        Painel_Produtos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela_produtos1.setBackground(new java.awt.Color(255, 255, 255));
        tabela_produtos1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_produtos1.setForeground(new java.awt.Color(5, 24, 64));
        tabela_produtos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Faixa Etária", "Preço", "Lucro", "Demanda", "Estoque", "Status Est.", "% Cartão", "% Pix", "% Outros"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_produtos1.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_produtos1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane9.setViewportView(tabela_produtos1);

        Painel_Produtos.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 550));

        Painel_7_Relatorios.add(Painel_Produtos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1140, 600));

        Painel_Cursos.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Cursos.setForeground(new java.awt.Color(255, 255, 255));
        Painel_Cursos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela_cursos1.setBackground(new java.awt.Color(255, 255, 255));
        tabela_cursos1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_cursos1.setForeground(new java.awt.Color(5, 24, 64));
        tabela_cursos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Status", "Nome", "Modalidade", "Nível", "Duração", "Vagas", "Matrículas", "Conclusão", "Emprega", "Investimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_cursos1.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_cursos1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane10.setViewportView(tabela_cursos1);

        Painel_Cursos.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1080, 550));

        Painel_7_Relatorios.add(Painel_Cursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1140, 600));

        Painel_Professores.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Professores.setForeground(new java.awt.Color(255, 255, 255));
        Painel_Professores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela_professores1.setBackground(new java.awt.Color(255, 255, 255));
        tabela_professores1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_professores1.setForeground(new java.awt.Color(5, 24, 64));
        tabela_professores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Status", "Nome", "Formação", "Curso", "Horário", "Disp.", "Ínicio", "Contrato", "Tempo", "Av. alunos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_professores1.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_professores1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane11.setViewportView(tabela_professores1);

        Painel_Professores.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1140, 550));

        Painel_7_Relatorios.add(Painel_Professores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1140, 600));

        Painel_Jovens.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Jovens.setForeground(new java.awt.Color(255, 255, 255));
        Painel_Jovens.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane12.setBackground(new java.awt.Color(255, 255, 255));

        tabela_jovens1.setBackground(new java.awt.Color(255, 255, 255));
        tabela_jovens1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tabela_jovens1.setForeground(new java.awt.Color(5, 24, 64));
        tabela_jovens1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Status", "Nome", "Idade", "Curso", "Freq.", "Média", "Contato", "Ingresso", "Última ativ.", "Financeiro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela_jovens1.setSelectionBackground(new java.awt.Color(5, 24, 64));
        tabela_jovens1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane12.setViewportView(tabela_jovens1);

        Painel_Jovens.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1090, 550));

        Painel_7_Relatorios.add(Painel_Jovens, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1140, 600));

        Painel_base.setBackground(new java.awt.Color(255, 255, 255));
        Painel_base.setForeground(new java.awt.Color(255, 255, 255));
        Painel_base.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Painel_7_Relatorios.add(Painel_base, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1140, 600));

        jLabel101.setBackground(new java.awt.Color(0, 0, 0));
        jLabel101.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(5, 24, 64));
        jLabel101.setText("RELATÓRIOS");
        Painel_7_Relatorios.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        btn_minimiza8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza8.setBorderPainted(false);
        btn_minimiza8.setContentAreaFilled(false);
        btn_minimiza8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza8ActionPerformed(evt);
            }
        });
        Painel_7_Relatorios.add(btn_minimiza8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair8.setBorderPainted(false);
        btn_sair8.setContentAreaFilled(false);
        btn_sair8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair8.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair8.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair8ActionPerformed(evt);
            }
        });
        Painel_7_Relatorios.add(btn_sair8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        Panel_Main.add(Painel_7_Relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 680));

        Painel_8_Financeiro.setBackground(new java.awt.Color(255, 255, 255));
        Painel_8_Financeiro.setMinimumSize(new java.awt.Dimension(540, 710));
        Painel_8_Financeiro.setPreferredSize(new java.awt.Dimension(540, 710));
        Painel_8_Financeiro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel102.setBackground(new java.awt.Color(0, 0, 0));
        jLabel102.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(5, 24, 64));
        jLabel102.setText("FINANCEIRO");
        Painel_8_Financeiro.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        btn_minimiza9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Minimizar_1.png"))); // NOI18N
        btn_minimiza9.setBorderPainted(false);
        btn_minimiza9.setContentAreaFilled(false);
        btn_minimiza9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimiza9ActionPerformed(evt);
            }
        });
        Painel_8_Financeiro.add(btn_minimiza9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        btn_sair9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Btn_Sair_1.png"))); // NOI18N
        btn_sair9.setBorderPainted(false);
        btn_sair9.setContentAreaFilled(false);
        btn_sair9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sair9.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btn_sair9.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_sair9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sair9ActionPerformed(evt);
            }
        });
        Painel_8_Financeiro.add(btn_sair9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 30, 40));

        txt_tipo2.setBackground(new java.awt.Color(255, 255, 255));
        txt_tipo2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txt_tipo2.setForeground(new java.awt.Color(5, 24, 64));
        txt_tipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "ONG", "Vendas" }));
        txt_tipo2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_tipo2.setFocusable(false);
        txt_tipo2.setOpaque(false);
        txt_tipo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_tipo2ItemStateChanged(evt);
            }
        });
        txt_tipo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tipo2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_tipo2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_tipo2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_tipo2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_tipo2MouseReleased(evt);
            }
        });
        txt_tipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipo2ActionPerformed(evt);
            }
        });
        txt_tipo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tipo2KeyReleased(evt);
            }
        });
        Painel_8_Financeiro.add(txt_tipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 330, -1));

        txt_ong1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_ONG_2.png"))); // NOI18N
        txt_ong1.setText("jLabel3");
        Painel_8_Financeiro.add(txt_ong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 370, 240));

        txt_ong2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_ONG_1.png"))); // NOI18N
        txt_ong2.setText("jLabel3");
        Painel_8_Financeiro.add(txt_ong2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 370, 240));

        txt_ong3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_ONG_4.png"))); // NOI18N
        txt_ong3.setText("jLabel3");
        Painel_8_Financeiro.add(txt_ong3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 370, 240));

        txt_ong4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_ONG_3.png"))); // NOI18N
        txt_ong4.setText("jLabel3");
        Painel_8_Financeiro.add(txt_ong4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 370, 240));

        txt_vendas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_Vendas_1.png"))); // NOI18N
        txt_vendas1.setText("jLabel3");
        Painel_8_Financeiro.add(txt_vendas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 370, 240));

        txt_vendas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_Vendas_2.png"))); // NOI18N
        txt_vendas2.setText("jLabel3");
        Painel_8_Financeiro.add(txt_vendas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 370, 240));

        txt_vendas3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_Vendas_3.png"))); // NOI18N
        txt_vendas3.setText("jLabel3");
        Painel_8_Financeiro.add(txt_vendas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 370, 240));

        txt_vendas4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_02_Imagens/Gráfico_Vendas_4.png"))); // NOI18N
        txt_vendas4.setText("jLabel3");
        Painel_8_Financeiro.add(txt_vendas4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 370, 240));

        Panel_Main.add(Painel_8_Financeiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1160, 700));

        getContentPane().add(Panel_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 850));

        setSize(new java.awt.Dimension(1360, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_login1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login1MouseEntered

    }//GEN-LAST:event_btn_login1MouseEntered

    private void btn_login1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login1MouseExited

    }//GEN-LAST:event_btn_login1MouseExited

    private void btn_login1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login1MouseReleased

    }//GEN-LAST:event_btn_login1MouseReleased

    private void btn_login1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login1ActionPerformed

    }//GEN-LAST:event_btn_login1ActionPerformed

    private void btn_login12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login12MouseEntered

    private void btn_login12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login12MouseExited

    private void btn_login12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login12MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login12MouseReleased

    private void btn_login12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login12ActionPerformed

    private void btn_login11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login11MouseEntered

    private void btn_login11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login11MouseExited

    private void btn_login11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login11MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login11MouseReleased

    private void btn_login11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login11ActionPerformed

    }//GEN-LAST:event_btn_login11ActionPerformed

    private void btn_login13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login13MouseEntered

    private void btn_login13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login13MouseExited

    private void btn_login13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login13MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login13MouseReleased

    private void btn_login13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login13ActionPerformed

    }//GEN-LAST:event_btn_login13ActionPerformed

    private void btn_login14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login14MouseEntered

    private void btn_login14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login14MouseExited

    private void btn_login14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login14MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login14MouseReleased

    private void btn_login14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_login14ActionPerformed

    private void btn_minimiza2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza2ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza2ActionPerformed

    private void btn_sair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair2ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair2ActionPerformed

    private void aciona_jovensMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_jovensMouseEntered
        if (!Painel_2_Jovens.isVisible()) {
            aciona_jovens.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_jovens.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_jovensMouseEntered

    private void aciona_jovensMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_jovensMouseExited
        
        if (!Painel_2_Jovens.isVisible()) {
            aciona_jovens.setBackground(new Color(1, 155, 255));
            aciona_jovens.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_aciona_jovensMouseExited

    private void aciona_jovensMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_jovensMouseReleased

    }//GEN-LAST:event_aciona_jovensMouseReleased

    private void aciona_jovensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_jovensActionPerformed
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(true);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        aciona_inicio2.setOpaque(true);
        
        aciona_jovens.setBackground(new Color(5, 24, 64)); // Mesma cor do mouseEntered
        aciona_jovens.setForeground(Color.WHITE);
        aciona_jovens.setOpaque(true);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);
        LimpaCamposJovens();
        CarregaTabelaJovens();
        TotalJovens();

    }//GEN-LAST:event_aciona_jovensActionPerformed

    private void aciona_professoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_professoresMouseEntered
        if (!Painel_3_Professores.isVisible()) {
            aciona_professores.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_professores.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_professoresMouseEntered

    private void aciona_professoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_professoresMouseExited
        if (!Painel_3_Professores.isVisible()) {
            aciona_professores.setBackground(new Color(1, 155, 255));
            aciona_professores.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_aciona_professoresMouseExited

    private void aciona_professoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_professoresMouseReleased

    }//GEN-LAST:event_aciona_professoresMouseReleased

    private void aciona_professoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_professoresActionPerformed
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(true);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        
        aciona_jovens.setBackground(new Color(1, 155, 225));
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(5, 24, 64));
        aciona_professores.setForeground(Color.WHITE);
        aciona_professores.setOpaque(true);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);
        LimpaCamposProfessores();
        CarregaTabelaProfessores();
        TotalProfessores();

    }//GEN-LAST:event_aciona_professoresActionPerformed

    private void aciona_cursosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_cursosMouseEntered
        if (!Painel_4_Cursos.isVisible()) {
            aciona_cursos.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_cursos.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_cursosMouseEntered

    private void aciona_cursosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_cursosMouseExited
        if (!Painel_4_Cursos.isVisible()) {
            aciona_cursos.setBackground(new Color(1, 155, 255));
            aciona_cursos.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_aciona_cursosMouseExited

    private void aciona_cursosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_cursosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aciona_cursosMouseReleased

    private void aciona_cursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_cursosActionPerformed
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(true);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        
        aciona_jovens.setBackground(new Color(1, 155, 225));
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(5, 24, 64));
        aciona_cursos.setForeground(Color.WHITE);
        aciona_cursos.setOpaque(true);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);
        
        LimpaCamposCursos();
        CarregaTabelaCursos();
        TotalCursos();

    }//GEN-LAST:event_aciona_cursosActionPerformed

    private void aciona_produtosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_produtosMouseEntered
        if (!Painel_5_Produtos.isVisible()) {
            aciona_produtos.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_produtos.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_produtosMouseEntered

    private void aciona_produtosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_produtosMouseExited
        if (!Painel_5_Produtos.isVisible()) {
            aciona_produtos.setBackground(new Color(1, 155, 255));
            aciona_produtos.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_aciona_produtosMouseExited

    private void aciona_produtosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_produtosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aciona_produtosMouseReleased

    private void aciona_produtosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_produtosActionPerformed
        
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(true);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        
        aciona_jovens.setBackground(new Color(1, 155, 225));
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(5, 24, 64));
        aciona_produtos.setForeground(Color.WHITE);
        aciona_produtos.setOpaque(true);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);
        
        LimpaCamposProdutos();
        CarregaTabelaProdutos();
        TotalProdutos();
        

    }//GEN-LAST:event_aciona_produtosActionPerformed

    private void aciona_vendasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_vendasMouseEntered
        if (!Painel_6_Vendas.isVisible()) {
            aciona_vendas.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_vendas.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_vendasMouseEntered

    private void aciona_vendasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_vendasMouseExited
        if (!Painel_6_Vendas.isVisible()) {
            aciona_vendas.setBackground(new Color(1, 155, 255));
            aciona_vendas.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_aciona_vendasMouseExited

    private void aciona_vendasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_vendasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aciona_vendasMouseReleased

    private void aciona_vendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_vendasActionPerformed
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(true);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        
        aciona_jovens.setBackground(new Color(1, 155, 225));
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(5, 24, 64));
        aciona_vendas.setForeground(Color.WHITE);
        aciona_vendas.setOpaque(true);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);
        
        LimpaCamposVendas();
        CarregaTabelaVendas();
        TotalVendas();
        

    }//GEN-LAST:event_aciona_vendasActionPerformed

    private void aciona_financeiroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_financeiroMouseEntered
        if (!Painel_8_Financeiro.isVisible()) {
            aciona_financeiro.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_financeiro.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_financeiroMouseEntered

    private void aciona_financeiroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_financeiroMouseExited
        if (!Painel_8_Financeiro.isVisible()) {
            aciona_financeiro.setBackground(new Color(1, 155, 255));
            aciona_financeiro.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_aciona_financeiroMouseExited

    private void aciona_financeiroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_financeiroMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aciona_financeiroMouseReleased

    private void aciona_financeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_financeiroActionPerformed
        txt_tipo2.setVisible(true);
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(true);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        
        aciona_jovens.setBackground(new Color(1, 155, 225));
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(5, 24, 64));
        aciona_financeiro.setForeground(Color.WHITE);
        aciona_financeiro.setOpaque(true);
        LimpaCamposFinanceiro();

    }//GEN-LAST:event_aciona_financeiroActionPerformed

    private void btn_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarActionPerformed
        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)

        mod1.setMatricula(txt_matricula.getText().toString());
        mod1.setRg(txt_rg.getText());
        mod1.setStatus(txt_status.getSelectedItem().toString());
        mod1.setData(txt_data.getText());
        mod1.setCurso(txt_curso.getSelectedItem().toString());
        mod1.setResponsavel(txt_responsavel.getText());
        
        mod1.setNome(txt_nome.getText());
        mod1.setDatanascimento(txt_nascimento.getText());
        mod1.setEmail(txt_email.getText());
        mod1.setCpf(txt_cpf.getText());
        mod1.setGenero(txt_genero.getSelectedItem().toString());
        mod1.setObservacoes(txt_observacoes.getText());
        
        cod1.update(mod1, matricula);        
        CarregaTabelaJovens();
        TotalJovens();
        LimpaCamposJovens();
    }//GEN-LAST:event_btn_alterarActionPerformed

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        insertDatabaseJovens();
        TotalJovens();
        CarregaTabelaJovens();
        LimpaCamposJovens();
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        String matricula = txt_matricula.getText().toString();
        cod1.delete(matricula);
        LimpaCamposJovens();
        CarregaTabelaJovens();
        TotalJovens();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_minimiza3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza3ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza3ActionPerformed

    private void btn_sair3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair3ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair3ActionPerformed

    private void btn_cadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrar1ActionPerformed
        insertDatabaseProfessores();
        CarregaTabelaProfessores();
        TotalProfessores();
        LimpaCamposProfessores();
    }//GEN-LAST:event_btn_cadastrar1ActionPerformed

    private void btn_alterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterar1ActionPerformed
        
        String idString = txt_id.getText();        
        mod2.setId(Integer.parseInt(idString));
        mod2.setAtuacao(txt_atuacao.getText());
        mod2.setStatus(txt_status1.getSelectedItem().toString());
        mod2.setFormacao(txt_formacao.getText());
        mod2.setCurso(txt_curso1.getSelectedItem().toString());
        mod2.setHorario(txt_horario.getSelectedItem().toString());
        
        mod2.setNome(txt_nome1.getText());
        mod2.setNascimento(txt_nascimento1.getText());
        mod2.setEmail(txt_email1.getText());
        mod2.setCpf(txt_cpf1.getText());
        mod2.setGenero(txt_genero1.getSelectedItem().toString());
        mod2.setObservacoes(txt_observacao.getText());
        
        int idInt = Integer.parseInt(txt_id.getText());
        cod2.update(mod2, idInt);        
        
        CarregaTabelaProfessores();
        TotalProfessores();
        LimpaCamposProfessores();
    }//GEN-LAST:event_btn_alterar1ActionPerformed

    private void btn_excluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir1ActionPerformed
        
        int idInt = Integer.parseInt(txt_id.getText());
        cod2.delete(idInt);
        LimpaCamposProfessores();
        
        CarregaTabelaProfessores();
        TotalProfessores();
    }//GEN-LAST:event_btn_excluir1ActionPerformed

    private void btn_minimiza4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza4ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza4ActionPerformed

    private void btn_sair4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair4ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair4ActionPerformed

    private void btn_excluir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir2ActionPerformed
        int idInt = Integer.parseInt(txt_id1.getText());
        cod3.delete(idInt);
        LimpaCamposCursos();
        CarregaTabelaCursos();
        
        TotalCursos();

    }//GEN-LAST:event_btn_excluir2ActionPerformed

    private void btn_login3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login3ActionPerformed
        insertDatabaseCursos();
        CarregaTabelaCursos();
        TotalCursos();
        LimpaCamposCursos();
    }//GEN-LAST:event_btn_login3ActionPerformed

    private void btn_alterar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterar2ActionPerformed

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        String idString = txt_id1.getText();        
        mod3.setId(Integer.parseInt(idString));
        mod3.setNome(txt_nome2.getText());
        mod3.setStatus(txt_status2.getSelectedItem().toString());
        mod3.setModalidade(txt_modalidade.getSelectedItem().toString());
        mod3.setProfessor(txt_professor11.getSelectedItem().toString());
        mod3.setDescricao(txt_descricao.getText());
        
        int idInt = Integer.parseInt(txt_id1.getText());
        cod3.update(mod3, idInt);        
        
        CarregaTabelaCursos();
        TotalCursos();
        LimpaCamposCursos();
    }//GEN-LAST:event_btn_alterar2ActionPerformed

    private void btn_minimiza5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza5ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza5ActionPerformed

    private void btn_sair5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair5ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair5ActionPerformed

    private void txt_producaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_producaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_producaoActionPerformed

    private void btn_login4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login4ActionPerformed
        
        insertDatabaseProdutos();
        TotalProdutos();
        CarregaTabelaProdutos();
        LimpaCamposProdutos();
    }//GEN-LAST:event_btn_login4ActionPerformed

    private void btn_alterar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterar3ActionPerformed

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        String skuString = txt_id2.getText();        
        mod4.setId(Integer.parseInt(skuString));
        mod4.setNome(txt_nome3.getText());
        
        mod4.setStatus(txt_status3.getSelectedItem().toString());
        mod4.setTipo(txt_tipo.getSelectedItem().toString());
        mod4.setEtaria(txt_etaria.getSelectedItem().toString());
        String materiais = String.join(",", txt_materiais.getSelectedValuesList());
        mod4.setMateriais(materiais);
        
        mod4.setPreco(new BigDecimal(txt_preco.getText()));
        
        mod4.setCusto(new BigDecimal(txt_custo.getText()));
        mod4.setEstoque(Integer.parseInt(txt_estoque.getText()));
        mod4.setProducao(txt_producao.getText());
        
        int idInt = Integer.parseInt(txt_id2.getText());
        cod4.update(mod4, idInt);        
        CarregaTabelaProdutos();
        TotalProdutos();
        LimpaCamposProdutos();
    }//GEN-LAST:event_btn_alterar3ActionPerformed

    private void btn_excluir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir3ActionPerformed
        int skuInt = Integer.parseInt(txt_id2.getText());
        cod4.delete(skuInt);
        LimpaCamposProdutos();
        CarregaTabelaProdutos();
        
        TotalProdutos();
    }//GEN-LAST:event_btn_excluir3ActionPerformed

    private void btn_minimiza6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza6ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza6ActionPerformed

    private void btn_sair6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair6ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair6ActionPerformed

    private void btn_login5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login5ActionPerformed
        
        insertDatabaseVendas();
        CarregaTabelaVendas();
        LimpaCamposVendas();
        TotalVendas();

    }//GEN-LAST:event_btn_login5ActionPerformed

    private void txt_descontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descontoActionPerformed

    private void btn_alterar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterar4ActionPerformed

        // Atribui os valores dos campos do formulário aos atributos do modelo (ModelPerson)
        mod5.setPedido(txt_pedido.getText().toString());
        mod5.setData(txt_data1.getText());
        mod5.setHora(txt_hora.getText());
        
        mod5.setNome(txt_nome4.getText());
        mod5.setCpf(txt_cpf2.getText());
        mod5.setEmail(txt_email2.getText());
        mod5.setTelefone(txt_telefone.getText());
        
        mod5.setPagamento(txt_pagamento.getSelectedItem().toString());
        
        mod5.setModo(txt_modo.getSelectedItem().toString());
        mod5.setPrazo(txt_prazo.getText());
        mod5.setRua(txt_rua.getText());
        mod5.setNumero(Integer.parseInt(txt_numero.getText()));
        mod5.setCep(txt_cep.getText());
        mod5.setBairro(txt_bairro.getText());
        mod5.setCidade(txt_cidade.getText());
        
        mod5.setProduto(txt_produto.getSelectedItem().toString());
        mod5.setQuantidade(Integer.parseInt(txt_quantidade.getText()));
        mod5.setPreco(new BigDecimal(txt_preco1.getText()));
        mod5.setDesconto(Integer.parseInt(txt_desconto.getText()));
        
        String valorPedido = txt_pedido.getText();
        cod5.update(mod5, valorPedido);
        CarregaTabelaVendas();
        
        TotalVendas();
        LimpaCamposVendas();
    }//GEN-LAST:event_btn_alterar4ActionPerformed

    private void btn_excluir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir4ActionPerformed
        String pedido = txt_pedido.getText().toString();
        cod5.delete(pedido);
        
        LimpaCamposVendas();
        CarregaTabelaVendas();
        TotalVendas();
    }//GEN-LAST:event_btn_excluir4ActionPerformed

    private void btn_minimiza7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza7ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza7ActionPerformed

    private void btn_sair7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair7ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair7ActionPerformed

    private void btn_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PDFActionPerformed
        GeradorPDF gerador = new GeradorPDF();
        if ("Jovens".equals(txt_tipo1.getSelectedItem())) {
            gerador.gerarPDF_Jovens();
        } else if ("Professores".equals(txt_tipo1.getSelectedItem())) {
            gerador.gerarPDF_Professores();
        } else if ("Cursos".equals(txt_tipo1.getSelectedItem())) {
            gerador.gerarPDF_Cursos();
        } else if ("Produtos".equals(txt_tipo1.getSelectedItem())) {
            gerador.gerarPDF_Produtos();
        } else if ("Vendas".equals(txt_tipo1.getSelectedItem())) {
            gerador.gerarPDF_Vendas();
        } else {
            JOptionPane.showMessageDialog(null, "Não selecionado nenhum relatório");
        }

    }//GEN-LAST:event_btn_PDFActionPerformed

    private void txt_tipo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo1MouseClicked

    }//GEN-LAST:event_txt_tipo1MouseClicked

    private void txt_tipo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo1MouseEntered

    }//GEN-LAST:event_txt_tipo1MouseEntered

    private void txt_tipo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo1MouseExited

    }//GEN-LAST:event_txt_tipo1MouseExited

    private void txt_tipo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipo1MousePressed

    private void txt_tipo1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo1MouseReleased

    }//GEN-LAST:event_txt_tipo1MouseReleased

    private void txt_tipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipo1ActionPerformed
        if ("Jovens".equals(txt_tipo1.getSelectedItem())) {
            Relatório_Jovens();
            custom.customizeHeader(tabela_jovens1);
            //custom.customizeCells(tabela_jovens);
            ajustecolunas.ajustarLargura(tabela_jovens1);
            Painel_base.setVisible(false);
            Painel_Jovens.setVisible(true);
            Painel_Professores.setVisible(false);
            Painel_Cursos.setVisible(false);
            Painel_Produtos.setVisible(false);
            Painel_Vendas.setVisible(false);
        } else if ("Professores".equals(txt_tipo1.getSelectedItem())) {
            Relatório_Professores();
            custom.customizeHeader(tabela_professores1);
            ajustecolunas.ajustarLargura(tabela_professores1);
            Painel_base.setVisible(false);
            Painel_Jovens.setVisible(false);
            Painel_Professores.setVisible(true);
            Painel_Cursos.setVisible(false);
            Painel_Produtos.setVisible(false);
            Painel_Vendas.setVisible(false);
        } else if ("Cursos".equals(txt_tipo1.getSelectedItem())) {
            Relatório_Cursos();
            custom.customizeHeader(tabela_cursos1);
            ajustecolunas.ajustarLargura(tabela_cursos1);
            Painel_base.setVisible(false);
            Painel_Jovens.setVisible(false);
            Painel_Professores.setVisible(false);
            Painel_Cursos.setVisible(true);
            Painel_Produtos.setVisible(false);
            Painel_Vendas.setVisible(false);
        } else if ("Produtos".equals(txt_tipo1.getSelectedItem())) {
            Relatório_Produtos();
            custom.customizeHeader(tabela_produtos1);
            ajustecolunas.ajustarLargura(tabela_produtos1);
            Painel_base.setVisible(false);
            Painel_Jovens.setVisible(false);
            Painel_Professores.setVisible(false);
            Painel_Cursos.setVisible(false);
            Painel_Produtos.setVisible(true);
            Painel_Vendas.setVisible(false);
        } else if ("Vendas".equals(txt_tipo1.getSelectedItem())) {
            Relatório_Vendas();
            custom.customizeHeader(tabela_vendas1);
            ajustecolunas.ajustarLargura(tabela_vendas1);
            Painel_base.setVisible(false);
            Painel_Jovens.setVisible(false);
            Painel_Professores.setVisible(false);
            Painel_Cursos.setVisible(false);
            Painel_Produtos.setVisible(false);
            Painel_Vendas.setVisible(true);
            
        } else {
            Painel_base.setVisible(false);
            Painel_Jovens.setVisible(false);
            Painel_Professores.setVisible(false);
            Painel_Cursos.setVisible(false);
            Painel_Produtos.setVisible(false);
            Painel_Vendas.setVisible(false);
        }
    }//GEN-LAST:event_txt_tipo1ActionPerformed

    private void txt_tipo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tipo1KeyReleased

    }//GEN-LAST:event_txt_tipo1KeyReleased

    private void btn_minimiza8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza8ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza8ActionPerformed

    private void btn_sair8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair8ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair8ActionPerformed

    private void btn_minimiza9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimiza9ActionPerformed
        setState(this.ICONIFIED);
    }//GEN-LAST:event_btn_minimiza9ActionPerformed

    private void btn_sair9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sair9ActionPerformed
        dispose();
    }//GEN-LAST:event_btn_sair9ActionPerformed

    private void txt_tipo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_tipo2ItemStateChanged

    }//GEN-LAST:event_txt_tipo2ItemStateChanged

    private void txt_tipo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo2MouseClicked

    }//GEN-LAST:event_txt_tipo2MouseClicked

    private void txt_tipo2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo2MouseEntered
        //txt_tipo.setBackground(new Color(5, 24, 64)); // RGB(5, 24, 64)
        //txt_tipo.setForeground(Color.WHITE); // Cor da fonte branca
    }//GEN-LAST:event_txt_tipo2MouseEntered

    private void txt_tipo2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo2MouseExited
        //txt_tipo.setBackground(new Color(5, 24, 64)); // RGB(5, 24, 64)
        //txt_tipo.setForeground(Color.WHITE); // Cor da fonte branca
    }//GEN-LAST:event_txt_tipo2MouseExited

    private void txt_tipo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo2MousePressed

    }//GEN-LAST:event_txt_tipo2MousePressed

    private void txt_tipo2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tipo2MouseReleased

    }//GEN-LAST:event_txt_tipo2MouseReleased

    private void txt_tipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipo2ActionPerformed
        if ("ONG".equals(txt_tipo2.getSelectedItem())) {
            txt_vendas1.setVisible(false);
            txt_vendas2.setVisible(false);
            txt_vendas3.setVisible(false);
            txt_vendas4.setVisible(false);
            
            txt_ong1.setVisible(true);
            txt_ong2.setVisible(true);
            txt_ong3.setVisible(true);
            txt_ong4.setVisible(true);
        } else if ("Vendas".equals(txt_tipo2.getSelectedItem())) {
            txt_vendas1.setVisible(true);
            txt_vendas2.setVisible(true);
            txt_vendas3.setVisible(true);
            txt_vendas4.setVisible(true);
            
            txt_ong1.setVisible(false);
            txt_ong2.setVisible(false);
            txt_ong3.setVisible(false);
            txt_ong4.setVisible(false);
        } else {
            txt_vendas1.setVisible(false);
            txt_vendas2.setVisible(false);
            txt_vendas3.setVisible(false);
            txt_vendas4.setVisible(false);
            
            txt_ong1.setVisible(false);
            txt_ong2.setVisible(false);
            txt_ong3.setVisible(false);
            txt_ong4.setVisible(false);
            
        }
    }//GEN-LAST:event_txt_tipo2ActionPerformed

    private void txt_tipo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tipo2KeyReleased

    }//GEN-LAST:event_txt_tipo2KeyReleased

    private void aciona_inicio2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_inicio2MouseEntered
        btn_PDF.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_PDF.setForeground(Color.WHITE);
        

    }//GEN-LAST:event_aciona_inicio2MouseEntered

    private void aciona_inicio2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_inicio2MouseExited
        btn_PDF.setBackground(new Color(1, 155, 255));
        btn_PDF.setForeground(Color.BLACK);

    }//GEN-LAST:event_aciona_inicio2MouseExited

    private void aciona_inicio2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_inicio2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aciona_inicio2MouseReleased

    private void aciona_inicio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_inicio2ActionPerformed
        Painel_1_Menu.setVisible(true);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(false);
        Painel_8_Financeiro.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(5, 24, 64));
        aciona_inicio2.setForeground(Color.WHITE);
        aciona_inicio2.setOpaque(true);
        
        aciona_jovens.setBackground(new Color(1, 155, 225)); // Mesma cor do mouseEntered
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(1, 155, 225));
        aciona_relatorios.setForeground(Color.BLACK);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);

    }//GEN-LAST:event_aciona_inicio2ActionPerformed

    private void aciona_relatoriosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_relatoriosMouseEntered
        if (!Painel_7_Relatorios.isVisible()) {
            aciona_relatorios.setBackground(new Color(5, 24, 64)); // Cor hover
            aciona_relatorios.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_aciona_relatoriosMouseEntered

    private void aciona_relatoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_relatoriosMouseExited
        if (!Painel_7_Relatorios.isVisible()) {
            aciona_relatorios.setBackground(new Color(1, 155, 255));
            aciona_relatorios.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_aciona_relatoriosMouseExited

    private void aciona_relatoriosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aciona_relatoriosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aciona_relatoriosMouseReleased

    private void aciona_relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aciona_relatoriosActionPerformed
        Painel_1_Menu.setVisible(false);
        Painel_2_Jovens.setVisible(false);
        Painel_3_Professores.setVisible(false);
        Painel_4_Cursos.setVisible(false);
        Painel_5_Produtos.setVisible(false);
        Painel_6_Vendas.setVisible(false);
        Painel_7_Relatorios.setVisible(true);
        Painel_8_Financeiro.setVisible(false);
        
        Painel_base.setVisible(false);
        Painel_Jovens.setVisible(false);
        Painel_Professores.setVisible(false);
        Painel_Cursos.setVisible(false);
        Painel_Produtos.setVisible(false);
        Painel_Vendas.setVisible(false);
        
        aciona_inicio2.setBackground(new java.awt.Color(1, 155, 225));
        aciona_inicio2.setForeground(Color.BLACK);
        
        aciona_jovens.setBackground(new Color(1, 155, 225));
        aciona_jovens.setForeground(Color.BLACK);
        
        aciona_professores.setBackground(new java.awt.Color(1, 155, 225));
        aciona_professores.setForeground(Color.BLACK);
        
        aciona_cursos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_cursos.setForeground(Color.BLACK);
        
        aciona_produtos.setBackground(new java.awt.Color(1, 155, 225));
        aciona_produtos.setForeground(Color.BLACK);
        
        aciona_vendas.setBackground(new java.awt.Color(1, 155, 225));
        aciona_vendas.setForeground(Color.BLACK);
        
        aciona_relatorios.setBackground(new java.awt.Color(5, 24, 64));
        aciona_relatorios.setForeground(Color.WHITE);
        aciona_relatorios.setOpaque(true);
        
        aciona_financeiro.setBackground(new java.awt.Color(1, 155, 225));
        aciona_financeiro.setForeground(Color.BLACK);
        LimpaCamposRelatorios();
        

    }//GEN-LAST:event_aciona_relatoriosActionPerformed

    private void txt_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cursoActionPerformed
        

    }//GEN-LAST:event_txt_cursoActionPerformed

    private void btn_PDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PDFMouseEntered
        btn_PDF.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_PDF.setForeground(Color.WHITE);

    }//GEN-LAST:event_btn_PDFMouseEntered

    private void btn_PDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PDFMouseExited
        btn_PDF.setBackground(new Color(1, 155, 255));
        btn_PDF.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_PDFMouseExited

    private void btn_cadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrarMouseEntered
        
        btn_cadastrar.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_cadastrar.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_cadastrarMouseEntered

    private void btn_cadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrarMouseExited
        btn_cadastrar.setBackground(new Color(1, 155, 255));
        btn_cadastrar.setForeground(Color.BLACK);

    }//GEN-LAST:event_btn_cadastrarMouseExited

    private void btn_alterarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterarMouseEntered
        btn_alterar.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_alterar.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_alterarMouseEntered

    private void btn_alterarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterarMouseExited
        btn_alterar.setBackground(new Color(1, 155, 255));
        btn_alterar.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_alterarMouseExited

    private void btn_excluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseEntered
        btn_excluir.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_excluir.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_excluirMouseEntered

    private void btn_excluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseExited
        btn_excluir.setBackground(new Color(1, 155, 255));
        btn_excluir.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_excluirMouseExited

    private void btn_cadastrar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrar1MouseEntered
        btn_cadastrar1.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_cadastrar1.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_cadastrar1MouseEntered

    private void btn_cadastrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrar1MouseExited
        btn_cadastrar1.setBackground(new Color(1, 155, 255));
        btn_cadastrar1.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_cadastrar1MouseExited

    private void btn_alterar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar1MouseEntered
        btn_alterar1.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_alterar1.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_alterar1MouseEntered

    private void btn_alterar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar1MouseExited
        btn_alterar1.setBackground(new Color(1, 155, 255));
        btn_alterar1.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_alterar1MouseExited

    private void btn_excluir1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir1MouseEntered
        btn_excluir1.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_excluir1.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_excluir1MouseEntered

    private void btn_excluir1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir1MouseExited
        btn_excluir1.setBackground(new Color(1, 155, 255));
        btn_excluir1.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_excluir1MouseExited

    private void btn_login3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login3MouseEntered
        btn_login3.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_login3.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_login3MouseEntered

    private void btn_login3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login3MouseExited
        btn_login3.setBackground(new Color(1, 155, 255));
        btn_login3.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_login3MouseExited

    private void btn_alterar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar2MouseEntered
        btn_alterar2.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_alterar2.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_alterar2MouseEntered

    private void btn_alterar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar2MouseExited
        btn_alterar2.setBackground(new Color(1, 155, 255));
        btn_alterar2.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_alterar2MouseExited

    private void btn_excluir2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir2MouseEntered
        btn_excluir2.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_excluir2.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_excluir2MouseEntered

    private void btn_excluir2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir2MouseExited
        btn_excluir2.setBackground(new Color(1, 155, 255));
        btn_excluir2.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_excluir2MouseExited

    private void btn_login4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login4MouseEntered
        btn_login4.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_login4.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_login4MouseEntered

    private void btn_login4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login4MouseExited
        btn_login4.setBackground(new Color(1, 155, 255));
        btn_login4.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_login4MouseExited

    private void btn_alterar3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar3MouseEntered
        btn_alterar3.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_alterar3.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_alterar3MouseEntered

    private void btn_alterar3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar3MouseExited
        btn_alterar3.setBackground(new Color(1, 155, 255));
        btn_alterar3.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_alterar3MouseExited

    private void btn_excluir3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir3MouseEntered
        btn_excluir3.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_excluir3.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_excluir3MouseEntered

    private void btn_excluir3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir3MouseExited
        btn_excluir3.setBackground(new Color(1, 155, 255));
        btn_excluir3.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_excluir3MouseExited

    private void btn_login5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login5MouseEntered
        btn_login5.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_login5.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_login5MouseEntered

    private void btn_login5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_login5MouseExited
        btn_login5.setBackground(new Color(1, 155, 255));
        btn_login5.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_login5MouseExited

    private void btn_alterar4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar4MouseEntered
        btn_alterar4.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_alterar4.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_alterar4MouseEntered

    private void btn_alterar4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alterar4MouseExited
        btn_alterar4.setBackground(new Color(1, 155, 255));
        btn_alterar4.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_alterar4MouseExited

    private void btn_excluir4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir4MouseEntered
        btn_excluir4.setBackground(new Color(5, 24, 64)); // Cor hover
        btn_excluir4.setForeground(Color.WHITE);
    }//GEN-LAST:event_btn_excluir4MouseEntered

    private void btn_excluir4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluir4MouseExited
        btn_excluir4.setBackground(new Color(1, 155, 255));
        btn_excluir4.setForeground(Color.BLACK);
    }//GEN-LAST:event_btn_excluir4MouseExited
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(_02_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(_02_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(_02_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(_02_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new _02_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Painel_0_Lateral;
    private javax.swing.JPanel Painel_1_Menu;
    private javax.swing.JPanel Painel_2_Jovens;
    private javax.swing.JPanel Painel_3_Professores;
    private javax.swing.JPanel Painel_4_Cursos;
    private javax.swing.JPanel Painel_5_Produtos;
    private javax.swing.JPanel Painel_6_Vendas;
    private javax.swing.JPanel Painel_7_Relatorios;
    private javax.swing.JPanel Painel_8_Financeiro;
    private javax.swing.JPanel Painel_Cursos;
    private javax.swing.JPanel Painel_Jovens;
    private javax.swing.JPanel Painel_Produtos;
    private javax.swing.JPanel Painel_Professores;
    private javax.swing.JPanel Painel_Vendas;
    private javax.swing.JPanel Painel_base;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JButton aciona_cursos;
    private javax.swing.JButton aciona_financeiro;
    private javax.swing.JButton aciona_inicio2;
    private javax.swing.JButton aciona_jovens;
    private javax.swing.JButton aciona_produtos;
    private javax.swing.JButton aciona_professores;
    private javax.swing.JButton aciona_relatorios;
    private javax.swing.JButton aciona_vendas;
    private javax.swing.JButton btn_PDF;
    private javax.swing.JButton btn_alterar;
    private javax.swing.JButton btn_alterar1;
    private javax.swing.JButton btn_alterar2;
    private javax.swing.JButton btn_alterar3;
    private javax.swing.JButton btn_alterar4;
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_cadastrar1;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_excluir1;
    private javax.swing.JButton btn_excluir2;
    private javax.swing.JButton btn_excluir3;
    private javax.swing.JButton btn_excluir4;
    private javax.swing.JButton btn_login3;
    private javax.swing.JButton btn_login4;
    private javax.swing.JButton btn_login5;
    private javax.swing.JButton btn_minimiza2;
    private javax.swing.JButton btn_minimiza3;
    private javax.swing.JButton btn_minimiza4;
    private javax.swing.JButton btn_minimiza5;
    private javax.swing.JButton btn_minimiza6;
    private javax.swing.JButton btn_minimiza7;
    private javax.swing.JButton btn_minimiza8;
    private javax.swing.JButton btn_minimiza9;
    private javax.swing.JButton btn_sair2;
    private javax.swing.JButton btn_sair3;
    private javax.swing.JButton btn_sair4;
    private javax.swing.JButton btn_sair5;
    private javax.swing.JButton btn_sair6;
    private javax.swing.JButton btn_sair7;
    private javax.swing.JButton btn_sair8;
    private javax.swing.JButton btn_sair9;
    private javax.swing.JLabel foto_user;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable tabela_cursos;
    private javax.swing.JTable tabela_cursos1;
    private javax.swing.JTable tabela_jovens;
    private javax.swing.JTable tabela_jovens1;
    private javax.swing.JTable tabela_produtos;
    private javax.swing.JTable tabela_produtos1;
    private javax.swing.JTable tabela_professores;
    private javax.swing.JTable tabela_professores1;
    private javax.swing.JTable tabela_vendas;
    private javax.swing.JTable tabela_vendas1;
    private javax.swing.JTextField txt_atuacao;
    private javax.swing.JTextField txt_bairro;
    private javax.swing.JFormattedTextField txt_cep;
    private javax.swing.JTextField txt_cidade;
    private javax.swing.JFormattedTextField txt_cpf;
    private javax.swing.JFormattedTextField txt_cpf1;
    private javax.swing.JFormattedTextField txt_cpf2;
    private javax.swing.JComboBox<String> txt_curso;
    private javax.swing.JComboBox<String> txt_curso1;
    private javax.swing.JTextField txt_custo;
    private javax.swing.JFormattedTextField txt_data;
    private javax.swing.JFormattedTextField txt_data1;
    private javax.swing.JTextField txt_desconto;
    private javax.swing.JTextPane txt_descricao;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_email1;
    private javax.swing.JTextField txt_email2;
    private javax.swing.JTextField txt_estoque;
    private javax.swing.JComboBox<String> txt_etaria;
    private javax.swing.JTextField txt_formacao;
    private javax.swing.JComboBox<String> txt_genero;
    private javax.swing.JComboBox<String> txt_genero1;
    private javax.swing.JLabel txt_grafico;
    private javax.swing.JFormattedTextField txt_hora;
    private javax.swing.JComboBox<String> txt_horario;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_id1;
    private javax.swing.JTextField txt_id2;
    private javax.swing.JList<String> txt_materiais;
    private javax.swing.JTextField txt_matricula;
    private javax.swing.JComboBox<String> txt_modalidade;
    private javax.swing.JComboBox<String> txt_modo;
    private javax.swing.JFormattedTextField txt_nascimento;
    private javax.swing.JFormattedTextField txt_nascimento1;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_nome1;
    private javax.swing.JTextField txt_nome2;
    private javax.swing.JTextField txt_nome3;
    private javax.swing.JTextField txt_nome4;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_observacao;
    private javax.swing.JTextField txt_observacoes;
    private javax.swing.JLabel txt_ong1;
    private javax.swing.JLabel txt_ong2;
    private javax.swing.JLabel txt_ong3;
    private javax.swing.JLabel txt_ong4;
    private javax.swing.JComboBox<String> txt_pagamento;
    private javax.swing.JTextField txt_pedido;
    private javax.swing.JFormattedTextField txt_prazo;
    private javax.swing.JTextField txt_preco;
    private javax.swing.JTextField txt_preco1;
    private javax.swing.JFormattedTextField txt_producao;
    private javax.swing.JComboBox<String> txt_produto;
    private javax.swing.JComboBox<String> txt_professor11;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_responsavel;
    private javax.swing.JFormattedTextField txt_rg;
    private javax.swing.JTextField txt_rua;
    private javax.swing.JComboBox<String> txt_status;
    private javax.swing.JComboBox<String> txt_status1;
    private javax.swing.JComboBox<String> txt_status2;
    private javax.swing.JComboBox<String> txt_status3;
    private javax.swing.JFormattedTextField txt_telefone;
    private javax.swing.JComboBox<String> txt_tipo;
    private javax.swing.JComboBox<String> txt_tipo1;
    private javax.swing.JComboBox<String> txt_tipo2;
    private javax.swing.JLabel txt_total;
    private javax.swing.JLabel txt_total1;
    private javax.swing.JLabel txt_total2;
    private javax.swing.JLabel txt_total3;
    private javax.swing.JLabel txt_total4;
    private javax.swing.JLabel txt_vendas1;
    private javax.swing.JLabel txt_vendas2;
    private javax.swing.JLabel txt_vendas3;
    private javax.swing.JLabel txt_vendas4;
    // End of variables declaration//GEN-END:variables

}
