// ========================================
// CONFIGURAÇÃO DA API
// ========================================

const API_CONFIG = {
    BASE_URL: 'http://localhost:8080',
    ENDPOINTS: {
        // Auth
        LOGIN: '/users/login',
        REGISTER_ADMIN: '/users/createADM',
        
        // Users
        USERS: '/users',
        
        // Companies
        COMPANIES: '/company',
        
        // Projects
        PROJECTS: '/projects',
        
        // Tasks
        TASKS: '/task',
        TASK_EDIT: '/task/editar',
        TASK_ASSIGN: '/task/atribuicao',
        
        // Tags
        TAGS: '/tags'
    }
};

// Headers padrão
const getHeaders = (includeAuth = true) => {
    const headers = {
        'Content-Type': 'application/json'
    };
    
    if (includeAuth) {
        const token = localStorage.getItem('token');
        if (token) {
            headers['Authorization'] = `Bearer ${token}`;
        }
    }
    
    return headers;
};

// Helper para fazer requisições
const request = async (endpoint, options = {}) => {
    const url = `${API_CONFIG.BASE_URL}${endpoint}`;
    
    try {
        const response = await fetch(url, {
            ...options,
            headers: getHeaders(options.auth !== false)
        });
        
        if (!response.ok) {
            if (response.status === 401) {
                // Token inválido ou expirado
                localStorage.removeItem('token');
                localStorage.removeItem('user');
                window.location.reload();
                throw new Error('Sessão expirada. Faça login novamente.');
            }
            
            const errorData = await response.json().catch(() => ({}));
            throw new Error(errorData.message || `Erro: ${response.status}`);
        }
        
        // Se não houver conteúdo, retornar null
        if (response.status === 204) {
            return null;
        }
        
        return await response.json();
    } catch (error) {
        console.error(`Erro na requisição ${endpoint}:`, error);
        throw error;
    }
};

// Exportar para uso global
window.API_CONFIG = API_CONFIG;
window.request = request;
