import java.util.Scanner;

public class DesafioProf {

    static Scanner input = new Scanner(System.in);

    static final int TOTAL_AVALIACOES = 3;

    static final String[] NOME_AVALIACOES = {"A1", "A2", "A3"};
    static final double[] NOTA_MAX_AVALIACOES = {30.00, 30.00, 40.00};

    static double[] notas = new double[TOTAL_AVALIACOES];

    static double lerNota(String mensagem, double notaMaxima) {

        double nota = 0.0;

        do {
            System.out.printf("%s = ", mensagem);
            nota = input.nextDouble();
        } while (nota > notaMaxima || nota < 0.00);

        return nota;

    }


    static void atualizarNota(int indexNota) {
        notas[indexNota] = lerNota(NOME_AVALIACOES[indexNota], NOTA_MAX_AVALIACOES[indexNota]);
    }

    static void mostrarNotas() {

        double notaFinal = 0.0;

        System.out.println("\n\nNotas");
        System.out.println("-----------------");
        avaliarSituacao(notaFinal);

        System.out.println("-----------------");
    }

    static void avaliarSituacao(double notaAvaliada) {
        if(notaAvaliada >= 70.00) {
            System.out.println("Nota final: " + notaAvaliada);
            System.out.printf("\nVoce foi aprovado com %.2f pontos de diferença", (notaAvaliada - 70.00));

        } else {
            System.out.println("Nota final: " + notaAvaliada);
            System.out.println("\nVoce foi REPROVADO por: " + (70.00 - notaAvaliada) + " pontos");
        }
    }

    static void mostrarMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t   Menu");
        System.out.println();

        System.out.println("[0] Sair");
        System.out.println("[1] Cadastrar notas da A1");
        System.out.println("[2] Cadastrar notas da A2");
        System.out.println("[3] Cadastrar notas da A3");
        System.out.println("[4] Mostrar notas ");

        System.out.println("\nDigite uma opcao");
        byte opcao = input.nextByte();

        switch (opcao) {
            case 0:
                System.exit(500);
            case 1:
                atualizarNota(0);
                break;
            case 2:
                atualizarNota(1);
                break;
            case 3:
                atualizarNota(2);
                break;
            case 4:
                mostrarNotas();
                break;
            default:
                mostrarMenu();
                break;
        }
        mostrarMenu();
    }


    public static void main(String[] args) {

        mostrarMenu();

    }
}
