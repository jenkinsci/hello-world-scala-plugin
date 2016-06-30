package org.jenkinsci.plugins.hw_scala

import hudson.model.{BuildListener, FreeStyleBuild}
import hudson.util.FormValidation
import hudson.{Extension, Launcher}
import net.sf.json.JSONObject
import org.jenkinsci.plugins.scala.stub.{BuildDescriptorAdapter, BuilderAdapter}
import org.kohsuke.stapler.{DataBoundConstructor, QueryParameter, StaplerRequest}

import scala.beans.BeanProperty

object HelloWorldBuilder {
  @Extension
  class DescriptorImpl extends BuildDescriptorAdapter {

    @BeanProperty
    var useFrench: Boolean = _

    def doCheckName(@QueryParameter value: String): FormValidation = {
      if (value.length == 0) return FormValidation.error("Please set a name")
      if (value.length < 4) return FormValidation.warning("Isn't the name too short?")
      FormValidation.ok()
    }

    def getDisplayName(): String = "Say hello world"

    override def configure(req: StaplerRequest, formData: JSONObject): Boolean = {
      useFrench = formData.getBoolean("useFrench")
      super.configure(req, formData)
    }
  }
}

class HelloWorldBuilder@DataBoundConstructor
(@BeanProperty val name: String) extends BuilderAdapter {
  override def scala_perform(build: FreeStyleBuild, launcher: Launcher, listener: BuildListener): Boolean = {
    if (getDescriptor.getUseFrench) listener.getLogger.println("Bonjour, " + name + "!") else listener.getLogger.println("Hello, " + name + "!")
    true
  }
  //override def getDescriptor(): DescriptorImpl = {
  //  super.getDescriptor.asInstanceOf[DescriptorImpl]
  //}
  override def getDescriptor(): HelloWorldBuilder.DescriptorImpl = {
    super.getDescriptor.asInstanceOf[HelloWorldBuilder.DescriptorImpl]
  }
}


