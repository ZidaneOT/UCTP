public class Curso {
    String nombre;
    int horasSemana;
    String tipo; // "teorico" o "laboratorio"

    public Curso(String nombre, int horasSemana, String tipo) {
        this.nombre = nombre;
        this.horasSemana = horasSemana;
        this.tipo = tipo;
    }
}
