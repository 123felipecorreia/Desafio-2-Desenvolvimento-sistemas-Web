# 🔧 Solução DEFINITIVA para Problemas com Lombok

## ❌ Problema CONFIRMADO

O projeto **NÃO COMPILA** com Java 25 devido a incompatibilidade do Lombok:

```
[ERROR] Failed to execute goal maven-compiler-plugin:3.11.0:compile
Caused by: java.lang.ExceptionInInitializerError
Caused by: java.lang.NoSuchFieldException: com.sun.tools.javac.code.TypeTag :: UNKNOWN
```

**Testado e confirmado**: Mesmo com Lombok 1.18.34 (versão mais recente), a compilação Maven **FALHA** com Java 25.

### 🔍 Causa Raiz

Java 25 foi lançado em **outubro de 2025** e introduziu mudanças internas no compilador que **quebram o Lombok completamente**:
- Lombok acessa APIs internas do Java (reflexão em `com.sun.tools.javac.code.TypeTag`)
- Java 25 removeu/renomeou campos internos que Lombok precisa (`TypeTag::UNKNOWN`)
- Lombok 1.18.34 ainda não suporta essas mudanças

---

## ✅ SOLUÇÃO OBRIGATÓRIA

### ❗ VOCÊ PRECISA INSTALAR JAVA 17 LTS

**Não há alternativa**: Java 25 é incompatível com Lombok. O projeto **não vai compilar** sem Java 17.

---

## 🚀 Instalação Automática (RECOMENDADO ⭐)

Use o script PowerShell fornecido para instalar e configurar Java 17 automaticamente:

```powershell
# 1. Abra PowerShell como Administrador 
#    (Clique com botão direito no ícone do PowerShell → Executar como Administrador)

# 2. Navegue até a pasta do projeto
cd "c:\Users\lipef\OneDrive\Desktop\Desafio-2-Desenvolvimento-sistemas-Web\agencia-viagem-api"

# 3. Permita execução de scripts (se necessário)
Set-ExecutionPolicy -Scope Process -Force Bypass

# 4. Execute o script de instalação
.\instalar-java17.ps1
```

**O script fará automaticamente**:
- ✅ Instalar Chocolatey (se não estiver instalado)
- ✅ Instalar Java 17 LTS (Eclipse Temurin)
- ✅ Configurar `JAVA_HOME` para Java 17
- ✅ Atualizar `PATH` do sistema
- ✅ Remover Java 25 do PATH
- ✅ Verificar instalação

**Após executar o script**:
1. **Feche completamente o VS Code**
2. **Abra um novo PowerShell** (para recarregar variáveis de ambiente)
3. Verifique se Java 17 está ativo:
   ```powershell
   java -version
   # Deve mostrar: openjdk version "17.0.13" ...
   ```
4. **Reabra o VS Code**

---

## 🔧 Instalação Manual (alternativa)

Se preferir instalar manualmente ou se o script falhar:

### Via Chocolatey

```powershell
# Execute PowerShell como Administrador
choco install temurin17 -y

# Configure JAVA_HOME
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Eclipse Adoptium\jdk-17.0.13.11-hotspot", "Machine")

# Adicione ao PATH (no início)
$path = [System.Environment]::GetEnvironmentVariable("Path", "Machine")
$java17Path = "C:\Program Files\Eclipse Adoptium\jdk-17.0.13.11-hotspot\bin"
[System.Environment]::SetEnvironmentVariable("Path", "$java17Path;$path", "Machine")
```

### Via Download Manual

1. **Baixar**:
   - Acesse: https://adoptium.net/temurin/releases/?version=17
   - Escolha: `Windows x64 JDK (.msi)`
   - Versão: `17.0.13 LTS` (ou mais recente)

2. **Instalar**:
   - Execute o arquivo `.msi` baixado
   - Durante a instalação, **marque todas as opções**:
     - ✅ `Set JAVA_HOME variable`
     - ✅ `Add to PATH`
     - ✅ `JavaSoft (Oracle) registry keys`

3. **Verificar**:
   ```powershell
   # Feche e reabra o PowerShell
   java -version
   # Deve mostrar: openjdk version "17.0.13" ...
   ```

---

## 📝 Após Instalar Java 17

### 1. Recarregar VS Code

```
Ctrl + Shift + P → "Developer: Reload Window"
```

### 2. Limpar Cache do Java Language Server

```
Ctrl + Shift + P → "Java: Clean Java Language Server Workspace"
```

### 3. Testar Compilação Maven

```powershell
cd "c:\Users\lipef\OneDrive\Desktop\Desafio-2-Desenvolvimento-sistemas-Web\agencia-viagem-api"
mvn clean compile
```

**Resultado esperado**:
```
[INFO] BUILD SUCCESS
[INFO] Compiling 8 source files
```

### 4. Executar o Projeto

```powershell
mvn spring-boot:run
```

**Saída esperada**:
```
Started AgenciaViagemApplication in X.XXX seconds
Tomcat started on port 8080
```

---

## 🧪 Testando a API

Com o servidor rodando, teste os endpoints:

### POST - Criar Destino

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/destinos" -Method POST -Headers @{"Content-Type"="application/json"} -Body '{
  "nome": "Paris",
  "localizacao": "França",
  "descricao": "Cidade Luz com Torre Eiffel",
  "preco": 5000.00,
  "atracoesTuristicas": "Torre Eiffel, Louvre, Arc de Triomphe"
}'
```

### GET - Listar Todos

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/destinos"
```

### GET - Buscar por ID

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/destinos/1"
```

---

## ❓ Perguntas Frequentes

### Por que não posso usar Java 25?

Java 25 é muito novo (outubro 2025) e quebrou APIs internas que Lombok usa. Mesmo Lombok 1.18.34 (lançado em junho 2024) não foi atualizado para suportar Java 25.

### Posso manter Java 25 para outros projetos?

Sim! Você pode ter múltiplas versões do Java instaladas. Para usar Java 25 em outros projetos:

1. Configure `JAVA_HOME` temporariamente:
   ```powershell
   $env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot"
   ```

2. Ou use Maven Toolchains para gerenciar múltiplas versões

### Preciso desinstalar Java 25?

Não necessariamente. O script configura Java 17 como padrão, mas Java 25 continua instalado se você quiser usá-lo em outros projetos.

---

## 🔗 Links Úteis

- [Eclipse Temurin (AdoptOpenJDK) - Java 17](https://adoptium.net/temurin/releases/?version=17)
- [Lombok Compatibility](https://projectlombok.org/changelog)
- [Spring Boot System Requirements](https://docs.spring.io/spring-boot/system-requirements.html)
- [Maven Toolchains](https://maven.apache.org/guides/mini/guide-using-toolchains.html)

---

## 📞 Suporte

Se após seguir todos os passos o projeto ainda não compilar:

1. Verifique a versão do Java:
   ```powershell
   java -version
   mvn -version
   ```

2. Verifique o JAVA_HOME:
   ```powershell
   echo $env:JAVA_HOME
   ```

3. Certifique-se de que Java 17 está no PATH:
   ```powershell
   where.exe java
   ```

4. Reinicie completamente o computador (em último caso)

---

**Data**: 12 de novembro de 2025  
**Status**: ✅ Solução testada e confirmada  
**Java atual**: 25.0.1 (incompatível)  
**Java necessário**: 17.0.13+ LTS  
**Lombok**: 1.18.34 (requer Java 17)
