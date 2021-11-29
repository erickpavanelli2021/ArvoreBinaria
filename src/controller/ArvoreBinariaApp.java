package controller;

import java.util.Scanner;

class ArvoreBinariaApp{
	public static void main(String[] args) {
		Scanner lerInformacao = new Scanner(System.in);
				Arvore arvore = new Arvore();
				int opcao;
				long x = 0;
				System.out.println("\n Programa Arvore binaria de long");
				do {
					System.out.println("\n **********************************");
					System.out.println("\nEntre com a opção:");
					System.out.println("\n ----1: Inserir");
					System.out.println("\n ----2: excluir");
					System.out.println("\n ----3: Pesquisar");
					System.out.println("\n ----4: Exibir");
					System.out.println("\n ----5: Imprimir arvore");
					System.out.println("\n ----6: Sair do programa");
					System.out.println("\n **********************************");
					System.out.println("\n-> ");
					opcao = lerInformacao.nextInt();
					switch (opcao) {
					case 1:{
						System.out.println("\n Informe o valor (long) -> ");
						x = lerInformacao.nextLong();
						arvore.inserir(x);;
						break;
					}
					case 2:{
						System.out.println("\n Informe o valor (long) -> ");
						x = lerInformacao.nextLong();
						arvore.inserir(x);
						break;
					}
					case 3:{
						System.out.println("\n Informe o valor (long) -> ");
						if (arvore.buscar(x) != null) 
							System.out.println("\n Valor Encontrado: ");
						else 
							System.out.println("\n Valor não encontado!");
						
						break;
					}
					case 4:{
						arvore.caminhar();
						break;
					}
					case 5:{
						arvore.print();
						break;
					}
					}//fim do switch
				}while (opcao != 6);
	}
}