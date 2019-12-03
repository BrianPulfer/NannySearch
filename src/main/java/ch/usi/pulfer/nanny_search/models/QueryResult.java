package ch.usi.pulfer.nanny_search.models;

public class QueryResult {
    private String title;
    private String name;
    private String text;
    private String link;

    public QueryResult(String title, String name, String text, String link) {
        this.title = title;
        this.name = name;
        this.text = text;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public String getSmallText(){
        return this.text.split(" Mon Tue Wed ")[0];
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
