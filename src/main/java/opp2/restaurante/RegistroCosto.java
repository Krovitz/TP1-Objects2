package opp2.restaurante;

import java.time.LocalDate;

public interface RegistroCosto {
    void registro(LocalDate fecha, float costoTotal);
}
