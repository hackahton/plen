// ========================================
// DASHBOARD
// ========================================

let dashboardCharts = {};

// Inicializar Dashboard
const initDashboard = async () => {
    try {
        await loadDashboardStats();
        initCharts();
        await loadRecentProjects();
    } catch (error) {
        console.error('Erro ao carregar dashboard:', error);
        showToast('Erro ao carregar dados do dashboard', 'error');
    }
};

// Carregar estatísticas
const loadDashboardStats = async () => {
    try {
        // Verificar se está autenticado
        if (!window.auth || !window.auth.isAuthenticated()) {
            console.log('Usuário não autenticado, aguardando login...');
            return { projects: [], tasks: [], users: [] };
        }
        
        // Carregar projetos
        const projects = await API.Projects.getAll().catch(() => []);
        const activeProjects = projects.filter(p => p.status === 'EM_ANDAMENTO').length;
        document.getElementById('activeProjects').textContent = activeProjects;
        
        // Carregar tarefas
        const tasks = await API.Tasks.getAll().catch(() => []);
        const pendingTasks = tasks.filter(t => t.status !== 'CONCLUIDO').length;
        document.getElementById('pendingTasks').textContent = pendingTasks;
        
        // Carregar usuários
        const users = await API.Users.getAll().catch(() => []);
        document.getElementById('teamMembers').textContent = users.length;
        
        // Horas (placeholder - implementar quando houver endpoint)
        document.getElementById('totalHours').textContent = '0h';
        
        // Retornar dados para uso nos gráficos
        return { projects, tasks, users };
    } catch (error) {
        console.error('Erro ao carregar estatísticas:', error);
        return { projects: [], tasks: [], users: [] };
    }
};

// Inicializar gráficos
const initCharts = () => {
    // Destruir gráficos existentes se houver
    if (dashboardCharts.tasks) {
        dashboardCharts.tasks.destroy();
    }
    if (dashboardCharts.projects) {
        dashboardCharts.projects.destroy();
    }
    
    // Gráfico de Tarefas por Status
    const tasksCtx = document.getElementById('tasksStatusChart');
    if (tasksCtx) {
        dashboardCharts.tasks = new Chart(tasksCtx, {
            type: 'doughnut',
            data: {
                labels: ['Pendente', 'Em Andamento', 'Concluído'],
                datasets: [{
                    data: [10, 5, 15],
                    backgroundColor: [
                        '#f59e0b',
                        '#3b82f6',
                        '#10b981'
                    ],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        position: 'bottom'
                    }
                }
            }
        });
    }
    
    // Gráfico de Projetos por Mês
    const projectsCtx = document.getElementById('projectsChart');
    if (projectsCtx) {
        dashboardCharts.projects = new Chart(projectsCtx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun'],
                datasets: [{
                    label: 'Projetos',
                    data: [3, 5, 4, 7, 6, 8],
                    borderColor: '#6366f1',
                    backgroundColor: 'rgba(99, 102, 241, 0.1)',
                    tension: 0.4,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
};

// Atualizar dados dos gráficos
const updateChartsData = (data) => {
    const { tasks, projects } = data;
    
    // Atualizar gráfico de tarefas
    if (dashboardCharts.tasks && tasks) {
        const pendente = tasks.filter(t => t.status === 'PENDENTE').length;
        const emAndamento = tasks.filter(t => t.status === 'EM_ANDAMENTO').length;
        const concluido = tasks.filter(t => t.status === 'CONCLUIDO').length;
        
        dashboardCharts.tasks.data.datasets[0].data = [pendente, emAndamento, concluido];
        dashboardCharts.tasks.update();
    }
};

// Carregar projetos recentes
const loadRecentProjects = async () => {
    try {
        // Verificar se está autenticado
        if (!window.auth || !window.auth.isAuthenticated()) {
            console.log('Usuário não autenticado, aguardando login...');
            return;
        }
        
        const projects = await API.Projects.getAll();
        const recentProjects = projects.slice(0, 5); // Últimos 5 projetos
        
        const container = document.getElementById('recentProjectsList');
        if (!container) return;
        
        if (recentProjects.length === 0) {
            container.innerHTML = '<p style="text-align: center; color: var(--text-secondary); padding: 2rem;">Nenhum projeto encontrado</p>';
            return;
        }
        
        container.innerHTML = recentProjects.map(project => `
            <div class="project-item" onclick="viewProject('${project.id}')">
                <div class="project-icon">
                    <i class="ph ph-folder"></i>
                </div>
                <div class="project-info">
                    <div class="project-name">${project.name}</div>
                    <div class="project-meta">
                        <span><i class="ph ph-calendar"></i> ${formatDate(project.startDate)}</span>
                        <span><i class="ph ph-users"></i> ${project.teamCount || 0} membros</span>
                    </div>
                </div>
                <span class="project-status status-${getStatusClass(project.status)}">
                    ${formatStatus(project.status)}
                </span>
            </div>
        `).join('');
    } catch (error) {
        console.error('Erro ao carregar projetos recentes:', error);
    }
};

// Helpers
const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
};

const formatStatus = (status) => {
    const statusMap = {
        'PLANEJAMENTO': 'Planejamento',
        'EM_ANDAMENTO': 'Em Andamento',
        'CONCLUIDO': 'Concluído',
        'CANCELADO': 'Cancelado'
    };
    return statusMap[status] || status;
};

const getStatusClass = (status) => {
    const classMap = {
        'PLANEJAMENTO': 'planning',
        'EM_ANDAMENTO': 'in-progress',
        'CONCLUIDO': 'completed',
        'CANCELADO': 'cancelled'
    };
    return classMap[status] || '';
};

const viewProject = (projectId) => {
    // Navegar para a página de projetos e abrir o projeto
    navigateTo('projects');
    // TODO: Abrir modal com detalhes do projeto
    console.log('Ver projeto:', projectId);
};

// Exportar para uso global
window.dashboard = {
    init: initDashboard,
    loadStats: loadDashboardStats,
    updateCharts: updateChartsData
};
