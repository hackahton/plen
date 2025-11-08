// ========================================
// TAREFAS
// ========================================

// Carregar tarefas em formato Kanban
const loadTasksKanban = async () => {
    try {
        // Verificar se está autenticado
        if (!window.auth || !window.auth.isAuthenticated()) {
            console.log('Usuário não autenticado');
            displayTasksKanban([]);
            return;
        }
        
        const tasks = await API.Tasks.getAll();
        displayTasksKanban(tasks);
    } catch (error) {
        console.error('Erro ao carregar tarefas:', error);
        showToast('Erro ao carregar tarefas', 'error');
    }
};

// Exibir tarefas no quadro Kanban
const displayTasksKanban = (tasks) => {
    const container = document.getElementById('tasksKanban');
    if (!container) return;
    
    // Se não houver tarefas e não estiver autenticado, mostrar mensagem
    if ((!tasks || tasks.length === 0) && (!window.auth || !window.auth.isAuthenticated())) {
        container.innerHTML = `
            <div style="grid-column: 1/-1; text-align: center; padding: 3rem;">
                <i class="ph ph-lock" style="font-size: 64px; color: var(--gray-300);"></i>
                <p style="color: var(--text-secondary); margin-top: 1rem;">Faça login para visualizar as tarefas</p>
            </div>
        `;
        return;
    }
    
    // Agrupar tarefas por status
    const columns = [
        { id: 'PENDENTE', title: 'Pendente', icon: 'circle' },
        { id: 'EM_ANDAMENTO', title: 'Em Andamento', icon: 'circle-half' },
        { id: 'CONCLUIDO', title: 'Concluído', icon: 'check-circle' }
    ];
    
    container.innerHTML = columns.map(column => {
        const columnTasks = tasks.filter(t => t.status === column.id);
        
        return `
            <div class="kanban-column">
                <div class="kanban-header">
                    <div class="kanban-title">
                        <i class="ph ph-${column.icon}"></i>
                        ${column.title}
                    </div>
                    <span class="kanban-count">${columnTasks.length}</span>
                </div>
                <div class="kanban-tasks">
                    ${columnTasks.map(task => generateTaskCard(task)).join('')}
                </div>
            </div>
        `;
    }).join('');
};

// Gerar card de tarefa
const generateTaskCard = (task) => {
    return `
        <div class="task-card" onclick="viewTaskDetails('${task.id}')">
            <div class="task-title">${task.title || task.name}</div>
            <div class="task-description">${task.description || 'Sem descrição'}</div>
            <div class="task-meta">
                <div class="task-meta-left">
                    ${task.priority ? `<span class="task-priority priority-${task.priority.toLowerCase()}">${formatPriority(task.priority)}</span>` : ''}
                    ${task.difficulty ? `<div class="task-difficulty"><i class="ph ph-gauge"></i> ${formatDifficulty(task.difficulty)}</div>` : ''}
                </div>
                ${task.assignee ? `<div class="team-avatar" style="background: ${getAvatarColor(task.assignee.name)}">${getInitials(task.assignee.name)}</div>` : ''}
            </div>
        </div>
    `;
};

// Formatar prioridade
const formatPriority = (priority) => {
    const map = {
        'ALTA': 'Alta',
        'MEDIA': 'Média',
        'BAIXA': 'Baixa'
    };
    return map[priority] || priority;
};

// Formatar dificuldade
const formatDifficulty = (difficulty) => {
    const map = {
        'FACIL': 'Fácil',
        'MEDIA': 'Média',
        'DIFICIL': 'Difícil'
    };
    return map[difficulty] || difficulty;
};

// Ver detalhes da tarefa
const viewTaskDetails = (taskId) => {
    // TODO: Abrir modal com detalhes da tarefa
    console.log('Ver tarefa:', taskId);
    showToast('Detalhes da tarefa em desenvolvimento', 'info');
};

// Criar nova tarefa
const createTask = async (taskData) => {
    try {
        const response = await API.Tasks.create(taskData);
        showToast('Tarefa criada com sucesso!', 'success');
        loadTasksKanban();
        return response;
    } catch (error) {
        console.error('Erro ao criar tarefa:', error);
        showToast('Erro ao criar tarefa: ' + error.message, 'error');
        throw error;
    }
};

// Editar tarefa
const editTask = async (taskData) => {
    try {
        const response = await API.Tasks.edit(taskData);
        showToast('Tarefa atualizada com sucesso!', 'success');
        loadTasksKanban();
        return response;
    } catch (error) {
        console.error('Erro ao editar tarefa:', error);
        showToast('Erro ao editar tarefa: ' + error.message, 'error');
        throw error;
    }
};

// Atribuir tarefa
const assignTask = async (taskId, userId) => {
    try {
        const response = await API.Tasks.assign({
            taskId,
            userId
        });
        showToast('Tarefa atribuída com sucesso!', 'success');
        loadTasksKanban();
        return response;
    } catch (error) {
        console.error('Erro ao atribuir tarefa:', error);
        showToast('Erro ao atribuir tarefa: ' + error.message, 'error');
        throw error;
    }
};

// Inicializar página de tarefas
const initTasksPage = () => {
    loadTasksKanban();
    
    // Alternar entre visualizações
    const viewToggles = document.querySelectorAll('.view-toggles .btn-icon');
    viewToggles.forEach(btn => {
        btn.addEventListener('click', () => {
            const view = btn.dataset.view;
            viewToggles.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            
            if (view === 'kanban') {
                loadTasksKanban();
            } else {
                // TODO: Implementar visualização em lista
                showToast('Visualização em lista em desenvolvimento', 'info');
            }
        });
    });
};

// Exportar para uso global
window.tasks = {
    load: loadTasksKanban,
    create: createTask,
    edit: editTask,
    assign: assignTask,
    init: initTasksPage
};
