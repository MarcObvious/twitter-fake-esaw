package models;

public class BeanNewtweet {

	private String title = "";
    private String tweet = "";
    private int privacity = 0;
    private String user = "";

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

        BeanNewtweet that = (BeanNewtweet) o;

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
