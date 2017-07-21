package model;

public class GroupData {


    private final String groupName;
    private final String groupHeader;
    private final String groupFooter;

    public GroupData(String groupName,String groupHeader, String groupFooter) {
        this.groupName = groupName;
        this.groupHeader= groupHeader;
        this.groupFooter = groupFooter;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupFooter() {
        return groupFooter;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;
    }

    @Override
    public int hashCode() {
        return groupName != null ? groupName.hashCode() : 0;
    }
}
