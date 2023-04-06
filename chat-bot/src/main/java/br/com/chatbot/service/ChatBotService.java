package br.com.chatbot.service;

import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBotService {

    private static final String API_KEY = "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";

    private static final Double TEMPERATURA = 0.02;

    private static final String MODELO = "gpt-3.5-turbo";

    public String apiChat(String perguntaUser){
        try {
            ChatMessage user = new ChatMessage(ChatMessageRole.USER.value(), perguntaUser);

            OpenAiService openAiService = new OpenAiService(API_KEY);
            List<ChatMessage> messages = new ArrayList<>(getConfigChatMessage(user));

            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                    .model(MODELO)
                    .maxTokens(1000)
                    .temperature(TEMPERATURA)
                    .messages(messages)
                    .build();

            return openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage().getContent();

        }
        catch (Exception e){
            return "Obtive um erro ao tentar gerar a resposta. Tente novamente";
        }
    }

    private static List<ChatMessage> getConfigChatMessage(ChatMessage user){
        List<ChatMessage> messagesConfig = new ArrayList<>();
        messagesConfig.add(geConfigSystem());
        messagesConfig.add(getConfigAssistent());
        messagesConfig.add(user);

        return messagesConfig;
    }

    private static ChatMessage geConfigSystem(){
        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Você será um chatbot de um hotel para pets");
        conteudo.append("\n");

        return new ChatMessage(ChatMessageRole.SYSTEM.value(), conteudo.toString());
    }

    private static ChatMessage getConfigAssistent(){
        StringBuilder conteudo = new StringBuilder();
        /**
         * Caso o usuário tome alguma das seguintes atitudes:
         * */
        conteudo.append("ódio: Conteúdo que expressa, incita ou promove o ódio com base em raça, gênero, etnia, religião, nacionalidade, orientação sexual, deficiência ou casta;");
        conteudo.append("\n");
        conteudo.append("ódio/ameaça: conteúdo de ódio que também inclui violência ou ofensa grave contra o grupo-alvo;");
        conteudo.append("\n");
        conteudo.append("automutilação: Conteúdo que promove, incentiva ou retrata atos de autoagressão, como suicídio, corte e distúrbios alimentares;");
        conteudo.append("\n");
        conteudo.append("sexual: Conteúdo destinado a despertar excitação sexual, como a descrição de atividade sexual ou que promova serviços sexuais (excluindo educação sexual e bem-estar);");
        conteudo.append("\n");
        conteudo.append("sexual/menores: Conteúdo sexual que inclua um indivíduo com menos de 18 anos de idade;");
        conteudo.append("\n");
        conteudo.append("violencia: Conteúdo que promove ou glorifica a violência ou celebra o sofrimento ou a humilhação de outras pessoas;");
        conteudo.append("\n");
        conteudo.append("violencia/graphic: Conteúdo violento que retrata morte, violência ou lesões físicas graves em detalhes gráficos extremos;");
        conteudo.append("\n");

        conteudo.append("Não responda caso o usuário pergunte ou comente sobre outro assunto aleatórios fora do que foi definido");
        conteudo.append("\n");
        conteudo.append("Responda saudações, despedidas, agradecimentos");
        conteudo.append("\n");

        return new ChatMessage(ChatMessageRole.ASSISTANT.value(), conteudo.toString());
    }


}


//    ChatMessage system = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Você será um chatbot de um hotel para pets");
//    ChatMessage assistent = new ChatMessage(ChatMessageRole.ASSISTANT.value(), "Não responda caso o usuário pergunte ou comente outro assunto fora do que foi definido");
//    ChatMessage errors = new ChatMessage(ChatMessageRole.ASSISTANT.value(),
//            "   \"hate\": \"Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.\",\n" +
//                    "        \"hate/threatening\": \"Hateful content that also includes violence or serious harm towards the targeted group.\",\n" +
//                    "        \"self-harm\": \"Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.\",\n" +
//                    "        \"sexual\": \"Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).\",\n" +
//                    "        \"sexual/minors\": \"Sexual content that includes an individual who is under 18 years old.\",\n" +
//                    "        \"violence\": \"Content that promotes or glorifies violence or celebrates the suffering or humiliation of others.\",\n" +
//                    "        \"violence/graphic\": \"Violent content that depicts death, violence, or serious physical injury in extreme graphic detail.\"");
//
//    ChatMessage user = new ChatMessage(ChatMessageRole.USER.value(), perguntaUser);
//
//    OpenAiService openAiService = new OpenAiService(API_KEY);
//    List<ChatMessage> messages = new ArrayList<>(List.of(system, assistent, user, errors));
//
//    ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
//            .model(MODELO)
//            .maxTokens(1000)
//            .temperature(TEMPERATURA)
//            .messages(messages)
//            .build();
//
//        return openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage().getContent();