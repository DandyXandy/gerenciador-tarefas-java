package com.portfolio;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TarefaDAO tarefaDAO = new TarefaDAO();
        int opcao = 0;

        System.out.println("=== MEU PORTFÓLIO: GERENCIADOR DE TAREFAS ===");

        while (opcao != 5) {
           System.out.println("\n1. Adicionar\n2. Listar\n3. Concluir\n4. Deletar\n5. Sair");
            System.out.print("Escolha: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                continue;
            }

            if (opcao == 1) {
                System.out.print("Descrição: ");
                tarefaDAO.salvar(new Tarefa(scanner.nextLine()));
                System.out.println("✅ Salvo no MySQL!");

            } else if (opcao == 2) {
                List<Tarefa> lista = tarefaDAO.listarTodas();
                System.out.println("\n--- TAREFAS NO BANCO ---");
                for (Tarefa t : lista) {
                    System.out.println(t.getId() + " [" + (t.isConcluida() ? "X" : " ") + "] " + t.getDescricao());
                }

            } else if (opcao == 3) {
                System.out.print("Digite o ID da tarefa: ");
                int id = Integer.parseInt(scanner.nextLine());
                tarefaDAO.marcarComoConcluida(id);
                System.out.println("✅ Atualizado!");
            } else if (opcao == 4) {
                System.out.print("Digite o ID da tarefa que deseja DELETAR: ");
                int idDeletar = Integer.parseInt(scanner.nextLine());
    
                System.out.print("Tem certeza? (S/N): ");
                String confirma = scanner.nextLine();
    
                if (confirma.equalsIgnoreCase("S")) {
                tarefaDAO.deletar(idDeletar);
             } else {
        System.out.println("Operação cancelada.");
    }
}
            
        }
        scanner.close();
    }
}