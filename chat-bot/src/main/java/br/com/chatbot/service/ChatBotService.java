package br.com.chatbot.service;

import br.com.chatbot.entidade.AbstractChatBot;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ChatBotService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ChatBotService.class);

    /**
     * Informa qual chatbot está sendo utilizado no momento
     * */
    private String tipoChat = "ChatBotHotelPets";

    /**
     * Path dos chatBot's
     * */
    private static final String pathPadrao = "br.com.chatbot.entidade.";

    /**
     * Lista contendo o contexto da contexto da conversa entre chat e usuario
     * */
    private List<String> contextoConversa;

    /**
     * Decide se deve ou n resetar o contexto da conversa
     * */
    private Boolean resetaConfig = false;

    public String apiChat(String perguntaUser){
        try {
            ChatMessage user = new ChatMessage(ChatMessageRole.USER.value(), perguntaUser);

            /**
             * Define qual chat será usado de forma dinâmica
             * */
            AbstractChatBot chatBot = Class.forName(pathPadrao + getTipoChat()).asSubclass(AbstractChatBot.class).getDeclaredConstructor().newInstance();

            OpenAiService openAiService = new OpenAiService(chatBot.getAPI_KEY());

            if(resetaConfig){
                contextoConversa = new LinkedList<>();
                resetaConfig = false;
            }

            contextoConversa.add("user: " + perguntaUser);
            chatBot.setContexto(contextoConversa);

            List<ChatMessage> messages = new ArrayList<>(chatBot.getConfigChatMessage(user, chatBot));

            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                    .model(chatBot.getMODELO())
                    .temperature(chatBot.getTEMPERATURA())
                    .messages(messages)
                    .build();

            String respostaDoChat = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage().getContent();
            contextoConversa.add("assistant: " + respostaDoChat);
            chatBot.setContexto(contextoConversa);
            return respostaDoChat;

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return "Obtive um erro ao tentar gerar a resposta. Tente novamente";
        }
    }

    public String getTipoChat() {
        return tipoChat;
    }

    public void setTipoChat(String tipoChat) {
        this.tipoChat = tipoChat;
    }

    public Boolean getResetaConfig() {
        return resetaConfig;
    }

    public void setResetaConfig(Boolean resetaConfig) {
        this.resetaConfig = resetaConfig;
    }
}