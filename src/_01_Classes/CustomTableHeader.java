package _01_Classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class CustomTableHeader {

    // Cores e fontes personalizadas
    private static final Color HEADER_BACKGROUND = new Color(5, 24, 64);
    private static final Color HEADER_FOREGROUND = Color.WHITE;
    private static final Font HEADER_FONT = new Font("Dialog", Font.PLAIN, 18);

    private static final Color CELL_FOREGROUND = new Color(5, 24, 64);
    private static final Font CELL_FONT = new Font("Dialog", Font.PLAIN, 16);
    private static final Color SELECTION_COLOR = new Color(5, 24, 64);

    public static void customizeTable(JTable table) {
        customizeHeader(table);
        customizeCells(table);
    }

    public static void customizeHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBackground(HEADER_BACKGROUND);
                setForeground(HEADER_FOREGROUND);
                setFont(HEADER_FONT);
                setHorizontalAlignment(CENTER);

                return this;
            }
        });
    }

  public static void customizeCells(JTable table) {
    // Habilitar e personalizar as grades da tabela
    table.setShowGrid(true);
    table.setGridColor(new Color(200, 200, 200));
    
    // Definir cores de seleção diretamente na tabela
    table.setSelectionBackground(SELECTION_COLOR);
    table.setSelectionForeground(Color.WHITE);
    
    // Criar um renderizador personalizado para as células
    TableCellRenderer renderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, column);
            
            // Aplicar estilo base
            c.setFont(CELL_FONT);
            c.setForeground(CELL_FOREGROUND);
            
            // Adicionar bordas
            ((JComponent)c).setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 1, table.getGridColor()),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            
            // Manter as cores de seleção padrão da tabela
            if (isSelected) {
                c.setBackground(table.getSelectionBackground());
                c.setForeground(table.getSelectionForeground());
            } else {
                c.setBackground(table.getBackground());
            }
            
            return c;
        }
    };
    
    // Aplicar o renderizador a todas as colunas
    for (int i = 0; i < table.getColumnCount(); i++) {
        table.getColumnModel().getColumn(i).setCellRenderer(renderer);
    }
}
}
