VariableElimination
===================

#1. How to compile the project?
    1. In Linux/Unix command terminal, enter to the directory of the project, then you can see a bunch of *.java source file
    2. Execute the command below to compile all the source files
  
javac *.java

#2. Run the project
   We specify the input file in command line
   For example, to compute on the input file BN_7.uai
 
java GraphicalModel ./BN_7.uai

   where GraphicalModel is the main class. ./BN_7.uai specify the input file. In this case, input is BN_7.uai, which located in the project directory. You can replace it with Unix-style file path. But GraphicalModel is unchangable.
So the general format of command is:

java GraphicalModel {InputFilePath}

#3. The output
   The name of the output is {InputFilePath} + ".output". In the case above, the output file is named BN_7.uai.output, which locates in the same directory of BN_7.uai

# Additional information about BN_6.uai
  I can NOT compute the probability of evidence because it encounter "java.lang.OutOfMemoryError: GC overhead limit exceeded".
  Because the space complexity of BN_6 network is huge, I have allocated almost all of my memory(up to 2G). But it still out of memory.
