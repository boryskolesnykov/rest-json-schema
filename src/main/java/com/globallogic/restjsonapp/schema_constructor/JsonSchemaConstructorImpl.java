package com.globallogic.restjsonapp.schema_constructor;

import com.globallogic.restjsonapp.dto.BankAccount;
import com.globallogic.restjsonapp.dto.Customer;
import com.globallogic.restjsonapp.dto.Product;

import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class JsonSchemaConstructorImpl implements SchemaConstructor {



    @Override
    public JsonSchema constructSchema(Class clazz) {

        JsonSchema schema = new JsonSchema();

        String className = clazz.getSimpleName().toLowerCase();

        Field[] fields = clazz.getDeclaredFields();
        String schemaJson = wrapSchema(className, buildJson(fields));

        schema.setId(className);
        schema.setJsonSchema(schemaJson);

        return schema;
    }


    private String buildJson(Field[] fields){

        TypeResolver typeResolver = new TypeResolverImpl();
        StringBuilder sb = new StringBuilder();

        if (fields.length > 0){
            sb.append("\"properties\": { ");
            for (Field field : fields){

                String type = typeResolver.resolve(field.getType());

                sb.append("\"").append(field.getName()).append("\": { ");
                sb.append("\"type\": ").append("\"").append(type).append("\", ");
                sb.append(getRestrictions(field));

                //recursive call to process all aggregated user's types
                if (type.equals(TypeResolver.OBJECT_TYPE))
                    sb.append(buildJson(field.getType().getDeclaredFields()));

                sb.append("}, ");

            }
            sb.append("} ");
        }

        return sb.toString();
    }

    private String getRestrictions(Field field){
        Annotation[] annotations = field.getDeclaredAnnotations();
        StringBuilder sb = new StringBuilder();

        for (Annotation annotation : annotations){
            Class<? extends Annotation> type = annotation.annotationType();
            if (type == NotNull.class)
                sb.append("\"required\": ").append(true).append(",");
            if (type == Min.class){
                Object value = getAnnotationFieldValue(annotation, "value");
                sb.append("\"minimum\": ").append(value).append(",");
            }
            if (type == Max.class){
                Object value = getAnnotationFieldValue(annotation, "value");
                sb.append("\"maximum\": ").append(value).append(",");
            }
            if (type == Size.class){
                Object value = getAnnotationFieldValue(annotation, "min");
                sb.append("\"minLength\": ").append(value).append(",");
                value = getAnnotationFieldValue(annotation, "max");
                sb.append("\"maxLength\": ").append(value).append(",");
            }
            if (type == Pattern.class){
                Object value = getAnnotationFieldValue(annotation, "regexp");
                sb.append("\"pattern\": ").append(value).append(",");
            }
        }

        return sb.toString();
    }

    private Object getAnnotationFieldValue(Annotation annotation, String fieldName){
        Class<? extends Annotation> type = annotation.annotationType();
        Object value = null;
        try {
            value = type.getMethod(fieldName).invoke(annotation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private String appendHeader(String id, String json){
        StringBuilder sb = new StringBuilder();

        sb.append("{ \"$schema\": \"http://json-schema.org/draft-03/schema\", ");
        sb.append("\"id\": \"").append(id).append("\", ");
        sb.append("\"required\": false, ");
        sb.append("\"type\": \"object\", ");
        sb.append(json);

        return sb.toString();
    }

    private String appendFooter(String json){
        StringBuilder sb = new StringBuilder(json);
        sb.append("}");
        return sb.toString();
    }

    private String wrapSchema(String id, String json){
        json = appendHeader(id, json);
        json = appendFooter(json);
        return json;
    }

}

