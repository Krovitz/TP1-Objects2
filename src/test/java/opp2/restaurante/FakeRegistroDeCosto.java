package opp2.restaurante;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FakeRegistroDeCosto implements RegistroCosto {
    private String msj;

    @Override
    public void registro(LocalDate fecha, float costoTotal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msj = fecha.format(formatter) + " || " + costoTotal;
    }

    public String Msj() {
        return msj;
    }
}
