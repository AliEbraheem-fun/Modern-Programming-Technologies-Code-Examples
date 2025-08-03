package lecture.eight.student.model;

/**
 * Enum representing user roles in the system.
 * <ul>
 *     <li>{@code USER} — обычный пользователь, имеет доступ только к просмотру студентов.</li>
 *     <li>{@code ADMIN} — администратор, может управлять студентами (добавление, редактирование, удаление).</li>
 *     <li>{@code SUPER_ADMIN} — главный администратор, имеет доступ ко всем возможностям, включая управление пользователями.</li>
 * </ul>
 */
public enum Role {
    USER,
    ADMIN,
    SUPER_ADMIN
}
