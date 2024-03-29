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
        private static boolean isFirst = true;
        // ENVIAR
        private PrintWriter out = null;
        // RECEBER
        private ServerSocket serverSocketMSG;
        private ServerSocket serverSocketIMG;
        private BufferedReader input = null;
        
        private final int qtdImg;
        private final JTextField txtConexao;
        private JFrameImage jfi;

        public Server(int qtdImg,JTextField txtConexao)
        {
            jfi = new JFrameImage();
            this.qtdImg = qtdImg;
            this.txtConexao = txtConexao;
            receberMensagem();
            txtConexao.setText("SERVIDOR INICIADO...");
        }

    
        public void receberMensagem() {                                           
            new Thread(() -> {
                try {
                    if(serverSocketMSG == null || (!serverSocketMSG.isBound()))
                        serverSocketMSG = new ServerSocket(SERVERPORT_MSG);
                    if( connectedSocketMSG == null || connectedSocketMSG.isClosed() )
                        connectedSocketMSG = serverSocketMSG.accept();
                    SERVER_IP = connectedSocketMSG.getInetAddress().toString().substring(1);
                    if(isFirst)
                        {
                        enviarSlides();
                        isFirst=false;
                        }
                    input = new BufferedReader(new InputStreamReader(connectedSocketMSG.getInputStream()));
                    do  {
                    final String message = input.readLine();
                    System.out.println("Mensagem recebida: "+message);
                    switch(message)
                        {
                        case "CURSOR":
                            String X = input.readLine();
                            String Y = input.readLine();
                            int x = Utils.getProporcaoX(Integer.parseInt(X));
                            int y = Utils.getProporcaoY(Integer.parseInt(Y));
                            Utils.mover(x, y);
                            break;
                        case "AVANCAR":
                            Utils.avancar();
                            jfi.fecharImage();
                            break;
                        case "RECUAR":
                            Utils.retroceder();
                            jfi.fecharImage();
                            break;
                        case "STOP":
                            connectedSocketMSG.close();
                            connectedSocketIMG.close();
                            jfi.fecharImage();
                            break;
                        default:break;
                        } 
                    }while(connectedSocketMSG!=null || (!connectedSocketMSG.isClosed()) );
                    } 
                    catch (IOException | AWTException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }).start();
            
        }      
        
        public void enviarSlides()
        {
            if(SERVER_IP.length()>0)
                    {
                        enviarSlides("C:\\Temp\\SLIDES", qtdImg);
                        txtConexao.setText("ENVIANDO IMAGENS PARA O CELULAR...");
                        receberImagens();
                        txtConexao.setText("PRONTO PARA RECEBER IMAGENS");        
                    }
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
                if( serverSocketIMG == null || (!serverSocketIMG.isBound()) )
                    serverSocketIMG = new ServerSocket(SERVERPORT_IMG);
                do {
                    if(connectedSocketIMG == null || connectedSocketIMG.isClosed() || !connectedSocketIMG.isBound() )
                        connectedSocketIMG = serverSocketIMG.accept();
                    int filesize = 6022386;
                    int bytesRead;
                    int current = 0;
                    byte[] mybytearray  = new byte [filesize];
                    InputStream is = connectedSocketIMG.getInputStream();
                    bytesRead = is.read(mybytearray,0,mybytearray.length);
                    if(bytesRead > 0){
                        current = bytesRead;
                        do {
                            bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
                            if(bytesRead >= 0) current += bytesRead;
                        } while(bytesRead > -1);

                        ByteArrayInputStream bis = new ByteArrayInputStream(mybytearray);
                        BufferedImage bImage2 = ImageIO.read(bis);
                        if(!jfi.telaAberta())
                            jfi.abrirImage();
                        jfi.setarImageDoLabel(bImage2);    
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
                                    os.close();
                                    System.out.println("ENVIOU " + x1);
                                }
                        }
                    }catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            sendImg.start();
            sendImg.interrupt();
            } 
        
        public int getSERVERPORT_MSG() {
        return SERVERPORT_MSG;
        }

        public int getSERVERPORT_IMG() {
            return SERVERPORT_IMG;
        }

        public String getSERVER_IP() {
            return SERVER_IP;
        }
        
        }      
        


