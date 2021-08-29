package bookmanager;

import bookmanager.models.BookModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {

    private static LoginManager loginManager;
    private static MySQLConnection con;
    private static Map<String, Runnable> mainOptions = new HashMap<>();

    public static void main(String[] args) throws Exception {
        init();
        
        String username, password;
        boolean logged;
        String asnwer;

        // INICIO
        
        Console.print("Bem vindo ao gerenciador de livros do IFRJ.");
        Console.print("Para confirmar que você tem permissão para acessar nossos produtos, precisamos que sua identidade seja confirmada.");
        Console.print("Somente frequêntantes da escola podem acessar.");
        Console.print("Este catálogo permite que você fique sabendo dos livros que temos em nossa biblioteca para que seja possível pegá-los emprestados presencialmente.");
        Console.readLine("Pressione enter para continuar!");
        
        do {
            Console.clear();
            Console.print("--------------- Login ---------------");
            username = Console.readLine("Usuário: ");
            password = Console.readLine("Senha: ");

            logged = loginManager.login(username.toLowerCase().trim(), password.trim());
            Console.print("logando...");
            Thread.sleep(500);
            if(!logged) {
                Console.readLine("Usuário inválido! Pressione enter para tentar novamente!");
            }
        } while(!logged);

        Console.clear();
        Console.print("Bem vindo, " + loginManager.getFullName() + "!");
        if(loginManager.isAdmin()) {
            Console.print();
            Console.print("Olá! Notamos que você possui uma conta de administrador.");
            Console.print("Um menu especial para realizar alterações em nossos produtos será estará a sua disposição :).");
            Console.print();
            Thread.sleep(1000);
        }

        do {
            Console.print("O que você deseja fazer?");
            Console.print("1 - Acessar catalogo de livros ordenados pelo nome.");
            Console.print("2 - Acessar catalogo de livros pelo autor.");
            Console.print("3 - Pesquisar livro pelo nome.");
            Console.print("sair - Sair da aplicação.");
            if(loginManager.isAdmin()) Console.print("admin - Menu especial para os administradores do sistema.");    
            
            asnwer = (Console.readLine("Digite sua escolha: ")).toLowerCase().trim();
            Console.print("");
            try {
                mainOptions.get(asnwer).run();
            } catch (Exception e) {
                Console.print("Opção inválida!");
                Console.readLine("Pressione enter para tentar novamente!");
            }
        } while(!asnwer.equals("sair"));        
    }

    private static void init() {
        try {
            con = new MySQLConnection("localhost", "user", "password", "bookmanager");
            BookManager bookManager = new BookManager(con);
            loginManager = new LoginManager(con);

            mainOptions.put("1", () -> booksByName(bookManager));
            mainOptions.put("2", () -> booksByAuthor(bookManager));
            mainOptions.put("3", () -> searchBookByName(bookManager));
            mainOptions.put("admin", () -> (new AdminModule(loginManager, bookManager)).start());
            mainOptions.put("sair", () -> Console.print("O aplicativo será finalizado. Obrigado por utilizar!"));
        } catch(Exception e) {
            Console.print("Falha ao iniciar o sistema. " + e.getMessage());
            return;
        }
    }

    private static void booksByName(BookManager manager) {
        List<BookModel> books = manager.getAllOrderByName();

        Console.clear();
        Console.printf("%-9s %-40s %-30s %-15s", "Número", "Título", "Autor", "Gênero");
        for(int i = 0; i < books.size(); i++) {
            BookModel b = books.get(i);
            Console.printf("%-9s %-40s %-30s %-15s", i+1, b.title, b.author, b.genre);
        }
        
        Console.print();
        String response = Console.readLine("Digite o número de algum livro para obter as informações completas. Pressione enter para continuar: ");
        Console.clear();
        try {
            BookModel model = books.get(Integer.parseInt(response) - 1);
            Console.print();
            Console.print("Título: " + model.title);
            Console.print("Autor: " + model.author);
            Console.print("Gênero: " + model.genre);
            Console.print("Data de lançamento: " + model.releaseDate);
            Console.print("Sinopse: " + model.synopsis);

            Console.readLine("Pressione enter para continuar.");
        } catch (Exception e) { }
        Console.clear();
    }

    private static void booksByAuthor(BookManager manager) {
        List<BookModel> books = manager.getAllOrderByAuthor();
        
        Console.clear();
        Console.printf("%-9s %-30s %-40s %-15s", "Número", "Autor", "Título", "Gênero");
        for(int i = 0; i < books.size(); i++) {
            BookModel b = books.get(i);
            Console.printf("%-9s %-30s %-40s %-15s", i+1, b.author, b.title, b.genre);
        }
        
        Console.print();
        String response = Console.readLine("Digite o número de algum livro para obter as informações completas. Pressione enter para continuar: ");
        Console.clear();
        try {
            BookModel model = books.get(Integer.parseInt(response) - 1);
            Console.print();
            Console.print("Título: " + model.title);
            Console.print("Autor: " + model.author);
            Console.print("Gênero: " + model.genre);
            Console.print("Data de lançamento: " + model.releaseDate);
            Console.print("Sinopse: " + model.synopsis);

            Console.readLine("Pressione enter para continuar.");
        } catch (Exception e) { }
        Console.clear();
    }

    private static void searchBookByName(BookManager manager) {
        Console.clear();
        String bookName = Console.readLine("Digite o nome do livro que deseja procurar: ");
        List<BookModel> books = manager.getAllByName(bookName);
        
        if(books == null || books.size() == 0) {
            Console.print("Nenhum livro encontrado");
        } else {
            Console.printf("%-9s %-40s %-30s %-15s", "Número", "Título", "Autor", "Gênero");
        }
        for(int i = 0; i < books.size(); i++) {
            BookModel b = books.get(i);
            Console.printf("%-9s %-40s %-30s %-15s", i+1, b.title, b.author, b.genre);
        }

        Console.print();
        String response = Console.readLine("Digite o número de algum livro para obter as informações completas. Pressione enter para continuar: ");
        Console.clear();
        try {
            BookModel model = books.get(Integer.parseInt(response) - 1);
            Console.print();
            Console.print("Título: " + model.title);
            Console.print("Autor: " + model.author);
            Console.print("Gênero: " + model.genre);
            Console.print("Data de lançamento: " + model.releaseDate);
            Console.print("Sinopse: " + model.synopsis);

            Console.readLine("Pressione enter para continuar.");
        } catch (Exception e) { }
        Console.clear();
    }

}
