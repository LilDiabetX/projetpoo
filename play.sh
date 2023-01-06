compile (){
    echo "Compiling .java files..."

    find . -name "*.java" > sources.txt
    javac -d bin @sources.txt
    rm sources.txt

    echo "Done!"
}

run (){
    echo "Executing..."

    
    java -cp bin/ Launcher
    
}

compile && run