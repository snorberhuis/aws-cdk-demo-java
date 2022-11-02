package com.myorg.stacks;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ecr.Repository;
import software.constructs.Construct;

public class DeploymentStack extends Stack {
    public final Repository repository;

    public DeploymentStack(final Construct scope, final String id, StackProps stackProps) {
        super(scope, id, stackProps);

        this.repository = Repository.Builder.create(this, "Repository")
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();
    }

}
