package bamboozle;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andreas on 2016-02-23.
 */
public class HackAsmGenerator {
    private ArrayList<String> codeList=new ArrayList<>();
    private  Writer writer;

    public HackAsmGenerator(String filename){
        try {
           writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename+".bzh")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeInstruction(String txt){

    }
    public void writeToDo(String txt){

    }
    public void writeVar(String txt){

    }
    public void writeDecl(String txt){
        write("var "+txt);
    }
    public void writeAssign(String txt,int nbr){
        write("@"+txt);
        write("M"+"="+nbr);
    }

    public void writePrint(String txt){

    }

    public void writeExpr(String txt){

    }

    public void writeInfo(String txt){

    }

    public void writeINT(String txt){

    }

    public void writeID(String txt){

    }
    public void write(String txt){
        try {
            writer.write(txt+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
