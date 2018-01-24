
package nz.co.twg;

public class EIMProperties {

    private String monitorLocation;
    private String maxNumOfFiles;
    private String maxFileAge;
    private String toEmailAddress;
    private String fromEmailAddress;
    private String fromEmailPassword;
    private String notificationBody;


    public String getMonitorLocation(){
        return monitorLocation;
    }

    public void setMonitorLocation(String monitorLocation){
        this.monitorLocation = monitorLocation;
    }

    public String getMaxNumOfFiles(){
        return maxNumOfFiles;
    }

    public void setMaxNumOfFiles(String maxNumOfFiles){
        this.maxNumOfFiles = maxNumOfFiles;
    }

    public String getMaxFileAge(){
        return maxFileAge;
    }

    public void setMaxFileAge(String maxFileAge){
        this.maxFileAge = maxFileAge;
    }

    public String getToEmailAddress(){
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress){
        this.toEmailAddress = toEmailAddress;
    }

    public String getFromEmailPassword(){
        return fromEmailPassword;
    }

    public void setFromEmailPassword(String fromEmailPassword){
        this.fromEmailPassword = fromEmailAddress;
    }

    public String getFromEmailAddress(){
        return fromEmailAddress;
    }

    public void setFromEmailAddress(String fromEmailAddress){
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getNotificationBody(){
        return notificationBody;
    }

    public void setNotificationBody(String notificationBody){
        this.notificationBody = notificationBody;
    }


}