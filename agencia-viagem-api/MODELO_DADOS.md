# 📊 Modelo de Dados - Classe Destino

## Estrutura da Entidade

A classe `Destino` representa um destino turístico no sistema da agência de viagens.

### Atributos

| Atributo | Tipo | Descrição | Obrigatório | Validação |
|----------|------|-----------|-------------|-----------|
| **id** | `Long` | Identificador único do destino (chave primária) | Sim (auto-gerado) | - |
| **nome** | `String` | Nome do destino turístico | Sim | Não pode ser vazio |
| **localizacao** | `String` | Cidade, país ou região do destino | Sim | Não pode ser vazio |
| **descricao** | `String` (TEXT) | Descrição detalhada do destino | Não | - |
| **preco** | `BigDecimal` | Preço médio do pacote turístico | Sim | Deve ser positivo |
| **atracoesTuristicas** | `String` (TEXT) | Lista de atrações turísticas do destino | Não | - |
| **avaliacaoMedia** | `BigDecimal` | Média das avaliações dos usuários (0-5) | Não | Calculado automaticamente |
| **totalAvaliacoes** | `Integer` | Quantidade total de avaliações recebidas | Não | Iniciado em 0 |
| **dataCriacao** | `LocalDateTime` | Data e hora de criação do registro | Sim (auto-gerado) | Não pode ser alterado |
| **dataAtualizacao** | `LocalDateTime` | Data e hora da última atualização | Sim (auto-gerado) | Atualizado automaticamente |

## Detalhamento dos Atributos

### 1. **Identificador Único (id)**
- Gerado automaticamente pelo banco de dados
- Tipo: `Long` (números inteiros grandes)
- Estratégia: `IDENTITY` (auto-incremento)
- Exemplo: `1, 2, 3, ...`

### 2. **Nome do Destino (nome)**
- Identificação principal do destino
- Campo obrigatório
- Armazenado como VARCHAR no banco
- Exemplos: 
  - "Rio de Janeiro"
  - "Paris"
  - "Machu Picchu"

### 3. **Localização (localizacao)**
- Informação geográfica completa
- Pode incluir cidade, estado, país
- Campo obrigatório
- Exemplos:
  - "Rio de Janeiro, Brasil"
  - "Paris, França"
  - "Cusco, Peru"

### 4. **Descrição (descricao)**
- Texto detalhado sobre o destino
- Armazenado como TEXT (sem limite de caracteres)
- Campo opcional
- Exemplo: 
  - "Cidade Maravilhosa conhecida por suas praias deslumbrantes, Cristo Redentor e vida noturna vibrante."

### 5. **Preço Médio (preco)**
- Valor médio do pacote turístico
- Tipo: `BigDecimal` (precisão para valores monetários)
- Precisão: 10 dígitos, 2 casas decimais
- Campo obrigatório
- Validação: Deve ser um valor positivo
- Exemplos: `1500.00`, `3250.50`, `899.99`

### 6. **Atrações Turísticas (atracoesTuristicas)**
- Lista ou descrição das principais atrações
- Armazenado como TEXT
- Campo opcional
- Pode ser separado por vírgula ou ponto e vírgula
- Exemplos:
  - "Cristo Redentor, Pão de Açúcar, Copacabana, Ipanema"
  - "Torre Eiffel, Louvre, Arco do Triunfo"
  - "Ruínas de Machu Picchu, Valle Sagrado, Cusco Colonial"

### 7. **Avaliação Média (avaliacaoMedia)**
- Média ponderada das notas dos usuários
- Tipo: `BigDecimal` (precisão: 3 dígitos, 2 decimais)
- Escala: 1.00 a 5.00
- Calculado automaticamente pelo sistema
- Inicialmente `null` (sem avaliações)
- Exemplos: `4.50`, `3.75`, `5.00`

### 8. **Total de Avaliações (totalAvaliacoes)**
- Contador de quantas avaliações o destino recebeu
- Tipo: `Integer`
- Valor inicial: `0`
- Incrementado a cada nova avaliação
- Usado para calcular a média ponderada

