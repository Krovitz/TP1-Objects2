package opp2.restaurante;

public class Viedma extends Tarjeta {
    public Viedma(String numero, String titular) {
        super(numero, titular);
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalComidas) {
        return totalBebidas + totalComidas;
    }
}
