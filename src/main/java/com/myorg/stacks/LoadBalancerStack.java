package com.myorg.stacks;


import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.certificatemanager.ICertificate;
import software.amazon.awscdk.services.ec2.IVpc;
import software.amazon.awscdk.services.elasticloadbalancingv2.*;
import software.constructs.Construct;

import java.util.List;


public class LoadBalancerStack extends Stack {

    public final ApplicationLoadBalancer loadBalancer;

    public LoadBalancerStack(final Construct scope, final String id, IVpc vpc, IApplicationLoadBalancerTarget backend, ICertificate certificate, StackProps stackProps) {
        super(scope, id, stackProps);

        ApplicationLoadBalancerProps albProps = ApplicationLoadBalancerProps.builder()
                .vpc(vpc)
                .internetFacing(true)
                .build();
        this.loadBalancer = new ApplicationLoadBalancer(this, "ALB", albProps);

        BaseApplicationListenerProps httpsListenerProps = BaseApplicationListenerProps.builder()
                .port(443)
                .open(true)
                .certificates(List.of(ListenerCertificate.fromCertificateManager(certificate)))
                .build();
        ApplicationListener listener = loadBalancer.addListener("HTTPSListener", httpsListenerProps);

        AddApplicationTargetsProps backendTargetProps = AddApplicationTargetsProps.builder()
                .port(80)
                .targets(List.of(backend))
                .build();
        listener.addTargets("BackendTarget", backendTargetProps);
    }


}

