package nz.co.twg;


public class yamlBean {
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
        int equalSign = toEmail.indexOf("=");
        this.recipientEmail = toEmail.substring(equalSign + 1);
    }

    public String getFromEmail(){
        return senderEmail;
    }

    public void setFromEmail(String fromEmail){
        int equalSign = fromEmail.indexOf("=");
        this.senderEmail = fromEmail.substring(equalSign + 1);
    }

    public String getEmailContent(){
        return emailMessage;
    }

    public void setEmailContent(String emailContent){
        int equalSign = emailContent.indexOf("=");
        this.emailMessage = emailContent.substring(equalSign + 1);
    }

    public yamlBean() {
    }
}
