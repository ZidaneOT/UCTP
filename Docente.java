import java.util.List;

public class Docente {
    String nombre;
    int horasDisponibles;
    List<String> cursosAsignables;

    public Docente(String nombre, int horasDisponibles, List<String> cursosAsignables) {
        this.nombre = nombre;
        this.horasDisponibles = horasDisponibles;
        this.cursosAsignables = cursosAsignables;
    }

    public boolean puedeEnsenar(String curso) {
        return cursosAsignables.contains(curso);
    }
}
