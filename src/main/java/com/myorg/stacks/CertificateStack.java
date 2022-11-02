package com.myorg.stacks;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.certificatemanager.Certificate;
import software.amazon.awscdk.services.certificatemanager.CertificateProps;
import software.amazon.awscdk.services.certificatemanager.CertificateValidation;
import software.amazon.awscdk.services.route53.HostedZone;
import software.amazon.awscdk.services.route53.HostedZoneProviderProps;
import software.amazon.awscdk.services.route53.IHostedZone;
import software.constructs.Construct;

public class CertificateStack extends Stack {

    public final Certificate certificate;

    public CertificateStack(final Construct scope, final String id, StackProps stackProps) {
        super(scope, id, stackProps);

        HostedZoneProviderProps hostedZoneProviderProps = HostedZoneProviderProps.builder()
                .domainName("live-conference-demo.com")
                .build();
        IHostedZone hostedZone = HostedZone.fromLookup(this, "HostedZone", hostedZoneProviderProps);

        CertificateProps certificateProps = CertificateProps.builder()
                .domainName("jfall.live-conference-demo.com")
                .validation(CertificateValidation.fromDns(hostedZone))
                .build();
        this.certificate = new Certificate(this, "Certificate", certificateProps);
    }

}
