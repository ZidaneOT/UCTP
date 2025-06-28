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

        List<HorarioAsignado> horarioFinal = new ArrayList<>();
        Random rand = new Random();

        for (Curso curso : cursos) {
            int horasAsignadas = 0;
            Set<String> usados = new HashSet<>();
            while (horasAsignadas < curso.horasSemana) {
                String dia = dias[rand.nextInt(dias.length)];
                int hora = horas[rand.nextInt(horas.length)];
                String clave = dia + ":" + hora + ":" + curso.nombre;
                if (usados.contains(clave)) continue;
                usados.add(clave);

                Docente docente = docentes.stream()
                    .filter(d -> d.puedeEnsenar(curso.nombre) && d.horasDisponibles > 0)
                    .findFirst().orElse(null);

                Salon salon = salones.stream()
                    .filter(s -> (!curso.tipo.equals("laboratorio") || s.esLaboratorio))
                    .findFirst().orElse(null);

                if (docente != null && salon != null) {
                    horarioFinal.add(new HorarioAsignado(curso, docente, salon, dia, hora));
                    docente.horasDisponibles--;
                    horasAsignadas++;
                } else {
                    System.out.println("No se pudo asignar: " + curso.nombre);
                    break;
                }
            }
        }

        for (HorarioAsignado h : horarioFinal) {
            System.out.println(h);
        }
    }
}
