package opp2.ejer2;

import opp2.persistencia.EnDataBaseRegistroPagos;
import opp2.persistencia.EnDiscoRegistroPagos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MesaTest {

    @Test
    public void CalcularCostoVisaTest() {
        String path = "C:/Users/Tomas/Desktop/Pruebas-Costos-Restaurante.txt";
        Mesa mesa = new Mesa(2, new EnDiscoRegistroPagos(path));
        Visa tarjeta = new Visa("1233445532321111", "Tomas");
        Comida comida = new Comida("Burger", 45.5F);
        Bebida bebida = new Bebida("Coca-Cola", 20F);

        mesa.agregarComidas(comida, 3);
        mesa.agregarBebidas(bebida, 2);

        float precioTotal = mesa.precioTotal(tarjeta, Propina.MEDIO);

        assertEquals(180.559F, precioTotal);
    }

    @Test
    public void CalcularCostoVisaDataBaseTest() {
        Mesa mesa = new Mesa(2, new EnDataBaseRegistroPagos()); //Se registra en la BD
        Visa tarjeta = new Visa("1233445532321111", "Tomas");
        Comida comida = new Comida("Burger", 45.5F);
        Bebida bebida = new Bebida("Coca-Cola", 20F);

        mesa.agregarComidas(comida, 3);
        mesa.agregarBebidas(bebida, 2);

        float precioTotal = mesa.precioTotal(tarjeta, Propina.MEDIO);

        assertEquals(180.559F, precioTotal);
    }

    @Test
    public void CalcularCostoMastercardTest() {
        String path = "C:/Users/Tomas/Desktop/Pruebas-Costos-Restaurante.txt";
        Mesa mesa = new Mesa(2, new EnDiscoRegistroPagos(path));
        Mastercard tarjeta = new Mastercard("1233445532321111", "Tomas");
        Comida comida = new Comida("Burger", 45.5F);
        Bebida bebida = new Bebida("Coca-Cola", 20F);

        mesa.agregarComidas(comida, 3);
        mesa.agregarBebidas(bebida, 2);

        float precioTotal = mesa.precioTotal(tarjeta, Propina.BAJO);

        assertEquals(177.2454F, precioTotal);
    }

    @Test
    public void CalcularCostoComarcaPlusTest() {
        String path = "C:/Users/Tomas/Desktop/Pruebas-Costos-Restaurante.txt";
        Mesa mesa = new Mesa(2, new EnDiscoRegistroPagos(path));
        ComarcaPlus tarjeta = new ComarcaPlus("1233445532321111", "Tomas");
        Comida comida = new Comida("Burger", 45.5F);
        Bebida bebida = new Bebida("Coca-Cola", 20F);

        mesa.agregarComidas(comida, 3);
        mesa.agregarBebidas(bebida, 2);

        float precioTotal = mesa.precioTotal(tarjeta, Propina.ALTO);

        assertEquals(181.6185F, precioTotal);
    }

    @Test
    public void CalcularCostoViedmaTest() {
        String path = "C:/Users/Tomas/Desktop/Pruebas-Costos-Restaurante.txt";
        Mesa mesa = new Mesa(2, new EnDiscoRegistroPagos(path));
        Viedma tarjeta = new Viedma("1233445532321111", "Tomas");
        Comida comida = new Comida("Burger", 45.5F);
        Bebida bebida = new Bebida("Coca-Cola", 20F);

        mesa.agregarComidas(comida, 3);
        mesa.agregarBebidas(bebida, 2);

        float precioTotal = mesa.precioTotal(tarjeta, Propina.ALTO);

        assertEquals(185.325F, precioTotal);
    }

    @Test
    public void CantidadCaracteresInvalidoTest() {
        var exception = assertThrows(RuntimeException.class, () -> new Visa("21212", "Tomas"));
        assertEquals(Tarjeta.NUMERO_TARJETA_NO_VALIDO, exception.getMessage());
    }

    @Test
    public void CantidadVaciaCaracteresTest() {
        var exception = assertThrows(RuntimeException.class, () -> new Visa("", "Tomas"));
        assertEquals(Tarjeta.NUMERO_VACIO, exception.getMessage());
    }

    @Test
    public void TitularVacioTest() {
        var exception = assertThrows(RuntimeException.class, () -> new Visa("1212121212112123", ""));
        assertEquals(Tarjeta.TITULAR_VACIO, exception.getMessage());
    }

    @Test
    public void PrecioInvalidoTest() {
        var exception = assertThrows(RuntimeException.class, () -> new Comida("Milanesa", -213F));
        assertEquals(Comida.PRECIO_INVALIDO, exception.getMessage());
        exception = assertThrows(RuntimeException.class, () -> new Bebida("Sprite", -232F));
        assertEquals(Bebida.PRECIO_INVALIDO, exception.getMessage());
    }
}
