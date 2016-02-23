package ByteCode;

import org.cojen.classfile.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Andreas on 2016-02-23.
 */
public class AddSum {

    static String generatedClassName = "AddSumGenerated2";

    public static void main(String[] args) throws IOException {
        ClassFile cf = createClassFile();
        cf.writeTo(new FileOutputStream(generatedClassName + ".class"));
    }

    private static ClassFile createClassFile() {
        ClassFile cf = new ClassFile(generatedClassName);
        cf.addDefaultConstructor();
        TypeDesc[] params = new TypeDesc[]{TypeDesc.STRING.toArrayType()};
        MethodInfo mi = cf.addMethod(Modifiers.PUBLIC_STATIC, "main", null, params);
        CodeBuilder b = new CodeBuilder(mi);
        TypeDesc printStream = TypeDesc.forClass("java.io.PrintStream");
        TypeDesc integer = TypeDesc.forClass("java.lang.Integer");

        LocalVariable x = b.createLocalVariable( TypeDesc.INT);
        LocalVariable z = b.createLocalVariable( TypeDesc.INT);

        b.loadConstant(10);
        b.storeLocal(x);
        b.loadConstant(1132);
        b.storeLocal(z);

        b.loadConstant(10);
        b.loadLocal(z);
        b.math(Opcode.IADD);
        b.storeLocal(x);
/*
        b.loadConstant(2000);
        b.loadLocal(z);
        b.math(Opcode.IADD);
        b.storeLocal(z);

        LocalVariable y = b.createLocalVariable("y", TypeDesc.INT);

        b.loadConstant(10);
        b.loadConstant(10);
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

        b.loadConstant(9);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);

        b.loadConstant(11);
        b.loadLocal(y);
        b.math(Opcode.IADD);
        b.storeLocal(y);

        b.loadStaticField("java.lang.System", "out", printStream);
        b.loadLocal(x);
        params = new TypeDesc[] { TypeDesc.INT };
        b.invokeStatic(integer, "toString", TypeDesc.STRING, params);
        params = new TypeDesc[] { TypeDesc.STRING };
        b.invokeVirtual(printStream, "println", null, params);

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
        b.invokeVirtual(printStream, "println", null, params);*/

        return cf;
    }
}