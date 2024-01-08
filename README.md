# Botão de Ação Sankhya para Atualização de Registros via Requisição à API "Rick and Morty"

## Descrição
Este projeto consiste em um botão de ação integrado ao sistema ERP Sankhya, que utiliza a API pública "Rick and Morty" para obter informações sobre um personagem com base no nome fornecido. Através dessa requisição, os dados obtidos são processados e utilizados para realizar a atualização de registros em uma tabela específica no banco de dados do ERP Sankhya.

## Detalhes Técnicos
- **Linguagem:** Java
- **Framework Sankhya:** Utiliza as extensões de botões de ação fornecidas pelo ERP Sankhya.
- **API Utilizada:** [Rick and Morty API](https://rickandmortyapi.com/documentation)

## Como Funciona
1. O botão de ação é acionado no sistema ERP Sankhya, enviando o nome do personagem como parâmetro.
2. Com base no nome fornecido, uma requisição à API "Rick and Morty" é feita para obter dados do personagem.
3. Os dados são processados para extrair a URL da imagem do personagem.
4. As informações da imagem são utilizadas para atualizar registros em uma tabela específica no banco de dados do ERP Sankhya.

## Configuração
1. Configure o botão de ação no ERP Sankhya para chamar a classe `Requisicao`.
2. Certifique-se de que as dependências necessárias estejam configuradas corretamente.
3. Consulte a [documentação do ERP Sankhya](https://developer.sankhya.com.br) para obter informações adicionais sobre integração.

## Segurança
- O código foi revisado para garantir que não contenha credenciais ou informações sensíveis.
- Certifique-se de seguir as melhores práticas de segurança ao configurar o acesso à API "Rick and Morty" e ao banco de dados do ERP Sankhya.

## Observações
- Este projeto é fornecido como exemplo e deve ser adaptado para atender às necessidades específicas do seu ambiente.
- Certifique-se de substituir os valores fictícios por chamadas reais à API e ajuste conforme necessário.

## Licença
Este projeto está disponibilizado sob a [sua escolha de licença de código aberto](https://choosealicense.com/).

## Referências
- [Documentação da API "Rick and Morty"](https://rickandmortyapi.com/documentation)
- [Documentação do ERP Sankhya](https://developer.sankhya.com.br)

