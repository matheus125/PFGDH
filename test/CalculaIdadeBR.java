
import com.raven.dao.DependenteDao;
import com.raven.model.Dependentes;
import java.util.List;

public class CalculaIdadeBR {

    public static void main(String[] args) {
    // Instancia o DAO
    DependenteDao dao = new DependenteDao(); // Substitua pelo nome real do seu DAO

    // Cria o filtro com nome ou CPF
    Dependentes filtro = new Dependentes();
    filtro.setPesquisar("EMILLY KAMYLE MARQUES DE OLIVEIRA"); // Altere aqui para o nome ou CPF desejado

    // Executa o método de busca
    List<Dependentes> lista = dao.buscarDependentesPorDependente(filtro);

    // Exibe os resultados no console
    for (Dependentes d : lista) {
        System.out.println("ID: " + d.getId());
        System.out.println("Nome: " + d.getNome_Completo());
        System.out.println("RG: " + d.getRg());
        System.out.println("CPF: " + d.getCpf());
        System.out.println("Idade: " + d.getIdade_cliente());
        System.out.println("Gênero: " + d.getGenero_cliente());
        System.out.println("Dependência: " + d.getDependencia_cliente());
        System.out.println("---------------------------");
    }

    if (lista.isEmpty()) {
        System.out.println("Nenhum dependente encontrado.");
    }
}

}
