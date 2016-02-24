package bamboozle;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andreas on 2016-02-24.
 */
public class ByteCodeGenerator {

    private ArrayList<String> codeList=new ArrayList<>();
    private Writer writer;

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
    public void writeEnd(){
        //Write return
        write("b.returnVoid();");
        write("return cf;");
        //Write end of method:
        write("}");
        //Write end of class:
        write("}");
    }
    public void createVariable(String var){
        write("LocalVariable "+var+" = b.createLocalVariable(\""+var+"\", TypeDesc.INT);");
    }
    public void loadConst(int nbr){
        write("b.loadConstant("+nbr+");");
    }
    public void storeValue(String var){
        write("b.storeLocal("+var+");");
    }
    public void addValues(){
        write("b.math(Opcode.IADD);");
    }
    public void loadLocal(String var){
        write("b.loadLocal("+var+");");
    }
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


    public void write(String txt){
        try {
            writer.write(txt+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main (String [] args){
        new ByteCodeGenerator("File");
    }
}
