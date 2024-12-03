package views; //Serve para poder importar esse arquivo para outros arquivos

import models.Funcionario; //Importa o modelo do funcionário

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFunc extends JDialog { //Classe principal do arquivo
    //Criação dos objetos
    private JLabel labelnome, labelcpf, labelstatus, labelcep, labelfunc, labelsexo; 
    private JTextField TFnome, TFcpf, TFcep;
    private JComboBox<String> ComboBoxSexo;
    private JRadioButton status1, status2;
    private JCheckBox CheckConf, CheckAtend, CheckFax;
    private JButton ButtonEnviar, ButtonCancelar;
    private ButtonGroup grupoRadio;
    private JPanel panelForm, panelButton, panelButton_1, panelButton_2, panelRadio, panelCheckBox, panel1, panel2, panel3, panel4, panel5, panel6;


    private Funcionario funcionario; //Cria um objeto funcionario que segue o modelo de funcionario
    private boolean isEditMode; //Define se o formulário esta no modo edição

    public CadastroFunc(Frame parent, String title) { //Metodo construtor do frame
        super(parent, title, true);
        this.isEditMode = false; //Determina que não esta no modo de edição
        initializeComponents(); //Chama a função initialize components
    }

    public CadastroFunc(Frame parent, String title, Funcionario funcionario) { //Metodo construtor para quando o banco for ser atualizado
        super(parent, title, true);
        this.funcionario = funcionario;
        this.isEditMode = true; //Determinaesta no modo de edição
        initializeComponents(); //Chama a função initialize components
        preencherCampos(); //Chama a função que preenche os campos para editar
    }

    private void initializeComponents() { //Metodo de iniciar os componentes
        //Criando os labels
        labelnome = new JLabel("Nome: "); 
        labelcpf = new JLabel("CPF: "); 
        labelstatus = new JLabel("Status: "); 
        labelcep = new JLabel("CEP: "); 
        labelfunc = new JLabel("Função: "); 
        labelsexo = new JLabel("Sexo: ");

        //Criando os TextFields
        TFnome = new JTextField(10);
        TFcpf = new JTextField(10);
        TFcep = new JTextField(10);

        //Criando a ComboBox e adicionando seus items
        ComboBoxSexo = new JComboBox<>();
        ComboBoxSexo.addItem("Masculino");
        ComboBoxSexo.addItem("Feminino");

        //Criando os radioButtons para o status
        status1 = new JRadioButton("Ativo", true);
        status2 = new JRadioButton("Desativado", false);

        //Criando as CheckBox
        CheckConf = new JCheckBox("Confeiteiro");
        CheckAtend = new JCheckBox("Atendente");
        CheckFax = new JCheckBox("Faxineiro");

        //Criando o botão enviar e colocando cor
        ButtonEnviar = new JButton("Enviar");
        ButtonEnviar.setBackground(Color.BLUE);
        ButtonEnviar.setForeground(Color.WHITE);

        //Criando o botão cancelar e colocando cor
        ButtonCancelar = new JButton("Cancelar");
        ButtonCancelar.setBackground(Color.BLUE);
        ButtonCancelar.setForeground(Color.WHITE);

        //Criando o grupo para os radiobuttons
        grupoRadio = new ButtonGroup();
        grupoRadio.add(status1);
        grupoRadio.add(status2);

        //criando todos os paineis
        panelRadio = new JPanel();
        panelForm = new JPanel();
        panelButton = new JPanel();
        panelButton_1 = new JPanel();
        panelButton_2 = new JPanel();
        panelCheckBox = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();

        //Definindo o layout dos paineis
        panelForm.setLayout(new GridLayout(6, 2));
        panelButton.setLayout(new GridLayout(1, 2));
        panelButton_1.setLayout(new FlowLayout());
        panelButton_2.setLayout(new FlowLayout());
        panelCheckBox.setLayout(new FlowLayout());
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        panel6.setLayout(new FlowLayout()); 
        panelRadio.setLayout(new FlowLayout()); 

        //Adicionando os botões em seu painel
        panelButton_1.add(ButtonEnviar);
        panelButton_2.add(ButtonCancelar);

        //Adicionando os checkbox em seu painel
        panelCheckBox.add(CheckConf);
        panelCheckBox.add(CheckFax);
        panelCheckBox.add(CheckAtend);

        //Adicionando os radiobuttons em seu painel
        panelRadio.add(status1);
        panelRadio.add(status2);
        
        //Adicionando os objetos em seus paineis
        panelButton.add(panelButton_1);
        panelButton.add(panelButton_2);

        panel1.add(labelnome);
        panel1.add(TFnome);

        panel2.add(labelcpf);
        panel2.add(TFcpf);

        panel3.add(labelstatus);
        panel3.add(panelRadio);

        panel4.add(labelcep);
        panel4.add(TFcep);

        panel5.add(labelfunc);
        panel5.add(panelCheckBox);

        panel6.add(labelsexo);
        panel6.add(ComboBoxSexo);

        //Adicionando os paineis no painel de formulário 
        panelForm.add(panel1);
        panelForm.add(panel2);
        panelForm.add(panel3);
        panelForm.add(panel4);
        panelForm.add(panel5);
        panelForm.add(panel6);

        //Criando e adicionando a função do botão de enviar 
        ButtonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) { //Determina se está no editmode vai editar o funcionário
                        atualizarFuncionario();
                    } else { //Se não estiver no editmode adiciona o funcionário
                        adicionarFuncionario();
                    }
                    dispose();
                }
            }
        });

        ButtonCancelar.addActionListener(e -> dispose()); //Adicionando a função de fechar a janela no botão cancelar

        //Adicionando os paineis na Janela
        this.add(panelForm, BorderLayout.CENTER);
        this.add(panelButton, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(getParent());
    }

    //Função de preencher os campos para o modo de edição
    private void preencherCampos() {
        if (funcionario != null) {
            TFnome.setText(funcionario.getNome());
            TFcpf.setText(funcionario.getCpf());
            status1.setSelected(funcionario.getAtivo());
            status2.setSelected(!funcionario.getAtivo());
            TFcep.setText(funcionario.getCep());
            CheckFax.setSelected(funcionario.getFaxineiro());
            CheckConf.setSelected(funcionario.getConfeiteiro());
            CheckAtend.setSelected(funcionario.getAtendente());
            String.valueOf(ComboBoxSexo.getSelectedItem());
        }
    }

    //Função que valida os TextFields que são obrigatórios
    private boolean validarCampos() {
        if (TFnome.getText().trim().isEmpty() || 
        TFcpf.getText().trim().isEmpty() || 
        TFcep.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Nome, CPF e CEP são obrigatórios.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return false; //retorna falso se ocorrer um erro
        }
        return true; //retorna true se tudo estiver correto
    }


    //Função de adicionar um funcionário
    private void adicionarFuncionario() {
        funcionario = new Funcionario(
            TFnome.getText().trim(),
            TFcpf.getText().trim(),
            status1.isSelected(),
            TFcep.getText().trim(),
            CheckFax.isSelected(),
            CheckConf.isSelected(),
            CheckAtend.isSelected(),
            String.valueOf(ComboBoxSexo.getSelectedItem())
        );
    }

    //Função para atualizar um funcionário
    private void atualizarFuncionario() {
        if (funcionario != null) {
            funcionario.setNome(TFnome.getText().trim());
            funcionario.setCpf(TFcpf.getText().trim());
            funcionario.setAtivo(status1.isSelected());
            funcionario.setCep(TFcep.getText().trim());
            funcionario.setFaxineiro(CheckFax.isSelected());
            funcionario.setConfeiteiro(CheckConf.isSelected());
            funcionario.setAtendente(CheckAtend.isSelected());
            funcionario.setSexo(String.valueOf(ComboBoxSexo.getSelectedItem()));
        }
    }

    //Função que retorna um funcionário
    public Funcionario getFuncionario() {
        return funcionario;
    }
}

