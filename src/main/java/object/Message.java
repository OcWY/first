package object;

/**
 * Created by yang on 07/05/16.
 */
public class Message {
    String name;
    String title;
    String sender;
    String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toJsonString(){
        String retVal;
        retVal="{ \"name\":\""+name+"\",\"title\":\""+title+"\",\"sender\":\""+sender+"\",\"url\":\""+url+"\""+"}";
        return retVal;
    }
}
