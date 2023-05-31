import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println("Hello world!");
        JFrame jFrame=new JFrame();
        jFrame.setSize(new Dimension(300,300));
        jFrame.setVisible(true);
        File file=new File("copylog");
        //System.out.flush();
        FileWriter filew= new FileWriter(file);


//        System.out.println(UUID.randomUUID());

        //System.out.println(System.getProperty("os.name"));
        //System.getProperties().list(System.out);
        File cook=new File("");
//        DebugGraphics r=new DebugGraphics();


        String clip=getClipBoard();
        System.out.println(clip);
        while(true) {
            if(!clip.equals(getClipBoard())){
                System.out.println(getClipBoard());
                clip=getClipBoard();
                try {
                    filew.append(clip).append("\n");
                    filew.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String getClipBoard(){
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
//            System.err.println(e.getMessage());
        }
        return "";
    }
}