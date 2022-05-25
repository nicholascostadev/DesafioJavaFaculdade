import java.util.Scanner;

public class DesafioFinalProf extends RuntimeException {


    static Scanner console = new Scanner(System.in);

    static final int TOTAL_AVALIACOES = 3;
    static final String[] NOMES_AVALIACOES = {"A1", "A2", "A3"};
    static final double[] NOTA_MAX_AVALIACOES = {30.00, 30.00, 40.00};
    static double[] notas = new double[TOTAL_AVALIACOES];
    static double notaFinal = 0.0;

    static boolean hasAlreadyTaken = false;


    /**
     * Ler uma nota do usuário
     *
     * @param mensagem O texto que aparecerá na tela
     * @return um número double representando a nota.
     */

    static double lerNota(String mensagem, double notaMaxima) {

        double nota;

        do {

            System.out.printf("%s = ", mensagem);
            nota = console.nextDouble();

        } while (nota < 0.00 || nota > notaMaxima);

        return nota;
    }


    /**
     * Atualiza o valor da respectiva nota do estudante
     *
     * @param indiceNota um número inteiro representando o índice (posição) da nota no vetor
     */
    static void atualizarNota(int indiceNota) {

        System.out.println();
        notas[indiceNota] = lerNota(NOMES_AVALIACOES[indiceNota], NOTA_MAX_AVALIACOES[indiceNota]);

    } // Fim do método atualizarNota


    /**
     * @param notaFinal A soma de todas as avalições feita pelo estudante ao longo do semestre
     * @return uma string representando o status final do estudante, são eles: APROVADO, REPROVADO, EM RECUPERAÇÃO.
     */
    static String avaliarSituacao(double notaFinal) {

        if(!hasAlreadyTaken) {
            if (notaFinal < 30)
                return "REPROVADO";
            else if (notaFinal < 70)
                return "EM RECUPERAÇÃO";
            else
                return "APROVADO";
        } else {
            if (notaFinal < 70)
                return "REPROVADO";
            else
                return "APROVADO";
        }

    } // Fim do método avaliarSituacao()


    /**
     * Mostra na tela um relatório das notas do estudante
     */

    static void calcularTotal() {
        // resetar o valor da variável quando
        // for calcular o total novamente
        notaFinal = 0.00;

        for (int i = 0; i < TOTAL_AVALIACOES; i++) {

            System.out.printf("Avaliacao %s = %.2f pts\n", NOMES_AVALIACOES[i], notas[i]);
            notaFinal += notas[i];

        }
    }

    static void gerarProvaAI() {
        System.out.println("\nVoce esta apto a fazer a prova AI");
        double AI = lerNota("\nInforme a nota da AI", NOTA_MAX_AVALIACOES[indexMenorNota(notas)]);

        /*
         * Verifica se a nova nota é maior que a anterior, se sim, atribui o valor à "notas"
         * se não, atribui-se seu próprio valor, para não alterá-lo.
         */
        notas[indexMenorNota(notas)] = Math.max(notas[indexMenorNota(notas)], AI);

        calcularTotal();

        hasAlreadyTaken = true;
    }

    static void mostrarSituacaoCompleta() {
        System.out.printf("\n\nNota Final = %.2f pts", notaFinal);
        System.out.printf("\nSituacao = %s, sua menor nota foi de %.2f pontos", avaliarSituacao(notaFinal), notas[indexMenorNota(notas)]);
        System.out.printf("\nSua media foi de: %.2f ", calcularMedia(notas));
        System.out.printf("\nSua prova com maior nota foi a: %s\n", maiorNota(notas));
    }


    static void mostrarNotas() {
        System.out.print("""
                \n
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                -----------------------------------------
                |               Notas                   |
                -----------------------------------------
                """);

        calcularTotal();

        mostrarSituacaoCompleta();

        /*
         * Verificar se alguma das notas A1 ou A2 foram abaixo de 30
         * para assim permitir ele fazer a prova AI
         */
        boolean estaApto = (notas[0] + notas[1]) < 60;

        /*
         * Se estiver em recuperacao, uma prova a mais é feita
         * Aproveitei a logica anterior para caso queira mudar
         * a nota máxima e mínima, a lógica de "AI" continue funcionando
         */
        if (avaliarSituacao(notaFinal).equalsIgnoreCase("EM RECUPERAÇÃO") && estaApto) {
            // Verifica se o aluno ja fez a prova, caso sim,
            // não pode refazer a prova "AI"
            if (hasAlreadyTaken) {
                mostrarSituacaoCompleta();
            } else {
                gerarProvaAI();
                mostrarSituacaoCompleta();
            }
        }
    } // Fim do método mostrarNotas()


    /**
     * Exibe o menu principal da aplicação
     */
    static void mostrarMenu() {

        System.out.println("\n\n");
//        System.out.println("-----------------------------------------");
        System.out.println("""
                -----------------------------------------
                |              <__Menu__>               |
                -----------------------------------------
                | [1] - Cadastrar Notas A1             |
                -----------------------------------------
                | [2] - Cadastrar Nota A2              |
                -----------------------------------------
                | [3] - Cadastrar Nota A3              |
                -----------------------------------------
                | [4] - Mostrar Notas                  |
                -----------------------------------------
                | [0] - Sair                           |
                -----------------------------------------
                """);
        System.out.print("Digite uma das opcoes:  ");
        byte opcao = console.nextByte();


        switch (opcao) {

            case 0:
                System.exit(0);
                break;

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
    } // Fim do método mostrarMenu()

    static double calcularMedia(double[] notas) {
        double media = 0.00;

        for (double notaAtual : notas) {
            media += notaAtual;
        }

        return media / TOTAL_AVALIACOES;
    }

    static String maiorNota(double[] notas) {
        String maiorNota = "";
        double verificadorMaior = 0;

        for (int i = 0; i < TOTAL_AVALIACOES; i++) {
            if (notas[i] > verificadorMaior) {
                verificadorMaior = notas[i];
                maiorNota = NOMES_AVALIACOES[i];
            }
        }
        return maiorNota;
    }

    static int indexMenorNota(double[] notas) {
        int indexMenorNota = 0;

        if (notas[0] > notas[1]) {
            indexMenorNota = 1;
        }

        return indexMenorNota;
    }


    public static void main(String[] args) {

        mostrarMenu();

    } // Fim do método main();

} // Fim da classe Main

