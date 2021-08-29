package bookmanager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import bookmanager.enums.GenreEnum;
import bookmanager.models.BookModel;
import bookmanager.models.UserModel;

public class AdminModule {
    
    private HashMap<String, Runnable> mainOptions;
    private BookManager bookManager;
    private LoginManager loginManager;

    public AdminModule(LoginManager loginManager, BookManager bookManager) {
        this.bookManager = bookManager;
        this.loginManager = loginManager;
    }

    public void start() {
        init();

        Console.clear();
        String option = null;

        do {
            Console.print("Olá " + loginManager.getFullName() + " o que deseja fazer?");
            Console.print("1 - Adicionar novo livro");
            Console.print("2 - Editar livro");
            Console.print("3 - Deletar livro");
    
            Console.print();
            Console.print("4 - Adicionar novo usuário");
            Console.print("5 - Excluir usuário");
            Console.print("Voltar - Voltar para a tela principal");
            
            try {
                option = Console.readLine("Digite sua opção: ");
                option = option.toLowerCase().trim();

                mainOptions.get(option).run();
            } catch(Exception e) {
                Console.print("Alternativa inválida!");
                Console.readLine("Tente novamente.");
                Console.clear();
            }
        } while(!option.equals("voltar"));

        Console.clear();
    }

    private void init() {
        mainOptions = new HashMap<String, Runnable>();

        mainOptions.put("1", () -> addBook());
        mainOptions.put("2", () -> updateBook());
        mainOptions.put("3", () -> deleteBook());
        mainOptions.put("4", () -> addUser());
        mainOptions.put("5", () -> deleteUser());
        mainOptions.put("voltar", () -> Console.print("Até a próxima :)."));
    }

    private void addBook() {
        Console.clear();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        boolean success = true;
        String title = null, synopsis = null, author = null;
        Date releaseDate = null;
        GenreEnum genre = null;

        do {
            try {
                title = Console.readLine("Digite o título do livro: ");
                synopsis = Console.readLine("Digite a sinopse do livro: ");
                author = Console.readLine("Digite o autor do livro: ");

                Console.print("Qual o gênero do livro?");
                Console.print("1 - Ação");
                Console.print("2 - Drama");
                Console.print("3 - Romance");
                Console.print("4 - Aventura");
                Console.print("5 - Ficção científica");
                Console.print("6 - Terror");
                genre = GenreEnum.fromInteger(Integer.parseInt(Console.readLine("Digite o gênero do livro: ")));
                releaseDate = format.parse(Console.readLine("Digite a data de lançamento do livro (Ex: 05/03/2001): "));
            } catch(Exception e) {
                success = false;
                Console.readLine("Valores inseridos incorretos.");
                String resp = Console.readLine("Deseja tentar novamente? (n - não | s - sim):");
                Console.clear();
                if(resp.toLowerCase().trim().equals("n")) {
                    return;
                }
            }
        } while(!success);
        
        if(bookManager.insertBook(title, author, synopsis, genre, releaseDate)) {
            Console.readLine("Livro adicionado com sucesso!");
        } else {
            Console.readLine("Erro ao adicionar o livro. Contate o administrador de seu sistema!");
        }
        Console.clear();
    }

    private void updateBook() {
        Console.clear();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        boolean success = true;
        String title = null, synopsis = null, author = null;
        Date releaseDate = null;
        GenreEnum genre = null;

        String bookName = Console.readLine("Digite o nome do livro a ser atualizado: ");
        BookModel book = searchBookByName(bookName);
        Console.clear();

        do {
            try {
                Console.print("Deixa um linha em branco caso não queira editar!");
                title = Console.readLine("Digite o título do livro: ");
                synopsis = Console.readLine("Digite a sinopse do livro: ");
                author = Console.readLine("Digite o autor do livro: ");

                Console.print("Qual o gênero do livro?");
                Console.print("1 - Ação");
                Console.print("2 - Drama");
                Console.print("3 - Romance");
                Console.print("4 - Aventura");
                Console.print("5 - Ficção científica");
                Console.print("6 - Terror");
                String g = Console.readLine("Digite o gênero do livro: ");
                if(g != "") {
                    genre = GenreEnum.fromInteger(Integer.parseInt(g));
                }
                String d = Console.readLine("Digite a data de lançamento do livro: ");
                if(d != "") {
                    releaseDate = format.parse(d);
                }
            } catch(Exception e) {
                success = false;
                Console.readLine("Valores inseridos incorretos.");
                String resp = Console.readLine("Deseja tentar novamente? (n - não | s - sim):");
                Console.clear();
                if(resp.toLowerCase().trim().equals("n")) {
                    return;
                }
            }
        } while(!success);
        
        if(bookManager.updateBook(book.id, title, author, synopsis, genre, releaseDate)) {
            Console.readLine("Livro atualizado com sucesso!");
        } else {
            Console.readLine("Erro ao atualizar o livro. Contate o administrador de seu sistema para resolver o problma!");
        }
        Console.clear();
    }

