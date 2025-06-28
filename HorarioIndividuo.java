import java.util.*;

public class HorarioIndividuo {
    List<HorarioAsignado> asignaciones;
    int fitness;

    public HorarioIndividuo(List<HorarioAsignado> asignaciones) {
        this.asignaciones = asignaciones;
        this.fitness = calcularFitness();
    }

    public int calcularFitness() {
        int score = 0;
        Set<String> ocupados = new HashSet<>();
        for (HorarioAsignado h : asignaciones) {
            String clave = h.dia + ":" + h.hora;
            String docenteKey = clave + ":D:" + h.docente.nombre;
            String salonKey = clave + ":S:" + h.salon.nombre;
            if (ocupados.contains(docenteKey) || ocupados.contains(salonKey)) {
                score -= 10;
            } else {
                ocupados.add(docenteKey);
                ocupados.add(salonKey);
                score += 1;
            }
        }
        return score;
    }
}
