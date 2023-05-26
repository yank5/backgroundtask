import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame jFrame=new JFrame();
        jFrame.setSize(new Dimension(300,300));
        jFrame.setVisible(true);

        BufferedWriter bufferedWriter;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(new File("/home/aru/copylog")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        System.out.println(UUID.randomUUID());

        String clip=getClipBoard();
        System.out.println(clip);
        while(true) {
            if(!clip.equals(getClipBoard())){
                System.out.println(getClipBoard());
                clip=getClipBoard();
                try {
                    bufferedWriter.write(clip);
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