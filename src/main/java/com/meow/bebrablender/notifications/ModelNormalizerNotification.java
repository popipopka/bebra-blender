package com.meow.bebrablender.notifications;

public enum ModelNormalizerNotification implements Notification {
    MODEL_NORMALIZER_NOTIFICATION(
            "Предупреждаем, что программа автоматически переводит" +
                    " нетреугольные полигоны в треугольные, а потому за работоспособность" +
                    " программы на моделях с текстурой мы не отвечаем."
    );

    private final String message;

    ModelNormalizerNotification(String message) {
        this.message = message;
    }

    @Override
    public String get() {
        return this.message;
    }
}
