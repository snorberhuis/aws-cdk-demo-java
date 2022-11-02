package com.myorg.stacks;


import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.dynamodb.ITable;
import software.amazon.awscdk.services.ecr.IRepository;

import software.amazon.awscdk.services.ecs.*;
import software.constructs.Construct;

import java.util.List;
import java.util.Map;


public class BackendStack extends Stack {

    public final FargateService ecsService;

    public BackendStack(final Construct scope, final String id, final ICluster cluster, final IRepository repository, final ITable table, StackProps stackProps) {
        super(scope, id, stackProps);


        TaskDefinition taskDefinition = new TaskDefinition(this, "TaskDefinition", TaskDefinitionProps.builder()
                .compatibility(Compatibility.FARGATE)
                .memoryMiB("4096")
                .cpu("2048")
                .build());

        taskDefinition.addContainer("backend", ContainerDefinitionOptions.builder()
                .image(RepositoryImage.fromEcrRepository(repository))
                .environment(Map.of("DB_NAME", table.getTableName()))
                .portMappings(List.of(PortMapping.builder()
                        .containerPort(80)
                        .hostPort(80)
                        .build()))
                .build());

        this.ecsService = new FargateService(this, "EcsService", FargateServiceProps.builder()
                .cluster(cluster)
                .desiredCount(2)
                .taskDefinition(taskDefinition)
                .build());

        table.grantReadWriteData(taskDefinition.getTaskRole());
    }


}

