package opp2.ejer2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int nro;
    private List<Bebida> bebidas;
    private List<Comida> comidas;
    private RegistroCosto registroCosto;

    public Mesa(int nro, RegistroCosto registroPagos) {
        this.nro = nro;
        this.bebidas = new ArrayList<>();
        this.comidas = new ArrayList<>();
        this.registroCosto = registroPagos;
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

    public float precioTotal(Tarjeta tarjeta, Propina propina) {
        float totalConDescuento = tarjeta.aplicarDescuento(totalBebidas(), totalComidas());
        ;
        totalConDescuento += (totalConDescuento * propina.porcentaje());
        registroCosto.registro(LocalDate.now(), totalConDescuento);
        return totalConDescuento;

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
