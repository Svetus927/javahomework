package model;

/**
 * Created on 23/11/2017. по  аналогии с видео 9.3
 */
public class Project {
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }
}
