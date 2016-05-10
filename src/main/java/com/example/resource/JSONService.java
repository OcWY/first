package com.example.resource;


import com.google.gwt.thirdparty.guava.common.io.Files;
import object.Message;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

@Path("/json")
public class JSONService {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Message> getTrackInJSON() {
        JSONParser parser = new JSONParser();
        File f = new File("abc.txt");
        if(!f.exists()){
           return null;
        }
        try {
            Scanner scn=new Scanner(f,"UTF-8");
            ArrayList<JSONObject> json=new ArrayList<JSONObject>();
            ArrayList<Message> messages = new ArrayList<>();
            while(scn.hasNext()) {
                JSONObject jsonObject= (JSONObject) new JSONParser().parse(scn.nextLine());
                Message message = new Message();
                String name = (String) jsonObject.get("name");
                message.setName(name);

                String city = (String) jsonObject.get("title");
                message.setTitle(city);

                String job = (String) jsonObject.get("sender");
                message.setSender(job);

                // loop array
                String url = (String) jsonObject.get("url");
                message.setUrl(url);
                messages.add(message);
            }
           /* Iterator<String> iterator = cars.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/
            return messages;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Message message) {
        boolean isNew = false;
        String result = "Message saved : " + message;
        System.out.println(result);
        File f = new File("abc.txt");
        if(!f.exists()){
            try {
                f.createNewFile();
                isNew = true;
            } catch (IOException e) {
               return Response.status(200).build();
            }
        }
        try
        {
            String jString =(isNew ? "" : System.lineSeparator())+message.toJsonString();
            Files.append(jString, f, Charset.forName("UTF-8"));
        }

        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

        return Response.status(201).entity(result).build();

    }

}