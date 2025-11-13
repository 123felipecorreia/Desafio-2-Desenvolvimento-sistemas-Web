# 📋 Requirements - Agência de Viagem API

Este documento lista todos os requisitos, dependências e extensões necessárias para o projeto.

---

## 🔧 Requisitos de Sistema

### Software Necessário

| Software | Versão Mínima | Versão Recomendada | Obrigatório |
|----------|---------------|-------------------|-------------|
| **Java JDK** | 17 | 17+ | ✅ Sim |
| **Apache Maven** | 3.6.3 | 3.9.0+ | ✅ Sim |
| **IDE** | - | IntelliJ IDEA / Eclipse / VS Code | ⚠️ Recomendado |
| **Git** | 2.0+ | Última versão | ⚠️ Recomendado |

---

## 📦 Dependências Maven (pom.xml)

### Spring Boot Parent
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.0</version>
</parent>
```

### Dependências Principais

#### 1. **Spring Boot Starter Web**
- **GroupId:** `org.springframework.boot`
- **ArtifactId:** `spring-boot-starter-web`
- **Versão:** Herdada do parent (3.2.0)
- **Escopo:** Compile (padrão)
- **Descrição:** Framework para criação de APIs REST
- **Inclui:**
  - Spring MVC
  - Tomcat (servidor embutido)
  - Jackson (serialização JSON)
  - Validação de entrada

**Uso no projeto:**
- Criação de controllers REST
- Mapeamento de endpoints HTTP
- Serialização/deserialização JSON automática

---

#### 2. **Spring Boot Starter Data JPA**
- **GroupId:** `org.springframework.boot`
- **ArtifactId:** `spring-boot-starter-data-jpa`
- **Versão:** Herdada do parent (3.2.0)
- **Escopo:** Compile
- **Descrição:** Persistência de dados com JPA/Hibernate
- **Inclui:**
  - Hibernate ORM
  - Spring Data JPA
  - Jakarta Persistence API

**Uso no projeto:**
- Mapeamento objeto-relacional (ORM)
- Repositories com queries automáticas
- Transações de banco de dados

---

#### 3. **H2 Database**
- **GroupId:** `com.h2database`
- **ArtifactId:** `h2`
- **Versão:** Gerenciada pelo Spring Boot
- **Escopo:** Runtime
- **Descrição:** Banco de dados relacional em memória
- **Características:**
  - Leve e rápido
  - Console web integrado
  - Ideal para desenvolvimento e testes

**Uso no projeto:**
- Armazenamento temporário de dados
- Desenvolvimento sem necessidade de banco externo
- Console acessível em `/h2-console`

---

#### 4. **Spring Boot Starter Validation**
- **GroupId:** `org.springframework.boot`
- **ArtifactId:** `spring-boot-starter-validation`
- **Versão:** Herdada do parent (3.2.0)
- **Escopo:** Compile
- **Descrição:** Validação de dados com Bean Validation
- **Inclui:**
  - Hibernate Validator
  - Jakarta Validation API

**Uso no projeto:**
- Validação de DTOs (`@Valid`)
- Anotações: `@NotBlank`, `@NotNull`, `@Positive`, `@Min`, `@Max`
- Mensagens de erro automáticas

---

#### 5. **Lombok**
- **GroupId:** `org.projectlombok`
- **ArtifactId:** `lombok`
- **Versão:** Gerenciada pelo Spring Boot
- **Escopo:** Compile
- **Opcional:** Sim
- **Descrição:** Reduz código boilerplate
- **Anotações usadas:**
  - `@Data` - Gera getters, setters, toString, equals, hashCode
  - `@NoArgsConstructor` - Construtor sem argumentos
  - `@AllArgsConstructor` - Construtor com todos argumentos

**Uso no projeto:**
- Entidades (Model)
- DTOs
- Redução significativa de código repetitivo

**⚠️ Importante:** Requer plugin do Lombok na IDE

---

#### 6. **Spring Boot DevTools**
- **GroupId:** `org.springframework.boot`
- **ArtifactId:** `spring-boot-devtools`
- **Versão:** Herdada do parent (3.2.0)
- **Escopo:** Runtime
- **Opcional:** Sim
- **Descrição:** Ferramentas de desenvolvimento
- **Funcionalidades:**
  - Hot reload automático
  - Live reload do navegador
  - Configurações otimizadas para desenvolvimento

**Uso no projeto:**
- Reinicialização automática ao salvar arquivos
- Acelera o desenvolvimento

---

#### 7. **Spring Boot Starter Test**
- **GroupId:** `org.springframework.boot`
- **ArtifactId:** `spring-boot-starter-test`
- **Versão:** Herdada do parent (3.2.0)
- **Escopo:** Test
- **Descrição:** Framework completo de testes
- **Inclui:**
  - JUnit 5 (Jupiter)
  - Mockito
  - AssertJ
  - Hamcrest
  - Spring Test

**Uso no projeto:**
- Testes unitários
- Testes de integração
- Mocks de serviços

---

## 🔌 Extensões VS Code Recomendadas

### Essenciais para Java

#### 1. **Extension Pack for Java**
- **ID:** `vscjava.vscode-java-pack`
- **Publisher:** Microsoft
- **Descrição:** Pacote completo para desenvolvimento Java
- **Inclui:**
  - Language Support for Java (Red Hat)
  - Debugger for Java
  - Test Runner for Java
  - Maven for Java
  - Project Manager for Java
  - Visual Studio IntelliCode

---

#### 2. **Spring Boot Extension Pack**
- **ID:** `pivotal.vscode-boot-dev-pack`
- **Publisher:** VMware (Pivotal)
- **Descrição:** Ferramentas para Spring Boot
- **Inclui:**
  - Spring Boot Tools
  - Spring Initializr Java Support
  - Spring Boot Dashboard

**Funcionalidades:**
- Autocomplete para `application.properties`
- Navegação rápida entre beans
- Execução e debug de aplicações Spring

---

#### 3. **Lombok Annotations Support**
- **ID:** `gabrielbb.vscode-lombok`
- **Publisher:** Gabriel Basilio Brito
- **Descrição:** Suporte para anotações Lombok
- **Necessário para:** Reconhecer getters/setters gerados

---

### Recomendadas para Produtividade

#### 4. **REST Client**
- **ID:** `humao.rest-client`
- **Publisher:** Huachao Mao
- **Descrição:** Cliente HTTP dentro do VS Code
- **Uso:** Testar endpoints sem ferramentas externas

---

#### 5. **Thunder Client**
- **ID:** `rangav.vscode-thunder-client`
- **Publisher:** Ranga Vadhineni
- **Descrição:** Cliente REST completo (alternativa ao Postman)
- **Uso:** Testar e documentar APIs

---

#### 6. **Git Graph**
- **ID:** `mhutchie.git-graph`
- **Publisher:** mhutchie
- **Descrição:** Visualizador de histórico Git
- **Uso:** Gerenciar commits e branches

---

#### 7. **GitLens**
- **ID:** `eamodio.gitlens`
- **Publisher:** GitKraken
- **Descrição:** Recursos avançados de Git
- **Uso:** Blame, histórico de arquivos, comparações

---

#### 8. **SonarLint**
- **ID:** `sonarsource.sonarlint-vscode`
- **Publisher:** SonarSource
- **Descrição:** Análise de código estática
- **Uso:** Detectar bugs e code smells

---

#### 9. **Error Lens**
- **ID:** `usernamehw.errorlens`
- **Publisher:** Alexander
- **Descrição:** Destaca erros inline
- **Uso:** Visualizar erros diretamente no código

---

#### 10. **Better Comments**
- **ID:** `aaron-bond.better-comments`
- **Publisher:** Aaron Bond
- **Descrição:** Comentários coloridos
- **Uso:** Organizar comentários (TODO, FIXME, etc.)

---

## 🛠️ Ferramentas Externas Recomendadas

### Para Testes de API

| Ferramenta | Tipo | Descrição |
|------------|------|-----------|
| **Postman** | Desktop/Web | Cliente REST completo com collections |
| **Insomnia** | Desktop | Cliente REST minimalista |
| **cURL** | CLI | Testes via linha de comando |

### Para Banco de Dados

| Ferramenta | Tipo | Descrição |
|------------|------|-----------|
| **H2 Console** | Web (Integrado) | Console do próprio H2 em `/h2-console` |
| **DBeaver** | Desktop | Cliente universal de banco de dados |
| **MySQL Workbench** | Desktop | Se migrar para MySQL |

---

## 📝 Configuração do Ambiente

### 1. Instalar Java 17

#### Windows (com Chocolatey)
```powershell
choco install openjdk17
```

#### Windows (Manual)
- Baixar do [Oracle](https://www.oracle.com/java/technologies/downloads/) ou [Adoptium](https://adoptium.net/)
- Configurar `JAVA_HOME` nas variáveis de ambiente

#### Verificar Instalação
```bash
java -version
```

### 2. Instalar Maven

#### Windows (com Chocolatey)
```powershell
choco install maven
```

#### Verificar Instalação
```bash
mvn -version
```

### 3. Configurar VS Code

#### Instalar Extensões Essenciais
```bash
code --install-extension vscjava.vscode-java-pack
code --install-extension pivotal.vscode-boot-dev-pack
code --install-extension gabrielbb.vscode-lombok
```

#### Configurar settings.json (opcional)
```json
{
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-17",
      "path": "C:\\Program Files\\Java\\jdk-17",
      "default": true
    }
  ],
  "spring-boot.ls.java.home": "C:\\Program Files\\Java\\jdk-17"
}
```

---

## 🚀 Comandos Maven Úteis

### Compilar o Projeto
```bash
mvn clean compile
```

### Executar a Aplicação
```bash
mvn spring-boot:run
```

### Executar Testes
```bash
mvn test
```

### Gerar JAR
```bash
mvn clean package
```

### Pular Testes ao Compilar
```bash
mvn clean package -DskipTests
```

### Limpar Build
```bash
mvn clean
```

### Verificar Dependências
```bash
mvn dependency:tree
```

### Atualizar Dependências
```bash
mvn versions:display-dependency-updates
```

---

## 📊 Versões das Dependências (Reference)

| Dependência | Versão | Gerenciada Por |
|-------------|--------|----------------|
| Spring Boot | 3.2.0 | Parent POM |
| Java | 17 | Configuração do projeto |
| Hibernate | 6.3.x | Spring Boot |
| Jackson | 2.15.x | Spring Boot |
| H2 Database | 2.2.x | Spring Boot |
| Lombok | 1.18.x | Spring Boot |
| JUnit | 5.10.x | Spring Boot |
| Mockito | 5.5.x | Spring Boot |

---

## 🔍 Verificação de Instalação

### Script de Verificação Completa

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar Git
git --version

# Compilar o projeto
cd agencia-viagem-api
mvn clean compile

# Executar a aplicação
mvn spring-boot:run
```

