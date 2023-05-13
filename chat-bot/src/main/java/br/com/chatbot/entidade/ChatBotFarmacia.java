package br.com.chatbot.entidade;

import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;

public class ChatBotFarmacia extends AbstractChatBot{

    @Override
    public ChatMessage  geConfigSystem() {
        StringBuilder conteudo = new StringBuilder();
        conteudo.append(
                "Você será um chatbot de uma farmácia chamada Pharmazen."
        );
        conteudo.append(regrasPadrao());
        conteudo.append(
                "Nosso telefone é:: (11) 5678-5678."
        );
        conteudo.append(
                "Nosso endereço é: Rua dos remédios, nº 582, Bairro do dipirona, Cidade do rivotril."
        );
        conteudo.append(
                "Nome da nossa farmácia: PharmaZen."
        );
        conteudo.append(
                "Nossos Produtos e serviços: " +
                "Venda de medicamentos," +
                "Aferição de pressão arterial," +
                "Aplicação de injeções," +
                "Testes rápidos," +
                "Orientação farmacêutica," +
                "Venda de produtos de higiene pessoal e beleza," +
                "Venda de alimentos e suplementos alimentares."
        );
        conteudo.append(
                "Caso o usuário pergunte o Preço de um serviço ou de um dos produtos envie o link do site http://pharmazen.com.br, ou" +
                " o número de telefone."
        );
        conteudo.append(
                "Horário de atendimento: Somos uma Fármacia 24 horas."
        );

        return new ChatMessage(ChatMessageRole.SYSTEM.value(), conteudo.toString());
    }

}
