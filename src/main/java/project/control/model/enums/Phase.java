package project.control.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Phase {
    PLANNED("Запланировано"),
    IN_PROCESS("В процессе"),
    FINISHED("Закончено");

    private String title;

    public Phase getByTitle(String title) {
        for (Phase phase: Phase.values()) {
            if (phase.title.equalsIgnoreCase(title)) {
                return phase;
            }
        }
        return null;
    }
}