set ff=UNIX
set -e
mkdir -p bin
find ./src -name *.java | javac -d bin -cp ../../antlr4/antlr-4.7.2-complete.jar @/dev/stdin