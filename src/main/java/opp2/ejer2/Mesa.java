package opp2.ejer2;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int nro;
    private List<Bebida> bebidas;
    private List<Comida> comidas;

    public Mesa(int nro) {
        this.nro = nro;
        this.bebidas = new ArrayList<>();
        this.comidas = new ArrayList<>();
    }

    public void agregarBebidas(Bebida bebida, int cantidad) {
        if (cantidad != 0) {
            for (int i = 0; i < cantidad; i++) {
                bebidas.add(bebida);
            }
        } else
            throw new RuntimeException("Cantidad nula de bebidas");
    }

    public void agregarComidas(Comida comida, int cantidad) {
        if (cantidad != 0) {
            for (int i = 0; i < cantidad; i++) {
                comidas.add(comida);
            }
        } else
            throw new RuntimeException("Cantidad nula de comidas");
    }

    public float precioTotalConDescuento(Tarjeta tarjeta, Propina propina) {
        float totalConDescuento = tarjeta.aplicarDescuento(totalBebidas(), totalComidas());
        return totalConDescuento + (totalConDescuento * propina.porcentaje());
    }

    private float totalComidas() {
        return comidas
                .stream()
                .map(Comida::precio)
                .reduce(0f, Float::sum);
    }

    private float totalBebidas() {
        return bebidas
                .stream()
                .map(Bebida::precio)
                .reduce(0f, Float::sum);
    }

}
