package opp2.ejer2;

public enum Propina {
    BAJO(0.02F),
    MEDIO(0.03F),
    ALTO(0.05F);

    private final float porcentajeDelTotal;

    Propina(float porcentajeDelTotal) {
        this.porcentajeDelTotal = porcentajeDelTotal;
    }

    public float porcentaje() {
        return porcentajeDelTotal;
    }
}
