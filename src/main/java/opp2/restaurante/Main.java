package opp2.restaurante;

import opp2.persistencia.EnDataBaseRegistroPagos;
import opp2.persistencia.EnDiscoRegistroPagos;

public class Main {
    public static void main(String[] args) {
        //Escritura en disco. Crea archivo si no existe en el Escritorio
        //Si no existe escribe en la ultima linea
        String path = "C:/Users/Tomas/Desktop/Pruebas-Costos-Restaurante.txt";
        Mesa mesa = new Mesa(1, new EnDiscoRegistroPagos(path));
        Visa tarjeta = new Visa("1233445532321111", "Tomas");
        Comida comida = new Comida("Burger", 45.5F);
        Bebida bebida = new Bebida("Coca-Cola", 20F);

        mesa.agregarComidas(comida, 3);
        mesa.agregarBebidas(bebida, 2);

        float precioTotal = mesa.precioTotal(tarjeta, Propina.MEDIO);

        //Escritura en BD.
        mesa.setRegistroCosto(new EnDataBaseRegistroPagos());
        precioTotal = mesa.precioTotal(tarjeta, Propina.MEDIO);
    }
}
