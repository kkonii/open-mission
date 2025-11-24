package racingcar.view;

public class Formatter {

    private static final String BANNER_LINE = "=============================";

    private static final String DISTANCE_SCALE = "           0    5    10   15";
    private static final String MOVE_FORWARD = "█";
    private static final String CURRENT_POSITION = "▮";

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String WHITE_SPACE = " ";

    public static String banner(String title) {
        int width = BANNER_LINE.length();
        String centeredTitle = center(title, width);

        return BANNER_LINE + LINE_SEPARATOR + centeredTitle + LINE_SEPARATOR + BANNER_LINE;
    }

    private static String center(String text, int width) {
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;

        return WHITE_SPACE.repeat(left) + text + WHITE_SPACE.repeat(right);
    }

    public static String bannerLine() {
        return LINE_SEPARATOR + BANNER_LINE;
    }

    public static String distanceScale() {
        return DISTANCE_SCALE;
    }

    public static String distanceBlock(int distance) {
        if (distance <= 0) {
            return CURRENT_POSITION;
        }

        return MOVE_FORWARD.repeat(distance) + CURRENT_POSITION;
    }
}
