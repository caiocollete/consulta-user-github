public class User {
    String name;
    String id;
    String avatar;

    public User(ApiInfoCatcher rec){
        name = rec.login();
        id = rec.id().toString();
        avatar = rec.avatar_url();
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getAvatar() {
        return avatar;
    }
}
