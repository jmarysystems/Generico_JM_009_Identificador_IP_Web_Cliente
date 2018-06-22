package identificador_ip_web_cliente;

import br.com.jmary.utilidades.JOPM;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author pc
 */
public class Ler_Email {
    
    public String ip_do_servidor = ""; 
         
    //Email_Home.list_cmd.get(0)  --  E-mail do destinatário 
    //Email_Home.list_cmd.get(1)  --  Senha do Remetente
    //Email_Home.list_cmd.get(2)  --  E-mail do Remetente 
    //Email_Home.list_cmd.get(3)  --  Usuário do E-mail 
    public List<String> list_cmd;
    public List<File>   fileMailsRecebido;
    
    public Ler_Email( List<String> list_cmd2, List<File> fileMails2 ) {

        list_cmd = list_cmd2;
        fileMailsRecebido = fileMails2;
                
        inicio();
    }
    
    private void inicio(){ //System.out.println( "private void inicio(){" ); 
        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
        
            iniciar();
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Materias_Home_Inicio" ); } //} }.start(); 
                
    }
    
    //Verificar Lista de Comandos
    private void iniciar(){ //System.out.println( "private void iniciar(){" ); 
        
        if( !list_cmd.isEmpty() ) {
            
            verificar_o_email_a_ser_enviado();
                                        
        }
        else{
            
            //Nenhum Comando Recebido
            JOPM JOPM = new JOPM( "MÉTODO: " + "iniciar()\n"
                ,"\nLista de Comandos referentes a impressão está vazia!" 
                ,this.getClass().getName()
            ); 
        }
    }  
    
