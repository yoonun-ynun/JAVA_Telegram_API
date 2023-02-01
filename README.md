# Java Telegram API

<h2>Overview</h2>

텔레그램 봇을 사용하기 위한 API 입니다.

API를 사용하기 위해 json 라이브러리와 jks로 되어있는 SSL/TLS 인증서가 필요합니다.<br>
웹훅을 위한 주소는 https://<hostname>/Telegram으로 설정되며
<br>위 주소로 웹훅을 설정하시면 됩니다.

기본적으로 443포트가 사용되지 않는 상태여야 합니다.

<h2>How to Use</h2>

기본적으로 http POST 통신을 위한 클래스가 내장되어 있습니다.<br>

jsonpost : application/json<br>
Post : application/x-www-form-urlencoded<br>
Multipart : multipart/form-data<br>

Server 클래스의 객체를 생성한 후 setJKS 함수로 jks 파일을 지정한 후 InputCommand 함수로 메시지가 들어왔을 때 실행할 내용을 넣으시면 됩니다. start 함수로 서버를 시작하며 stop 함수로 서버를 멈춥니다.<br>
만약 다른 페이지를 넣고싶다면 InputContext 함수를 이용하여 추가적으로 넣을 수 있습니다.
<br>

InputCommand 함수에 들어가는 객체의 경우 Telegram 클래스를 상속받은 클래스의 객체를 넣어주시면 됩니다.<br>
Telegram 클래스 Command 함수를 오버라이딩 하는것으로 메시지가 들어왔을 때의 실행할 코드를 짤 수 있습니다.<br>
Command 함수에는 모든 내용을 담고있는 JSONObject 클래스의 info 변수가 들어오며 chat_id, user_id, message_id, message, message_type, is_command에 대한 내용이 추가적으로 들어옵니다. message 변수의 경우 비디오, 포토 등의 메시지 타입이 들어와 메시지의 내용이 없으면
 null값이 들어오므로 NullPointerException 에 주의하여 주시기 바랍니다.

Methods 클래스에는 기본적인 TelegramAPI의 함수가 들어있습니다. 앞으로 추가해 나갈 예정입니다.

<h3>example</h3>

```java
public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setJKS(new File("JKS_Path"), "JKS_Password");
        server.InputCommand(new Input());
        server.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("If you want to stop the server, please type 'stop'");
        while (true){
            String command = br.readLine();
            if(command.equals("stop")) break;
        }
        server.stop();
    }
}

class Input extends Telegram{
    String token = "example_token";
    Methods me = new Methods(token);
    @Override
    void Command(JSONObject info, long chat_id, long user_id, long message_id, String message, String message_type, boolean is_command) {
        if(message != null && is_command){
            String command = message.split(" ")[0];
            if(command.equals("/say")){
                String text = message.substring(5);
                me.sendMessage(chat_id, text);
            }
        }
    }
}
```


/say 커맨드로 메시지를 보내면 봇이 따라하는 예제