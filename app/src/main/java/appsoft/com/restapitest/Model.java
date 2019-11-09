package appsoft.com.restapitest;

import com.google.gson.annotations.SerializedName;

public class Model
{
    // user_name, user_password, user_type
    @SerializedName("user_name")
    private String name;
    @SerializedName("user_password")
    private String password;
    @SerializedName("user_type")
    private String type;

    public Model(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}
