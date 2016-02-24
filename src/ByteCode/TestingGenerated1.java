package ByteCode;
import org.cojen.classfile.*;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class TestingGenerated1 {

static String generatedClassName = "TestingGenerated1";

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

b.returnVoid();
return cf;
}
}
