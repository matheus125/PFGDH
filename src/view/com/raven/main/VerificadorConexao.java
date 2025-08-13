package view.com.raven.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerificadorConexao {
    public static boolean temConexao() {
        try {
            final URL url = new URL("http://www.google.com");
            final HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("HEAD");
            conexao.setConnectTimeout(3000);
            conexao.connect();
            return (conexao.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            return false;
        }
    }
}