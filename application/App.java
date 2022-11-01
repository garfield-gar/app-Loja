import java.util.Scanner;

public class App {

    public static  Produto produtogeral;
    public static void main(String[] args) throws Exception {
       // teste a conexão com mysql
       conexaomysql BancoDados= new conexaomysql();
       BancoDados.AbrirConexao();
       System.out.println(BancoDados.lastError);
       String inserirtCidade= "insert into cidade (nome) values('contagem')";
       BancoDados.ExecutadaQry(inserirtCidade);
       BancoDados.FechaConexao();
        if(BancoDados.lastCount==1)
            System.out.println("A cidade inserida no banco ");
    
        // Produto produtogeral;
        produtogeral = new Produto();
        char resposta = ' '; 
        Scanner Tecla = new Scanner(System.in);

        while (resposta != 'S')
        {
           ExibirMenu();
           resposta = Tecla.next().charAt(0);
           switch (resposta)
           {
            case 'E':  // entrada de estoque;
            case 'e':
                    EntradaEstoque(Tecla); 
            break; 
            case 'P':
            case 'p':
                      CadastrarProduto(Tecla);
            break;
            case  'A':
            case 'a':
                    SaldoEstoque();
            break;
         } // fim case
        } 
 

        Tecla.close(); 
       
    }
    private static void SaldoEstoque() {

        System.out.println("\nProduto: " + produtogeral.Nome
          +"\nMarca: "+produtogeral.Marca
          +"\nPreço: "+produtogeral.PrecoUnitario
          +"\nEstoque Mínimo: "+produtogeral.getEstoqueMinimo()
          +"\nSaldo Estoque:"+produtogeral.SaldoEstoque()
          );
    }
    private static void CadastrarProduto(Scanner Tecla2) {
        System.out.println("Nome do produto: ");
        // dados iniciais do produto
        String Aux = Tecla2.next(); Tecla2.nextLine();
        produtogeral.Nome = Aux;
        System.out.println("Marca: ");
        Aux = Tecla2.next(); Tecla2.nextLine();
        produtogeral.Marca = Aux;
        // preço, qtde, estominimo
        System.out.println("Preço unitário: ");
        Aux = Tecla2.next(); Tecla2.nextLine();
        Aux = Aux.replaceAll(",", ".");
        Double Valor = Double.parseDouble(Aux);
        produtogeral.PrecoUnitario = Valor;
        
        System.out.println("Estoque inicial: ");
        Aux = Tecla2.next(); Tecla2.nextLine();
        int Aux2 = Integer.parseInt(Aux);
        produtogeral.CreditarEstoque(Aux2);

        System.out.println("Estoque mínimo: ");
        Aux = Tecla2.next(); Tecla2.nextLine();
        Aux2 = Integer.parseInt(Aux);
        produtogeral.setEstoqueMinimo(Aux2);

        System.out.println("Produto inserido com sucesso!");
    }
    private static void EntradaEstoque(Scanner Tecla2)
    {
        System.out.println("Qual a qtde? ");
        int Qtde = Tecla2.nextInt();
        Tecla2.nextLine();
        produtogeral.CreditarEstoque(Qtde);

        System.out.println("\nEstoque atualizado! ");
    }

  
    {
        produtogeral.setEstoque(100);
    }

   
    
    private static void ExibirMenu() {
        System.out.println(
            "\n========== MENU OPÇÕES ========="
            +"\nP - Cadastrar Produto"
            +"\nE - Entrada Estoque"
            +"\nQ - Saída Estoque"
            +"\nA - Saber Estoque atual"
            +"\nV - Vender Produto"
            +"\nS - Sair do sistema"
            +"\n================================");
    }
}
