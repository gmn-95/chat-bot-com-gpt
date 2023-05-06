package br.com.chatbot.controllers;


import br.com.chatbot.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping("/pergunta")
    public Object respostaChat(@RequestBody Object pergunta){
        return chatBotService.apiChat((String) pergunta);
    }

    @PostMapping ("/tipoChat")
    public ResponseEntity<?> alteraTipoChat(@RequestBody String tipoChat){
        try {
            chatBotService.setTipoChat(tipoChat);
            chatBotService.setResetaConfig(true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Alterado!");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao trocar tipo do chat");
        }
    }
}
