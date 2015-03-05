package com.novomatic.applicationfragment5;

/**
 * Created by fpirazzi on 02/03/2015.
 */
public class Prospect {

    private boolean isMaster;
    private int intermediario_id;
    private int prospectID;
    private String denominazione;
    private String telefono;
    private String toponimo;
    private String nCivico;
    private String CAP;
    private String comune;
    private String codice;
    private int indirizzo_id;
    private int contratti;

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public int getIntermediario_id() {
        return intermediario_id;
    }

    public void setIntermediario_id(int intermediario_id) {
        this.intermediario_id = intermediario_id;
    }

    public int getProspectID() {
        return prospectID;
    }

    public void setProspectID(int prospectID) {
        this.prospectID = prospectID;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getToponimo() {
        return toponimo;
    }

    public void setToponimo(String toponimo) {
        this.toponimo = toponimo;
    }

    public String getnCivico() {
        return nCivico;
    }

    public void setnCivico(String nCivico) {
        this.nCivico = nCivico;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public int getIndirizzo_id() {
        return indirizzo_id;
    }

    public void setIndirizzo_id(int indirizzo_id) {
        this.indirizzo_id = indirizzo_id;
    }

    public int getContratti() {
        return contratti;
    }

    public void setContratti(int contratti) {
        this.contratti = contratti;
    }
}
