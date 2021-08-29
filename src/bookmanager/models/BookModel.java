package bookmanager.models;

import bookmanager.enums.GenreEnum;
import java.util.Date;


public class BookModel {
    
    public int id;
    public String title;
    public String synopsis;
    public String author;
    public GenreEnum genre;
    public Date releaseDate;

    public BookModel() { }

    public BookModel(int id, String title, String synopsis, String author, GenreEnum genre, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public BookModel(String title, GenreEnum genre) {
        this.title = title;
        this.genre = genre;
    }

    public BookModel(String title, String synopsis, String author, GenreEnum genre) {
        this.title = title;
        this.synopsis = synopsis;
        this.author = author;
        this.genre = genre;
    }

    public BookModel(int id, String title, String author, GenreEnum genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public BookModel(String title, String author) {
        this.title = title;
        this.author = author;
    }

}