package views; //Serve para poder importar esse arquivo para outros arquivos

//Importando as bibliotecas necessárias
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Funcionario; //Importa o modelo do funcionário

//Mais bibliotecas
import java.awt.*;
import java.util.List;

public class FuncTableView extends JFrame { //Classe principal do frame
    //criando os objetos table e tablemodel
    private JTable table;
    private DefaultTableModel tableModel;

    public FuncTableView() { //Metodo construtor 
        super("Gerenciamento de Funcionários");
        initializeComponents();
    }

    private void initializeComponents() { //Função que inicializa os componentes da tela
        //Criando os objetos 
        //Criando ums string com o nome das colunas
        String[] columnNames = {"ID", "Nome", "CPF", "Ativo", "CEP", "Faxineiro", "Confeiteiro", "Atendente", "Sexo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table); //Criando um scroll panel 

        scrollPane.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Colocando uma borda no painel
        
        //Setando o layout da janela e adicionando o scroll panel
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        //Determinando o tamanho e a operação de fechar da tela
        this.setSize(600, 400); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    //Função para atualizar a tabela
    public void atualizarTabela(List<Funcionario> funcionarios) {
        tableModel.setRowCount(0); 
        for (Funcionario funcionario : funcionarios) {
            Object[] row = {
            funcionario.getId(),
            funcionario.getNome(),
            funcionario.getCpf(),
            funcionario.getAtivo(),
            funcionario.getCep(),
            funcionario.getFaxineiro(),
            funcionario.getConfeiteiro(),
            funcionario.getAtendente(),
            funcionario.getSexo()
            };
            tableModel.addRow(row);
        }
    }

    //Função para selecionar o funcionário pela id 
    public int getSelectedFuncionarioId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
}
