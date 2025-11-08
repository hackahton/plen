// ========================================
// API SERVICES
// ========================================

// Projects API
const ProjectsAPI = {
    // Criar projeto
    create: async (projectData) => {
        return await request(API_CONFIG.ENDPOINTS.PROJECTS, {
            method: 'POST',
            body: JSON.stringify(projectData)
        });
    },
    
    // Listar todos os projetos
    getAll: async () => {
        return await request(API_CONFIG.ENDPOINTS.PROJECTS, {
            method: 'GET'
        });
    },
    
    // Buscar projeto por ID
    getById: async (id) => {
        return await request(`${API_CONFIG.ENDPOINTS.PROJECTS}/${id}`, {
            method: 'GET'
        });
    },
    
    // Atualizar projeto
    update: async (id, projectData) => {
        return await request(`${API_CONFIG.ENDPOINTS.PROJECTS}/${id}`, {
            method: 'PUT',
            body: JSON.stringify(projectData)
        });
    },
    
    // Deletar projeto
    delete: async (id) => {
        return await request(`${API_CONFIG.ENDPOINTS.PROJECTS}/${id}`, {
            method: 'DELETE'
        });
    }
};

// Tasks API
const TasksAPI = {
    // Criar tarefa
    create: async (taskData) => {
        return await request(API_CONFIG.ENDPOINTS.TASKS, {
            method: 'POST',
            body: JSON.stringify(taskData)
        });
    },
    
    // Editar tarefa
    edit: async (taskData) => {
        return await request(API_CONFIG.ENDPOINTS.TASK_EDIT, {
            method: 'PUT',
            body: JSON.stringify(taskData)
        });
    },
    
    // Atribuir tarefa
    assign: async (assignmentData) => {
        return await request(API_CONFIG.ENDPOINTS.TASK_ASSIGN, {
            method: 'PUT',
            body: JSON.stringify(assignmentData)
        });
    },
    
    // Listar todas as tarefas
    getAll: async () => {
        return await request(API_CONFIG.ENDPOINTS.TASKS, {
            method: 'GET'
        });
    }
};

// Users API
const UsersAPI = {
    // Criar usuário (requer autenticação)
    create: async (userData) => {
        return await request(API_CONFIG.ENDPOINTS.USERS, {
            method: 'POST',
            body: JSON.stringify(userData)
        });
    },
    
    // Listar todos os usuários
    getAll: async () => {
        return await request(API_CONFIG.ENDPOINTS.USERS, {
            method: 'GET'
        });
    },
    
    // Buscar usuário por ID
    getById: async (id) => {
        return await request(`${API_CONFIG.ENDPOINTS.USERS}/${id}`, {
            method: 'GET'
        });
    }
};

// Companies API
const CompaniesAPI = {
    // Criar empresa
    create: async (companyData) => {
        return await request(API_CONFIG.ENDPOINTS.COMPANIES, {
            method: 'POST',
            body: JSON.stringify(companyData)
        });
    },
    
    // Editar empresa
    edit: async (id, companyData) => {
        return await request(`${API_CONFIG.ENDPOINTS.COMPANIES}/${id}`, {
            method: 'PUT',
            body: JSON.stringify(companyData)
        });
    },
    
    // Listar empresas
    getAll: async () => {
        return await request(API_CONFIG.ENDPOINTS.COMPANIES, {
            method: 'GET'
        });
    }
};

// Tags API
const TagsAPI = {
    // Criar tag
    create: async (tagData) => {
        return await request(API_CONFIG.ENDPOINTS.TAGS, {
            method: 'POST',
            body: JSON.stringify(tagData)
        });
    },
    
    // Listar todas as tags
    getAll: async () => {
        return await request(API_CONFIG.ENDPOINTS.TAGS, {
            method: 'GET'
        });
    }
};

// Exportar para uso global
window.API = {
    Projects: ProjectsAPI,
    Tasks: TasksAPI,
    Users: UsersAPI,
    Companies: CompaniesAPI,
    Tags: TagsAPI
};
