package org.jenkinsci.plugins.hw_scala.original;


import hudson.Launcher;
import hudson.Extension;
import hudson.model.FreeStyleBuild;
import hudson.util.FormValidation;
import hudson.model.BuildListener;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;

import org.jenkinsci.plugins.scala.stub.BuilderAdapter;
import org.jenkinsci.plugins.scala.stub.BuildDescriptorAdapter;

import org.jenkinsci.plugins.hw_scala.HelloWorldBuilder;

public class HelloWorldJava extends BuilderAdapter {

    private final String name;

    private final HelloWorldBuilder hwb = new HelloWorldBuilder("a");

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public HelloWorldJava(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean scala_perform(FreeStyleBuild build, Launcher launcher, BuildListener listener) {
        if (getDescriptor().getUseFrench())
            listener.getLogger().println("Bonjour, " + name + "!");
        else
            listener.getLogger().println("Hello, " + name + "!");
        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildDescriptorAdapter {

        private boolean useFrench;

        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error("Please set a name");
            if (value.length() < 4)
                return FormValidation.warning("Isn't the name too short?");
            return FormValidation.ok();
        }

        public String getDisplayName() {
            return "Say hello world";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            useFrench = formData.getBoolean("useFrench");

            return super.configure(req, formData);
        }

        public boolean getUseFrench() {
            return useFrench;
        }
    }
}
