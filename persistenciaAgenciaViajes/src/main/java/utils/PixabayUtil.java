/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


import entidades.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author pauli
 */
public class PixabayUtil {
    private static final String API_KEY = "API_KEY"; // Pon tu key aquí
    private static final String BASE_URL = "https://pixabay.com/api/";

    private Gson gson = new Gson();

    public String buscarImagen(String query) {
        try {
            // Construir la URL con parámetros
            String urlStr = BASE_URL + "?key=" + API_KEY + "&q=" + 
                            java.net.URLEncoder.encode(query, "UTF-8") + "&image_type=photo&per_page=3";

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer respuesta
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parsear JSON
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
                JsonArray hits = jsonObject.getAsJsonArray("hits");

                if (hits.size() > 0) {
                    JsonObject primerHit = hits.get(0).getAsJsonObject();
                    // Obtener URL de la imagen tamaño medio
                    return primerHit.get("webformatURL").getAsString();
                } else {
                    return null; // No hay resultados
                }
            } else {
                System.err.println("Error en la conexión a Pixabay API, código: " + responseCode);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}