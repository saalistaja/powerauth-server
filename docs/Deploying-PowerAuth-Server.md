# Deploying PowerAuth Server

This chapter explains how to deploy PowerAuth Server.

## Supported Databases

Following databases are supported:
- Oracle Database 11g or 12c, or
- MySQL 5.5 or newer, or
- PostgreSQL 9.5.4 or newer

Note that MSSQL database is not supported.

## Downloading PowerAuth Server WAR

You can download the latest `powerauth-java-server.war` at the [PowerAuth Server releases page](https://github.com/wultra/powerauth-server/releases).

## Adding Database Connector on Classpath

In order for the database connectivity to work, you need to add appropriate DB client libraries on your classpath.

For example, when using Oracle with Tomcat, make sure to add `ojdbc-${VERSION}.jar` to the `${CATALINA_HOME}/lib` folder (server restart will be required).

## Creating the Database Schema

In order for the PowerAuth Server to work, you need to have a correct schema in the database. To create the correct database schema, execute these SQL scripts for your database engine:

- [Oracle - Create Database Schema](./sql/oracle/create_schema.sql)
- [MySQL - Create Database Schema](./sql/mysql/create_schema.sql)
- [PostgreSQL - Create Database Schema](./sql/postgresql/create_schema.sql)

You can read more about PowerAuth Server database schema in following guide:

- [Database Structure](./Database-Structure.md)

## Connecting PowerAuth Server to Database

### Default Database Connectivity Parameters

The default database connectivity parameters in `powerauth-java-server.war` are following (MySQL defaults):

```sh
spring.datasource.url=jdbc:mysql://localhost:3306/powerauth
spring.datasource.username=powerauth
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
```

These parameters are of course only for the testing purposes, they are not suitable for production environment. They should be overridden for your production environment using a standard [Spring database connectivity related properties](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-connect-to-production-database).

### Oracle Connectivity Parameters

For Oracle database use following connectivity parameters (example):
```
spring.datasource.url=jdbc:oracle:thin:@//[HOST]:[PORT]/[SERVICENAME]
spring.datasource.username=powerauth
spring.datasource.password=*********
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
```

### PostgreSQL Connectivity Parameters

For PostgreSQL use following connectivity parameters (example):
```
spring.datasource.url=jdbc:postgresql://[HOST]:[PORT]/[DATABASE]
spring.datasource.username=powerauth
spring.datasource.password=*********
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
```

### Specifying Database Connection Character Set

The character set is defined when creating database and each database supports different character sets. 
In case of any national character issues, make sure to configure character encoding for database connection (example):
```
spring.jpa.properties.hibernate.connection.characterEncoding=utf8
spring.jpa.properties.hibernate.connection.useUnicode=true
```

## PowerAuth Server Configuration

_(optional)_ Optionally, you may set up following properties in order to configure your PowerAuth Server instance:

```sh
powerauth.service.applicationName=powerauth
powerauth.service.applicationDisplayName=PowerAuth Server
powerauth.service.applicationEnvironment=
```

These properties are returned when calling the `getSystemStatus` method of the SOAP interface.

## Enabling PowerAuth Server Security

_(optional)_ By default, PowerAuth Server can be accessed by any application that can see the WSDL and SOAP services (or access the RESTful interface). To change this behavior, you can set up a restricted access flag in the server configuration:

```sh
powerauth.service.restrictAccess=true # 'false' is default value
```

If the restricted access is enabled, PowerAuth Server uses credentials stored in `pa_integration` table to verify the access permission. Therefore, you must create a record for each application you that will integrate with PowerAuth Server.

```sql
INSERT INTO `powerauth`.`pa_integration` (`id`, `name`, `client_token`, `client_secret`)
    VALUES ("$(ID)", "$(NAME)", "$(CLIENT_TOKEN)", "$(CLIENT_SECRET)");
```

Values of `ID`, `CLIENT_TOKEN` and `CLIENT_SECRET` must be in UUID Level 4 format (for example `60586743-89d0-4689-b0fb-f4c597294b67`), `NAME` can be any name of the integration (for example, a name of the associated application).

_Note: For SOAP interface, PowerAuth Server uses WS-Security, `UsernameToken` validation (plain text password). The RESTful interface is secured using Basic HTTP Authentication (pre-emptive)._

## Deploying PowerAuth Server WAR File

You can deploy PowerAuth Server WAR into any Java EE container.

The default configuration works best with Apache Tomcat server running on default port 8080. In this case, the deployed server is accessible on `http://localhost:8080/powerauth-java-server/` (WSDL is then available on `http://localhost:8080/powerauth-java-server/soap/service.wsdl`).

To deploy PowerAuth Server to Apache Tomcat, simply copy the WAR file in your `webapps` folder or deploy it using the "Tomcat Web Application Manager" application (usually deployed on default Tomcat address `http://localhost:8080/manager`).

## Deploying PowerAuth Server Outside the Container

You can also execute PowerAuth Server WAR file directly using the following command:

```bash
java -jar powerauth-java-server.war
```

_Note: You can overwrite the port using `-Dserver.port=8090` parameter to avoid port conflicts._

## Generating Your First Application

In order to initialize the database with an application, call PowerAuth Server RESTful service endpoint:

```bash
$ curl -s -H "Content-Type: application/json" -X POST -d '{ "requestObject": { "applicationName": "DEMO APPLICATION NAME" } }' http://localhost:8080/powerauth-java-server/rest/v3/application/create | json_pp
{
   "status" : "OK",
   "responseObject" : {
      "applicationId" : 1,
      "applicationName" : "DEMO APPLICATION NAME"
   }
}
```

This command will create:

- A new application instance named "DEMO APPLICATION NAME" with an `id = 1`.
- A default application version named "default" with associated `application_key` and `application_secret` values
- A new master key pair associated with the application.

To get the application details, you can copy the `applicationId` value from the previous response and call:

```bash
$ curl -s -H "Content-Type: application/json" -X POST -d '{ "requestObject": { "applicationId": 1 } }' http://localhost:8080/powerauth-java-server/rest/v3/application/detail | json_pp
{
   "status" : "OK",
   "responseObject" : {
      "masterPublicKey" : "BKOUTVjJKVB/AnRwq3tbqVkol6omI9DS6E/Yu3swh0l6MewONsjL01LA2/dxpgN5+6Ihy9cW1BpuYtdoFrxxlTA=",
      "applicationId" : 1,
      "versions" : [
         {
            "applicationVersionId" : 1,
            "applicationVersionName" : "default",
            "applicationKey" : "zinbZhRMTXP4UTY+QrjZsg==",
            "applicationSecret" : "tzE7Ps0Ia8G/pFM75rh6yA==",
            "supported" : true
         }
      ],
      "applicationName" : "DEMO APPLICATION NAME"
   }
}
```

## Deploying PowerAuth Server On JBoss / Wildfly

Follow the extra instructions in chapter [Deploying PowerAuth Server on JBoss / Wildfly](./Deploying-Wildfly.md).

## Troubleshooting

### Issues With Database Connectivity

Note that some database engines (for example MySQL) let you specify the default schema as a part of a URL. Other engines, for example **Oracle**, do not allow this. In order to specify the correct default schema, you need to use a following property:

```sh
spring.jpa.properties.hibernate.default_schema=powerauth
```

Some application servers, such as **WildFly** by JBoss, are very restrictive in class loading. As a result, you might get "Cannot load driver class: oracle.jdbc.driver.OracleDriver" errors despite the fact [the proper driver is on the server classpath](https://developer.jboss.org/wiki/DataSourceConfigurationInAS7). In order to workaround this issue in a clean fashion, you need to create a JNDI datasource (named for example `jdbc/powerauth`) and use it instead of JDBC properties - you must set these to empty values. This way, server smartly recognizes that the driver library must be loaded. To use JNDI configuration, set the system properties like so:

```sh
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=
spring.jpa.hibernate.ddl-auto=none
spring.datasource.jndi-name=java:/jdbc/powerauth
```

### Issues With Bouncy Castle Provider

PowerAuth Server uses Bouncy Castle as a Java cryptography provider. 
Please follow our tutorial [how to configure Bouncy Castle](./Installing-Bouncy-Castle.md).

