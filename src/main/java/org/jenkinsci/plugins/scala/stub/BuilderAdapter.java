package org.jenkinsci.plugins.scala.stub;

import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.tasks.Builder;
import org.kohsuke.stapler.StaplerRequest;

public abstract class BuilderAdapter extends Builder {

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        return scala_perform(build, launcher, listener);
    }

    public abstract boolean scala_perform(AbstractBuild build, Launcher launcher, BuildListener listener);

}

