package model;

public class GroupData {

    public int getGroupId() {
        return groupId;
    }

    private  int groupId;
    private final String groupName;
    private final String groupHeader;
    private final String groupFooter;

    public GroupData(String groupName,String groupHeader, String groupFooter) {
        this.groupId= 0;
        this.groupName = groupName;
        this.groupHeader= groupHeader;
        this.groupFooter = groupFooter;
    }

    public GroupData(int groupId, String groupName,String groupHeader, String groupFooter) {
        this.groupId= groupId;
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

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (groupId != groupData.groupId) return false;
        return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
