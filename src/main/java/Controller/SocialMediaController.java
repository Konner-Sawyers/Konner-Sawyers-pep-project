package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }


    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registrationHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::newMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getOneMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registrationHandler(Context ctx) throws JsonProcessingException{        
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.createAccount(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
        else{
            ctx.status(400);
        }
    }

    private void loginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.login(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
        else{
            ctx.status(401);
        }
    }

    private void newMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null){
            ctx.json(mapper.writeValueAsString(createdMessage));
        }
        else{
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx){
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getOneMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String str_id = ctx.pathParam("message_id");
        Message message = messageService.getMessageById(Integer.parseInt(str_id));
        System.out.println(message);
        if(message != null){
            ctx.json(mapper.writeValueAsString(message));
        }
        else{
            ctx.json("");
        }
    }

    private void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String str_id = ctx.pathParam("message_id");
        Message message = messageService.deleteMessageById(Integer.parseInt(str_id));
        System.out.println("RESULT: -> " + message);
        
        if(message != null){
            ctx.json(mapper.writeValueAsString(message));
        }
        else{
            ctx.json("");
        }
    }

    private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String str_id = ctx.pathParam("message_id");
        Message message = messageService.updateMessageById(Integer.parseInt(str_id), mapper.readValue(ctx.body(), Message.class));
        if(message != null){
            ctx.json(mapper.writeValueAsString(message));
        }
        else{
            ctx.status(400);
        }
    }

    private void getAllMessagesByUserIdHandler(Context ctx) throws JsonProcessingException{
        String str_id = ctx.pathParam("account_id");
        List <Message> messages = messageService.getMessageByUserId(Integer.parseInt(str_id));
        ctx.json(messages);
    }


}