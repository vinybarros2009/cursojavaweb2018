/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinarinformatica.sakilaweb.web;

import br.com.treinarinformatica.sakilaweb.model.Film;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ADM
 */
@Named
@ViewScoped
public class FilmsBean implements Serializable {    
    private List<Film> filmsList;
    // valores possiveis: CREATE, EDIT, LIST
    private String currentState;
    private Film film;
    
    @PostConstruct    
    /*anotacao que indica que o o objeto deve ser criado logo apos a classe film
    nao usar construtor padrao para instanciar objetos - framework gerencia criacao de objetos */   
    
    public void postConstruct () {
        
        filmsList = new ArrayList<>();
        Film film;
        
        film = new Film();
        film.setId(1);
        film.setTitle("Logan");
        filmsList.add(film);
        
        film = new Film();
        film.setId(2);
        film.setTitle("Vingadores");
        filmsList.add(film);
        
        currentState = "LIST";
    }
    
    public void newFilm() {
        film = new Film();
        currentState = "CREATE";
    }
    
    public void save() {
        
        FacesMessage msg = null;
        try {
            film.setId(filmsList.size()+1);
            filmsList.add(film);
            currentState = "LIST";
            film = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully",null);
            
            
        } catch (Exception ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to save film", null);
            ex.printStackTrace();
            
        } finally {
            if (msg != null) {
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
                
    }

    public List<Film> getFilmsList() {
        return filmsList;
    }

    public void setFilmsList(List<Film> filmsList) {
        this.filmsList = filmsList;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    
}
