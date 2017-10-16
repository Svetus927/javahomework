package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("group")  // м. использоватьля для определения названий тегов при создании тестовых данных в формате xml
@Entity // для маппинга объектов  при соединении с БД через Hibernate ( тест HBConnectionTests )
@Table(name="group_list") // для Hibernate  привязка к нужной таблице, если бы имя класса и таблицы были одикановы, то не нужно
public class GroupData {

    @Id // для указания что это уник идр в базе
    @Column(name="group_id") // @Column для привязки к столбцу в БД ( hibernate)
    @XStreamOmitField // для того чтобы это поле не сохранялось в формате xml, т.к. это всегда макс инт
    private  int id = Integer.MAX_VALUE;

    @Column(name="group_name") // @Column для привязки к столбцу в БД ( hibernate)
    private  String name;

    @Column(name="group_header") // @Column для привязки к столбцу в БД ( hibernate)
    @Type(type="text")
    private  String header;

    @Column(name="group_footer") // @Column для привязки к столбцу в БД ( hibernate)
    @Type(type="text")
    private  String footer;

    public int id() {return id;}

    public String name() {
        return name;
    }

    public String footer() {
        return footer;
    }

    public String header() {
        return header;
    }

    @ManyToMany (mappedBy = "groups") // это означает что в парном классе UserData надо найти поле groups и взять связки оттуда
    private Set<GroupData> groups = new HashSet<>(); // set т к контакт может  входить в неск разных групп


    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
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


    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
