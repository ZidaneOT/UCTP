import java.util.*;

public class Main {
    static String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
    static int[] horas = {8, 9, 10, 11, 14, 15, 16};

    public static void main(String[] args) {
        List<Curso> cursos = Arrays.asList(
            new Curso("Matemáticas", 3, "teorico"),
            new Curso("Física", 2, "laboratorio")
        );

        List<Docente> docentes = Arrays.asList(
            new Docente("Profe Ana", 5, Arrays.asList("Matemáticas")),
            new Docente("Profe Luis", 4, Arrays.asList("Física"))
        );

        List<Salon> salones = Arrays.asList(
            new Salon("Aula 101", 30, false),
            new Salon("Lab Físico", 25, true)
        );

        // Algoritmo Genético Simple
        List<HorarioIndividuo> poblacion = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            poblacion.add(crearHorarioAleatorio(cursos, docentes, salones));
        }

        for (int generacion = 0; generacion < 50; generacion++) {
            poblacion.sort(Comparator.comparingInt(h -> -h.fitness));
            List<HorarioIndividuo> nuevaGen = new ArrayList<>();
            nuevaGen.add(poblacion.get(0)); // elitismo
            for (int i = 1; i < 10; i++) {
                HorarioIndividuo hijo = cruzar(poblacion.get(0), poblacion.get(i));
                nuevaGen.add(hijo);
            }
            poblacion = nuevaGen;
        }

        HorarioIndividuo mejor = poblacion.stream().max(Comparator.comparingInt(h -> h.fitness)).get();
        for (HorarioAsignado h : mejor.asignaciones) {
            System.out.println(h);
        }
    }

    static HorarioIndividuo crearHorarioAleatorio(List<Curso> cursos, List<Docente> docentes, List<Salon> salones) {
        Random rand = new Random();
        List<HorarioAsignado> asignaciones = new ArrayList<>();
        for (Curso curso : cursos) {
            int horasAsignadas = 0;
            while (horasAsignadas < curso.horasSemana) {
                String dia = dias[rand.nextInt(dias.length)];
                int hora = horas[rand.nextInt(horas.length)];
                Docente docente = docentes.get(rand.nextInt(docentes.size()));
                if (!docente.puedeEnsenar(curso.nombre)) continue;
                Salon salon = salones.stream()
                    .filter(s -> (!curso.tipo.equals("laboratorio") || s.esLaboratorio))
                    .findFirst().orElse(null);
                if (salon == null) continue;
                asignaciones.add(new HorarioAsignado(curso, docente, salon, dia, hora));
                horasAsignadas++;
            }
        }
        return new HorarioIndividuo(asignaciones);
    }

    static HorarioIndividuo cruzar(HorarioIndividuo p1, HorarioIndividuo p2) {
        Random rand = new Random();
        List<HorarioAsignado> nuevo = new ArrayList<>();
        for (int i = 0; i < p1.asignaciones.size(); i++) {
            nuevo.add(rand.nextBoolean() ? p1.asignaciones.get(i) : p2.asignaciones.get(i));
        }
        return new HorarioIndividuo(nuevo);
    }
}
