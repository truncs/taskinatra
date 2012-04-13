package id.wolfe.taskinatra.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Represents a person that uses the system.
 */
public class Person {

    @Min(5)
    @Max(10)
    private String login;

    @JsonIgnore // we don't want to send this EVER
    private String password;

    @Min(3)
    @Max(10)
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
