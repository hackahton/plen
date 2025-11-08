// ========================================
// EQUIPE / USUÁRIOS
// ========================================

// Carregar membros da equipe
const loadTeamMembers = async () => {
    try {
        // Verificar se está autenticado
        if (!window.auth || !window.auth.isAuthenticated()) {
            console.log('Usuário não autenticado');
            displayTeamMembers([]);
            return;
        }
        
        const users = await API.Users.getAll();
        displayTeamMembers(users);
    } catch (error) {
        console.error('Erro ao carregar equipe:', error);
        showToast('Erro ao carregar membros da equipe', 'error');
    }
};

// Exibir membros da equipe
const displayTeamMembers = (users) => {
    const container = document.getElementById('teamMembersList');
    if (!container) return;
    
    if (users.length === 0) {
        container.innerHTML = `
            <div style="text-align: center; padding: 3rem;">
                <i class="ph ph-users" style="font-size: 64px; color: var(--gray-300);"></i>
                <p style="color: var(--text-secondary); margin-top: 1rem;">Nenhum membro encontrado</p>
            </div>
        `;
        return;
    }
    
    container.innerHTML = users.map(user => `
        <div class="team-member-card">
            <div class="member-avatar" style="background: ${getAvatarColor(user.name)}">
                ${getInitials(user.name)}
            </div>
            <div class="member-info">
                <div class="member-name">${user.name}</div>
                <div class="member-email">${user.email}</div>
                <div class="member-tags">
                    <span class="member-tag member-role">${formatRole(user.role)}</span>
                    <span class="member-status status-${user.status === 'ATIVO' ? 'active' : 'inactive'}">
                        ${formatUserStatus(user.status)}
                    </span>
                    ${user.tags && user.tags.length > 0 ? user.tags.map(tag => `
                        <span class="member-tag">${tag.name}</span>
                    `).join('') : ''}
                </div>
            </div>
        </div>
    `).join('');
};

// Formatar role
const formatRole = (role) => {
    const map = {
        'ADMIN': 'Administrador',
        'GERENTE': 'Gerente',
        'DESENVOLVEDOR': 'Desenvolvedor',
        'DESIGNER': 'Designer',
        'TESTER': 'Testador'
    };
    return map[role] || role;
};

// Formatar status do usuário
const formatUserStatus = (status) => {
    const map = {
        'ATIVO': 'Ativo',
        'INATIVO': 'Inativo',
        'PENDENTE': 'Pendente'
    };
    return map[status] || status;
};

// Criar novo membro
const createMember = async (userData) => {
    try {
        const response = await API.Users.create(userData);
        showToast('Membro adicionado com sucesso!', 'success');
        loadTeamMembers();
        return response;
    } catch (error) {
        console.error('Erro ao criar membro:', error);
        showToast('Erro ao adicionar membro: ' + error.message, 'error');
        throw error;
    }
};

// Inicializar página de equipe
const initTeamsPage = () => {
    loadTeamMembers();
    
    // Botão adicionar membro
    const addMemberBtn = document.getElementById('addMemberBtn');
    if (addMemberBtn) {
        addMemberBtn.addEventListener('click', () => {
            // TODO: Abrir modal para adicionar membro
            showToast('Adicionar membro em desenvolvimento', 'info');
        });
    }
};

// Exportar para uso global
window.teams = {
    load: loadTeamMembers,
    create: createMember,
    init: initTeamsPage
};
