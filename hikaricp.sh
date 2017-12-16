#!bin/sh

# Prerequisites:
#	. Java
#	. Maven
#	. MySQL
#		.. user "rabbit" with grants on "rabbithole" schema (see schema.sql for commands)
#		.. schema "rabbithole" (see schema.sql for commands)

# Compile and deploy with Maven
mvn package

# Start MySQL, using alias for
#	sudo launchctl load -F /Library/LaunchDaemons/com.oracle.oss.mysql.mysqld.plist
mysql-start



### USING GRADLE

# Test 1: no input, just to initialise the database
java -jar -Dspring.datasource.initialize=true build/libs/rh-hikaricp-0.1.jar

# Test 2: insert new customer
java -jar build/libs/rh-hikaricp-0.1.jar insert newUser newEmail

# Test 3: display all customers
java -jar build/libs/rh-hikaricp-0.1.jar display



### USING MAVEN

# Test 1: no input, just to initialise the database
java -jar -Dspring.datasource.initialize=true target/hikaricp-0.1.jar

# Test 2: insert new customer
java -jar target/hikaricp-0.1.jar insert newUser newEmail

# Test 3: display all customers
java -jar target/hikaricp-0.1.jar display



# Stop MySQL, using alias for
#	sudo launchctl unload -F /Library/LaunchDaemons/com.oracle.oss.mysql.mysqld.plist
mysql-stop
