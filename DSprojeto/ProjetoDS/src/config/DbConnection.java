package config; //Serve para poder importar esse arquivo para outros arquivos

//Importando as bibliotecas necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection { //Classe principal
    //URL do BD
    private static final String URL =
    "jdbc:mysql://localhost:3306/lojadedoces";
    private static final String USER = "root"; //User para acesso do BD
    private static final String PASSWORD = ""; //Senha para acesso do BD

    private static Connection connection = null; //Objeto connection para a conexão

    public static Connection getConnection() throws SQLException { //Função para conectar com o BD
        if (connection == null || connection.isClosed()) {
            try {
                //Carregar o drive do MySQL (opcional em JDBC moderno)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                //Erro caso não encontre o drive do MySQL
                System.out.println("Driver do MySQL não encontrado.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return connection; //Retorna a conexão
    }

    public static void disconnect(Connection connection) { //Função utilizada para fazer desconectar o BD
        try {
            connection.close(); //fecha a conexão
        } catch (SQLException e) {
            throw new RuntimeException("Error disconnection the database", e); //Caso haja um erro retorna a mensagem de erro
        }
    }
}
