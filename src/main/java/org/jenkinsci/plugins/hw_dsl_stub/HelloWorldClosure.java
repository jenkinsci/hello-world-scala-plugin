package org.jenkinsci.plugins.hw_dsl_stub;

import hudson.Extension;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Method;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Parameter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jeremymarshall on 31/01/2015.
 */
@Extension
public class HelloWorldClosure extends org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Closure{

    String who;

    @Override
    public String getName(){
        return "helloWorld closure";
    }

    @Override
    public String getDescription(){
        return "Hello World step closure";
    }

    @Override
    public final boolean hasMethods(){
        return true;
    };

    @Method(description="Set Hello World Name")
    public void who(@Parameter(description="The name to use in the hello world step") String name) {

        this.who = name;
    }

    public String getWho(){
        return who;
    }
}
