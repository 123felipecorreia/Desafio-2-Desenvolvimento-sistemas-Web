# 🚀 Guia Rápido de Instalação - Maven

## ✅ Status Atual

- ✅ **Java 25** instalado e funcionando
- ⚠️ **Maven** NÃO instalado (necessário para o projeto)

---

## 📦 Como Instalar o Maven

### **Método 1: PowerShell como Administrador (Mais Rápido)**

1. **Feche o VS Code**

2. **Abra PowerShell como Administrador:**
   - Pressione `Win + X`
   - Selecione "Windows PowerShell (Admin)" ou "Terminal (Admin)"

3. **Execute:**
   ```powershell
   choco install maven -y
   ```

4. **Aguarde a instalação concluir**

5. **Feche todos os terminais**

6. **Abra um novo PowerShell normal e verifique:**
   ```powershell
   mvn -version
   ```

---

### **Método 2: Download Manual (Se não tiver permissões de Admin)**

#### Passo 1: Download
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe: **apache-maven-3.9.11-bin.zip** (Binary zip archive)
3. Salve na pasta Downloads

#### Passo 2: Extrair
1. Extraia o arquivo ZIP
2. Mova a pasta extraída para: `C:\Program Files\Apache\maven`
   - Se não tiver permissão, use: `C:\Users\lipef\maven`

#### Passo 3: Configurar Variáveis de Ambiente

**Via PowerShell (Usuário atual):**
```powershell
# Definir MAVEN_HOME
[System.Environment]::SetEnvironmentVariable('MAVEN_HOME', 'C:\Program Files\Apache\maven', 'User')

# Obter PATH atual
$currentPath = [System.Environment]::GetEnvironmentVariable('Path', 'User')

# Adicionar Maven ao PATH
$newPath = "$currentPath;%MAVEN_HOME%\bin"
[System.Environment]::SetEnvironmentVariable('Path', $newPath, 'User')
```

**Via Interface Gráfica:**
1. Pressione `Win + Pause` (ou vá em Configurações do Sistema)
2. Clique em "Configurações avançadas do sistema"
3. Clique em "Variáveis de Ambiente"
4. Em "Variáveis do usuário":
   - **Criar nova variável:**
     - Nome: `MAVEN_HOME`
     - Valor: `C:\Program Files\Apache\maven` (ou onde você extraiu)
   - **Editar PATH:**
     - Adicione: `%MAVEN_HOME%\bin`
5. Clique OK em todas as janelas

#### Passo 4: Verificar
1. **Feche TODOS os terminais e VS Code**
2. Abra um novo PowerShell
3. Execute:
   ```powershell
   mvn -version
   ```

Deve mostrar:
```
Apache Maven 3.9.11
Maven home: C:\Program Files\Apache\maven
Java version: 25.0.1, vendor: Eclipse Adoptium
```

---

## 🔧 Após Instalar o Maven

### 1. Navegar até o projeto
```powershell
cd "C:\Users\lipef\OneDrive\Desktop\Desafio-2-Desenvolvimento-sistemas-Web\agencia-viagem-api"
```

### 2. Limpar e compilar
```powershell
mvn clean compile
```

### 3. Se compilar com sucesso, executar:
```powershell
mvn spring-boot:run
```

### 4. Acessar a aplicação
- URL: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

---

## ❓ Problemas Comuns

### Maven não é reconhecido após instalação
**Solução:** Feche TODOS os terminais e VS Code, depois abra novamente.

### Erro de permissão ao instalar via Chocolatey
**Solução:** Use o Método 2 (Download Manual) ou execute PowerShell como Admin.

### mvn -version mostra Java 8 ao invés de Java 25
**Solução:** Configure JAVA_HOME:
```powershell
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-25.0.1+8', 'User')
```

---

## 📝 Próximos Passos Após Maven Instalado

1. ✅ Instalar Maven
2. ✅ Compilar projeto: `mvn clean compile`
3. ✅ Corrigir erros do Lombok (recarregar VS Code)
4. ✅ Executar aplicação: `mvn spring-boot:run`
5. ✅ Testar endpoints da API

---

**Última atualização:** 12 de Novembro de 2025  
**Java instalado:** 25.0.1 ✅  
**Maven instalado:** Pendente ⚠️