### 9. **Data de Criação (dataCriacao)**
- Timestamp de quando o destino foi cadastrado
- Tipo: `LocalDateTime`
- Gerado automaticamente no momento da criação
- Não pode ser alterado posteriormente (`updatable = false`)
- Formato: `yyyy-MM-ddTHH:mm:ss`

### 10. **Data de Atualização (dataAtualizacao)**
- Timestamp da última modificação
- Tipo: `LocalDateTime`
- Atualizado automaticamente em qualquer edição
- Formato: `yyyy-MM-ddTHH:mm:ss`

## Ciclo de Vida da Entidade

### Criação (@PrePersist)
Quando um novo destino é cadastrado:
- `dataCriacao` é definida automaticamente
- `dataAtualizacao` é definida automaticamente
- `totalAvaliacoes` é iniciado em `0`
- `avaliacaoMedia` permanece `null`

### Atualização (@PreUpdate)
Quando um destino é modificado:
- `dataAtualizacao` é atualizada automaticamente
- `dataCriacao` permanece inalterada

### Avaliação
Quando um usuário avalia um destino:
- `totalAvaliacoes` é incrementado
- `avaliacaoMedia` é recalculada usando média ponderada

**Fórmula da Avaliação:**
```
novaMedia = (avaliacaoMedia × totalAvaliacoes + novaNota) / (totalAvaliacoes + 1)
```

## Exemplo de Objeto Completo

```json
{
  "id": 1,
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Conhecida como Cidade Maravilhosa, o Rio de Janeiro encanta com suas praias paradisíacas, montanhas exuberantes e cultura vibrante. O Cristo Redentor observa a cidade do alto do Corcovado, enquanto as praias de Copacabana e Ipanema atraem turistas do mundo inteiro.",
  "preco": 1500.00,
  "atracoesTuristicas": "Cristo Redentor, Pão de Açúcar, Copacabana, Ipanema, Jardim Botânico, Lapa, Maracanã, Sambódromo",
  "avaliacaoMedia": 4.75,
  "totalAvaliacoes": 127,
  "dataCriacao": "2025-11-01T10:30:00",
  "dataAtualizacao": "2025-11-09T14:45:30"
}
```

## Validações Implementadas

### Anotações JPA/Hibernate
- `@Entity` - Define como entidade JPA
- `@Table(name = "destinos")` - Nome da tabela no banco
- `@Id` - Chave primária
- `@GeneratedValue` - Geração automática do ID

### Anotações de Validação (Bean Validation)
- `@NotBlank` - Campo não pode ser vazio (nome, localização)
- `@NotNull` - Campo não pode ser nulo (preço)
- `@Positive` - Valor deve ser positivo (preço)
- `@Min` / `@Max` - Limites de valor (avaliações: 1-5)

### Anotações Lombok
- `@Data` - Gera getters, setters, toString, equals, hashCode
- `@NoArgsConstructor` - Construtor sem argumentos
- `@AllArgsConstructor` - Construtor com todos os argumentos

## Relacionamentos Futuros (Possíveis Expansões)

O modelo pode ser expandido para incluir:

1. **Fotos do Destino** (relacionamento One-to-Many)
2. **Avaliações Detalhadas** (comentários + notas)
3. **Categorias** (praia, montanha, histórico, etc.)
4. **Pacotes Turísticos** específicos
5. **Temporadas** (alta/baixa temporada com preços diferentes)
6. **Hotéis** associados ao destino
7. **Atividades** disponíveis no destino

## Banco de Dados

A entidade é mapeada para a tabela `destinos` com a seguinte estrutura:

```sql
CREATE TABLE destinos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    atracoes_turisticas TEXT,
    avaliacao_media DECIMAL(3,2),
    total_avaliacoes INT DEFAULT 0,
    data_criacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);
```

---

**Observação:** Este modelo de dados foi projetado para ser simples, mas completo o suficiente para um sistema de agência de viagens funcional. Pode ser facilmente expandido conforme as necessidades do negócio evoluem.
