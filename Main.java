import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// CLASSE ALUNO
class Aluno {
    private static final double MEDIA_MINIMA = 6.0;
    private static final int MAX_FALTAS = 8;

    private String nome;
    private double nota1;
    private double nota2;
    private int faltas;

    public Aluno(String nome, double nota1, double nota2, int faltas) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.faltas = faltas;
    }

    public String getNome() {
        return nome;
    }

    public double calcularMedia() {
        return (nota1 + nota2) / 2;
    }

    public boolean estaAprovado() {
        return calcularMedia() >= MEDIA_MINIMA && faltas <= MAX_FALTAS;
    }
}

// CLASSE TURMA
class Turma {
    private List<Aluno> alunos = new ArrayList<>();

    // transforma as linhas "Ana;8.5;9.0;2" em objetos Aluno
    public void carregarAlunos(String[] linhas) {
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            String nome = partes[0];
            double n1 = Double.parseDouble(partes[1]);
            double n2 = Double.parseDouble(partes[2]);
            int faltas = Integer.parseInt(partes[3]);

            alunos.add(new Aluno(nome, n1, n2, faltas));
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public double calcularMediaTurma() {
        double soma = 0;
        for (Aluno a : alunos) {
            soma += a.calcularMedia();
        }
        return soma / alunos.size();
    }

    public double calcularMelhorMedia() {
        double melhor = alunos.get(0).calcularMedia();
        for (Aluno a : alunos) {
            if (a.calcularMedia() > melhor) {
                melhor = a.calcularMedia();
            }
        }
        return melhor;
    }

    public int contarAprovados() {
        int cont = 0;
        for (Aluno a : alunos) {
            if (a.estaAprovado()) {
                cont++;
            }
        }
        return cont;
    }

    public int contarReprovados() {
        return alunos.size() - contarAprovados();
    }

    public void exibirRelatorio() {
        System.out.println("=== RELATÓRIO DA TURMA ===");

        for (Aluno a : alunos) {
            String situacao = a.estaAprovado() ? "APROVADO" : "REPROVADO";
            System.out.println(String.format(Locale.US,
                    "%-6s média %.1f  ->  %s", a.getNome(), a.calcularMedia(), situacao));
        }

        System.out.println("------------------------------");
        System.out.println("Aprovados: " + contarAprovados() + " | Reprovados: " + contarReprovados());
        System.out.println(String.format(Locale.US,
                "Média da turma: %.1f | Melhor média: %.1f",
                calcularMediaTurma(), calcularMelhorMedia()));
    }
}

// MAIN
public class Main {
    public static void main(String[] args) {
        String[] dadosBrutos = {
            "Ana;8.5;9.0;2",
            "Bruno;5.0;6.5;10",
            "Carla;7.0;4.0;0",
            "Diego;9.5;10.0;1",
            "Gabriel;9.5;8.0;1"
        };

        Turma turma = new Turma();
        turma.carregarAlunos(dadosBrutos);
        turma.exibirRelatorio();
    }
}
