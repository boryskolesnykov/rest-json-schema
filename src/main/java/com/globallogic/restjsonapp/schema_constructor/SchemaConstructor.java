package com.globallogic.restjsonapp.schema_constructor;


import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface SchemaConstructor {

    public static final Map<Class, String> RESTRICTIONS =
            Collections.unmodifiableMap(new HashMap<Class, String>(){{
                put(NotNull.class, "\"required\": true");
            }});

    JsonSchema constructSchema(Class clazz);


}
