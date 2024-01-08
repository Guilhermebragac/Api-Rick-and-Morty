package pacote;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class Requisição implements AcaoRotinaJava{
private static final String BASE_URL = "https://rickandmortyapi.com/api/character/";

	
	//BOTÃO ERP SANKHYA
    public void doAction(ContextoAcao ctx) throws Exception {
        String personagem = (String) ctx.getParam("PERSONAGEM");

        if (personagem != null) {
            main(null, personagem);
        }
    }

    //VALIDAÇÃO
    public static void main(String[] args, String personagem) {
        try {
            int characterId;

            if ("Johnny".equals(personagem)) {
                characterId = 183;
            } else {
                characterId = 1;
            }

            String characterUrl = BASE_URL + characterId;
            String characterJson = sendHttpGetRequest(characterUrl);
            processCharacterData(characterJson, characterId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //REQUEST
    private static String sendHttpGetRequest(String apiUrl) throws Exception {
        return "[{ \"id\": 1, \"name\": \"Rick Sanchez\", \"status\": \"Alive\", \"species\": \"Human\", \"type\": \"\", \"gender\": \"Male\", \"origin\": { \"name\": \"Earth (C-137)\", \"url\": \"https://rickandmortyapi.com/api/location/1\" }, \"location\": { \"name\": \"Earth (Replacement Dimension)\", \"url\": \"https://rickandmortyapi.com/api/location/20\" }, \"image\": \"https://rickandmortyapi.com/api/character/avatar/1.jpeg\", \"episode\": [ \"https://rickandmortyapi.com/api/episode/1\", \"https://rickandmortyapi.com/api/episode/2\" ], \"url\": \"https://rickandmortyapi.com/api/character/1\", \"created\": \"2017-11-04T18:48:46.250Z\" }, { \"id\": 183, \"name\": \"Johnny Depp\", \"status\": \"Alive\", \"species\": \"Human\", \"type\": \"\", \"gender\": \"Male\", \"origin\": { \"name\": \"Earth (C-500A)\", \"url\": \"https://rickandmortyapi.com/api/location/23\" }, \"location\": { \"name\": \"Earth (C-500A)\", \"url\": \"https://rickandmortyapi.com/api/location/23\" }, \"image\": \"https://rickandmortyapi.com/api/character/avatar/183.jpeg\", \"episode\": [ \"https://rickandmortyapi.com/api/episode/8\" ], \"url\": \"https://rickandmortyapi.com/api/character/183\", \"created\": \"2017-12-29T18:51:29.693Z\" }]";
    }

    private static void processCharacterData(String characterJson, int characterId) throws SQLException, IOException {
        JSONArray characterArray = new JSONArray(characterJson);

        for (int i = 0; i < characterArray.length(); i++) {
            JSONObject characterObject = characterArray.getJSONObject(i);
            if (characterObject.getInt("id") == characterId) {
                String imageUrl = characterObject.getString("image");
                updateCampo(imageUrl);
                break;
            }
        }
    }

    private static void updateCampo(String imageUrl) throws SQLException, IOException {
    	 SessionHandle hnd = JapeSession.open();
		    hnd.setFindersMaxRows(-1);
		    EntityFacade entity = EntityFacadeFactory.getDWFFacade();
		    JdbcWrapper jdbc = entity.getJdbcWrapper();
		    jdbc.openSession();
	  
//	            String imageUrl1 = "https://example.com/image.jpg";
	            byte[] imageBytes = convertImageUrlToBytes(imageUrl);
	            
	            
	            //UPDATES EM BANCO DE DADOS (DADOS DE TABELA CONFIDENCIAIS)
	            try {
	                NativeSql sql = new NativeSql(jdbc);
	                sql.appendSql("UPDATE EM TABELA SANKHYA SET CAMPO SANKHYA = :TEXTO WHERE ID = :ID");
	                sql.setNamedParameter("TEXTO", imageUrl);
	                sql.setNamedParameter("ID", BigDecimal.valueOf(1));
	                sql.executeUpdate();
	                
	               
	                
	            } catch (Exception e) {
	                e.printStackTrace();}

	            try {
	                NativeSql sql = new NativeSql(jdbc);
	                sql.appendSql("UPDATE EM TABELA SANKHYA SET CAMPO SANKHYA = :TEXTO WHERE ID = :ID");
	                sql.setNamedParameter("TEXTO", imageBytes);
	                sql.setNamedParameter("ID", BigDecimal.valueOf(1));
	                sql.executeUpdate();
	                
	               
	                
	            } catch (Exception e) {
	                e.printStackTrace();}
	    	   
	    	
	    	 finally {
	             JdbcWrapper.closeSession(jdbc);
	             JapeSession.close(hnd);}
	        }
	        
    
	        
    
    
    private static byte[] convertImageUrlToBytes(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            in = url.openStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}