### Checklist de Instalação

- [ ] Java 17+ instalado
- [ ] Maven 3.6.3+ instalado
- [ ] Git instalado
- [ ] VS Code instalado
- [ ] Extension Pack for Java instalado
- [ ] Spring Boot Extension Pack instalado
- [ ] Lombok Support instalado
- [ ] Projeto compila sem erros (`mvn clean compile`)
- [ ] Aplicação inicia corretamente (`mvn spring-boot:run`)
- [ ] H2 Console acessível em `http://localhost:8080/h2-console`

---

## 🆘 Problemas Comuns

### Erro: JAVA_HOME não configurado
**Solução:** Adicionar `JAVA_HOME` às variáveis de ambiente apontando para o JDK 17

### Erro: Lombok não funciona no VS Code
**Solução:** Instalar extensão `gabrielbb.vscode-lombok` e recarregar o VS Code

### Erro: Porta 8080 já está em uso
**Solução:** Alterar porta em `application.properties`:
```properties
server.port=8081
```

### Erro: Maven não encontrado
**Solução:** Adicionar Maven ao PATH do sistema

---

## 📚 Documentação Oficial

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Project Lombok](https://projectlombok.org/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Jakarta Validation](https://jakarta.ee/specifications/bean-validation/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

**Última Atualização:** 12 de Novembro de 2025  
**Versão do Projeto:** 0.0.1-SNAPSHOT  
**Spring Boot:** 3.2.0  
**Java:** 17
