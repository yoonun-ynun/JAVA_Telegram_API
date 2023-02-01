package JTA;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;

public class Server {
    private HttpsServer server;
    private final boolean[] check = new boolean[2];

    public Server(){
        try{
            server = HttpsServer.create(new InetSocketAddress(443), 0);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setJKS(File file ,String passwd){
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");

            char[] password = passwd.toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(file);
            ks.load(fis, password);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                @Override
                public void configure(HttpsParameters params) {
                    try {
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                        params.setSSLParameters(defaultSSLParameters);
                    } catch (Exception ex) {
                        System.err.println("Https 포트를 여는데 실패하였습니다.");
                        ex.printStackTrace();
                    }
                }
            });
            check[0] = true;
        }catch (Exception e){
            System.err.println("Https 서버를 여는데 실패하였습니다.");
            e.printStackTrace();
        }
    }

    public void InputContext(String path, HttpHandler handler){
        server.createContext(path, handler);
    }
    public void InputCommand(Telegram command){
        server.createContext("/JTA.Telegram", command);
        check[1] = true;
    }
    public void start(){
        if(!(check[0] && check[1])){
            throw new NotRunFunctionException();
        }
        server.start();
    }
    public void stop(){
        server.stop(1);
    }
}
class NotRunFunctionException extends RuntimeException{
    NotRunFunctionException(){
        super("Please run the setJKS and InputCommand functions first.");
    }
}
