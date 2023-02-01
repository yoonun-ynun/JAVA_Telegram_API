package JTA;

import org.json.JSONObject;
import java.io.File;

public class Methods {
    String Address;
    public Methods(String token){
        Address = "https://api.telegram.org/bot" + token + "/";
    }

    public JSONObject setWebhook(String url){
        String Address = this.Address + "setWebhook";
        Post post = new Post(Address);
        post.append("url", url + "/JTA.Telegram");
        return new JSONObject(post.start().toString());
    }

    public JSONObject getMe(){
        String Address = this.Address + "getMe";
        Post post = new Post(Address);
        return new JSONObject(post.start().toString());
    }

    public JSONObject logOut(){
        String Address = this.Address + "logOut";
        Post post = new Post(Address);
        return new JSONObject(post.start().toString());
    }

    public JSONObject close(){
        String Address = this.Address + "close";
        Post post = new Post(Address);
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendMessage(long chat_id, String text){
        String Address = this.Address + "sendMessage";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("text", text);
        return new JSONObject(post.start().toString());
    }

    public JSONObject forwardMessage(long chat_id, long from_chat_id, long message_id){
        String Address = this.Address + "forwardMessage";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("from_chat_id", Long.toString(from_chat_id));
        post.append("message_id", Long.toString(message_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject copyMessage(long chat_id, long from_chat_id, long message_id){
        String Address = this.Address + "copyMessage";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("from_chat_id", Long.toString(from_chat_id));
        post.append("message_id", Long.toString(message_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendPhoto(long chat_id, File file){
        String Address = this.Address + "sendPhoto";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.input_file("image/" + ext, "photo", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendAudio(long chat_id, File file){
        String Address = this.Address + "sendAudio";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.input_file("audio/" + ext, "audio", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendDocument(long chat_id, File file){
        String Address = this.Address + "sendDocument";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        multi.input_file("document", "document", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendVideo(long chat_id, File file){
        String Address = this.Address + "sendVideo";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.input_file("video/" + ext, "video", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendAnimation(long chat_id, File file){
        String Address = this.Address + "sendAnimation";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.input_file("video/" + ext, "animation", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendVoice(long chat_id, File file){
        String Address = this.Address + "sendVoice";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.input_file("audio/" + ext, "voice", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendVideoNote(long chat_id, File file){
        String Address = this.Address + "sendVideoNote";
        Multipart multi = new Multipart(Address);
        multi.append("chat_id", Long.toString(chat_id));
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.input_file("audio/" + ext, "video_note", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject sendLocation(long chat_id, float latitude, float longitude){
        String Address = this.Address + "sendLocation";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("latitude", Float.toString(latitude));
        post.append("longitude", Float.toString(longitude));
        return new JSONObject(post.start().toString());
    }

    public JSONObject editMessageLiveLocation(long chat_id, long message_id, float latitude, float longitude){
        String Address = this.Address + "editMessageLiveLocation";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("message_id", Long.toString(message_id));
        post.append("latitude", Float.toString(latitude));
        post.append("longitude", Float.toString(longitude));
        return new JSONObject(post.start().toString());
    }

    public JSONObject stopMessageLiveLocation(long chat_id, long message_id){
        String Address = this.Address + "stopMessageLiveLocation";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("message_id", Long.toString(message_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendVenue(long chat_id, float latitude, float longitude, String title, String Address){
        String url = this.Address + "sendVenue";
        Post post = new Post(url);
        post.append("chat_id", Long.toString(chat_id));
        post.append("latitude", Float.toString(latitude));
        post.append("longitude", Float.toString(longitude));
        post.append("title", title);
        post.append("address", Address);
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendContact(long chat_id, String phone_number, String first_name, String last_name){
        String Address = this.Address + "sendContact";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("phone_number", phone_number);
        post.append("first_name", first_name);
        post.append("last_name", last_name);
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendPoll(long chat_id, String question, String[] options, boolean is_anonymous){
        String Address = this.Address + "sendPoll";
        JSONObject request = new JSONObject();
        request.append("chat_id", chat_id).append("question", question).append("options", options).append("is_anonymous", is_anonymous);
        jsonpost post = new jsonpost(Address);
        post.append(request);
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendDice(long chat_id){
        String Address = this.Address + "sendDice";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject sendChatAction(long chat_id, String action){
        String Address = this.Address + "sendChatAction";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("action", action);
        return new JSONObject(post.start().toString());
    }

    public JSONObject getUserProfilePhotos(long user_id){
        String Address = this.Address + "getUserProfilePhotos";
        Post post = new Post(Address);
        post.append("user_id", Long.toString(user_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject getFile(String file_id){
        String Address = this.Address + "getFile";
        Post post = new Post(Address);
        post.append("file_id", file_id);
        return new JSONObject(post.start().toString());
    }

    public JSONObject banChatMember(long chat_id, long user_id){
        String Address = this.Address + "banChatMember";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("user_id", Long.toString(user_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject unbanChatMember(long chat_id, long user_id){
        String Address = this.Address + "unbanChatMember";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("user_id", Long.toString(user_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject restrictChatMember
            (long chat_id, long user_id,boolean can_send_messages, boolean can_send_media_messages, boolean can_send_polls, boolean can_send_other_messages, boolean can_add_web_page_previews, boolean can_change_info,boolean can_invite_users, boolean can_pin_messages){
        String Address = this.Address + "restrictChatMember";
        JSONObject permissions = new JSONObject();
        permissions.append("can_send_messages", can_send_messages).append("can_send_media_messages", can_send_media_messages).append("can_send_polls", can_send_polls).append("can_send_other_messages", can_send_other_messages).append("can_add_web_page_previews", can_add_web_page_previews).append("can_change_info", can_change_info).append("can_invite_users", can_invite_users).append("can_pin_messages", can_pin_messages);
        JSONObject request = new JSONObject();
        request.append("chat_id", Long.toString(chat_id)).append("user_id", Long.toString(user_id)).append("permissions", permissions);
        jsonpost post = new jsonpost(Address);
        post.append(request);
        return new JSONObject(post.start().toString());
    }

    public JSONObject promoteChatMember
            (long chat_id, long user_id, boolean is_anonymous, boolean can_manage_chat, boolean can_post_messages, boolean can_edit_messages, boolean can_delete_messages, boolean can_manage_video_chats,
             boolean can_restrict_members, boolean can_promote_members, boolean can_change_info, boolean can_invite_users, boolean can_pin_messages, boolean can_manage_topics){
        String Address = this.Address + "promoteChatMember";
        JSONObject request = new JSONObject();
        request.append("chat_id", Long.toString(chat_id)).append("user_id", Long.toString(user_id)).append("is_anonymous", is_anonymous).append("can_manage_chat", can_manage_chat).append("can_post_messages", can_post_messages).append("can_edit_messages", can_edit_messages).append("can_delete_messages", can_delete_messages).append("can_manage_video_chats", can_manage_video_chats)
                .append("can_restrict_members", can_restrict_members).append("can_promote_members", can_promote_members).append("can_change_info", can_change_info).append("can_invite_users", can_invite_users).append("can_pin_messages", can_pin_messages).append("can_manage_topics", can_manage_topics);
        jsonpost post = new jsonpost(Address);
        post.append(request);
        return new JSONObject(post.start().toString());
    }

    public JSONObject setChatAdministratorCustomTitle(long chat_id, long user_id, String custom_title){
        String Address = this.Address + "setChatAdministratorCustomTitle";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id)).append("user_id", Long.toString(user_id)).append("custom_title", custom_title);
        return new JSONObject(post.start().toString());
    }

    public JSONObject setChatPhoto(long chat_id, File file){
        String Address = this.Address + "setChatPhoto";
        Multipart multi = new Multipart(Address);
        String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        multi.append("chat_id", Long.toString(chat_id)).input_file("image/" + ext, "photo", file);
        return new JSONObject(multi.start().toString());
    }

    public JSONObject deleteChatPhoto(long chat_id){
        String Address = this.Address + "deleteChatPhoto";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject setChatTitle(long chat_id, String title){
        String Address = this.Address + "setChatTitle";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id)).append("title", title);
        return new JSONObject(post.start().toString());
    }

    public JSONObject setChatDescription(long chat_id, String description){
        String Address = this.Address + "setChatDescription";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id)).append("description", description);
        return new JSONObject(post.start().toString());
    }

    public JSONObject pinChatMessage(long chat_id, long message_id, boolean disable_notification){
        String Address = this.Address + "pinChatMessage";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id)).append("message_id", Long.toString(message_id)).append("disable_notification", Boolean.toString(disable_notification));
        return new JSONObject(post.start().toString());
    }

    public JSONObject unpinChatMessage(long chat_id, long message_id){
        String Address = this.Address + "unpinChatMessage";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id)).append("message_id", Long.toString(message_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject unpinAllChatMessages(long chat_id){
        String Address = this.Address + "unpinAllChatMessages";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject leaveChat(long chat_id){
        String Address = this.Address + "leaveChat";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject getChat(long chat_id){
        String Address = this.Address + "getChat";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject getChatAdministrators(long chat_id){
        String Address = this.Address + "getChatAdministrators";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject getChatMemberCount(long chat_id){
        String Address = this.Address + "getChatMemberCount";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject getChatMember(long chat_id, long user_id){
        String Address = this.Address + "getChatMember";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id)).append("user_id", Long.toString(user_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject editMessageText(long chat_id, long message_id, String text){
        String Address = this.Address + "editMessageText";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("message_id", Long.toString(message_id));
        post.append("text", text);
        return new JSONObject(post.start().toString());
    }

    public JSONObject editMessageCaption(long chat_id, long message_id, String caption){
        String Address = this.Address + "editMessageCaption";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("message_id", Long.toString(message_id));
        post.append("caption", caption);
        return new JSONObject(post.start().toString());
    }

    public JSONObject stopPoll(long chat_id, long message_id){
        String Address = this.Address + "stopPoll";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("message_id", Long.toString(message_id));
        return new JSONObject(post.start().toString());
    }

    public JSONObject deleteMessage(long chat_id, long message_id){
        String Address = this.Address + "deleteMessage";
        Post post = new Post(Address);
        post.append("chat_id", Long.toString(chat_id));
        post.append("message_id", Long.toString(message_id));
        return new JSONObject(post.start().toString());
    }
}
