import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalculaIdadeBR {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Formato brasileiro
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Entrada da data de nascimento
        System.out.print("Digite sua data de nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine();

        // Converte para LocalDate usando o formato BR
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatoBR);

        // Data atual
        LocalDate dataAtual = LocalDate.now();

        // Cálculo da idade
        Period idade = Period.between(dataNascimento, dataAtual);

        System.out.println("Você tem " + idade.getYears() + " anos.");

        scanner.close();
    }
}