package com.globallogic.restjsonapp.schema_constructor;

import java.util.*;

public class TypeResolverImpl implements TypeResolver{

    private Class clazz;

    public TypeResolverImpl(){}
    public TypeResolverImpl(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public String resolve() {
        if (clazz == null)
            throw new IllegalStateException("field 'clazz' cannot be null");

        return resolve(clazz);
    }

    @Override
    public String resolve(Class clazz) {

        if (clazz == null)
            throw new IllegalArgumentException("parameter cannot be null");

        if (isString(clazz))
            return STRING_TYPE;

        if (isBoolean(clazz))
            return BOOLEAN_TYPE;

        if (isCollectionOrArray(clazz))
            return COLLECTION_TYPE;

        if (isNumber(clazz)){
            if (isInteger(clazz))
                return INTEGER_TYPE;
            else
                return NUMBER_TYPE;
        }

        return OBJECT_TYPE;
    }

    private boolean isCollectionOrArray(Class clazz){
        return Collection.class.isAssignableFrom(clazz)
                || Map.class.isAssignableFrom(clazz)
                || clazz.isArray();
    }

    private boolean isNumber(Class clazz){
        return Number.class.isAssignableFrom(clazz)
                || double.class.isAssignableFrom(clazz)
                || long.class.isAssignableFrom(clazz)
                || float.class.isAssignableFrom(clazz)
                || isInteger(clazz);
    }

    private boolean isInteger(Class clazz){
        return Integer.class.isAssignableFrom(clazz)
                || Short.class.isAssignableFrom(clazz)
                || Byte.class.isAssignableFrom(clazz)
                || int.class.isAssignableFrom(clazz)
                || short.class.isAssignableFrom(clazz)
                || byte.class.isAssignableFrom(clazz);
    }

    private boolean isString(Class clazz){
        return String.class.isAssignableFrom(clazz)
                || Character.class.isAssignableFrom(clazz)
                || char.class.isAssignableFrom(clazz)
                || clazz.isEnum();
    }

    private boolean isBoolean(Class clazz){
        return Boolean.class.isAssignableFrom(clazz)
                || boolean.class.isAssignableFrom(clazz);
    }

}