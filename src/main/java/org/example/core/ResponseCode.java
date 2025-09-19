package org.example.core;

public class ResponseCode extends AbstractResponse{
    protected String code;

    public ResponseCode(int idRaspuns, String txt, String code) {
        super(idRaspuns, txt);
        this.code = code;
    }
}
