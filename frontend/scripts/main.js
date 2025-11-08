// ========================================
// APLICAÇÃO PRINCIPAL
// ========================================

// Navegação entre páginas
const navigateTo = (pageName) => {
    // Atualizar itens de navegação
    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('active');
        if (item.dataset.page === pageName) {
            item.classList.add('active');
        }
    });
    
    // Atualizar páginas
    document.querySelectorAll('.page').forEach(page => {
        page.classList.remove('active');
    });
    
    const activePage = document.getElementById(`${pageName}Page`);
    if (activePage) {
        activePage.classList.add('active');
    }
    
    // Atualizar título
    const titles = {
        dashboard: 'Dashboard',
        projects: 'Projetos',
        tasks: 'Tarefas',
        teams: 'Equipe',
        reports: 'Relatórios',
        settings: 'Configurações'
    };
    
    document.getElementById('pageTitle').textContent = titles[pageName] || pageName;
    
    // Inicializar dados da página
    initPageData(pageName);
};

// Inicializar dados da página
const initPageData = (pageName) => {
    // Não inicializar se não estiver autenticado
    if (!window.auth || !window.auth.isAuthenticated()) {
        console.log('Usuário não autenticado - não inicializando dados da página');
        return;
    }
    
    switch (pageName) {
        case 'dashboard':
            if (window.dashboard) window.dashboard.init();
            break;
        case 'projects':
            if (window.projects) window.projects.init();
            break;
        case 'tasks':
            if (window.tasks) window.tasks.init();
            break;
        case 'teams':
            if (window.teams) window.teams.init();
            break;
    }
};

// Controle de modais
const openModal = (modalId) => {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add('active');
        document.body.style.overflow = 'hidden';
    }
};

const closeModal = (modalId) => {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove('active');
        document.body.style.overflow = '';
    }
};

// Sistema de notificações toast
const showToast = (message, type = 'info') => {
    // Criar container se não existir
    let container = document.querySelector('.toast-container');
    if (!container) {
        container = document.createElement('div');
        container.className = 'toast-container';
        document.body.appendChild(container);
    }
    
    // Criar toast
    const toast = document.createElement('div');
    toast.className = `toast alert alert-${type}`;
    
    const icon = {
        success: 'check-circle',
        error: 'x-circle',
        warning: 'warning',
        info: 'info'
    }[type] || 'info';
    
    toast.innerHTML = `
        <i class="ph ph-${icon}"></i>
        <span>${message}</span>
    `;
    
    container.appendChild(toast);
    
    // Remover após 3 segundos
    setTimeout(() => {
        toast.style.animation = 'slideInRight 0.3s ease reverse';
        setTimeout(() => {
            container.removeChild(toast);
            if (container.children.length === 0) {
                document.body.removeChild(container);
            }
        }, 300);
    }, 3000);
};

// Toggle da sidebar em mobile
const toggleSidebar = () => {
    const sidebar = document.getElementById('sidebar');
    if (sidebar) {
        sidebar.classList.toggle('active');
    }
};

// Inicialização da aplicação
document.addEventListener('DOMContentLoaded', () => {
    // Event listeners para navegação
    document.querySelectorAll('.nav-item').forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            const pageName = item.dataset.page;
            if (pageName) {
                navigateTo(pageName);
            }
        });
    });
    
    // Toggle mobile menu
    const mobileMenuToggle = document.getElementById('mobileMenuToggle');
    if (mobileMenuToggle) {
        mobileMenuToggle.addEventListener('click', toggleSidebar);
    }
    
    // Fechar modais ao clicar no botão de fechar
    document.querySelectorAll('[data-close]').forEach(btn => {
        btn.addEventListener('click', () => {
            const modalId = btn.dataset.close;
            closeModal(modalId);
        });
    });
    
    // Fechar modal ao clicar fora
    document.querySelectorAll('.modal').forEach(modal => {
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                closeModal(modal.id);
            }
        });
    });
    
    // Prevenir fechamento ao clicar dentro do modal
    document.querySelectorAll('.modal-content').forEach(content => {
        content.addEventListener('click', (e) => {
            e.stopPropagation();
        });
    });
    
    // Inicializar primeira página se autenticado
    if (window.auth && window.auth.isAuthenticated()) {
        navigateTo('dashboard');
    }
});

// Exportar funções globais
window.navigateTo = navigateTo;
window.openModal = openModal;
window.closeModal = closeModal;
window.showToast = showToast;
window.toggleSidebar = toggleSidebar;
window.formatDate = formatDate;
window.formatStatus = formatStatus;
window.getStatusClass = getStatusClass;
window.getInitials = getInitials;
window.getAvatarColor = getAvatarColor;
