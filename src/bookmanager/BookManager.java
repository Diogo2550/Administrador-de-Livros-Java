package bookmanager;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bookmanager.enums.GenreEnum;
import bookmanager.models.BookModel;

public class BookManager {
    
    private MySQLConnection con;
    
    public BookManager(MySQLConnection con) {
        this.con = con;
    }

    public List<BookModel> getAllOrderByName() {
        String query = "SELECT * FROM books ORDER BY title";
        
        List<BookModel> models = MySQLConnection.resultSetToList(con.selectQuery(query), "BookModel");
        return models;     
    }

    public List<BookModel> getAllOrderByAuthor() {
        String query = "SELECT * FROM books ORDER BY author, title";
        
        List<BookModel> models = MySQLConnection.resultSetToList(con.selectQuery(query), "BookModel");
        return models;
    }

    public List<BookModel> getAllByName(String name) {
        String query = "SELECT * FROM books WHERE title LIKE '%" + name + "%'";

        List<BookModel> models = MySQLConnection.resultSetToList(con.selectQuery(query), "BookModel");
        return models;
    }

    public boolean insertBook(String title, String author, String synopsis, GenreEnum genre, Date releaseDate) {
        String fields = "", values = "";
        if(!title.isEmpty()) {
            fields += ", title"; values += ", '" + title + "'";
        }
        if(!author.isEmpty()) {
            fields += ", author"; values += ", '" + author + "'";
        }
        if(!synopsis.isEmpty()) {
            fields += ", synopsis"; values += ", '" + synopsis + "'";
        }
        if(genre != null) {
            fields += ", genre"; values += ", " + (genre.ordinal() + 1);
        }
        if(releaseDate != null) {
            fields += ", releaseDate"; values += ", '" + (new SimpleDateFormat("yyyy-mm-dd")).format(releaseDate) + "'";
        }
        String query = "INSERT INTO books(id" + fields + ") VALUES (DEFAULT" + values + ")";
        
        int result = con.insertQuery(query);
        if(result != 0) {
            return true;
        }
        return false;
    }

    public boolean updateBook(int id, String title, String author, String synopsis, GenreEnum genre, Date releaseDate) {
        String set = "";
        if(!title.isEmpty()) {
            set += ", title='" + title + "'";
        }
        if(!author.isEmpty()) {
            set += ", author='" + author + "'";
        }
        if(!synopsis.isEmpty()) {
            set += ", synopsis='" + synopsis + "'";
        }
        if(genre != null) {
            set += ", genre=" + (genre.ordinal() + 1);
        }
        if(releaseDate != null) {
            set += ", releaseDate='" + (new SimpleDateFormat("yyyy-mm-dd")).format(releaseDate) + "'";
        }
        String query = "UPDATE books SET id=" + (id) + set + " WHERE id=" + id;
        
        int result = con.insertQuery(query);
        if(result != 0) {
            return true;
        }
        return false;
    }

    public boolean deleteBookById(int id) {
        String query = "DELETE FROM books WHERE id=" + id;
        int result = con.insertQuery(query);
        if(result != 0) {
            return true;
        }
        return false;
    }

}
