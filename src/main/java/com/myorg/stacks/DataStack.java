package com.myorg.stacks;

import com.myorg.resources.dynamodb.StandardizedTable;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class DataStack extends Stack {

    public final StandardizedTable table;

    public DataStack(final Construct scope, final String id, StackProps stackProps) {
        super(scope, id, stackProps);

        this.table = new StandardizedTable(this, "DynamoDB");
    }

}
