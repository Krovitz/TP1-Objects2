package opp2.restaurante;

public class ComarcaPlus extends Tarjeta {
    private final float descuento;

    public ComarcaPlus(String numero, String titular) {
        super(numero, titular);
        this.descuento = 0.02F;
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalComidas) {
        return (totalBebidas + totalComidas) - ((totalBebidas + totalComidas) * descuento);
    }
}
