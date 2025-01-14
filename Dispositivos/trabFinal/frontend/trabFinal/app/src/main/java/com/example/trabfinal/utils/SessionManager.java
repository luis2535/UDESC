package com.example.trabfinal.utils;

import com.example.trabfinal.model.UsuarioResponse;

public class SessionManager {
    private static SessionManager instance;
    private UsuarioResponse usuarioResponse;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public UsuarioResponse getUsuarioResponse() {
        return usuarioResponse;
    }

    public void setUsuarioResponse(UsuarioResponse usuarioResponse) {
        this.usuarioResponse = usuarioResponse;
    }
}