// ========================================
// PROJETOS
// ========================================

// Carregar todos os projetos
const loadProjects = async (filters = {}) => {
    try {
        // Verificar se está autenticado
        if (!window.auth || !window.auth.isAuthenticated()) {
            console.log('Usuário não autenticado');
            displayProjects([]);
            return;
        }
        
        const projects = await API.Projects.getAll();
        
        // Aplicar filtros
        let filteredProjects = projects;
        
        if (filters.search) {
            const searchLower = filters.search.toLowerCase();
            filteredProjects = filteredProjects.filter(p => 
                p.name.toLowerCase().includes(searchLower) ||
                (p.description && p.description.toLowerCase().includes(searchLower))
            );
        }
        
        if (filters.status) {
            filteredProjects = filteredProjects.filter(p => p.status === filters.status);
        }
        
        displayProjects(filteredProjects);
    } catch (error) {
        console.error('Erro ao carregar projetos:', error);
        showToast('Erro ao carregar projetos', 'error');
    }
};

// Exibir projetos na grade
const displayProjects = (projects) => {
    const container = document.getElementById('projectsGrid');
    if (!container) return;
    
    if (projects.length === 0) {
        container.innerHTML = `
            <div style="grid-column: 1/-1; text-align: center; padding: 3rem;">
                <i class="ph ph-folder" style="font-size: 64px; color: var(--gray-300);"></i>
                <p style="color: var(--text-secondary); margin-top: 1rem;">Nenhum projeto encontrado</p>
            </div>
        `;
        return;
    }
    
    container.innerHTML = projects.map(project => `
        <div class="project-card" onclick="viewProjectDetails('${project.id}')">
            <div class="project-card-header">
                <div class="project-card-title">${project.name}</div>
                <div class="project-card-description">${project.description || 'Sem descrição'}</div>
            </div>
            <div class="project-card-body">
                <div class="project-card-stats">
                    <div class="project-stat">
                        <i class="ph ph-calendar"></i>
                        ${formatDate(project.startDate)}
                    </div>
                    <div class="project-stat">
                        <i class="ph ph-clock"></i>
                        ${formatDate(project.endDate)}
                    </div>
                    <div class="project-stat">
                        <i class="ph ph-check-square"></i>
                        ${project.taskCount || 0} tarefas
                    </div>
                    <div class="project-stat">
                        <i class="ph ph-users"></i>
                        ${project.teamCount || 0} membros
                    </div>
                </div>
            </div>
            <div class="project-card-footer">
                <span class="project-status status-${getStatusClass(project.status)}">
                    ${formatStatus(project.status)}
                </span>
                <div class="project-team">
                    ${generateTeamAvatars(project.team || [])}
                </div>
            </div>
        </div>
    `).join('');
};

// Gerar avatares da equipe
const generateTeamAvatars = (team) => {
    if (!team || team.length === 0) {
        return '<span style="font-size: 12px; color: var(--text-secondary);">Sem membros</span>';
    }
    
    const maxDisplay = 3;
    const displayed = team.slice(0, maxDisplay);
    const remaining = team.length - maxDisplay;
    
    let html = displayed.map(member => `
        <div class="team-avatar" style="background: ${getAvatarColor(member.name)}">
            ${getInitials(member.name)}
        </div>
    `).join('');
    
    if (remaining > 0) {
        html += `<div class="team-avatar">+${remaining}</div>`;
    }
    
    return html;
};

// Obter iniciais do nome
const getInitials = (name) => {
    if (!name) return '?';
    const parts = name.split(' ');
    if (parts.length === 1) return parts[0].charAt(0).toUpperCase();
    return (parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase();
};

// Gerar cor de avatar baseada no nome
const getAvatarColor = (name) => {
    const colors = [
        '#6366f1', '#8b5cf6', '#ec4899', '#f59e0b', 
        '#10b981', '#3b82f6', '#ef4444', '#06b6d4'
    ];
    const index = (name || '').charCodeAt(0) % colors.length;
    return colors[index];
};

// Ver detalhes do projeto
const viewProjectDetails = (projectId) => {
    // TODO: Abrir modal com detalhes completos do projeto
    console.log('Ver detalhes do projeto:', projectId);
    showToast('Detalhes do projeto em desenvolvimento', 'info');
};

// Criar novo projeto
const createProject = async (projectData) => {
    try {
        const response = await API.Projects.create(projectData);
        showToast('Projeto criado com sucesso!', 'success');
        closeModal('newProjectModal');
        loadProjects();
        return response;
    } catch (error) {
        console.error('Erro ao criar projeto:', error);
        showToast('Erro ao criar projeto: ' + error.message, 'error');
        throw error;
    }
};

// Inicializar página de projetos
const initProjectsPage = () => {
    // Carregar projetos
    loadProjects();
    
    // Event listeners para filtros
    const searchInput = document.getElementById('projectSearch');
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const statusFilter = document.getElementById('projectStatusFilter').value;
            loadProjects({
                search: e.target.value,
                status: statusFilter
            });
        });
    }
    
    const statusFilter = document.getElementById('projectStatusFilter');
    if (statusFilter) {
        statusFilter.addEventListener('change', (e) => {
            const searchInput = document.getElementById('projectSearch').value;
            loadProjects({
                search: searchInput,
                status: e.target.value
            });
        });
    }
    
    // Botão novo projeto
    const newProjectBtn = document.getElementById('newProjectBtn');
    if (newProjectBtn) {
        newProjectBtn.addEventListener('click', () => {
            openModal('newProjectModal');
        });
    }
    
    // Formulário de novo projeto
    const newProjectForm = document.getElementById('newProjectForm');
    if (newProjectForm) {
        newProjectForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            
            const formData = new FormData(newProjectForm);
            const projectData = {
                name: formData.get('name'),
                description: formData.get('description'),
                startDate: formData.get('startDate'),
                endDate: formData.get('endDate'),
                status: 'PLANEJAMENTO'
            };
            
            const submitBtn = newProjectForm.querySelector('button[type="submit"]');
            
            try {
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<span class="loading"></span> Criando...';
                
                await createProject(projectData);
                newProjectForm.reset();
            } catch (error) {
                // Erro já tratado na função createProject
            } finally {
                submitBtn.disabled = false;
                submitBtn.innerHTML = 'Criar Projeto';
            }
        });
    }
};

// Exportar para uso global
window.projects = {
    load: loadProjects,
    create: createProject,
    init: initProjectsPage
};
