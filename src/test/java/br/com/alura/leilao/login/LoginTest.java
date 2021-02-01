package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fechar();
    }

    @Test
    @DisplayName("Deveria efetuar login com dados válidos.")
    public void validLogin(){
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuarLogin();

        assertFalse(paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    @DisplayName("Não deveria logar com dados inválidos.")
    public void invalidLogin(){
        paginaDeLogin.preencheFormularioDeLogin("fulaninho", "pass");
        paginaDeLogin.efetuarLogin();

        assertTrue(paginaDeLogin.isPaginaDeLogin());
        assertNull(paginaDeLogin.getNomeUsuarioLogado());
        assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    @DisplayName("Não deveria acessar página restrita sem estar logado.")
    public void restrictPage(){
        paginaDeLogin.navegaParaPaginaDeLances();

        assertTrue(paginaDeLogin.isPaginaDeLogin());
        assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }

}
