package com.portfolio;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.util.List;

public class TelaTarefas extends JFrame {
    private TarefaDAO dao = new TarefaDAO();
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private JList<String> listaVisual = new JList<>(modeloLista);
    private JTextField campoDescricao = new JTextField(20);

    public TelaTarefas() {
        setTitle("Gerenciador de Tarefas Pro");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel titulo = new JLabel("Minhas Tarefas (MySQL)", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Centro: Lista
        atualizarLista();
        add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        // Painel Inferior (Inserção e Ações)
        JPanel painelInferior = new JPanel(new GridLayout(2, 1)); // Duas linhas

        // Linha 1: Campo de texto e Salvar
        JPanel painelInserir = new JPanel();
        painelInserir.add(new JLabel("Nova Tarefa:"));
        painelInserir.add(campoDescricao);
        JButton btnSalvar = new JButton("Adicionar");
        painelInserir.add(btnSalvar);

        // Linha 2: Ações para itens selecionados
        JPanel painelAcoes = new JPanel();
        JButton btnConcluir = new JButton("Marcar como Concluída");
        JButton btnDeletar = new JButton("Excluir Tarefa");
        
        // Cores para os botões (um charme extra antes de "deixar bonito")
        btnConcluir.setBackground(new Color(144, 238, 144)); // Verde claro
        btnDeletar.setBackground(new Color(255, 182, 193));  // Vermelho claro

        painelAcoes.add(btnConcluir);
        painelAcoes.add(btnDeletar);

        painelInferior.add(painelInserir);
        painelInferior.add(painelAcoes);
        add(painelInferior, BorderLayout.SOUTH);

        // --- EVENTOS DOS BOTÕES ---

        btnSalvar.addActionListener(e -> {
            String desc = campoDescricao.getText();
            if (!desc.trim().isEmpty()) {
                dao.salvar(new Tarefa(desc));
                campoDescricao.setText("");
                atualizarLista();
            }
        });

        btnConcluir.addActionListener(e -> {
            int id = getIDSelecionado();
            if (id != -1) {
                dao.marcarComoConcluida(id);
                atualizarLista();
            }
        });

        btnDeletar.addActionListener(e -> {
            int id = getIDSelecionado();
            if (id != -1) {
                int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?");
                if (resposta == JOptionPane.YES_OPTION) {
                    dao.deletar(id);
                    atualizarLista();
                }
            }
        });
    }

    // Método auxiliar para pegar o ID da linha selecionada
    private int getIDSelecionado() {
        String selecionada = listaVisual.getSelectedValue();
        if (selecionada != null) {
            try {
                return Integer.parseInt(selecionada.split(" - ")[0]);
            } catch (Exception e) {
                return -1;
            }
        }
        JOptionPane.showMessageDialog(this, "Selecione uma tarefa na lista primeiro!");
        return -1;
    }

    private void atualizarLista() {
        modeloLista.clear();
        List<Tarefa> tarefas = dao.listarTodas();
        for (Tarefa t : tarefas) {
            String status = t.isConcluida() ? "[CONCLUÍDA] " : "[ A FAZER ] ";
            modeloLista.addElement(t.getId() + " - " + status + t.getDescricao());
        }
    }

    public static void main(String[] args) {
    try {
        // Ativa o Modo Escuro Moderno
        FlatDarkLaf.setup();
        
        // Dica: Se quiser o modo claro moderno, use: FlatLightLaf.setup();
    } catch (Exception e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
        new TelaTarefas().setVisible(true);
    });
}
}