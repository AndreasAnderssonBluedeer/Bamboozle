package ByteCode;

import org.cojen.classfile.*;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * Created by Andreas on 2016-02-23.
 */
public class AddSum {

    static String generatedClassName = "AddSumGenerated5";

    public static void main(String[] args) throws Exception {
        RuntimeClassFile cf = createClassFile();
        Class cls =cf.defineClass();
        Method m = cls.getMethod("main", new Class[] {String[].class});
        m.invoke(null, new Object[] {args});

        cf.writeTo(new FileOutputStream(generatedClassName + ".class"));
    }

    private static RuntimeClassFile createClassFile() {
        RuntimeClassFile cf = new RuntimeClassFile(generatedClassName);
        cf.addDefaultConstructor();



        TypeDesc[] params = new TypeDesc[]{TypeDesc.STRING.toArrayType()};
        MethodInfo mi = cf.addMethod(Modifiers.PUBLIC_STATIC, "main", null, params);
        CodeBuilder b = new CodeBuilder(mi);

        TypeDesc printStream = TypeDesc.forClass("java.io.PrintStream");
        TypeDesc integer = TypeDesc.forClass("java.lang.Integer");

        LocalVariable x = b.createLocalVariable("x", TypeDesc.INT);
        LocalVariable z = b.createLocalVariable("z", TypeDesc.INT);

//
        b.loadConstant(10);
        b.storeLocal(x);
        b.loadConstant(1132);
        b.storeLocal(z);
//
        b.loadConstant(10);
        b.loadLocal(z);
        b.math(Opcode.IADD);
        b.storeLocal(x);

        b.loadConstant(2000);
        b.loadLocal(z);
        b.math(Opcode.IADD);
        b.storeLocal(z);


        LocalVariable y = b.createLocalVariable("y", TypeDesc.INT);
        b.loadConstant(10);
        b.loadConstant(10);
        b.math(Opcode.IADD);
        b.storeLocal(y);
//
        b.loadConstant(11);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);

        b.loadConstant(9);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);

        b.loadConstant(11);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);

        b.loadConstant(9);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);

        b.loadConstant(11);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);
//
        b.loadStaticField("java.lang.System", "out", printStream);
        b.loadLocal(x);
        params = new TypeDesc[] { TypeDesc.INT };
        b.invokeStatic(integer, "toString", TypeDesc.STRING, params);
        params = new TypeDesc[] { TypeDesc.STRING };
        b.invokeVirtual(printStream, "println", null, params);
//
        b.loadStaticField("java.lang.System", "out", printStream);
        b.loadLocal(z);
        params = new TypeDesc[] { TypeDesc.INT };
        b.invokeStatic(integer, "toString", TypeDesc.STRING, params);
        params = new TypeDesc[] { TypeDesc.STRING };
        b.invokeVirtual(printStream, "println", null, params);

        b.loadStaticField("java.lang.System", "out", printStream);
        b.loadLocal(y);
        params = new TypeDesc[] { TypeDesc.INT };
        b.invokeStatic(integer, "toString", TypeDesc.STRING, params);
        params = new TypeDesc[] { TypeDesc.STRING };
        b.invokeVirtual(printStream, "println", null, params);


        b.returnVoid();

        return cf;
    }
}