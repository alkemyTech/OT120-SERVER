package com.alkemy.ong.enums;

public enum MailMessage {

    REGISTER_TITLE("Registro de cuenta: "),
    WELCOME_SUBJECT("Bienvenido/a a SOMOS MÁS!"),
    WELCOME_IMAGE("https://alkemy-ot120-server-m1.s3.sa-east-1.amazonaws.com/1641227537559-LOGO-SOMOS_MAS.png"),
    CONTACT_MAIL("Mail: somosfundacionmas@gmail.com"),
    CONTACT_FACEBOOK("Somos_Más"),
    CONTACT_INSTAGRAM("SomosMás"),
    CONTACT_PHONE("Teléfono de contacto: 1160112988");


    private String value;

    MailMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getWelcomeMsg(String firstName, String lastName){
        return "Bienvenido/a " + firstName + " " + lastName + ", gracias por haberte registrado en nuestro sitio web!";
    }

    public static String GetRegisterContactMsg(String firstName){
        return "Hola " + firstName + "! Gracias por tu registro en Somos Más!";
    }

}
