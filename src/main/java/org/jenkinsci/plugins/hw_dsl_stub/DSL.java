package org.jenkinsci.plugins.hw_dsl_stub;

import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Method;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Parameter;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Step;
import hudson.Extension;
/**
 * Created by jeremymarshall on 1/01/2015.
 */
@Extension
public class DSL extends org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Step{
    @Override
    public String getName(){
        return "helloWorld";
    }

    @Override
    public String getDescription(){
        return "Add a Hello World build step";
    }

    @Override
    public final boolean hasMethods(){
        return true;
    };

    @Method(description="Add a helloWorld step")
    public Object helloWorld(@Parameter(description="The name to use in the hello world step") String name) {
        return new HelloWorldBuilder(name);
    }

}