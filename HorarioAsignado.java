public class HorarioAsignado {
    Curso curso;
    Docente docente;
    Salon salon;
    String dia;
    int hora;

    public HorarioAsignado(Curso curso, Docente docente, Salon salon, String dia, int hora) {
        this.curso = curso;
        this.docente = docente;
        this.salon = salon;
        this.dia = dia;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return dia + " - " + hora + ":00h | " + curso.nombre + " en " + salon.nombre + " por " + docente.nombre;
    }
}
