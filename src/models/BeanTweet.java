package models;

public class BeanTweet {

    private String title = "";
    private String tweet = "";
    private int privacity = 0;
    private int idtweet = 0;
    private int iduser = 0;
    private String user = "";
    private String datetweet = "";

    public int getIdtweet() {
        return idtweet;
    }

    public void setIdtweet(int idtweet) {
        this.idtweet = idtweet;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getDatetweet() {
        return datetweet;
    }

    public void setDatetweet(String datetweet) {
        this.datetweet = datetweet;
    }

    public int getPrivacity() {
        return privacity;
    }

    public void setPrivacity(int privacity) {
        this.privacity = privacity;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isComplete() {
        return(hasValue(getTweet()) && hasValue(getTitle()));
    }

    private boolean hasValue(String val) {
        return((val != null) && (!val.equals("")));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanTweet that = (BeanTweet) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (tweet != null ? !tweet.equals(that.tweet) : that.tweet != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (tweet != null ? tweet.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
