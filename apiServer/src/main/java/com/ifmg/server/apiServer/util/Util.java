package com.ifmg.server.apiServer.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Util {

    Set<String> ignore;

    public Util() {
        this.ignore = new HashSet<>();
        ignore.addAll(Arrays.asList(new String[]{"locations", "password", "email", "score", "role"}));
    }

    public String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (var pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || ignore.contains(pd.getName())) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }

}
