Gedcom2Xml
    usage: java Gedcom2Xml <input> <output>
    
description:
    Convert GedCom files to equivelant XML file. The program
    requires two arguments to run, these are input filename 
    and output filename.
    
    build and run instructions below.

build:    
    (from src folder in project root)
    javac Gedcom2Xml.java

run:     
    (from src folder in project root)
    java Gedcom2Xml Royalty.txt Parsed.xml
    
    - The input file must be located in Data folder
    - The output file is generated in Result folder