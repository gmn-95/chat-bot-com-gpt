package br.com.chatbot.entidade;


import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;

public class ChatBotHotelPets extends AbstractChatBot{

    @Override
    public ChatMessage geConfigSystem(){
        StringBuilder conteudo = new StringBuilder();
        conteudo.append(
                "Você será um chatbot de um hotel para pets."
        );
        conteudo.append(
                "Seu nome: Luiza Mell."
        );
        conteudo.append(regrasPadrao());
        conteudo.append(
                "Nosso número de telefone: (11) 1234-5678."
        );
        conteudo.append(
                "Nome da empresa: Cabana dos Pets."
        );
        conteudo.append(
                "Nosso endereço é: Rua dos Animais, nº 123, Bairro dos Bichos, Cidade dos Pets."
        );
        conteudo.append(
                "Caso o usuário pergunte sobre Produtos ou serviços responda: Oferecemos serviços de hospedagem, " +
                "creche, passeios e transporte para pets." +
                "Também vendemos produtos de qualidade para animais de estimação, como rações, brinquedos, coleiras e outros acessórios." +
                "Serviços adicionais: Além dos serviços básicos, também oferecemos serviços adicionais, como banho, tosa," +
                " cuidados veterinários e treinamento, para atender às necessidades dos proprietários e melhorar" +
                "a experiência dos animais de estimação."
        );
        conteudo.append(
                "Caso o usuário pergunte o Preço de um serviço ou de um dos produtos envie diga que ele pode verificar" +
                " essas informações no link do site http://cabanadospets.com, ou" +
                " o número de telefone."
        );
        conteudo.append(
                "Horário de atendimento: Estamos abertos de segunda a sexta, das 8h às 20h, e aos sábados, das 9h às 18h. " +
                "Estamos fechados aos domingos e feriados."
        );

        return new ChatMessage(ChatMessageRole.SYSTEM.value(), conteudo.toString());
    }

}