    private void deleteBook() {
        Console.clear();
        String bookName = Console.readLine("Digite o nome do livro a ser excluído: ");
        BookModel book = searchBookByName(bookName);

        if(book == null) {
            return;
        }
        if(bookManager.deleteBookById(book.id)) {
            Console.readLine("Livro deletado com sucesso!");
        } else {
            Console.readLine("Falha ao deletar livro. Contate o administrador do seu sistema para resolver o problma!");
        }
    }

    private void addUser() {
        Console.clear();
        boolean success = true;
        
        String name, username, password, admin;
        boolean isAdmin;
            
        do {
            success = true;
            name = Console.readLine("Insira o nome completo do usuário: ");
            username = Console.readLine("Insira o nome de usuário para login: ");
            password = Console.readLine("Insira a senha do usuário: ");
            admin = Console.readLine("O usuário será um administrador? (s - sim | n - não | padrão - não): ");
            if(admin.toLowerCase().trim().equals("s")) {
                isAdmin = true;
            } else {
                isAdmin = false;
            }
            if(name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                success = false;
            }
        } while(!success);

        if(loginManager.insertUser(name, username, password, isAdmin)) {
            Console.readLine("Usuário adicionado com sucesso!");
        } else {
            Console.readLine("Falha ao adicionar novo usuário. Contato o administrador do sistema para mais detalhes");
        }
        Console.clear();
    }

    private void deleteUser() {
        Console.clear();
        String userName = Console.readLine("Digite o nome do usuário a ser excluído: ");
        UserModel user = searchUserByName(userName);

        if(user == null) {
            return;
        }
        if(loginManager.deleteUserById(user.id)) {
            Console.readLine("Usuário deletado com sucesso!");
        } else {
            Console.readLine("Falha ao deletar livro. Contate o administrador do seu sistema para resolver o problma!");
        }
    }

    private BookModel searchBookByName(String bookName) {
        Console.clear();
        List<BookModel> books = bookManager.getAllByName(bookName);
        
        Console.printf("%-6s %-40s %-30s %-15s", "Número", "Título", "Autor", "Gênero");
        for(int i = 0; i < books.size(); i++) {
            BookModel b = books.get(i);
            Console.printf("%-6s %-40s %-30s %-15s", i+1, b.title, b.author, b.genre);
        }

        Console.print();
        String response = Console.readLine("Digite o número de algum livro para deletá-lo do sistema: ");
        try {
            return books.get(Integer.parseInt(response) - 1);
        } catch (Exception e) {
            Console.readLine("Número inválido!");
        }
        Console.clear();
        return null;
    }

    private UserModel searchUserByName(String userName) {
        Console.clear();
        List<UserModel> users = loginManager.getAllByName(userName);
        
        Console.printf("%-6s %-30s %-30s", "Número", "Nome", "Username");
        for(int i = 0; i < users.size(); i++) {
            UserModel u = users.get(i);
            Console.printf("%-6s %-30s %-30s", i+1, u.fullName, u.username);
        }

        Console.print();
        String response = Console.readLine("Digite o número do usuário que deseja deletar do sistema: ");
        try {
            return users.get(Integer.parseInt(response) - 1);
        } catch (Exception e) {
            Console.readLine("Número inválido!");
        }
        Console.clear();
        return null;
    }

}
