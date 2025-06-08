# APS 2025.1 - Plataforma de Anúncios de Serviços Autônomos

Este projeto consiste em uma aplicação web desenvolvida em Java com Spring Boot, cujo objetivo é conectar clientes a prestadores de serviços autônomos. Clientes podem contratar serviços e prestadores podem anunciar seus serviços na plataforma.

## Funcionalidades

- Cadastro e autenticação de usuários (clientes e prestadores)
- Cadastro e gerenciamento de anúncios de serviços
- Busca e filtragem de serviços disponíveis
- Contratação de serviços por parte dos clientes
- Área do prestador para gerenciar seus anúncios
- Área do cliente para acompanhar contratações
- Sistema de categorias para organização dos serviços
- Relatórios e visualização de dados
- Interface web responsiva com Bootstrap

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.5**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Thymeleaf
- **MySQL** (conector incluído)
- **Bootstrap 5** (via WebJars)
- **jQuery** (via WebJars)
- **Maven** (gerenciamento de dependências)

## Estrutura do Projeto

- `src/main/java/br/edu/br/meuprimeirospringboot/`
  - `controllers/` – Controladores das rotas web (ex: AnuncioController, UsuarioController)
  - `entity/` – Entidades do domínio (ex: Usuario, Anuncio, Servico, Aluno)
  - `repository/` – Interfaces de acesso a dados (JPA)
  - `service/` e `serviceImpl/` – Lógica de negócio e serviços
  - `config/` – Configurações de segurança e aplicação
- `src/main/resources/` – Templates Thymeleaf, arquivos de configuração, etc.

## Como Executar

1. **Pré-requisitos:**
   - Java 17 instalado
   - MySQL em execução
   - Maven instalado

2. **Configuração do Banco de Dados:**
   - Configure as credenciais do banco de dados em `src/main/resources/application.properties`.

3. **Build e execução:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Ou, no Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

4. **Acesse a aplicação:**
   - Abra o navegador e acesse: `http://localhost:8080`

## Contribuição

Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença

Este projeto é apenas para fins acadêmicos.

