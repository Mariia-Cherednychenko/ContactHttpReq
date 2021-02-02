package menuAction;

public interface MenuAction {

    String getName();
    void execute();
    default boolean closeAfter(){
        return false;
    }
    String getToken();

    void setToken(String token);

    boolean isVisible();
}