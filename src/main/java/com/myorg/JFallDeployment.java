package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class JFallDeployment {
    public static void main(final String[] args) {
        App app = new App();

        StackProps stackProps = StackProps.builder()
                .env(Environment.builder()
                        .account("416703003071")
                        .region("eu-west-1")
                        .build())
                .build();

        new DemoApp(app, stackProps);

        app.synth();
    }
}