    //cmd_0 = Endereço local do Arquivo a ser impresso
    public void verificar_o_email_a_ser_enviado(){   //System.out.println( "public void verificar_o_email_a_ser_enviado(){" );        
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
           
            String cmd_0 = list_cmd.get(0); 
            
            if( !cmd_0.trim().equals("") ) {
                                
                verificar_a_senha_do_email();
            }
            else{
            
                //Nenhum Comando Recebido
                JOPM JOPM = new JOPM( "MÉTODO: " + "verificar_o_email_a_ser_enviado()\n"
                    ,"\nO E-mail do destinatário está vazio!" 
                    ,this.getClass().getName()
                ); 
            }
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "verificar_o_email_a_ser_enviado()\n"
                    ,"\nErro ao tentar ler o E-mail do destinatário - cmd_0" 
                    ,this.getClass().getName() ); 
        } //} }.start();                   
    }
    
    //cmd_1 = Verificar o nome da impressora a ser impresso o documento
    public void verificar_a_senha_do_email(){  //System.out.println( "public void verificar_a_senha_do_email(){" );          
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
           
            String cmd_1 = list_cmd.get(1); 
            
            if( !cmd_1.trim().equals("") ) {
                                
                mailJava();
            }
            else{
            
                //Nenhum nome de impressora Recebido
                JOPM JOPM = new JOPM( "MÉTODO: " + "verificar_a_senha_do_email()\n"
                    ,"\nA Senha do E-mail do remetente está vazio!" 
                    ,this.getClass().getName()
                ); 
            }
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "verificar_a_senha_do_email()\n"
                    ,"\nErro ao tentar ler a senha do E-mail do remetente - cmd_1" 
                    ,this.getClass().getName() ); 
        } //} }.start();                 
    }
    
    //indica se o formato de texto será texto ou html
    public String TYPE_TEXT_PLAIN = "text/plain";
    public String TYPE_TEXT_HTML = "text/html";
    
    //indica qual será o servidor de email(gmail, hotmail...)        
    public String smtpHostMail;
    //indica a porta de acesso ao servidor
    public String smtpPortMail;
    //indica que a necessidade de autenticação no servidor(true ou false)
    public String smtpAuth;
    //indica ao servidor que ele está recebendo uma conexão segura
    public String smtpStarttls;
    //nome do remetente do email
    public String fromNameMail;
    //email do remetente
    public String userMail;
    //senha do email do remetente
    public String passMail;
    //assunto do email
    public String subjectMail;
    //corpo do email, onde estará o texto da mensagem
    public String bodyMail;
    //lista com email e nome dos destinatários
    public Map<String, String> toMailsUsers;
    //lista contendo os arquivos anexos
    public List<File> fileMails;
    //charset, no caso de html é necessário
    public String charsetMail;
    //tipo do formato da mensagem, texto ou html
    public String typeTextMail;
    public void mailJava(){   //System.out.println( "public void mailJava(){" );       
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
            /*Configuração de servidores * 
             Servidor       smtpHostMail       smtpPortMail    smtpStarttls
                Gmail      smtp.gmail.com	  587	         true
                Bol        smtps.bol.com.br	  587	         true
                Ibest	   smtp.ibest.com.br	  587	         true
                IG	   smtp.ig.com.br	  587	         true
                Hotmail    smtp.live.com	  25	         true
            **/
           
             smtpHostMail = "smtp.gmail.com";
             smtpPortMail = "587";
             smtpAuth     = "true";
             smtpStarttls = "true";
             fromNameMail = list_cmd.get(3);     //Usuário do E-mail //"subzero3463.01"
             userMail     = list_cmd.get(2);                //E-mail //"subzero3463.01@gmail.com"
             passMail     = list_cmd.get(1);                 //Senha //"subzero3463"
             subjectMail  = list_cmd.get(4);                         //Assunto do E-mail
             subjectMail = "ip - " + getIP();
             bodyMail     = htmlMessage();
             
             toMailsUsers = new HashMap<String, String>();
             toMailsUsers.put( list_cmd.get(0), "email gmail" ); //Email_Home.list_cmd.get(0)  --  "jmarysystems@gmail.com"
             
/********************************************/
             toMailsUsers.put( list_cmd.get(0) , "FF" );

/********************************************/
             
             fileMails    = fileMailsRecebido; 
             
             charsetMail  = "ISO-8859-1"; 
             typeTextMail = TYPE_TEXT_HTML;
            
             autenticar();
             
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "mailJava()\n"
                    ,"\nErro ao tentar setar dados da lista de comandos" 
                    ,this.getClass().getName() ); 
        } //} }.start();                 
    }
    
    private String htmlMessage() {
        GregorianCalendar gc = new GregorianCalendar();

        return 
       "<html>\n" +
       "\t<head>\n" +
       "\t\t<title>" + "Cleilson Henrique de Araujo" + "</title> \n" +
       "\t</head>\n" +
       "\t<body>\n" +              
           
       "<hr>" +         
       "<table align='center' width='100%' border='0' cellpadding='0' cellspacing='0' > " +
                
            "<tr>" +   
                "<td style='vertical-align: central;'>" 
                    + "<div style=\"font-size:18.8px\"><span style=\"font-size:18.8px\">"
                            
                        + "<div style=\"font-size:12.8px\"><span style=\"font-size:12.8px\"><br></span></div>"
                        + "<div style=\"font-size:12.8px\"><span style=\"font-size:12.8px\"><br></span></div>"
                    + "</span>" + "</div>"
                +"</td>" +
            "</tr>" + 
                
            "<tr>" +   
                "<td style='vertical-align: central;'>" 
                    + "<div style=\"font-size:18.8px\"><span style=\"font-size:18.8px\">"
                
                            + "Cleilson Henrique de Araujo"
                
                        + "</span>" + "</div>"
                    + "<div style=\"font-size:12.8px\"><span style=\"font-size:12.8px\"><br></span></div>"
                +"</td>" +
            "</tr>" +         
                
            "<tr>" +   
                "<td style='vertical-align: central;'>" 
                    +"<div style=\"font-size:12.8px\"><h1 style=\"line-height:28.8px;margin-bottom:1px;border-top:1px solid rgb(238,238,238);background:none 0% 0% repeat scroll rgb(248,248,248);padding:10px\">"
                        
                        + "<font color=\"#424242\" face=\"Arial, Georgia, serif\" size=\"5\">"               
                            
                        + "</font>"
                
                        + "<font face=\"Arial, Georgia, serif\" color=\"#424242\" style=\"font-size:12.8px\"><br></font></h1>"
                        + "<div style=\"font-size:12.8px\"><span style=\"font-size:12.8px\"><br></span></div>"
                        + "<div style=\"font-size:12.8px\"><span style=\"font-size:12.8px\"><br></span></div>"
                
                        + "<div style=\"font-size:18.8px\"><span style=\"font-size:18.8px\">"
                            + "Cleilson Henrique de Araujo"
                        + "</span>" + "</div>"
                
                    + "</div>"
                +"</td>" +
            "</tr>" +  
  
       "</table>" +         
                              
                
       "\t</body> \n" +
       "</html>";
    }
    
    public Authenticator auth;
    public void autenticar(){  //System.out.println( "public void autenticar(){" );      
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
           
            //classe anonima que realiza a autenticação
            //do usuario no servidor de email.
            auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                                   userMail, passMail                                                                                                                                                                                                                      
                 );
            } };
            
            proprerties();
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "autenticar()\n"
                    ,"\nErro ao tentar setar a autenticação" 
                    ,this.getClass().getName() ); 
        } //} }.start();                 
    }
    
    
    Properties props;
    public void proprerties(){ //System.out.println( "public void proprerties(){" );       
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
           
            props = new Properties();
            props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com"); //yes
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable","true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.mime.charset", "ISO-8859-1");
            
            props.setProperty("mail.smtp.socketFactory.port", "587");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            
            ////////////////////////////////////////////////////////
            props.setProperty("mail.imap.ssl.trust", "imap.gmail.com"); //yes            
            props.setProperty("mail.imap.host", "imap.gmail.com");
            props.setProperty("mail.imap.auth", "true");
            props.setProperty("mail.imap.starttls.enable","true");
            props.setProperty("mail.imap.port", "993");            
            props.setProperty("mail.imap.socketFactory.class",  "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.imap.socketFactory.fallback", "false");
            props.setProperty("mail.store.protocol", "imaps");
            
            //props.setProperty("mail.transport.protocol", "imap");
            props.setProperty("mail.mime.charset", "ISO-8859-1");
            ////////////////////////////////////////////////////////
            
            sessao();
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "proprerties()\n"
                    ,"\nErro ao tentar setar as propriedades" 
                    ,this.getClass().getName() ); 
        } //} }.start();                 
    }
    
    Session session;
    public void sessao(){  //System.out.println( "public void sessao(){" );      
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
           
            // Cria a sessao passando as propriedades e a autenticação
            session = Session.getDefaultInstance(props, auth);
            //session.setDebug(true);
            
            criar_mensagem_e_setar_remetentes();
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "sessao()\n"
                    ,"\nErro ao tentar setar a sessão" 
                    ,this.getClass().getName() ); 
        } //} }.start();                 
    }
    
    Message msg;
    public void criar_mensagem_e_setar_remetentes(){ //System.out.println( "public void criar_mensagem_e_setar_remetentes(){" );       
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 
           
            //cria a mensagem setando o remetente e seus destinatários
            msg = new MimeMessage(session);
            //aqui seta o remetente
            msg.setFrom(new InternetAddress(userMail, fromNameMail));
            
            boolean first = true;            
            for (Map.Entry<String, String> map : toMailsUsers.entrySet()) {
                if (first) {
                    //setamos o 1° destinatario
                    msg.addRecipient(Message.RecipientType.TO,
                              new InternetAddress(map.getKey(), map.getValue())
                     );
                    first = false;
                } else {
                    //setamos os demais destinatarios
                    msg.addRecipient(Message.RecipientType.CC,
                              new InternetAddress(map.getKey(), map.getValue())
                     );
                }
        
            }
            
            msg.getReceivedDate();
            // Adiciona um Assunto a Mensagem
            msg.setSubject(subjectMail);
            
            msg.setText("blabla");
                        
            // Cria o objeto que recebe o texto do corpo do email
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(bodyMail, typeTextMail);
            // Monta a mensagem SMTP  inserindo o conteudo, texto e anexos
            
            Multipart mps = new MimeMultipart();
            /*for (int index = 0; index < fileMails.size(); index++) {
                // Cria um novo objeto para cada arquivo, e anexa o arquivo
                MimeBodyPart attachFilePart = new MimeBodyPart();
                FileDataSource fds =   new FileDataSource(
                    fileMails.get(index)
                );
                attachFilePart.setDataHandler(new DataHandler(fds));
                attachFilePart.setFileName(fds.getName());
                //adiciona os anexos da mensagem
                mps.addBodyPart(attachFilePart, index);
            }                                
            */
            
            //adiciona o corpo texto da mensagem
            mps.addBodyPart(textPart);
            //adiciona a mensagem o conteúdo texto e anexo
            msg.setContent(mps);
            
            enviar_mensagem();
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "criar_mensagem_e_setar_remetentes()\n"
                    ,"\nErro ao tentar criar_mensagem_e_setar_remetentes" 
                    ,this.getClass().getName() ); 
        } //} }.start();                 
    }
    
    public void enviar_mensagem(){ System.out.println( "public void enviar_mensagem(){" );         
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 ); 

            //System.out.println( "\n" );
            ler_mensagem();
            //System.out.println( "\n" );
        
            //Transport.send(msg);
            
        } catch( Exception e ){ JOPM JOPM = new JOPM( "MÉTODO: " + "enviar_mensagem()\n"
                    ,"\nErro ao tentar enviar_mensagem" 
                    ,this.getClass().getName() ); 

            e.printStackTrace();
        } //} }.start();                 
    }
    
    public void ler_mensagem(){ System.out.println( "public void ler_mensagem(){" );       
        /*new Thread() {   @Override public void run() {*/ try { Thread.sleep( 1 );             
            try{
                Store store = session.getStore("imap");
                store.connect("imap.gmail.com", list_cmd.get(2), list_cmd.get(1));
                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_WRITE);
                int count = inbox.getMessageCount();
                Message[] messages = inbox.getMessages(1, count);
                for (Message message : messages) {
                    try {
                        //message.setFlag(Flags.Flag.DELETED, true);
                        ip_do_servidor = message.getSubject();
                        //System.out.println( "2 - ip_do_servidor - " + ip_do_servidor );
                        
                    } catch (Exception ex) { System.out.println("Error reading content!!"); ex.printStackTrace(); }
                }

            } catch( Exception e ){ e.printStackTrace(); }
            
        } catch( Exception e ){ e.printStackTrace(); } //} }.start();                 
    }
    
    List<String> lista_de_IP = new ArrayList<String>();
    public String getIP(){
        String IPreturn = "";
         try{
                        
            IPreturn = "Não Enviado";
            
        }catch(Exception e){
            e.printStackTrace();
        }
 
     return IPreturn;
 
    }
    
    
    public static void main( String args[] ){
        
        try{ while(true){ Thread.sleep( 300000 ); /* 300000 = 5 minutos */
            /*
            List<String> list_cmd = new ArrayList();
            list_cmd.add( "jmarysystems"+"@gmail.com" );                               //Destinatário "jmarysystems@gmail.com"
            list_cmd.add( "subzero3463" );                                  //Senha //"subzero3463"
            list_cmd.add( "subzero3463.01@gmail.com" );                    //E-mail //"subzero3463.01@gmail.com"  -  "pretim21@gmail.com"
            list_cmd.add( "Cleilson Henrique de Araujo" );          //nome do remetente do email //"subzero3463.01"
            list_cmd.add( "Auxiliar de Produção - Fábrica" );                                              //Assunto do E-mail
        
            List<String> fileMails = new ArrayList<String>();                       //lista contendo os arquivos anexos
            String arquivo1 = Imagens.class.getResource( "/anexos/CLEILSON_HENRIQUE_DE_ARAUJO" + ".pdf" ).getFile();
            fileMails.add(arquivo1);
        
            Ler_Email Ler_Email = new Ler_Email( list_cmd, fileMails, new Home() );  
            */
        } }catch( Exception e ){}
    }
        
}
