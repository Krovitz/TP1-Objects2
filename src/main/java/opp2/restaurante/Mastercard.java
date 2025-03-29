package opp2.restaurante;

public class Mastercard extends Tarjeta {
    private final float descuento;

    public Mastercard(String numero, String titular) {
        super(numero, titular);
        this.descuento = 0.02F;
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalComidas) {
        return totalBebidas + (totalComidas - (totalComidas * descuento));
    }
}
