package br.com.clinicaveterinaria.main;

import br.com.clinicaveterinaria.controller.ClinicaController;
import br.com.clinicaveterinaria.model.Animal;
import br.com.clinicaveterinaria.model.Consulta;
import br.com.clinicaveterinaria.model.Proprietario;
import br.com.clinicaveterinaria.model.Veterinario;
import br.com.clinicaveterinaria.util.DatabaseCleaner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        
        ClinicaController controller = new ClinicaController();
        
        DatabaseCleaner.limparBanco();
        try {
            //criando proprietário
        	System.out.println("COMEÇANDO TESTE");
            System.out.println("\nCriando Proprietários");
            //cpf, nome, telefone, endereco, email
            controller.salvarProprietario("11122233300", "Lucas", "71988887777", "Etiópia", "lucasOli@gmail.com");
            controller.salvarProprietario("11144433300", "Badskill", "71955557777", "Grécia", "Tiberio@gmail.com");
            controller.salvarProprietario("11155533300", "Genix", "71988886666", "Somália", "genesis@hotmail.com");
            //vai violar o campo unico email
            //controller.salvarProprietario("11144433300", "Genix", "71988886666", "Somália", "genesisx@hotmail.com");
            
            //é pra dar erro msm por causa do unique no campo cpf
            //controller.salvarProprietario("11155533300", "Darimaru", "71988883366", "Angola", "dari@hotmail.com");
            
            //controller.atualizarProprietario("11155533301", "Genixx", "71977772222", "Somália", "genes@hotmail.com");
            
            //controller.deletarProprietario(2);
            
            System.out.println("\nCriando veterinarios");
            //crmv, nome, especialidade, telefone
            controller.salvarVeterinario("BA-12345", "Suellen", "Fisioterapia", "71999999999");
            controller.salvarVeterinario("RJ-23456", "Nicolau", "Cardiologista", "21988888877");
            controller.salvarVeterinario("SP-34567", "Julia", "Neurologista", "11966665544");
            //controller.salvarVeterinario("SP-34569", "Maria", "Neurologista", "11966665555");
            
            //controller.atualizarVeterinario("SP-34569", "Juliah", "Ortopedista", "11966665599");
            
            //controller.deletarVeterinario(1);
            
            System.out.println("\nCriando animais");
            //nome, especie, raca, data_nasc, peso, proprietario_id
            controller.salvarAnimal("Titalo", "Cachorro", "Pinsher", LocalDate.of(2018, 6, 12), 4.5, 3);
            controller.salvarAnimal("Mau Mau", "Hamster", "Ratinho", LocalDate.of(2015, 3, 25), 0.6, 2);
            controller.salvarAnimal("Roger", "Macaco", "Prego", LocalDate.of(2023, 8, 23), 5.5, 1);
            controller.salvarAnimal("Bolinha", "Gato", "vira-lata", LocalDate.of(2023, 8, 23), 5.5, 1);
            
            //idAnimal, nome, especie, raca, dataNasc, peso, proprietario_id
            //controller.atualizarAnimal(2, "Leonardo", "Macaco", "Prego", LocalDate.of(2023, 8, 23), 6.2, 5);
            
            System.out.println("\nCriando consultas");
            //data_hora, diagnostico, valor, animal_id, veterinario_id
            controller.salvarConsulta(LocalDateTime.of(2025, 3, 14, 13, 30), "Traumatismo craniano", 100, 1, 3);
            controller.salvarConsulta(LocalDateTime.of(2024, 9, 24, 11, 20), "Pata machucada", 50, 2, 1);
            controller.salvarConsulta(LocalDateTime.of(2024, 7, 26, 18, 15), "Insuficiencia cardiaca", 200, 3, 2);
            
            //idConsulta, dataHora, diagnostico, valor, animalId, veterinario_id
            //controller.atualizarConsulta(1, LocalDateTime.of(2024, 9, 24, 11, 20), "Perda total", 25, 2, 1);
            
            controller.deletarConsulta(1);
            controller.deletarAnimal(1);
            controller.deletarProprietario(3);
            controller.deletarVeterinario(3);
            
            
            
            /*
            List<Proprietario> props = controller.listarProprietarios();
            for (Proprietario proprietario : props) {
				System.out.println(proprietario);
			}
 
            System.out.println();
            List<Veterinario> vets = controller.listarVeterinarios();
            for (Veterinario veterinario : vets) {
				System.out.println(veterinario);
			}
            System.out.println();
            List<Animal> animais = controller.listarAnimais();
            for (Animal animal : animais) {
				System.out.println(animal);
			}
         	*/
            System.out.println();
            List<Consulta> consultas = controller.listarConsultas();
            for (Consulta consulta : consultas) {
				System.out.println(consulta);
			}
        } catch (Exception e) {
            System.err.println("Ocorreu algum B.O no teste mano: " + e.getMessage());
            e.printStackTrace();
        }
    }
}