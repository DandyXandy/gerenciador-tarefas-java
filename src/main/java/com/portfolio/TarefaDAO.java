package com.portfolio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void salvar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)";
        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isConcluida());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Tarefa> listarTodas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        try (Connection conn = FabricaConexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tarefa t = new Tarefa(rs.getString("descricao"));
                t.setId(rs.getInt("id"));
                t.setConcluida(rs.getBoolean("concluida"));
                tarefas.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return tarefas;
    }

    public void marcarComoConcluida(int id) {
        String sql = "UPDATE tarefas SET concluida = true WHERE id = ?";
        try (Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void deletar(int id) {
    String sql = "DELETE FROM tarefas WHERE id = ?";

    try (Connection conn = FabricaConexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas > 0) {
            System.out.println("✅ Tarefa removida do banco de dados!");
        } else {
            System.out.println("⚠️ Nenhuma tarefa encontrada com esse ID.");
        }
        
    } catch (SQLException e) {
        System.out.println("Erro ao deletar: " + e.getMessage());
    }
}
}