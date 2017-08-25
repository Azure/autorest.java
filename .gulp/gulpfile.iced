require './common.iced'

# ==============================================================================
# tasks required for this build 
Tasks "dotnet"  # dotnet functions
Tasks "regeneration"

# ==============================================================================
# Settings
Import
  initialized: false
  solution: "#{basefolder}/autorest.java.sln"
  sourceFolder:  "#{basefolder}/src/"

# ==============================================================================
# Tasks

task 'init', "" ,(done)->
  Fail "YOU MUST HAVE NODEJS VERSION GREATER THAN 7.10.0" if semver.lt( process.versions.node , "7.10.0" )
  done()
  
# Run language-specific tests:
task 'test', "", [], (done) ->
  await execute "mvn test -pl test/vanilla",     defer code, stderr, stdout
  await execute "mvn test -pl test/azure",       defer code, stderr, stdout
  await execute "mvn test -pl test/azurefluent", defer code, stderr, stdout
  done();
