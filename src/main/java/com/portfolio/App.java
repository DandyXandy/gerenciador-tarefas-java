package com.portfolio;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<Tarefa> lista = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("=== MEU PORTFÓLIO: GERENCIADOR DE TAREFAS ===");

       
        while (opcao != 4) {
            System.out.println("\n1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Marcar como Concluída");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) {
                System.out.print("Digite a descrição da tarefa: ");
                String desc = scanner.nextLine();
                lista.add(new Tarefa(desc));
                System.out.println("Tarefa adicionada!");

            } else if (opcao == 2) {
                System.out.println("\n---- SUAS TAREFAS ---");
                if (lista.isEmpty()) {
                    System.out.println("Nenhuma tarefa cadastrada.");
                } else {
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println(i + ". " + lista.get(i));
                    }
                }

            } else if (opcao == 3) {
                System.out.println("\n--- MARCAR COMO CONCLUÍDA ---");
                System.out.print("Digite o número da tarefa: ");
                int indice = scanner.nextInt();

                
                if (indice >= 0 && indice < lista.size()) {
                    lista.get(indice).setConcluida(true);
                    System.out.println("Tarefa atualizada com sucesso!");
                } else {
                    System.out.println("Erro: Índice inválido.");
                }
            }
        }

        System.out.println("Sistema encerrado. Até logo!");
        scanner.close();
    }
}