package model;

public class GroupData {

    public int getGroupId() {
        return id;
    }

    private  int id = Integer.MAX_VALUE;
    private  String name;
    private  String header;
    private  String footer;


    public String name() {
        return name;
    }

    public String footer() {
        return footer;
    }

    public String header() {
        return header;
    }


    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;

    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }
    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
