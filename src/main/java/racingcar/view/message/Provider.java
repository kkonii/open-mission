package racingcar.view.message;

import java.util.ResourceBundle;
import racingcar.system.Language;

public class Provider {

    private final ResourceBundle messages;

    public Provider(ResourceBundle messages) {
        this.messages = messages;
    }

    public static Provider fetchBy(Language language) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", language.locale());

        return new Provider(bundle);
    }

    public String messageOf(MessageKey messageKey) {
        return messages.getString(messageKey.name());
    }
}
