package project.control.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Priority {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий"),
    EMERGENCY("Чрезвычайный");

    private String title;

    public Priority getByTitle(String title) {
        for (Priority priority : Priority.values()) {
            if (priority.title.equalsIgnoreCase(title)) {
                return priority;
            }
        }
        return null;
    }
}