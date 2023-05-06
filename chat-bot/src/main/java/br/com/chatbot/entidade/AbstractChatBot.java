package br.com.chatbot.entidade;


import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractChatBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractChatBot.class);

    protected List<String> contexto;

    private final String API_KEY = "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";

    private final Double TEMPERATURA = 0.01;

    private final String MODELO = "gpt-3.5-turbo";

    protected String regrasPadrao(){
        StringBuilder conteudo = new StringBuilder();

        conteudo.append(
                "ódio: Conteúdo que expressa, incita ou promove o ódio com base em raça, gênero, etnia, religião, nacionalidade, orientação sexual, deficiência ou casta."
        );
        conteudo.append(
                "ódio/ameaça: conteúdo de ódio que também inclui violência ou ofensa grave contra o grupo-alvo."
        );
        conteudo.append(
                "automutilação: Conteúdo que promove, incentiva ou retrata atos de autoagressão, como suicídio, corte e distúrbios alimentares."
        );
        conteudo.append(
                "sexual: Conteúdo destinado a despertar excitação sexual, como a descrição de atividade sexual ou que promova serviços sexuais (excluindo educação sexual e bem-estar)."
        );
        conteudo.append(
                "sexual/menores: Conteúdo sexual que inclua um indivíduo com menos de 18 anos de idade."
        );
        conteudo.append(
                "violencia: Conteúdo que promove ou glorifica a violência ou celebra o sofrimento ou a humilhação de outras pessoas."
        );
        conteudo.append(
                "violencia/graphic: Conteúdo violento que retrata morte, violência ou lesões físicas graves em detalhes gráficos extremos."
        );
        conteudo.append(
                "Não responda caso o usuário pergunte ou comente sobre outro assunto aleatórios fora do que foi definido."
        );
        conteudo.append(
                "Responda saudações, despedidas, agradecimentos."
        );

        return conteudo.toString();
    }

    public List<ChatMessage> getConfigChatMessage(ChatMessage user, AbstractChatBot chatBotClass) {
        List<ChatMessage> messagesConfig = new ArrayList<>();

        try {
            messagesConfig.add(chatBotClass.geConfigSystem());
            messagesConfig.add(chatBotClass.getConfigAssistent());
            messagesConfig.add(user);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return messagesConfig;
    }

    public void verificaTamanhoDaConfig(StringBuilder conteudo){
        if(conteudo.length() > 4097){
            LOGGER.info("Quantidade de tokens: " + conteudo.length() );
            throw new RuntimeException("Excedeu o limite de tokens");
        }
    }

    public ChatMessage getConfigAssistent() {
        StringBuilder conteudo = new StringBuilder();

        if(this.contexto != null){
            if(!getContexto().isEmpty()){
                conteudo.append(getContexto());
            }
        }

        return new ChatMessage(ChatMessageRole.ASSISTANT.value(), conteudo.toString());
    }

    public abstract ChatMessage geConfigSystem();

    public List<String> getContexto() {
        return contexto;
    }

    public void setContexto(List<String> contexto) {
        this.contexto = contexto;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public Double getTEMPERATURA() {
        return TEMPERATURA;
    }

    public String getMODELO() {
        return MODELO;
    }
}
