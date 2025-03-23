package opp2.ejer2;

public class Viedma extends Tarjeta {
    public Viedma(String numero, String titular) {
        super(numero, titular);
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalComidas) {
        return totalBebidas + totalComidas;
    }
}
