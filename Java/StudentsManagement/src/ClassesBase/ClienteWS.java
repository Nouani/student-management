package ClassesBase;
import java.io.*;
/*
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
*/
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
ClienteWS class represents the client for an API.
This class allows the user to use an API.
In this class, we can find some static methods.
@author André Luís dos Reis Gomes de Carvalho.
@since 2020.
*/
public class ClienteWS
{
	/** 
     * Gets an Object.
     * Gets an Object using the API.
     * @param tipoObjetoRetorno the Object's class.
     * @param urlWebService the API's url.
     * @param parametros parameters needed by the API.
     * @return the found Object.
    */
    public static Object getObjeto (Class tipoObjetoRetorno,
                                    String urlWebService,
                                    String... parametros)
    {
        Object objetoRetorno = null;

        try
        {
            for(String parametro: parametros)
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20");

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            connection.disconnect();

            return fromJson(responseJson, tipoObjetoRetorno);
        }
        catch (Exception erro)
        {
             erro.printStackTrace();
        }

        return objetoRetorno;
    }
    
    /** 
     * Posts an Object.
     * Posts an Object using the API.
     * @param tipoObjetoRetorno the Object's class.
     * @param urlWebService the API's url.
     * @param objetoEnvio the object sent to the API.
     * @return the post result.
    */
    public static Object postObjeto (Object objetoEnvio,
                                     Class tipoObjetoRetorno,
                                     String urlWebService)
    {
        Object objetoRetorno = null;

        try
        {
            String requestJson = toJson(objetoEnvio);

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
	    connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));

            DataOutputStream stream =
            new DataOutputStream (connection.getOutputStream());
            stream.write (requestJson.getBytes("UTF-8"));
            stream.flush ();
            stream.close ();
            connection.connect ();

            String responseJson = inputStreamToString (connection.getInputStream());
            connection.disconnect();
            objetoRetorno = fromJson (responseJson, tipoObjetoRetorno);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return objetoRetorno;
    }

    /** 
     * Converts an InputStream to String.
     * Converts an InputStream to String.
     * @param is InputStream to be converted.
     * @throws IOException when any action goes wrong.
     * @return the String made.
    */
    public static String inputStreamToString (InputStream is) throws IOException
    {
        if (is != null)
        {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try
            {
                Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1)
                {
                    writer.write(buffer, 0, n);
                }
            }
            finally
            {
                is.close();
            }

            return writer.toString();
        }
        else
        {
            return "";
        }
    }
    
    /** 
     * Converts an Object to JSon.
     * Converts an Object to JSon.
     * @param objeto Object to be converted.
     * @throws Exception when any action goes wrong.
     * @return the String made.
    */
    public static String toJson(Object objeto) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter jsonValue = new StringWriter();
        mapper.writeValue(new PrintWriter(jsonValue), objeto);
        return jsonValue.toString();
    }
    
    /** 
     * Converts an Object from JSon.
     * Converts an Object from JSon.
     * @param json JSon to be converted.
     * @param objectClass the "to be converted" Object's class.
     * @throws Exception when any action goes wrong.
     * @return the Object made.
    */
    public static Object fromJson(String json, Class objectClass) throws Exception
    {
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(json);
        Object obj = jp.readValueAs(objectClass);
        return obj;
    }
}
