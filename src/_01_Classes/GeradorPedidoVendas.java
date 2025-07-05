package _01_Classes;

public class GeradorPedidoVendas {
    private static int contador = 0;
    
    public String gerar() {
        contador++;
        return String.format("#%02d", contador);
    }
    
    // Método para sincronizar com o último pedido do banco
    public static void sincronizarContador(int ultimoPedido) {
        contador = ultimoPedido;
    }
}