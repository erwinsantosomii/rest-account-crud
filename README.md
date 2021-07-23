# Simple REST Account CRUD

This application write in java and build using maven.
Default are using mariaDB as database but can be change through properties file, see Configuration.

### How to run
* Clean & Build : "mvn clean install"
* Running the project : "java -jar ./target/rest-account-crud-0.0.1-SNAPSHOT.jar"

### Configuration
The configuration are located on "config" folder named : application.properties
* spring.datasource.url --> database url
* spring.datasource.username --> database credential username, add "spring.datasource.password" if need password to access the database.
* spring.datasource.driver-class-name --> database driver class
* spring.jpa.hibernate.ddl-auto --> default set to "update" for automatically create the schema on the database.
* logging.file.name --> specified log file.