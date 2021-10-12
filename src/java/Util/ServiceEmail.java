/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rozo-
 */
public class ServiceEmail {
    //Dirección del servidor smtp, puede cambiarlo a su servidor de su cuenta particular, en el caso de gmail es smtp.gmail.com

    private final String direccionServidorEmail = "smtp.gmail.com";
    //Numéro del puerto del servidor smtp, en el caso de gmail es el 587
    private final String puertoServidor = "587";
    private Properties props = new Properties();
    //Dirección del email del usario que envía el mensaje
    private String emailUsuarioEmisor;
    //Contraseña del usuario que envía el correo electrónico
    private String claveEmailUsuarioEmisor;

    /**
     *
     * Crea un objeto para enviar correo electrónico a través de los servidores
     * de gmail
     *
     * @param emailUsuarioEmisor dirección email del usuario que envía el
     * mensaje
     * @param claveEmailUsuarioEmisor contraseña del usuario que envía el
     * mensaje
     */
    public ServiceEmail(String emailUsuarioEmisor, String claveEmailUsuarioEmisor) {
        this.emailUsuarioEmisor = emailUsuarioEmisor;
        this.claveEmailUsuarioEmisor = claveEmailUsuarioEmisor;
        inicializarPropiedades();
    }

    public ServiceEmail() {
    }

    private void inicializarPropiedades() {
        props.setProperty("mail.smtp.host", this.direccionServidorEmail);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", this.puertoServidor);
        props.setProperty("mail.smtp.user", this.emailUsuarioEmisor);
        props.setProperty("mail.smtp.auth", "true");
    }

    /**
     * Método que permite enviar un correo electrónico en texto plano
     *
     * @param receptor dirección email del usuario a quien se le envía el
     * mensaje
     * @param asunto asunto del correo electrónico
     * @param cuerpoMensaje cuerpo del mensaje del correo electrónico
     */
    public void enviarEmail(String receptor, String asunto, String cuerpoMensaje) {
        try {
            Session session = Session.getDefaultInstance(props);
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.emailUsuarioEmisor));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            message.setSubject(asunto);
            message.setText(cuerpoMensaje);
            Transport t = session.getTransport("smtp");
            t.connect(this.emailUsuarioEmisor, this.claveEmailUsuarioEmisor);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getClaveEmailUsuarioEmisor() {
        return claveEmailUsuarioEmisor;
    }

    public void setClaveEmailUsuarioEmisor(String claveEmailUsuarioEmisor) {
        this.claveEmailUsuarioEmisor = claveEmailUsuarioEmisor;
    }

    public String getEmailUsuarioEmisor() {
        return emailUsuarioEmisor;
    }

    public void setEmailUsuarioEmisor(String emailUsuarioEmisor) {
        this.emailUsuarioEmisor = emailUsuarioEmisor;
    }

}
