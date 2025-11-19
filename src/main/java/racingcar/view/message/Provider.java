package racingcar.view.message;

import java.util.ResourceBundle;
import racingcar.system.Language;
import racingcar.system.Utf8ResourceBundle;

public class Provider {

    private static final ResourceBundle.Control UTF8_CONTROL = new Utf8ResourceBundle();

    private final ResourceBundle messages;

    public Provider(ResourceBundle messages) {
        this.messages = messages;
    }

    public static Provider fetchBy(Language language) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", language.locale(), UTF8_CONTROL);

        return new Provider(bundle);
    }

    public String messageOf(MessageKey messageKey) {
        return messages.getString(messageKey.name());
    }
}
