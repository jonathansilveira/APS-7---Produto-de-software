package _01_Classes;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {

    // Variável para armazenar o caminho
    private String path;

    // Lista de linhas (dados da tabela)
    private ArrayList linhas = null;

    // Array de colunas (nomes das colunas da tabela)
    private String[] colunas = null;

    // Construtor que recebe as linhas e as colunas para inicializar a tabela
    public ModeloTabela(ArrayList lin, String[] col) {
        setLinhas(lin);  // Inicializa as linhas com os dados fornecidos
        setColunas(col); // Inicializa as colunas com os nomes fornecidos
    }

    // Construtor padrão 
    public ModeloTabela() {
        throw new UnsupportedOperationException("Not supported yet."); // Exceção caso este construtor seja utilizado
    }

    // Retorna a lista de linhas
    public ArrayList getLinhas() {
        return linhas; // Retorna as linhas (dados) da tabela
    }

    // Define a lista de linhas
    public void setLinhas(ArrayList dados) {
        linhas = dados; // Define as linhas (dados) para a tabela
    }

    // Retorna o array de colunas
    public String[] getColunas() {
        return colunas; // Retorna os nomes das colunas da tabela
    }

    // Define os nomes das colunas
    public void setColunas(String[] nomes) {
        colunas = nomes; // Define os nomes das colunas para a tabela
    }

    // Retorna o número de colunas
    @Override
    public int getColumnCount() {
        return colunas.length; // Retorna o número de colunas (baseado no tamanho do array colunas)
    }

    // Retorna o número de linhas
    @Override
    public int getRowCount() {
        return linhas.size(); // Retorna o número de linhas (tamanho da lista linhas)
    }

    // Retorna o nome da coluna especificada pelo índice
    @Override
    public String getColumnName(int numCol) {
        return colunas[numCol]; // Retorna o nome da coluna com base no índice numCol
    }

    // Retorna o valor de uma célula específica, com base na linha e coluna
    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin); // Obtém a linha baseada no índice numLin
        return linha[numCol]; // Retorna o valor da célula na linha e coluna especificadas
    }
}
