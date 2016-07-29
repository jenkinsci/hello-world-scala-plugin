package org.jenkinsci.plugins.scala.stub;

import hudson.Launcher;
import hudson.matrix.MatrixRun;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.FreeStyleBuild;
import hudson.tasks.Builder;

public abstract class BuilderAdapter extends Builder {

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {
        //pretty tanky due to parameterized type problems in scala...
        try {
            return scala_perform((FreeStyleBuild) build, launcher, listener);
        } catch (ClassCastException e) {
            try {
                return scala_perform((MatrixRun)build, launcher, listener);
            } catch (ClassCastException e2) {
                return false;
            }
        }
    }

    public abstract boolean scala_perform(FreeStyleBuild build, Launcher launcher, BuildListener listener);
    public abstract boolean scala_perform(MatrixRun build, Launcher launcher, BuildListener listener);

}

