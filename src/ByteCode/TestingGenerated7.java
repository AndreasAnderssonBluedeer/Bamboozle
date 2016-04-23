package ByteCode;
import org.cojen.classfile.*;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class TestingGenerated7 {

static String generatedClassName = "TestingGenerated7";

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
b.loadConstant(10);
b.storeLocal(x);
b.loadConstant(1132);
b.storeLocal(z);
b.loadLocal(z);
b.loadConstant(10);
b.math(Opcode.IADD);
b.storeLocal(x);
b.loadLocal(z);
b.loadConstant(2000);
b.math(Opcode.IADD);
b.storeLocal(z);
LocalVariable y = b.createLocalVariable("y", TypeDesc.INT);
b.loadConstant(10);
b.loadConstant(10);
b.math(Opcode.IADD);
b.loadConstant(11);
b.math(Opcode.IADD);
b.loadConstant(9);
b.math(Opcode.IADD);
b.loadConstant(11);
b.math(Opcode.IADD);
b.loadConstant(9);
b.math(Opcode.IADD);
b.loadConstant(11);
b.math(Opcode.IADD);
b.storeLocal(y);
b.loadLocal(x);

b.loadStaticField("java.lang.System", "out", printStream);
b.loadLocal(x);
params = new TypeDesc[] { TypeDesc.INT };
b.invokeStatic(integer, "toString", TypeDesc.STRING, params);
params = new TypeDesc[] { TypeDesc.STRING };
b.invokeVirtual(printStream, "println", null, params);

b.loadLocal(z);

b.loadStaticField("java.lang.System", "out", printStream);
b.loadLocal(z);
params = new TypeDesc[] { TypeDesc.INT };
b.invokeStatic(integer, "toString", TypeDesc.STRING, params);
params = new TypeDesc[] { TypeDesc.STRING };
b.invokeVirtual(printStream, "println", null, params);

b.loadLocal(y);

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
