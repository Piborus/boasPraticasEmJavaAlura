package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import br.com.alura.service.AbrigoService;
import com.fasterxml.jackson.databind.ObjectMapper; // Add this import
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {

    private final ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private final AbrigoService abrigoService = new AbrigoService(client);
    private final HttpResponse<String> response = mock(HttpResponse.class);
    private final Abrigo abrigo = new Abrigo("Teste", "85913131313", "teste@teste.com");

    @Test
    public void deveVerificarSeHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados: ";
        String expectedIdNome = "0 - Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        ObjectMapper objectMapper = new ObjectMapper(); // Create ObjectMapper instance


        String jsonAbrigo = objectMapper.writeValueAsString(new Abrigo[]{abrigo});

        when(response.body()).thenReturn(jsonAbrigo);
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdNome = lines[1];

        Assertions.assertEquals(expectedAbrigosCadastrados.trim(), actualAbrigosCadastrados.trim());
        Assertions.assertEquals(expectedIdNome, actualIdNome);

    }

    @Test
    public void deveVerificarSeNaoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expected = "Não há Abrigos cadastrados";


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[0];


        Assertions.assertEquals(expected.trim(), actual.trim());


    }
}