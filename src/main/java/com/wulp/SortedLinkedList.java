package com.wulp;

import java.util.Comparator;
import java.util.LinkedList;

public class SortedLinkedList {

    private final LinkedList<Object> list = new LinkedList<>();
    private Type typeOfList = null;

    public boolean add(Object newValue) {

        if (newValue == null) {
            return false;
        }

        Type typeOfValue = getTypeOfValue(newValue);

        if (typeOfList == null) {
            if (typeOfValue == Type.INT || typeOfValue == Type.STRING) {
                typeOfList = typeOfValue;
            }
        }

        if (typeOfList == Type.UNDEFINED) {
            return false;
        }
        if (!typeOfValue.equals(typeOfList)) {
            return false;
        }

        boolean added = list.add(newValue);
        Comparator<Object> customComparator = Comparator.comparing(Object::toString);
        list.sort(customComparator);

        return added;
    }


    public Object get(int index) {
        return list.get(index);
    }

    public Type getTypeOfList() {
        return typeOfList;
    }

    private Type getTypeOfValue(Object newValue) {
        if (newValue instanceof String) {
            return Type.STRING;
        }
        if (newValue instanceof Integer) {
            return Type.INT;
        }

        return Type.UNDEFINED;
    }

    public enum Type {
        INT, STRING, UNDEFINED
    }
}
