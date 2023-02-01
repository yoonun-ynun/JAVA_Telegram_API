import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {
    public Post(String Address){
        this.Address = Address;
    }
    private final StringBuilder data = new StringBuilder();

    private final String Address;
    public StringBuilder start(){
        try {
            URL url = new URL(Address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(data.toString());
            bw.flush();
            bw.close();

            String line;
            StringBuilder result = new StringBuilder();
            BufferedReader br;

            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null)
                    result.append(line).append('\n');
                return result;
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null)
                    result.append(line).append('\n');
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Post append(String id, String data){
        if(this.data.length()>1){
            this.data.append("&");
        }
        this.data.append(id).append("=").append(data);
        return this;
    }
}
