package com.example.atv2Dac;

import com.example.atv2Dac.controller.LutadorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Atv2DacApplication implements CommandLineRunner {

	@Autowired
	private LutadorController lutadorController;

	public static void main(String[] args) {
		SpringApplication.run(Atv2DacApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		Scanner leitor = new Scanner(System.in);

		String menu = "\n1- Adicionar nova Edição do UFC"
				+ "\n2- Mostrar todas as edições do UFC"
				+ "\n3- Mostrar UFC por Id"
				+ "\n4- Atualizar UFC"
				+ "\n5- Deletar UFC por Id"
				+ "\n6- Adicionar Lutador"
				+ "\n7- Mostrar todos os Lutadores"
				+ "\n8- Mostrar lutador por Id"
				+ "\n9- Atualizar Lutador"
				+ "\n10- Deletar Lutador"
				+ "\n0- Sair." ;

		int opcao = 1;
		long idLutador;

		while (opcao != 0){

			System.out.println(menu);

			opcao = Integer.parseInt(leitor.nextLine());

			if (opcao == 1){
				System.out.println("Edição");
				Long edicao = Long.parseLong(leitor.nextLine());
				System.out.println("Selecione o id do primeiro jogador da Luta principal");
				idLutador = Long.parseLong(leitor.nextLine());
				List<Lutador> principal = new ArrayList<>();
				principal.add(lutadorController.getLutador(idLutador));
				System.out.println("Selecione o id do segundo jogador da Luta principal");
			    idLutador = Long.parseLong(leitor.nextLine());
				principal.add(lutadorController.getLutador(idLutador));

				ufcController.save(edicao,principal);
			}
			else if(opcao == 2){
				List<UFC> list = ufcController.getUFC();
				for (UFC c: list) {
					System.out.println("\n" + c.toString());
				}
			}
			else if(opcao == 3){
				System.out.println("Qual o id? ");
				Long id = Long.parseLong(leitor.nextLine());
				UFC ufc = ufcController.busca(id);
				ufc.toString();
			}
			else if(opcao == 4){
				System.out.println("Qual o id do UFC a ser atualizado? ");
				Long id = Long.parseLong(leitor.nextLine());
				System.out.println("Qual a nova edição? ");
				Long edicao = Long.parseLong(leitor.nextLine());

				System.out.println("Selecione o id do primeiro jogador da Luta principal");
				idLutador = Long.parseLong(leitor.nextLine());
				List<Lutador> principal = new ArrayList<>();
				principal.add(lutadorController.getLutador(idLutador));
				System.out.println("Selecione o id do segundo jogador da Luta principal");
				idLutador = Long.parseLong(leitor.nextLine());
				principal.add(lutadorController.getLutador(idLutador));

				System.out.println("Selecione o id dos 2 jogadores da Luta principal");
				idLutador = Long.parseLong(leitor.nextLine());
				List<Lutador> preliminar = new ArrayList<>();
				preliminar.add(lutadorController.getLutador(idLutador));
				System.out.println("Selecione o id dos 2 jogadores da Luta principal");
				idLutador = Long.parseLong(leitor.nextLine());
				preliminar.add(lutadorController.getLutador(idLutador));

				ufcController.update(id,edicao, principal);
			}
			else if(opcao == 5){
				System.out.println("Qual o id do UFC a ser Deletado? ");
				Long id = Long.parseLong(leitor.nextLine());
				ufcController.delete(id);
			}
			else if(opcao == 6){
				System.out.println("Qual o nome do Lutador? ");
				String nome = leitor.nextLine();
				System.out.println("Qual o cartel do lutador? ");
				String cartel = leitor.nextLine();
				lutadorController.save(nome,cartel);
			}
			else if(opcao == 7){
				List<Lutador> list = lutadorController.getLutadores();
				for (Lutador c: list) {
					System.out.println("\n" + c.toString());
				}
			}
			else if (opcao == 8){
				System.out.println("Qual id do Lutador? ");
				idLutador = Long.parseLong(leitor.nextLine());
				System.out.println("\n" + lutadorController.getLutador(idLutador).toString());
			}
			else if (opcao == 9) {
				System.out.println("Qual id do lutador a ser atualizado? ");
				idLutador = Long.parseLong(leitor.nextLine());
				System.out.println("Qual o nome do lutador? ");
				String nome = leitor.nextLine();
				System.out.println("Qual o cartel do lutador?");
				String cartel = leitor.nextLine();
				lutadorController.update(idLutador, nome, cartel);
			}
			else if (opcao == 10) {
				System.out.println("Qual o id do lutador?");
				idLutador = Long.parseLong(leitor.nextLine());
				lutadorController.delete(idLutador);
			}

		}

		 */

	}
}
