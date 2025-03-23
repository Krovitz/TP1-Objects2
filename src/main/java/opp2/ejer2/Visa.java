package opp2.ejer2;

public class Visa extends Tarjeta {
    private final float descuento;

    public Visa(String numero, String titular) {
        super(numero, titular);
        this.descuento = 0.03F;
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalComidas) {
        return (totalBebidas - (totalBebidas * descuento)) + totalComidas;
    }

}
