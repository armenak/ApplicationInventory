# ApplicationInventory - Agent

# How to build Agent scanner
The following will generate an executable jar file called agent.jar

mvn clean package

# How To Run
To see the options for running the scanner type:
java -jar agent.jar --help

To run specifying s property file, the scan option and persist option (write 
results to xml output file):
java -jar agent.jar --scan --persist --config c:\users\meaton\downloads\scanner.properties
