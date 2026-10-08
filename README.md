# Sistema de Análise e Alertas para Day Trading

Um programa desktop desenvolvido em **Java com GUI (interface gráfica)** para análise técnica e geração de sinais de compra/venda em operações de day trading com ações.

## 📊 Funcionalidades

- **Interface Gráfica (GUI)**: Construída com Swing, permitindo seleção de ações e configurações de análise
- **Análise Técnica**: Cálculo de duas médias móveis (SMA 10 e SMA 40) para identificação de tendências
- **Indicadores**: Cálculo de Desvio Padrão (ATR) e volatilidade
- **Sinais de Trading**: Geração automática de sinais (Comprar / Vender / Aguardar) baseados em cruzamento de médias
- **Visualização Gráfica**: Exibição de gráficos comparativos das médias móveis em tempo real
- **Day Trading**: Análise com intervalo de 5 minutos para operações intraday

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Paradigma**: Orientação a Objetos (POO)
- **GUI**: Swing (JFrame, JPanel, JButton, etc.)
- **Gráficos**: JFreeChart
- **API Financeira**: Alpha Vantage (cotações em tempo real)
- **Padrões de Código**: Métodos genéricos, encapsulamento, tratamento de exceções

## 📋 Conceitos de POO Aplicados

- **Encapsulamento**: Separação de responsabilidades entre classes (Calc, Graph, GuiTrabFinal)
- **Herança e Polimorfismo**: Estrutura de classes para diferentes tipos de análise
- **Métodos Genéricos**: Reutilização de código para limpeza e processamento de dados
- **Exception Handling**: Tratamento de erros em requisições HTTP e processamento de dados

## 📁 Estrutura do Projeto

```
src/trabfinal/
├── GuiTrabFinal.java       # Interface principal - seleção de ativo e modo de análise
├── Calc.java               # Lógica de cálculo de médias móveis e sinais
├── Graph.java              # Janela secundária para exibição de gráficos
├── viewChart.java          # Renderização de gráficos (JFreeChart)
├── StandardDeviation.java  # Cálculo de indicadores de volatilidade
├── GenericMetods.java      # Métodos utilitários reutilizáveis
└── Temp.java              # Classe auxiliar para processamento
```

## 🚀 Como Usar

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/Felipecluiz/Crypto_Currency_Buy_and_Sell_Allert_Program_With_Gui.git
   cd Crypto_Currency_Buy_and_Sell_Allert_Program_With_Gui
   ```

2. **Compilar o projeto**:
   ```bash
   javac -cp "lib/*" src/trabfinal/*.java -d out/
   ```

3. **Executar a aplicação**:
   ```bash
   java -cp "out:lib/*" trabfinal.GuiTrabFinal
   ```

4. **Usar a interface**:
   - Selecione o modo de análise (Boiler/M.Movel)
   - Digite o símbolo do ativo (ex: PETR4, VALE3)
   - Clique em "GOL!" para iniciar a análise
   - Acompanhe o gráfico e o sinal gerado

## 📊 Estratégia de Trading

O programa utiliza **cruzamento de médias móveis** como estratégia:

- **COMPRAR**: Quando a SMA 10 cruza acima da SMA 40 (sinal de alta)
- **VENDER**: Quando a SMA 10 cruza abaixo da SMA 40 (sinal de baixa)
- **AGUARDAR**: Quando não há confirmação de cruzamento

## ⚙️ Dependências

- JDK 8+
- JFreeChart (para gráficos)
- Alpha Vantage API Key (gratuita)

## 📌 Notas Importantes

- O programa requer conexão com a internet para buscar dados de cotações
- A API Alpha Vantage possui limite de requisições (5 por minuto no plano gratuito)
- Ideal para estudar análise técnica e operações de day trading
- Este é um projeto acadêmico para fins educacionais

## 👨‍💻 Autor

Felipe Luz (Felipecluiz)

## 📄 Licença

Projeto desenvolvido como trabalho acadêmico.

---

**Aprendizados principais**: Programação orientada a objetos, manipulação de APIs HTTP, desenvolvimento de interfaces gráficas, análise de dados financeiros e algoritmos de trading.
