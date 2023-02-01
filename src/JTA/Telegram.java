package JTA;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public abstract class Telegram implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request_method = exchange.getRequestMethod();

        if(request_method.equals("POST")){
            JSONObject ob = new JSONObject();
            ob.put("code", 1);
            ob.put("message", "ok");
            Headers responseheader = exchange.getResponseHeaders();
            OutputStream response = exchange.getResponseBody();
            responseheader.set("Content-Type", "application/json");
            responseheader.set("Connection", "keep-alive");
            exchange.sendResponseHeaders(200, ob.toString().length());
            response.write(ob.toString().getBytes());
            response.flush();


            //Webhook 입력
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader request = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            while ((line = request.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
            request.close();
            response.close();

            JSONObject jObject = new JSONObject(sb.toString());

            //기본 정보
            long chat_id;
            long user_id;
            long message_id;
            boolean is_command = false;
            String message = null;
            String message_type = null;

            //메시지 수정일 경우
            if(jObject.has("edited_message")){
                message_id = jObject.getJSONObject("edited_message").getLong("message_id");
                message = jObject.getJSONObject("edited_message").getString("text");
                message_type = "edited_message";
                chat_id = jObject.getJSONObject("edited_message").getJSONObject("chat").getLong("id");
                user_id = jObject.getJSONObject("edited_message").getJSONObject("from").getLong("id");
            }else{ //아닐경우
                message_id = jObject.getJSONObject("message").getLong("message_id");
                chat_id = jObject.getJSONObject("message").getJSONObject("chat").getLong("id");
                user_id = jObject.getJSONObject("message").getJSONObject("from").getLong("id");
                if(jObject.getJSONObject("message").has("text")){
                    message = jObject.getJSONObject("message").getString("text");
                }
            }

            //메시지 타입 확인
            JSONObject mess = jObject.getJSONObject("message");
            if(mess.has("animation")) message_type = "animation";
            if(mess.has("audio")) message_type = "audio";
            if(mess.has("document")) message_type = "document";
            if(mess.has("photo")) message_type = "photo";
            if(mess.has("sticker")) message_type = "sticker";
            if(mess.has("video")) message_type = "video";
            if(mess.has("video_note")) message_type = "video_note";
            if(mess.has("voice")) message_type = "voice";
            if(mess.has("contact")) message_type = "contact";
            if(mess.has("dice")) message_type = "dice";
            if(mess.has("game")) message_type = "game";
            if(mess.has("poll")) message_type = "poll";
            if(mess.has("venue")) message_type = "venue";
            if(mess.has("location")) message_type = "location";
            if(message_type == null) message_type = "text";

            if(jObject.getJSONObject("message").has("entities")) {
                JSONArray jArray = jObject.getJSONObject("message").getJSONArray("entities");
                JSONObject obj = jArray.getJSONObject(0);
                String type = obj.getString("type");
                if (type.equals("bot_command")) is_command = true;
            }

            Command(jObject, chat_id, user_id, message_id, message, message_type, is_command);
        }
    }
    abstract void Command(JSONObject info, long chat_id, long user_id, long message_id, String message, String message_type, boolean is_command);
}
