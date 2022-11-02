package com.myorg.resources.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.*;

public class StandardizedTable extends Table {

    public StandardizedTable(Stack stack, String id){
        super(stack, id, TableProps.builder()
                .partitionKey(Attribute.builder()
                        .name("pk")
                        .type(AttributeType.STRING)
                        .build())
                .sortKey(Attribute.builder()
                        .name("sk")
                        .type(AttributeType.STRING)
                        .build())
                .encryption(TableEncryption.AWS_MANAGED)
                .build());
    }
}
