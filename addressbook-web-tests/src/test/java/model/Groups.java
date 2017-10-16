package model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by uasso on 25/07/2017.
 *
 * создан для красоты тестов - чтобы  добавить методы withAdded и without внутрь сета групдата
 */
public class Groups extends ForwardingSet<GroupData> {
    private Set<GroupData> delegatedSet;

    public Groups() {
        this.delegatedSet = new HashSet<GroupData>();
    }

    public Groups(Groups groups) { // конструктор с параметром

        this.delegatedSet = new HashSet<>(groups.delegate());  // создаем копию объекта
    }

    public Groups(Collection groups) { // конструктор с параметром, для  создания объекта типа Groups из результата запроса к БД

        this.delegatedSet = new HashSet<GroupData>(groups);

    }


    @Override
    protected Set delegate() {
        return delegatedSet;
    }



    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

}
