package opp2.restaurante;

public class Bebida {
    public static final String PRECIO_INVALIDO = "El precio no puede ser negativo";
    private String nombre;
    private float precio;

    public Bebida(String nombre, float precio) {
        checkPrecioIsValid(precio);
        this.nombre = nombre;
        this.precio = precio;
    }

    public float precio() {
        return precio;
    }

    private void checkPrecioIsValid(float precio) {
        if (precio < 0) {
            throw new RuntimeException(PRECIO_INVALIDO);
        }
    }
}
