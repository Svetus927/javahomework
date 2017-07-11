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
}
