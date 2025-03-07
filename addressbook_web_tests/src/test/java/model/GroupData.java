package model;

public record GroupData(String name, String header, String footer) {

    public GroupData() {
        this("", "", "");
    }

    public GroupData withName(String name) {
        return new GroupData(name, this.header, this.footer);
    }

    public GroupData withNHeader(String header) {
        return new GroupData(name, header, this.footer);
    }

    public GroupData withFooter(String footer) {
        return new GroupData(name, this.header, footer);
    }
}