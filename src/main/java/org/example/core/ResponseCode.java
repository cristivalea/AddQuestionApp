package org.example.core;

public class ResponseCode extends AbstractResponse{
    protected String code;

    public ResponseCode(int idRaspuns, String txt, String code) {
        super(idRaspuns, txt);
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code='" + code + '\'' +
                ", idRaspuns=" + idRaspuns +
                ", txt='" + txt + '\'' +
                '}';
    }

    public String getCode(){
        return this.code;
    }
}
