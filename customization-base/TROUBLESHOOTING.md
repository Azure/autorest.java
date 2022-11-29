# Troubleshooting Azure Autorest Customizations

The `azure-autorest-customization` package provides APIs for customizing Autorest code generation safely and
programmatically to support special cases not supported by Autorest code generation directly using Eclipse language
server to ensure valid Java code.

## Table of Contents

* [EclipseLanguageServer failed to start on CLIENT_PORT](#eclipselanguageserver-failed-to-start-on-client_port)
* [Failed to build with status code WITH_ERROR]()

## EclipseLanguageServer failed to start on CLIENT_PORT

One possible issue is that `azure-autorest-customization` requires Java 11+ to run, when an earlier version of Java is 
being used it'll result in `EclipseLanguageServer` failing to start. To resolve this set either the process JAVA_HOME
or system JAVA_HOME, and restart the process, to a Java 11+ version.

## Failed to build with status code WITH_ERROR

One possible issue is that `azure-autorest-customization` is missing required dependencies in the Maven .m2 cache.
Eclipse language server will generate a log file based on where Autorest is installed (most commonly this is the
current user's base directory in a folder `.autorest`). Determine which version of Autorest is being used by parsing
the CLI logs, the version will be at the very beginning of the command running. Once the version is determined find the
`.log` in the path `@autorest_java@<version>\node_modules\@autorest\java\postprocessor\jdt-language-server\workspace\.metadata`
from the root of the `.autorest` file. An example of this is Autorest is installed at `C:\Users\Work`, so `C:\Users\Work\.autorest`,
and Autorest Java 4.1.9 is being used, the `.log` file will be at
`C:\Users\Work\.autorest\@autorest_java@4.1.9\node_modules\@autorest\java\postprocessor\jdt-language-server\workspace\.metadata\.log`.
The log file will contain information about the configuration used to start the Eclipse language server and what commands
it received or why it failed to run.
