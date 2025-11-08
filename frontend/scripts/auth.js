// ========================================
// AUTENTICAÇÃO
// ========================================

// Verificar se usuário está autenticado
const isAuthenticated = () => {
    const token = localStorage.getItem('token');
    const user = localStorage.getItem('user');
    return !!(token && user);
};

// Obter usuário atual
const getCurrentUser = () => {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
};

// Salvar dados de autenticação
const saveAuth = (token, user) => {
    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(user));
};

// Limpar autenticação
const clearAuth = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
};

// Login
const login = async (email, password) => {
    try {
        const response = await request(API_CONFIG.ENDPOINTS.LOGIN, {
            method: 'POST',
            auth: false,
            body: JSON.stringify({ email, password })
        });
        
        if (response && response.token) {
            // Salvar token e dados do usuário
            saveAuth(response.token, {
                id: response.id,
                name: response.name,
                email: response.email,
                role: response.role
            });
            
            return response;
        }
        
        throw new Error('Resposta de login inválida');
    } catch (error) {
        console.error('Erro no login:', error);
        throw error;
    }
};

// Registro de Admin
const registerAdmin = async (userData) => {
    try {
        const response = await request(API_CONFIG.ENDPOINTS.REGISTER_ADMIN, {
            method: 'POST',
            auth: false,
            body: JSON.stringify(userData)
        });
        
        return response;
    } catch (error) {
        console.error('Erro no registro:', error);
        throw error;
    }
};

// Logout
const logout = () => {
    clearAuth();
    window.location.reload();
};

// Inicializar autenticação na página
const initAuth = () => {
    const loginScreen = document.getElementById('loginScreen');
    const mainApp = document.getElementById('mainApp');
    
    if (isAuthenticated()) {
        // Usuário autenticado - mostrar app
        loginScreen.style.display = 'none';
        mainApp.style.display = 'grid';
        
        // Atualizar informações do usuário na sidebar
        const user = getCurrentUser();
        if (user) {
            document.getElementById('userName').textContent = user.name;
            document.getElementById('userRole').textContent = user.role;
        }
    } else {
        // Usuário não autenticado - mostrar login
        loginScreen.style.display = 'flex';
        mainApp.style.display = 'none';
    }
};

// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    // Formulário de login
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            
            const email = document.getElementById('loginEmail').value;
            const password = document.getElementById('loginPassword').value;
            const submitBtn = loginForm.querySelector('button[type="submit"]');
            
            try {
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<span class="loading"></span> Entrando...';
                
                await login(email, password);
                
                // Redirecionar para o app
                window.location.reload();
            } catch (error) {
                alert('Erro ao fazer login: ' + error.message);
                submitBtn.disabled = false;
                submitBtn.innerHTML = '<i class="ph ph-sign-in"></i> Entrar';
            }
        });
    }
    
    // Link para mostrar modal de registro
    const showRegisterLink = document.getElementById('showRegisterLink');
    if (showRegisterLink) {
        showRegisterLink.addEventListener('click', (e) => {
            e.preventDefault();
            openModal('registerModal');
        });
    }
    
    // Formulário de registro
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            
            const formData = new FormData(registerForm);
            const userData = {
                name: formData.get('name'),
                email: formData.get('email'),
                cpf: formData.get('cpf'),
                password: formData.get('password'),
                role: 'ADMIN'
            };
            
            const submitBtn = registerForm.querySelector('button[type="submit"]');
            
            try {
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<span class="loading"></span> Criando...';
                
                await registerAdmin(userData);
                
                alert('Conta criada com sucesso! Faça login para continuar.');
                closeModal('registerModal');
                registerForm.reset();
            } catch (error) {
                alert('Erro ao criar conta: ' + error.message);
            } finally {
                submitBtn.disabled = false;
                submitBtn.innerHTML = 'Criar Conta';
            }
        });
    }
    
    // Botão de logout
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            if (confirm('Deseja realmente sair?')) {
                logout();
            }
        });
    }
    
    // Inicializar autenticação
    initAuth();
});

// Exportar para uso global
window.auth = {
    isAuthenticated,
    getCurrentUser,
    login,
    registerAdmin,
    logout,
    saveAuth,
    clearAuth
};
