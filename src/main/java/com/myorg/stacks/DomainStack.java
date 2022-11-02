package com.myorg.stacks;


import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.elasticloadbalancingv2.*;
import software.amazon.awscdk.services.route53.*;
import software.amazon.awscdk.services.route53.targets.LoadBalancerTarget;
import software.constructs.Construct;


public class DomainStack extends Stack {

    public DomainStack(final Construct scope, final String id, IApplicationLoadBalancer loadBalancer, StackProps stackProps) {
        super(scope, id, stackProps);

        HostedZoneProviderProps hostedZoneProviderProps = HostedZoneProviderProps.builder()
                .domainName("live-conference-demo.com")
                .build();
        IHostedZone hostedZone = HostedZone.fromLookup(this, "HostedZone", hostedZoneProviderProps);

        RecordTarget loadBalancerTarget = RecordTarget.fromAlias(new LoadBalancerTarget(loadBalancer));

        ARecordProps aRecordProps = ARecordProps.builder()
                .zone(hostedZone)
                .target(loadBalancerTarget)
                .recordName("jfall")
                .build();
        new ARecord(this, "ALBRecord", aRecordProps);
    }


}

