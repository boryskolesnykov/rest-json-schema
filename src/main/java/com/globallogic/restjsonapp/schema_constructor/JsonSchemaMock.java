package com.globallogic.restjsonapp.schema_constructor;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class JsonSchemaMock {

    private String id;
    private String href;

}
