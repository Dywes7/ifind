package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

import br.edu.br.meuprimeirospringboot.entity.Anuncio;
import br.edu.br.meuprimeirospringboot.service.ChatGPTService;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {

	private final OpenAiService openAiService;
	
	public ChatGPTServiceImpl(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }
	
	@Override
	public List<Long> buscarAnunciosRelevantes(String consulta, List<Anuncio> anuncios) {
	    List<Long> ids = new ArrayList<>();

	    try {
	        // Monta a lista de anúncios para o prompt
	    	String anunciosTexto = anuncios.stream()
	    		    .map(a -> String.format("ID: %d | Categoria: %s | Título: %s | Descrição: %s",
	    		            a.getId(), a.getCategoria(), a.getTitulo(), a.getDescricao()))
	    		    .collect(Collectors.joining("\n"));

	    	String prompt = 
	    		    "Abaixo está uma lista de anúncios com seus IDs, categorias, títulos e descrições.\n" +
	    		    "Com base na consulta do usuário: \"" + consulta + "\", responda com apenas uma linha contendo apenas os números dos IDs dos anúncios mais relevantes, separados por vírgula, sem nenhum texto adicional.\n" +
	    		    "Exemplo de resposta: 1, 2, 3\n\n" +
	    		    anunciosTexto;

	        List<ChatMessage> messages = new ArrayList<>();
	        messages.add(new ChatMessage("system", "Você é um sistema de recomendação de anúncios."));
	        messages.add(new ChatMessage("user", prompt));

	        ChatCompletionRequest request = ChatCompletionRequest.builder()
	                .model("gpt-3.5-turbo")
	                .messages(messages)
	                .temperature(0.3)
	                .maxTokens(100)
	                .build();

	        ChatCompletionResult result = openAiService.createChatCompletion(request);
	        String resposta = result.getChoices().get(0).getMessage().getContent();

	        System.out.println("🔎 Resposta da IA:\n" + resposta);

	        Pattern pattern = Pattern.compile("\\d+");
	        Matcher matcher = pattern.matcher(resposta);

	        while (matcher.find()) {
	            ids.add(Long.parseLong(matcher.group()));
	        }

	    } catch (Exception e) {
	        System.err.println("⚠️ Erro ao chamar a API da OpenAI: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return ids;
	}

	
}
