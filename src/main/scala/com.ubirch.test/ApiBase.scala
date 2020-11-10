package com.ubirch.test

import com.typesafe.scalalogging.LazyLogging
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.{CorsSupport, ScalatraServlet}
import org.scalatra.json.NativeJsonSupport
import org.scalatra.swagger.{Swagger, SwaggerSupport, SwaggerSupportSyntax}
import com.ubirch.testlib
import com.ubirch.testlib.Version

class ApiBase (implicit val swagger: Swagger) extends ScalatraServlet
  with NativeJsonSupport with SwaggerSupport with CorsSupport with LazyLogging {

  // Allows CORS support to display the swagger UI when using the same network
  options("/*") {
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, PUT")
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"))
  }

  // Stops the APIJanusController from being abstract
  protected val applicationDescription = "An example API"

  // Sets up automatic case class to JSON output serialization
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  val getHelloWorld: SwaggerSupportSyntax.OperationBuilder =
    (apiOperation[String]("hello")
      summary "Get an hello"
      description "Hello there"
      tags "Hello")

  get("/hello", operation(getHelloWorld)) {
    logger.info("users: get(/hello)")
    "Oh hey there."
  }


  val getVersion: SwaggerSupportSyntax.OperationBuilder =
    (apiOperation[String]("version")
      summary "Get the version used by the software"
      description "Get the version used by the software."
      tags "Version")

  get("/version", operation(getVersion)) {
    logger.info("users: get(/hello)")
    val vClass = Version()
    "running version: " + vClass.hello
  }

}
