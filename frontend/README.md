# 🚀 Plen - Sistema de Gestão de Projetos

Frontend desenvolvido para o sistema Plen de gestão de projetos e tarefas.

## 📁 Estrutura do Projeto

```
frontend/
├── index.html              # Página principal
├── styles/
│   ├── main.css           # Estilos base e layout
│   ├── dashboard.css      # Estilos do dashboard e projetos
│   ├── components.css     # Componentes reutilizáveis
│   └── modals.css         # (incluído em components.css)
└── scripts/
    ├── config.js          # Configuração da API
    ├── auth.js            # Autenticação JWT
    ├── api.js             # Serviços de API
    ├── dashboard.js       # Lógica do dashboard
    ├── projects.js        # Gerenciamento de projetos
    ├── tasks.js           # Gerenciamento de tarefas
    ├── teams.js           # Gerenciamento de equipe
    └── main.js            # Lógica principal e navegação
```

## ✨ Funcionalidades

### ✅ Implementadas

- **Autenticação JWT**
  - Login de usuários
  - Registro de administradores
  - Gerenciamento de token
  - Logout

- **Dashboard**
  - Cards de estatísticas (projetos, tarefas, equipe, horas)
  - Gráficos de tarefas por status
  - Gráfico de evolução de projetos
  - Lista de projetos recentes

- **Gestão de Projetos**
  - Listagem em grade
  - Filtros por status e busca
  - Criação de novos projetos
  - Visualização de detalhes

- **Gestão de Tarefas**
  - Quadro Kanban (Pendente, Em Andamento, Concluído)
  - Criação de tarefas
  - Edição de tarefas
  - Atribuição de tarefas

- **Gestão de Equipe**
  - Listagem de membros
  - Visualização de roles e status
  - Tags de habilidades

- **UI/UX**
  - Design responsivo (mobile-first)
  - Sistema de modais
  - Notificações toast
  - Ícones Phosphor
  - Sidebar retrátil
  - Navegação SPA

## 🎨 Design

- **Cores principais:**
  - Primary: `#6366f1` (Indigo)
  - Secondary: `#8b5cf6` (Purple)
  - Success: `#10b981` (Green)
  - Warning: `#f59e0b` (Amber)
  - Danger: `#ef4444` (Red)

- **Bibliotecas:**
  - [Phosphor Icons](https://phosphoricons.com/) - Ícones
  - [Chart.js](https://www.chartjs.org/) - Gráficos

## 🔧 Configuração

### 1. Configurar a URL da API

Edite `scripts/config.js` e ajuste a URL do backend:

```javascript
const API_CONFIG = {
    BASE_URL: 'http://localhost:8080',
    // ...
};
```

### 2. Iniciar o Backend

Certifique-se de que o backend Spring Boot está rodando na porta 8080.

### 3. Abrir o Frontend

Abra o arquivo `index.html` em um navegador moderno ou use um servidor local:

```bash
# Usando Python
python -m http.server 8000

# Usando Node.js (http-server)
npx http-server

# Usando PHP
php -S localhost:8000
```

Acesse: `http://localhost:8000`

## 📋 Endpoints da API

O frontend está integrado com os seguintes endpoints:

### Autenticação
- `POST /users/login` - Login
- `POST /users/createADM` - Criar admin

### Usuários
- `GET /users` - Listar usuários
- `POST /users` - Criar usuário (autenticado)

### Empresas
- `POST /company` - Criar empresa
- `PUT /company/{id}` - Editar empresa

### Projetos
- `GET /projects` - Listar projetos
- `POST /projects` - Criar projeto

### Tarefas
- `POST /task` - Criar tarefa
- `PUT /task/editar` - Editar tarefa
- `PUT /task/atribuicao` - Atribuir tarefa

### Tags
- `GET /tags` - Listar tags
- `POST /tags` - Criar tag

## 🚀 Como Usar

### 1. Primeiro Acesso

1. Clique em "Criar conta de administrador"
2. Preencha os dados:
   - Nome completo
   - Email
   - CPF
   - Senha
3. Faça login com as credenciais

### 2. Dashboard

- Visualize estatísticas gerais
- Veja gráficos de produtividade
- Acesse projetos recentes

### 3. Criar Projeto

1. Clique em "Novo Projeto"
2. Preencha os dados:
   - Nome do projeto
   - Descrição
   - Data de início
   - Data de término
3. O projeto será criado com status "Planejamento"

### 4. Gerenciar Tarefas

1. Acesse a página "Tarefas"
2. Visualize o quadro Kanban
3. Crie, edite ou atribua tarefas

### 5. Gerenciar Equipe

1. Acesse a página "Equipe"
2. Visualize todos os membros
3. Adicione novos membros

## 🔒 Segurança

- Tokens JWT armazenados em `localStorage`
- Headers de autenticação automáticos
- Redirecionamento automático em caso de token expirado
- Validação de formulários

## 📱 Responsividade

O sistema é totalmente responsivo:

- **Desktop**: Sidebar fixa, layout em grade
- **Tablet**: Sidebar retrátil, layout adaptativo
- **Mobile**: Menu hamburger, layout empilhado

## 🎯 Próximas Melhorias

- [ ] Detalhes completos de projetos (modal)
- [ ] Detalhes completos de tarefas (modal)
- [ ] Drag and drop no Kanban
- [ ] Filtros avançados
- [ ] Relatórios em PDF
- [ ] Sistema de notificações em tempo real
- [ ] Upload de arquivos
- [ ] Comentários em tarefas
- [ ] Histórico de alterações
- [ ] Temas claro/escuro

## 🐛 Troubleshooting

### Token Expirado
Se você receber erro 401, faça logout e login novamente.

### CORS Error
Certifique-se de que o backend está configurado para permitir CORS:

```java
@CrossOrigin(origins = "http://localhost:8000")
```

### Dados não carregam
1. Verifique se o backend está rodando
2. Abra o DevTools (F12) e veja o Console
3. Verifique a aba Network para ver os requests

## 📄 Licença

Este projeto faz parte do sistema Plen desenvolvido para o hackathon.

---

**Desenvolvido com ❤️ para gestão eficiente de projetos**
