package _01_Classes;

import java.util.Random;

public class GeradorMatriculaJovens {
    private final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String NUMEROS = "0123456789";
    private final Random random = new Random();

    public String gerar() {
        return String.format("%s%d%d%s%s%d%d",
            randomLetra(), randomNumero(), randomNumero(),
            randomLetra(), randomLetra(), randomNumero(), randomNumero());
    }

    private char randomLetra() {
        return LETRAS.charAt(random.nextInt(LETRAS.length()));
    }

    private int randomNumero() {
        return random.nextInt(10);
    }
}