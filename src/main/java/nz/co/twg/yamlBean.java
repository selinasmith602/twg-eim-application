package nz.co.twg;

import java.util.regex.*;

public class yamlBean implements yamlDAO{
    private String folder;
    private String recipientEmail;
    private String senderEmail;
    private String emailMessage;
    private int fileLimit;
    private int ageLimit;

    public String getFolder(){
        return folder;
    }

    public void setFolder(final String foldername) {
        this.folder = foldername;
    }

    public int getFileLimit() {
        return fileLimit;
    }

    public void setFileLimit(final String limit) {
        this.fileLimit = Integer.parseInt(limit.replaceAll("[\\D]", ""));
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(final String age) {
        this.ageLimit = Integer.parseInt(age.replaceAll("[\\D]", ""));
    }

    public String getToEmail(){
        return recipientEmail;
    }

    public void setToEmail(String toEmail){
        this.recipientEmail = toEmail.replaceAll("(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)", "");
        //Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(toEmail);
        //this.recipientEmail = toEmail;
    }

    public String getFromEmail(){
        return senderEmail;
    }

    public void setFromEmail(String fromEmail){
        this.senderEmail = fromEmail;
    }

    public String getEmailContent(){
        return emailMessage;
    }

    public void setEmailContent(String emailContent){
        this.emailMessage = emailContent;
    }

    public yamlBean() {
    }
}

