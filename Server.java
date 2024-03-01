import java.net.*;
import java.io.*;
class Server{
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Server(){
        try {
            server=new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("waiting");
            socket=server.accept();

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

            
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("COnnection closed");
        }
        System.out.println("COnnection closed");
    }
    public void startReading(){
        Runnable r1 = () -> {
            System.out.println("Reader started");
            try{
                while(true){
                        String msg=br.readLine();
                        if (msg.equals("exit")){
                            System.out.println("Client terminated the chat");
                            socket.close();
                            break;
                        }
                        System.out.println("Client : "+msg);           
                }
            } catch ( Exception e ){
                //e.printStackTrace();
                System.out.println("COnnection closed");
        }
            System.out.println("COnnection closed");

        };
        new Thread(r1).start();
        }
    public void startWriting(){
        System.out.println("Writer started");
        Runnable r2 = () -> {
            try {
                while(true && !socket.isClosed()){

                        BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                        String content=br1.readLine();
                        out.println(content);
                        out.flush();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        };
        new Thread(r2).start();

    }

    public static void main(String[] args ){
        System.out.println("This is server..going to start server");
        new Server();
    }
}