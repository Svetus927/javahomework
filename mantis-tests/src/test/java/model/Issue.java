package model;

/**
 * Created by uasso on 23/11/2017.
 */
public class Issue {

    public int id;
    public String summary;
    public String project;
    public String description;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;

    }

    public String getProject() {
        return project;
    }

    public Issue withProject(String project) {
        this.project = project;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }



}
