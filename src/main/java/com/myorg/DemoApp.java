package com.myorg;

import com.myorg.stacks.*;
import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class DemoApp {
    public DemoApp(App app, StackProps stackProps){
        CertificateStack certificate = new CertificateStack(app, "Certificate", stackProps);
        DeploymentStack deployment = new DeploymentStack(app, "Deployment", stackProps);

        VPCStack vpc = new VPCStack(app, "VPC", stackProps);

        DataStack data = new DataStack(app, "Data", stackProps);

        BackendStack backend = new BackendStack(app, "Backend", vpc.cluster, deployment.repository, data.table, stackProps);

        LoadBalancerStack loadBalancer = new LoadBalancerStack(app, "LoadBalancer", vpc.vpc, backend.ecsService, certificate.certificate, stackProps);

        DomainStack domain = new DomainStack(app, "Domain", loadBalancer.loadBalancer, stackProps);
    }
}

