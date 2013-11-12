package com.globallogic.restjsonapp.schema_constructor;

public interface TypeResolver {

    public static final String COLLECTION_TYPE = "array";
    public static final String NUMBER_TYPE = "number";
    public static final String INTEGER_TYPE = "integer";
    public static final String STRING_TYPE = "string";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String OBJECT_TYPE = "object";

    String resolve();
    String resolve(Class clazz);

}
