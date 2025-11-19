package com.linguacards.app.model;

public enum ProgressStatus {
    NEW,
    IN_PROGRESS,
    LEARNED,
    REVIEW //если получена ошибка (failCount > 0)
}
