package com.globallogic.restjsonapp.schema_constructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonSchema {

    private String id;
    private Object jsonSchema;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonSchema() {
        return (String)jsonSchema;
    }

    public void setJsonSchema(String jsonSchema) {
        this.jsonSchema = jsonSchema;
    }

    @Override
    public String toString() {
        return "JsonSchema: \n" +
                "\tid='" + id + '\'' +
                ",\n\tjsonSchema='" + jsonSchema + '\'';
    }
}
