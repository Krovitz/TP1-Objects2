package opp2.restaurante;

public abstract class Tarjeta {
    public static final String NUMERO_VACIO = "El número no puede ser vacío";
    public static final String NUMERO_TARJETA_NO_VALIDO = "El número debe tener 16 dígitos";
    public static final String TITULAR_VACIO = "El titular no puede ser vacío";
    private String numero;
    private String titular;


    public Tarjeta(String numero, String titular) {
        checkNumeroValido(numero);
        checkTituloNotBlank(titular);
        this.numero = numero;
        this.titular = titular;

    }

    private void checkTituloNotBlank(String titular) {
        if (titular.isBlank()) {
            throw new RuntimeException(TITULAR_VACIO);
        }
    }

    private void checkNumeroValido(String numero) {
        if (numero.isBlank()) {
            throw new RuntimeException(NUMERO_VACIO);
        }
        if (numero.length() != 16) {
            throw new RuntimeException(NUMERO_TARJETA_NO_VALIDO);
        }
    }

    public abstract float aplicarDescuento(float totalBebidas, float totalComidas);
}
