package bamboozle2;

import java.io.*;

/**
 * This class converts Bamboozle code to Java Bytecode via Cojen. It writes all instructions to a .java file that is
 * runnable and creates a .class file with auto-generated Java code. This class creates a default template which means
 * that the programmer only needs to call methods about variables and prints + End file.
 *
 *      ******* Saves the .Java file at src/ByteCode ******
 *
 * Created by Andreas & Sara on 2016-02-24.
 */
public class ByteCodeGenerator {

    private Writer writer;

    /**
     * Constructor that creates a default Java Bytecode template.
     * Recieves a Filename that is used as Classname and Filename.
     * @param filename -Filename and ClassName
     */
    public ByteCodeGenerator(String filename){
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("src/ByteCode/"+filename+".java")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Default Construction- write:

        //Package Declaration
        write("package ByteCode;");

        //Imports
        write("import org.cojen.classfile.*;");
        write("import java.io.FileOutputStream;");
        write("import java.lang.reflect.Method;");
        write("");  //Space

        //Class
        write("public class "+filename+" {");
        write("");  //Space

        //Instance Variable
        write("static String generatedClassName = \""+filename+"\";");
        write("");  //Space

        //Main method
        write("public static void main(String[] args) throws Exception {");
        write("");  //Space
        write("RuntimeClassFile cf = createClassFile();");
        write("Class cls =cf.defineClass();");
        write("Method m = cls.getMethod(\"main\", new Class[] {String[].class});");
        write("m.invoke(null, new Object[] {args});");
        write("cf.writeTo(new FileOutputStream(generatedClassName + \".class\"));");
        write("}");
        write("");  //Space

        //CreateClassFile
        write("private static RuntimeClassFile createClassFile() {");
        write("");  //Space
        write("RuntimeClassFile cf = new RuntimeClassFile(generatedClassName);");
        write("cf.addDefaultConstructor();");
        write("TypeDesc[] params = new TypeDesc[]{TypeDesc.STRING.toArrayType()};");
        write("MethodInfo mi = cf.addMethod(Modifiers.PUBLIC_STATIC, \"main\", null, params);");
        write("CodeBuilder b = new CodeBuilder(mi);");
        write("");

        //Print Variables
        write("TypeDesc printStream = TypeDesc.forClass(\"java.io.PrintStream\");");
        write("TypeDesc integer = TypeDesc.forClass(\"java.lang.Integer\");");

    }

    /**
     * Writes the End of File, returns classfile and encapsulates the code with "}"
     */
    public void writeEnd(){
        //Write return
        write("b.returnVoid();");
        write("return cf;");
        //Write end of method:
        write("}");
        //Write end of class:
        write("}");
    }

    /**
     * Create a ByteCode Variable, Parameter with Variable name.
     * @param var Variable name
     */
    public void createVariable(String var){
        write("LocalVariable "+var+" = b.createLocalVariable(\""+var+"\", TypeDesc.INT);");
    }

    /**
     * Loads a constant value as ByteCode. Parameter = Value
     * @param nbr Value
     */
    public void loadConst(int nbr){
        write("b.loadConstant("+nbr+");");
    }

    /**
     * Save a value to a Variable as Bytecode. Parameter = Variable to save value to.
     * @param var Variable
     */
    public void storeValue(String var){
        write("b.storeLocal("+var+");");
    }

    /**
     * Calculate the sum of two values as ByteCode.
     */
    public void addValues(){
        write("b.math(Opcode.IADD);");
    }

    /**
     * Load a Local variable as ByteCode. Parameter = Variable name.
     * @param var Variable
     */
    public void loadLocal(String var){
        write("b.loadLocal("+var+");");
    }

    /**
     * Print a Integer variable. Parameter = Variable name.
     * @param var Variable
     */
    public void print(String var){

        write("");//Space
        write("b.loadStaticField(\"java.lang.System\", \"out\", printStream);");
        loadLocal(var);
        write("params = new TypeDesc[] { TypeDesc.INT };");
        write("b.invokeStatic(integer, \"toString\", TypeDesc.STRING, params);");
        write("params = new TypeDesc[] { TypeDesc.STRING };");
        write("b.invokeVirtual(printStream, \"println\", null, params);");
        write("");//Space
    }

    /**
     * A general method to write all instructions to file. Receives a parameter with String/ instruction in
     * Bytecode.
     * @param txt Instruction
     */
    public void write(String txt){
        try {
            writer.write(txt+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Main-method for testing.
     * @param args
     */
    public static void main (String [] args){
        new ByteCodeGenerator("File");
    }
}
