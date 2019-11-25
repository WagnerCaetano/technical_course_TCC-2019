    package classes;

    import java.awt.AWTException;
    import java.awt.image.BufferedImage;
    import java.io.BufferedInputStream;
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.ByteArrayInputStream;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStream;
    import java.io.OutputStreamWriter;
    import java.io.PrintWriter;
    import java.net.InetAddress;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.imageio.ImageIO;
    import javax.swing.ImageIcon;
    import javax.swing.JLabel;
    import javax.swing.JTextField;

    public class Server {
        // CONFIG
        private Socket connectedSocketMSG;
        private Socket connectedSocketIMG;
        private static final int SERVERPORT_MSG = 32500;
        private static final int SERVERPORT_IMG = 32499;
        private static String SERVER_IP = "";
        // ENVIAR
        private PrintWriter out = null;
        // RECEBER
        private ServerSocket serverSocket;
        private BufferedReader input = null;
        
        private String PATH_SLIDE="";
        private final int qtdImg;
        private final JTextField txtConexao;
        JLabel jl = new JLabel();        
        JFrameImage jf = new JFrameImage(jl);

        public Server(String PATH_SLIDE,int qtdImg,JTextField txtConexao)
        {
            this.PATH_SLIDE = PATH_SLIDE;
            this.qtdImg = qtdImg;
            this.txtConexao = txtConexao;
            receberMensagem();
            txtConexao.setText("SERVIDOR INICIADO...");
        }
        
        public void receberMensagem() {                                           
            new Thread(() -> {
                Socket socket = null;
                try {
                    serverSocket = new ServerSocket(SERVERPORT_MSG);
                    connectedSocketMSG = serverSocket.accept();
                    SERVER_IP = connectedSocketMSG.getInetAddress().toString().substring(1);
                    if(SERVER_IP.length()>0)
                    {
                        enviarSlides("C:\\Temp\\SLIDES", qtdImg);
                        txtConexao.setText("ENVIANDO IMAGENS PARA O CELULAR...");
                        receberImagens();
                        txtConexao.setText("PRONTO PARA RECEBER IMAGENS");        
                    }
                    input = new BufferedReader(new InputStreamReader(connectedSocketMSG.getInputStream()));
                    final String message = input.readLine();
                    System.out.println("Mensagem recebida: "+message);
                    switch(message)
                        {
                        case "CURSOR":
                            String X = input.readLine();
                            String Y = input.readLine();
                            Utils.mover(new Integer(X), new Integer(Y));
                            break;
                        /*case "ZOOM":
                            String X = input.readLine();
                            String Y = input.readLine();
                            Utils.mover(new Integer(X), new Integer(Y));
                            enviarMensagem("OK");
                            break;*/
                        case "AVANCAR":
                            Utils.avancar();
                            jf.fecharImage();
                            break;
                        case "RECUAR":
                            Utils.retroceder();
                            jf.fecharImage();
                            break;
                        case "STOP":
                            socket.close();
                            break;
                        default:break;
                        }        
                    } catch (IOException | AWTException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }).start();
        }           
                      
        public void enviarMensagem(String txtMessage) {                                          
            try {
                Thread enviar = new Thread(() -> {
                    try {
                        if(connectedSocketMSG==null)
                        {
                            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                            connectedSocketMSG = new Socket(serverAddr, SERVERPORT_MSG);
                        }
                        if(connectedSocketMSG !=null){
                            if (out == null)
                                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectedSocketMSG.getOutputStream())), true);
                            out.println(txtMessage);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                enviar.start();
                enviar.interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
       public void receberImagens(){
        new Thread(() -> {
            try {
                //JLabel jl = null;
                serverSocket = new ServerSocket(SERVERPORT_IMG);
                do {
                    Socket connectedSocket = serverSocket.accept();
                    int filesize = 6022386;
                    int bytesRead;
                    int current = 0;
                    byte[] mybytearray  = new byte [filesize];
                    InputStream is = connectedSocketIMG.getInputStream();
                    bytesRead = is.read(mybytearray,0,mybytearray.length);
                    current = bytesRead;
                    do {
                        bytesRead =is.read(mybytearray, current, (mybytearray.length-current));
                        if(bytesRead >= 0) current += bytesRead;
                    } while(bytesRead > -1);
                    ByteArrayInputStream bis = new ByteArrayInputStream(mybytearray);
                    BufferedImage imageSobrePor = ImageIO.read(bis);
                    
                    if (jf.getJl() == null) jl = jf.abrirImage();
                    else{
                        jf.setarImageDoLabel(imageSobrePor);
                    }
                } while (connectedSocketIMG != null);
            }catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
        }

        public void enviarSlides(String pathImg ,int qtdImg) {                                           
            Thread sendImg = new Thread(() -> {
                    try {
                        for (int x1 = 1; x1 <= qtdImg; x1++) {
                        if (connectedSocketIMG == null || connectedSocketIMG.isClosed()){
                                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                                connectedSocketIMG = new Socket(serverAddr, SERVERPORT_IMG);
                            }
                        if (connectedSocketIMG !=null) {
                                
                                    File input_file = new File(pathImg+"\\slide" + x1 + ".jpg");
                                    byte [] byteArray  = new byte [(int)input_file.length()];
                                    FileInputStream fis = new FileInputStream(input_file);
                                    BufferedInputStream bis = new BufferedInputStream(fis);
                                    bis.read(byteArray,0,byteArray.length);
                                    OutputStream os = connectedSocketIMG.getOutputStream();
                                    os.write(byteArray,0,byteArray.length);
                                    os.flush();
                                    Thread.sleep(2000);
                                    System.out.println("ENVIOU " + x1);
                                }
                                connectedSocketIMG.close();
                            }
                    }catch (IOException | InterruptedException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            sendImg.start();
            sendImg.interrupt();
            } 
        }                                          
