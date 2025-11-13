# Script para instalar Java 17 LTS (Eclipse Temurin)
# Execute como Administrador

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Instalador Java 17 LTS (Temurin)     " -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Verificar se está rodando como administrador
$isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)

if (-not $isAdmin) {
    Write-Host "⚠️  ERRO: Este script precisa ser executado como Administrador!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Clique com botão direito no PowerShell e selecione 'Executar como Administrador'" -ForegroundColor Yellow
    Write-Host ""
    pause
    exit 1
}

Write-Host "✅ Executando como Administrador" -ForegroundColor Green
Write-Host ""

# Verificar se Chocolatey está instalado
$chocoInstalled = Get-Command choco -ErrorAction SilentlyContinue

if (-not $chocoInstalled) {
    Write-Host "❌ Chocolatey não está instalado!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Instalando Chocolatey..." -ForegroundColor Yellow
    
    Set-ExecutionPolicy Bypass -Scope Process -Force
    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
    Invoke-Expression ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
    
    # Recarregar PATH
    $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    
    Write-Host "✅ Chocolatey instalado com sucesso!" -ForegroundColor Green
    Write-Host ""
}

Write-Host "📦 Instalando Java 17 LTS (Eclipse Temurin)..." -ForegroundColor Cyan
Write-Host ""

# Instalar Java 17
choco install temurin17 -y

Write-Host ""
Write-Host "✅ Java 17 instalado com sucesso!" -ForegroundColor Green
Write-Host ""

# Configurar JAVA_HOME para Java 17
$java17Path = "C:\Program Files\Eclipse Adoptium\jdk-17.0.13.11-hotspot"

if (Test-Path $java17Path) {
    Write-Host "🔧 Configurando JAVA_HOME para Java 17..." -ForegroundColor Cyan
    
    # Configurar variável de ambiente JAVA_HOME
    [System.Environment]::SetEnvironmentVariable("JAVA_HOME", $java17Path, "Machine")
    
    # Atualizar PATH
    $machinePath = [System.Environment]::GetEnvironmentVariable("Path", "Machine")
    
    # Remover qualquer Java 25 do PATH
    $machinePath = $machinePath -replace 'C:\\Program Files\\Eclipse Adoptium\\jdk-25[^;]*;?', ''
    
    # Adicionar Java 17 no início do PATH
    if ($machinePath -notlike "*$java17Path\bin*") {
        $machinePath = "$java17Path\bin;" + $machinePath
    }
    
    [System.Environment]::SetEnvironmentVariable("Path", $machinePath, "Machine")
    
    # Recarregar PATH na sessão atual
    $env:JAVA_HOME = $java17Path
    $env:Path = "$java17Path\bin;" + $env:Path
    
    Write-Host "✅ JAVA_HOME configurado: $java17Path" -ForegroundColor Green
    Write-Host ""
}

# Verificar a instalação
Write-Host "🔍 Verificando instalação do Java..." -ForegroundColor Cyan
java -version

Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "  ✅ Java 17 instalado com sucesso!    " -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""
Write-Host "⚠️  IMPORTANTE: Feche e reabra o VS Code para que as mudanças tenham efeito!" -ForegroundColor Yellow
Write-Host ""
Write-Host "Pressione qualquer tecla para continuar..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
