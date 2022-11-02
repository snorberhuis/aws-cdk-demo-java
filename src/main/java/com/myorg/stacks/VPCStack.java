package com.myorg.stacks;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcProps;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ClusterProps;
import software.constructs.Construct;

public class VPCStack extends Stack {

    public final Vpc vpc;
    public final Cluster cluster;

    public VPCStack(final Construct scope, final String id, StackProps stackProps) {
        super(scope, id, stackProps);

        VpcProps vpcProps = VpcProps.builder()
                .cidr("10.0.0.0/16")
                .enableDnsHostnames(true)
                .enableDnsSupport(true)
                .maxAzs(3)
                .build();

        this.vpc = new Vpc(this, "VPC", vpcProps);

        ClusterProps clusterProps = ClusterProps.builder()
                .vpc(this.vpc)
                .containerInsights(true)
                .build();
        this.cluster = new Cluster(this, "Cluster", clusterProps);
    }

}
