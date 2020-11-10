import com.ubirch.test.{ApiBase, ApiSwagger, ConfigBase, ResourcesApp}
import javax.servlet.ServletContext
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle with ConfigBase {

  implicit val swagger: ApiSwagger = new ApiSwagger

  override def init(context: ServletContext): Unit = {

    context.setInitParameter("org.scalatra.cors.preflightMaxAge", "5")

    context.setInitParameter("org.scalatra.cors.allowCredentials", "false")

    context.setInitParameter("org.scalatra.environment", scalatraEnv)

    context.mount(new ApiBase, "/users", "UserApi")
    context.mount(new ResourcesApp, "/api-docs")
  }
}

