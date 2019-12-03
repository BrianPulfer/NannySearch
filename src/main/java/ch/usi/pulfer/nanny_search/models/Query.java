package ch.usi.pulfer.nanny_search.models;


public class Query {

    public Query(){}

    private String text;
    public String getText(){ return this.text;}
    public void setText(String queryText){this.text = queryText;}

    private String location;
    public String getLocation(){return this.location;}
    public void setLocation(String location){this.location = location;}

    private String minAge;
    public String getMinAge(){return this.minAge;}
    public void setMinAge(String minAge){this.minAge = minAge;}

    private String maxAge;
    public String getMaxAge(){return this.maxAge;}
    public void setMaxAge(String maxAge){this.maxAge = maxAge;}

    private String experience;
    public String getExperience(){return this.experience;}
    public void setExperience(String experience){this.experience = experience;}

    @Override
    public String toString(){
        return "TEXT: "+this.getText()+
                "\nLOCATION: "+this.getLocation() +
                "\nEXPERIENCE: "+this.getExperience() +
                "\nMIN AGE: "+this.getMinAge() +
                "\nMAX AGE: "+this.getMaxAge();
    }

}
