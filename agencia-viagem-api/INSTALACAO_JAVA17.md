# 🔧 Guia de Instalação e Configuração do Projeto

## ✅ Status Atual (12/11/2025)

### Java - INSTALADO ✅
```
openjdk version "25.0.1" 2025-10-21 LTS
OpenJDK Runtime Environment Temurin-25.0.1+8
```
**Status:** Java 25 está instalado e é compatível com o projeto!

### Maven - PENDENTE ⚠️
**Status:** Não instalado. Necessário para compilar e executar o projeto.

---

## 📦 Próximos Passos

### 1. Instalar Maven (NECESSÁRIO)

Maven não está instalado. Você precisa dele para compilar e executar o projeto.

#### **Opção A: PowerShell como Administrador**
```powershell
# Abra PowerShell como Admin e execute:
choco install maven -y
```

#### **Opção B: Download Manual**
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe "Binary zip archive" (apache-maven-3.9.x-bin.zip)
3. Extraia para `C:\Program Files\Apache\maven`
4. Adicione ao PATH:
   - Variável: `MAVEN_HOME` → `C:\Program Files\Apache\maven`
   - PATH: Adicione `%MAVEN_HOME%\bin`

#### **Opção C: WinGet (Windows 11)**
```powershell
winget install --id=Apache.Maven -e
```

### 2. Verificar Instalação do Maven
```powershell
# Feche e reabra o terminal, então:
mvn -version
```

Deve mostrar algo como:
```
Apache Maven 3.9.x
Maven home: C:\Program Files\Apache\maven
Java version: 25.0.1, vendor: Eclipse Adoptium
```

### 3. Compilar o Projeto
```powershell
cd agencia-viagem-api
mvn clean compile
```

### 4. Executar a Aplicação
```powershell
mvn spring-boot:run
```

---

## 🔍 Solução de Problemas Lombok

### Problema Atual: Lombok não funciona no VS Code

Os erros que você está vendo:
```
cannot find symbol: method getNome()
cannot find symbol: method getPreco()
```

São causados pelo Lombok não estar processando as anotações corretamente.

### Soluções:

#### **Solução 1: Recarregar VS Code (Mais Simples)**
1. Pressione `Ctrl+Shift+P`
2. Digite "Developer: Reload Window"
3. Pressione Enter
4. Aguarde o Java Language Server reiniciar

#### **Solução 2: Limpar Cache do Java Language Server**
1. Pressione `Ctrl+Shift+P`
2. Digite "Java: Clean Java Language Server Workspace"
3. Selecione "Restart and delete"
4. Aguarde reiniciar

#### **Solução 3: Configurar Java Home no VS Code**
1. Pressione `Ctrl+,` (Configurações)
2. Procure por "java.jdt.ls.java.home"
3. Configure o caminho do Java 25:
   - Exemplo: `C:\Program Files\Eclipse Adoptium\jdk-25.0.1+8`

#### **Solução 4: Instalar Extensão Lombok**
1. Vá em Extensions (`Ctrl+Shift+X`)
2. Procure "Lombok Annotations Support for VS Code"
3. Instale: `gabrielbb.vscode-lombok`
4. Recarregue o VS Code

---

## ✅ Checklist Completo

- [x] Java 25 instalado
- [ ] Maven instalado
- [ ] Variáveis de ambiente configuradas (JAVA_HOME, MAVEN_HOME)
- [ ] VS Code com extensões Java instaladas
- [ ] Extensão Lombok instalada
- [ ] Projeto compila sem erros (`mvn clean compile`)
- [ ] Aplicação executa (`mvn spring-boot:run`)

---

### **Opção 1: Via Chocolatey (Recomendado - Requer Admin)**

1. **Abra o PowerShell como Administrador**
   - Clique com botão direito no menu Iniciar
   - Selecione "Windows PowerShell (Admin)" ou "Terminal (Admin)"

2. **Execute o comando:**
   ```powershell
   choco install openjdk17 -y
   ```

3. **Aguarde a instalação concluir**

4. **Feche e reabra o VS Code**

---

### **Opção 2: Download Manual (Mais Simples)**

#### **Passo 1: Baixar o Java 17**

Escolha uma das opções:

**A) Eclipse Temurin (Recomendado)**
- Acesse: https://adoptium.net/
- Clique em "Temurin 17 (LTS)"
- Escolha o instalador Windows (.msi)
- Baixe e execute

**B) Oracle JDK 17**
- Acesse: https://www.oracle.com/java/technologies/downloads/#java17
- Escolha "Windows x64 Installer"
- Baixe e execute

**C) Microsoft Build of OpenJDK**
- Acesse: https://learn.microsoft.com/en-us/java/openjdk/download
- Escolha "JDK 17" para Windows
- Baixe e execute

