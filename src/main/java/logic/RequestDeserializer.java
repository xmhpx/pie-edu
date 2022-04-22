package logic;

import com.google.gson.*;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class RequestDeserializer implements JsonDeserializer<Request> {
    private static final Logger log = LogManager.getLogger(RequestDeserializer.class);

    private static RequestDeserializer requestDeserializer;

    public static RequestDeserializer getInstance(){
        if(requestDeserializer == null)requestDeserializer = new RequestDeserializer();
        return requestDeserializer;
    }



    private final String requestTypeElementName;
    private final Gson gson;
    private final Map<String, Class<? extends Request>> requestTypes;



    public RequestDeserializer() {
        this.requestTypeElementName = "type";
        this.gson = new Gson();
        this.requestTypes = new HashMap<>();
    }

    public void addRequestType(String requestTypeName, Class<? extends Request> requestType) {
        if(requestTypeName == null){
            log.warn("'requestTypeName' is null");
            return;
        }
        if(requestType == null){
            log.warn("'requestType' is null");
            return;
        }
        requestTypes.put(requestTypeName, requestType);
    }

    @Override
    public Request deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if(json == null){
            log.warn("'json' is null");
            return null;
        }
        if(typeOfT == null){
            log.warn("'typeOfT' is null");
            return null;
        }
        if(context == null){
            log.warn("'context' is null");
            return null;
        }

        JsonObject requestObject = json.getAsJsonObject();
        JsonElement requestTypeElement = requestObject.get(requestTypeElementName);

        Class<? extends Request> requestType = requestTypes.get(requestTypeElement.getAsString());
        return gson.fromJson(requestObject, requestType);
    }
}
