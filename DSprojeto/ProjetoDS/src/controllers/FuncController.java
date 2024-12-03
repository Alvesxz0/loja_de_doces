package controllers; //Serve para poder importar esse arquivo para outros arquivos

//Importando o modelo do funcionário, o repositório e as telas da pasta views
import models.Funcionario;
import repository.FuncRepository;
import views.CadastroFunc;
import views.FuncTableView;

//Importando as bibliotecas necessárias
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FuncController { //Função principal
    //Criando os objetos que serão utilizados
    private FuncRepository repository;
    private FuncTableView tableView;

    public FuncController() { //Metodo construtor para iniciar o controller
        repository = new FuncRepository();
        tableView = new FuncTableView();
        inicializar();
    }


private void inicializar() {
    // Atualizar a tabela com os funcionários existentes
    atualizarTabela();

    // Criar a barra de ferramentas (toolbar) com botões
    JToolBar toolBar = new JToolBar();
    JButton adicionarButton = new JButton("Adicionar");
    JButton editarButton = new JButton("Editar");
    JButton deletarButton = new JButton("Deletar");

    //Adicionando os botões na toolbar
    toolBar.add(adicionarButton);
    toolBar.add(editarButton);
    toolBar.add(deletarButton);

    //Adicionando a toolbar na tableview
    tableView.add(toolBar, java.awt.BorderLayout.NORTH);

    // Ações dos botões

    //Botão adicionar
    adicionarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            adicionarFuncionario();
        }
    });

    //Botão editar
    editarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editarFuncionario();
        }
    });

    //Botão deleter
    deletarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deletarFuncionario();
        }
    });

    tableView.setVisible(true); //Deixando a tableview visivel
}

//função para atualizar a tabela
private void atualizarTabela() {
        List<Funcionario> funcionarios = repository.obterTodosFuncionarios();
        tableView.atualizarTabela(funcionarios);
    }

//Função para adicionar um funcionário
private void adicionarFuncionario() {
        CadastroFunc form = new CadastroFunc(tableView, "Adicionar Funcionario");
        form.setVisible(true);
        Funcionario novoFuncionario = form.getFuncionario();
        if (novoFuncionario != null) {
            repository.adicionarFuncionario(novoFuncionario);
            atualizarTabela();
        }
    }

//Função para editar um funcionário
private void editarFuncionario() {
    int selectedId = tableView.getSelectedFuncionarioId();
    if (selectedId != -1) {
        Funcionario funcionario = repository.obterFuncionarioPorId(selectedId);
        if (funcionario != null) {
            CadastroFunc form = new CadastroFunc(tableView,
                "Editar Funcionario", funcionario);
            form.setVisible(true);
            Funcionario funcionarioAtualizado = form.getFuncionario();
            if (funcionarioAtualizado != null) {
                funcionarioAtualizado = new Funcionario(
                    selectedId,
                    funcionarioAtualizado.getNome(),
                    funcionarioAtualizado.getCpf(),
                    funcionarioAtualizado.getAtivo(),
                    funcionarioAtualizado.getCep(),
                    funcionarioAtualizado.getFaxineiro(),
                    funcionarioAtualizado.getConfeiteiro(),
                    funcionarioAtualizado.getAtendente(),
                    funcionarioAtualizado.getSexo()
                );
                repository.atualizarFuncionario(funcionarioAtualizado);
                atualizarTabela();
            }
        } else { //Else para a mensagem de erro caso o usuario n tenha sido encontrado
            JOptionPane.showMessageDialog(tableView,
                "Funcionário não encontrado.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else { //Else para a mensagem de erro caso um funcionário não tenha sido selecionado
        JOptionPane.showMessageDialog(tableView,
        "Selecione um funcionário para editar.",
        "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}

//Função para deletar um funcionário
private void deletarFuncionario() {
    int selectedId = tableView.getSelectedFuncionarioId();
    if (selectedId != -1) {
        int confirm = JOptionPane.showConfirmDialog(
            tableView,
            "Tem certeza que deseja deletar este funcionário",
            "Confirmar Deleção",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            repository.deletarFuncionario(selectedId);
            atualizarTabela();
        }
    } else { //Else para a mensagem de erro caso um funcionário não tenha sido selecionado
        JOptionPane.showMessageDialog(
            tableView,
            "Selecione um funcionário para deletar",
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
    }
}

    public void iniciar() {
        // Ações já são inicializadas on construtor
    }

}