#### **Passo 2: Instalar**

1. Execute o instalador baixado
2. Siga as instruções padrão (Next → Next → Install)
3. **IMPORTANTE**: Marque a opção "Set JAVA_HOME" se disponível

#### **Passo 3: Configurar JAVA_HOME (se necessário)**

1. Abra o PowerShell e execute:
   ```powershell
   [System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-17.0.XX-hotspot', 'Machine')
   ```
   *(Ajuste o caminho conforme sua instalação)*

2. Ou configure manualmente:
   - Pressione `Win + Pause` ou vá em Configurações do Sistema
   - Clique em "Configurações avançadas do sistema"
   - Clique em "Variáveis de Ambiente"
   - Em "Variáveis do sistema", clique em "Novo"
   - Nome: `JAVA_HOME`
   - Valor: Caminho da instalação do Java 17 (ex: `C:\Program Files\Eclipse Adoptium\jdk-17.0.XX-hotspot`)

#### **Passo 4: Atualizar PATH**

1. Nas "Variáveis de Ambiente", encontre a variável `Path` em "Variáveis do sistema"
2. Clique em "Editar"
3. Adicione ou mova para o topo: `%JAVA_HOME%\bin`

---

## 🔍 Verificar Instalação

Abra um **novo** PowerShell e execute:

```powershell
java -version
```

Deve mostrar algo como:
```
openjdk version "17.0.XX" 2024-XX-XX
OpenJDK Runtime Environment Temurin-17.0.XX+X (build 17.0.XX+X)
OpenJDK 64-Bit Server VM Temurin-17.0.XX+X (build 17.0.XX+X, mixed mode, sharing)
```

---

## 🛠️ Corrigir Configuração do VS Code

### Passo 1: Desinstalar Extensão Oracle Java (Conflitante)

1. No VS Code, vá em Extensions (Ctrl+Shift+X)
2. Procure por "Oracle Java"
3. Se encontrar, clique em "Uninstall"

### Passo 2: Instalar Extensões Corretas

Instale essas extensões essenciais:

1. **Extension Pack for Java** (vscjava.vscode-java-pack)
   - Publisher: Microsoft
   - Inclui tudo necessário para Java

2. **Spring Boot Extension Pack** (vmware.vscode-boot-dev-pack)
   - Publisher: VMware
   - Para desenvolvimento Spring Boot

### Passo 3: Configurar VS Code para usar Java 17

1. Pressione `Ctrl+Shift+P`
2. Digite "Preferences: Open User Settings (JSON)"
3. Adicione ou modifique:

```json
{
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-17",
      "path": "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.XX-hotspot",
      "default": true
    }
  ],
  "java.home": "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.XX-hotspot"
}
```

**IMPORTANTE**: Ajuste o caminho `path` para onde você instalou o Java 17!

Caminhos comuns:
- Eclipse Temurin: `C:\Program Files\Eclipse Adoptium\jdk-17.0.XX-hotspot`
- Oracle JDK: `C:\Program Files\Java\jdk-17`
- Microsoft OpenJDK: `C:\Program Files\Microsoft\jdk-17.0.XX-hotspot`

### Passo 4: Recarregar o VS Code

1. Pressione `Ctrl+Shift+P`
2. Digite "Developer: Reload Window"
3. Pressione Enter

---

## 🧪 Testar o Projeto

Após instalar o Java 17 e configurar o VS Code:

```powershell
# Navegar para o projeto
cd "C:\Users\lipef\OneDrive\Desktop\Desafio-2-Desenvolvimento-sistemas-Web\agencia-viagem-api"

# Limpar e compilar
mvn clean compile

# Executar a aplicação
mvn spring-boot:run
```

Se tudo estiver correto, você verá:
```
Started AgenciaViagemApplication in X.XXX seconds
```

E a aplicação estará disponível em: http://localhost:8080

---

## ❓ Problemas Comuns

### "java -version" ainda mostra Java 8

**Solução**: Feche TODOS os terminais e VS Code, abra novamente. As variáveis de ambiente são carregadas ao iniciar o terminal.

### Múltiplas versões do Java instaladas

**Solução**: Use `JAVA_HOME` para apontar para a versão correta (Java 17).

### Maven não encontra Java 17

**Solução**: Configure `JAVA_HOME` corretamente e reinicie o terminal.

---

## 📞 Precisa de Ajuda?

Após instalar o Java 17:
1. Reinicie o VS Code completamente
2. Execute: `java -version`
3. Me envie a saída do comando

---

**Data**: 12 de Novembro de 2025  
**Projeto**: Agência de Viagem API  
**Java Requerido**: 17+  
**Java Atual**: 8 (INCOMPATÍVEL)
