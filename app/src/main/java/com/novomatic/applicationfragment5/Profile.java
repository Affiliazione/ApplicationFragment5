package com.novomatic.applicationfragment5;

/**
 * Created by fpirazzi on 05/03/2015.
 */
public class Profile {

    private String nome;
    private String cognome;
    private int intermediarioId;
    private int userID;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getIntermediarioId() {
        return intermediarioId;
    }

    public void setIntermediarioId(int intermediarioId) {
        this.intermediarioId = intermediarioId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
