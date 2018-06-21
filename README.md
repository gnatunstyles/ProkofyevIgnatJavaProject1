How to launch application from the command line:
1)Project Structure -> Artifacts -> Jar -> From modules with dependencies
2)Choose "Ls" as a main class
3)OK
4)Build -> Build Artifact
5) All libraries will be integrated into the target Jar.
6)The executable itself will be generated in the directory out -> artifacts
7)Launch application through " java -jar path-to-jar-file args-of-prog" command