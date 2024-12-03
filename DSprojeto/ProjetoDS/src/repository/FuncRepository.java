package repository; //Serve para poder importar esse arquivo para outros arquivos

//Importando o modelo do funcionario e a conexão com o BD
import models.Funcionario;
import config.DbConnection;

//Importando as bibliotecas necessárias 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncRepository { //Classe principal

    // Adicionar um novo funcionário
    public void adicionarFuncionario(Funcionario funcionario) {
        //Insert no SQL
        String sql = "INSERT INTO funcionarios (nome, CPF, ativo, CEP, faxineiro, confeiteiro, atendente, sexo) VALUES (?, ?, ?, ?, ? ,? ,? ,?)";

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString( 1, funcionario.getNome());
            stmt.setString( 2, funcionario.getCpf());
            stmt.setBoolean( 3, funcionario.getAtivo());
            stmt.setString( 4, funcionario.getCep());
            stmt.setBoolean( 5, funcionario.getFaxineiro());
            stmt.setBoolean( 6, funcionario.getConfeiteiro());
            stmt.setBoolean( 7, funcionario.getAtendente());
            stmt.setString( 8, funcionario.getSexo());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) { //If para a mensagen de sucesso 
                System.out.println("Funcionário adicionado com sucesso!");
            }

        } catch (SQLException e) { //Catch para o erro ao adicionar funcionário
            System.out.println("Erro ao adicionar funcionário.");
            e.printStackTrace();;
        }
    }

    // Obter todos os funcionários
    public List<Funcionario> obterTodosFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios"; //Select no SQL

        try (Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getBoolean("ativo"),
                    rs.getString("cep"),
                    rs.getBoolean("faxineiro"),
                    rs.getBoolean("confeiteiro"),
                    rs.getBoolean("atendente"),
                    rs.getString("sexo")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) { //Catch para a mensagem de erro caso haja um erro ao obter o funcionário
            System.out.println("Erro ao obter funcionários.");
            e.printStackTrace();;
        }

        return funcionarios;
    }

 // Obter funcionário por ID
    public Funcionario obterFuncionarioPorId(int id) {
        //Select no SQL
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        Funcionario funcionario = null;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getBoolean("ativo"),
                    rs.getString("cep"),
                    rs.getBoolean("faxineiro"),
                    rs.getBoolean("confeiteiro"),
                    rs.getBoolean("atendente"),
                    rs.getString("sexo")
                );
            }
        } catch (SQLException e) { //Catch para a mensagem de erro caso haja um erro ao obter o funcionário por ID
            System.out.println("Erro ao obter funcionário por ID.");
            e.printStackTrace();
        }

        return funcionario;
    }

 // Atualizar um funcionário
    public void atualizarFuncionario(Funcionario funcionario) {
        //Update no SQL
        String sql =
          "UPDATE funcionarios SET nome = ?, CPF = ?, ativo = ?, CEP = ?, faxineiro = ?, confeiteiro = ?, atendente = ?, sexo =? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString( 1, funcionario.getNome());
            stmt.setString( 2, funcionario.getCpf());
            stmt.setBoolean( 3, funcionario.getAtivo());
            stmt.setString( 4, funcionario.getCep());
            stmt.setBoolean( 5, funcionario.getFaxineiro());
            stmt.setBoolean( 6, funcionario.getConfeiteiro());
            stmt.setBoolean( 7, funcionario.getAtendente());
            stmt.setString( 8, funcionario.getSexo());
            stmt.setInt(9, funcionario.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) { //If para as mensagens de sucesso ou funcionário não encontrado
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) { //Catch para a mensagem de erro caso haja um erro ao atualizar funcionário
            System.out.println("Erro ao atualizar funcionário.");
            e.printStackTrace();
        }
    }

 // Deletar um funcionário
    public void deletarFuncionario(int id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?"; //Delete no SQL

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) { //If para as mensagens de sucesso ou funcionário não encontrado
                System.out.println("Funcionário deletado com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) { //Catch para a mensagem de erro caso haja um erro ao deletar o funcionário
            System.out.println("Erro ao deletar funcionário.");
            e.printStackTrace();
        }
    }
}
