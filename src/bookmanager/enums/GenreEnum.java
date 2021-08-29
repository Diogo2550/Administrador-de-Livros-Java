package bookmanager.enums;

public enum GenreEnum {
    Action(1),
    Drama(2),
    Romance(3),
    Adventure(4),
    SciFi(5),
    Horror(6);

    private GenreEnum(int genre) { }

    public static GenreEnum fromInteger(int x) {
        switch(x) {
        case 1:
            return Action;
        case 2:
            return Drama;
        case 3:
            return Romance;
        case 4:
            return Adventure;
        case 5:
            return SciFi;
        case 6:
            return Horror;
        }
        return null;
    }
}
