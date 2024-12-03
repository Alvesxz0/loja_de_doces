import controllers.FuncController; //Importando o controller 

public class App { //Classe principal App
    public static void main(String[] args) throws Exception { //Função Main
        FuncController controller = new FuncController(); //Criando o objeto controller
        controller.iniciar(); //Iniciando o controller
    }
}
