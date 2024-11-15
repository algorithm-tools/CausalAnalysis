package org.algorithmtools.ca4j.utils;

import java.util.List;

public class CollectionUtil {

    public static boolean isEmpty(List list){
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(List list){
        return !isEmpty(list);
    }

}
