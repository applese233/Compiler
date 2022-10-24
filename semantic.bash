set ff=UNIX
set -e
cat | java -cp ../../antlr4/antlr-4.7.2-complete.jar:./bin Main -semantic
