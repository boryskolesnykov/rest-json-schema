package com.globallogic.restjsonapp.services;

import com.globallogic.restjsonapp.schema_constructor.*;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.*;

public class SchemasService {

    private final static Map<String, Class> SCHEMA_ID_TO_CLASS_MAP =
            Collections.unmodifiableMap(new HashMap<String, Class>(){{
                Set<Class<?>> classes = getClassesForPackage("com.globallogic.restjsonapp.dto");
                for(Class clazz : classes){
                    put(clazz.getSimpleName().toLowerCase(), clazz);
                }
            }});

    public List<JsonSchemaMock> getAllSchemasMocks(String packageName){
        List<JsonSchemaMock> result = new ArrayList<>();

        for (Class clazz : SCHEMA_ID_TO_CLASS_MAP.values()){
            JsonSchemaMock mock = new JsonSchemaMock();
            mock.setId(clazz.getSimpleName().toLowerCase());
            mock.setHref("/api/schemas/" + mock.getId());
            result.add(mock);
        }

        return result;
    }

    public JsonSchema getSchemaById(String id){
        SchemaConstructor constructor = new JsonSchemaConstructorImpl();
        Class clazz = SCHEMA_ID_TO_CLASS_MAP.get(id);

        if (clazz == null)
            return null;

        return constructor.constructSchema(clazz);
    }

    private static Set<Class<?>> getClassesForPackage(String packageName){
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(packageName))
        );
        return reflections.getTypesAnnotatedWith(Scannable.class);
    }
}

