package org.jenkinsci.plugins.hw_dsl_stub

import hudson.Launcher
import hudson.Extension
import hudson.util.FormValidation
import hudson.model.AbstractBuild
import hudson.model.BuildListener
import net.sf.json.JSONObject
import org.kohsuke.stapler.DataBoundConstructor
import org.kohsuke.stapler.StaplerRequest
import org.kohsuke.stapler.QueryParameter
import javax.servlet.ServletException
import java.io.IOException
import org.jenkinsci.plugins.scala.stub.BuilderAdapter
import org.jenkinsci.plugins.scala.stub.BuildDescriptorAdapter
import HelloWorldBuilder._
import scala.reflect.{BeanProperty, BooleanBeanProperty}
//remove if not needed
import scala.collection.JavaConversions._

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

  override def scala_perform(build: AbstractBuild, launcher: Launcher, listener: BuildListener): Boolean = {
    if (getDescriptor.getUseFrench) listener.getLogger.println("Bonjour, " + name + "!") else listener.getLogger.println("Hello, " + name + "!")
    true
  }

  override def getDescriptor(): DescriptorImpl = {
    super.getDescriptor.asInstanceOf[DescriptorImpl]
  }
}